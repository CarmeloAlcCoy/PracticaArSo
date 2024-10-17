package cliente;

import soap.Saludo;
import soap.SaludoImplService;

public class ProgramaCliente {
	
	public static void main(String[] args) {
		SaludoImplService servicio = new SaludoImplService();
		Saludo puerto = servicio.getSaludoImplPort(); // SEI
		String respuesta = puerto.getSaludo("Pepe");
		System.out.println(respuesta);
	}
	
}