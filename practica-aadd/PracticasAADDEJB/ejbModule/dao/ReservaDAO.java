package dao;

import modelo.Reserva;
import modelo.Usuario;
import modelo.Viaje;

public interface ReservaDAO {
	
	Reserva buscarReserva(int id);
	Reserva crearReserva(Usuario usuario, Viaje viaje,String comentario );
	//Se necesita para las operaciones aceptarViaje y rechazar viaje del controlador
	Reserva actualiarEstadoReserva(Reserva reserva);
	void borrarReserva(int id);

}
