package exceptions;

public class ExposicaoExistenteException extends Exception {
	
	private static final long serialVersionUID = -6587679387890359970L;
	private String message = "";
	
	public ExposicaoExistenteException() {
		this.message = "Exposição já existe!";
	}
	
	public String getMessage() {
		return this.message;
	}
}
