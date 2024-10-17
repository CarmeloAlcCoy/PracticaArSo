package ejercicio1_12;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import calificacion.Calificaciones;
import calificacion.Calificaciones.Calificacion;
import calificacion.Calificaciones.Diligencia;

public class Ejercicio12 {
	
	/**
	 * @param args
	 * @throws DatatypeConfigurationException 
	 * @throws JAXBException 
	 */
	public static void main(String[] args) throws DatatypeConfigurationException, JAXBException {
		Calificaciones calificaciones =  new Calificaciones();
		calificaciones.setCurso(new BigInteger("2018"));
		calificaciones.setAsignatura("1902");
		calificaciones.setConvocatoria("febrero");
		
		List<Calificacion> lista =  calificaciones.getCalificacion();
		Calificacion c1 = new Calificacion();
		c1.setNif("23322156M");
		c1.setNota(new BigDecimal("10"));
		Calificacion c2 = new Calificacion();
		c2.setNif("13322156M");
		c2.setNombre("Pepe");
		c2.setNota(new BigDecimal("8"));
		lista.add(c1);
		lista.add(c2);
		Diligencia d = new Diligencia();
		d.setNif("13322156M");
		d.setNota(new BigDecimal("9"));
		XMLGregorianCalendar fecha = DatatypeFactory.newInstance().newXMLGregorianCalendar();
		fecha.setYear(2019);
		fecha.setMonth(2);
		fecha.setDay(12);
		d.setFecha(fecha);
		
		JAXBContext contexto = JAXBContext.newInstance("calificacion");
		
		Marshaller marshaller = contexto.createMarshaller();
		marshaller.marshal(calificaciones, new File("ejercicio1.12/calificaciones-generado.xml"));
		
	}

}
