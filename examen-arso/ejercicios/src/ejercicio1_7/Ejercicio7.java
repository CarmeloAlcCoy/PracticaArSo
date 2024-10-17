package ejercicio1_7;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Ejercicio7 {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		
				DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
				DocumentBuilder analizador = factoria.newDocumentBuilder();
				Document documento = analizador.parse("ejercicio1.3/ejercicio3.xml");
				
				NodeList calificaciones = documento.getElementsByTagName("calificacion");
				
				for (int i=0; i<calificaciones.getLength(); i++) {
					Element calificacion = (Element) calificaciones.item(i);
					NodeList notas = calificacion.getElementsByTagName("nota");
					Element nota = (Element) notas.item(0);
					double valor = Double.parseDouble(nota.getTextContent())+0.5;
					if(valor > 10) valor = 10;
					nota.setTextContent(Double.toString(valor));
			
				}
				
				// 1. Construye la factoría de transformación y obtiene un transformador
				TransformerFactory tFactoria = TransformerFactory.newInstance();
				Transformer transformacion = tFactoria.newTransformer();
				// 2. Establece la entrada, como un árbol DOM
				Source input = new DOMSource(documento);
				File fichero = new File("xml/ejercicio3_modificado.xml");
				// 3. Establece la salida, un fichero en disco
				Result output = new StreamResult(fichero );
				// 4. Aplica la transformación
				transformacion.transform(input, output);
				
			}
	}


