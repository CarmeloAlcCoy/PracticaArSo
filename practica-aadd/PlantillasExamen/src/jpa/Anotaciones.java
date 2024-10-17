package jpa;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class Anotaciones {
	// @Entity
	// @Table(name="NOMBRE")

	// @Id
	// @GeneratedValue.(SEQUENCE o TABLE o AUTO )

	// @Column(name="Nombre")
	// @Basic(fetch = FecthType.(Lazy o EAGER) Por defecto Eager
	// @Enumerated //Se puede forzar a la bbdd a utilizar columna de texto
	// (EnumType.STRING)
	// @Transient para atributos calculados

	// Colleccione aplicar siempre List o Collection
	// @ElementCollection(fecth= ...)
	// @CollectionTable(name = "NOMBRE")

	// @Embeddable
	// @Embedded

	// ***Asociaciones***
	// @OneToOne
	// Bidireccional -> (mappdBy = "nombre_atributo") asociacion de la parte no
	// dueña
	// @OrderBy()
	// @OneToMany
	// Bidireccional mappedBy en el OneToMany en el otro lado @ManyToOne
	// @ManyToMany mappedBy en uno de ellos

	// **Herencia**
	// @Inheritance(strategy = ...) en la padre
	// Una tabla -> InheritanceType.SINGLE_TABLE
	// @DiscriminatorValue("Algo") en las hijas

	// Una tabla por clase -> InheritanceType.JOINED

	// Una tabla por subclase -> InheritanceType.TABLE_PER_CLASS

	EntityManagerFactory emf;
	EntityManager em;

	String crearUnaConexion() {
		emf = Persistence.createEntityManagerFactory("examenAADD");

		synchronized (emf) {

			em = emf.createEntityManager();

		}

		EntityTransaction tx = em.getTransaction();

		tx.begin();

		try {
			em.merge("Una clase");
			em.persist("Otra clase");
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			return null;
		}

		return "";
	}

	public List<String> buscarUsuarios() {
		// @NamedQuery(name = "NOMBRE_CLASE.nombre_consulta", query= "SELECT u FROM
		// Usuario u")
		TypedQuery<String> query = em.createNamedQuery("NOMBRE_CLASE.nombre_consulta", String.class);
		List<String> usuarios = query.getResultList();
		return usuarios;

	}

	private final static String CONSULTAJPQL = "SELECT v " + "FROM Viaje v " + "JOIN Parada o ON o=v.origen "
			+ "JOIN Parada d ON d=v.destino " + "WHERE o.fecha >= :fechaIni AND o.fecha <= :fechaFin " + "ORDER BY o.";

	public List<String> consultaJPQL() {
		Query query = em.createQuery(CONSULTAJPQL);
		
		query.setParameter("fechaIni", new Date());
		query.setParameter("fechaFin", new Date());

		@SuppressWarnings("unchecked")
		List<String> viajes = (List<String>) query.getResultList();
		return viajes;
	}

	@Override
	protected void finalize() throws Throwable {
		em.close();
		emf.close();
		super.finalize();

	}
}
