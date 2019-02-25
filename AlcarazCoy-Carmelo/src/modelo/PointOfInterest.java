package modelo;

public class PointOfInterest {

	private String name;
	private int id;
	
	public PointOfInterest(String name, int id) {
		super();
		this.name = name;
		this.id = id;
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

	@Override
	public String toString() {
		return "PointOfInterest [name=" + name + ", id=" + id + "]";
	}
	
		
}
