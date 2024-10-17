package modelo;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Parada
 *
 */
@Entity
public class Parada implements Serializable {

	@Id
	@GeneratedValue
	private int id;
	private String ciudad;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	@Embedded
	private Direccion direccion;

	private static final long serialVersionUID = 1L;

	public Parada() {
		super();
	}
	
	

	public Parada(String ciudad, Date fecha, String calle, int cp) {
		super();
		this.ciudad = ciudad;
		this.fecha = fecha;
		Direccion dir = new Direccion(calle, cp);
		this.direccion = dir;
	}



	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Direccion getDireccion() {
		return this.direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

}
