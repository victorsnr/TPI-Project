package exceptions;

public class ExposicaoNaoEncontradaException extends Exception {

	private static final long serialVersionUID = 5357944070556608912L;
	private String message = "";
		
	public ExposicaoNaoEncontradaException() {
		this.message = "Exposição não encontrada";
	}
	
	public String getMessage() {
		return this.message;
	}
}
