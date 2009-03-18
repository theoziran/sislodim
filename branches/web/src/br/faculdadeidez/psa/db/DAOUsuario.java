package br.faculdadeidez.psa.db;

import java.util.List;

import br.faculdadeidez.psa.entity.Usuario;

public class DAOUsuario extends DAOFactory<Usuario> {
	public DAOUsuario() {
		super();
	}
	
	public Usuario find(int chave){
		return super.find(Usuario.class, chave);
	}
	
	public List<Usuario> findByField(String campo, String valor){
		return super.findByField(Usuario.class, campo, valor);
	}
	
	public List<Usuario> findAll(){
		return super.findAll(Usuario.class);
	}
	
	
}
