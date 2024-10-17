package beans;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;
import controladorEJB3.ControladorBlaBlaCar;
import jms.SuscriptorApartado;
import modelo.Viaje;

@ManagedBean
@RequestScoped
public class BeanCrearViaje {
	
	@Pattern(regexp="\\d+", message = "{plazas.pattern}")
	private String plazas;
	@Range(min = 1, max=1000)
	private String precio;
	private List<String> notas;
	@Size(min = 1, max=30)
	private String ciudadOrigen;
	@Size(min = 1, max=30)
	private String ciudadDestino;
	@Size(min = 1, max=50)
	private String calleOrigen;
	@Size(min = 1, max=50)
	private String calleDestino;
	@Pattern(regexp="\\d{5}", message="cp.pattern")
	private String cpOrigen;
	@Pattern(regexp="\\d{5}", message="cp.pattern")
	private String cpDestino;
	@NotNull
	private Date fechaOrigen;
	@NotNull
	private Date fechaDestino;

	public String getPlazas() {
		return plazas;
	}

	public void setPlazas(String plazas) {
		this.plazas = plazas;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public List<String> getNotas() {
		return notas;
	}

	public void setNotas(List<String> notas) {
		this.notas = notas;
	}

	public String getCiudadOrigen() {
		return ciudadOrigen;
	}

	public void setCiudadOrigen(String ciudadOrigen) {
		this.ciudadOrigen = ciudadOrigen;
	}

	public String getCiudadDestino() {
		return ciudadDestino;
	}

	public void setCiudadDestino(String ciudadDestino) {
		this.ciudadDestino = ciudadDestino;
	}

	public String getCalleOrigen() {
		return calleOrigen;
	}

	public void setCalleOrigen(String calleOrigen) {
		this.calleOrigen = calleOrigen;
	}

	public String getCalleDestino() {
		return calleDestino;
	}

	public void setCalleDestino(String calleDestino) {
		this.calleDestino = calleDestino;
	}

	public String getCpOrigen() {
		return cpOrigen;
	}

	public void setCpOrigen(String cpOrigen) {
		this.cpOrigen = cpOrigen;
	}

	public String getCpDestino() {
		return cpDestino;
	}

	public void setCpDestino(String cpDestino) {
		this.cpDestino = cpDestino;
	}

	public Date getFechaOrigen() {
		return fechaOrigen;
	}

	public void setFechaOrigen(Date fechaOrigen) {
		this.fechaOrigen = fechaOrigen;
	}

	public Date getFechaDestino() {
		return fechaDestino;
	}

	public void setFechaDestino(Date fechaDestino) {
		this.fechaDestino = fechaDestino;
	}
	
	public String registrarViaje(){
		
		ControladorBlaBlaCar controller = ControladorBlaBlaCar.getInstancia();
		Viaje v = controller.registrarViaje( Integer.valueOf(plazas), Integer.valueOf(precio));
		controller.registrarParadaOrigen(v.getId(), ciudadOrigen, calleOrigen, Integer.valueOf(cpOrigen), fechaOrigen);
		controller.registrarParadaDestino(v.getId(), ciudadDestino, calleDestino, Integer.valueOf(cpDestino), fechaDestino);
		try {
			SuscriptorApartado.registrarApartado(v.getId());
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		return "faceletsWelcome";
		
	}

}
