package cliente;

import ejercicio2_2.ExceptionConversion;
import ejercicio2_2.StringConversion;
import ejercicio2_2.StringConversionImplService;

// Bien

public class Programa {

	public static void main(String[] args) {
		
		StringConversionImplService servicio = new StringConversionImplService();
		StringConversion puerto = servicio.getStringConversionImplPort();
		int respuesta;
		try {
			respuesta = puerto.string2Int("555");
			System.out.println("El número asociado a 555 es "+respuesta);
			respuesta = puerto.string2Int("5.2");
		} catch (ExceptionConversion e) {
			System.err.println(e.getFaultInfo());
			e.printStackTrace();
		}
		
		
	}

}
