package ejercicio4;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Element;

import modelo.City;
import modelo.MeteoInfo;
import modelo.PointOfInterest;

public class Main {

	private static City city = new City("Cartagena", 2520058, "Spain", 37.60512, -0.98623);
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
	private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	public static void main(String[] args)
			throws ParserConfigurationException, SAXException, FileNotFoundException, XMLStreamException {
		// 1. Obtener una factoría
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
		// 2. Pedir a la factoría la construcción del analizador
		DocumentBuilder analizador = factoria.newDocumentBuilder();
		// 3. Analizar el documento
		city.setMeteoInfo(new MeteoInfo(new Date(), "stationName", 52.2, "Nubes"));
		PointOfInterest p = new PointOfInterest("hola", 52);
		List<PointOfInterest> lista = new ArrayList<PointOfInterest>();
		lista.add(p);
		city.addPointsOfInterest(lista);

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

		writeCity();

	}

	private static void writeCity() throws FileNotFoundException, XMLStreamException {
		XMLOutputFactory xof = XMLOutputFactory.newInstance();
		XMLStreamWriter writer = xof.createXMLStreamWriter(new FileOutputStream("xml/" + city.getId() + ".xml"));

		writer.writeStartDocument();
		writer.writeStartElement("city");
		writer.writeNamespace("", "http://www.example.org/Schema");
		writer.writeNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
		writer.writeAttribute("http://www.w3.org/2001/XMLSchema-instance", "schemaLocation",
				"http://www.example.org/Schema Schema.xsd ");
		writer.writeAttribute("updatedOn", sdf.format(city.getUpdatedDate()));
		// name
		writeElement(writer, "name", city.getName());
		// geonamesid
		writeElement(writer, "geonamesid", Integer.toString(city.getId()));
		// country
		writeElement(writer, "country", city.getCountry());
		// population
		writeElement(writer, "population", Integer.toString(city.getPopulation()));
		// position
		writer.writeStartElement("position");
		// lat
		writeElement(writer, "lat", Double.toString(city.getLatitude()));
		// lng
		writeElement(writer, "lng", Double.toString(city.getLongitude()));
		writer.writeEndElement();
		// urlBDpedia
		if (city.getUrlDBpedia() != null)
			writeElement(writer, "urlBDpedia", city.getUrlDBpedia());
		// urlWikipedia
		if (city.getUrlWikipedia() != null)
			writeElement(writer, "urlWikipedia", city.getUrlWikipedia());

		// meteoInfo
		MeteoInfo meteo = city.getMeteoInfo();
		if (meteo != null) {

			writer.writeStartElement("meteoInfo");
			writer.writeAttribute("takenOn", sdf1.format(meteo.getTakenOn()));
			// stationName
			writeElement(writer, "stationName", meteo.getStationName());
			// temperature
			writeElement(writer, "temperature", Double.toString(meteo.getTemperature()));
			// clouds
			writeElement(writer, "clouds", meteo.getClouds());
		}
		writer.writeEndElement();
		for (PointOfInterest place : city.getPointsOfInterest()) {
			writer.writeStartElement("interestPlace");
			writeElement(writer, "name", place.getName());
			writeElement(writer, "id", Integer.toString(place.getId()));
			writer.writeEndElement();
		}
		writer.writeEndElement();
		writer.writeEndDocument();
		writer.flush();
		writer.close();
	}

	public static void writeElement(XMLStreamWriter writer, String name, String value) throws XMLStreamException {
		writer.writeStartElement(name);
		writer.writeCharacters(value);
		writer.writeEndElement();
	}

	private static String parseResourceUrl(Document resourceUrl) throws SAXException {

		// <gn:population>211996</gn:population>
		NodeList list = resourceUrl.getElementsByTagName("gn:population");
		Element population = (Element) list.item(0);
		city.setPopulation(Integer.parseInt(population.getTextContent()));

		// <rdfs:seeAlso rdf:resource="http://dbpedia.org/resource/Cartagena%2C_Spain"/>
		list = resourceUrl.getElementsByTagName("rdfs:seeAlso");
		Element dbpediaUrl = (Element) list.item(0);
		if (dbpediaUrl != null)
			city.setUrlDBpedia(dbpediaUrl.getAttribute("rdf:resource"));

		// <gn:wikipediaArticle
		// rdf:resource="https://en.wikipedia.org/wiki/Cartagena%2C_Spain"/>
		list = resourceUrl.getElementsByTagName("gn:wikipediaArticle");
		Element wikipediaUrl = (Element) list.item(0);
		if (wikipediaUrl != null)
			city.setUrlWikipedia(wikipediaUrl.getAttribute("rdf:resource"));

		// <dcterms:modified
		// rdf:datatype="http://www.w3.org/2001/XMLSchema#date">2012-03-04</dcterms:modified>
		list = resourceUrl.getElementsByTagName("dcterms:modified");
		Element date = (Element) list.item(0);
		try {
			city.setUpdatedDate(sdf.parse(date.getTextContent()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// <gn:nearbyFeatures
		// rdf:resource="http://sws.geonames.org/2520058/nearby.rdf"/>
		list = resourceUrl.getElementsByTagName("gn:nearbyFeatures");
		Element nearBy = (Element) list.item(0);
		return nearBy.getAttribute("rdf:resource");
	}

	private static void parseNearBy(Document document) throws SAXException {
		NodeList list = document.getElementsByTagName("gn:Feature");
		List<PointOfInterest> places = new ArrayList<PointOfInterest>();
		for (int i = 0; i < list.getLength(); i++) {
			Element interestPlace = (Element) list.item(i);
			Element e = (Element) interestPlace.getElementsByTagName("gn:name").item(0);
			String name = e.getTextContent();
			int id = 1;

			String url = interestPlace.getAttribute("rdf:about");
			Pattern p = Pattern.compile("./([0-9]+)/");
			Matcher m = p.matcher(url);
			m.find();
			id = Integer.parseInt(m.group(1)); // is your string. do what you want

			places.add(new PointOfInterest(name, id));
		}
		city.addPointsOfInterest(places);

	}

	private static void parseWeather(Document document) throws SAXException {
		// <gn:population>211996</gn:population>
		MeteoInfo meteo = new MeteoInfo();
		NodeList list = document.getElementsByTagName("observationTime");
		Element time = (Element) list.item(0);
		try {
			meteo.setTakenOn(sdf2.parse(time.getTextContent()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		list = document.getElementsByTagName("stationName");
		Element station = (Element) list.item(0);
		meteo.setStationName(station.getTextContent());

		list = document.getElementsByTagName("clouds");
		Element clouds = (Element) list.item(0);
		meteo.setClouds(clouds.getTextContent());

		list = document.getElementsByTagName("temperature");
		Element temperature = (Element) list.item(0);
		meteo.setTemperature(Double.parseDouble(temperature.getTextContent()));

		city.setMeteoInfo(meteo);
	}

}
