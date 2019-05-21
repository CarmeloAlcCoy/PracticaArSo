package servicio.rest;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement()
@XmlType(name = "", propOrder = {
	    "code",
	    "cause",
	    "message"
	})
public class Error {
	
	private int code;
	private String cause;
	private String message;
	
	public Error() {
		super();
	}

	public Error(int code, String cause, String message) {
		super();
		this.code = code;
		this.cause = cause;
		this.message = message;
	}



	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
