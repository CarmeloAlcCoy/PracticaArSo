
package beans;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.jms.JMSException;
import javax.naming.NamingException;
import controladorEJB3.ControladorBlaBlaCar;
import jms.PublicadorApartado;
import jms.SuscriptorApartado;
import modelo.EstadoReserva;
import modelo.Reserva;
import modelo.Usuario;
import modelo.Valoracion;
import modelo.Viaje;

@ManagedBean
@SessionScoped
public class BeanValorar implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Reserva reserva;
	private int rate;
	private String nombreUser;

	private String comentario;
	
	

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getNombreUser() {
		return nombreUser;
	}

	public void setNombreUser(String nombreUser) {
		this.nombreUser = nombreUser;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public Map<String, String> getUsuariosValorables() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		if (reserva != null) {
			Viaje v = reserva.getViaje();
			map.put(new String(v.getConductor().getUsuario() + "-Conductor"), v.getConductor().getUsuario());
			for (Reserva r : v.getReservas()) {
				if (r.getEstado() == EstadoReserva.ACEPTADA
						&& !r.getUsuario().equals(ControladorBlaBlaCar.getInstancia().getUsuarioActual())) {
					map.put(new String(r.getUsuario().getUsuario() + "-Pasajero"), r.getUsuario().getUsuario());
				}

			}
		}
		return map;
	}

	public void enviarValoracion() {
		String resultado = "";
		//Reserva r = ControladorBlaBlaCar.getInstancia().buscarReserva(idReserva);
		Usuario usuario = ControladorBlaBlaCar.getInstancia().existeUsuario(nombreUser);
		Valoracion v = new Valoracion(comentario, rate, reserva.getUsuario(), usuario, reserva);
		/*
		 * if (comentario == null || comentario.equals("")) { resultado =
		 * "No se puede enviar un mensaje vacio.";
		 * FacesContext.getCurrentInstance().addMessage("ShippingForm:texto", new
		 * FacesMessage(resultado)); }
		 */
		try {
			PublicadorApartado.enviar(v, reserva.getViaje().getId());
		} catch (NamingException e) {
			resultado = "Error durante el envio.";
			e.printStackTrace();
		} catch (JMSException e) {
			resultado = "Error durante el envio.";
			e.printStackTrace();
		}
		resultado = "Envio realizado correctamente.";
		FacesContext.getCurrentInstance().addMessage("ShippingForm:texto", new FacesMessage(resultado));
		// comentario = "";
	}

	public void recibirTodosTexto(ActionEvent event) {
		/*try {
			SuscriptorApartado.registrarApartado();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}*/
	}
	
	public void registrarApartado(int idViaje) {
		try {
			SuscriptorApartado.registrarApartado(idViaje);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
