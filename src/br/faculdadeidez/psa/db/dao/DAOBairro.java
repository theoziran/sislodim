package br.faculdadeidez.psa.db.dao;

import java.util.List;
import java.util.Vector;

import br.faculdadeidez.psa.db.entity.Bairro;
import br.faculdadeidez.psa.vo.BairroVO;

public class DAOBairro extends DAOFactory<Bairro> {
	public DAOBairro() {
		super();
	}
	
	public BairroVO find(int chave){		
		return Bairro.VO(super.find(Bairro.class, chave));
	}
	
	public List<BairroVO> findByField(String campo, String valor){
		return ConvertList(super.findByField(Bairro.class, campo, valor));
	}
	
	public List<BairroVO> findAll(){
		return ConvertList(super.findAll(Bairro.class));
	}
	
	public void update(BairroVO vo){		
		super.update(new Bairro(vo));
	}
	
	public void persist(BairroVO vo){		
		super.persist(new Bairro(vo));
	}
	
	public void remove(BairroVO vo){		
		super.remove(new Bairro(vo));
	}
	
	/*
	 * Converte um List<Tipo1> para um List<Tipo2>
	 */
	private List<BairroVO> ConvertList(List<Bairro> lista)
	{
		List<BairroVO> newLista = new Vector<BairroVO>();
		for(Bairro set : lista)
			newLista.add(Bairro.VO(set));		
		return newLista;
	}
}
