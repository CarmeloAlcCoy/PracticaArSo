package ejercicio1_8;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class Ejercicio5 {

	public static void main(String[] args) throws FileNotFoundException, XMLStreamException {

		XMLInputFactory xif = XMLInputFactory.newInstance();
		XMLStreamReader reader = xif.createXMLStreamReader(new FileInputStream("ejercicio1.3/ejercicio3.xml"));

		LinkedList<String> pila = new LinkedList<String>();
		int suma = 0;
		int notas = 0;

		while (reader.hasNext()) {
			int evento = reader.next();

			switch (evento) {
			case XMLStreamConstants.START_ELEMENT:
				pila.push(reader.getLocalName());
				break;

			case XMLStreamConstants.END_ELEMENT:
				pila.pop();
				break;
			case XMLStreamConstants.CHARACTERS:
				String texto = reader.getText();
				String elemento = pila.peek();
				if (elemento.equals("nota")) {
					String elementoAnt = pila.get(1);
					if (elementoAnt.equals("calificacion")) {
						notas++;
						int nota = Integer.parseInt(texto);
						suma += nota;
					}
				}
				break;
			}
		}
		System.out.println("La media de las calificaciones es: "+suma/notas+".");

	}
}
