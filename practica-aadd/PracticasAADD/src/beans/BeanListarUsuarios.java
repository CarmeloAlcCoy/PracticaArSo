package beans;

import java.util.Collection;
import java.util.LinkedList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import controladorEJB3.ControladorBlaBlaCar;
import modelo.Usuario;

@ManagedBean(name = "beanListarUsuarios")
@SessionScoped
public class BeanListarUsuarios {
	private Collection<Usuario> usuarios;

	public Collection<Usuario> getUsuarios() {
		usuarios = new LinkedList<Usuario>();
		Collection<Usuario> todosUsuarios = ControladorBlaBlaCar.getInstancia().recuperarUsuarios();
		for (Usuario usuario : todosUsuarios) {
			usuarios.add(usuario);
		}
		return usuarios;
	}

	public BeanListarUsuarios() {
		usuarios = new LinkedList<Usuario>();
	}

	public void setUsuarios(Collection<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
}