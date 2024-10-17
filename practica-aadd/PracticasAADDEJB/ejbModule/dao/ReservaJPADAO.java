package dao;

import javax.persistence.EntityManager;

import javax.persistence.EntityTransaction;

import modelo.Reserva;
import modelo.Usuario;
import modelo.Viaje;

public class ReservaJPADAO implements ReservaDAO {

	private EntityManager em;

	public ReservaJPADAO(EntityManager em) {

		this.em = em;
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		em.close();
	}

	@Override
	public Reserva buscarReserva(int id) {
		Reserva reserva = em.find(Reserva.class, id);
		return reserva;

	}

	public Reserva crearReserva(Usuario usuario, Viaje viaje, String comentario) {
		Reserva reserva = viaje.reservar(usuario, comentario);
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			em.merge(usuario);
			em.merge(viaje);
			em.persist(reserva);
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			return null;
		}

		return reserva;
	}

	@Override
	public Reserva actualiarEstadoReserva(Reserva reserva) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			em.merge(reserva);
			reserva.setEstado(reserva.getEstado());
			tx.commit();

		} catch (Exception e) {
			tx.rollback();
			return null;
		}

		return reserva;
	}

	@Override
	public void borrarReserva(int id) {

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Reserva r = buscarReserva(id);
		try {
			em.merge(r);
			em.remove(r);
			tx.commit();
		} catch (Exception e) {

			tx.rollback();

		}
	}

}
