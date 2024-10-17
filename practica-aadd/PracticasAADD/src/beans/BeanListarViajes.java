package beans;

import java.io.Serializable;
import java.util.Collection;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import controladorEJB3.ControladorBlaBlaCar;
import modelo.Viaje;

@ManagedBean(name = "beanListarViajes")
@RequestScoped
public class BeanListarViajes implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Collection<Viaje> viajes;

	@ManagedProperty(value = "#{param.user}")
	private String user;
	private boolean pendientes;
	private boolean realizados;
	@ManagedProperty(value = "#{param.propios}")
	private boolean propios;
	private boolean ordenFecha;
	private boolean ordenCiudad;

	public BeanListarViajes() {
		viajes = ControladorBlaBlaCar.getInstancia().listarViajes(user,pendientes, realizados,propios, ordenFecha, ordenCiudad);
	
	}
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Collection<Viaje> getViajes() {
				
		viajes = ControladorBlaBlaCar.getInstancia().listarViajes(user,pendientes, realizados,propios, ordenFecha, ordenCiudad);
		return viajes;
	}

	public boolean isPropios() {
		return propios;
	}

	public void setPropios(boolean propios) {
		this.propios = propios;
	}

	public void setViajes(Collection<Viaje> viajes) {
		this.viajes = viajes;
	}

	public boolean isPendientes() {
		return pendientes;
	}

	public void setPendientes(boolean pendientes) {
		this.pendientes = pendientes;
	}

	public boolean isRealizados() {
		return realizados;
	}

	public void setRealizados(boolean realizados) {
		this.realizados = realizados;
	}

	public boolean isOrdenFecha() {
		return ordenFecha;
	}

	public void setOrdenFecha(boolean ordenFecha) {
		this.ordenFecha = ordenFecha;
	}

	public boolean isOrdenCiudad() {
		return ordenCiudad;
	}

	public void setOrdenCiudad(boolean ordenCiudad) {
		this.ordenCiudad = ordenCiudad;
	}

	}