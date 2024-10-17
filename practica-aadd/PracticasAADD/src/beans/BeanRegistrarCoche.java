package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import controladorEJB3.ControladorBlaBlaCar;

@ManagedBean(name = "beanRegistrarCoche")
@SessionScoped
public class BeanRegistrarCoche {
	@Size(min = 1, max=30)
	private String modelo;
	@NotNull
	@Pattern(regexp="\\d{4}\\w{3}", message="{matricula.pattern}")
	private String matricula;
	@Range(min=1600, max=2018)
	private int year;
	@NotNull
	@Range(min=1, max=5)
	private int confort;

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public int getConfort() {
		return confort;
	}

	public void setConfort(int confort) {
		this.confort = confort;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String registrarCoche(String user) {
		if (ControladorBlaBlaCar.getInstancia().anadirCoche( matricula, modelo, year, confort) != null)
			return "miPerfil";
		return "faceletsFallo";
	}
}
