package servicio.clases;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "entry")
public class Entry {

	private String id;
	private String title;
	@XmlSchemaType(name = "dateTime")
	private XMLGregorianCalendar updated;
	private String summary;
	private Link link;

	public Entry() {
		super();
	}
	
	public Entry(String title, String id, XMLGregorianCalendar updated, String summary, Link link) {
		this();
		this.id = id;
		this.title = title;
		this.updated = updated;
		this.summary = summary;
		this.link=link;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public XMLGregorianCalendar getUpdated() {
		return updated;
	}

	public void setUpdated(XMLGregorianCalendar updated) {
		this.updated = updated;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Link getLink() {
		return link;
	}

	public void setLink(Link link) {
		this.link = link;
	}

	
}
