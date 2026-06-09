package exceptions;

public class ObraNaoEncontradaException extends Exception {
	private static final long serialVersionUID = -690028224798853415L;
	private String message;
	
	public ObraNaoEncontradaException() {
		message = "Erro! Obra não encontrada!";
	}

	public String getMessage() {
		return message;
	}
}
