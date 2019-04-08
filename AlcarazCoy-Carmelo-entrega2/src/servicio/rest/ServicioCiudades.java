package servicio.rest;

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

import servicio.controlador.ServicioGeoNames;
import servicio.tipos.City;



@Path("ciudades")
public class ServicioCiudades {

	private ServicioGeoNames controlador = ServicioGeoNames.getUnicaInstancia();

	@Context
	private UriInfo uriInfo;
	@Context
	private HttpServletRequest request;

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Response getActividad(@PathParam("id") String id) {
		City city = controlador.getCiudad(id);

		if (city != null)
			return Response.status(Status.OK).entity(city).build();
		else
			return Response.status(Status.BAD_REQUEST).build();
	}

	
}