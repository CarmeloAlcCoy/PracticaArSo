package ejercicio4;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javanet.staxutils.IndentingXMLStreamWriter;
import modelo.City;
import modelo.InterestPlace;
import modelo.MeteoInfo;

public class Analizador {

	private static City city = new City("Cartagena", 2520058, "Spain", 37.60512, -0.98623);
	private static final SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat sdfTimeXMLSchema = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
	private static final SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	
	public void analiza()
			throws ParserConfigurationException, SAXException,  XMLStreamException, CityServiceException {
		// 1. Obtener una factor�a
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
		factoria.setNamespaceAware(true);
		// 2. Pedir a la factor�a la construcción del analizador
		DocumentBuilder analizador = factoria.newDocumentBuilder();
		// 3. Analizar el documento
		
		Document resource;
		try {
			resource = analizador.parse(city.getUri());
		} catch (IOException e) {
			System.err.println("Resource not found");
			return;
		}

		String interestPlacesUrl = parseResourceUrl(resource);

		Document interestPlacesResource;
		try {
			interestPlacesResource = analizador.parse(interestPlacesUrl);
			parseNearBy(interestPlacesResource);
		} catch (IOException e) {
			System.err.println("Resource not found:" + interestPlacesUrl);
			return;
		}

		Document weatherResource;
		try {
			weatherResource = analizador.parse("http://api.geonames.org/findNearByWeather?lat=" + city.getLatitude()
					+ "&lng=" + city.getLongitude() + "&username=arso");
			parseWeather(weatherResource);
		} catch (IOException e) {
			System.err.println("Resource not found:" + interestPlacesUrl);
			return;
		}

		try {
			writeCity();
		} catch (IOException e) {
			System.err.println("Could not write Resource File: xml/" + city.getId() + ".xml");
			return;
		}

	}

	private static void writeCity() throws FileNotFoundException, XMLStreamException {
		XMLOutputFactory xof = XMLOutputFactory.newInstance();
		XMLStreamWriter writer = xof.createXMLStreamWriter(new FileOutputStream("xml/" + city.getId() + ".xml"), "UTF-8");
		writer = new IndentingXMLStreamWriter(writer); // Duda 1
		writer.writeStartDocument("UTF-8", "1.0"); // Solved
		writer.writeStartElement(ELEMENT_CITY);
		writer.writeNamespace("", DEFAULT_NAMESPACE);
		writer.writeNamespace("xsi", XSI_NAMESPACE);
		writer.writeAttribute(XSI_NAMESPACE, "schemaLocation",SCHEMA_LOCATION);
		writer.writeAttribute(DEFAULT_NAMESPACE,GEONAMES_ID, String.valueOf(city.getId()));
		writer.writeAttribute(DEFAULT_NAMESPACE,UPDATEN_ON, sdfDate.format(city.getUpdatedDate()));
		// name
		writeElement(writer, NAME, city.getName());
		// geonamesid
		//writeElement(writer, GEONAMES_ID, Integer.toString(city.getId()));
		// country
		writeElement(writer, COUNTRY, city.getCountry());
		// population
		writeElement(writer, POPULATION, Integer.toString(city.getPopulation()));
		// position
		writer.writeStartElement(DEFAULT_NAMESPACE,POSITION);
		// lat
		writeElement(writer, LATITUDE, Double.toString(city.getLatitude()));
		// lng
		writeElement(writer, LONGITUDE, Double.toString(city.getLongitude()));
		writer.writeEndElement();
		// urlBDpedia
		String urlDBpedia = city.getUrlDBpedia();
		if (urlDBpedia != null && !urlDBpedia.isEmpty() )
			writeElement(writer, URL_BDPEDIA, urlDBpedia);
		// urlWikipedia
		String urlWikipedia = city.getUrlWikipedia();
		if (urlWikipedia != null && !urlWikipedia.isEmpty())
			writeElement(writer, URL_WIKIPEDIA, urlWikipedia);

		// meteoInfo
		MeteoInfo meteo = city.getMeteoInfo();
		if (meteo != null) {
			writer.writeStartElement(DEFAULT_NAMESPACE,INFORMACION_METEOROLOGICA);
			writer.writeAttribute(DEFAULT_NAMESPACE,TAKEN_ON, sdfTimeXMLSchema.format(meteo.getTakenOn()));
			// stationName
			writeElement(writer, STATION_NAME , meteo.getStationName());
			// temperature
			writeElement(writer, TEMPERATURE, Double.toString(meteo.getTemperature()));
			// clouds
			writeElement(writer, CLOUDS, meteo.getClouds());
		}
		writer.writeEndElement();
		for (InterestPlace place : city.getPointsOfInterest()) {
			writer.writeStartElement(DEFAULT_NAMESPACE,INTEREST_PLACE);
			writeElement(writer, NAME, place.getName());
			writeElement(writer, ID, Integer.toString(place.getId()));
			writer.writeEndElement();
		}
		writer.writeEndElement();
		writer.writeEndDocument();
		writer.flush();
		writer.close();
	}

