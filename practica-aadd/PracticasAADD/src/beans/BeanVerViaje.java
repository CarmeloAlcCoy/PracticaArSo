package beans;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.jms.JMSException;

import controladorEJB3.ControladorBlaBlaCar;
import jms.SuscriptorApartado;
import modelo.Reserva;
import modelo.Viaje;

@SessionScoped
@ManagedBean
public class BeanVerViaje {

	private int viajeSeleccionado;

	public Viaje getViajeSeleccionado() {
		return ControladorBlaBlaCar.getInstancia().buscarViaje(viajeSeleccionado);
	}

	public void setViajeSeleccionado(int viajeSeleccionado) {
		this.viajeSeleccionado = viajeSeleccionado;
	}

	public void aceptarViaje(Reserva r) {
		
		ControladorBlaBlaCar.getInstancia().aceptarViaje(viajeSeleccionado, r.getUsuario().getUsuario());
		try {
			SuscriptorApartado.registrarOyente(r.getViaje().getId());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	
	}

	public void rechazarViaje(Reserva r) {
		
		ControladorBlaBlaCar.getInstancia().rechazarViaje(viajeSeleccionado, r.getUsuario().getUsuario());
	}

}
