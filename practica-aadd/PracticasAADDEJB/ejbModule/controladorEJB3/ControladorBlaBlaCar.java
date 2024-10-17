package controladorEJB3;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import dao.FactoriaDAO;
import dao.FactoriaDAOLocal;
import modelo.Coche;
import modelo.Parada;
import modelo.Reserva;
import modelo.Usuario;
import modelo.Viaje;

@Stateful(name="ControladorBlaBlaCarRemoto")
public class ControladorBlaBlaCar implements ControladorBlaBlaCarRemote {
	
	private Usuario usuarioActual;
	
	@EJB(beanName="Contador")
	private ContadorEJB contador;
	@EJB(beanName="Factoria")
	private FactoriaDAOLocal factoria;
	@Resource
	private SessionContext contexto; //No se usa
	
	@PostConstruct
	public void configurarBlaBlaCarEJB(){
		
		System.out.println("ControladorBlaBlaCar.configurarBlaBlaCarEJB()");
		factoria.setFactoriaDAO(FactoriaDAO.JPA);
		
	}
	@Override
	public Usuario existeUsuario(String usuario) {

		if (usuario != null) // ADDED
			return factoria.getUsuarioDAO().buscarUsuario(usuario);
		return null;

	}
	@Override
	public Usuario registroUsuario(String usuario, String clave, Date fechaNacimiento, String profesion, String email,
			String nombre, String apellidos) {
		return factoria.getUsuarioDAO().crearUsuario(usuario, clave, fechaNacimiento, profesion, email, nombre, apellidos);

	}
	@Override
	public boolean loginUsuario(String usuario, String clave) {
		Usuario usu = existeUsuario(usuario);

		if (usu != null && usu.getClave().equals(clave)){
			
			usuarioActual = usu;
			System.out.println("Login correctos por sesion:" + contador.siguienteValor()); 
			return true;
			
		}

		return false;
	}
	
	@Override
	public void borrarUsuario(String usuario) {

		factoria.getUsuarioDAO().borrarUsuario(usuario);

	}
	
	@Override
	public Coche anadirCoche(String matricula, String modelo, int year, int confort) {
		if (usuarioActual == null)
			return null;
		return factoria.getCocheDAO().crearCoche(usuarioActual, matricula, modelo, year, confort);

	}
	
	@Override
	public void borrarCoche(String matricula) {

		factoria.getCocheDAO().borrarCoche(matricula);

	}
	
	@Override
	public Coche buscarCoche(String matricula) {

		return factoria.getCocheDAO().buscarCoche(matricula);

	}

	@Override
	public Viaje registrarViaje(int plazas, double precio) {
		if (usuarioActual == null)
			return null;
		if (usuarioActual.getCoche() == null)
			return null;
		Viaje viaje = factoria.getViajeDAO().crearViaje(usuarioActual,plazas, precio);
		factoria.getCocheDAO().anadirViaje(usuarioActual.getCoche(), viaje);
		return viaje;
	}

	@Override
	public Viaje buscarViaje(int id) {

		return factoria.getViajeDAO().buscarViaje(id);

	}

	@Override
	public void borrarViaje(int id) {

		factoria.getViajeDAO().borrarViaje(id);

	}

	@Override
	public void borrarParada(int id) {

		factoria.getParadaDAO().borrarParada(id);

	}
	
	@Override
	public Parada registrarParadaOrigen(int idViaje, String ciudad, String calle, int cp, Date fecha) {
		Viaje viaje = factoria.getViajeDAO().buscarViaje(idViaje);
		if (viaje == null)
			return null;
		return factoria.getParadaDAO().anadirParadaOrigen(viaje, ciudad, calle, cp, fecha);
	}

	@Override
	public Parada registrarParadaDestino(int idViaje, String ciudad, String calle, int cp, Date fecha) {
		Viaje viaje = factoria.getViajeDAO().buscarViaje(idViaje);
		if (viaje == null)
			return null;
		return factoria.getParadaDAO().anadirParadaDestino(viaje, ciudad, calle, cp, fecha);
	}

	@Override
	public Reserva buscarReserva(int id) {

		return factoria.getReservaDAO().buscarReserva(id);

	}
	
	@Override
	public void borrarReserva(int id) {

		factoria.getReservaDAO().borrarReserva(id);

	}

