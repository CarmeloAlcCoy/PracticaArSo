package servicio.controlador;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import static servicio.controlador.Constants.RUTA_BD;
import static servicio.controlador.Constants.CONSULTA_GEONAMES;
import static servicio.controlador.Constants.INTERNAL_ERROR;
import static servicio.controlador.Constants.INVALID_ID;

import servicio.clases.CiudadResultado;
import servicio.clases.CiudadResultadoJSON;
import servicio.clases.CiudadesFavoritas;
import servicio.clases.Entry;
import servicio.clases.Link;
import servicio.clases.ListadoCiudades;
import servicio.clases.ListadoCiudadesAtom;
import servicio.clases.ListadoCiudadesJSON;
import servicio.clases.ListadoLinks;
import servicio.tipos.City;

public class ServicioGeoNames {

	private static ServicioGeoNames unicaInstancia;
	private SAXParserFactory factoriaSAX;
	private JAXBContext contexto;
	private JAXBContext contextoFavorito;

	static {
		File folder = new File("xml-bd");
		if (!folder.exists())
			folder.mkdir();
	}

	private ServicioGeoNames() throws CityServiceException {
		// Construimos el analizador SAX
		factoriaSAX = SAXParserFactory.newInstance();
		try {
			contextoFavorito = JAXBContext.newInstance(CiudadesFavoritas.class);
			contexto = JAXBContext.newInstance("servicio.tipos");
		} catch (JAXBException e) {
			throw new CityServiceException(INTERNAL_ERROR, "JAXBFactory" + e.toString());
		}

	}

	public static ServicioGeoNames getUnicaInstancia() throws CityServiceException {
		if (unicaInstancia == null)
			unicaInstancia = new ServicioGeoNames();
		return unicaInstancia;
	}

	private Marshaller createMarshaller(JAXBContext con) throws CityServiceException {
		Marshaller marshaller;

		try {
			marshaller = con.createMarshaller();

			marshaller.setProperty("jaxb.formatted.output", true);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

			if (con.equals(contexto))
				marshaller.setProperty("jaxb.schemaLocation", "http://www.example.org/schema Schema.xsd ");
		} catch (JAXBException e) {
			throw new CityServiceException(INTERNAL_ERROR, "Marshaller" + e.toString());
		}
		return marshaller;
	}

	private Unmarshaller createUnmarshaller(JAXBContext con) throws CityServiceException {
		try {
			return con.createUnmarshaller();
		} catch (JAXBException e) {
			throw new CityServiceException(INTERNAL_ERROR, "Unmarshaller" + e.toString());
		}
	}

	private void marshallCiudadFavorita(CiudadesFavoritas ciudades, File file) throws CityServiceException {
		Marshaller marshaller = createMarshaller(contextoFavorito);
		try {
			marshaller.marshal(ciudades, file);
		} catch (JAXBException e) {
			throw new CityServiceException(INTERNAL_ERROR, "Marshall" + e.toString());
		}
	}

	private CiudadesFavoritas unmarshallCiudadFavorita(File file) throws CityServiceException {

		CiudadesFavoritas ciudades;
		Unmarshaller unmarshaller = createUnmarshaller(contextoFavorito);

		try {
			ciudades = (CiudadesFavoritas) unmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			throw new CityServiceException(INTERNAL_ERROR, "UnMarshall" + e.toString());
		}
		return ciudades;
	}

	public List<CiudadResultado> buscar(String busqueda) throws CityServiceException {
		Manejador manejador = new Manejador();

		try {
			SAXParser analizadorSAX = factoriaSAX.newSAXParser();
			String uri = CONSULTA_GEONAMES + URLEncoder.encode(busqueda, "UTF-8");
			analizadorSAX.parse(uri, manejador);

		} catch (IOException e) {
			throw new CityServiceException(INTERNAL_ERROR,
					"Could not open" + CONSULTA_GEONAMES + busqueda + "\n" + e.toString());
		} catch (SAXException e) {
			throw new CityServiceException(INTERNAL_ERROR, "XML invalid File\n" + e.toString());
		} catch (ParserConfigurationException e) {
			throw new CityServiceException(INTERNAL_ERROR, "ParserConfiguration\n" + e.toString());
		}
		return manejador.getCiudades();
	}

	public ListadoCiudades getResultadosBusquedaXML(String busqueda) throws CityServiceException {

		ListadoCiudades lc = new ListadoCiudades();
		lc.setResultados(buscar(busqueda));
		return lc;

	}

