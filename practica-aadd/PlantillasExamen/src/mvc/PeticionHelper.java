package mvc;

import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class PeticionHelper {
	private static final String ACCION_PROPIEDADES = "/WEBINF/accion.properties";
	protected HttpServletRequest peticion;
	protected String dirAplicacion;

	public PeticionHelper(HttpServletRequest peticion, String dirAplicacion) {
		this.peticion = peticion;
		this.dirAplicacion = dirAplicacion;
	}

	public Accion getAccion() {
		// Analiza la URI para determinar la acción a realizar
		String uri = peticion.getRequestURI();
		// Obtiene la cadena entre la última "/" y ".ctrl"
		int posIni = uri.lastIndexOf("/");
		int posFin = uri.lastIndexOf(".");
		String accionKey = uri.substring(posIni + 1, posFin);
		// Recupera el nombre de la clase que representa la acción del fichero de propiedades
		Accion acc = null;
		try {
			InputStream is = new FileInputStream(dirAplicacion + ACCION_PROPIEDADES);
			Properties props = new Properties();
			props.load(is);
			String strClaseAccion = props.getProperty(accionKey);
			// Instacia el objeto Accion utilizando reflexión
			Class<?> claseAccion = Class.forName(strClaseAccion);
			acc = (Accion) claseAccion.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return acc;
	}

	public boolean necesitaValidacion() {
		//Se podría almacenar en un fichero los recursos que necesitan
		// validación y realizar la comprobación en este método.
		// Se omite su implementación
		return false;
	}

	public boolean errorNavegacion() {
		// Se podría almacenar en un fichero de propiedades las
		// relaciones de navegación entre los recursos y comprobarlas en
		// este método. Se omite su implementación
		return false;
	}
}
