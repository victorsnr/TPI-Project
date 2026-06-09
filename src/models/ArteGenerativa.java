package models;

public class ArteGenerativa extends Obra{
	
	private String algoritmo;
	private long seed;
	
	public ArteGenerativa(String titulo, String autor, String algoritmo, long seed) throws IllegalArgumentException {
		super(titulo, autor);
		
		if (algoritmo.isBlank()) {
			throw new IllegalArgumentException("Algoritmo inválido");
		}
		
		this.algoritmo = algoritmo.trim();
		this.seed = seed;
	}
	
	public String getAlgoritmo() {
		return this.algoritmo;
	}

	public long getSeed() {
		return this.seed;
	}

	@Override
	public String exibirDetalhes() {
		String retorno = String.format("Título: %s\nAutor: %s\nTipo: Modelo 3D \nAlgoritmo: %s\nSeed: %d\nNota Geral:%.2f", 
				this.titulo, this.autor, this.algoritmo, this.seed, this.mediaAvaliacoes());
		return retorno;
	}
	
	public String getType() {
		return "Arte Generativa";
	};
	
}
