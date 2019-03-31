package ejercicio5;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class Main {

	public static final String transformacion = "xml/search2kml.xsl";
	public static final String documentoEntrada = "http://api.geonames.org/search?name=Cartagena&maxRows=10&username=arso";
	public static final String documentoSalida = "xml/cartagena.kml";
	
	
	public static void main(String[] args) throws TransformerException {
		TransformerFactory factoria = TransformerFactory.newInstance();
		Transformer transformador =	factoria.newTransformer(new StreamSource(transformacion));
		Source origen = new StreamSource(documentoEntrada);
		Result destino = new StreamResult(documentoSalida);
		transformador.transform(origen, destino); 
		System.out.println("Fin");
	}

}