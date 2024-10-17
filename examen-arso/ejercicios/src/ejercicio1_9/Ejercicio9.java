package ejercicio1_9;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class Ejercicio9 {
	
	public static final String transformacion = "ejercicio1.9/ejercicio9_declarativo.xsl";
	public static final String transformacionDeclarativa = "ejercicio1.9/ejercicio9.xsl";
	public static final String documentoEntrada = "ejercicio1.9/poema.xml";
	public static final String documentoSalida = "ejercicio1.9/poema_transformado_dec.html";
	
	
	public static void main(String[] args) throws TransformerException {
		TransformerFactory factoria = TransformerFactory.newInstance();
		Transformer transformador =	factoria.newTransformer(new StreamSource(transformacion));
		Source origen = new StreamSource(documentoEntrada);
		Result destino = new StreamResult(documentoSalida);
		transformador.transform(origen, destino);
		System.out.println("Fin");
	}
}