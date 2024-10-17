package jaxb.manual;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class Programa {

	public static void main(String[] args) throws JAXBException {
		
		JAXBContext contexto = JAXBContext.newInstance(Poema.class);
		
		Poema poema = new Poema();
		poema.setLugar("Granada");
		poema.setFecha("Abril de 1915");
		poema.setTitulo("Alba");
		
		poema.getVerso().add("Mi corazón oprimido");
		poema.getVerso().add("siente junto a la alborada");
		poema.getVerso().add("el dolor de sus amores");
		poema.getVerso().add("y el sueño de las distancias.");
		
		
		Marshaller marshaller = contexto.createMarshaller();
		marshaller.setProperty("jaxb.formatted.output", true);
		
		marshaller.marshal(poema, new File("xml/poema2.xml"));
		System.out.println("Fin.");
		

	}

}
