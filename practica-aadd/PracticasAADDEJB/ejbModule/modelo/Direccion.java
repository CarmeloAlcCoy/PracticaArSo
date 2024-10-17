package modelo;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Direccion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String calle;
	private int CP;
	
	public Direccion() {
		super();
	}

	public Direccion(String calle, int cP) {
		super();
		this.calle = calle;
		CP = cP;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public int getCP() {
		return CP;
	}

	public void setCP(int cP) {
		CP = cP;
	}

}
