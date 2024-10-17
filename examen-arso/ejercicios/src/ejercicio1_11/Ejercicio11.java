package ejercicio1_11;

import java.io.File;
import java.math.BigDecimal;
import java.util.Calendar;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import calificacion.Calificaciones;
import calificacion.Calificaciones.Calificacion;
import calificacion.Calificaciones.Diligencia;

public class Ejercicio11 {

	public static void main(String[] args) throws JAXBException {
		
		// 1. Construir el contexto JAXB
		JAXBContext contexto = JAXBContext.newInstance("calificacion");
		// 2. Obtener el árbol de contenido de un documento XML
		Unmarshaller unmarshaller = contexto.createUnmarshaller();
		Calificaciones calificaciones =		(Calificaciones) unmarshaller.unmarshal(new File("ejercicio1.3/ejercicio3.xml"));
		Calendar ahora = Calendar.getInstance();
		Calendar haceUnMes = Calendar.getInstance();
		haceUnMes.add(Calendar.MONTH, -1);
		int numDiligenciasUltMes=0;
		
		for (Diligencia  diligencia : calificaciones.getDiligencia()) {
			Calendar fecha = diligencia.getFecha().toGregorianCalendar();
			if(ahora.compareTo(fecha) >=0 && haceUnMes.compareTo(fecha)<=0) {
				numDiligenciasUltMes++;
			}
		}
		System.out.println("La calificación tiene "+numDiligenciasUltMes+" diligencias en el último mes..");
		int num=0;
		int suma=0;
		for (Calificacion  cal : calificaciones.getCalificacion()) {
			num++;
			double nota = cal.getNota().doubleValue();
			suma+=nota;
			if(nota<=9.5) nota+=0.5;
			cal.setNota(new BigDecimal(nota));
		}
		System.out.println("La media es "+suma/num+".");
		
		Marshaller marshaller = contexto.createMarshaller();
		marshaller.marshal(calificaciones, new File("ejercicio1.11/calificaciones-modificado.xml"));
		
	}

}
