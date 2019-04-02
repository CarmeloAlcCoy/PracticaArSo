package servicio.controlador;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javanet.staxutils.IndentingXMLStreamWriter;

import org.w3c.dom.Element;

import modelo.City;
import modelo.MeteoInfo;
import modelo.InterestPlace;

import static servicio.controlador.Constants.*;

/**
 * Esta clase se corresponde con el código implementado en el ejercicio 4
 * adaptao al controlador
 *
 */
public class CityXMLProvider {

	private City city;
	private String countryCode;

	public void recuperarDocumento(String idGeoNames) throws ParseXMLException {

		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
		DocumentBuilder analizador;
		try {
			analizador = factoria.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new ParseXMLException("JAXBUnMarshaller", e.toString());
		}

		int id;
		try {
			id = Integer.parseInt(idGeoNames);
		} catch (NumberFormatException e) {
			throw new ParseXMLException("http://sws.geonames.org/" + idGeoNames + "/about.rdf",
					"Not an integer " + idGeoNames);
		}

		city = new City();
		city.setId(id);

		/*
		 * Document resourceUri; try { resourceUri = analizador.parse(city.getUri()); }
		 * catch (IOException e) { throw new ParseXMLException(city.getUri(),
		 * "Unknown"); }
		 * 
		 * parseResourceUri(resourceUri);
		 */
		Document resource;
		try {
			resource = analizador.parse("http://sws.geonames.org/" + id + "/about.rdf");
		} catch (IOException e) {
			throw new ParseXMLException("http://sws.geonames.org/" + id + "/about.rdf", "Unknown:" + e.toString());
		} catch (SAXException e) {
			throw new ParseXMLException("http://sws.geonames.org/" + id + "/about.rdf",
					"XML invalid File" + e.toString());
		}

		String interestPlacesUrl = parseRDF(resource);

		Document resourceCountry;
		try {
			resourceCountry = analizador.parse(CONSULTA_GEONAMES_COUNTRY + countryCode);
		} catch (IOException e) {
			throw new ParseXMLException(CONSULTA_GEONAMES_COUNTRY + countryCode, "Unknown:" + e.toString());
		} catch (SAXException e) {
			throw new ParseXMLException(CONSULTA_GEONAMES_COUNTRY + countryCode, "XML invalid File" + e.toString());
		}

		parseCountry(resourceCountry);

		Document interestPlacesResource;
		try {
			interestPlacesResource = analizador.parse(interestPlacesUrl);

		} catch (IOException e) {
			throw new ParseXMLException(interestPlacesUrl, "Unknown:" + e.toString());
		} catch (SAXException e) {
			throw new ParseXMLException(interestPlacesUrl, "XML invalid File" + e.toString());
		}

		parseNearBy(interestPlacesResource);

		Document weatherResource;
		String uri = "http://api.geonames.org/findNearByWeather?lat=" + city.getLatitude() + "&lng="
				+ city.getLongitude() + "&username=arso";
		try {
			weatherResource = analizador.parse(uri);

		} catch (IOException e) {
			throw new ParseXMLException(uri, "Unknown:" + e.toString());
		} catch (SAXException e) {
			throw new ParseXMLException(uri, "XML invalid File" + e.toString());
		}
		parseWeather(weatherResource);

		try {
			writeCity();
		} catch (IOException e) {
			throw new ParseXMLException("File:" + RUTA_BD + city.getId() + ".xml",
					"Could not write on the resource:" + e.getMessage());

		} catch (XMLStreamException e) {
			throw new ParseXMLException("XMLStreamException", e.toString());
		}
	}

	/*
	 * private void parseResourceUri(Document resourceUri) throws ParseXMLException
	 * { // <gn:name>Cartagena</gn:name> NodeList list =
	 * resourceUri.getElementsByTagName(NAME_TAG_NAME); if (list.getLength() == 0) {
	 * throw new ParseXMLException(resourceUri.getBaseURI(), FIELD_NOT_FOUND +
	 * NAME_TAG_NAME); } Element name = (Element) list.item(0);
	 * city.setName(name.getTextContent());
	 * 
	 * }
	 */

