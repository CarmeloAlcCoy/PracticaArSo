package beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import controladorEJB3.ControladorBlaBlaCar;
import modelo.Usuario;

@ManagedBean
@RequestScoped
public class BeanMiPerfil {
	
	private Usuario user;
	
	public BeanMiPerfil(){
		
		user = ControladorBlaBlaCar.getInstancia().getUsuarioActual();
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}
	
	public String registrarCoche() {
		return "registrarCoche";
	}
	

}
