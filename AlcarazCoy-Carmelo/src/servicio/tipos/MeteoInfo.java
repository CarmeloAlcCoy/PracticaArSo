//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2019.04.08 a las 04:13:41 PM CEST 
//


package servicio.tipos;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para MeteoInfo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="MeteoInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="stationName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="temperature" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="clouds" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *       &lt;attribute name="takenOn" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MeteoInfo", propOrder = {
    "stationName",
    "temperature",
    "clouds"
})
public class MeteoInfo {

    @XmlElement(required = true)
    protected String stationName;
    @XmlElement(required = true)
    protected BigDecimal temperature;
    @XmlElement(required = true)
    protected String clouds;
    @XmlAttribute(name = "takenOn")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar takenOn;

    /**
     * Obtiene el valor de la propiedad stationName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStationName() {
        return stationName;
    }

    /**
     * Define el valor de la propiedad stationName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStationName(String value) {
        this.stationName = value;
    }

    /**
     * Obtiene el valor de la propiedad temperature.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTemperature() {
        return temperature;
    }

    /**
     * Define el valor de la propiedad temperature.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTemperature(BigDecimal value) {
        this.temperature = value;
    }

    /**
     * Obtiene el valor de la propiedad clouds.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClouds() {
        return clouds;
    }

    /**
     * Define el valor de la propiedad clouds.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClouds(String value) {
        this.clouds = value;
    }

    /**
     * Obtiene el valor de la propiedad takenOn.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTakenOn() {
        return takenOn;
    }

    /**
     * Define el valor de la propiedad takenOn.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTakenOn(XMLGregorianCalendar value) {
        this.takenOn = value;
    }

}
