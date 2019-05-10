package ejercicio4;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.SAXException;

public class Ejercicio4 {

	public static void main(String[] args) throws ParserConfigurationException, SAXException,  XMLStreamException, CityServiceException {
		
		Analizador analizador = new Analizador();
		analizador.analiza();
		
		System.out.println("Fin");
		
	}
	
	
	
}
