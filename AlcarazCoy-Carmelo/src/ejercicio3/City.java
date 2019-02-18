package ejercicio3;

public class City {
	
	private String name;
	private int id;
	private String country;
	private double latitude;
	private double longitude;
	
	public City() {
		super();
	}
	
	public City(String name, int id, String country, double latitude, double longitude) {
		this();
		this.name = name;
		this.id = id;
		this.country = country;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public City(City ciudad) {
		this(ciudad.name, ciudad.id, ciudad.country,ciudad.latitude,ciudad.longitude);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "[name=" + name + ", id=" + id + ", country=" + country + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", uri=" + getUri() + "]";
	}
	
	

}
