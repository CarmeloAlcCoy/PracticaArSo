package beans;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class BeanMensajes {
	
	private Map<String, String> mensajesRecibidos = new LinkedHashMap<String, String>();
	private String[] mensajesRecibidosSeleccionados = null;
	
	public Map<String, String> getMensajesRecibidos() {
		return mensajesRecibidos;
	}

	public void setMensajesRecibidos(Map<String, String> mensajesRecibidos) {
		this.mensajesRecibidos = mensajesRecibidos;
	}

	public String[] getMensajesRecibidosSeleccionados() {
		return mensajesRecibidosSeleccionados;
	}

	public void setMensajesRecibidosSeleccionados(String[] mensajesRecibidosSeleccionados) {
		this.mensajesRecibidosSeleccionados = mensajesRecibidosSeleccionados;
	}
	
	
}
