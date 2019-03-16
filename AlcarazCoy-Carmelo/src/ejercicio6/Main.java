package ejercicio6;

import java.util.List;

import modelo.City;
import servicio.controlador.ServicioGeoNames;

public class Main {

	public static void main(String[] args) {
		
		List<City> ciudades = ServicioGeoNames.getUnicaInstancia().buscar("Cartagena");
		for (City city : ciudades) {
			System.out.println(city);
		}
	}

}
