package br.faculdadeidez.psa.db;

import java.util.List;

import javax.persistence.Query;

import br.faculdadeidez.psa.entity.Setor;

public class DAOSetor extends DAOFactory<Setor> {
	public DAOSetor() {
		super();
	}
	
	public Setor find(int chave){
		return super.find(Setor.class, chave);
	}
	
	public List<Setor> findByField(String campo, String valor){
		return super.findByField(Setor.class, campo, valor);
	}
	
	public List<Setor> findAll(){
		return super.findAll(Setor.class);
	}
	
	public List<Setor> findAllActive(){
		Query query = getManager().createQuery("select a from SIS_SETOR a");
		List<Setor> result;
		result = query.getResultList();
		return  result;
	}
	
	public List<Setor> findAllInactive(){
		Query query = getManager().createQuery("select a from SIS_SETOR a");
		List<Setor> result;
		result = query.getResultList();
		return  result;
	}
	
}
