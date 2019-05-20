package servicio.clases;

import javax.xml.bind.annotation.XmlType;

@XmlType
public class ListadoLinks {

	private Link self;
	private Link first;
	private Link prev;
	private Link next;
	private Link last;
	
	

	public ListadoLinks() {
		super();
	}

	public ListadoLinks(String href) {
		this(new Link(href),null,null,null,null);
	}
	
	public ListadoLinks(Link self, Link first, Link prev, Link next, Link last) {
		this();
		this.self = self;
		this.first = first;
		this.prev = prev;
		this.next = next;
		this.last = last;
	}

	public Link getSelf() {
		return self;
	}

	public void setSelf(Link self) {
		this.self = self;
	}

	public Link getFirst() {
		return first;
	}

	public void setFirst(Link first) {
		this.first = first;
	}

	public Link getPrev() {
		return prev;
	}

	public void setPrev(Link prev) {
		this.prev = prev;
	}

	public Link getNext() {
		return next;
	}

	public void setNext(Link next) {
		this.next = next;
	}

	public Link getLast() {
		return last;
	}

	public void setLast(Link last) {
		this.last = last;
	}


	@XmlType
	public static class Link{
		private String href;

		public Link() {
			super();
		}

		public Link(String href) {
			super();
			this.href = href;
		}

		public String getHref() {
			return href;
		}

		public void setHref(String href) {
			this.href = href;
		}
	
	}
}
