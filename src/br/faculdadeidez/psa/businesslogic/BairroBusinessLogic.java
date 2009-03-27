package br.faculdadeidez.psa.businesslogic;

import java.util.List;

import br.faculdadeidez.psa.db.dao.DAOBairro;

import br.faculdadeidez.psa.vo.BairroVO;

public class BairroBusinessLogic {
	public String delete(BairroVO vo){
		try {
			DAOBairro dBairro = new DAOBairro();
			BairroVO set = dBairro.find(vo.getCodigo());			
			dBairro.remove(set);
			return "removido";
		} catch (Exception e) {
			// TODO: handle exception
			return "problemaRemover";
		}
	}
	
	public String update(BairroVO vo){
		try {
			DAOBairro dBairro = new DAOBairro();							
			dBairro.update(vo);
			return "atualizado";
		} catch (Exception e) {
			// TODO: handle exception
			return "problemaAtualizar";
		}
	}
	
	public String create(BairroVO vo){
		try {
			DAOBairro dBairro = new DAOBairro();
			dBairro.persist(vo);
			return "inserido";
		} catch (Exception e) {
			// TODO: handle exception
			return "problemaInserir";
		}
	}
	

	public List<BairroVO> listar(){
		DAOBairro dBairro = new DAOBairro();
		return dBairro.findAll();		
	}
}
