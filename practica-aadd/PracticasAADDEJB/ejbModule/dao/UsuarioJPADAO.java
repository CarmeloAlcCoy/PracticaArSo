package dao;

import java.util.Date;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import modelo.Usuario;

public class UsuarioJPADAO implements UsuarioDAO {

	private EntityManager em;

	public UsuarioJPADAO(EntityManager em) {

		this.em = em;
	}

	@Override
	public Usuario crearUsuario(String usuario, String clave, Date fechaNacimiento, String profesion, String email,
			String nombre, String apellidos) {

		Usuario c = new Usuario(usuario, clave, fechaNacimiento, profesion, email, nombre, apellidos);

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			em.persist(c);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			return null;
		}

		return c;

	}

	@Override
	public Usuario buscarUsuario(String usuario) {
		if (usuario == null) return null;
		Usuario u = em.find(Usuario.class, usuario);
		return u;

	}
	
	@Override
	public void borrarUsuario(String usuario){
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try{
			Usuario u = buscarUsuario(usuario);
			em.merge(u); 
			em.remove(u);
			tx.commit();
			
		}catch(Exception e){
			
			tx.rollback();
			
		}
		
		
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		em.close();
	}

	@Override
	public List<Usuario> buscarUsuarios() {
		
		TypedQuery<Usuario> query = em.createNamedQuery("Usuario.findAll", Usuario.class);
		List<Usuario> usuarios = query.getResultList();
		return usuarios;
		
	}

	

}
