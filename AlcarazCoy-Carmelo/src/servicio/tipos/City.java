//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2019.03.16 a las 06:20:19 PM CET 
//


package servicio.tipos;

import java.math.BigDecimal;
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
 *         &lt;element name="geonamesid" type="{http://www.example.org/Schema}tipo_geonameid"/>
 *         &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="population" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="position">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="lat">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                         &lt;maxInclusive value="90"/>
 *                         &lt;minInclusive value="-90"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="lng">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                         &lt;maxInclusive value="180"/>
 *                         &lt;minInclusive value="-180"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="urlBDpedia" type="{http://www.example.org/Schema}url" minOccurs="0"/>
 *         &lt;element name="urlWikipedia" type="{http://www.example.org/Schema}url" minOccurs="0"/>
 *         &lt;element name="meteoInfo" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="stationName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="temperature" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                   &lt;element name="clouds" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="takenOn" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="interestPlace" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="id" type="{http://www.example.org/Schema}tipo_geonameid"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
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
    "geonamesid",
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
    protected String geonamesid;
    @XmlElement(required = true)
    protected String country;
    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger population;
    @XmlElement(required = true)
    protected City.Position position;
    protected String urlBDpedia;
    protected String urlWikipedia;
    protected City.MeteoInfo meteoInfo;
    protected List<City.InterestPlace> interestPlace;
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
     * Obtiene el valor de la propiedad geonamesid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGeonamesid() {
        return geonamesid;
    }

    /**
     * Define el valor de la propiedad geonamesid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGeonamesid(String value) {
        this.geonamesid = value;
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
     *     {@link City.Position }
     *     
     */
    public City.Position getPosition() {
        return position;
    }

    /**
     * Define el valor de la propiedad position.
     * 
     * @param value
     *     allowed object is
     *     {@link City.Position }
     *     
     */
    public void setPosition(City.Position value) {
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
     *     {@link City.MeteoInfo }
     *     
     */
    public City.MeteoInfo getMeteoInfo() {
        return meteoInfo;
    }

    /**
     * Define el valor de la propiedad meteoInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link City.MeteoInfo }
     *     
     */
    public void setMeteoInfo(City.MeteoInfo value) {
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
     * {@link City.InterestPlace }
     * 
     * 
     */
    public List<City.InterestPlace> getInterestPlace() {
        if (interestPlace == null) {
            interestPlace = new ArrayList<City.InterestPlace>();
        }
        return this.interestPlace;
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
     *         &lt;element name="id" type="{http://www.example.org/Schema}tipo_geonameid"/>
     *       &lt;/sequence>
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
        "id"
    })
    public static class InterestPlace {

        @XmlElement(required = true)
        protected String name;
        @XmlElement(required = true)
        protected String id;

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
         * Obtiene el valor de la propiedad id.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getId() {
            return id;
        }

        /**
         * Define el valor de la propiedad id.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setId(String value) {
            this.id = value;
        }

    }


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
    @XmlType(name = "", propOrder = {
        "stationName",
        "temperature",
        "clouds"
    })
    public static class MeteoInfo {

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
     *         &lt;element name="lat">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *               &lt;maxInclusive value="90"/>
     *               &lt;minInclusive value="-90"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="lng">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *               &lt;maxInclusive value="180"/>
     *               &lt;minInclusive value="-180"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "lat",
        "lng"
    })
    public static class Position {

        @XmlElement(required = true)
        protected BigDecimal lat;
        @XmlElement(required = true)
        protected BigDecimal lng;

        /**
         * Obtiene el valor de la propiedad lat.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getLat() {
            return lat;
        }

        /**
         * Define el valor de la propiedad lat.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setLat(BigDecimal value) {
            this.lat = value;
        }

        /**
         * Obtiene el valor de la propiedad lng.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getLng() {
            return lng;
        }

        /**
         * Define el valor de la propiedad lng.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setLng(BigDecimal value) {
            this.lng = value;
        }

    }

}
