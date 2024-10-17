package ejercicio2_2;

import javax.jws.WebService;

@WebService
public interface StringConversion {

	public int string2int(String str) throws ExceptionConversion;
}
