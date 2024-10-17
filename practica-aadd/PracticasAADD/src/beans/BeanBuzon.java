package beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.jms.JMSException;
import javax.naming.NamingException;

import jms.EmisorCola;

@ManagedBean
@RequestScoped
public class BeanBuzon {

	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void enviarSugerencia() {
		System.out.println("BeanBuzon.enviarSugerencia()" + text);

		String resultado = "";
		if (text == null || text.equals("")) {
			resultado = "No se puede enviar un mensaje vacio.";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(resultado));
		} else {
			try {
				EmisorCola.enviar(text);
				resultado = "Envio realizado correctamente.";
			} catch (NamingException | JMSException e) {
				resultado = "Error durante el envio.";
				e.printStackTrace();
			}
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(resultado));
		text = "";

	}
}
