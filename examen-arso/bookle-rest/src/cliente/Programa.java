package cliente;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import bookle.tipos.Actividad;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class Programa {
	private static final String URL_SERVICIO = "http://localhost:8080/bookle-rest/rest/";

	public static void main(String[] args) {
		// Invocar operación: crear una actividad
		System.out.println("***Creamos una actividad***");
		Client cliente = Client.create();
		String path = "actividades";
		WebResource recurso = cliente.resource(URL_SERVICIO + path);
		MultivaluedMap<String, String> parametros = new MultivaluedMapImpl();
		parametros.add("titulo", "Actividad 5");
		parametros.add("descripcion", "Ejemplo");
		parametros.add("profesor", "Pepe");
		parametros.add("email", "pepe@gmail.com");
		ClientResponse respuesta = recurso.method("POST", ClientResponse.class, parametros);
		System.out.println("Código de retorno: " + respuesta.getStatus());
		String actividadURL = respuesta.getLocation().toString();
		System.out.println("Actividad: " + actividadURL);

		// Invocar la operación: consultar una actividad en formato XML
		// Se utiliza la URL obtenida en el paso anterior
		System.out.println("***Consultamos la actividad***");
		recurso = cliente.resource(actividadURL);
		Builder builder = recurso.accept(MediaType.APPLICATION_XML);

		respuesta = builder.method("GET", ClientResponse.class);

		Actividad actividad = respuesta.getEntity(Actividad.class);

		System.out.println("Titulo: " + actividad.getTitulo());

		System.out.println("***Actualizamos la actividad***");
		// Invocar operación: actualizar una actividad
		path = "actividades/" + actividad.getId();
		recurso = cliente.resource(URL_SERVICIO + path);
		parametros = new MultivaluedMapImpl();
		parametros.add("titulo", "Actividad Modificada");
		parametros.add("descripcion", "Ejemplo Modificado");
		parametros.add("profesor", "Pepe");
		parametros.add("email", "pepe@gmail.com");
		respuesta = recurso.method("PUT", ClientResponse.class, parametros);
		System.out.println("Código de retorno: " + respuesta.getStatus());

		recurso = cliente.resource(actividadURL);
		builder = recurso.accept(MediaType.APPLICATION_XML);

		respuesta = builder.method("GET", ClientResponse.class);

		actividad = respuesta.getEntity(Actividad.class);

		System.out.println("Titulo: " + actividad.getTitulo());

		System.out.println("***Añadimos un día a la actividad***");
		// Invocar operación: añadir dia
		path = "actividades/" + actividad.getId() + "/agenda";
		recurso = cliente.resource(URL_SERVICIO + path);
		parametros = new MultivaluedMapImpl();
		parametros.add("fecha", "15-04-2019");
		respuesta = recurso.method("POST", ClientResponse.class, parametros);
		System.out.println("Código de retorno: " + respuesta.getStatus());
		String fechaURL = respuesta.getLocation().toString();
		System.out.println("Fecha: " + fechaURL);

		System.out.println("***Añadimos un día a la actividad***");
		// Invocar operación: añadir dia
		path = "actividades/" + actividad.getId() + "/agenda";
		recurso = cliente.resource(URL_SERVICIO + path);
		parametros = new MultivaluedMapImpl();
		parametros.add("fecha", "16-04-2019");
		respuesta = recurso.method("POST", ClientResponse.class, parametros);
		System.out.println("Código de retorno: " + respuesta.getStatus());
		fechaURL = respuesta.getLocation().toString();
		System.out.println("Fecha: " + fechaURL);

		// Invocar la operación: eliminar dia
		System.out.println("***Eliminamos un día a la actividad***");
		recurso = cliente.resource(fechaURL);
		parametros = new MultivaluedMapImpl();
		respuesta = recurso.method("DELETE", ClientResponse.class, parametros);
		System.out.println("Código de retorno: " + respuesta.getStatus());

		// Invocar la operación: eliminar dia
		System.out.println("***Eliminamos una actividad***");
		recurso = cliente.resource(URL_SERVICIO + "actividades/8d44d8a0-cbe6-4449-a4f6-fb98265050eb");
		parametros = new MultivaluedMapImpl();
		respuesta = recurso.method("DELETE", ClientResponse.class, parametros);
		System.out.println("Código de retorno: " + respuesta.getStatus());
		System.out.println("Hecho");
	}
}