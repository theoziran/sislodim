package br.faculdadeidez.psa.businesslogic;

import java.util.List;

import br.faculdadeidez.psa.db.DAOSetor;
import br.faculdadeidez.psa.entity.Setor;

public class SetorBusinessLogic {
	public String delete(int codigo){
		try {
			DAOSetor dSetor = new DAOSetor();
			Setor setor = dSetor.find( codigo );
			dSetor.remove(setor);
			return "removido";
		} catch (Exception e) {
			// TODO: handle exception
			return "problemaRemover";
		}
	}
	
	public String update(String nome){
		try {
			DAOSetor dSetor = new DAOSetor();
			Setor setor = new Setor(nome);
			dSetor.update(setor);
			return "atualizado";
		} catch (Exception e) {
			// TODO: handle exception
			return "problemaAtualizar";
		}
	}
	
	public String create(String nome){
		try {
			DAOSetor dSetor = new DAOSetor();
			Setor setor = new Setor(nome);
			dSetor.persist(setor);
			return "inserido";
		} catch (Exception e) {
			// TODO: handle exception
			return "problemaInserir";
		}
	}
	

	public List listar(){
		DAOSetor dSetor = new DAOSetor();
		List setores = dSetor.findAll();
		return setores;
		
	}
}
