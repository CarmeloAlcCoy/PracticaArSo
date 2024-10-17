package ejercicio1_8;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class Ejercicio4 {

	public static void main(String[] args) throws FileNotFoundException, XMLStreamException {

		XMLInputFactory xif = XMLInputFactory.newInstance();
		XMLStreamReader reader = xif.createXMLStreamReader(new FileInputStream("ejercicio1.3/ejercicio3.xml"));

		int numDiligencias = 0;
		int numDiligenciasUltMes = 0;
		Calendar calendario = Calendar.getInstance();
		Date ahora = calendario.getTime();
		calendario.add(Calendar.MONTH, -1);
		Date haceUnMes = calendario.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");;

		while (reader.hasNext()) {
			int evento = reader.next();

			switch (evento) {
			case XMLStreamConstants.START_ELEMENT:
				if (reader.getLocalName().equals("diligencia")) {
					numDiligencias++;
					Date fecha;
					try {
						fecha = sdf.parse(reader.getAttributeValue("", "fecha"));
					} catch (ParseException e) {
						// Este punto se alcanza cuando el elemento no tiene el campo fecha
						return;
					}
					if (fecha.after(haceUnMes) && fecha.before(ahora)) {
						numDiligenciasUltMes++;
					}
				}
				break;
			}

		}
		
		System.out.println("La calificación tiene "+numDiligencias+" diligencias.");
		System.out.println("La calificación tiene "+numDiligenciasUltMes+" diligencias en el último mes.");

	}
}
