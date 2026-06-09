package exceptions;

public class NotaInvalidaException extends Exception {
	private static final long serialVersionUID = 3126045581599357227L;
	private String message;
	
	public NotaInvalidaException() {
		message = "Nota inválida! A avaliação deve ser entre 0 e 10";
	}
	
	public String getMessage() {
		return message;
	}
}
