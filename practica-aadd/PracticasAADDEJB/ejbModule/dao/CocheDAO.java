package dao;

import modelo.Coche;
import modelo.Usuario;
import modelo.Viaje;

public interface CocheDAO {
	
	Coche crearCoche(Usuario usuario, String matricula, String modelo, int year, int confort);
	Coche anadirViaje(Coche coche, Viaje viaje);
	Coche buscarCoche(String matricula);
	void borrarCoche(String matricula);

}
