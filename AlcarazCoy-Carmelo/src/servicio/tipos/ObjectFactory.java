//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2019.03.16 a las 06:20:19 PM CET 
//


package servicio.tipos;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cal package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cal
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link City }
     * 
     */
    public City createCity() {
        return new City();
    }

    /**
     * Create an instance of {@link City.Position }
     * 
     */
    public City.Position createCityPosition() {
        return new City.Position();
    }

    /**
     * Create an instance of {@link City.MeteoInfo }
     * 
     */
    public City.MeteoInfo createCityMeteoInfo() {
        return new City.MeteoInfo();
    }

    /**
     * Create an instance of {@link City.InterestPlace }
     * 
     */
    public City.InterestPlace createCityInterestPlace() {
        return new City.InterestPlace();
    }

}
