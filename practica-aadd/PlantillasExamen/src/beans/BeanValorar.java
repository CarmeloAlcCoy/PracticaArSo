
package beans;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class BeanValorar implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public void enviarValoracion() {
		FacesContext.getCurrentInstance().addMessage("ShippingForm:texto", new FacesMessage("mensaje"));

	}

	

}
