//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2019.03.27 a las 04:37:15 PM CET 
//


package bookle.tipos;
import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the calificaiones package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: calificaiones
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Actividad }
     * 
     */
    public Actividad createActividad() {
        return new Actividad();
    }

    /**
     * Create an instance of {@link Actividad.Agenda }
     * 
     */
    public Actividad.Agenda createActividadAgenda() {
        return new Actividad.Agenda();
    }

    /**
     * Create an instance of {@link Actividad.Agenda.Turno }
     * 
     */
    public Actividad.Agenda.Turno createActividadAgendaTurno() {
        return new Actividad.Agenda.Turno();
    }

    /**
     * Create an instance of {@link Actividad.Agenda.Turno.Reserva }
     * 
     */
    public Actividad.Agenda.Turno.Reserva createActividadAgendaTurnoReserva() {
        return new Actividad.Agenda.Turno.Reserva();
    }

}
