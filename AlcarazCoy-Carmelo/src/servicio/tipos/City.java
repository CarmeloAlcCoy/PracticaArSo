//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2019.03.31 a las 03:14:23 PM CEST 
//


package servicio.tipos;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="population" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="position" type="{http://www.example.org/Schema}Position"/>
 *         &lt;element name="urlBDpedia" type="{http://www.example.org/Schema}url" minOccurs="0"/>
 *         &lt;element name="urlWikipedia" type="{http://www.example.org/Schema}url" minOccurs="0"/>
 *         &lt;element name="meteoInfo" type="{http://www.example.org/Schema}MeteoInfo" minOccurs="0"/>
 *         &lt;element name="interestPlace" type="{http://www.example.org/Schema}InterestPlace" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *       &lt;attribute name="updatedOn" type="{http://www.w3.org/2001/XMLSchema}date" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "name",
    "country",
    "population",
    "position",
    "urlBDpedia",
    "urlWikipedia",
    "meteoInfo",
    "interestPlace"
})
@XmlRootElement(name = "city")
public class City {

    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String country;
    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger population;
    @XmlElement(required = true)
    protected Position position;
    protected String urlBDpedia;
    protected String urlWikipedia;
    protected MeteoInfo meteoInfo;
    protected List<InterestPlace> interestPlace;
    @XmlAttribute(name = "id")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger id;
    @XmlAttribute(name = "updatedOn")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar updatedOn;

    /**
     * Obtiene el valor de la propiedad name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Define el valor de la propiedad name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Obtiene el valor de la propiedad country.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountry() {
        return country;
    }

    /**
     * Define el valor de la propiedad country.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountry(String value) {
        this.country = value;
    }

    /**
     * Obtiene el valor de la propiedad population.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPopulation() {
        return population;
    }

    /**
     * Define el valor de la propiedad population.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPopulation(BigInteger value) {
        this.population = value;
    }

    /**
     * Obtiene el valor de la propiedad position.
     * 
     * @return
     *     possible object is
     *     {@link Position }
     *     
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Define el valor de la propiedad position.
     * 
     * @param value
     *     allowed object is
     *     {@link Position }
     *     
     */
    public void setPosition(Position value) {
        this.position = value;
    }

    /**
     * Obtiene el valor de la propiedad urlBDpedia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlBDpedia() {
        return urlBDpedia;
    }

    /**
     * Define el valor de la propiedad urlBDpedia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlBDpedia(String value) {
        this.urlBDpedia = value;
    }

    /**
     * Obtiene el valor de la propiedad urlWikipedia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlWikipedia() {
        return urlWikipedia;
    }

    /**
     * Define el valor de la propiedad urlWikipedia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlWikipedia(String value) {
        this.urlWikipedia = value;
    }

    /**
     * Obtiene el valor de la propiedad meteoInfo.
     * 
     * @return
     *     possible object is
     *     {@link MeteoInfo }
     *     
     */
    public MeteoInfo getMeteoInfo() {
        return meteoInfo;
    }

    /**
     * Define el valor de la propiedad meteoInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link MeteoInfo }
     *     
     */
    public void setMeteoInfo(MeteoInfo value) {
        this.meteoInfo = value;
    }

    /**
     * Gets the value of the interestPlace property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the interestPlace property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInterestPlace().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InterestPlace }
     * 
     * 
     */
    public List<InterestPlace> getInterestPlace() {
        if (interestPlace == null) {
            interestPlace = new ArrayList<InterestPlace>();
        }
        return this.interestPlace;
    }

    /**
     * Obtiene el valor de la propiedad id.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getId() {
        return id;
    }

    /**
     * Define el valor de la propiedad id.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setId(BigInteger value) {
        this.id = value;
    }

    /**
     * Obtiene el valor de la propiedad updatedOn.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpdatedOn() {
        return updatedOn;
    }

    /**
     * Define el valor de la propiedad updatedOn.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpdatedOn(XMLGregorianCalendar value) {
        this.updatedOn = value;
    }

}
