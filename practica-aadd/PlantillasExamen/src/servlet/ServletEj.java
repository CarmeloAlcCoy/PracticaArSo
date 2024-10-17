package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/ServletEj")
public class ServletEj extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Coger un parametro o cabecera
		String nombre = request.getParameter("nombre");
		String referer = request.getHeader("Referer");
		
		//Estado por defecto el siguiente
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("text/html");
		response.setHeader("refresh", "10; index.html");
		
		PrintWriter out = response.getWriter();
		out.println("JEJE");
		
		response.sendError(HttpServletResponse.SC_FORBIDDEN, "Por aquí no locuelo");
		response.sendRedirect("index.html");
		HttpSession sesion = request.getSession();
		sesion.getAttribute("Nombre");
		sesion.setAttribute("Nombre", "Juan");
		
		Cookie c = new Cookie("id", "identificador");
		response.addCookie(c);
		request.getCookies();
		
		ServletContext context = getServletConfig().getServletContext();
		context.getAttribute("atributo");
		context.setAttribute("atributo", new Object());
		
			
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
