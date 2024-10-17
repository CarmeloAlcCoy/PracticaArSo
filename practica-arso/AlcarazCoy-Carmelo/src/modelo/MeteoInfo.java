package modelo;

import java.util.Date;

public class MeteoInfo {
	
	private Date takenOn;
	private String stationName;
	private double temperature;
	private String clouds;
	
	public MeteoInfo() {
		super();
	}
	
	public MeteoInfo(Date takenOn, String stationName, double temperature, String clouds) {
		super();
		this.takenOn = takenOn;
		this.stationName = stationName;
		this.temperature = temperature;
		this.clouds = clouds;
	}

	public Date getTakenOn() {
		return takenOn;
	}

	public void setTakenOn(Date takenOn) {
		this.takenOn = takenOn;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public String getClouds() {
		return clouds;
	}

	public void setClouds(String clouds) {
		this.clouds = clouds;
	}

	@Override
	public String toString() {
		return "MeteoInfo [takenOn=" + takenOn + ", stationName=" + stationName + ", temperature=" + temperature
				+ ", clouds=" + clouds + "]";
	}
	
	
	
	
	

}
