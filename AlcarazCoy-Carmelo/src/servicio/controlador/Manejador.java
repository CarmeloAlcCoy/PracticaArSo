package servicio.controlador;

import java.util.LinkedList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import modelo.CityResult;

import static servicio.controlador.Constants.*;

public class Manejador extends DefaultHandler{
	
	private List<CityResult> cities;
	private LinkedList<String> pila;
	private CityResult city;
	
	@Override
	public void startDocument() throws SAXException {
		cities = new LinkedList<CityResult>();
		pila = new LinkedList<String>();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		pila.push(qName);
		if(qName.equals("geoname")) 
			city = new CityResult();
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {

		String texto = new String(ch, start, length);
		String elemento = pila.peek();
	
		switch (elemento) {
		case SEARCH_TAG_NAME:
			city.setName(texto);
			break;
		case SEARCH_TAG_ID:
			city.setId(Integer.parseInt(texto));
			break;
		case SEARCH_TAG_COUNTRY:
			city.setCountry(texto);
			break;

		case SEARCH_TAG_LATITUDE:
			city.setLatitude(Double.parseDouble(texto));
			break;

		case SEARCH_TAG_LONGITUDE:
			city.setLongitude(Double.parseDouble(texto));
			break;

		default:
			break;
		}
		

	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		String elemento = pila.pop();
		if(elemento.equals("geoname")) {
			cities.add(city);
		}
		 
	}

	public List<CityResult> getCiudades() {
		return cities;
	}
	
	
}
