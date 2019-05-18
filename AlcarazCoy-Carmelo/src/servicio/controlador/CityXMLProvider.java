package servicio.controlador;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javanet.staxutils.IndentingXMLStreamWriter;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import modelo.Book;
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

	public void recuperarDocumento(String idGeoNames) throws CityServiceException {

		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
		factoria.setNamespaceAware(true);
		DocumentBuilder analizador;
		try {
			analizador = factoria.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new CityServiceException(INTERNAL_ERROR, "JAXBUnMarshaller"+e.toString());
		}

		int id;
		try {
			id = Integer.parseInt(idGeoNames);
		} catch (NumberFormatException e) {
			throw new CityServiceException(INVALID_ID+idGeoNames,"Not an Integer");
		}

		city = new City();
		city.setId(id);

		String uri = "http://sws.geonames.org/" + id + "/about.rdf";
		
		Document resource;
		try {
			resource = analizador.parse(uri);
			
		} catch (IOException e) {
			throw new CityServiceException(INTERNAL_ERROR, "Unknown\n" + e.toString());
		} catch (SAXException e) {
			throw new CityServiceException(INTERNAL_ERROR, "XML invalid File\n" + e.toString());
		}

		//Analizamos el RDF y devolveos la URI donde está el RDF con los sitios de interés
		String interestPlacesUrl = parseRDF(resource);
		
		String param;
		try {
			param = URLEncoder.encode( countryCode, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new CityServiceException(INTERNAL_ERROR,"Encoder\n"+e.toString());
		}
		
		Document resourceCountry;
		try {
			resourceCountry = analizador.parse(CONSULTA_GEONAMES_COUNTRY +param);
		} catch (IOException e) {
			throw new CityServiceException(INTERNAL_ERROR, "Unknown:" + e.toString());
		} catch (SAXException e) {
			throw new CityServiceException(INTERNAL_ERROR, "XML invalid File" + e.toString());
		}

		parseCountry(resourceCountry);

		Document interestPlacesResource;
		try {
			interestPlacesResource = analizador.parse(interestPlacesUrl);

		} catch (IOException e) {
			throw new CityServiceException(INTERNAL_ERROR, "Unknown:" + e.toString());
		} catch (SAXException e) {
			throw new CityServiceException(INTERNAL_ERROR, "XML invalid File" + e.toString());
		}

		parseNearBy(interestPlacesResource);

		Document weatherResource;
		try {
			uri = URI_GEONAMES_WEATHER+"lat="+URLEncoder.encode( Double.toHexString(city.getLatitude()),"UTF-8") 
			+ "&lng="+URLEncoder.encode( Double.toHexString(city.getLongitude()),"UTF-8")
				+"&username=arso";
		} catch (UnsupportedEncodingException e) {
			throw new CityServiceException(INTERNAL_ERROR,"Encoder\n"+e.toString());
		}
		try {
			weatherResource = analizador.parse(uri);

		} catch (IOException e) {
			throw new CityServiceException(INTERNAL_ERROR, "Unknown:" + e.toString());
		} catch (SAXException e) {
			throw new CityServiceException(INTERNAL_ERROR, "XML invalid File" + e.toString());
		}
		parseWeather(weatherResource);

		Document bookResource;

		uri =URI_GOOGLE_BOOKS+"q=" + city.getName() + "&start-index=1";
		try {
			bookResource = analizador.parse(uri);

		} catch (IOException e) {
			throw new CityServiceException(INTERNAL_ERROR, "Unknown:" + e.toString());
		} catch (SAXException e) {
			throw new CityServiceException(INTERNAL_ERROR, "XML invalid File" + e.toString());
		}

		parseBook(bookResource);

		try {
			writeCity();
		} catch (IOException e) {
			throw new CityServiceException(INTERNAL_ERROR, "Could not write on the resource:" + RUTA_BD + city.getId() + ".xml\n"
					+ e.getMessage());

		} catch (XMLStreamException e) {
			throw new CityServiceException(INTERNAL_ERROR, "XMLStreamException\n"+ e.toString());
		}
	}

	private String parseRDF(Document resourceUrl) throws CityServiceException {

		// Obtenemos el nombre de la ciudad
		NodeList list = resourceUrl.getElementsByTagNameNS(NAME_NS, NAME_TAG_NAME);
		if (list.getLength() == 0) {
			throw new CityServiceException(INVALID_ID+city.getId(), FIELD_NOT_FOUND+NAME);
		}
		Element name = (Element) list.item(0);
		city.setName(name.getTextContent());

		// CountryCode
		list = resourceUrl.getElementsByTagNameNS(COUNTRY_CODE_NS, COUNTRY_CODE_TAG_NAME);
		if (list.getLength() == 0) {
			throw new CityServiceException(INVALID_ID+city.getId(), FIELD_NOT_FOUND+COUNTRY);
		}
		Element country = (Element) list.item(0);
		countryCode = country.getTextContent();

		// Obtenemos la latitud
		list = resourceUrl.getElementsByTagNameNS(LAT_NS, LAT_TAG_NAME);
		if (list.getLength() == 0) {
			throw new CityServiceException(INVALID_ID+city.getId(), FIELD_NOT_FOUND+LATITUDE);
		}
		Element latitude = (Element) list.item(0);
		double d;
		try {
			d = Double.parseDouble(latitude.getTextContent());
		} catch (NumberFormatException e) {
			throw new CityServiceException(INVALID_ID+city.getId(), FIELD_NOT_FOUND+LATITUDE);
		}
		city.setLatitude(d);

		// Obtenemos la latitud
		list = resourceUrl.getElementsByTagNameNS(LNG_NS, LNG_TAG_NAME);
		if (list.getLength() == 0) {
			throw new CityServiceException(INVALID_ID+city.getId(), FIELD_NOT_FOUND+LONGITUDE);
		}
		Element longitude = (Element) list.item(0);

		try {
			d = Double.parseDouble(longitude.getTextContent());
		} catch (NumberFormatException e) {
			throw new CityServiceException(INVALID_ID+city.getId(), FIELD_NOT_FOUND+LONGITUDE);
		}
		city.setLongitude(d);

		// Obtenemos la población del lugar
		list = resourceUrl.getElementsByTagNameNS(POPULATION_NS, POPULATION_TAG_NAME);
		if (list.getLength() == 0) {
			throw new CityServiceException(INVALID_ID+city.getId(), FIELD_NOT_FOUND+POPULATION);
		}
		Element population = (Element) list.item(0);
		int i;
		try {
			i = Integer.parseInt(population.getTextContent());
		} catch (NumberFormatException e) {
			throw new CityServiceException(INVALID_ID+city.getId(), FIELD_NOT_FOUND+POPULATION);
		}
		city.setPopulation(i);

		// Obtenemos la url en BDPedia
		list = resourceUrl.getElementsByTagNameNS(DBPEDIA_URL_NS, DBPEDIA_URL_TAG_NAME);
		if (list.getLength() > 0) {
			Element dbpediaUrl = (Element) list.item(0);
			String url = dbpediaUrl.getAttributeNS(DBPEDIA_URL_ATTRIBUTE_NS, DBPEDIA_URL_ATTRIBUTE_NAME);
			if (!url.isEmpty())
				city.setUrlDBpedia(url);
		}

		// Obtenemos la Url de Wikipedia
		list = resourceUrl.getElementsByTagNameNS(WIKIPEDIA_URL_NS, WIKIPEDIA_URL_TAG_NAME);
		if (list.getLength() > 0) {
			Element wikipediaUrl = (Element) list.item(0);
			String url = wikipediaUrl.getAttributeNS(WIKIPEDIA_URL_ATTRIBUTE_NS, WIKIPEDIA_URL_ATTRIBUTE_NAME);
			if (!url.isEmpty())
				city.setUrlWikipedia(url);
		}

		// Obtenemos la fecha que fue modificado por última vez
		list = resourceUrl.getElementsByTagNameNS(UPDATED_DATE_NS, UPDATED_DATE_TAG_NAME);
		if (list.getLength() == 0) {
			throw new CityServiceException(INVALID_ID+city.getId(), FIELD_NOT_FOUND+UPDATEN_ON);
		}
		Element date = (Element) list.item(0);
		try {
			city.setUpdatedDate(sdfDate.parse(date.getTextContent()));
		} catch (ParseException e) {
			throw new CityServiceException(INVALID_ID+city.getId(), FIELD_NOT_FOUND+UPDATEN_ON);
		}

		// Obtenemos la URI del recurso que contiene los lugares de interes
		list = resourceUrl.getElementsByTagNameNS(NEARBY_URL_NS, NEARBY_URL_TAG_NAME);
		if (list.getLength() == 0) {
			throw new CityServiceException(INVALID_ID+city.getId(), FIELD_NOT_FOUND+ INTEREST_PLACE);
		}
		Element nearBy = (Element) list.item(0);
		String uri = nearBy.getAttributeNS(NEARBY_URL_ATTRIBUTE_NS, NEARBY_URL_ATTRIBUTE_NAME);
		if (uri.isEmpty()) {
			throw new CityServiceException(INVALID_ID+city.getId(), FIELD_NOT_FOUND+ INTEREST_PLACE);
		}
		return uri;
	}

	private void parseCountry(Document resourceCountry) throws CityServiceException {
		// Obtenemos el nombre de la ciudad
		NodeList list = resourceCountry.getElementsByTagName(COUNTRY_NAME_TAG_NAME);
		if (list.getLength() == 0) {
			throw new CityServiceException(INVALID_ID+city.getId(), FIELD_NOT_FOUND+ COUNTRY);
		}
		Element country = (Element) list.item(0);
		city.setCountry(country.getTextContent());

	}

	private void parseNearBy(Document document) throws CityServiceException {
		// System.out.println(document.getBaseURI());
		NodeList list = document.getElementsByTagNameNS(INTEREST_PLACE_NS, INTEREST_PLACE_TAG_NAME);
		if (list.getLength() == 0) {
			throw new CityServiceException(INVALID_ID+city.getId(), FIELD_NOT_FOUND+ INTEREST_PLACE);
		}
		List<InterestPlace> places = new ArrayList<InterestPlace>();
		int id = 0;
		for (int i = 0; i < list.getLength(); i++) {
			Element interestPlace = (Element) list.item(i);
			NodeList lista = interestPlace.getElementsByTagNameNS(INTEREST_PLACE_NAME_NS, INTEREST_PLACE_NAME_TAG_NAME);
			if (lista.getLength() == 0) {
				throw new CityServiceException(INVALID_ID+city.getId(), FIELD_NOT_FOUND+ INTEREST_PLACE);
			}
			Element e = (Element) lista.item(0);
			String name = e.getTextContent();

			String url = interestPlace.getAttributeNS(INTEREST_PLACE_URL_NS, INTEREST_PLACE_URL_TAG_NAME);
			if (url.isEmpty()) {
				throw new CityServiceException(INVALID_ID+city.getId(), FIELD_NOT_FOUND+ INTEREST_PLACE);
			}
			Pattern p = Pattern.compile("./([0-9]+)/");
			Matcher m = p.matcher(url);
			m.find();
			try {
				id = Integer.parseInt(m.group(1));
			} catch (IllegalStateException e1) {
				throw new CityServiceException(INVALID_ID+city.getId(), FIELD_NOT_FOUND+ INTEREST_PLACE);
			}

			places.add(new InterestPlace(name, id));
		}
		city.addPointsOfInterest(places);

	}

	private void parseWeather(Document document) throws CityServiceException {

		MeteoInfo meteo = new MeteoInfo();
		NodeList list = document.getElementsByTagName(TAKEN_ON_DATE_TAG_NAME);
		if (list.getLength() == 0) {
			throw new CityServiceException(INVALID_ID+city.getId(), FIELD_NOT_FOUND+ TAKEN_ON);
		}
		Element time = (Element) list.item(0);
		try {
			meteo.setTakenOn(sdfTime.parse(time.getTextContent()));
		} catch (ParseException e) {
			throw new CityServiceException(INVALID_ID+city.getId(), FIELD_NOT_FOUND+ TAKEN_ON);
		}

		list = document.getElementsByTagName(STATION_NAME_TAG_NAME);
		if (list.getLength() == 0) {
			throw new CityServiceException(INVALID_ID+city.getId(), FIELD_NOT_FOUND+ STATION_NAME);
		}
		Element station = (Element) list.item(0);
		meteo.setStationName(station.getTextContent());

		list = document.getElementsByTagName(CLOUDS_TAG_NAME);
		if (list.getLength() == 0) {
			throw new CityServiceException(INVALID_ID+city.getId(), FIELD_NOT_FOUND+ CLOUDS);
		}
		Element clouds = (Element) list.item(0);
		meteo.setClouds(clouds.getTextContent());

		list = document.getElementsByTagName(TEMPERATURE_TAG_NAME);
		if (list.getLength() == 0) {
			throw new CityServiceException(INVALID_ID+city.getId(), FIELD_NOT_FOUND+ TEMPERATURE);
		}
		Element temperature = (Element) list.item(0);
		double d;
		try {
			d = Double.parseDouble(temperature.getTextContent());
		} catch (NumberFormatException e) {
			throw new CityServiceException(INVALID_ID+city.getId(), FIELD_NOT_FOUND+ TEMPERATURE);
		}
		meteo.setTemperature(d);

		city.setMeteoInfo(meteo);
	}

	private void parseBook(Document document) throws CityServiceException {

		XPathFactory factoria = XPathFactory.newInstance();
		XPath xpath = factoria.newXPath();
		xpath.setNamespaceContext(new EspaciosNombres());

		NodeList list;

		try {

			XPathExpression consulta = xpath.compile("//a:entry[contains(dc:subject,'" + city.getName() + "') "
					+ "and contains(dc:subject, '" + city.getCountry() + "') ]");
			XPathExpression consultaTitulo = xpath.compile("a:title");
			XPathExpression consultaId = xpath.compile("a:id");
			XPathExpression consultaISBN = xpath.compile("dc:identifier[contains(., 'ISBN') ][1]");
			XPathExpression consultaImg = xpath.compile("a:link[contains(@type, 'image')]/@href[1]");
			XPathExpression consultaUrl = xpath.compile("a:link[@type ='text/html']/@href[1]");
			list = (NodeList) consulta.evaluate(document, XPathConstants.NODESET);
			
			Book libro = new Book();
			for (int i = 0; i < list.getLength(); i++) {
				libro = new Book();
				Element book = (Element) list.item(i);

				Node title;
				title = (Node) consultaTitulo.evaluate(book, XPathConstants.NODE);
				if (title == null)
					throw new CityServiceException(INVALID_ID+city.getId(), FIELD_NOT_FOUND+ BOOK);
				libro.setTitle(title.getTextContent());

				Node id;
				id = (Node) consultaId.evaluate(book, XPathConstants.NODE);
				if (id == null)
					throw new CityServiceException(INVALID_ID+city.getId(), FIELD_NOT_FOUND+ BOOK);

				libro.setId(id.getTextContent());

				Node isbn;
				isbn = (Node) consultaISBN.evaluate(book, XPathConstants.NODE);
				if (isbn != null)
					libro.setIsbn(isbn.getTextContent());

				Node img;
				img = (Node) consultaImg.evaluate(book, XPathConstants.NODE);
				if (img == null)
					throw new CityServiceException(INVALID_ID+city.getId(), FIELD_NOT_FOUND+ BOOK);
				libro.setImg(img.getTextContent());

				Node url;
				url = (Node) consultaUrl.evaluate(book, XPathConstants.NODE);
				if (url == null)
					throw new CityServiceException(INVALID_ID+city.getId(), FIELD_NOT_FOUND+ BOOK);
				libro.setUrlGoogleBooks(url.getTextContent());

				city.addBook(libro);
			}

		} catch (XPathExpressionException e) {
			throw new CityServiceException(INTERNAL_ERROR, "XPATH" + e.toString());

		}

	}

	private void writeCity() throws FileNotFoundException, XMLStreamException {
		XMLOutputFactory xof = XMLOutputFactory.newInstance();
		XMLStreamWriter writer = xof.createXMLStreamWriter(new FileOutputStream(RUTA_BD + city.getId() + ".xml"),
				"UTF-8");
		writer = new IndentingXMLStreamWriter(writer); // Duda 1
		writer.writeStartDocument("UTF-8", "1.0"); // Solved
		writer.writeStartElement(ELEMENT_CITY);
		writer.writeNamespace("", DEFAULT_NAMESPACE);
		writer.writeNamespace("xsi", XSI_NAMESPACE);
		writer.writeAttribute(XSI_NAMESPACE, "schemaLocation", SCHEMA_LOCATION);

		writer.writeAttribute(DEFAULT_NAMESPACE,GEONAMES_ID, String.valueOf(city.getId()));
		writer.writeAttribute(DEFAULT_NAMESPACE,UPDATEN_ON, sdfDate.format(city.getUpdatedDate()));
		// name
		writeElement(writer, NAME, city.getName());
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
			writer.writeStartElement(DEFAULT_NAMESPACE,INFORMACION_METEOROLOGICA);
			writer.writeAttribute(DEFAULT_NAMESPACE,TAKEN_ON, sdfTimeXMLSchema.format(meteo.getTakenOn()));
			// stationName
			writeElement(writer, STATION_NAME, meteo.getStationName());
			// temperature
			writeElement(writer, TEMPERATURE, Double.toString(meteo.getTemperature()));
			// clouds
			writeElement(writer, CLOUDS, meteo.getClouds());
		}
		writer.writeEndElement();
		//Interest Places
		for (InterestPlace place : city.getPointsOfInterest()) {
			writer.writeStartElement(DEFAULT_NAMESPACE,INTEREST_PLACE);
			writeElement(writer, NAME, place.getName());
			writeElement(writer, ID, Integer.toString(place.getId()));
			writer.writeEndElement();
		}

		//Books
		for (Book book : city.getBooks()) {
			writer.writeStartElement(DEFAULT_NAMESPACE, BOOK);
			writeElement(writer, TITLE, book.getTitle());
			writeElement(writer, BOOK_ID, book.getId());
			if (book.getIsbn() != null)
				writeElement(writer, ISBN, book.getIsbn());
			writeElement(writer, IMG, book.getImg());
			writeElement(writer, ULR_GOOGLE_BOOKS, book.getUrlGoogleBooks());

			writer.writeEndElement();
		}

		writer.writeEndElement();
		writer.writeEndDocument();
		writer.flush();
		writer.close();
	}

	private void writeElement(XMLStreamWriter writer, String name, String value) throws XMLStreamException {
		writer.writeStartElement(DEFAULT_NAMESPACE, name);
		writer.writeCharacters(value);
		writer.writeEndElement();
	}

}
