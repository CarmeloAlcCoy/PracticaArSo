package ejercicio3;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class Programa {

	public static void main(String[] args) throws ParserConfigurationException, SAXException {
		// 1. Obtener una factor�a
		SAXParserFactory factoria = SAXParserFactory.newInstance();

		// 2. Pedir a la factor�a la construcci�n del analizador
		SAXParser analizador = factoria.newSAXParser();

		// 3. Analizar el documento
		try {
			Manejador manejador = new Manejador();
			//He guardado el resutado de la siguiente consulta en el fichero search.xml
			//http://api.geonames.org/search?name=Cartagena&maxRows=10&username=arso
			analizador.parse("xml/search.xml", manejador);

			for (City ciudad : manejador.getCiudades()) {
				System.out.println(ciudad);
			}
			

		} catch (IOException e) {
			System.out.println("El documento no ha podido ser le�do");
		} catch (SAXException e) {
			System.out.println("Error de pocesamiento: " + e.getMessage());
		}

		
	}
}
