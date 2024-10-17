//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2019.03.13 a las 06:15:24 PM CET 
//


package calificacion;

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
 *         &lt;element name="calificacion" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="nif" type="{http://www.example.org/ejercicio3}tipo_dni"/>
 *                   &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="nota" type="{http://www.example.org/ejercicio3}tipo_nota"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="diligencia" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="nif" type="{http://www.example.org/ejercicio3}tipo_dni"/>
 *                   &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="nota" type="{http://www.example.org/ejercicio3}tipo_nota"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="fecha" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *                 &lt;attribute name="extraordinaria" default="no">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                       &lt;enumeration value="si"/>
 *                       &lt;enumeration value="no"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="asignatura" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="convocatoria">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="febrero"/>
 *             &lt;enumeration value="junio"/>
 *             &lt;enumeration value="julio"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="curso" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "calificacion",
    "diligencia"
})
@XmlRootElement(name = "calificaciones")
public class Calificaciones {

    @XmlElement(required = true)
    protected List<Calificaciones.Calificacion> calificacion;
    protected List<Calificaciones.Diligencia> diligencia;
    @XmlAttribute(name = "asignatura", required = true)
    protected String asignatura;
    @XmlAttribute(name = "convocatoria")
    protected String convocatoria;
    @XmlAttribute(name = "curso")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger curso;

    /**
     * Gets the value of the calificacion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the calificacion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCalificacion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Calificaciones.Calificacion }
     * 
     * 
     */
    public List<Calificaciones.Calificacion> getCalificacion() {
        if (calificacion == null) {
            calificacion = new ArrayList<Calificaciones.Calificacion>();
        }
        return this.calificacion;
    }

    /**
     * Gets the value of the diligencia property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the diligencia property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDiligencia().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Calificaciones.Diligencia }
     * 
     * 
     */
    public List<Calificaciones.Diligencia> getDiligencia() {
        if (diligencia == null) {
            diligencia = new ArrayList<Calificaciones.Diligencia>();
        }
        return this.diligencia;
    }

    /**
     * Obtiene el valor de la propiedad asignatura.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAsignatura() {
        return asignatura;
    }

    /**
     * Define el valor de la propiedad asignatura.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAsignatura(String value) {
        this.asignatura = value;
    }

    /**
     * Obtiene el valor de la propiedad convocatoria.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConvocatoria() {
        return convocatoria;
    }

    /**
     * Define el valor de la propiedad convocatoria.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConvocatoria(String value) {
        this.convocatoria = value;
    }

    /**
     * Obtiene el valor de la propiedad curso.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCurso() {
        return curso;
    }

    /**
     * Define el valor de la propiedad curso.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCurso(BigInteger value) {
        this.curso = value;
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
     *         &lt;element name="nif" type="{http://www.example.org/ejercicio3}tipo_dni"/>
     *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="nota" type="{http://www.example.org/ejercicio3}tipo_nota"/>
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
        "nif",
        "nombre",
        "nota"
    })
    public static class Calificacion {

        @XmlElement(required = true)
        protected String nif;
        protected String nombre;
        @XmlElement(required = true)
        protected BigDecimal nota;

        /**
         * Obtiene el valor de la propiedad nif.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNif() {
            return nif;
        }

        /**
         * Define el valor de la propiedad nif.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNif(String value) {
            this.nif = value;
        }

        /**
         * Obtiene el valor de la propiedad nombre.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNombre() {
            return nombre;
        }

        /**
         * Define el valor de la propiedad nombre.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNombre(String value) {
            this.nombre = value;
        }

        /**
         * Obtiene el valor de la propiedad nota.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getNota() {
            return nota;
        }

        /**
         * Define el valor de la propiedad nota.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setNota(BigDecimal value) {
            this.nota = value;
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
     *         &lt;element name="nif" type="{http://www.example.org/ejercicio3}tipo_dni"/>
     *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="nota" type="{http://www.example.org/ejercicio3}tipo_nota"/>
     *       &lt;/sequence>
     *       &lt;attribute name="fecha" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
     *       &lt;attribute name="extraordinaria" default="no">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *             &lt;enumeration value="si"/>
     *             &lt;enumeration value="no"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "nif",
        "nombre",
        "nota"
    })
    public static class Diligencia {

        @XmlElement(required = true)
        protected String nif;
        protected String nombre;
        @XmlElement(required = true)
        protected BigDecimal nota;
        @XmlAttribute(name = "fecha", required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar fecha;
        @XmlAttribute(name = "extraordinaria")
        protected String extraordinaria;

        /**
         * Obtiene el valor de la propiedad nif.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNif() {
            return nif;
        }

        /**
         * Define el valor de la propiedad nif.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNif(String value) {
            this.nif = value;
        }

        /**
         * Obtiene el valor de la propiedad nombre.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNombre() {
            return nombre;
        }

        /**
         * Define el valor de la propiedad nombre.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNombre(String value) {
            this.nombre = value;
        }

        /**
         * Obtiene el valor de la propiedad nota.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getNota() {
            return nota;
        }

        /**
         * Define el valor de la propiedad nota.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setNota(BigDecimal value) {
            this.nota = value;
        }

        /**
         * Obtiene el valor de la propiedad fecha.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getFecha() {
            return fecha;
        }

        /**
         * Define el valor de la propiedad fecha.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setFecha(XMLGregorianCalendar value) {
            this.fecha = value;
        }

        /**
         * Obtiene el valor de la propiedad extraordinaria.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getExtraordinaria() {
            if (extraordinaria == null) {
                return "no";
            } else {
                return extraordinaria;
            }
        }

        /**
         * Define el valor de la propiedad extraordinaria.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setExtraordinaria(String value) {
            this.extraordinaria = value;
        }

    }

}
