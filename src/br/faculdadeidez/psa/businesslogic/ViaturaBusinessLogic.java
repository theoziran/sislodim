package br.faculdadeidez.psa.businesslogic;

import java.util.List;

import br.faculdadeidez.psa.db.DAOViatura;
import br.faculdadeidez.psa.entity.Viatura;

public class ViaturaBusinessLogic {
	public String delete(String codigo){
		try {
			DAOViatura dViatura = new DAOViatura();			
			Viatura via = dViatura.find(codigo);						
			dViatura.remove(via);
			return "removido";
		} catch (Exception e) {
			// TODO: handle exception
			return "problemaRemover";
		}
	}
	
	public String update(Viatura objeto){
		try {
			DAOViatura dViatura = new DAOViatura();							
			dViatura.update(objeto);
			return "atualizado";
		} catch (Exception e) {
			// TODO: handle exception
			return "problemaAtualizar";
		}
	}
	
	public String create(Viatura objeto){
		try {
			DAOViatura dViatura = new DAOViatura();
			
			if(dViatura.find(objeto.getCodigo()) == null){
				dViatura.persist(objeto);
				return "inserido";
			}else{
				return "viaturaExistente";
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			return "problemaInserir";
		}
	}
	

	public List<Viatura> listar(){
		DAOViatura dViatura = new DAOViatura();
		return dViatura.findAll();		
	}
}
