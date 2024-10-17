package controladorEJB3;

import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
import modelo.Coche;
import modelo.Parada;
import modelo.Reserva;
import modelo.Usuario;
import modelo.Viaje;

@Remote
public interface ControladorBlaBlaCarRemote {
	
	public Usuario existeUsuario(String usuario);
	public Usuario registroUsuario(String usuario, String clave, Date fechaNacimiento, String profesion, String email,
			String nombre, String apellidos);
	public boolean loginUsuario(String usuario, String clave);
	public Usuario getUsuarioActual();
	public void setUsuarioActual(Usuario usuario);
	public void borrarUsuario(String usuario);
	public Coche anadirCoche(String matricula, String modelo, int year, int confort);
	public void borrarCoche(String matricula);
	public Coche buscarCoche(String matricula);
	public Viaje registrarViaje(int plazas, double precio);
	public Viaje buscarViaje(int id);
	public void borrarViaje(int id);
	public void borrarParada(int id);
	public Parada registrarParadaOrigen(int idViaje, String ciudad, String calle, int cp, Date fecha);
	public Parada registrarParadaDestino(int idViaje, String ciudad, String calle, int cp, Date fecha);
	public Reserva buscarReserva(int id);
	public void borrarReserva(int id);
	public Reserva reservarViaje(int idViaje,String comentario);
	public boolean aceptarViaje(int idViaje, String usuario);
	public boolean rechazarViaje(int idViaje, String usuario);
	public List<Usuario> recuperarUsuarios();
	public List<Viaje> listarViajes(String user, boolean pendientes, boolean realizados, boolean propios,
			boolean ordenFecha, boolean ordenCiudad);
}