	private String parseRDF(Document resourceUrl) throws ParseXMLException {

		// Obtenemos el nombre de la ciudad
		NodeList list = resourceUrl.getElementsByTagName(NAME_TAG_NAME);
		if (list.getLength() == 0) {
			throw new ParseXMLException(resourceUrl.getBaseURI(), FIELD_NOT_FOUND + NAME_TAG_NAME);
		}
		Element name = (Element) list.item(0);
		city.setName(name.getTextContent());

		// CountryCode
		list = resourceUrl.getElementsByTagName(COUNTRY_CODE_TAG_NAME);
		if (list.getLength() == 0) {
			throw new ParseXMLException(resourceUrl.getBaseURI(), FIELD_NOT_FOUND + COUNTRY_CODE_TAG_NAME);
		}
		Element country = (Element) list.item(0);
		countryCode = country.getTextContent();

		// Obtenemos la latitud
		list = resourceUrl.getElementsByTagName(LAT_TAG_NAME);
		if (list.getLength() == 0) {
			throw new ParseXMLException(resourceUrl.getBaseURI(), FIELD_NOT_FOUND + LAT_TAG_NAME);
		}
		Element latitude = (Element) list.item(0);
		double d;
		try {
			d = Double.parseDouble(latitude.getTextContent());
		} catch (NumberFormatException e) {
			throw new ParseXMLException(resourceUrl.getBaseURI(), PARSE_DOUBLE_ERROR + LAT_TAG_NAME);
		}
		city.setLatitude(d);

		// Obtenemos la latitud
		list = resourceUrl.getElementsByTagName(LNG_TAG_NAME);
		if (list.getLength() == 0) {
			throw new ParseXMLException(resourceUrl.getBaseURI(), FIELD_NOT_FOUND + LNG_TAG_NAME);
		}
		Element longitude = (Element) list.item(0);

		try {
			d = Double.parseDouble(longitude.getTextContent());
		} catch (NumberFormatException e) {
			throw new ParseXMLException(resourceUrl.getBaseURI(), PARSE_DOUBLE_ERROR + LAT_TAG_NAME);
		}
		city.setLongitude(d);

		// Obtenemos la población del lugar
		list = resourceUrl.getElementsByTagName(POPULATION_TAG_NAME);
		if (list.getLength() == 0) {
			throw new ParseXMLException(resourceUrl.getBaseURI(), FIELD_NOT_FOUND + POPULATION_TAG_NAME);
		}
		Element population = (Element) list.item(0);
		int i;
		try {
			i = Integer.parseInt(population.getTextContent());
		} catch (NumberFormatException e) {
			throw new ParseXMLException(resourceUrl.getBaseURI(), PARSE_INT_ERROR + POPULATION_TAG_NAME);
		}
		city.setPopulation(i);

		// Obtenemos la url en BDPedia
		list = resourceUrl.getElementsByTagName(DBPEDIA_URL_TAG_NAME);
		if (list.getLength() > 0) {
			Element dbpediaUrl = (Element) list.item(0);
			String url = dbpediaUrl.getAttribute(DBPEDIA_URL_ATTRIBUTE_NAME);
			if (!url.isEmpty())
				city.setUrlDBpedia(url);
		}

		// Obtenemos la Url de Wikipedia
		list = resourceUrl.getElementsByTagName(WIKIPEDIA_URL_TAG_NAME);
		if (list.getLength() > 0) {
			Element wikipediaUrl = (Element) list.item(0);
			String url = wikipediaUrl.getAttribute(WIKIPEDIA_URL_ATTRIBUTE_NAME);
			if (!url.isEmpty())
				city.setUrlWikipedia(url);
		}

		// Obtenemos la fecha que fue modificado por última vez
		list = resourceUrl.getElementsByTagName(UPDATED_DATE_TAG_NAME);
		if (list.getLength() == 0) {
			throw new ParseXMLException(resourceUrl.getBaseURI(), FIELD_NOT_FOUND + UPDATED_DATE_TAG_NAME);
		}
		Element date = (Element) list.item(0);
		try {
			city.setUpdatedDate(sdfDate.parse(date.getTextContent()));
		} catch (ParseException e) {
			throw new ParseXMLException(resourceUrl.getBaseURI(), PARSE_DATE_ERROR + UPDATED_DATE_TAG_NAME);
		}

		// Obtenemos la URI del recurso que contiene los lugares de interes
		list = resourceUrl.getElementsByTagName(NEARBY_URL_TAG_NAME);
		if (list.getLength() == 0) {
			throw new ParseXMLException(resourceUrl.getBaseURI(), FIELD_NOT_FOUND + NEARBY_URL_TAG_NAME);
		}
		Element nearBy = (Element) list.item(0);
		String uri = nearBy.getAttribute(NEARBY_URL_ATTRIBUTE_NAME);
		if (uri.isEmpty()) {
			throw new ParseXMLException(resourceUrl.getBaseURI(),
					"Attribute \"rdf:resource\"\" not found in field \"gn:nearbyFeatures\".");
		}
		return uri;
	}