	@Override
	public Reserva reservarViaje(int idViaje, String comentario) {
		Viaje viaje = factoria.getViajeDAO().buscarViaje(idViaje);
		if (viaje == null || usuarioActual == null)
			return null;
		
		return factoria.getReservaDAO().crearReserva(usuarioActual, viaje, comentario);

	}

	@Override
	public boolean aceptarViaje(int idViaje, String usuario) {
		Viaje viaje = factoria.getViajeDAO().buscarViaje(idViaje);
		Usuario usu = factoria.getUsuarioDAO().buscarUsuario(usuario);
		if (viaje == null || usu == null)
			return false;
		Reserva res = usu.aceptarReservaViaje(viaje);
		if (res == null)
			return false;
		return factoria.getReservaDAO().actualiarEstadoReserva(res) != null;
	}

	@Override
	public boolean rechazarViaje(int idViaje, String usuario) {
		Viaje viaje = factoria.getViajeDAO().buscarViaje(idViaje);
		Usuario usu = factoria.getUsuarioDAO().buscarUsuario(usuario);
		if (viaje == null || usu == null)
			return false;
		Reserva res = usu.rechazarReservaViaje(viaje);
		if (res == null)
			return false;
		return factoria.getReservaDAO().actualiarEstadoReserva(res) != null;
	}

	@Override
	public List<Usuario> recuperarUsuarios() {
		return factoria.getUsuarioDAO().buscarUsuarios();
	}

	/**
	 * La función devuelve los viajes cuyos campos coincidan
	 * 
	 * @param usuario
	 *            Si es distinto de null y existe un usuario con este nombre, los viajes debe ser realizados por el
	 *            usuario "usuario" como pasajero. Si no se devolverán los
	 *            viajes de cualquier usuario.
	 * @param pendientes
	 *            Si es true los viajes deben estar pendientes.
	 * @param realizados
	 *            Si es true los viajes deben estar realizados.
	 * @param propios
	 *            Si es true los viajes deben ser realizados por el "usuario"
	 *            como conductor. Si es null o no existe un usuario con nombre 
	 *            "usuario" la lista será vacia.
	 * @param ordenFecha
	 *            Si es true se ordenarán los viajes por fecha.
	 * 
	 * @param ordenCiudad
	 *            Si es true se ordenarán los viajes por la ciudad de origen. Si
	 *            es parámetro ordenFecha vale true prevalece este orden
	 * 
	 * @return Devuelve una lista de viajes que cumplan las opciones de
	 *         filtrado.
	 */

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private static final String FIN_DE_LOS_TIEMPOS = "01/01/3000";
	private static final String INICIO_DE_LOS_TIEMPOS = "01/01/2000";

	@Override
	public List<Viaje> listarViajes(String user, boolean pendientes, boolean realizados, boolean propios,
			boolean ordenFecha, boolean ordenCiudad) {
		Usuario usuario = factoria.getUsuarioDAO().buscarUsuario(user);
		// Aquí no debría haber ninguna lógica de negocio pero bueno...
		Date fechaIni = null, fechaFin = null;
		String orden = "fecha";

		try {
			fechaIni = sdf.parse(INICIO_DE_LOS_TIEMPOS);
			fechaFin = sdf.parse(FIN_DE_LOS_TIEMPOS);
		} catch (ParseException e) {
			// Nunca se alcanza este punto
		}

		if (pendientes) {
			fechaIni = new Date(System.currentTimeMillis());
		}
		if (realizados) {
			fechaFin = new Date(System.currentTimeMillis());
		}
		if (ordenCiudad) {
			orden="ciudad";
		}	
		
		if (propios) {
			if (usuario == null)
				return null;
			return usuario.filtrarViajes(fechaIni, fechaFin, orden);
		}

		if (usuario != null)
			return factoria.getViajeDAO().buscarViajes(usuario, fechaIni, fechaFin, orden);
		
		return factoria.getViajeDAO().buscarViajes(usuario, fechaIni, fechaFin, orden);
	}

	@Override
	public Usuario getUsuarioActual() {
		return usuarioActual;
	}

	@Override
	public void setUsuarioActual(Usuario usuario) {
		usuarioActual=usuario;
		
	}
}
