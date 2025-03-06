package exception;

public class CorvException extends Exception {
	
	/**
	*
	*/
	private static final long serialVersionUID = -2458441792379886336L;
	public CorvException (String mensaje) {
	super(mensaje);
	}
	public String getMensaje() {
	return super.getMessage();
	}

}
