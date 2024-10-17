package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.jms.JMSException;

import controladorEJB3.ControladorBlaBlaCar;
import jms.SuscriptorApartado;
import modelo.Reserva;
import modelo.Usuario;
import modelo.Viaje;

@SessionScoped
@ManagedBean
public class BeanContratarViaje {
	
	private String comentario;
	private Viaje viajeSeleccionado; 
	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Viaje getViajeSeleccionado() {
		return viajeSeleccionado;
	}

	public void setViajeSeleccionado(Viaje viajeSeleccionado) {
		this.viajeSeleccionado = viajeSeleccionado;
	}

	public String contratar(Viaje v){
		
		ControladorBlaBlaCar controller = ControladorBlaBlaCar.getInstancia();
		Reserva r = controller.reservarViaje(v.getId(), comentario);
		
		
		
		if(r==null)
			return "reservaRechazada";
					
		return "reservaAceptada";
		
		
	}
	
	public boolean isPropio(Viaje v){
		Usuario u = ControladorBlaBlaCar.getInstancia().getUsuarioActual();
		if(u ==null || v == null)
			return false;
		return u.puedeReservar(v);
		
	}
	
	public boolean puedeReservar(Viaje v){
		
		return ControladorBlaBlaCar.getInstancia().getUsuarioActual().puedeReservar(v);
		
	}
	

}
