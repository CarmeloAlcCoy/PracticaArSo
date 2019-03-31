package servicio.tipos;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ciudadesFavoritas")
public class CiudadesFavoritas {

	@XmlElement(required = true)
	private String id;
	private List<String> ciudadesFavoritas;
	
	public CiudadesFavoritas() {
		super();
		ciudadesFavoritas= new ArrayList<String>();
	}
	
	public CiudadesFavoritas(String id) {
		this();
		this.id=id;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getCiudadesFavoritas() {
		return ciudadesFavoritas;
	}

	public void addCiudadFavorita(String idCiudad) {
		ciudadesFavoritas.add(idCiudad);
	}
	
	public boolean removeCiudadFavorita(String idCiudad) {
		return ciudadesFavoritas.remove(idCiudad);
	}

}
