package exceptions;

public class TiposIncompativeisException extends Exception {

	private static final long serialVersionUID = 3180022781672742547L;
	private String message = "";
	
	public TiposIncompativeisException() {
		this.message = "Obras com tipos diferentes!";
	}
	
	public String getMessage() {
		return this.message;
	}
}
