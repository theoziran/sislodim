package br.faculdadeidez.psa.db.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.faculdadeidez.psa.db.entity.Setor;
import br.faculdadeidez.psa.db.entity.Usuario;
import br.faculdadeidez.psa.db.entity.Viatura;
import br.faculdadeidez.psa.vo.SetorVO;
import br.faculdadeidez.psa.vo.UsuarioVO;

public class DAOSetor extends DAOFactory<Setor> {
	public DAOSetor() {
		super();
	}
	
	public SetorVO find(int chave){		
		return Setor.VO(super.find(Setor.class, chave));
	}
	
	public List<SetorVO> findByField(String campo, String valor){
		return ConvertList(super.findByField(Setor.class, campo, valor));
	}
	
	public List<SetorVO> findAll(){
		return ConvertList(super.findAll(Setor.class));
	}
	
	public List<SetorVO> findAllActivated(){
		String strQuery = "SELECT  s FROM Setor s WHERE s.ativo = 1";
		EntityManager em = getManager();
		Query query = em.createQuery(strQuery);
		
		List<SetorVO> resultList = ConvertList( query.getResultList());
		
		return resultList;
	}
	
	public void update(SetorVO vo){		
		super.update(new Setor(vo));
	}
	
	public void persist(SetorVO vo){		
		super.persist(new Setor(vo));
	}
	
	public void remove(SetorVO vo){	
		Setor setor = super.find(Setor.class, vo.getCodigo());
		super.remove(setor);
	}
	
	/*
	 * Converte um List<Tipo1> para um List<Tipo2>
	 */
	private List<SetorVO> ConvertList(List<Setor> lista)
	{
		List<SetorVO> newLista = new Vector<SetorVO>();
		for(Setor set : lista)
			newLista.add(Setor.VO(set));		
		return newLista;
	}
	
}
