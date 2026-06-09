package models;
import exceptions.NotaInvalidaException;

public class Avaliacao {
	private String usuario;
	private int nota;
	private String comentario;
	
	public Avaliacao (String usuario, int nota, String comentario) throws NotaInvalidaException, IllegalArgumentException {
		if (nota < 0 || nota > 10) {
			throw new NotaInvalidaException();
		}
		
		if (usuario.isBlank()) {
			throw new IllegalArgumentException("Usuário inválido!");
		}
		
		if (comentario.isBlank()) {
			throw new IllegalArgumentException("Comentário inválido!");
		}
		
		this.nota = nota;
		this.usuario = usuario;
		this.comentario = comentario;
	}
	
	public String getComentario() {
		return comentario;
	}
	
	public String getUsuario() {
		return usuario;
	}	
	
	public int getNota() {
		return nota;
	}	
}
