package servicio.clases;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "feed")
public class ListadoCiudadesJSON {

	private String name;
	private String country;
	private double latitude;
	private double longitude;
	
	
	public ListadoCiudadesJSON() {
		super();

	}

	public ListadoCiudadesJSON(String name, String country, double latitude, double longitude) {
		this();
		this.name = name;
		this.country = country;
		this.latitude = latitude;
		this.longitude = longitude;
	}



	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public double getLatitude() {
		return latitude;
	}


	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}


	public double getLongitude() {
		return longitude;
	}


	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	
}


