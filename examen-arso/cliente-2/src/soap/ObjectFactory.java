
package soap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the soap package. 
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

    private final static QName _GetSaludoResponse_QNAME = new QName("http://soap/", "getSaludoResponse");
    private final static QName _GetSaludo_QNAME = new QName("http://soap/", "getSaludo");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetSaludo }
     * 
     */
    public GetSaludo createGetSaludo() {
        return new GetSaludo();
    }

    /**
     * Create an instance of {@link GetSaludoResponse }
     * 
     */
    public GetSaludoResponse createGetSaludoResponse() {
        return new GetSaludoResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSaludoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap/", name = "getSaludoResponse")
    public JAXBElement<GetSaludoResponse> createGetSaludoResponse(GetSaludoResponse value) {
        return new JAXBElement<GetSaludoResponse>(_GetSaludoResponse_QNAME, GetSaludoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSaludo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap/", name = "getSaludo")
    public JAXBElement<GetSaludo> createGetSaludo(GetSaludo value) {
        return new JAXBElement<GetSaludo>(_GetSaludo_QNAME, GetSaludo.class, null, value);
    }

}
