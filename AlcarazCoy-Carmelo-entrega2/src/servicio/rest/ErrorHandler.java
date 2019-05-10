package servicio.rest;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import servicio.controlador.CityServiceException;

import static servicio.controlador.Constants.INVALID_ID;

public class ErrorHandler implements ExceptionMapper<CityServiceException> {

	@Override
	public Response toResponse(CityServiceException arg0) {
		String source = arg0.getSource();
		Response response;
		if(source.startsWith(INVALID_ID)){
			response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(arg0.getMessage()).build();
		}else {
			response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(arg0.getMessage()).build();
		} 
		return response;
	}

}
