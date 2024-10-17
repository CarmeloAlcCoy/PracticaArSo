package dao;

import java.util.Date;
import modelo.Parada;
import modelo.Viaje;

public interface ParadaDAO {
	
	Parada anadirParadaOrigen(Viaje viaje, String ciudad, String calle, int cp, Date fecha);
	Parada anadirParadaDestino(Viaje viaje, String ciudad, String calle, int cp, Date fecha);
	Parada buscarParada(int id);
	void borrarParada(int id);
}
