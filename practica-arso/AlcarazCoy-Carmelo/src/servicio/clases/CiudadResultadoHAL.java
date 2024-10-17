package servicio.clases;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {
	    "_links",
	    "name",
	    "country",
	    "latitude",
	    "longitude"
	})
public class CiudadResultadoHAL{

	private LinkHAL _links;
	private String name;
	private String country;
	private double latitude;
	private double longitude;
	
	
	public CiudadResultadoHAL() {
		super();

	}

	public CiudadResultadoHAL(String name, String country, double latitude, double longitude, String href) {
		this();
		this.name = name;
		this.country = country;
		this.latitude = latitude;
		this.longitude = longitude;
		this._links = new LinkHAL(href);
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

	public LinkHAL get_links() {
		return _links;
	}

	public void set_links(LinkHAL _links) {
		this._links = _links;
	}
	
	@XmlType
	public static class LinkHAL {

		private HRef self;
		
		public LinkHAL() {
			super();
		}

		public LinkHAL(String href) {
			this(new HRef(href));
		}
		
		public LinkHAL(HRef self) {
			this();
			this.self = self;
		}

		public HRef getSelf() {
			return self;
		}

		public void setSelf(HRef self) {
			this.self = self;
		}

		
	}

		

		@XmlType
		public static class HRef{
			private String href;

			public HRef() {
				super();
			}

			public HRef(String href) {
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

	