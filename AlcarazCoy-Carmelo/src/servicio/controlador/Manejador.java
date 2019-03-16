package servicio.controlador;

import java.util.LinkedList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import modelo.City;

public class Manejador extends DefaultHandler{
	
	private static final String NAME="name";
	private static final String ID="geonameId";
	private static final String COUNTRY="countryName";
	private static final String LATITUDE="lat";
	private static final String LONGITUDE="lng";

	/**
	 * TODO Los Errores
	 */
	
	private List<City> cities;
	private LinkedList<String> pila;
	private City city;
	

	@Override
	public void startDocument() throws SAXException {
		cities = new LinkedList<City>();
		pila = new LinkedList<String>();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		pila.push(qName);
		if(qName.equals("geoname")) 
			city = new City();
		
		 

	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {

		String texto = new String(ch, start, length);
		String elemento = pila.peek();
	
		switch (elemento) {
		case NAME:
			city.setName(texto);
			break;
		case ID:
			city.setId(Integer.parseInt(texto));
			break;
		case COUNTRY:
			city.setCountry(texto);
			break;

		case LATITUDE:
			city.setLatitude(Double.parseDouble(texto));
			break;

		case LONGITUDE:
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

	public List<City> getCiudades() {
		return cities;
	}
	
	
}
