package mvc;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controladorEJB3.ControladorBlaBlaCar;
import modelo.Usuario;

public class AccionInicio implements Accion {

	@Override
	public String ejecutar(HttpServletRequest peticion, HttpServletResponse respuesta, ServletContext aplicacion) {
			
		
		Usuario usuario = ControladorBlaBlaCar.getInstancia().getUsuarioActual();
		if(usuario != null) {
			return FrontController.INICIOLOG;
		}
		return FrontController.INICIO;
	}

}
