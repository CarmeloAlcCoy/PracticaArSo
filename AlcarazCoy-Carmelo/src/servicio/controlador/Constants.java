package servicio.controlador;

import java.text.SimpleDateFormat;

public class Constants {
	
	//RDF Tag Names
	public static final String NAME_TAG_NAME = "gn:name";
	public static final String LNG_TAG_NAME = "wgs84_pos:long";
	public static final String LAT_TAG_NAME = "wgs84_pos:lat";
	public static final String INTEREST_PLACE_URL_TAG_NAME = "rdf:about";
	public static final String INTEREST_PLACE_NAME_TAG_NAME = NAME_TAG_NAME;
	public static final String INTEREST_PLACE_TAG_NAME = "gn:Feature";
	public static final String TEMPERATURE_TAG_NAME = "temperature";
	public static final String CLOUDS_TAG_NAME = "clouds";
	public static final String STATION_NAME_TAG_NAME = "stationName";;
	public static final String TAKEN_ON_DATE_TAG_NAME = "observationTime";
	public static final String POPULATION_TAG_NAME = "gn:population";
	public static final String DBPEDIA_URL_TAG_NAME = "rdfs:seeAlso";
	public static final String DBPEDIA_URL_ATTRIBUTE_NAME = "rdf:resource";
	public static final String WIKIPEDIA_URL_TAG_NAME = "gn:wikipediaArticle";
	public static final String WIKIPEDIA_URL_ATTRIBUTE_NAME = "rdf:resource";
	public static final String UPDATED_DATE_TAG_NAME = "dcterms:modified";
	public static final String NEARBY_URL_TAG_NAME = "gn:nearbyFeatures";
	public static final String NEARBY_URL_ATTRIBUTE_NAME = "rdf:resource";
	
	//Schema tags
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
	public static final String GEONAMES_ID = "id"; //geonamesid
	public static final String NAME = "name";
	public static final String UPDATEN_ON = "updatedOn";
	public static final String SCHEMA_LOCATION = "http://www.example.org/Schema Schema.xsd ";
	public static final String XSI_NAMESPACE = "http://www.w3.org/2001/XMLSchema-instance";
	public static final String DEFAULT_NAMESPACE = "http://www.example.org/Schema";
	public static final String ELEMENT_CITY = "city";

	//Errors
	public static final String PARSE_DATE_ERROR = "Could not parse Date in field:";
	public static final String FIELD_NOT_FOUND = "Field not found:";

	//Parsers
	public static final SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat sdfTimeXMLSchema = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
	public static final SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	//Search Tag
	public static final String SEARCH_TAG_NAME="name";
	public static final String SEARCH_TAG_ID="geonameId";
	public static final String SEARCH_TAG_COUNTRY="countryName";
	public static final String SEARCH_TAG_LATITUDE="lat";
	public static final String SEARCH_TAG_LONGITUDE="lng";
	
	//Rutas
	public static final String RUTA_BD = "xml-bd/";

}
