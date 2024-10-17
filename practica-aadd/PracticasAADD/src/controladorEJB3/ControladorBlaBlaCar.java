package controladorEJB3;

import java.util.Date;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import modelo.Coche;
import modelo.Parada;
import modelo.Reserva;
import modelo.Usuario;
import modelo.Viaje;

public class ControladorBlaBlaCar  {

	private ControladorBlaBlaCarRemote blablacar;
	private static ControladorBlaBlaCar unicaInstancia = null;

	private ControladorBlaBlaCar() {

		try {
			blablacar = (ControladorBlaBlaCarRemote) new InitialContext()
					.lookup("java:global/PracticasAADDEJB/ControladorBlaBlaCarRemoto");
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	public static ControladorBlaBlaCar getInstancia() {

		if (unicaInstancia == null)
			unicaInstancia = new ControladorBlaBlaCar();

		return unicaInstancia;

	}

	public Usuario existeUsuario(String usuario) {

		return blablacar.existeUsuario(usuario);

	}

	public Usuario registroUsuario(String usuario, String clave, Date fechaNacimiento, String profesion, String email,
			String nombre, String apellidos) {
		return blablacar.registroUsuario(usuario, clave, fechaNacimiento, profesion, email, nombre, apellidos);

	}

	public boolean loginUsuario(String usuario, String clave) {

		return blablacar.loginUsuario(usuario, clave);

	}

	public void borrarUsuario(String usuario) {

		blablacar.borrarUsuario(usuario);

	}

	public Coche anadirCoche(String matricula, String modelo, int year, int confort) {

		return blablacar.anadirCoche(matricula, modelo, year, confort);

	}

	public void borrarCoche(String matricula) {

		blablacar.borrarCoche(matricula);

	}

	public Coche buscarCoche(String matricula) {

		return blablacar.buscarCoche(matricula);

	}

	public Viaje registrarViaje(int plazas, double precio) {

		return blablacar.registrarViaje(plazas, precio);

	}

	public Viaje buscarViaje(int id) {

		return blablacar.buscarViaje(id);

	}

	public void borrarViaje(int id) {

		blablacar.borrarViaje(id);

	}

	public void borrarParada(int id) {

		blablacar.borrarParada(id);

	}

	public Parada registrarParadaOrigen(int idViaje, String ciudad, String calle, int cp, Date fecha) {

		return blablacar.registrarParadaOrigen(idViaje, ciudad, calle, cp, fecha);

	}

	public Parada registrarParadaDestino(int idViaje, String ciudad, String calle, int cp, Date fecha) {

		return blablacar.registrarParadaDestino(idViaje, ciudad, calle, cp, fecha);

	}

	public Reserva buscarReserva(int id) {

		return blablacar.buscarReserva(id);

	}

	public void borrarReserva(int id) {

		blablacar.borrarReserva(id);

	}

	public Reserva reservarViaje(int idViaje, String comentario) {

		return blablacar.reservarViaje(idViaje, comentario);

	}

	public boolean aceptarViaje(int idViaje, String usuario) {

		return blablacar.aceptarViaje(idViaje, usuario);

	}

	public boolean rechazarViaje(int idViaje, String usuario) {

		return blablacar.rechazarViaje(idViaje, usuario);

	}

	public List<Usuario> recuperarUsuarios() {

		return blablacar.recuperarUsuarios();

	}

	public List<Viaje> listarViajes(String user, boolean pendientes, boolean realizados, boolean propios,
			boolean ordenFecha, boolean ordenCiudad) {
		return blablacar.listarViajes(user, pendientes, realizados, propios, ordenFecha, ordenCiudad);

	}

	
	public Usuario getUsuarioActual() {
		return blablacar.getUsuarioActual();
	}

	
	public void setUsuarioActual(Usuario usuario) {
		blablacar.setUsuarioActual(usuario);

	}
	
	
}
