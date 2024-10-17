package modelo;

import java.io.Serializable;

import java.lang.String;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: Reserva
 *
 */
@Entity
public class Reserva implements Serializable {

	@Id
	@GeneratedValue
	private int id;
	private String comentario;
	@Enumerated(EnumType.STRING)
	private EstadoReserva estado;
	@ManyToOne
	private Usuario usuario;
	@ManyToOne
	private Viaje viaje;

	private static final long serialVersionUID = 1L;

	public Reserva() {
		super();
		estado = EstadoReserva.PENDIENTE;
	}

	public Reserva(String comentario, Usuario usuario, Viaje viaje) {
		this();
		this.comentario = comentario;
		this.usuario = usuario;
		this.viaje = viaje;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public EstadoReserva getEstado() {
		return estado;
	}

	public void setEstado(EstadoReserva estado) {
		this.estado = estado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Viaje getViaje() {
		return viaje;
	}

	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}

	public void aceptar() {
		estado = EstadoReserva.ACEPTADA;
		
	}
	
	public void rechazar() {
		estado = EstadoReserva.RECHAZADA;
		
	}
	
	public boolean isValorable() {
		Date fechaActual = new Date(System.currentTimeMillis());
		if(viaje==null || viaje.getDestino()==null) return false;
		return estado==EstadoReserva.ACEPTADA && fechaActual.after(viaje.getDestino().getFecha()); 
	}
	
	

}
