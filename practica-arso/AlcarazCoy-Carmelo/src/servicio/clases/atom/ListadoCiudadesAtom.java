package servicio.clases.atom;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "feed")
public class ListadoCiudadesAtom {

	private String id;
	private String title;
	@XmlElement(name = "author")
	private List<Author> authors;
	@XmlSchemaType(name = "dateTime")
	private XMLGregorianCalendar updated;
	@XmlElement(name = "link")
	private List<Link> links;
	@XmlElement(name = "entry")
	private List<Entry> entries;
	
	

	public ListadoCiudadesAtom() {
		super();
		authors= new ArrayList<Author>(2);
		links= new ArrayList<Link>();
		entries=new ArrayList<Entry>(10);
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

	public List<Entry> getEntries() {
		return entries;
	}

	public void setEntries(List<Entry> entries) {
		this.entries = entries;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void addAuthor(String name, String email) {
		authors.add(new Author(name, email));
	}
	
	public List<Link> getLinks() {
		return links;
	}
	
	public void addLink(String rel , String href ){
		links.add(new Link(rel, href));
	}
	
	@XmlType
	public static class Author {
		private String name;
		private String email;

		public Author() {
			super();
		}
		
		public Author(String name, String email) {
			this();
			this.name = name;
			this.email = email;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

	}
}


