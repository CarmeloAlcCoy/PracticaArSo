package jms;

import java.util.Map;
import javax.faces.context.FacesContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import beans.BeanMensajes;
import beans.BeanValorar;

public class OyenteSuscripcion implements MessageListener {
	private BeanMensajes beanMensajes;

	public OyenteSuscripcion() {
		Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		beanMensajes = (BeanMensajes) session.get("beanMensajes");
		
		if(beanMensajes == null){
			
			beanMensajes = new BeanMensajes();
			session.put("beanMensajes", beanMensajes);			
			
		}
	}

	@Override
	public void onMessage(Message mensaje) {
		if (mensaje instanceof TextMessage) {
			TextMessage mensajeTexto = (TextMessage) mensaje;
			System.out.println("OyenteSuscripcion.onMessage()");
			try {
				beanMensajes.getMensajesRecibidos().put(mensajeTexto.getText(), mensajeTexto.getText());
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}
}