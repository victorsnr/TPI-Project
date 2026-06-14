package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import interfaces.*;

import java.util.Vector;

import models.*;

import exceptions.*;

public class DatabaseConstructor {
	
	Connection connection;
	IArtGallery minhaGaleria;
	
	public DatabaseConstructor (IArtGallery minhaGaleria) throws SQLException {
		
		this.minhaGaleria = minhaGaleria;

		this.connection = DatabaseConnection.conectar();

	}
	
	
	public void makeDatabase () {
		try {
			Statement stmt = this.connection.createStatement();
			
			stmt.executeUpdate("""
					CREATE SCHEMA IF NOT EXISTS artgallery
					""");
			
			stmt.executeUpdate("""
					CREATE TABLE IF NOT EXISTS artgallery.obra (
						titulo VARCHAR(100) NOT NULL, 
						autor VARCHAR(100) NOT NULL,
						ativa BOOLEAN NOT NULL, 
						tipo VARCHAR(50) NOT NULL,
						
						PRIMARY KEY (titulo, autor)
					);
					""");
			
			stmt.executeUpdate("""
					CREATE TABLE IF NOT EXISTS artgallery.arte_generativa (
					titulo VARCHAR(100) NOT NULL, 
					autor VARCHAR(100) NOT NULL,
					algoritmo VARCHAR(100) NOT NULL, 
					seed BIGINT NOT NULL,
					
					PRIMARY KEY (titulo, autor),
					
					FOREIGN KEY (titulo, autor)
					REFERENCES artgallery.obra (titulo, autor)
					ON DELETE CASCADE
				);
				""");
			
			stmt.executeUpdate("""
					CREATE TABLE IF NOT EXISTS artgallery.modelagem_3d (
					titulo VARCHAR(100) NOT NULL, 
					autor VARCHAR(100) NOT NULL,
					num_poligonos INTEGER NOT NULL, 
					engine VARCHAR(50) NOT NULL,
					
					PRIMARY KEY (titulo, autor),
					
					FOREIGN KEY (titulo, autor)
					REFERENCES artgallery.obra (titulo, autor)
					ON DELETE CASCADE
				);
				""");
			
			stmt.executeUpdate("""
					CREATE TABLE IF NOT EXISTS artgallery.pintura_digital (
					titulo VARCHAR(100) NOT NULL, 
					autor VARCHAR(100) NOT NULL,
					resolucao VARCHAR(100) NOT NULL, 
					software VARCHAR(50) NOT NULL,
					
					PRIMARY KEY (titulo, autor),
					
					FOREIGN KEY (titulo, autor)
					REFERENCES artgallery.obra (titulo, autor)
					ON DELETE CASCADE
				);
				""");
			
			stmt.executeUpdate("""
					CREATE TABLE IF NOT EXISTS artgallery.avaliacao (
					titulo_obra VARCHAR(100) NOT NULL, 
					autor_obra VARCHAR(100) NOT NULL,
					usuario VARCHAR(100) NOT NULL,
					nota INTEGER NOT NULL CHECK (nota BETWEEN 0 AND 10), 
					comentario TEXT NOT NULL,
					
					PRIMARY KEY (titulo_obra, autor_obra, usuario),
					
					FOREIGN KEY (titulo_obra, autor_obra)
					REFERENCES artgallery.obra (titulo, autor)
					ON DELETE CASCADE
				);
				""");
			
			
			stmt.executeUpdate("""
					CREATE TABLE IF NOT EXISTS artgallery.obra_exposicao (
					titulo_obra VARCHAR(100) NOT NULL,
					autor_obra VARCHAR(100) NOT NULL,
					nome_expo VARCHAR(100) NOT NULL,
					
					PRIMARY KEY (titulo_obra, autor_obra, nome_expo),
					
					FOREIGN KEY (titulo_obra, autor_obra)
					REFERENCES artgallery.obra(titulo, autor)
					ON DELETE CASCADE
					
				);
				""");
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void writeData() {
		
		try (
			PreparedStatement stmtObra = connection.prepareStatement("""
					INSERT INTO artgallery.obra (titulo, autor, ativa, tipo)
					VALUES (?, ?, ?, ?);
					""");
			PreparedStatement stmtAG = connection.prepareStatement("""
					INSERT INTO artgallery.arte_generativa (titulo, autor, algoritmo, seed)
					VALUES (?, ?, ?, ?);
					""");
			PreparedStatement stmtMD = connection.prepareStatement("""
					INSERT INTO artgallery.modelagem_3d (titulo, autor, num_poligonos, engine)
					VALUES (?, ?, ?, ?);
					""");
			PreparedStatement stmtPD = connection.prepareStatement("""
					INSERT INTO artgallery.pintura_digital (titulo, autor, resolucao, software)
					VALUES (?, ?, ?, ?);
					""");
			PreparedStatement stmtAV = connection.prepareStatement("""
					INSERT INTO artgallery.avaliacao (titulo_obra, autor_obra, usuario, nota, comentario)
					VALUES (?, ?, ?, ?, ?);
					""");
			PreparedStatement stmtOE = connection.prepareStatement("""
					INSERT INTO artgallery.obra_exposicao (titulo_obra, autor_obra, nome_expo)
					VALUES (?, ?, ?);
					""");
			
			Statement stmtClearTables = connection.createStatement();
			){
			
			stmtClearTables.executeUpdate("""
					TRUNCATE TABLE
						artgallery.obra_exposicao,
						artgallery.avaliacao,
						artgallery.arte_generativa,
						artgallery.modelagem_3d,
						artgallery.pintura_digital,
						artgallery.obra
					CASCADE;
					""");
			
			Vector<Obra> repo = minhaGaleria.getRepositorio();
			
			for (Obra obra : repo) {
				stmtObra.setString(1, obra.getTitulo());
				stmtObra.setString(2, obra.getAutor());
				stmtObra.setBoolean(3, obra.isAtiva());
				stmtObra.setString(4, obra.getType());
				stmtObra.executeUpdate();
				
				if (obra.getType().equals("Arte Generativa")) {
					stmtAG.setString(1, obra.getTitulo());
					stmtAG.setString(2, obra.getAutor());
					stmtAG.setString(3, ((ArteGenerativa) obra).getAlgoritmo());
					stmtAG.setLong(4, ((ArteGenerativa) obra).getSeed());
					stmtAG.executeUpdate();
					
				} else if (obra.getType().equals("Modelagem 3D")) {
					stmtMD.setString(1, obra.getTitulo());
					stmtMD.setString(2, obra.getAutor());
					stmtMD.setInt(3, ((Modelagem3D) obra).getNumeroPoligonos());
					stmtMD.setString(4, ((Modelagem3D) obra).getEngine());
					stmtMD.executeUpdate();
				} else {
					stmtPD.setString(1, obra.getTitulo());
					stmtPD.setString(2, obra.getAutor());
					stmtPD.setString(3, ((PinturaDigital) obra).getResolucao());
					stmtPD.setString(4, ((PinturaDigital) obra).getSoftwareUtilizado());
					stmtPD.executeUpdate();
				}
				
				Vector<Avaliacao> avaliacoes = obra.getAvaliacoes();
				if (!avaliacoes.isEmpty()) {
					for (Avaliacao avaliacao : avaliacoes) {
						stmtAV.setString(1,  obra.getTitulo());
						stmtAV.setString(2,  obra.getAutor());
						stmtAV.setString(3,  avaliacao.getUsuario());
						stmtAV.setInt(4,  avaliacao.getNota());
						stmtAV.setString(5, avaliacao.getComentario());
						stmtAV.executeUpdate();
					}
				}
			}
			
			Vector<Exposicao> exposicoes = minhaGaleria.getExposicoes();
			
			for (Exposicao expo : exposicoes) {
				
				Vector<Obra> obrasExpo = expo.listarObra();
				
				if (!obrasExpo.isEmpty()) {
					for (Obra obra : obrasExpo) {
						stmtOE.setString(1, obra.getTitulo());
						stmtOE.setString(2, obra.getAutor());
						stmtOE.setString(3, expo.getNome());
						stmtOE.executeUpdate();
					}
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} 
	
	public void readData () {
		try (
			PreparedStatement stmtObra = connection.prepareStatement("""
					SELECT * FROM artgallery.obra;
					""");
			PreparedStatement stmtAV = connection.prepareStatement("""
					SELECT * FROM artgallery.avaliacao;
					""");
			PreparedStatement stmtEX = connection.prepareStatement("""
					SELECT * FROM artgallery.exposicao;
					""");
			PreparedStatement stmtOE = connection.prepareStatement("""
					SELECT * FROM artgallery.obra_exposicao;
					""")
			){
			
			try (ResultSet resultObra = stmtObra.executeQuery()) {
			
				while (resultObra.next()) {
					String titulo = resultObra.getString("titulo");
					 
					String autor = resultObra.getString("autor");
					 
					boolean ativa = resultObra.getBoolean("ativa");
					 
					String tipo = resultObra.getString("tipo");
					 
					Obra obra = null;
					 
					if (tipo.equals("Arte Generativa")) {
						try (
							PreparedStatement stmtAG = connection.prepareStatement("""
									SELECT *
									FROM artgallery.arte_generativa ag
									WHERE ag.titulo = ? AND ag.autor = ?
									""")
							){
						 
							stmtAG.setString(1, titulo);
							stmtAG.setString(2, autor);
						 
							try(ResultSet resultAG = stmtAG.executeQuery()) {
								if (resultAG.next()) {	
									String algoritmo = resultAG.getString("algoritmo");
								 	long seed = resultAG.getLong("seed");
							 
								 	obra = new ArteGenerativa(titulo, autor, algoritmo, seed);
								}
							}
					 	}
				 	} else if (tipo.equals("Modelagem 3D")) {
				 		try (
							PreparedStatement stmtMD = connection.prepareStatement("""
									SELECT *
									FROM artgallery.modelagem_3d md
									WHERE md.titulo = ? AND md.autor = ?
									""")
							){
							 
							stmtMD.setString(1, titulo);
							stmtMD.setString(2, autor);
							 
							try(ResultSet resultMD = stmtMD.executeQuery()) {
								if (resultMD.next()) {	
									int numPoligonos = resultMD.getInt("num_poligonos");
								 	String engine = resultMD.getString("engine");
							 
								 	try {
								 		obra = new Modelagem3D(titulo, autor, numPoligonos, engine);
								 	} catch (QuantidadeInvalidaException e) {
								 		e.printStackTrace();
								 	}
								}
							}
						}
				 	} else {
				 		try (
							PreparedStatement stmtPD = connection.prepareStatement("""
									SELECT *
									FROM artgallery.pintura_digital ag
									WHERE ag.titulo = ? AND ag.autor = ?;
									""")
							){
							 
							stmtPD.setString(1, titulo);
							stmtPD.setString(2, autor);
							 
							try(ResultSet resultPD = stmtPD.executeQuery()) {
								if (resultPD.next()) {	
									String resolucao = resultPD.getString("resolucao");
								 	String software = resultPD.getString("software");
								 
								 	obra = new PinturaDigital(titulo, autor, resolucao, software);
								}
							}
						}
				 	}
					
					if (obra != null ) {
						if (!ativa) {
							obra.setAtiva(false);
						}
						this.minhaGaleria.publicarObra(obra);
					}
				}
			}	
			
			try (ResultSet resultAV = stmtAV.executeQuery();) {
				while (resultAV.next()) {	
					String titulo_obra = resultAV.getString("titulo_obra");
					
					String autor_obra = resultAV.getString("autor_obra");
					
					String usuario = resultAV.getString("usuario");
					
					int nota = resultAV.getInt("nota");
					
					String comentario = resultAV.getString("comentario");
					
					try {
						Avaliacao avaliacao = new Avaliacao(usuario, nota, comentario);
						minhaGaleria.avaliarObra(titulo_obra, autor_obra, avaliacao);
					} catch (NotaInvalidaException e) {
						e.printStackTrace();
					} 
				}
			}
			
			try (ResultSet resultOE = stmtOE.executeQuery()){
				while (resultOE.next()) {
					String titulo_obra = resultOE.getString("titulo_obra");
			
					String autor_obra = resultOE.getString("autor_obra");
					
					String nome_expo = resultOE.getString("nome_expo");
					
					IRepositorioObra repo = minhaGaleria.getRepoOfc();
					
					Exposicao nova_expo = new Exposicao(nome_expo);
					
					try {
						minhaGaleria.adicionarExposicao(nova_expo);
						
					} catch (ExposicaoExistenteException e) {
					} 
					
					try {
						Obra obra = repo.buscar(titulo_obra, autor_obra);
						
						if (obra != null) {
							minhaGaleria.adicionarObraNaExposicao(nome_expo, obra);
						}
					} catch (ExposicaoNaoEncontradaException e) {
						e.printStackTrace();
					} catch (ObraJaCadastradaException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public void endConnection () {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
