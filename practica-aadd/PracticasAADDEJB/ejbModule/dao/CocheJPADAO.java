package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import modelo.Coche;
import modelo.Usuario;
import modelo.Viaje;

public class CocheJPADAO implements CocheDAO {

	private EntityManager em;

	public CocheJPADAO(EntityManager em) {

		this.em = em;
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		em.close();
	}

	@Override
	public Coche crearCoche(Usuario usuario, String matricula, String modelo, int year, int confort) {

		Coche coche = new Coche(matricula, modelo, confort, year, usuario);
		EntityTransaction tx = em.getTransaction();

		tx.begin();

		try {

			usuario.setCoche(coche);
			em.merge(usuario);
			em.persist(coche);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			return null;
		}

		return coche;

	}

	@Override
	public Coche anadirViaje(Coche coche, Viaje viaje) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			coche.anadirViaje(viaje);
			em.merge(coche);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			return null;
		}

		return coche;

	}

	@Override
	public Coche buscarCoche(String matricula) {

		Coche c = em.find(Coche.class, matricula);
		return c;

	}

	@Override
	public void borrarCoche(String matricula) {

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Coche c = buscarCoche(matricula);
		try {
			em.merge(c);
			em.remove(c);
			tx.commit();
		} catch (Exception e) {

			tx.rollback();

		}
	}

}