	private static void writeElement(XMLStreamWriter writer, String name, String value) throws XMLStreamException {
		writer.writeStartElement(DEFAULT_NAMESPACE,name);
		writer.writeCharacters(value);
		writer.writeEndElement();
	}

	private static String parseResourceUrl(Document resourceUrl) throws CityServiceException {

		// Obtenemos la población del lugar 
		NodeList list = resourceUrl.getElementsByTagNameNS(POPULATION_NS,POPULATION_TAG_NAME);
		if(list.getLength()==0) {
			throw new CityServiceException(INVALID_ID+city.getId(), FIELD_NOT_FOUND+POPULATION_TAG_NAME);
		}
		Element population = (Element) list.item(0);
		int i;
		try {
			i = Integer.parseInt(population.getTextContent());
		} catch (NumberFormatException e) {
			throw new CityServiceException(INVALID_ID+city.getId(), FIELD_NOT_FOUND+POPULATION_TAG_NAME);
			
		}
		city.setPopulation(i);

		// Obtenemos la url en BDPedia
		list = resourceUrl.getElementsByTagNameNS(DBPEDIA_URL_NS,DBPEDIA_URL_TAG_NAME);
		if(list.getLength()>0) {
			Element dbpediaUrl = (Element) list.item(0);
			String url = dbpediaUrl.getAttributeNS(DBPEDIA_URL_ATTRIBUTE_NS,DBPEDIA_URL_ATTRIBUTE_NAME);
			if(!url.isEmpty())
				city.setUrlDBpedia(url);
		}

		//Obtenemos la Url de Wikipedia
		list = resourceUrl.getElementsByTagNameNS(WIKIPEDIA_URL_NS,WIKIPEDIA_URL_TAG_NAME);
		if(list.getLength()>0) {
			Element wikipediaUrl = (Element) list.item(0);
			String url = wikipediaUrl.getAttributeNS(WIKIPEDIA_URL_ATTRIBUTE_NS, WIKIPEDIA_URL_ATTRIBUTE_NAME);
			if(!url.isEmpty())
				city.setUrlWikipedia(url);
		}
		
		//Obtenemos la fecha que fue modificado por última vez
		list = resourceUrl.getElementsByTagNameNS(UPDATED_DATE_NS,UPDATED_DATE_TAG_NAME);
		if(list.getLength()==0) {
			throw new CityServiceException(INVALID_ID+city.getId(), FIELD_NOT_FOUND+UPDATED_DATE_TAG_NAME);
			
		}
		Element date = (Element) list.item(0);
		try {
			city.setUpdatedDate(sdfDate.parse(date.getTextContent()));
		} catch (ParseException e) {
			throw new CityServiceException(INVALID_ID+city.getId(), FIELD_NOT_FOUND+UPDATED_DATE_TAG_NAME);
		}

		//Obtenemos la URI del recurso que contiene los lugares de interes
		list = resourceUrl.getElementsByTagNameNS(NEARBY_URL_NS, NEARBY_URL_TAG_NAME);
		if(list.getLength()==0) {
			throw new CityServiceException(INVALID_ID+city.getId(), FIELD_NOT_FOUND+NEARBY_URL_TAG_NAME);
		}
		Element nearBy = (Element) list.item(0);
		String uri =nearBy.getAttributeNS(NEARBY_URL_ATTRIBUTE_NS, NEARBY_URL_ATTRIBUTE_NAME);
		if(uri.isEmpty()) {
			throw new CityServiceException(INVALID_ID+city.getId(), FIELD_NOT_FOUND+NEARBY_URL_TAG_NAME);
		}
		return uri;
	}

