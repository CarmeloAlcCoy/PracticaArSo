package examen2018.soap;

public class Mensaje {

	private String remitente;
	private String destinatario;
	private String texto;

	
	public Mensaje() {
		super();
	}

	public Mensaje(String remitente, String destinatario, String texto) {
		super();
		this.remitente = remitente;
		this.destinatario = destinatario;
		this.texto = texto;
	}

	public String getRemitente() {
		return remitente;
	}

	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	@Override
	public String toString() {
		return "Mensaje [remitente=" + remitente + ", destinatario=" + destinatario + ", texto=" + texto + "]";
	}

}
