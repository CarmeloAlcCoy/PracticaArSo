package beans;

import controladorEJB3.ControladorBlaBlaCar;

public class BeanLogin {

	private String usuario;
	private String clave;

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getClave() {
		return clave;
	}

	public String validacion() {
		if (ControladorBlaBlaCar.getInstancia().loginUsuario(usuario, clave)) {
			return "si";
		} else {
			setUsuario(new String());
			setClave(new String());
			return "no";
		}
	}

	public String logout() {
		ControladorBlaBlaCar.getInstancia().setUsuarioActual(null);
		return "logoutCompleto";

	}

}
