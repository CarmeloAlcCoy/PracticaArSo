package mvc;

import javax.servlet.http.HttpServletRequest;

public class PeticionHelper {

	private HttpServletRequest peticion;

	public PeticionHelper(HttpServletRequest peticion) {

		this.peticion = peticion;

	}

	public boolean necesitaValidacion() {
		return false;
	}

	public Accion getAccion() {
		// Analiza la URI para determinar la acción a realizar
		String uri = peticion.getRequestURI();
		String accionKey;
		

		// Obtiene la cadena entre la última "/" y ".ctrl"
		int posIni = uri.lastIndexOf("/");
		int posFin = uri.lastIndexOf(".");
		
		
		if(posFin < 0) {
			accionKey="Inicio";
		}
		else {
			accionKey = uri.substring(posIni + 1, posFin);
			// Convertimos el primer caracter a Mayuscula
			if (Character.isLowerCase(accionKey.charAt(0)))
				accionKey = accionKey.toUpperCase().charAt(0) + accionKey.substring(1, accionKey.length()).toLowerCase();
		}

		

		// Recupera el nombre de la clase que representa la acción
		// del fichero de propiedades
		Accion acc = null;
		try {
			Class<?> claseAccion = Class.forName("mvc.Accion" + accionKey);
			acc = (Accion) claseAccion.newInstance();

		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			return null;
		}
		return acc;
	}

}
