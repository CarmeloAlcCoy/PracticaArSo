package cliente;

import com.cdyne.ws.IP2Geo;
import com.cdyne.ws.IP2GeoSoap;
import com.cdyne.ws.IPInformation;

// Bien

public class Programa {

	public static void main(String[] args) {
		
		final String ip = "155.54.1.1";
		IP2Geo servicio = new IP2Geo();
		IP2GeoSoap puerto = servicio.getIP2GeoSoap();
		IPInformation respuesta = puerto.resolveIP(ip, "0");
		System.out.printf("La IP " +ip+ " se encuentra en "+respuesta.getCity()+"("+respuesta.getCountry()+")");

	}

}
