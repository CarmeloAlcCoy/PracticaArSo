package dao;


import java.util.Date;
import java.util.List;
import modelo.Usuario;
import modelo.Viaje;

public interface ViajeDAO {
	
	Viaje buscarViaje(int id);
	Viaje crearViaje(Usuario conductor,int plazas, double precio);
	void borrarViaje(int id);
	List<Viaje> buscarViajes(Usuario usuario, Date fechaIni, Date fechaFin,String orden);
	public List<Viaje> findAll();
	
}
