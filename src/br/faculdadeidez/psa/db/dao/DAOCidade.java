package br.faculdadeidez.psa.db.dao;

import java.util.List;
import java.util.Vector;

import br.faculdadeidez.psa.db.entity.Cidade;
import br.faculdadeidez.psa.vo.CidadeVO;

public class DAOCidade extends DAOFactory<Cidade> {
	public DAOCidade() {
		super();
	}
	
	public CidadeVO find(int chave){		
		return Cidade.VO(super.find(Cidade.class, chave));
	}
	
	public List<CidadeVO> findByField(String campo, String valor){
		return ConvertList(super.findByField(Cidade.class, campo, valor));
	}
	
	public List<CidadeVO> findAll(){
		return ConvertList(super.findAll(Cidade.class));
	}
	
	public void update(CidadeVO vo){		
		super.update(new Cidade(vo));
	}
	
	public void persist(CidadeVO vo){		
		super.persist(new Cidade(vo));
	}
	
	public void remove(CidadeVO vo){		
		super.remove(new Cidade(vo));
	}
	
	/*
	 * Converte um List<Tipo1> para um List<Tipo2>
	 */
	public List<CidadeVO> ConvertList(List<Cidade> lista)
	{
		List<CidadeVO> newLista = new Vector<CidadeVO>();
		for(Cidade set : lista)
			newLista.add(Cidade.VO(set));		
		return newLista;
	}
	
	public List<Cidade> ConverteEntidade(List<CidadeVO> lista)
	{
		List<Cidade> newLista = new Vector<Cidade>();
		for(CidadeVO cidade : lista)
			newLista.add(super.find(Cidade.class, cidade.getCodigo()));
		return newLista;
	}
}
