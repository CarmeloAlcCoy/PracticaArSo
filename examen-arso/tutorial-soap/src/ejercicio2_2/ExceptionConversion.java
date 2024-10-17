package ejercicio2_2;

import javax.xml.ws.WebFault;

@WebFault
public class ExceptionConversion extends Exception {

	private static final long serialVersionUID = 1L;
	
	protected String errInfo;

	public ExceptionConversion(String errInfo) {
		this.errInfo = errInfo;
	}

	public String getFaultInfo() {
		return errInfo;
	}

}
