package bookle.prueba;

import java.util.Date;

import bookle.controlador.BookleControlador;
import bookle.controlador.BookleControladorImpl;

public class Prueba {

	public static void main(String[] args) {
		BookleControlador controlador = BookleControladorImpl.getUnicaInstancia();
		String id1 = controlador.createActividad("Activdad1", "Una actividad de prueba",
				"Carmelo", null);
		String id2 = controlador.createActividad("Activdad2", "Actividad sin turno",
				"Juan", "carmelo.alcarz@um.es");
		
		controlador.updateActividad(id2, "Activdad2", "Actividad modificada sin turno",
					"Juan", "carmelo.alcarz@um.es");
		
		System.out.println(controlador.getActividad(id2).getDescripcion());
		 
		String id5 = controlador.createActividad("Activdad5", "Esta actividad no debe aparecer",
					"Carmelo", null);
		controlador.removeActividad(id5);
		
		controlador.addDiaActividad(id1, new Date(), 5);
		Date fecha = new Date();
		controlador.addDiaActividad(id2, fecha, 5);
		controlador.removeDiaActividad(id2, fecha);
		
		 
		
		System.out.println("Done");
		
	}
}
