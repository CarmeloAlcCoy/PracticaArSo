package servicio.controlador;

import java.text.SimpleDateFormat;

public class Constants {

	public static final String CONSULTA_GEONAMES = "http://api.geonames.org/search?featureClass=P&username=arso&name=";
	public static final String CONSULTA_GEONAMES_COUNTRY="http://api.geonames.org/countryInfo?username=arso&country=";
	public static final String URI_GOOGLE_BOOKS = "http://books.google.com/books/feeds/volumes?";
	public static final String URI_GEONAMES_WEATHER = "http://api.geonames.org/findNearByWeather?";
	

	// Erros Messages
	public static final String INVALID_ID = "Invalid id: ";
	public static final String FIELD_NOT_FOUND = "Could not find field:";
	public static final String INTERNAL_ERROR = "Internal Server Exception";

	// NameSpaces
	public static final String GN_NAMESPACE = "http://www.geonames.org/ontology#";
	public static final String DCTERMS_NAMESPACE = "http://purl.org/dc/terms/";
	public static final String RDF_NAMESPACE = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
	public static final String RDFS_NAMESPACE = "http://www.w3.org/2000/01/rdf-schema#";
	public static final String WGS84_POS_NAMESPACE ="http://www.w3.org/2003/01/geo/wgs84_pos#";

	// TagNames and NameSpaces
	public static final String NAME_NS = GN_NAMESPACE;
	public static final String NAME_TAG_NAME = "name";
	public static final String LNG_NS = WGS84_POS_NAMESPACE;
	public static final String LNG_TAG_NAME = "long";
	public static final String LAT_NS = WGS84_POS_NAMESPACE;
	public static final String LAT_TAG_NAME = "lat";
	public static final String COUNTRY_CODE_NS = GN_NAMESPACE;
	public static final String COUNTRY_CODE_TAG_NAME = "countryCode";
	public static final String POPULATION_NS = GN_NAMESPACE;
	public static final String POPULATION_TAG_NAME = "population";
	public static final String DBPEDIA_URL_NS = RDFS_NAMESPACE;
	public static final String DBPEDIA_URL_TAG_NAME = "seeAlso";
	public static final String DBPEDIA_URL_ATTRIBUTE_NS = RDF_NAMESPACE;
	public static final String DBPEDIA_URL_ATTRIBUTE_NAME = "resource";
	public static final String WIKIPEDIA_URL_NS = GN_NAMESPACE;
	public static final String WIKIPEDIA_URL_TAG_NAME = "wikipediaArticle";
	public static final String WIKIPEDIA_URL_ATTRIBUTE_NS = RDF_NAMESPACE;
	public static final String WIKIPEDIA_URL_ATTRIBUTE_NAME = "resource";
	public static final String UPDATED_DATE_NS = DCTERMS_NAMESPACE;
	public static final String UPDATED_DATE_TAG_NAME = "modified";

	public static final String NEARBY_URL_NS = GN_NAMESPACE;
	public static final String NEARBY_URL_TAG_NAME = "nearbyFeatures";
	public static final String NEARBY_URL_ATTRIBUTE_NS = RDF_NAMESPACE;
	public static final String NEARBY_URL_ATTRIBUTE_NAME = "resource";
	public static final String INTEREST_PLACE_NS = GN_NAMESPACE;
	public static final String INTEREST_PLACE_TAG_NAME = "Feature";
	public static final String INTEREST_PLACE_URL_NS = RDF_NAMESPACE;
	public static final String INTEREST_PLACE_URL_TAG_NAME = "about";
	public static final String INTEREST_PLACE_NAME_NS = GN_NAMESPACE;
	public static final String INTEREST_PLACE_NAME_TAG_NAME = "name";

	public static final String TEMPERATURE_TAG_NAME = "temperature";
	public static final String CLOUDS_TAG_NAME = "clouds";
	public static final String STATION_NAME_TAG_NAME = "stationName";
	public static final String TAKEN_ON_DATE_TAG_NAME = "observationTime";

	public static final String COUNTRY_NAME_TAG_NAME = "countryName";
	
	// XML File NameSpaces
	public static final String SCHEMA_LOCATION = "http://www.example.org/Schema/ciudades Schema.xsd";
	public static final String XSI_NAMESPACE = "http://www.w3.org/2001/XMLSchema-instance";
	public static final String DEFAULT_NAMESPACE = "http://www.example.org/Schema";

	// XML File Tags
	public static final String ID = "id";
	public static final String INTEREST_PLACE = "interestPlace";
	public static final String CLOUDS = "clouds";
	public static final String TEMPERATURE = "temperature";
	public static final String STATION_NAME = "stationName";
	public static final String TAKEN_ON = "takenOn";
	public static final String INFORMACION_METEOROLOGICA = "meteoInfo";
	public static final String URL_WIKIPEDIA = "urlWikipedia";
	public static final String URL_BDPEDIA = "urlBDpedia";
	public static final String LONGITUDE = "lng";
	public static final String LATITUDE = "lat";
	public static final String POSITION = "position";
	public static final String POPULATION = "population";
	public static final String COUNTRY = "country";
	public static final String GEONAMES_ID = "id";
	public static final String NAME = "name";
	public static final String UPDATEN_ON = "updatedOn";
	public static final String ELEMENT_CITY = "city";
	public static final String BOOK = "book";
	public static final String ULR_GOOGLE_BOOKS = "ulrGoogleBooks";
	public static final String IMG = "img";
	public static final String ISBN = "isbn";
	public static final String BOOK_ID = "id";
	public static final String TITLE = "title";

	// Parsers
	public static final SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat sdfTimeXMLSchema = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
	public static final SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	// Search Tag
	public static final String SEARCH_TAG_NAME = "name";
	public static final String SEARCH_TAG_ID = "geonameId";
	public static final String SEARCH_TAG_COUNTRY = "countryName";
	public static final String SEARCH_TAG_LATITUDE = "lat";
	public static final String SEARCH_TAG_LONGITUDE = "lng";

	// Rutas
	public static final String RUTA_BD = "xml-bd/";


}


