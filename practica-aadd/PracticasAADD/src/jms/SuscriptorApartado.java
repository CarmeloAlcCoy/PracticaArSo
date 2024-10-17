package jms;

import java.util.LinkedList;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import controladorEJB3.ControladorBlaBlaCar;
import modelo.Reserva;

public class SuscriptorApartado {
	private static List<TopicSubscriber> topicSubscribers = new LinkedList<>();
	private static List<OyenteSuscripcion> oyentesSuscripcion = new LinkedList<OyenteSuscripcion>();

	public static void registrarApartado(int viajeId) throws NamingException, JMSException {
			
			InitialContext iniCtx = new InitialContext();
			Object tmp = iniCtx.lookup("jms/TopicConnectionFactory");
			TopicConnectionFactory qcf = (TopicConnectionFactory) tmp;
			TopicConnection conn = qcf.createTopicConnection();
			Topic topic = (Topic) iniCtx.lookup("topic/adApartado");
			TopicSession session = conn.createTopicSession(false, TopicSession.AUTO_ACKNOWLEDGE);
			conn.start();
			TopicSubscriber ts = session.createSubscriber(topic, "viaje LIKE '" + String.valueOf(viajeId) + "'", false);
			topicSubscribers.add(ts);
	}
	
	public static void registrarOyente(int viajeId) throws JMSException{
		
		OyenteSuscripcion o = new OyenteSuscripcion();
		oyentesSuscripcion.add(o);
		
		for(int i = 0; i < topicSubscribers.size(); i++) {
			
			String s = topicSubscribers.get(i).getMessageSelector();
			String[] partes = s.split("'");
			
			if(partes[1].equals(String.valueOf(viajeId)))
				topicSubscribers.get(i).setMessageListener(o);
			
		}
		
	}
}