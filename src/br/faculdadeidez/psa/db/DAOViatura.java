package br.faculdadeidez.psa.db;

import java.util.List;

import br.faculdadeidez.psa.entity.Setor;
import br.faculdadeidez.psa.entity.Viatura;

public class DAOViatura extends DAOFactory<Viatura> {
	public DAOViatura() {
		super();
	}
	
	public Viatura find(String chave){
		return super.find(Viatura.class, chave);
	}
	
	public List<Viatura> findByField(String campo, String valor){
		return super.findByField(Viatura.class, campo, valor);
	}
	
	public List<Viatura> findAll(){
		return super.findAll(Viatura.class);
	}	
}
