package beans;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import controladorEJB3.ControladorBlaBlaCar;
import modelo.Usuario;

@ManagedBean
@SessionScoped
public class Validator {
	
	public void validate(){
		Usuario user = ControladorBlaBlaCar.getInstancia().getUsuarioActual();
		if(user == null)
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("faces/login.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
public void validateCoche(){
		
		Usuario user = ControladorBlaBlaCar.getInstancia().getUsuarioActual();
		if(user == null)
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("faces/login.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		else if(user.getCoche()!=null) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("miPerfil.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
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