	private void parseCountry(Document resourceCountry) throws ParseXMLException {
		// Obtenemos el nombre de la ciudad
		NodeList list = resourceCountry.getElementsByTagName(COUNTRY_NAME_TAG_NAME);
		if (list.getLength() == 0) {
			throw new ParseXMLException(resourceCountry.getBaseURI(), FIELD_NOT_FOUND + COUNTRY_NAME_TAG_NAME);
		}
		Element country = (Element) list.item(0);
		city.setCountry(country.getTextContent());

	}

	private void parseNearBy(Document document) throws ParseXMLException {
		// System.out.println(document.getBaseURI());
		NodeList list = document.getElementsByTagName(INTEREST_PLACE_TAG_NAME);
		if (list.getLength() == 0) {
			throw new ParseXMLException(document.getBaseURI(), FIELD_NOT_FOUND + INTEREST_PLACE_TAG_NAME);
		}
		List<InterestPlace> places = new ArrayList<InterestPlace>();
		int id = 0;
		for (int i = 0; i < list.getLength(); i++) {
			Element interestPlace = (Element) list.item(i);
			NodeList lista = interestPlace.getElementsByTagName(INTEREST_PLACE_NAME_TAG_NAME);
			if (lista.getLength() == 0) {
				throw new ParseXMLException(document.getBaseURI(), FIELD_NOT_FOUND + INTEREST_PLACE_NAME_TAG_NAME);
			}
			Element e = (Element) lista.item(0);
			String name = e.getTextContent();

			String url = interestPlace.getAttribute(INTEREST_PLACE_URL_TAG_NAME);
			if (url.isEmpty()) {
				throw new ParseXMLException(document.getBaseURI(), "Attribute \"" + INTEREST_PLACE_URL_TAG_NAME
						+ "\"\" not found in field " + INTEREST_PLACE_TAG_NAME);
			}
			Pattern p = Pattern.compile("./([0-9]+)/");
			Matcher m = p.matcher(url);
			m.find();
			try {
				id = Integer.parseInt(m.group(1));
			} catch (IllegalStateException e2) {
				throw new ParseXMLException(document.getBaseURI(), "Could not find ID in Url:" + url);
			}

			places.add(new InterestPlace(name, id));
		}
		city.addPointsOfInterest(places);

	}

	private void parseWeather(Document document) throws ParseXMLException {

		MeteoInfo meteo = new MeteoInfo();
		NodeList list = document.getElementsByTagName(TAKEN_ON_DATE_TAG_NAME);
		if (list.getLength() == 0) {
			throw new ParseXMLException(document.getBaseURI(), FIELD_NOT_FOUND + TAKEN_ON_DATE_TAG_NAME);
		}
		Element time = (Element) list.item(0);
		try {
			meteo.setTakenOn(sdfTime.parse(time.getTextContent()));
		} catch (ParseException e) {
			throw new ParseXMLException(document.getBaseURI(), PARSE_DATE_ERROR + TAKEN_ON_DATE_TAG_NAME);
		}

		list = document.getElementsByTagName(STATION_NAME_TAG_NAME);
		if (list.getLength() == 0) {
			throw new ParseXMLException(document.getBaseURI(), FIELD_NOT_FOUND + STATION_NAME_TAG_NAME);
		}
		Element station = (Element) list.item(0);
		meteo.setStationName(station.getTextContent());

		list = document.getElementsByTagName(CLOUDS_TAG_NAME);
		if (list.getLength() == 0) {
			throw new ParseXMLException(document.getBaseURI(), FIELD_NOT_FOUND + CLOUDS_TAG_NAME);
		}
		Element clouds = (Element) list.item(0);
		meteo.setClouds(clouds.getTextContent());

		list = document.getElementsByTagName(TEMPERATURE_TAG_NAME);
		if (list.getLength() == 0) {
			throw new ParseXMLException(document.getBaseURI(), FIELD_NOT_FOUND + TEMPERATURE_TAG_NAME);
		}
		Element temperature = (Element) list.item(0);
		double d;
		try {
			d = Double.parseDouble(temperature.getTextContent());
		} catch (NumberFormatException e) {
			throw new ParseXMLException(document.getBaseURI(), PARSE_DOUBLE_ERROR + TEMPERATURE_TAG_NAME);
		}
		meteo.setTemperature(d);

		city.setMeteoInfo(meteo);
	}

