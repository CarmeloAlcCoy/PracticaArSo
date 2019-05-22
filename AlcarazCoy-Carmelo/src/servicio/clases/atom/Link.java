package servicio.clases.atom;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class Link {
	private String rel;
	private String href;

	public Link() {
		super();
	}
	
	public Link(String rel, String href) {
		this();
		this.rel = rel;
		this.href = href;
	}

	@XmlAttribute
	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	@XmlAttribute
	public String getType() {
		return "application/atom+xml";
	}

	public void setType(String type) {

	}

	@XmlAttribute
	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

}