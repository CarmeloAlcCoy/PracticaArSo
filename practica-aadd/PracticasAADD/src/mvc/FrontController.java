package mvc;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import dao.FactoriaDAO;

/**
 * Servlet implementation class FrontController
 */
@WebServlet(name = "FrontController", urlPatterns = { "*.ctrl", "" })
public class FrontController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	// protected static final String HOME = "index.html";
	// protected static final String LOGIN = "login.html";
	protected static final String ERROR = "error.jsp";
	protected static final String INICIO = "bienvenido.jsp";
	protected static final String INICIOLOG = "faceletsWelcome.xhtml";

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	public void doGet(HttpServletRequest peticion, HttpServletResponse respuesta)
			throws ServletException, java.io.IOException {
		procesa(peticion, respuesta);
	}

	public void doPost(HttpServletRequest peticion, HttpServletResponse respuesta)
			throws ServletException, java.io.IOException {
		procesa(peticion, respuesta);
	}

	// Método de procesamiento
	protected void procesa(HttpServletRequest peticion, HttpServletResponse respuesta)
			throws ServletException, java.io.IOException {

		

		// Utiliza una clase Helper para analizar la acción a realizar
		PeticionHelper peticionHelper = new PeticionHelper(peticion);
		// Acciones comunes de la aplicación
		// 1. Si rompe el flujo de navegación, se envía a la página de inicio
		// 2. Si accede a un recurso para el que necesita estar validado,se envía a la
		// página de login
		if (peticionHelper.necesitaValidacion()) {
			visualizar(ERROR, peticion, respuesta);
			return;
		}

		// 3. Cualquier otra acción común
		// Obtiene la acción a ejecutar asociada a la petición
		Accion acc = peticionHelper.getAccion();

		// La página no existe se envía una página de error
		if (acc == null) {
			visualizar(ERROR, peticion, respuesta);
			return;
		}

		// Ejecuta la acción ,obtiene la vista y la visualiza
		String vista = acc.ejecutar(peticion, respuesta, getServletConfig().getServletContext());
		visualizar(vista, peticion, respuesta);
	}

	protected void visualizar(String vista, HttpServletRequest peticion, HttpServletResponse respuesta)
			throws ServletException, IOException {
		respuesta.sendRedirect(vista);

	}

}
