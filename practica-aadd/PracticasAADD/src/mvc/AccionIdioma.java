package mvc;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccionIdioma implements Accion {

	@Override
	public String ejecutar(HttpServletRequest peticion, HttpServletResponse respuesta, ServletContext aplicacion) {
		
		if (peticion.getParameter("language") != null) {

			String[] planguage = peticion.getParameter("language").split("_");
			String language = planguage[0];
			//ControladorBlaBlaCar.getInstancia().setLanguage(language);
			
			peticion.getSession().setAttribute("lang", language);
			//Volvemos a la página desde la que ha sido llamada
			return peticion.getRequestURL().toString().substring(0, peticion.getRequestURL().toString().length()-12);
		}
		return FrontController.ERROR;
	}

}
