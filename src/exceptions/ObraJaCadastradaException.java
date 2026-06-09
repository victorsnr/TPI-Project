package exceptions;

public class ObraJaCadastradaException extends Exception {
	private static final long serialVersionUID = 5634136895906288450L;
	private String message;
	
	public ObraJaCadastradaException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
