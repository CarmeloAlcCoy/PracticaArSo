package modelo;

import java.util.List;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Viaje
 *
 */

@Entity
@Table(name = "ViajeCocheJPA")
public class Viaje implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "asientos")
	private int plazas;
	private double precio;
	@CollectionTable(name = "NotasViaje")
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> notas;
	@OneToOne(cascade = CascadeType.REMOVE)
	private Parada origen;
	@OneToOne(cascade = CascadeType.REMOVE)
	private Parada destino;
	@OneToMany(mappedBy = "viaje",fetch = FetchType.EAGER)
	private List<Reserva> reservas;
	
	//Este atributo ha sido necesario añadirlo 
	//porque en la parte de las valoraciones necesitamos 
	//saber quien es el conductor de un viaje
	private Usuario conductor;

	private static final long serialVersionUID = 1L;

	public Viaje() {
		super();
		notas = new ArrayList<String>();
		reservas = new ArrayList<Reserva>();
	}

	public Viaje(Usuario conductor, int plazas, double precio, List<String> notas) {
		this();
		this.plazas = plazas;
		this.precio = precio;
		this.notas = notas;
		this.conductor= conductor;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPlazas() {
		return this.plazas;
	}

	public void setPlazas(int plazas) {
		this.plazas = plazas;
	}

	public double getPrecio() {
		return this.precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public List<String> getNotas() {
		return this.notas;
	}

	public void setNotas(List<String> notas) {
		this.notas = notas;
	}

	public Parada getOrigen() {
		return origen;
	}

	public void setOrigen(Parada origen) {
		this.origen = origen;
	}

	public Parada getDestino() {
		return destino;
	}

	public void setDestino(Parada destino) {
		this.destino = destino;
	}

	public Usuario getConductor() {
		return conductor;
	}

	public void setConductor(Usuario conductor) {
		this.conductor = conductor;
	}

	public void anadirReserva(Reserva reserva) {
		reservas.add(reserva);

	}

	public int getReservasRestantes() {
		
		int n = plazas;
		
		for(int i = 0; i < reservas.size(); i++)
			if(reservas.get(i).getEstado() == EstadoReserva.ACEPTADA)
				n--;
		return n;

	}

	public Reserva reservar(Usuario usuario, String comentario) {
		
		for (Reserva r : getReservas()) {
			
			if(r.getUsuario().getUsuario() == usuario.getUsuario())
				return null;
		}
		
		if (getReservasRestantes() > 0) {
			Reserva r = new Reserva(comentario, usuario, this);
			anadirReserva(r);
			usuario.anadirReserva(r);
			return r;
		}
		return null;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	// DISCUSS Este equals se podría hacer mejor
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Viaje other = (Viaje) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public boolean enFecha(Date fechaIni, Date fechaFin) {

		return origen != null && fechaIni.compareTo(origen.getFecha()) <= 0
				&& fechaFin.compareTo(origen.getFecha()) >= 0;
	}

}
