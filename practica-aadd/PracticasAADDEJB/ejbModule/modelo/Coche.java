package modelo;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Coche implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private String matricula;
	private String modelo;
	// El confort va de 1 a 5
	private int confort;
	private int year;
	@OneToOne(mappedBy = "coche")
	private Usuario usuario;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "coche") // DISCUSS: atributo coche en tabla viaje
	private List<Viaje> viajes;

	public Coche() {
		super();
		viajes = new LinkedList<Viaje>();
	}

	public Coche(String matricula, String modelo, int confort, int year, Usuario usuario) {
		this();
		this.matricula = matricula;
		this.modelo = modelo;
		this.confort = confort;
		this.year = year;
		this.usuario = usuario;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getConfort() {
		return confort;
	}

	public void setConfort(int confort) {
		this.confort = confort;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Viaje> getViajes() {
		return viajes;
	}

	public void setViajes(List<Viaje> viajes) {
		this.viajes = viajes;
	}

	public void anadirViaje(Viaje viaje) {
		viajes.add(viaje);

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + confort;
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		result = prime * result + ((modelo == null) ? 0 : modelo.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		result = prime * result + ((viajes == null) ? 0 : viajes.hashCode());
		result = prime * result + year;
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
		Coche other = (Coche) obj;
		if (confort != other.confort)
			return false;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		if (modelo == null) {
			if (other.modelo != null)
				return false;
		} else if (!modelo.equals(other.modelo))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.getUsuario().equals(other.usuario.getUsuario()))
			return false;
		if (viajes == null) {
			if (other.viajes != null)
				return false;
		} else if (!viajes.equals(other.viajes))
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	public List<Viaje> filtrarViajes(Date fechaIni, Date fechaFin, String orden) {
		LinkedList<Viaje> resultado = new LinkedList<Viaje>();
		for (Viaje viaje : viajes) {
			if (viaje.enFecha(fechaIni, fechaFin))
				resultado.add(viaje);
		}
		if (orden.equals("fecha")) {
			Comparator<Viaje> comp = new Comparator<Viaje>() {

				@Override
				public int compare(Viaje o1, Viaje o2) {

					return o1.getOrigen().getFecha().compareTo(o2.getDestino().getFecha());
				}
			};

			Collections.sort(resultado, comp);

		} else if (orden.equals("ciudad")) {
			Comparator<Viaje> comp = new Comparator<Viaje>() {
				@Override
				public int compare(Viaje o1, Viaje o2) {
					return o1.getOrigen().getCiudad().compareTo(o2.getDestino().getCiudad());
				}
			};
			Collections.sort(resultado, comp);
		}

		return resultado;
	}

}