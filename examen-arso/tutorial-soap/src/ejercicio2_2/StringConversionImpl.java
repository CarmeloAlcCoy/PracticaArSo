package ejercicio2_2;

import javax.jws.WebService;

@WebService(endpointInterface = "ejercicio2_2.StringConversion")
public class StringConversionImpl implements StringConversion {

	@Override
	public int string2int(String str) throws ExceptionConversion {
		int i;
		try {
			i=Integer.parseInt(str);
		} catch (NumberFormatException e) {
			throw new ExceptionConversion("Not an integer");
		}
		return i;
	}

}
