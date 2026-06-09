package models;
import java.util.Vector;

public class Exposicao {
	private String nome;
	private Vector<Obra> obras;
	
	public Exposicao(String nome) throws IllegalArgumentException {
		if (nome.isBlank() ) {
			throw new IllegalArgumentException("Nome inválido");
		}
		
		this.nome = nome.trim().toUpperCase();
		this.obras = new Vector<>();
	}
	
	public void adicionarObra(Obra obra) {
		this.obras.add(obra);
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public Vector<Obra> listarObra() {
		Vector<Obra> obrasAtivas = new Vector<>();
		
		for (Obra obra : this.obras) {
			if (obra.isAtiva()) {
				obrasAtivas.add(obra);
			}
		}
		
		return obrasAtivas;
	}
}