	private static void parseNearBy(Document document) throws  CityServiceException {
		//ystem.out.println(document.getBaseURI());
		NodeList list = document.getElementsByTagNameNS(INTEREST_PLACE_NS, INTEREST_PLACE_TAG_NAME);
		if(list.getLength()==0) {
			throw new CityServiceException(INVALID_ID+city.getId(), FIELD_NOT_FOUND+INTEREST_PLACE_TAG_NAME);
		}
		List<InterestPlace> places = new ArrayList<InterestPlace>();
		int id=0;
		for (int i = 0; i < list.getLength(); i++) {
			Element interestPlace = (Element) list.item(i);
			NodeList lista = interestPlace.getElementsByTagNameNS(INTEREST_PLACE_NAME_NS,INTEREST_PLACE_NAME_TAG_NAME);
			if(lista.getLength()==0) {
				throw new CityServiceException(INVALID_ID+city.getId(), FIELD_NOT_FOUND+INTEREST_PLACE_TAG_NAME);
			}
			Element e = (Element) lista.item(0);
			String name = e.getTextContent();
			
			String url = interestPlace.getAttributeNS(INTEREST_PLACE_URL_NS,INTEREST_PLACE_URL_TAG_NAME);
			if(url.isEmpty()) {
				throw new CityServiceException(INVALID_ID+city.getId(), FIELD_NOT_FOUND+INTEREST_PLACE_TAG_NAME);
			}
			Pattern p = Pattern.compile("./([0-9]+)/");
			Matcher m = p.matcher(url);
			m.find();
			try {
				id = Integer.parseInt(m.group(1));
			} catch (IllegalStateException e2) {
				throw new CityServiceException(INVALID_ID+city.getId(), FIELD_NOT_FOUND+INTEREST_PLACE_TAG_NAME);
			}
			
			places.add(new InterestPlace(name, id));
		}
		city.addPointsOfInterest(places);

	}

	private static void parseWeather(Document document) throws CityServiceException {
		
		MeteoInfo meteo = new MeteoInfo();
		NodeList list = document.getElementsByTagName(TAKEN_ON_DATE_TAG_NAME);
		if(list.getLength()==0) {
			throw new CityServiceException(INVALID_ID+city.getId(), FIELD_NOT_FOUND+TAKEN_ON_DATE_TAG_NAME);
		}
		Element time = (Element) list.item(0);
		try {
			meteo.setTakenOn(sdfTime.parse(time.getTextContent()));
		} catch (ParseException e) {
			throw new CityServiceException(INVALID_ID+city.getId(), FIELD_NOT_FOUND+TAKEN_ON_DATE_TAG_NAME);
		}
		
		list = document.getElementsByTagName(STATION_NAME_TAG_NAME);
		if(list.getLength()==0) {
			throw new CityServiceException(INVALID_ID+city.getId(), FIELD_NOT_FOUND+STATION_NAME_TAG_NAME);
		}
		Element station = (Element) list.item(0);
		meteo.setStationName(station.getTextContent());

		list = document.getElementsByTagName(CLOUDS_TAG_NAME);
		if(list.getLength()==0) {
			throw new CityServiceException(INVALID_ID+city.getId(), FIELD_NOT_FOUND+CLOUDS_TAG_NAME);
		}
		Element clouds = (Element) list.item(0);
		meteo.setClouds(clouds.getTextContent());

		list = document.getElementsByTagName(TEMPERATURE_TAG_NAME);
		if(list.getLength()==0) {
			throw new CityServiceException(INVALID_ID+city.getId(), FIELD_NOT_FOUND+TEMPERATURE_TAG_NAME);
		}
		Element temperature = (Element) list.item(0);
		double d;
		try {
			d= Double.parseDouble(temperature.getTextContent());
		} catch (NumberFormatException e) {
			throw new CityServiceException(INVALID_ID+city.getId(), FIELD_NOT_FOUND+TEMPERATURE_TAG_NAME);
		}
		
		meteo.setTemperature(d);

		city.setMeteoInfo(meteo);
	}


	
	//Erros Messages
	private static final String INVALID_ID = "Invalid id: ";
	private static final String FIELD_NOT_FOUND = "Could not find field:";
	
