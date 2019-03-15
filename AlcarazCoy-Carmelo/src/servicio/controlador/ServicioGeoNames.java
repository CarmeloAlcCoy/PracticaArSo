package servicio.controlador;

import java.io.File;

public class ServicioGeoNames {
	
	private static ServicioGeoNames unicaInstancia;
	
	static {        	
	  	File folder = new File("xml-bd");        	
	  	if (! folder.exists())
	  	  	folder.mkdir();
	}

	
	private ServicioGeoNames() {
		
	}

	public static ServicioGeoNames getUnicaInstancia() {
		if(unicaInstancia==null) unicaInstancia = new ServicioGeoNames();
		return unicaInstancia;
	}
	
	
	public void buscar(String busqueda) {
		
	}
	
	public void getCiudad(String idGeoNames) {
		
	}

}
