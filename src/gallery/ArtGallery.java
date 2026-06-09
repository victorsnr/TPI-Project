package gallery;

import java.util.Vector;
import interfaces.IArtGallery;
import interfaces.IRepositorioObra;
import models.Avaliacao;
import models.Obra;
import models.Exposicao;
import exceptions.ObraJaCadastradaException;
import exceptions.ObraNaoEncontradaException; 
import exceptions.ExposicaoNaoEncontradaException;
import exceptions.ExposicaoExistenteException;

public class ArtGallery implements IArtGallery {

	private IRepositorioObra repositorio;
	private Vector<Exposicao> exposicoes;
	
	public ArtGallery(IRepositorioObra repositorio) {
		this.repositorio = repositorio;
		exposicoes = new Vector<>();
	}

	@Override
	public String publicarObra(Obra obra) {
		String message = ""; 
		try{
			this.repositorio.cadastrar(obra);
			message = "Obra cadastrada com sucesso";
		} catch (ObraJaCadastradaException erro) {
			message = erro.getMessage();
		}
		
		return message;
	}

	@Override
	public String removerObra(String titulo, String autor) {
		String message = ""; 
		try{
			this.repositorio.remover(titulo, autor);
			message = "Obra desativada com sucesso";
		} catch (ObraNaoEncontradaException erro) {
			message = erro.getMessage();
		}	
		return message;
	}

	@Override
	public String avaliarObra(String titulo, String autor, Avaliacao avaliacao) {
		String message = ""; 
		Obra obraEncontrada = this.repositorio.buscar(titulo, autor);
		
		if (obraEncontrada != null) {
			obraEncontrada.adicionarAvaliacao(avaliacao);
			message = "Obra avaliada com sucesso!";
		} else {
			message = "Obra não encontrada";
		}
		
		return message;
	}
	

	@Override
	public Vector<Obra> listarObras() {
		Vector<Obra> obrasTotais = this.repositorio.listar();
		Vector<Obra> obrasAtivas = new Vector<>();
		
		for (Obra obra : obrasTotais) {
			if (obra.isAtiva()) {
				obrasAtivas.add(obra);
			}
		}
		
		return obrasAtivas;
	}

	@Override
	public Vector<Obra> buscarPorAutor(String autor) {
		Vector<Obra> obrasTotais = this.repositorio.listar();
		Vector<Obra> obrasAutor = new Vector<>();
		
		autor = autor.trim().toUpperCase();
		
		for (Obra obra : obrasTotais) {
			if (obra.getAutor().equals(autor)) {
				obrasAutor.add(obra);
			}
		}
		
		return obrasAutor;
	}

	@Override
	public Vector<Obra> topObras() {
		Vector<Obra> obrasOrdenadas = listarObras();
		
		int n = obrasOrdenadas.size();
		
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				Obra obra1 = obrasOrdenadas.get(j);
				Obra obra2 = obrasOrdenadas.get(j + 1);
				
				double mediaObra1 = obra1.mediaAvaliacoes();
				double mediaObra2 = obra2.mediaAvaliacoes();

				if (mediaObra1 < mediaObra2) {		
					obrasOrdenadas.set(j, obra2);
					obrasOrdenadas.set(j + 1, obra1);
				}
			}
		}
		
		return obrasOrdenadas;
	}

	@Override
	public Vector<Obra> obrasExpostas(String nomeExposicao) throws ExposicaoNaoEncontradaException {
		Exposicao exposicaoExistente = null;
		nomeExposicao = nomeExposicao.trim().toUpperCase();
		for (Exposicao expo : this.exposicoes) {
			String nomeExpo = expo.getNome();
			if (nomeExpo.equals(nomeExposicao)) {
				exposicaoExistente = expo;
				break;
			}
		}
		
		if (exposicaoExistente != null) {
			return exposicaoExistente.listarObra();
		} else {
			throw new ExposicaoNaoEncontradaException();
		}
	}

	public void adicionarExposicao(Exposicao exposicao) throws ExposicaoExistenteException {
		String nomeExpo = exposicao.getNome();
		
		for (Exposicao expo : this.exposicoes) {
			if (expo.getNome().equals(nomeExpo)) {
				throw new ExposicaoExistenteException();
			}
		}
		
		exposicoes.add(exposicao);
	}
	
	public void adicionarObraNaExposicao(String nomeExpo, Obra obra) throws ExposicaoNaoEncontradaException, ObraJaCadastradaException{
		nomeExpo = nomeExpo.trim().toUpperCase();
		Exposicao expoEncontrada = null;
		for (Exposicao expo : this.exposicoes) {
			if (expo.getNome().equals(nomeExpo)) {
				expoEncontrada = expo;
			}
		}
		
		if (expoEncontrada == null) {
			throw new ExposicaoNaoEncontradaException();
		}
		
		Vector<Obra> obrasExpo = expoEncontrada.listarObra();
		
		for (Obra o : obrasExpo) {
			if (o.getTitulo().equals(obra.getTitulo()) && o.getAutor().equals(obra.getAutor())) {
				throw new ObraJaCadastradaException("A obra já está na exposição!");
			}
		}
		
		expoEncontrada.adicionarObra(obra);
	}
}
