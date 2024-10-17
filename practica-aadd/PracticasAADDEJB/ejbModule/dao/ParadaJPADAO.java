package dao;

import java.util.Date;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import modelo.Parada;
import modelo.Viaje;

public class ParadaJPADAO implements ParadaDAO {

	private EntityManager em;

	public ParadaJPADAO(EntityManager em) {

		this.em = em;
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		em.close();
	}

	@Override
	public Parada anadirParadaOrigen(Viaje viaje, String ciudad, String calle, int cp, Date fecha) {
		Parada parada = new Parada(ciudad, fecha,calle,cp);
		
		
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			em.persist(parada);
			viaje.setOrigen(parada);
			em.merge(viaje);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			return null;
		}

		return parada;
	}

	@Override
	public Parada anadirParadaDestino(Viaje viaje, String ciudad, String calle, int cp, Date fecha) {
		Parada parada = new Parada(ciudad, fecha,calle,cp);

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			em.persist(parada);
			viaje.setDestino(parada);
			em.merge(viaje);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			return null;
		}

		return parada;
	}
	
	@Override
	public Parada buscarParada(int id) {
		
		Parada parada = em.find(Parada.class, id);
		return parada;
		
	}

	@Override
	public void borrarParada(int id) {
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Parada p = buscarParada(id);
		em.merge(p);
		em.remove(p);
		tx.commit();
		
	}

}
