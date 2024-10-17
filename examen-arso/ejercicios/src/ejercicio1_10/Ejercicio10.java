package ejercicio1_10;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class Ejercicio10 {

	public static final String transformacion = "ejercicio1.10/ejercicio10.xsl";
	public static final String documentoEntrada = "ejercicio1.10/poema.html";
	public static final String documentoSalida = "ejercicio1.10/poema_transformado.xml";

	public static void main(String[] args) throws TransformerException {
		TransformerFactory factoria = TransformerFactory.newInstance();
		Transformer transformador = factoria.newTransformer(new StreamSource(transformacion));
		Source origen = new StreamSource(documentoEntrada);
		Result destino = new StreamResult(documentoSalida);
		transformador.transform(origen, destino);
		System.out.println("Fin");
	}
}
