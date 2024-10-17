package examen2018.soap;

import javax.xml.ws.WebFault;

@WebFault
public class ExceptionMensaje extends Exception {

	private static final long serialVersionUID = 1L;

	protected String errInfo;

	public ExceptionMensaje(String errInfo) {
		this.errInfo = errInfo;
	}

	public String getFaultInfo() {
		return errInfo;
	}

}
