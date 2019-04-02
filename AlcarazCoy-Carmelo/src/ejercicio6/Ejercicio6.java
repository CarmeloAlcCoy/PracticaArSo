package ejercicio6;

import java.util.List;



import servicio.controlador.ServicioGeoNames;
import servicio.tipos.City;
import servicio.tipos.CiudadResultado;;

public class Ejercicio6 {

	public static void main(String[] args) {
		
		List<CiudadResultado> ciudades = ServicioGeoNames.getUnicaInstancia().buscar("Cartagena");
		for (CiudadResultado city : ciudades) {
			System.out.println(city);
		}
		
		City city = ServicioGeoNames.getUnicaInstancia().getCiudad("2520058");
		System.out.println(city.getName());
		System.out.println("Fin");
	}

}
