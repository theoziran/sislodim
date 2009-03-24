package br.faculdadeidez.psa.db.dao;

import java.util.List;

import javax.persistence.Query;

import br.faculdadeidez.psa.db.entity.Usuario;

public class DAOUsuario extends DAOFactory<Usuario> {
	public DAOUsuario() {
		super();
	}

	public Usuario find(int chave) {
		return super.find(Usuario.class, chave);
	}

	public List<Usuario> findByField(String campo, String valor) {
		return super.findByField(Usuario.class, campo, valor);
	}

	public List<Usuario> existsRg(String rg, String orgaoExpedidor){
		Query query = getManager().createQuery("select a from Usuario a where a.rg=\""+rg+"\" and a.orgExpeditor=\""+orgaoExpedidor+"\"");
		List<Usuario> result;
		result = query.getResultList();
		return  result;
	}

	public List<Usuario> findAll() {
		return super.findAll(Usuario.class);
	}

	public List<Usuario> findAllActive() {
		Query query = getManager().createQuery(
				"select a from Usuario a where a.ativo=1");
		List<Usuario> result;
		result = query.getResultList();
		return result;
	}

	public List<Usuario> findAllInactive() {
		Query query = getManager().createQuery(
				"select a from Usuario a where a.ativo=0");
		List<Usuario> result;
		result = query.getResultList();
		return result;
	}

}
