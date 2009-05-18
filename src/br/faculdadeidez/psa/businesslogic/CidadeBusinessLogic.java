package br.faculdadeidez.psa.businesslogic;

import java.util.List;

import br.faculdadeidez.psa.db.dao.DAOCidade;
import br.faculdadeidez.psa.vo.CidadeVO;

public class CidadeBusinessLogic {
	public String delete(CidadeVO vo){
		try {
			DAOCidade dCidade = new DAOCidade();
			CidadeVO set = dCidade.find(vo.getCodigo());			
			dCidade.remove(set);
			return "removido";
		} catch (Exception e) {
			return "problemaRemover";
		}
	}
	
	public String update(CidadeVO vo){
		try {
			DAOCidade dCidade = new DAOCidade();							
			dCidade.update(vo);
			return "atualizado";
		} catch (Exception e) {
			 
			return "problemaAtualizar";
		}
	}
	
	public String create(CidadeVO vo){
		try {
			DAOCidade dCidade = new DAOCidade();
			dCidade.persist(vo);
			return "inserido";
		} catch (Exception e) {
			 
			return "problemaInserir";
		}
	}
	

	public List<CidadeVO> listar(){
		DAOCidade dCidade = new DAOCidade();
		return dCidade.findAll();		
	}
	
	public CidadeVO find(int chave){
		return new DAOCidade().find(chave);
	}
	
	public CidadeVO findNome(String nome){
		DAOCidade dCidade = new DAOCidade();
		return dCidade.findByField("nome", nome).get(0);
	}
}
