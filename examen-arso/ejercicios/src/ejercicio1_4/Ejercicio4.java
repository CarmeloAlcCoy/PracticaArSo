package ejercicio1_4;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class Ejercicio4 {

	public static void main(String[] args) throws ParserConfigurationException, SAXException {

		
		// 1. Obtener una factor�a
		SAXParserFactory factoria = SAXParserFactory.newInstance();
		// 2. Pedir a la factor�a la construcci�n del analizador
		SAXParser analizador = factoria.newSAXParser(); 
		// 3. Analizar el documento

		try {
			Manejador manejador = new Manejador();
			analizador.parse("ejercicio1.3/ejercicio3.xml", manejador);
			System.out.println("La calificaci�n tiene "+manejador.getNumDiligencias()+" diligencias.");
			System.out.println("La calificaci�n tiene "+manejador.getNumDiligenciasUltMes()+" diligencias en el �ltimo mes.");
		} 
		catch (IOException e) {
			System.out.println("El documento no ha podido ser le�do");
		}
		catch (SAXException e) {
			System.out.println("Error de pocesamiento: " + e.getMessage());
		}

		System.out.println("fin.");		
	}
}