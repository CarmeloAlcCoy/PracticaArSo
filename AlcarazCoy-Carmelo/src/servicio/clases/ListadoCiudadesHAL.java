package servicio.clases;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "")
public class ListadoCiudadesHAL {
	
	private ListadoLinks _links;
	private int count;
	private int total;
	private List<CiudadResultadoHAL> _embedded;
	
	public ListadoCiudadesHAL() {
		super();
	}

	public ListadoCiudadesHAL(ListadoLinks _links, int count, int total, List<CiudadResultadoHAL> _embedded) {
		super();
		this._links = _links;
		this.count = count;
		this.total = total;
		this._embedded = _embedded;
	}

	public ListadoLinks get_links() {
		return _links;
	}

	public void set_links(ListadoLinks _links) {
		this._links = _links;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<CiudadResultadoHAL> get_embedded() {
		return _embedded;
	}

	public void set_embedded(List<CiudadResultadoHAL> _embedded) {
		this._embedded = _embedded;
	}
	
	
	
}


