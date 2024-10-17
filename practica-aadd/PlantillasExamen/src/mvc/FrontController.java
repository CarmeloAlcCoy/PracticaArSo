package mvc;

import javax.servlet.*;
import javax.servlet.http.*;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String HOME = "index.html";
	private static final String LOGIN = "login.html";

	//Redefinición método init
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		//Obtiene una factoria DAO y la almacena en la aplicación
		try {
			//DAOFactoria daoFactoria = DAOFactoria.getDAOFactoria(DAOFactoria.JDBC);
			ServletContext aplicacion = config.getServletContext();
			//aplicacion.setAttribute("DAOFactoria", daoFactoria);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doGet(HttpServletRequest peticion, HttpServletResponse respuesta)
			throws ServletException, java.io.IOException {
		procesa(peticion, respuesta);
	}

	public void doPost(HttpServletRequest peticion, HttpServletResponse respuesta)
			throws ServletException, java.io.IOException {
		procesa(peticion, respuesta);
	}

	//Método de procesamiento
	protected void procesa(HttpServletRequest peticion, HttpServletResponse respuesta)
			throws ServletException, java.io.IOException {

		//Obtiene la ruta física de la aplicación para que el 
		//objeto PeticionHelper pueda acceder a sus ficheros de propiedades
		String dirAplicacion = getServletConfig().getServletContext().getRealPath("/");
		//Utiliza una clase Helper para analizar la acción a realizar
		PeticionHelper peticionHelper = new PeticionHelper(peticion, dirAplicacion);

		//Acciones comunes de la aplicación
		//1. Si rompe el flujo de navegación, se envía a la página de inicio
		if (peticionHelper.errorNavegacion()) {
			visualizar(HOME, peticion, respuesta);
			return;
		}
		//2. Si accede a un recurso para el que necesita estar validado,se envía a la página de login
		if (peticionHelper.necesitaValidacion()) {
			//Convendria guardar la URI solicita en la sesión para que el servlet
			//Login navegara hacia ella después de la validación ...
			visualizar(LOGIN, peticion, respuesta);
			return;
		}
		
		//3. Cualquier otra acción común peticionHelper.log() ...
		
		//Obtiene la acción a ejecutar asociada a la petición
		Accion acc = peticionHelper.getAccion();
		//Recupera el objeto aplicación para la acción
		ServletContext aplicacion = getServletConfig().getServletContext();
		//Ejecuta la acción y obtiene la vista
		String vista = acc.ejecutar(peticion, respuesta, aplicacion);
		//Visualiza el resultado
		visualizar(vista, peticion, respuesta);
	}

	protected void visualizar(String vista, HttpServletRequest peticion, HttpServletResponse respuesta)
			throws ServletException, java.io.IOException {
		
		// Transforma la URL en relativa al contexto
		// Utilizamos URLs relativas al contexto para ilustrar su uso
		String vistaContexto = vista;
		
		// Reenvía la petición a la vista
		RequestDispatcher rd = peticion.getRequestDispatcher(vistaContexto);
		rd.forward(peticion, respuesta);
	}
}