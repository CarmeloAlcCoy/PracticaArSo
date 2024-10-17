package soap;

import javax.jws.WebService;

@WebService(endpointInterface = "soap.Saludo")
public class SaludoImpl implements Saludo {
	
	@Override
	public String getSaludo(String nombre) {
		return "Hola " + nombre;
	}
	
}