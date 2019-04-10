package ejercicio2_1;

import java.util.List;

import servicio.controlador.ServicioGeoNames;
import servicio.tipos.Book;
import servicio.tipos.City;

public class Ejercicio1 {

	public static void main(String[] args) {

		
		City city = ServicioGeoNames.getUnicaInstancia().getCiudad("2520058");
		List<Book> list = city.getBook();
		for (Book book : list) {
			System.out.println(book.getTitle());
		}

		System.out.println("Fin");
	}

}
