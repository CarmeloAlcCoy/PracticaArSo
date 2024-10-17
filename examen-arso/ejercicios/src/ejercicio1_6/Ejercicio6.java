package ejercicio1_6;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class Ejercicio6 {

	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		double suma=0;
		// 1. Obtener una factoría
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
		// 2. Pedir a la factoría la construcción del analizador
		DocumentBuilder analizador = factoria.newDocumentBuilder();
		// 3. Analizar el documento
		Document documento = analizador.parse("ejercicio1.3/ejercicio3.xml");
		
		NodeList calificaciones = documento.getElementsByTagName("calificacion");
		
		for (int i=0; i<calificaciones.getLength(); i++) {
			Element calificacion = (Element) calificaciones.item(i);
			NodeList notas = calificacion.getElementsByTagName("nota");
			Element nota = (Element) notas.item(0);
			suma += Double.parseDouble(nota.getTextContent());
	
		}
		
		System.out.println("La media de las calificaciones es: "+suma/calificaciones.getLength()+".");
		
	}
}
