package modelo;

import java.io.Serializable;

public class Valoracion implements Serializable{
	
	private static final long serialVersionUID = 6494351297219254176L;
	private int id;
	private String comentario;
	// La puntuacion va de 1 a 10
	private int puntuacion;
	private Usuario emisor;
	private Usuario receptor;
	private Reserva reserva;

	public Valoracion() {
		super();
	}

	public Valoracion(String comentario, int puntuacion, Usuario emisor, Usuario receptor, Reserva reserva) {
		super();
		this.comentario = comentario;
		this.puntuacion = puntuacion;
		this.emisor = emisor;
		this.receptor = receptor;
		this.reserva = reserva;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public Usuario getEmisor() {
		return emisor;
	}

	public void setEmisor(Usuario emisor) {
		this.emisor = emisor;
	}

	public Usuario getReceptor() {
		return receptor;
	}

	public void setReceptor(Usuario receptor) {
		this.receptor = receptor;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
	
	

}
