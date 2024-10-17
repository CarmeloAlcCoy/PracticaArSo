package jaxb.manual;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Poema {

	@XmlAttribute(required=true)
	private String fecha;
	@XmlAttribute(required=true)
	private String lugar;
	@XmlElement(required=true)
	private String titulo;
	@XmlElement(required=true)
	private List<String> verso;
	
	public Poema() {
		super();
		verso = new LinkedList<String>();
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<String> getVerso() {
		return verso;
	}
	

}
