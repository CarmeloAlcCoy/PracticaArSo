package examen2018.client;

import notas.ServicioNotas;
import notas.ServicioNotasException;
import notas.ServicioNotasImplService;

public class Programa {

	public static void main(String[] args) {
		
		ServicioNotasImplService servicio = new ServicioNotasImplService();
		ServicioNotas puerto = servicio.getServicioNotasImplPort();
		String clave = puerto.getClaveUso();
		
		System.out.println("La clave es " + clave);
		
		try {
			puerto.addNota(clave, "Recordatorio0");
			puerto.addNota(clave, "Recordatorio1");
		} catch (ServicioNotasException e) {
			System.out.println("No se puedo crear la nota");
		}

		try {
			for (String str : puerto.getNotas(clave)) {
				System.out.println(str);
			}
		} catch (ServicioNotasException e) {
			System.out.println("La clave no existe");
		}
		
		try {
			puerto.removeNota(clave, 1);
			puerto.removeNota(clave, 5);
		} catch (ServicioNotasException e) {
			System.out.println("La nota 5 no existe");
		}
		
		try {
			for (String str : puerto.getNotas(clave)) {
				System.out.println(str);
			}
		} catch (ServicioNotasException e) {
			System.out.println("La clave no existe");
		}
		
		try {
			for (String str : puerto.getNotas("Juan")) {
				System.out.println(str);
			}
		} catch (ServicioNotasException e) {
			System.err.println("La clave no existe");
		}
	}

}
