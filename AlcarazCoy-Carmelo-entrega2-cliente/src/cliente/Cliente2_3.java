package cliente;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import servicio.clases.CiudadResultadoHAL;
import servicio.clases.ListadoCiudadesHAL;
import servicio.clases.atom.Entry;
import servicio.clases.atom.ListadoCiudadesAtom;


public class Cliente2_3 {

	private static final String URL_SERVICIO = "http://localhost:8080/AlcarazCoy-Carmelo-entrega2/rest/ciudades";

	
	public static void main(String[] args) {
		
		Client cliente = Client.create();
		System.out.println("***Búsqueda en formato kml***");
		WebResource recurso = cliente.resource(URL_SERVICIO+"/kml?ciudad=cartagena");
		
		ClientResponse respuesta = recurso.method("GET", ClientResponse.class);
		System.out.println("Código de retorno: " + respuesta.getStatus());
		System.out.println(respuesta.getEntity(String.class).toString());
		
		System.out.println("***Búsqueda en formato atom***");
		
		recurso = cliente.resource(URL_SERVICIO+"/atom?ciudad=cartagena");
		respuesta = recurso.method("GET", ClientResponse.class);
		System.out.println("Código de retorno: " + respuesta.getStatus());
		ListadoCiudadesAtom lca = respuesta.getEntity(ListadoCiudadesAtom.class);
		System.out.println(lca.getTitle());
		for (Entry e : lca.getEntries()) {
			System.out.println(e.getTitle());
		}		
		
		System.out.println("***Búsqueda en formato hal***");
		
		recurso = cliente.resource(URL_SERVICIO+"/hal?ciudad=cartagena");
		respuesta = recurso.method("GET", ClientResponse.class);
		System.out.println("Código de retorno: " + respuesta.getStatus());
		ListadoCiudadesHAL lch = respuesta.getEntity(ListadoCiudadesHAL.class);
		System.out.println("Results:"+lch.getTotal());
		for (CiudadResultadoHAL ch : lch.get_embedded()) {
			System.out.println(ch.getName());
		}	
		
	}
	
}
