package ejercicio1_5;


import java.util.LinkedList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Manejador extends DefaultHandler {

	private LinkedList<String> pila;
	private int suma;
	private int notas;

	public Manejador() {
		super();
		pila = new LinkedList<String>();
	}

	@Override
	public void startDocument() throws SAXException {

		pila = new LinkedList<String>();
		suma = 0;
		notas = 0;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		pila.push(qName);

	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {

		String texto = new String(ch, start, length);
		String elemento = pila.peek();
		if (elemento.equals("nota")) {
			String elementoAnt = pila.get(1);
			if (elementoAnt.equals("calificacion")) {
				notas++;
				int nota = Integer.parseInt(texto);
				suma += nota;
			}
		}

	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {

		pila.pop();
		 
	}

	public double getMedia() {
		return suma / notas;
	}

}
