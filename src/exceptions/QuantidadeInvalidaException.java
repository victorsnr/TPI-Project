package exceptions;

public class QuantidadeInvalidaException extends Exception {
	private static final long serialVersionUID = 1819152125861740267L;
	private String message;
	
	public QuantidadeInvalidaException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
