package ejercicio4;

public class ParseXMLException extends Exception {

	private static final long serialVersionUID = -466464981249449978L;
	
	public ParseXMLException(String source, String cause) {
		super("Could not parse the following resource:" + source+ "\nCause:"+cause);
;	}
	
}
