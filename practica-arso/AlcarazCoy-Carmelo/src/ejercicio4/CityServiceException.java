package ejercicio4;

public class CityServiceException extends Exception {

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
