package models;
import exceptions.QuantidadeInvalidaException;

public class Modelagem3D extends Obra {

	private int numeroPoligonos;
	private String engine; 
	
	public Modelagem3D(String titulo, String autor, int numeroPoligonos, String engine) 
			throws QuantidadeInvalidaException, IllegalArgumentException{
		super(titulo, autor);
		
		if (numeroPoligonos <= 0) {
			throw new QuantidadeInvalidaException("A quantidade de polígonos deve ser maior que zero");
		}
		
		if (engine.isBlank()) {
			throw new IllegalArgumentException("Erro!Campos em branco");
		}
		
		this.numeroPoligonos = numeroPoligonos;
		this.engine = engine.trim().toLowerCase();
	}

	public int getNumeroPoligonos() {
		return numeroPoligonos;
	}

	public String getEngine() {
		return engine;
	}

	@Override
	public String exibirDetalhes() {
		String retorno = String.format("Título: %s\nAutor: %s\nTipo: Modelo 3D \nNº de polígonos: %d\nEngine: %s\nNota Geral:%.2f", 
					this.titulo, this.autor, this.numeroPoligonos, this.engine, this.mediaAvaliacoes());
		return retorno;
	}
	
	public String getType() {
		return "Modelagem 3D";
	};

}
