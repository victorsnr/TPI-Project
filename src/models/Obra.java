package models;
import java.util.Vector;

public abstract class Obra {
	protected String titulo;
	protected String autor;
	protected boolean ativa;
	protected Vector<Avaliacao> avaliacoes;
	
	public Obra(String titulo, String autor) throws IllegalArgumentException {
		if (titulo.isBlank()) {
			throw new IllegalArgumentException("Título inválido!");
		}
		
		if (autor.isBlank()) {
			throw new IllegalArgumentException("Autor inválido!");
		}
		
		this.titulo = titulo.trim().toUpperCase();
		this.autor = autor.trim().toUpperCase();
		this.ativa = true;
		this.avaliacoes = new Vector<>();
	}
	
	public String getTitulo() {
		return this.titulo;
	}
	
	public String getAutor() {
		return this.autor;
	}
	
	public Vector<Avaliacao> getAvaliacoes(){
		return avaliacoes;
	}
	
	public boolean isAtiva() {
		return this.ativa;
	}
	
	public void setAtiva (boolean ativa) {
		this.ativa = ativa;
	}
	
	public void adicionarAvaliacao (Avaliacao avaliacao){
		this.avaliacoes.add(avaliacao);
	}
	
	public double mediaAvaliacoes() {
		if (this.avaliacoes.isEmpty()) {
			return 0;
		}
		
		double soma = 0;
		for (Avaliacao a : this.avaliacoes) {
			soma += a.getNota();
		}
		
		return soma/this.avaliacoes.size();
	}
	
	@Override
	public String toString() {
		return titulo + " - " + autor;
	}
	
	public abstract String exibirDetalhes();
	
	public abstract String getType();
	
	
}
