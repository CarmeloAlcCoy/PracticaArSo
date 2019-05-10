package servicio.controlador;

public class CityServiceException extends RuntimeException {

	private static final long serialVersionUID = -466464981249449978L;
	
	private String source;
	
	public CityServiceException(String source, String cause) {
		super(source+ "\nCause:"+cause);
		this.source=source;
	}

	public String getSource() {
		return source;
	}
	
}
