package beans;


import java.io.Serializable;
import java.util.Locale;

import javax.faces.context.FacesContext;

public class BeanLocale implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String locale;

	public BeanLocale() {
		
		//FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(this.locale));
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
		FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(this.locale));
	}

}