	private void writeCity() throws FileNotFoundException, XMLStreamException {
		XMLOutputFactory xof = XMLOutputFactory.newInstance();
		XMLStreamWriter writer = xof.createXMLStreamWriter(new FileOutputStream(RUTA_BD + city.getId() + ".xml"));
		writer = new IndentingXMLStreamWriter(writer); // Duda 1
		writer.writeStartDocument("UTF-8", "1.0"); // Solved
		writer.writeStartElement(ELEMENT_CITY);
		writer.writeNamespace("", DEFAULT_NAMESPACE);
		writer.writeNamespace("xsi", XSI_NAMESPACE);
		writer.writeAttribute(XSI_NAMESPACE, "schemaLocation", SCHEMA_LOCATION);

		writer.writeAttribute(GEONAMES_ID, String.valueOf(city.getId()));
		writer.writeAttribute(UPDATEN_ON, sdfDate.format(city.getUpdatedDate()));
		// name
		writeElement(writer, NAME, city.getName());
		// geonamesid
		// writeElement(writer, GEONAMES_ID, Integer.toString(city.getId()));
		// country
		writeElement(writer, COUNTRY, city.getCountry());
		// population
		writeElement(writer, POPULATION, Integer.toString(city.getPopulation()));
		// position
		writer.writeStartElement(POSITION);
		// lat
		writeElement(writer, LATITUDE, Double.toString(city.getLatitude()));
		// lng
		writeElement(writer, LONGITUDE, Double.toString(city.getLongitude()));
		writer.writeEndElement();
		// urlBDpedia
		String urlDBpedia = city.getUrlDBpedia();
		if (urlDBpedia != null && !urlDBpedia.isEmpty())
			writeElement(writer, URL_BDPEDIA, urlDBpedia);
		// urlWikipedia
		String urlWikipedia = city.getUrlWikipedia();
		if (urlWikipedia != null && !urlWikipedia.isEmpty())
			writeElement(writer, URL_WIKIPEDIA, urlWikipedia);

		// meteoInfo
		MeteoInfo meteo = city.getMeteoInfo();
		if (meteo != null) {
			writer.writeStartElement(INFORMACION_METEOROLOGICA);
			writer.writeAttribute(TAKEN_ON, sdfTimeXMLSchema.format(meteo.getTakenOn()));
			// stationName
			writeElement(writer, STATION_NAME, meteo.getStationName());
			// temperature
			writeElement(writer, TEMPERATURE, Double.toString(meteo.getTemperature()));
			// clouds
			writeElement(writer, CLOUDS, meteo.getClouds());
		}
		writer.writeEndElement();
		for (InterestPlace place : city.getPointsOfInterest()) {
			writer.writeStartElement(INTEREST_PLACE);
			writeElement(writer, NAME, place.getName());
			writeElement(writer, ID, Integer.toString(place.getId()));
			writer.writeEndElement();
		}
		writer.writeEndElement();
		writer.writeEndDocument();
		writer.flush();
		writer.close();
	}

	private void writeElement(XMLStreamWriter writer, String name, String value) throws XMLStreamException {
		writer.writeStartElement(name);
		writer.writeCharacters(value);
		writer.writeEndElement();
	}

}