	//NameSpaces
	private static final String GN_NAMESPACE = "http://www.geonames.org/ontology#";
	private static final String DCTERMS_NAMESPACE = "http://purl.org/dc/terms/";
	private static final String RDF_NAMESPACE = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
	private static final String RDFS_NAMESPACE = "http://www.w3.org/2000/01/rdf-schema#";
	
	//TagNames and NameSpaces
	private static final String POPULATION_NS = GN_NAMESPACE; 
	private static final String POPULATION_TAG_NAME = "population";
	private static final String DBPEDIA_URL_NS = RDFS_NAMESPACE;
	private static final String DBPEDIA_URL_TAG_NAME = "seeAlso";
	private static final String DBPEDIA_URL_ATTRIBUTE_NS = RDF_NAMESPACE;
	private static final String DBPEDIA_URL_ATTRIBUTE_NAME = "resource";
	private static final String WIKIPEDIA_URL_NS = GN_NAMESPACE;
	private static final String WIKIPEDIA_URL_TAG_NAME = "wikipediaArticle";
	private static final String WIKIPEDIA_URL_ATTRIBUTE_NS = RDF_NAMESPACE;
	private static final String WIKIPEDIA_URL_ATTRIBUTE_NAME = "resource";
	private static final String UPDATED_DATE_NS = DCTERMS_NAMESPACE;
	private static final String UPDATED_DATE_TAG_NAME = "modified";
	
	private static final String NEARBY_URL_NS = GN_NAMESPACE;
	private static final String NEARBY_URL_TAG_NAME = "nearbyFeatures";
	private static final String NEARBY_URL_ATTRIBUTE_NS = RDF_NAMESPACE;
	private static final String NEARBY_URL_ATTRIBUTE_NAME = "resource";
	private static final String INTEREST_PLACE_NS = GN_NAMESPACE;
	private static final String INTEREST_PLACE_TAG_NAME = "Feature";
	private static final String INTEREST_PLACE_URL_NS = RDF_NAMESPACE;
	private static final String INTEREST_PLACE_URL_TAG_NAME = "about";
	private static final String INTEREST_PLACE_NAME_NS = GN_NAMESPACE;
	private static final String INTEREST_PLACE_NAME_TAG_NAME = "name";
	
	private static final String TEMPERATURE_TAG_NAME = "temperature";
	private static final String CLOUDS_TAG_NAME = "clouds";
	private static final String STATION_NAME_TAG_NAME = "stationName";
	private static final String TAKEN_ON_DATE_TAG_NAME = "observationTime";
 
	//XML File NameSpaces
	private static final String SCHEMA_LOCATION = "http://www.example.org/Schema/ciudades Schema.xsd";
	private static final String XSI_NAMESPACE = "http://www.w3.org/2001/XMLSchema-instance";
	private static final String DEFAULT_NAMESPACE = "http://www.example.org/Schema";
	
	//XML File Tags 
	private static final String ID = "id";
	private static final String INTEREST_PLACE = "interestPlace";
	private static final String CLOUDS = "clouds";
	private static final String TEMPERATURE = "temperature";
	private static final String STATION_NAME = "stationName";
	private static final String TAKEN_ON = "takenOn";
	private static final String INFORMACION_METEOROLOGICA = "meteoInfo";
	private static final String URL_WIKIPEDIA = "urlWikipedia";
	private static final String URL_BDPEDIA = "urlBDpedia";
	private static final String LONGITUDE = "lng";
	private static final String LATITUDE = "lat";
	private static final String POSITION = "position";
	private static final String POPULATION = "population";
	private static final String COUNTRY = "country";
	private static final String GEONAMES_ID = "id";
	private static final String NAME = "name";
	private static final String UPDATEN_ON = "updatedOn";
	private static final String ELEMENT_CITY = "city";
	
}
