package sevlets;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.LinkedList;
import java.util.List;
import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jms.ReceptorCola;

/**
 * Servlet implementation class ServletBuzonSugerencias
 */
@WebServlet("/Buzon")
public class ServletBuzonSugerencias extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final long TIEMPOESPERA = 1000;
	private List<String> sugerencias = new LinkedList<String>();

	public ServletBuzonSugerencias() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		procesa(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		procesa(request, response);
	}

	protected void procesa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		//Recibimos si hay sugerencias
		recibirSugerencias();
		
		// Escribimos la respuesta
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<html> <head> <title> Buzón de Sugerencias </title> </head> <body>");
		out.println("<h1>Sugerencias</h1>");
		for (String sugerencia: sugerencias) {
			out.println("<a>"+sugerencia+"</a><br>");
		}
		

		out.println("</body>");
		out.println("</html>");

	}

	public void recibirSugerencias() {
		String recibido = "";
		while (recibido != null) {
			try {
				recibido = ReceptorCola.recibir(TIEMPOESPERA);
			} catch (NamingException | JMSException e) {
				recibido=null;
			} 
			if (recibido != null) {
				sugerencias.add(recibido);
			}
		}
	}

}