	public City getCiudad(String idGeoNames) throws CityServiceException {

		File file = recuperarDocumento(idGeoNames);

		Unmarshaller unmarshaller;
		City city;
		try {
			unmarshaller = createUnmarshaller(contexto);
			city = (City) unmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			throw new CityServiceException(INTERNAL_ERROR, "JAXBUnMarshaller\n" + e.toString());
		}

		return city;

	}

	private File recuperarDocumento(String idGeoNames) throws CityServiceException {
		File file = new File(Constants.RUTA_BD + idGeoNames + ".xml");

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR, -1);
		Date haceUnaHora = calendar.getTime();
		Date lastModified = new Date(file.lastModified());

		if (!(file.exists() && lastModified.after(haceUnaHora))) {
			CityXMLProvider xml = new CityXMLProvider();
			xml.recuperarDocumento(idGeoNames);
		}

		return file;

	}

	public String crearDocumentoFavoritos() throws CityServiceException {
		String id = UUID.randomUUID().toString();

		File f = new File(Constants.RUTA_BD + "favoritos-" + id + ".xml");
		if (!f.exists())
			try {
				CiudadesFavoritas ciudades = new CiudadesFavoritas(id);
				marshallCiudadFavorita(ciudades, f);
				f.createNewFile();
			} catch (IOException e) {
				throw new CityServiceException(INTERNAL_ERROR,
						"Could not open" + Constants.RUTA_BD + "favoritos-" + id + ".xml\n" + e.toString());
			}

		return id;
	}

	public void addCiudadFavorita(String idFavoritos, String idGeoNames) throws CityServiceException {
		File file = new File(RUTA_BD + "favoritos-" + idFavoritos + ".xml");
		if (!file.exists())
			throw new CityServiceException(INVALID_ID + idFavoritos, "Not found");

		CiudadesFavoritas ciudades;
		ciudades = unmarshallCiudadFavorita(file);
		if (!ciudades.getCiudadesFavoritas().contains(idGeoNames)) {
			ciudades.addCiudadFavorita(idGeoNames);
			marshallCiudadFavorita(ciudades, file);
		}

	}

	public boolean removeCiudadFavorita(String idFavoritos, String idGeoNames) throws CityServiceException {
		File file = new File(RUTA_BD + "favoritos-" + idFavoritos + ".xml");
		if (!file.exists())
			return false;

		CiudadesFavoritas ciudades = unmarshallCiudadFavorita(file);

		boolean removed = ciudades.removeCiudadFavorita(idGeoNames);

		marshallCiudadFavorita(ciudades, file);

		return removed;
	}

	public CiudadesFavoritas getFavoritos(String idFavoritos) throws CityServiceException {

		File file = new File(RUTA_BD + "favoritos-" + idFavoritos + ".xml");
		if (!file.exists())
			return null;

		return unmarshallCiudadFavorita(file);

	}

	public void removeDocumentoFavoritos(String idFavoritos) {
		File file = new File(RUTA_BD + "favoritos-" + idFavoritos + ".xml");
		if (file.exists())
			file.delete();

	}

	public List<CiudadResultado> buscarPaginada(String busqueda, int startRow, int maxRows)
			throws CityServiceException {
		Manejador manejador = new Manejador();

		try {
			SAXParser analizadorSAX = factoriaSAX.newSAXParser();
			String uri = CONSULTA_GEONAMES + URLEncoder.encode(busqueda, "UTF-8") + "&maxRows="
					+ URLEncoder.encode(Integer.toString(maxRows), "UTF-8") + "&startRow="
					+ URLEncoder.encode(Integer.toString(startRow), "UTF-8");
			analizadorSAX.parse(uri, manejador);

		} catch (IOException e) {
			throw new CityServiceException(INTERNAL_ERROR,
					"Could not open" + CONSULTA_GEONAMES + busqueda + "\n" + e.toString());
		} catch (SAXException e) {
			throw new CityServiceException(INTERNAL_ERROR, "XML invalid File\n" + e.toString());
		} catch (ParserConfigurationException e) {
			throw new CityServiceException(INTERNAL_ERROR, "ParserConfiguration\n" + e.toString());
		}
		return manejador.getCiudades();
	}

	public ListadoCiudadesAtom getResultadosBusquedaATOM(String busqueda, int startRow, String baseURI)
			throws CityServiceException {

		ListadoCiudadesAtom lc = new ListadoCiudadesAtom();
		lc.setTitle("Results for the search: " + busqueda);
		lc.setId(baseURI);
		lc.addAuthor("Carmelo Alcaraz Coy", "carmelo.alcaraz@um.es");
		lc.addAuthor("Nicolás Hamed Sánchez Mimón", "nicolashamed.sanchezm@um.es");
		DatatypeFactory df;
		try {
			df = DatatypeFactory.newInstance();
		} catch (DatatypeConfigurationException e) {
			throw new CityServiceException(INTERNAL_ERROR, "DatatypeFactory\n" + e.toString());
		}
		GregorianCalendar now = (GregorianCalendar) GregorianCalendar.getInstance();
		lc.setUpdated(df.newXMLGregorianCalendar(now));
		lc.addLink("previous", baseURI + "?ciudad=" + busqueda + "&startRow=" + ((startRow - 1) / 10 * 10 + 1));
		lc.addLink("self", baseURI + "?ciudad=" + busqueda + "&startRow=" + startRow);
		lc.addLink("next", baseURI + "?ciudad=" + busqueda + "&startRow=" + ((startRow - 1) / 10 * 10 + 11));

		String uriSinExtension = baseURI.substring(0, baseURI.length()-5);
		List<CiudadResultado> cities = buscarPaginada(busqueda, startRow, 10);
		List<Entry> entries = cities.stream().map((c) -> new Entry(c.getName() + " (" + c.getCountry() + ")", // Title
				uriSinExtension + "/" + c.getId(), // Id
				df.newXMLGregorianCalendar(now), // Updated
				"Entry corresponding to the city of " + c.getName() + " in " + c.getCountry() + ". Coordinates:"
						+ c.getLatitude() + "," + c.getLongitude(),
				new Link("alternate", baseURI)))// Alternate Link
				.collect(Collectors.toList());

		lc.setEntries(entries);
		return lc;

	}

	public Node getResultadosBusquedaKML(String busqueda, ServletContext context) throws CityServiceException {
		// Creamos la factoria
		TransformerFactory factoria = TransformerFactory.newInstance();
		Transformer transformador;
			
		//System.out.println(servletURI);
		// Obtenemos el archivo con una ruta relativa
		InputStream transformacion = context.getResourceAsStream("/WEB-INF/search2kml.xsl");

		// Construimos el transformador
		try {
			transformador = factoria.newTransformer(new StreamSource(transformacion));
		} catch (TransformerConfigurationException e) {
			throw new CityServiceException(INTERNAL_ERROR, "TransformerConfiguration\n" + e.toString());
		}

		// Creamos el origen
		Source origen;
		try {
			origen = new StreamSource(CONSULTA_GEONAMES + URLEncoder.encode(busqueda, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new CityServiceException(INTERNAL_ERROR,
					"Could not open" + CONSULTA_GEONAMES + busqueda + "\n" + e.toString());
		}

		// Creamos el destino
		DOMResult destino = new DOMResult();

		// Realizamos la transformación
		try {
			transformador.transform(origen, destino);

		} catch (TransformerException e) {
			throw new CityServiceException(INTERNAL_ERROR, "TransformerConfiguration\n" + e.toString());
		}

		return destino.getNode();
	}

	public ListadoCiudadesJSON getResultadosBusquedaJSON(String busqueda, int startRow, String baseURI)
			throws CityServiceException {
		ListadoCiudadesJSON lc = new ListadoCiudadesJSON();
		String uriSinExtension = baseURI.substring(0, baseURI.length()-5);
		List<CiudadResultado> cities = buscar(busqueda);
		List<CiudadResultadoJSON> _embedded = cities.stream().
				skip(startRow).
				limit(10).
				map((c) -> new CiudadResultadoJSON(
						c.getName(),c.getCountry(),c.getLatitude(),c.getLongitude(),uriSinExtension+"/"+c.getId()))
				.collect(Collectors.toList());

		lc.set_embedded(_embedded);
		lc.setCount(_embedded.size());
		lc.setTotal(cities.size());
		
		ListadoLinks ll = new ListadoLinks(
				new ListadoLinks.Link(baseURI + "?ciudad=" + busqueda + "&startRow=" + startRow),//self
				new ListadoLinks.Link(baseURI + "?ciudad=" + busqueda + "&startRow=1"),//first
				new ListadoLinks.Link(baseURI + "?ciudad=" + busqueda + "&startRow=" + ((startRow - 1) / 10 * 10 + 1)),//prev
				new ListadoLinks.Link( baseURI + "?ciudad=" + busqueda + "&startRow=" + ((startRow - 1) / 10 * 10 + 11)),//next
				new ListadoLinks.Link(baseURI + "?ciudad=" + busqueda + "&startRow="+cities.size()));//last
		
		lc.set_links(ll);
		
		return lc;
	}

}
