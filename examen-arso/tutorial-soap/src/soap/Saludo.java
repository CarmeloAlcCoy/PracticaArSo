package soap;

import javax.jws.WebService;

@WebService
public interface Saludo {

	String getSaludo(String nombre);

}