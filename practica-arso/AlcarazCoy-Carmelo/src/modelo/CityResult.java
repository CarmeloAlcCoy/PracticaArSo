package modelo;

public class CityResult {
	
	private int id;
	private String name;
	private String country;
	private double latitude;
	private double longitude;
	
	

	public CityResult(int id, String name, String country, double latitude, double longitude) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public CityResult() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getUri() {
		return "http://sws.geonames.org/"+id+"/about.rdf";
	}
	
	public String toString() {
		
		return "[name=" + name + ", id=" + id + ", country=" + country + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", uri=" + getUri() + "]";
	}

	

}
