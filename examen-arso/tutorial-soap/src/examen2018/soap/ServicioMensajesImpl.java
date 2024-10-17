package examen2018.soap;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.jws.WebService;

@WebService(endpointInterface = "examen2018.soap.ServicioMensajes")
public class ServicioMensajesImpl implements ServicioMensajes{

	private List<Mensaje> buzon;
	
	public ServicioMensajesImpl() {
		buzon= new LinkedList<Mensaje>();
	}

	@Override
	public void escribirMensajes(String remitente, String destinatario, String texto) throws ExceptionMensaje {
		if(remitente == null || remitente.isEmpty()) {
			throw new ExceptionMensaje("Remitente must be a non empty parameter");
		}
		
		if(destinatario == null || destinatario.isEmpty()) {
			throw new ExceptionMensaje("destinatario must be a non empty parameter");
		}
		
		if(texto == null || texto.isEmpty()) {
			throw new ExceptionMensaje("texto must be a non empty parameter");
		}
		
		
	}

	@Override
	public List<Mensaje> leerMensajes(String destinatario)throws ExceptionMensaje {
		if(destinatario == null || destinatario.isEmpty()) {
			throw new ExceptionMensaje("destintario must be a non empty parameter");
		}
		List<Mensaje> recibidos = buzon.stream()
				.filter((m)-> m.getDestinatario().equals(destinatario))
				.collect(Collectors.toList());
		buzon.removeAll(recibidos);
		return recibidos;
	}
	

}
