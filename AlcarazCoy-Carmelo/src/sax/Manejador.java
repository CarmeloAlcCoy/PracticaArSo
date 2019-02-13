package sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Manejador extends DefaultHandler{

	/*
	 * Atributos
	 * 
	 * Los atributos son necesarios para: - Recordar el estado del procesamiento
	 * (dentroElemento1, etc.) - Almacenar el resultado del procesamiento. Se
	 * ofrecer� un m�todo de consulta para obtener el resultado.
	 * 
	 */

	/*
	 * Nota: cuando el estado del procesamiento es complejo, puede resultar �til
	 * usar una pila, como por ejemplo, LinkedList<String>
	 * 
	 * - En el evento startElement: pila.push(qName);
	 * 
	 * - En el evento endElement: pila.pop();
	 * 
	 * - En el evento characters obtenemos el elemento al que pertenece el texto:
	 * String elemento = pila.peek();
	 * 
	 */

	@Override
	public void startDocument() throws SAXException {

		/*
		 * Cuando el an�lisis no es simple y requiere atributos, este m�todo est� a
		 * cargo de resetar los atributos.
		 * 
		 * Esto permite que un mismo objeto pueda ser utilizado en varios an�lisis.
		 */

	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		/*
		 * Habitualmente en este evento se realizan dos tareas: - Obtener informaci�n de
		 * los atributos del elemento (attributes). - Poner a verdadero los atributos
		 * que llevan el estado del procesamiento. Ejemplo:
		 * 
		 * if (qName.equals("elemento1")) dentroElemento1 = true;
		 * 
		 * Alternativamente, si se utiliza una pila:
		 * 
		 * pila.push(qName);
		 * 
		 */

	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {

		String texto = new String(ch, start, length);

		/*
		 * En este evento se recupera el texto que envuelve un elemento. Para conocer de
		 * que elemento se trata, debemos consultar los atributos de estado o la cima de
		 * la pila:
		 * 
		 * String elemento = pila.peek()
		 */

	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {

		/*
		 * En este evento establecemos a falso los atributos que llevan el estado del
		 * procesamiento. Ejemplo:
		 * 
		 * if (qName.equals("elemento1")) dentroElemento1 = false;
		 * 
		 * Alternativamente, quitamos la cima de la pila:
		 * 
		 * pila.pop();
		 */
	}
}
