package bookle.controlador;

import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import bookle.tipos.Actividad;
import bookle.tipos.Actividad.Agenda;
import bookle.tipos.Actividad.Agenda.Turno;

public class BookleControladorImpl implements BookleControlador {

	private static BookleControlador unicaInstancia;
	private JAXBContext contexto;
	private BookleControladorImpl() throws BookleException {

		try {
			contexto = JAXBContext.newInstance("bookle.tipos");
			
		} catch (JAXBException e) {
			throw new BookleException("Could not instanciate marshaller");
		}

	}

	public Unmarshaller createUnmarshaller()  {
		try {
			return contexto.createUnmarshaller();
		} catch (JAXBException e) {
			throw new BookleException("Could not instanciate the Unmarshaller");
		}
	}

	public Marshaller createMarshaller() {
		Marshaller marshaller ;
		try {
			marshaller = contexto.createMarshaller();
			marshaller.setProperty("jaxb.formatted.output", true);
			marshaller.setProperty("jaxb.schemaLocation", "http://www.example.org/schema schema.xsd ");
		} catch ( JAXBException e) {
			throw new BookleException("Could not instanciate the Marshaller");
		} 
		return marshaller;
	}

	public static BookleControlador getUnicaInstancia() throws BookleException {
		if (unicaInstancia == null)
			unicaInstancia = new BookleControladorImpl();
		return unicaInstancia;
	}

	static {
		File folder = new File("actividad");
		if (!folder.exists())
			folder.mkdir();
	}

	@Override
	public String createActividad(String titulo, String descripcion, String profesor, String email)
			throws BookleException {
		Actividad actividad = new Actividad();
		actividad.setId(Utils.createId());
		if (titulo == null)
			throw new IllegalArgumentException("El parametro titulo no puede ser nulo");
		actividad.setTitulo(titulo);
		actividad.setDescripcion(descripcion);
		if (descripcion == null)
			throw new IllegalArgumentException("El parametro profesor no puede ser nulo");
		actividad.setProfesor(profesor);
		actividad.setEmail(email);
		
		Marshaller marshaller = createMarshaller();
		try {
			marshaller.marshal(actividad, new File("actividad/" + actividad.getId() + ".xml"));
		} catch (JAXBException e) {
			throw new BookleException("Could not create activity: titulo:"+titulo);
		}

		return actividad.getId();
	}

	@Override
	public void updateActividad(String id, String titulo, String descripcion, String profesor, String email)
			throws BookleException {

		File file = new File("actividad/"+id+".xml");
		if (!file.exists())
			throw new BookleException("Actividad con id "+id+" no existe.");
		Actividad actividad;
		Unmarshaller unmarshaller = createUnmarshaller();
		try {
			 actividad = (Actividad) unmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			throw new BookleException("could not marshall the Activity");
		}
		
		if (titulo == null)
			throw new IllegalArgumentException("El parametro titulo no puede ser nulo");
		actividad.setTitulo(titulo);
		actividad.setDescripcion(descripcion);
		if (profesor == null)
			throw new IllegalArgumentException("El parametro profesor no puede ser nulo");
		actividad.setProfesor(profesor);
		actividad.setEmail(email);

		Marshaller marshaller = createMarshaller();
		try {
			marshaller.marshal(actividad, new File("actividad/" + actividad.getId() + ".xml"));
		} catch (JAXBException e) {
			throw new BookleException("Could not create activity: titulo:"+titulo);
		}


	}

	@Override
	public Actividad getActividad(String id) throws BookleException {
		File file = new File("actividad/"+id+".xml");
		if (!file.exists())
			return null;
		Actividad actividad;
		Unmarshaller unmarshaller = createUnmarshaller();
		try {
			 actividad = (Actividad) unmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			throw new BookleException("could not marshall the Activity");
		}
		return actividad;
	}

	@Override
	public boolean removeActividad(String id) throws BookleException {
		File file = new File("actividad/"+id+".xml");
		return file.delete();
		
	}

	@Override
	public void addDiaActividad(String id, Date fecha, int turnos) throws BookleException {
		File file = new File("actividad/"+id+".xml");
		if (!file.exists())
			throw new BookleException("Actividad con id "+id+" no existe.");
		
		Actividad actividad;
		Unmarshaller unmarshaller = createUnmarshaller();
		try {
			 actividad = (Actividad) unmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			throw new BookleException("could not marshall the Activity");
		}
		
		
		Agenda agenda = new Agenda();
		agenda.setFecha(Utils.createFecha(fecha));
		
		Turno turno = new Turno();
		for (int i = 0; i < turnos; i++) {
			agenda.getTurno().add(turno);	
		}
		
		
		actividad.getAgenda().add(agenda);

		Marshaller marshaller = createMarshaller();
		try {
			marshaller.marshal(actividad, new File("actividad/" + actividad.getId() + ".xml"));
		} catch (JAXBException e) {
			throw new BookleException("Could not add date to activity:"+id);
		}


	}

	@Override
	public boolean removeDiaActividad(String id, Date fecha) throws BookleException {
		File file = new File("actividad/"+id+".xml");
		if (!file.exists())
			return false;
		
		Actividad actividad;
		Unmarshaller unmarshaller = createUnmarshaller();
		try {
			 actividad = (Actividad) unmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			throw new BookleException("could not marshall the Activity");
		}
		
		boolean borrado = false;
		
		for (Iterator<Agenda> it = actividad.getAgenda().iterator(); it.hasNext();) {
			Agenda a= (Agenda) it.next();
			if(a.getFecha().equals(Utils.createFecha(fecha))) {
				it.remove();
				borrado = true;
			}
		}
		
		Marshaller marshaller = createMarshaller();
		try {
			marshaller.marshal(actividad, new File("actividad/" + actividad.getId() + ".xml"));
		} catch (JAXBException e) {
			throw new BookleException("Could not add date to activity:"+id);
		}
		
		return borrado;
	}

	@Override
	public int addTurnoActividad(String id, Date fecha) throws BookleException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void removeTurnoActividad(String id, Date fecha, int turno) throws BookleException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setHorario(String idActividad, Date fecha, int indice, String horario) throws BookleException {
		// TODO Auto-generated method stub

	}

	@Override
	public String createReserva(String idActividad, Date fecha, int indice, String alumno, String email)
			throws BookleException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeReserva(String idActividad, String ticket) throws BookleException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public LinkedList<Actividad> getActividades() throws BookleException {
		// TODO Auto-generated method stub
		return null;
	}

}
