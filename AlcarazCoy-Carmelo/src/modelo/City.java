package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class City {
	
	private String name;
	private int id;
	private String country;
	private int population;
	private double latitude;
	private double longitude;
	private String urlDBpedia;
	private String urlWikipedia;
	private Date updatedDate;
	private MeteoInfo meteoInfo;
	private List<InterestPlace> pointsOfInterest;
	
	
	public City() {
		super();
		pointsOfInterest= new ArrayList<InterestPlace>();
	}
	
	public City(String name, int id, String country, double latitude, double longitude) {
		this();
		this.name = name;
		this.id = id;
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
	

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public String getUrlDBpedia() {
		return urlDBpedia;
	}

	public void setUrlDBpedia(String urlDBpedia) {
		this.urlDBpedia = urlDBpedia;
	}

	public String getUrlWikipedia() {
		return urlWikipedia;
	}

	public void setUrlWikipedia(String urlWikipedia) {
		this.urlWikipedia = urlWikipedia;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public MeteoInfo getMeteoInfo() {
		return meteoInfo;
	}

	public void setMeteoInfo(MeteoInfo meteoInfo) {
		this.meteoInfo = meteoInfo;
	}

	public List<InterestPlace> getPointsOfInterest() {
		return pointsOfInterest;
	}

	public void addPointsOfInterest(List<InterestPlace> pointsOfInterest) {
		this.pointsOfInterest.addAll(pointsOfInterest);
	}

	@Override
	public String toString() {
		return "[name=" + name + ", id=" + id + ", country=" + country + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", uri=" + getUri() + "]";
	}

	public String toStringFull() {
		String toString = 
				"City [name=" + name + "\n"
				+ "id=" + id + "\n"
				+ "country=" + country + "\n"
				+ "population=" + population+ "\n"
				+ "latitude=" + latitude + "\n"
				+ "longitude=" + longitude + "\n"
				+ "urlDBpedia=" + urlDBpedia+ "\n"
				+ "urlWikipedia=" + urlWikipedia + "\n"
				+ "updatedDate=" + updatedDate + "\n"
				+ "meteoInfo=" + meteoInfo+ "\n"
				+ "pointsOfInterest=";
		for (InterestPlace pointOfInterest : pointsOfInterest) {
			toString.concat("\t"+pointOfInterest.toString()+"\n");
		}
		
		return toString + "Uri=" + getUri() + "]";
	}
	
	

}
