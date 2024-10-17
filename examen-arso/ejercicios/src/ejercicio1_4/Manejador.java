package ejercicio1_4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Manejador extends DefaultHandler {

	private int numDiligencias = 0;
	private int numDiligenciasUltMes = 0;

	private SimpleDateFormat sdf;

	public Manejador() {
		super();
		sdf = new SimpleDateFormat("yyyy-MM-dd");

	}

	@Override
	public void startDocument() throws SAXException {

		numDiligencias = 0;
		numDiligenciasUltMes = 0;

	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		Calendar calendario = Calendar.getInstance();
		Date ahora = calendario.getTime();
		calendario.add(Calendar.MONTH, -1);
		Date haceUnMes= calendario.getTime();
		
		if (qName.equals("diligencia")) {
			numDiligencias++;
			Date fecha;
			try {
				fecha = sdf.parse(attributes.getValue("fecha"));
			} catch (ParseException e) {
				// Este punto se alcanza cuando el elemento no tiene el campo fecha
				return;
			}
			if (fecha.after(haceUnMes) && fecha.before(ahora)) {
				numDiligenciasUltMes++;
			}
		}
	}

	public int getNumDiligencias() {
		return numDiligencias;
	}

	public int getNumDiligenciasUltMes() {
		return numDiligenciasUltMes;
	}


}