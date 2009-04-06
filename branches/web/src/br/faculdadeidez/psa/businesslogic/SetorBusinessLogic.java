package br.faculdadeidez.psa.businesslogic;

import java.util.List;

import br.faculdadeidez.psa.db.dao.DAOSetor;
import br.faculdadeidez.psa.vo.SetorVO;

public class SetorBusinessLogic {
	public String delete(SetorVO vo){
		try {
			DAOSetor dSetor = new DAOSetor();
			SetorVO set = dSetor.find(vo.getCodigo());
			if(set == null)
				return "setorInexistente";
			set.setAtivo(0);
			dSetor.update(set);
			return "removido";
		} catch (Exception e) {
			// TODO: handle exception
			return "problemaRemover";
		}
	}
	
	public String update(SetorVO vo){
		try {
			DAOSetor dSetor = new DAOSetor();							
			dSetor.update(vo);
			return "atualizado";
		} catch (Exception e) {
			// TODO: handle exception
			return "problemaAtualizar";
		}
	}
	
	public String create(SetorVO setor){
		try {
			DAOSetor daoSetor = new DAOSetor();
			if(daoSetor.findByField("nome", setor.getNome()).isEmpty()){
				setor.setAtivo(1);
				daoSetor.persist(setor);
				return "inserido";
			}else
				return "setorExistente";
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			return "problemaInserir";
		}
	}
	

	public List<SetorVO> listar(){
		DAOSetor dSetor = new DAOSetor();
		return dSetor.findAll();		
	}
	
	public List<SetorVO> listarAtivos(){
		DAOSetor dSetor = new DAOSetor();
		return dSetor.findAllActived();		
	}
	
	public List<SetorVO> pesquisar(String valor){
		DAOSetor dSetor = new DAOSetor();
		List<SetorVO> retorno = dSetor.findByField("nome", valor);
		return retorno;
	}
}
