package br.faculdadeidez.psa.db.dao;

import java.util.List;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.faculdadeidez.psa.db.entity.Escala;
import br.faculdadeidez.psa.db.entity.Viatura;
import br.faculdadeidez.psa.vo.EscalaVO;
import br.faculdadeidez.psa.vo.ViaturaVO;

public class DAOEscala extends DAOFactory<Escala> {
	public DAOEscala() {
		super();
	}
	
	public EscalaVO find(int chave){		
		return Escala.VO(super.find(Escala.class, chave));
	}
	
	public List<EscalaVO> findByField(String campo, String valor){
		return ConvertList(super.findByField(Escala.class, campo, valor));
	}
	
	public List<EscalaVO> findAll(){
		return ConvertList(super.findAll(Escala.class));
	}
	
	public List<EscalaVO> findAllActivated(){
		String strQuery = "SELECT e FROM Escala e WHERE e.ativo = 1";
		EntityManager em = getManager();
		Query query = em.createQuery(strQuery);
		
		List<EscalaVO> resultList = ConvertList( query.getResultList());
		
		return resultList;
	}
		
	public void update(EscalaVO vo){		
		super.update(new Escala(vo));
	}
	
	public void persist(EscalaVO vo){		
		super.persist(new Escala(vo));
	}
	
	public void remove(EscalaVO vo){	
		super.remove(super.find(Escala.class, vo.getCodigo()));
	}
	
	/** 
	 * Verifica se as viaturas presentes na escala atual já estão cadastradas em outras escalas
	 * que estão entre o período inicial e final desta escala
	 * @param vo
	 * @return
	 */
	public String verificaViaturasNoutrasEscalasComMesmoDia(EscalaVO vo) {
		String strQuery = "SELECT v FROM Viatura v " + "JOIN v.escalas sv "
		+ "WHERE :dataInicioEscala between sv.dataInicial and sv.dataFinal AND sv.ativo = 1 AND v.ativo = 1";
		EntityManager em = getManager();
		Query query = em.createQuery(strQuery);
		query.setParameter("dataInicioEscala", vo.getDataInicial());
		
		List<Viatura> viaturas = query.getResultList();
		
		for(Viatura via : viaturas) { 
			// verifica se alguma das viaturas retornadas estão dentre as viaturas que 
			// foram definidas nesta escala
			
			for(ViaturaVO viaEsc : vo.getViaturas()) { 
				if(viaEsc.getCodigo().equals(via.getCodigo()))
					return viaEsc.getCodigo();
			}
		}		
				
		return null;
	}
	
	/*
	 * Converte um List<Tipo1> para um List<Tipo2>
	 */
	public List<EscalaVO> ConvertList(List<Escala> lista)
	{
		List<EscalaVO> newLista = new Vector<EscalaVO>();
		for(Escala set : lista)
			newLista.add(Escala.VO(set));		
		return newLista;
	}
	
}
