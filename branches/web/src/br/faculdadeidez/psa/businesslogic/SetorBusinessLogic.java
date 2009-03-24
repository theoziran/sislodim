package br.faculdadeidez.psa.businesslogic;

import java.util.List;

import br.faculdadeidez.psa.db.dao.DAOSetor;
import br.faculdadeidez.psa.db.entity.Setor;
import br.faculdadeidez.psa.vo.SetorVO;

public class SetorBusinessLogic {
	public String delete(SetorVO vo){
		try {
			DAOSetor dSetor = new DAOSetor();
			SetorVO set = dSetor.find(vo.getCodigo());			
			dSetor.remove(set);
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
	
	public String create(SetorVO vo){
		try {
			DAOSetor dSetor = new DAOSetor();
			
			if(dSetor.findByField("nome",vo.getNome()) == null){
				dSetor.persist(vo);
				return "inserido";
			}else{
				return "setorExistente";
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			return "problemaInserir";
		}
	}
	

	public List<SetorVO> listar(){
		DAOSetor dSetor = new DAOSetor();
		return dSetor.findAll();		
	}
}
