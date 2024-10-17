package dao;

import javax.ejb.Stateless;

@Stateless(name="Factoria")
public class FactoriaDAO implements FactoriaDAOLocal {
	
	protected FactoriaDAO factoria = null;
	
	public static final int JPA = 0;
	public static final int JDBC = 1;
	
	public UsuarioDAO getUsuarioDAO(){
		
		return factoria.getUsuarioDAO();
		
	}
	public CocheDAO getCocheDAO(){
		
		return factoria.getCocheDAO();
		
	}
	public ParadaDAO getParadaDAO(){
		
		return factoria.getParadaDAO();
		
	}
	public ReservaDAO getReservaDAO(){
		
		return factoria.getReservaDAO();
		
	}
	public ViajeDAO getViajeDAO(){
		
		return factoria.getViajeDAO();
		
	}

	@Override
	public void setFactoriaDAO(int tipo) {
		
		switch (tipo) {
		case JPA:{
			
			factoria = new FactoriaJPADAO();
			break;
		}

		default:
			break;
		}
		
	}
	
}
