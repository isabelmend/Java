package dto;

public class ErrorDTO {
	
	boolean error;
	String mensaje;
	public boolean isError() {
	return error;
	}
	public void setError(boolean error) {
		
		this.error = error;
	}
	public String getMensaje() {
	return mensaje;
	}
	public void setMensaje(String mensaje) {
	this.mensaje = mensaje;
	}
	@Override
	public String toString() {
	return "ErrorDto [error=" + error + ", mensaje=" + mensaje + "]";

	}

}
