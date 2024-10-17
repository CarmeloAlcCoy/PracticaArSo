package examen2018.soap;

import java.util.List;

import javax.jws.WebService;

@WebService
public interface ServicioMensajes {

	public void escribirMensajes(String remitente, String destintario, String texto) throws ExceptionMensaje;
	public List<Mensaje> leerMensajes(String destinatario) throws ExceptionMensaje;
}
