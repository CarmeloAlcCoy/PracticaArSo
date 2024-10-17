package modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@NamedQuery(name = "Usuario.findAll", query= "SELECT u FROM Usuario u")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private String usuario;
	private String clave;
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;
	private String profesion;
	private String email;
	private String nombre;
	private String apellidos;
	@OneToOne
	private Coche coche;
	@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER) 
	private List<Reserva> reservas;
	// private List<Valoracion> valoracionesRecibidas;
	// private List<Valoracion> valoracionesEmitidas;

	public Usuario() {
		super();
		reservas = new LinkedList<Reserva>();
		// valoracionesEmitidas = new LinkedList<Valoracion>();
		// valoracionesRecibidas = new LinkedList<Valoracion>();
	}

	public Usuario(String usuario, String clave, Date fechaNacimiento, String profesion, String email, String nombre,
			String apellidos) {
		this();
		this.usuario = usuario;
		this.clave = clave;
		this.fechaNacimiento = fechaNacimiento;
		this.profesion = profesion;
		this.email = email;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getProfesion() {
		return profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Coche getCoche() {
		return coche;
	}

	public void setCoche(Coche coche) {
		this.coche = coche;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public void anadirReserva(Reserva reserva) {
		reservas.add(reserva);

	}

	/*
	 * public List<Valoracion> getValoracionesRecibidas() { return
	 * valoracionesRecibidas; }
	 * 
	 * public void setValoracionesRecibidas(List<Valoracion> valoracionesRecibidas)
	 * { this.valoracionesRecibidas = valoracionesRecibidas; }
	 * 
	 * public List<Valoracion> getValoracionesEmitidas() { return
	 * valoracionesEmitidas; }
	 * 
	 * public void setValoracionesEmitidas(List<Valoracion> valoracionesEmitidas) {
	 * this.valoracionesEmitidas = valoracionesEmitidas; }
	 */

	public Reserva aceptarReservaViaje(Viaje viaje) {
		for (Reserva reserva : reservas) {
			if (viaje.equals(reserva.getViaje())) {
				reserva.aceptar();
				return reserva;
			}

		}
		return null;

	}

	public Reserva rechazarReservaViaje(Viaje viaje) {
		for (Reserva reserva : reservas) {
			if (viaje.equals(reserva.getViaje())) {
				reserva.rechazar();
				return reserva;
			}

		}
		return null;

	}

	@Override
	public String toString() {
		return "Usuario [usuario=" + usuario + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellidos == null) ? 0 : apellidos.hashCode());
		result = prime * result + ((clave == null) ? 0 : clave.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fechaNacimiento == null) ? 0 : fechaNacimiento.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((profesion == null) ? 0 : profesion.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (apellidos == null) {
			if (other.apellidos != null)
				return false;
		} else if (!apellidos.equals(other.apellidos))
			return false;
		if (clave == null) {
			if (other.clave != null)
				return false;
		} else if (!clave.equals(other.clave))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fechaNacimiento == null) {
			if (other.fechaNacimiento != null)
				return false;
		} else if (!fechaNacimiento.equals(other.fechaNacimiento))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (profesion == null) {
			if (other.profesion != null)
				return false;
		} else if (!profesion.equals(other.profesion))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;

		return true;
	}

	public List<Viaje> filtrarViajes(Date fechaIni, Date fechaFin, String orden) {
		if(coche == null ) return null;
		return coche.filtrarViajes(fechaIni, fechaFin, orden);
	}

	/**Un usuario puede puede reservar un viaje cuando quedan plazas y no es 
	 * él el conductor
	 * 
	 * @param v
	 * @return
	 */
	public boolean puedeReservar(Viaje v) {
		if(v.getReservasRestantes() <=0)
			return false;
		
		if(coche == null)
			return  true;
			
		return !coche.getViajes().contains(v);

	}


	
}
