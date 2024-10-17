package dao;

import java.util.Date;
import java.util.List;

import modelo.Usuario;

public interface UsuarioDAO {
	
	Usuario crearUsuario(String usuario, String clave, Date fechaNacimiento, String profesion, String email, String nombre,
			String apellidos);
	Usuario buscarUsuario(String usuario);
	void borrarUsuario(String usuario);
	List<Usuario> buscarUsuarios();

}
