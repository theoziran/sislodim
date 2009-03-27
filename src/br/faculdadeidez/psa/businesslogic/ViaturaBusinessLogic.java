package br.faculdadeidez.psa.businesslogic;

import java.util.List;

import br.faculdadeidez.psa.db.dao.DAOViatura;
import br.faculdadeidez.psa.vo.ViaturaVO;

public class ViaturaBusinessLogic {
	public String delete(ViaturaVO vo){
		try {
			DAOViatura dViatura = new DAOViatura();			
			ViaturaVO via = dViatura.find(vo.getCodigo());						
			dViatura.remove(via);
			return "removido";
		} catch (Exception e) {
			// TODO: handle exception
			return "problemaRemover";
		}
	}
	
	public String update(ViaturaVO vo){
		try {
			DAOViatura dViatura = new DAOViatura();							
			dViatura.update(vo);
			return "atualizado";
		} catch (Exception e) {
			// TODO: handle exception
			return "problemaAtualizar";
		}
	}
	
	public String create(ViaturaVO vo){
		try {
			DAOViatura dViatura = new DAOViatura();			
			dViatura.persist(vo);
			return "inserido";			
		} catch (Exception e) {
			// TODO: handle exception
			return "problemaInserir";
		}
	}
	

	public List<ViaturaVO> listar(){
		DAOViatura dViatura = new DAOViatura();
		return dViatura.findAll();		
	}
	
	public List<ViaturaVO> pesquisar(String valor){
		DAOViatura dViatura = new DAOViatura();
		List<ViaturaVO> retorno = dViatura.findByField("nome", valor);
		return retorno;
	}
}
