package servicio.rest;

import javax.ws.rs.core.Response;

import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import servicio.controlador.CityServiceException;

import static servicio.controlador.Constants.INVALID_ID;
import static servicio.controlador.Constants.INVALID_PARAMETER;
import static servicio.controlador.Constants.FIELD_NOT_FOUND;

@Provider
public class ErrorHandler implements ExceptionMapper<CityServiceException> {

	@Override
	public Response toResponse(CityServiceException arg0) {
		String source = arg0.getSource();
		Response response;
		if(source.startsWith(INVALID_ID)){
			response = Response.status(Response.Status.BAD_REQUEST)
					.entity(new Error(400, source, arg0.getCausa() ))
					.build();
		}else if (source.startsWith(INVALID_PARAMETER)){
			response = Response.status(Response.Status.BAD_REQUEST)
					.entity(new Error(404, source, arg0.getCausa() ))
					.build();
		}else if(source.startsWith(FIELD_NOT_FOUND)){
			response = Response.status(Response.Status.NOT_FOUND).build();
		}
		else {
			response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		} 
		return response;
	}

}
