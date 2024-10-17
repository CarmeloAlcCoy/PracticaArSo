package beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import controladorEJB3.ControladorBlaBlaCar;
import modelo.Reserva;
import modelo.Usuario;

@ManagedBean
@SessionScoped
public class BeanMisReservas {
	
	private List<Reserva> reservas;

	public BeanMisReservas() {
		Usuario u = ControladorBlaBlaCar.getInstancia().getUsuarioActual();
		if(u!=null)
			reservas= u.getReservas();
	}

	public List<Reserva> getReservas() {
		Usuario u = ControladorBlaBlaCar.getInstancia().getUsuarioActual();
		if(u!=null)
			reservas= u.getReservas();
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}
	
	

}
