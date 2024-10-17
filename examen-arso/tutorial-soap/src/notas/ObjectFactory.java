
package notas;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the notas package. 
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

    private final static QName _AddNota_QNAME = new QName("http://notas/", "addNota");
    private final static QName _RemoveNota_QNAME = new QName("http://notas/", "removeNota");
    private final static QName _RemoveNotaResponse_QNAME = new QName("http://notas/", "removeNotaResponse");
    private final static QName _AddNotaResponse_QNAME = new QName("http://notas/", "addNotaResponse");
    private final static QName _ServicioNotasException_QNAME = new QName("http://notas/", "ServicioNotasException");
    private final static QName _GetNota_QNAME = new QName("http://notas/", "getNota");
    private final static QName _GetNotasResponse_QNAME = new QName("http://notas/", "getNotasResponse");
    private final static QName _GetClaveUsoResponse_QNAME = new QName("http://notas/", "getClaveUsoResponse");
    private final static QName _GetNotas_QNAME = new QName("http://notas/", "getNotas");
    private final static QName _GetNotaResponse_QNAME = new QName("http://notas/", "getNotaResponse");
    private final static QName _GetClaveUso_QNAME = new QName("http://notas/", "getClaveUso");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: notas
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetClaveUsoResponse }
     * 
     */
    public GetClaveUsoResponse createGetClaveUsoResponse() {
        return new GetClaveUsoResponse();
    }

    /**
     * Create an instance of {@link GetNotas }
     * 
     */
    public GetNotas createGetNotas() {
        return new GetNotas();
    }

    /**
     * Create an instance of {@link GetNotaResponse }
     * 
     */
    public GetNotaResponse createGetNotaResponse() {
        return new GetNotaResponse();
    }

    /**
     * Create an instance of {@link GetClaveUso }
     * 
     */
    public GetClaveUso createGetClaveUso() {
        return new GetClaveUso();
    }

    /**
     * Create an instance of {@link RemoveNotaResponse }
     * 
     */
    public RemoveNotaResponse createRemoveNotaResponse() {
        return new RemoveNotaResponse();
    }

    /**
     * Create an instance of {@link AddNotaResponse }
     * 
     */
    public AddNotaResponse createAddNotaResponse() {
        return new AddNotaResponse();
    }

    /**
     * Create an instance of {@link GetNota }
     * 
     */
    public GetNota createGetNota() {
        return new GetNota();
    }

    /**
     * Create an instance of {@link GetNotasResponse }
     * 
     */
    public GetNotasResponse createGetNotasResponse() {
        return new GetNotasResponse();
    }

    /**
     * Create an instance of {@link AddNota }
     * 
     */
    public AddNota createAddNota() {
        return new AddNota();
    }

    /**
     * Create an instance of {@link RemoveNota }
     * 
     */
    public RemoveNota createRemoveNota() {
        return new RemoveNota();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNota }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://notas/", name = "addNota")
    public JAXBElement<AddNota> createAddNota(AddNota value) {
        return new JAXBElement<AddNota>(_AddNota_QNAME, AddNota.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveNota }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://notas/", name = "removeNota")
    public JAXBElement<RemoveNota> createRemoveNota(RemoveNota value) {
        return new JAXBElement<RemoveNota>(_RemoveNota_QNAME, RemoveNota.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveNotaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://notas/", name = "removeNotaResponse")
    public JAXBElement<RemoveNotaResponse> createRemoveNotaResponse(RemoveNotaResponse value) {
        return new JAXBElement<RemoveNotaResponse>(_RemoveNotaResponse_QNAME, RemoveNotaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNotaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://notas/", name = "addNotaResponse")
    public JAXBElement<AddNotaResponse> createAddNotaResponse(AddNotaResponse value) {
        return new JAXBElement<AddNotaResponse>(_AddNotaResponse_QNAME, AddNotaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://notas/", name = "ServicioNotasException")
    public JAXBElement<String> createServicioNotasException(String value) {
        return new JAXBElement<String>(_ServicioNotasException_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNota }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://notas/", name = "getNota")
    public JAXBElement<GetNota> createGetNota(GetNota value) {
        return new JAXBElement<GetNota>(_GetNota_QNAME, GetNota.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNotasResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://notas/", name = "getNotasResponse")
    public JAXBElement<GetNotasResponse> createGetNotasResponse(GetNotasResponse value) {
        return new JAXBElement<GetNotasResponse>(_GetNotasResponse_QNAME, GetNotasResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetClaveUsoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://notas/", name = "getClaveUsoResponse")
    public JAXBElement<GetClaveUsoResponse> createGetClaveUsoResponse(GetClaveUsoResponse value) {
        return new JAXBElement<GetClaveUsoResponse>(_GetClaveUsoResponse_QNAME, GetClaveUsoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNotas }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://notas/", name = "getNotas")
    public JAXBElement<GetNotas> createGetNotas(GetNotas value) {
        return new JAXBElement<GetNotas>(_GetNotas_QNAME, GetNotas.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNotaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://notas/", name = "getNotaResponse")
    public JAXBElement<GetNotaResponse> createGetNotaResponse(GetNotaResponse value) {
        return new JAXBElement<GetNotaResponse>(_GetNotaResponse_QNAME, GetNotaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetClaveUso }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://notas/", name = "getClaveUso")
    public JAXBElement<GetClaveUso> createGetClaveUso(GetClaveUso value) {
        return new JAXBElement<GetClaveUso>(_GetClaveUso_QNAME, GetClaveUso.class, null, value);
    }

}
