package servicio.controlador;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import static servicio.controlador.Constants.RUTA_BD;
import static servicio.controlador.Constants.CONSULTA_GEONAMES;

import servicio.tipos.City;
import servicio.tipos.CiudadResultado;
import servicio.tipos.CiudadesFavoritas;
import servicio.tipos.ListadoCiudades;

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

	private ServicioGeoNames() throws ParseXMLException {
		// Construimos el analizador SAX
		factoriaSAX = SAXParserFactory.newInstance();
		try {
			contexto = JAXBContext.newInstance("servicio.tipos");
			contextoFavorito = JAXBContext.newInstance(CiudadesFavoritas.class);
		} catch (JAXBException e) {
			throw new ParseXMLException("JAXBFactory", "Unknown");
		}

	}

	public static ServicioGeoNames getUnicaInstancia() throws ParseXMLException {
		if (unicaInstancia == null)
			unicaInstancia = new ServicioGeoNames();
		return unicaInstancia;
	}

	private Marshaller createMarshaller(JAXBContext con) {
		Marshaller marshaller;
				
		try {
			marshaller = con.createMarshaller();

			marshaller.setProperty("jaxb.formatted.output", true);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

			if (con.equals(contexto))
				marshaller.setProperty("jaxb.schemaLocation", "http://www.example.org/schema Schema.xsd ");
		} catch (JAXBException e) {
			throw new ParseXMLException("Marshaller", e.toString());
		}
		return marshaller;
	}

	private Unmarshaller createUnmarshaller(JAXBContext con) {
		try {
			return con.createUnmarshaller();
		} catch (JAXBException e) {
			throw new ParseXMLException("Unmarshaller", e.toString());
		}
	}

	private void marshallCiudadFavorita(CiudadesFavoritas ciudades, File file) throws ParseXMLException {
		Marshaller marshaller = createMarshaller(contextoFavorito);
		try {
			marshaller.marshal(ciudades, file);
		} catch (JAXBException e) {
			throw new ParseXMLException("Marshall", e.toString());
		}
	}

	private CiudadesFavoritas unmarshallCiudadFavorita(File file) throws ParseXMLException {

		CiudadesFavoritas ciudades;
		Unmarshaller unmarshaller = createUnmarshaller(contextoFavorito);

		try {
			ciudades = (CiudadesFavoritas) unmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			throw new ParseXMLException("UnMarshall", e.toString());
		}
		return ciudades;
	}

	public List<CiudadResultado> buscar(String busqueda) throws ParseXMLException {
		Manejador manejador = new Manejador();

		try {
			SAXParser analizadorSAX = factoriaSAX.newSAXParser();
			analizadorSAX.parse(CONSULTA_GEONAMES + busqueda, manejador);

		} catch (IOException e) {
			throw new ParseXMLException(CONSULTA_GEONAMES + busqueda, "Unknown:" + e.toString());
		} catch (SAXException e) {
			throw new ParseXMLException(CONSULTA_GEONAMES + busqueda, "XML invalid File" + e.toString());
		} catch (ParserConfigurationException e) {
			throw new ParseXMLException(CONSULTA_GEONAMES + busqueda, "Parser Exception:" + e.toString());
		}
		return manejador.getCiudades();
	}

	public ListadoCiudades getResultadosBusquedaXML(String busqueda) throws ParseXMLException {

		ListadoCiudades lc = new ListadoCiudades();
		lc.setResultados(buscar(busqueda));
		return lc;

	}

	public City getCiudad(String idGeoNames) throws ParseXMLException {

		File file = recuperarDocumento(idGeoNames);

		Unmarshaller unmarshaller;
		City calificaciones;
		try {
			unmarshaller = createUnmarshaller(contexto);
			calificaciones = (City) unmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			throw new ParseXMLException("JAXBUnMarshaller", e.toString());
		}

		return calificaciones;

	}

	private File recuperarDocumento(String idGeoNames) throws ParseXMLException {
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

	public String crearDocumentoFavoritos() throws ParseXMLException {
		String id = UUID.randomUUID().toString();

		File f = new File(Constants.RUTA_BD + "favoritos-" + id + ".xml");
		if (!f.exists())
			try {
				f.createNewFile();
			} catch (IOException e) {
				throw new ParseXMLException(Constants.RUTA_BD + "favoritos-" + id + ".xml", "Unknown:" + e.toString());
			}

		return id;
	}

	public void addCiudadFavorita(String idFavoritos, String idGeoNames) throws ParseXMLException {
		File file = new File(RUTA_BD + "favoritos-" + idFavoritos + ".xml");
		if (!file.exists())
			throw new ParseXMLException(RUTA_BD + "favoritos-" + idFavoritos + ".xml", "Not found");

		CiudadesFavoritas ciudades;

		if (file.length() != 0) {

			ciudades = unmarshallCiudadFavorita(file);

			if (!ciudades.getCiudadesFavoritas().contains(idGeoNames)) {
				ciudades.addCiudadFavorita(idGeoNames);

				marshallCiudadFavorita(ciudades, file);
			}
		} else {

			ciudades = new CiudadesFavoritas(idFavoritos);
			ciudades.addCiudadFavorita(idGeoNames);
			marshallCiudadFavorita(ciudades, file);

		}

	}

	public boolean removeCiudadFavorita(String idFavoritos, String idGeoNames) throws ParseXMLException {
		File file = new File(RUTA_BD + "favoritos-" + idFavoritos + ".xml");
		if (!file.exists())
			return false;

		CiudadesFavoritas ciudades = unmarshallCiudadFavorita(file);

		boolean removed = ciudades.removeCiudadFavorita(idGeoNames);

		marshallCiudadFavorita(ciudades, file);

		return removed;
	}

	public CiudadesFavoritas getFavoritos(String idFavoritos) throws ParseXMLException {

		File file = new File(RUTA_BD + "favoritos-" + idFavoritos + ".xml");
		if (!file.exists())
			return null;

		return unmarshallCiudadFavorita(file);

	}

	public void removeDocumentoFavoritos(String idFavoritos) {
		File file = new File(RUTA_BD + idFavoritos + ".xml");
		if (file.exists())
			file.delete();

	}

}
