package ejercicio6;

import java.util.List;


import modelo.CityResult;
import servicio.controlador.ServicioGeoNames;
import servicio.tipos.City;

public class Main {

	public static void main(String[] args) {
		
		List<CityResult> ciudades = ServicioGeoNames.getUnicaInstancia().buscar("Cartagena");
		for (CityResult city : ciudades) {
			System.out.println(city);
		}
		
		City city = ServicioGeoNames.getUnicaInstancia().getCiudad("2520058");
		System.out.println(city.getName());
		System.out.println("Fin");
	}

}
