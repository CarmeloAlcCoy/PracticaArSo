package pruebas;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import controladorEJB3.ControladorBlaBlaCar;
import modelo.Coche;
import modelo.Reserva;
import modelo.Usuario;
import modelo.Viaje;

public class Tests {

	static ControladorBlaBlaCar controlador;
	static java.util.Date fecha;
	static String username = "CarmeloCoy";
	static String matricula = "8053 CMK";
	public static Usuario user;
	public static Usuario user2;
	public static Coche coche;
	public static Viaje viaje;
	public static Reserva reserva;

	@BeforeClass
	public static void inicilize() {
		controlador = ControladorBlaBlaCar.getInstancia();
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
		fecha = null;
		try {
			fecha = formatoDelTexto.parse("25/12/1996");
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		
		user = controlador.registroUsuario(username, "asdf", fecha, "Estudiante", "carmelo.alcaraz@um.es", "Carmelo",
				"Alcaraz Coy");
		user2 = controlador.registroUsuario("Nico", "asdf", fecha, "Estudiante", "nico.alcaraz@um.es", "Carmelo",
				"Nico Coy");
		coche = controlador.anadirCoche( matricula, "Mercedes", 1986, 4);

		viaje = controlador.registrarViaje( 3, 2.5);

		java.util.Date fechaViaje = null;
		java.util.Date fechaViaje2 = null;
		try {
			fechaViaje = formatoDelTexto.parse("04/11/2018");
			fechaViaje2 = formatoDelTexto.parse("05/11/18");
		} catch (ParseException ex) {
			ex.printStackTrace();
		}

		controlador.registrarParadaOrigen(viaje.getId(), "Murcia", "C/Mayor,25", 30001, fechaViaje);
		controlador.registrarParadaDestino(viaje.getId(), "Cartagena", "Plaza de España", 30201, fechaViaje2);
		reserva = controlador.reservarViaje(viaje.getId(),  "Test");
		System.out.println(reserva);

	}
	
	@AfterClass
	public static void finalice() {
		controlador.borrarReserva(reserva.getId());
		controlador.borrarUsuario(user2.getUsuario());
		
		controlador.borrarUsuario(user.getUsuario());
		controlador.borrarCoche(coche.getMatricula());
		controlador.borrarViaje(viaje.getId());
	}

	@Test
	public void testRegistroUsuario() {
		assertEquals(user, controlador.existeUsuario(user.getUsuario()));
	}

	// Las pruebas del metodo existeUsuario se omiten ya que este método queda
	// probado
	// al hacer los tests del método login

	@Test
	public void testLoginUsuarioExiste() {

		boolean bool = controlador.loginUsuario(user.getUsuario(), user.getClave());
		assertEquals(true, bool);

	}

	@Test
	public void testBorrarUsuario() {

		Usuario user1 = controlador.registroUsuario("Juan", "asdf", fecha, "Estudiante", "carmelo.alcaraz@um.es",
				"Carmelo", "Alcaraz Coy");
		controlador.borrarUsuario(user1.getUsuario());

		assertNull(controlador.existeUsuario(user1.getUsuario()));

	}

	@Test
	public void testLoginUsuarioNoExiste() {

		boolean bool = controlador.loginUsuario("Paco", user.getClave());
		assertEquals(false, bool);
	}

	@Test
	public void testLoginUsuarioNull() {

		boolean bool = controlador.loginUsuario(null, user.getClave());
		assertEquals(false, bool);

	}

	@Test
	public void testLoginUsuarioExistePasswdErronea() {

		boolean bool = controlador.loginUsuario(user.getUsuario(), "Patata");
		assertEquals(false, bool);
	}

	@Test
	public void testLoginPasswdNull() {
		boolean bool = controlador.loginUsuario(user.getUsuario(), null);
		assertEquals(false, bool);

	}

	@Test
	public void testAnadirCoche() {

		assertEquals(user.getCoche(), controlador.buscarCoche(coche.getMatricula()));

	}

	@Test
	public void testAnadirCoche2() {

		assertEquals(controlador.existeUsuario(user.getUsuario()).getCoche(), coche);

	}

	@Test
	public void testRegistroViaje() {

		assertEquals(viaje, controlador.buscarViaje(viaje.getId()));

	}

	@Test
	public void testRegistroViajeParada() {

		assertEquals(reserva, controlador.buscarViaje(viaje.getId()).getReservas().get(0));

	}

	@Test
	public void testReservarViaje() {

		assertEquals(1, controlador.buscarViaje(viaje.getId()).getReservas().size());

	}

	

	@Test
	public void testListarViajes() {
		List<Viaje> viajes = controlador.listarViajes(username, false, false, true, true, false);
		assertEquals(viajes.size(), 1);
	}
	
	@Test
	public void testListarViajes1() {
		List<Viaje> viajes = controlador.listarViajes(null, false, false, false, true, false);
		assertEquals(viajes.size(), 1);
	}
	
	@Test
	public void testListarViajes2() {
		List<Viaje> viajes = controlador.listarViajes("Nico", false, false, false, true, false);
		assertEquals(viajes.size(), 1);
	}

}
