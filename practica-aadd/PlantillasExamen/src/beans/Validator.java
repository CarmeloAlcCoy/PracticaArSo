package beans;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


@ManagedBean
@SessionScoped
public class Validator {
	
	public void validate(){
		String user = ControladorBlaBlaCar.getInstancia().getUsuarioActual();
		if(user == null)
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("faces/login.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
public void validateCoche(){
		
	}
	
	public String testValidate(){
		
		Usuario user = ControladorBlaBlaCar.getInstancia().getUsuarioActual();
		return user != null ? "true" : "false";
		
	}
	
	public void returnHome(){
		
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("inicio.ctrl");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	

}
