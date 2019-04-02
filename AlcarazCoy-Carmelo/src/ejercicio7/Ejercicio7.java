package ejercicio7;

import servicio.controlador.ServicioGeoNames;
import servicio.tipos.CiudadesFavoritas;
import servicio.tipos.ListadoCiudades;

public class Ejercicio7 {

	public static void main(String[] args) {
		ServicioGeoNames servicio = ServicioGeoNames.getUnicaInstancia();
		
		ListadoCiudades cities = servicio.getResultadosBusquedaXML("Lorca");
		
		cities.print();
		
		String id = servicio.crearDocumentoFavoritos();
		String id1 = servicio.crearDocumentoFavoritos();
		
		servicio.addCiudadFavorita(id, "2520058");
		
		servicio.addCiudadFavorita(id1, "2520058");
		
		servicio.addCiudadFavorita(id, "3687238");
		
		servicio.removeCiudadFavorita(id, "3687238");
		
		servicio.addCiudadFavorita(id, "3687238");
		
		CiudadesFavoritas ciudad = servicio.getFavoritos(id);
		for (String string : ciudad.getCiudadesFavoritas()) {
			System.out.println(string);
		}
		
		servicio.removeDocumentoFavoritos(id1);
		
		System.out.println("Fin");
	}
}
