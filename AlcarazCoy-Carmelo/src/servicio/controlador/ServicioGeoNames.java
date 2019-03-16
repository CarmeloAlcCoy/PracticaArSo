package servicio.controlador;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import ejercicio3.Manejador;
import modelo.City;

public class ServicioGeoNames {
	
	private static final String CONSULTA_GEONAMES = 
			"http://api.geonames.org/search?username=arso&name=";
	private static ServicioGeoNames unicaInstancia;
	private SAXParser analizadorSAX;
	
	static {        	
	  	File folder = new File("xml-bd");        	
	  	if (! folder.exists())
	  	  	folder.mkdir();
	}

	
	private ServicioGeoNames() {
		// Construimos el analizador SAX
		SAXParserFactory factoria = SAXParserFactory.newInstance();
		try {
			analizadorSAX = factoria.newSAXParser();
		} catch (ParserConfigurationException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static ServicioGeoNames getUnicaInstancia() {
		if(unicaInstancia==null) unicaInstancia = new ServicioGeoNames();
		return unicaInstancia;
	}
	
	
	public List<City> buscar(String busqueda) {
		Manejador manejador = new Manejador();
		try {
			
			analizadorSAX.parse(CONSULTA_GEONAMES+busqueda, manejador);

		} catch (IOException e) {
			System.out.println("El documento no ha podido ser leído. Consulta: "+busqueda);
			return null;
		} catch (SAXException e) {
			System.out.println("Error de pocesamiento: " + e.getMessage());
			return null;
		}
		return manejador.getCiudades();
	}
	
	public void getCiudad(String idGeoNames) {
		
	}

}
