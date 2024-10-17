
package ejercicio2_2;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ejercicio2_2 package. 
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

    private final static QName _ExceptionConversion_QNAME = new QName("http://ejercicio2_2/", "ExceptionConversion");
    private final static QName _String2IntResponse_QNAME = new QName("http://ejercicio2_2/", "string2intResponse");
    private final static QName _String2Int_QNAME = new QName("http://ejercicio2_2/", "string2int");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ejercicio2_2
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link String2Int }
     * 
     */
    public String2Int createString2Int() {
        return new String2Int();
    }

    /**
     * Create an instance of {@link String2IntResponse }
     * 
     */
    public String2IntResponse createString2IntResponse() {
        return new String2IntResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ejercicio2_2/", name = "ExceptionConversion")
    public JAXBElement<String> createExceptionConversion(String value) {
        return new JAXBElement<String>(_ExceptionConversion_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String2IntResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ejercicio2_2/", name = "string2intResponse")
    public JAXBElement<String2IntResponse> createString2IntResponse(String2IntResponse value) {
        return new JAXBElement<String2IntResponse>(_String2IntResponse_QNAME, String2IntResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String2Int }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ejercicio2_2/", name = "string2int")
    public JAXBElement<String2Int> createString2Int(String2Int value) {
        return new JAXBElement<String2Int>(_String2Int_QNAME, String2Int.class, null, value);
    }

}
