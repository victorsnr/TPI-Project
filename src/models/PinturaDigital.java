package models;

public class PinturaDigital extends Obra {
	
	private String resolucao;
	private String softwareUtilizado;

	public PinturaDigital(String titulo, String autor, String resolucao, String softwareUtilizado) {
		super(titulo, autor);
		
		if (resolucao.isBlank()) {
			throw new IllegalArgumentException("Resolução inválida!");
		}
		
		if (softwareUtilizado.isBlank()) {
			throw new IllegalArgumentException("Software utilizado inválido!");
		}
		
		this.resolucao = resolucao.trim().toLowerCase();
		this.softwareUtilizado = softwareUtilizado.trim().toLowerCase();
	}
	
	public String getResolucao () {
		return this.resolucao;
	}
	
	public String getSoftwareUtilizado () {
		return this.softwareUtilizado;
	}
	
	@Override
	public String exibirDetalhes() {
		String retorno = String.format("Título: %s\nAutor: %s\nTipo: Pintura Digital\nResolução: %s\nSoftware: %s\\nNota Geral:%.2f", 
				this.titulo, this.autor, this.resolucao, this.softwareUtilizado, this.mediaAvaliacoes());
		return retorno;
	}
	
	public String getType() {
		return "Pintura Digital";
	};
}
