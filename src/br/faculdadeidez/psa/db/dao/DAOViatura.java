package br.faculdadeidez.psa.db.dao;

import java.util.List;
import java.util.Vector;

import br.faculdadeidez.psa.db.entity.Viatura;
import br.faculdadeidez.psa.vo.ViaturaVO;

public class DAOViatura extends DAOFactory<Viatura> {
	public DAOViatura() {
		super();
	}
	
	public ViaturaVO find(String chave){
		return Viatura.VO(super.find(Viatura.class, chave));
	}
	
	public List<ViaturaVO> findByField(String campo, String valor){
		return ConvertList(super.findByField(Viatura.class, campo, valor));
	}
	
	public List<ViaturaVO> findAll(){
		return ConvertList(super.findAll(Viatura.class));
	}	
	
	public void update(ViaturaVO vo){		
		super.update(new Viatura(vo));
	}
	
	public void persist(ViaturaVO vo){		
		super.persist(new Viatura(vo));
	}
	
	public void remove(ViaturaVO vo){
		Viatura viatura = super.find(Viatura.class, vo.getCodigo());
		super.remove(viatura);
	}
	
	/*
	 * Converte um List<Tipo1> para um List<Tipo2>
	 */
	private List<ViaturaVO> ConvertList(List<Viatura> lista)
	{
		List<ViaturaVO> newLista = new Vector<ViaturaVO>();
		for(Viatura set : lista)
			newLista.add(Viatura.VO(set));		
		return newLista;
	}
}
