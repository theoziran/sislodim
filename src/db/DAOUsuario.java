package db;

import businesslogic.Usuario;

public class DAOUsuario extends DAOFactory<Usuario> {
	public DAOUsuario() {
		super();
	}
	
	public Usuario find(int chave){
		return super.find(Usuario.class, chave);
	}
	
	
	
}
