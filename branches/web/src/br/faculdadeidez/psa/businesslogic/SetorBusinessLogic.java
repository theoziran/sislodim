package br.faculdadeidez.psa.businesslogic;

import java.util.List;

import br.faculdadeidez.psa.db.DAOSetor;
import br.faculdadeidez.psa.entity.Setor;

public class SetorBusinessLogic {
	public String delete(int codigo){
		try {
			DAOSetor dSetor = new DAOSetor();
			Setor set = dSetor.find(codigo);			
			dSetor.remove(set);
			return "removido";
		} catch (Exception e) {
			// TODO: handle exception
			return "problemaRemover";
		}
	}
	
	public String update(Setor objeto){
		try {
			DAOSetor dSetor = new DAOSetor();							
			dSetor.update(objeto);
			return "atualizado";
		} catch (Exception e) {
			// TODO: handle exception
			return "problemaAtualizar";
		}
	}
	
	public String create(Setor objeto){
		try {
			DAOSetor dSetor = new DAOSetor();
			dSetor.persist(objeto);
			return "inserido";
		} catch (Exception e) {
			// TODO: handle exception
			return "problemaInserir";
		}
	}
	

	public List<Setor> listar(){
		DAOSetor dSetor = new DAOSetor();
		return dSetor.findAll();		
	}
}
