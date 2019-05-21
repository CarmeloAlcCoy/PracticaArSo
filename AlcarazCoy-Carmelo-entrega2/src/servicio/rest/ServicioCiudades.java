package servicio.rest;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.w3c.dom.Node;

import servicio.clases.CiudadesFavoritas;
import servicio.clases.ListadoCiudades;
import servicio.clases.ListadoCiudadesAtom;
import servicio.clases.ListadoCiudadesJSON;
import servicio.controlador.CityServiceException;
import servicio.controlador.ServicioGeoNames;
import servicio.tipos.City;

import static servicio.controlador.Constants.INVALID_PARAMETER;

@Path("ciudades")
public class ServicioCiudades {

	private ServicioGeoNames controlador = ServicioGeoNames.getUnicaInstancia();

	@Context
	private UriInfo uriInfo;
	@Context
	private HttpServletRequest request;
	@Context
	private ServletContext context;

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response buscarCiudad(@QueryParam("ciudad") String busqueda) {
		return buscarCiudadXML(busqueda);

	}

	@GET
	@Path("xml")
	@Produces(MediaType.APPLICATION_XML)
	public Response buscarCiudadXML(@QueryParam("ciudad") String busqueda) {
		if (busqueda == null)
			throw new CityServiceException(INVALID_PARAMETER, "Must Provide Parameter 'Ciudad'");
		ListadoCiudades ciudades = controlador.getResultadosBusquedaXML(busqueda);
		return Response.status(Status.OK).entity(ciudades).build();
	}

	@GET
	@Path("atom")
	@Produces(MediaType.APPLICATION_ATOM_XML)
	public Response buscarCiudadATOM(@QueryParam("ciudad") String busqueda, @QueryParam("startRow") String startRow) {
		if (busqueda == null)
			throw new CityServiceException(INVALID_PARAMETER, "Must Provide Parameter 'Ciudad'");
		int start = 1;
		if (startRow != null) {
			try {
				start = Integer.parseInt(startRow);
			} catch (NumberFormatException e) {
				throw new CityServiceException(INVALID_PARAMETER, "'startRow must be an integer'");
			}
		}
		ListadoCiudadesAtom ciudades = controlador.getResultadosBusquedaATOM(busqueda, start,
				uriInfo.getAbsolutePath().toString());
		return Response.status(Status.OK).entity(ciudades).build();

	}

	@GET
	@Path("kml")
	@Produces(MediaType.APPLICATION_XML)
	public Response buscarCiudadKML(@QueryParam("ciudad") String busqueda) {
		if (busqueda == null)
			throw new CityServiceException(INVALID_PARAMETER, "Must Provide Parameter 'Ciudad'");
		Node ciudades = controlador.getResultadosBusquedaKML(busqueda, context);
		return Response.status(Status.OK).entity(ciudades).build();
	}

	@GET
	@Path("json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarCiudadJSON(@QueryParam("ciudad") String busqueda, @QueryParam("startRow") String startRow) {
		if (busqueda == null)
			throw new CityServiceException(INVALID_PARAMETER, "Must Provide Parameter 'Ciudad'");
		int start = 1;
		if (startRow != null) {
			try {
				start = Integer.parseInt(startRow);
			} catch (NumberFormatException e) {
				throw new CityServiceException(INVALID_PARAMETER, "'startRow must be an integer'");
			}
		}
		ListadoCiudadesJSON ciudades = controlador.getResultadosBusquedaJSON(busqueda, start,
				uriInfo.getAbsolutePath().toString());
		return Response.status(Status.OK).entity(ciudades).type("application/hal+json").build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Response getCiudad(@PathParam("id") String id) {
		City city = controlador.getCiudad(id);

		if (city != null)
			return Response.status(Status.OK).entity(city).build();
		else
			return Response.status(Status.BAD_REQUEST).build();
	}

	@POST
	@Path("/favoritas")
	@Produces(MediaType.APPLICATION_XML)
	public Response crearDocumentoFavoritos() {
		String id = controlador.crearDocumentoFavoritos();

		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(id);
		return Response.created(builder.build()).build();
	}

	@GET
	@Path("/favoritas/{idDocumento}")
	@Produces(MediaType.APPLICATION_XML)
	public Response recuperarDocumentoFavoritos(@PathParam("idDocumento") String id) {

		CiudadesFavoritas favs = controlador.getFavoritos(id);
		return Response.status(Status.OK).entity(favs).build();
	}

	@DELETE
	@Path("/favoritas/{idDocumento}")
	public Response borrarDocumentoFavoritos(@PathParam("idDocumento") String id) {

		controlador.removeDocumentoFavoritos(id);
		return Response.noContent().build();
	}

	@PUT
	@Path("/favoritas/{idDocumento}/{idGeoNames}")
	public Response anadirCiudadDocumentoFavoritos(@PathParam("idDocumento") String idDocumento,
			@PathParam("idGeoNames") String idGeoNames) {

		controlador.addCiudadFavorita(idDocumento, idGeoNames);
		return Response.noContent().build();
	}

	@DELETE
	@Path("/favoritas/{idDocumento}/{idGeoNames}")
	public Response borrarCiudadDocumentoFavoritos(@PathParam("idDocumento") String idDocumento,
			@PathParam("idGeoNames") String idGeoNames) {

		controlador.removeCiudadFavorita(idDocumento, idGeoNames);
		return Response.noContent().build();
	}

}