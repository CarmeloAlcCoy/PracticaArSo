package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import modelo.Usuario;
import modelo.Viaje;

public class ViajeJPADAO implements ViajeDAO {

	private final static String CONSULTAJPQL = 
			"SELECT v " 
			+ "FROM Viaje v " 
					+ "JOIN Parada o ON o=v.origen "
					+ "JOIN Parada d ON d=v.destino " 
			+ "WHERE o.fecha >= :fechaIni AND o.fecha <= :fechaFin " 
			+ "ORDER BY o.";

	private final static String CONSULTAJPQLUSER = 
			"SELECT DISTINCT v " 
			+ "FROM Viaje v "
				+ "JOIN Parada o ON o=v.origen " 
				+ "JOIN Parada d ON d=v.destino " 
				+ "JOIN Reserva r ON r.viaje=v "
			+ "WHERE o.fecha >= :fechaIni AND o.fecha <= :fechaFin " 
			+ "AND :user = r.usuario " + "ORDER BY o.";
	
	@SuppressWarnings("unused")
	private final static String sqlNativo = "SELECT v.* " + "FROM ViajeCocheJPA v "
			+ "JOIN PARADA o ON o.ID=v.ORIGEN_ID " + "JOIN PARADA d ON d.ID=v.DESTINO_ID "
			+ "WHERE o.FECHA >= #fechaIni AND o.FECHA <= #fechaFin " + "ORDER BY o.";

	private EntityManager em;

	public ViajeJPADAO(EntityManager em) {

		this.em = em;
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		em.close();
	}

	@Override
	public Viaje buscarViaje(int id) {
		Viaje viaje = em.find(Viaje.class, id);
		return viaje;
	}

	@Override
	public Viaje crearViaje(Usuario conductor, int plazas, double precio) {
		ArrayList<String> notas = null;// new
		// ArrayList<String>(Arrays.asList("Nota1","Nota2"));
		Viaje viaje = new Viaje(conductor, plazas, precio, notas);
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//Coger el coche del usuario actual en el controlador y añadirle este viaje a su coche
		try {
			
			em.persist(viaje);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			return null;
		}

		return viaje;

	}

	@Override
	public void borrarViaje(int id) {

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Viaje v = buscarViaje(id);
		try {
			em.merge(v);
			em.remove(v);
			tx.commit();
		} catch (Exception e) {

			tx.rollback();

		}

	}

	@Override
	public List<Viaje> buscarViajes(Usuario usuario, Date fechaIni, Date fechaFin, String orden) {
		Query query;
		if(usuario == null) {
			query = em.createQuery(CONSULTAJPQL + orden);
		}else {
			query = em.createQuery(CONSULTAJPQLUSER + orden);
			query.setParameter("user", usuario);
		}
		
		query.setParameter("fechaIni", fechaIni);
		query.setParameter("fechaFin", fechaFin);
		

		@SuppressWarnings("unchecked")
		List<Viaje> viajes = (List<Viaje>) query.getResultList();
		List<Viaje> resultados = new ArrayList<Viaje>();
		for (Viaje viaje : viajes) {
			Viaje v = new Viaje(viaje.getConductor(),viaje.getPlazas(), viaje.getPlazas(), viaje.getNotas());
			v.setOrigen(v.getOrigen());
			v.setDestino(viaje.getDestino());
			resultados.add(v);
		}
		viajes.size();
		return viajes;
	}

	@Override
	public List<Viaje> findAll() {

		TypedQuery<Viaje> query = em.createNamedQuery("Viajes.findAll", Viaje.class);
		List<Viaje> viajes = (List<Viaje>) query.getResultList();
		return viajes;

	}

}
