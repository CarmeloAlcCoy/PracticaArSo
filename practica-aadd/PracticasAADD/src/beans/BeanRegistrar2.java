package beans;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import controladorEJB3.ControladorBlaBlaCar;

@ManagedBean(name = "beanRegistrar2")
@SessionScoped
public class BeanRegistrar2 {
	
	@Size(min = 3, max = 12)
	private String nombre;
	@Size(min = 1, max = 30)
	private String apellidos;
	@Size(min = 1, max = 20)
	private String profesion;
	@Pattern(regexp="[\\w\\.]+@\\w+\\.\\w+", message = "{email.pattern}")
	private String correo;
	@Size(min = 4, max = 12)
	private String usuario;
	@Size(min = 8, max = 15)
	private String clave;
	@Size(min = 8, max = 15)
	private String clave2;
	@NotNull
	private Date fechaNacimiento;

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getClave2() {
		return clave2;
	}

	public void setClave2(String clave2) {
		this.clave2 = clave2;
	}

	public String getProfesion() {
		return profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String registro() {
		java.util.Date fecha = new java.util.Date(fechaNacimiento.getTime());
		if (clave.equals(clave2)) {
			if (ControladorBlaBlaCar.getInstancia().registroUsuario(usuario, clave, fecha, profesion, correo, nombre,
					apellidos) != null)
				return "registroCorrecto";
		}
		setNombre(new String());
		setApellidos(new String());
		setProfesion(new String());
		setCorreo(new String());
		setUsuario(new String());
		setApellidos(new String());
		setFechaNacimiento(new Date());
		return "faceletsFallo";
	}
}