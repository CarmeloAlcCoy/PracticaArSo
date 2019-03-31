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
import javax.xml.stream.XMLStreamException;

import org.xml.sax.SAXException;

import static servicio.controlador.Constants.RUTA_BD;

import servicio.tipos.City;
import servicio.tipos.CiudadResultado;
import servicio.tipos.CiudadesFavoritas;

public class ServicioGeoNames {

	private static final String CONSULTA_GEONAMES = "http://api.geonames.org/search?username=arso&name=";
	private static ServicioGeoNames unicaInstancia;
	private SAXParserFactory factoriaSAX;
	private JAXBContext contexto;

	static {
		File folder = new File("xml-bd");
		if (!folder.exists())
			folder.mkdir();
	}

	private ServicioGeoNames() {
		// Construimos el analizador SAX
		factoriaSAX = SAXParserFactory.newInstance();
		try {
			contexto = JAXBContext.newInstance("servicio.tipos");
		} catch (JAXBException e) {
			throw new ParseXMLException("JAXBFactory", "Unknown");
		}

	}

	private Marshaller createMarshaller() {
		Marshaller marshaller;
		try {
			marshaller = contexto.createMarshaller();
			marshaller.setProperty("jaxb.formatted.output", true);
			marshaller.setProperty("jaxb.schemaLocation", "http://www.example.org/schema schema.xsd ");
		} catch (JAXBException e) {
			throw new ParseXMLException("Marshaller", e.toString());
		}
		return marshaller;
	}

	private Unmarshaller createUnmarshaller() {
		try {
			return contexto.createUnmarshaller();
		} catch (JAXBException e) {
			throw new ParseXMLException("Unmarshaller", e.toString());
		}
	}
	
	private void marshallCiudadFavorita(CiudadesFavoritas ciudades) throws ParseXMLException {
		Marshaller marshaller = createMarshaller();
		try {
			marshaller.marshal(ciudades, new File(Constants.RUTA_BD + ciudades.getId() + ".xml"));
		} catch (JAXBException e) {
			throw new ParseXMLException("Marshall", e.toString());
		}
	}

	private CiudadesFavoritas unmarshallCiudadFavorita(File file) throws ParseXMLException {
		CiudadesFavoritas ciudades;
		Unmarshaller unmarshaller = createUnmarshaller();
		try {
			ciudades = (CiudadesFavoritas) unmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			throw new ParseXMLException("UnMarshall", e.toString());
		}
		return ciudades;
	}

	public static ServicioGeoNames getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new ServicioGeoNames();
		return unicaInstancia;
	}

	public List<CiudadResultado> buscar(String busqueda) {
		Manejador manejador = new Manejador();

		try {
			SAXParser analizadorSAX = factoriaSAX.newSAXParser();
			analizadorSAX.parse(CONSULTA_GEONAMES + busqueda, manejador);

		} catch (IOException e) {
			System.out.println("El documento no ha podido ser leído. Consulta: " + busqueda);
			return null;
		} catch (SAXException e) {
			System.out.println("Error de pocesamiento: " + e.getMessage());
			return null;
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return manejador.getCiudades();
	}

	public City getCiudad(String idGeoNames) throws ParseXMLException {

		File file = recuperarDocumento(idGeoNames);

		Unmarshaller unmarshaller;
		City calificaciones;
		try {
			unmarshaller = contexto.createUnmarshaller();
			calificaciones = (City) unmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			throw new ParseXMLException("JAXBUnMarshaller", e.toString());
		}

		return calificaciones;

	}

	private File recuperarDocumento(String idGeoNames) throws ParseXMLException {
		File file = new File(Constants.RUTA_BD + idGeoNames + ".xml");
		if (file.exists()) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.HOUR, -1);
			Date haceUnaHora = calendar.getTime();
			Date lastModified = new Date(file.lastModified());
			if (lastModified.after(haceUnaHora)) {
				return file;
			}
		} else {
			CityXMLProvider xml = new CityXMLProvider();
			try {
				xml.recuperarDocumento(idGeoNames);
			} catch (ParserConfigurationException | SAXException | XMLStreamException e) {
				throw new ParseXMLException("XML FILE", e.toString());
			}
		}

		return file;

	}

	public String crearDocumentoFavoritos() throws ParseXMLException {
		String id = UUID.randomUUID().toString();
		CiudadesFavoritas ciudades = new CiudadesFavoritas(id);

		marshallCiudadFavorita(ciudades);

		return id;
	}

	public void addCiudadFavorita(String idFavoritos, String idGeoNames) throws ParseXMLException {
		File file = new File(RUTA_BD + idFavoritos + ".xml");
		if (!file.exists())
			throw new ParseXMLException(RUTA_BD + idFavoritos + ".xml", "Not found");

		CiudadesFavoritas ciudades = unmarshallCiudadFavorita(file);

		if (!ciudades.getCiudadesFavoritas().contains(idGeoNames)) {
			ciudades.addCiudadFavorita(idGeoNames);

			marshallCiudadFavorita(ciudades);
		}

	}

	

	public boolean removeCiudadFavorita(String idFavoritos, String idGeoNames) {
		File file = new File(RUTA_BD + idFavoritos + ".xml");
		if (!file.exists())
			throw new ParseXMLException(RUTA_BD + idFavoritos + ".xml", "Not found");

		CiudadesFavoritas ciudades = unmarshallCiudadFavorita(file);

		boolean removed = ciudades.removeCiudadFavorita(idGeoNames);

		marshallCiudadFavorita(ciudades);

		return removed;
	}

	public CiudadesFavoritas getFavoritos(String idFavoritos) {
		
		File file = new File(RUTA_BD + idFavoritos + ".xml");
		if (!file.exists())
			throw new ParseXMLException(RUTA_BD + idFavoritos + ".xml", "Not found");

		return unmarshallCiudadFavorita(file);
		
	}

	public void removeDocumentoFavoritos(String idFavoritos) {
		File file = new File(RUTA_BD + idFavoritos + ".xml");
		if (file.exists())
			file.delete();

	}

}
