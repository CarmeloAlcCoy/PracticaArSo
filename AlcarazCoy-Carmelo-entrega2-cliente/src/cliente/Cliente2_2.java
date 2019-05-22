package cliente;

import javax.ws.rs.core.MediaType;

import servicio.clases.CiudadResultado;
import servicio.clases.CiudadesFavoritas;
import servicio.clases.ListadoCiudades;
import servicio.tipos.City;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class Cliente2_2 {
	private static final String URL_SERVICIO = "http://localhost:8080/AlcarazCoy-Carmelo-entrega2/rest/ciudades";

	public static void main(String[] args) {
		
		Client cliente = Client.create();
		
		System.out.println("***Realizar la búsqueda 'cartagena'***");
		
		WebResource recurso = cliente.resource(URL_SERVICIO+"?ciudad=cartagena");
		
		Builder builder = recurso.accept(MediaType.APPLICATION_XML);
		ClientResponse respuesta = builder.method("GET", ClientResponse.class);
		System.out.println("Código de retorno: " + respuesta.getStatus());
		ListadoCiudades lc = respuesta.getEntity(ListadoCiudades.class);
		for (CiudadResultado c : lc.getResultados()) {
			System.out.println(c.getName());
		}

		System.out.println("***Obtener la ciudad 'cartagena'***");
		
		recurso = cliente.resource(URL_SERVICIO+"/2520058");
		respuesta = recurso.method("GET", ClientResponse.class);
		System.out.println("Código de retorno: " + respuesta.getStatus());
		City c = respuesta.getEntity(City.class);
		System.out.println(c.getName()+"("+c.getUrlWikipedia()+")");
		System.out.println("Interest Place:"+c.getInterestPlace().get(0).getName());
		
		System.out.println("***Crear documento favoritos y añadir Cartagena***");
		recurso = cliente.resource(URL_SERVICIO+"/favoritas");
		respuesta = recurso.method("POST", ClientResponse.class);
		System.out.println("Código de retorno: " + respuesta.getStatus());
		String path = respuesta.getLocation().toString();
		System.out.println(path);
		
		recurso = cliente.resource(path+"/2520058");
		respuesta = recurso.method("PUT", ClientResponse.class);
		System.out.println("Código de retorno: " + respuesta.getStatus());
		
		System.out.println("***Recuperar documento favoritos***");
		
		recurso = cliente.resource(path);
		respuesta = recurso.method("GET", ClientResponse.class);
		System.out.println("Código de retorno: " + respuesta.getStatus());
		
		CiudadesFavoritas cf = respuesta.getEntity(CiudadesFavoritas.class);
		System.out.println(cf.getId());
		for (String str : cf.getCiudadesFavoritas()) {
			System.out.println(str);
		}
		
		System.out.println("***Eliminar Cartagena del documento de favoritos***");
		recurso = cliente.resource(path+"/2520058");
		respuesta = recurso.method("DELETE", ClientResponse.class);
		System.out.println("Código de retorno: " + respuesta.getStatus());
		
		System.out.println("***Recuperar documento favoritos***");
		
		recurso = cliente.resource(path);
		respuesta = recurso.method("GET", ClientResponse.class);
		System.out.println("Código de retorno: " + respuesta.getStatus());
		
		cf = respuesta.getEntity(CiudadesFavoritas.class);
		System.out.println(cf.getId());
		if(cf.getCiudadesFavoritas()==null || cf.getCiudadesFavoritas().isEmpty()) 
			System.out.println("Vacio");
		else
			for (String str : cf.getCiudadesFavoritas()) {
				System.out.println(str);
			}
		
		System.out.println("***Eliminar el documento de favoritos***");
		recurso = cliente.resource(path);
		respuesta = recurso.method("DELETE", ClientResponse.class);
		System.out.println("Código de retorno: " + respuesta.getStatus());
		
		System.out.println("***Realizar la búsqueda 'cartagena' en JSON***");
		recurso = cliente.resource(URL_SERVICIO+"?ciudad=cartagena");
		
		builder = recurso.accept(MediaType.APPLICATION_JSON);
		respuesta = builder.method("GET", ClientResponse.class);
		System.out.println("Código de retorno: " + respuesta.getStatus());
		lc = respuesta.getEntity(ListadoCiudades.class);
		for (CiudadResultado c1 : lc.getResultados()) {
			System.out.println(c1.getName());
		}
	}
}