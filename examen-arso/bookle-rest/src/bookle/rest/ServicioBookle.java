package bookle.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import bookle.controlador.BookleControlador;
import bookle.controlador.BookleControladorImpl;
import bookle.tipos.Actividad;

// Bien.

@Path("actividades")
public class ServicioBookle {

	private BookleControlador controlador = BookleControladorImpl.getUnicaInstancia();

	@Context
	private UriInfo uriInfo;
	@Context
	private HttpServletRequest request;

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Response getActividad(@PathParam("id") String id) {
		Actividad actividad = controlador.getActividad(id);

		if (actividad != null)
			return Response.status(Status.OK).entity(actividad).build();
		else
			return Response.status(Status.BAD_REQUEST).build();
	}

	@POST
	public Response crearActividad(@FormParam("titulo") String titulo, @FormParam("descripcion") String descripcion,
			@FormParam("profesor") String profesor, @FormParam("email") String email) {

		String id = controlador.createActividad(titulo, descripcion, profesor, email);

		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(id);

		return Response.created(builder.build()).build();
	}

	@DELETE
	@Path("/{id}")
	public Response eliminarActividad(@PathParam("id") String id) {
		controlador.removeActividad(id);
		return Response.noContent().build();
	}

	@PUT
	@Path("/{id}")
	public Response actualizarActividad(@PathParam("id") String id, @FormParam("titulo") String titulo,
			@FormParam("descripcion") String descripcion, @FormParam("profesor") String profesor,
			@FormParam("email") String email) {

		controlador.updateActividad(id, titulo, descripcion, profesor, email);
		return Response.noContent().build();
	}

	@POST
	@Path("/{id}/agenda")
	public Response anadirFechaActividad(@PathParam("id") String id, @FormParam("fecha") String fecha) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");

		try {
			controlador.addDiaActividad(id, sdf.parse(fecha), 5);
		} catch (ParseException e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).build();
		}

		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(fecha);
		return Response.created(builder.build()).build();

	}

	@DELETE
	@Path("/{id}/agenda/{fecha}")
	public Response eliminarDiaAgenda(@PathParam("id") String id, @PathParam("fecha") String fecha) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");

		try {
			controlador.removeDiaActividad(id, sdf.parse(fecha));
		} catch (ParseException e) {
			return Response.status(Status.BAD_REQUEST).build();
		}

		return Response.noContent().build();
	}
}