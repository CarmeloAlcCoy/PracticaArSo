package servicio.clases;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "listadoCiudades")
public class ListadoCiudades {
	
	@XmlElement(name="resultado")
	private List<CiudadResultado> resultados;

	public List<CiudadResultado> getResultados() {
		return resultados;
	}

	public void setResultados(List<CiudadResultado> resultados) {
		this.resultados = resultados;
	}
	
	public void print() {
		
		for (CiudadResultado city : resultados) {
			System.out.println(city);
		}
		
	}

}
