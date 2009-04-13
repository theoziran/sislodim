package br.faculdadeidez.psa.businesslogic;

import java.util.List;

import br.faculdadeidez.psa.db.dao.DAOEscala;
import br.faculdadeidez.psa.vo.EscalaVO;

public class EscalaBusinessLogic {
	public String delete(EscalaVO vo){
		try {
			DAOEscala dEscala = new DAOEscala();
			EscalaVO esc = dEscala.find(vo.getCodigo());
			if(esc == null) return "escalaInexistente";
			dEscala.remove(esc);
			return "removido";
		} catch (Exception e) {
			// TODO: handle exception
			return "problemaRemover";
		}
	}
	
	public String update(EscalaVO vo){
		try {
			DAOEscala dEscala = new DAOEscala();							
			dEscala.update(vo);
			return "atualizado";
		} catch (Exception e) {
			// TODO: handle exception
			return "problemaAtualizar";
		}
	}
	
	public String create(EscalaVO escala){
		try {
			DAOEscala daoEscala = new DAOEscala();
			if(daoEscala.findByField("codigo", String.valueOf(escala.getCodigo())).isEmpty()){
				daoEscala.persist(escala);
				return "inserido";
			}else
				return "escalaExistente";
		} catch (Exception e) {
			// TODO: handle exception
			return "problemaInserir";
		}
	}	

	public List<EscalaVO> listar(){
		DAOEscala dEscala = new DAOEscala();
		return dEscala.findAll();		
	}
		
	public List<EscalaVO> pesquisar(int valor){
		DAOEscala dEscala = new DAOEscala();
		List<EscalaVO> retorno = dEscala.findByField("codigo", String.valueOf(valor));
		return retorno;
	}
}
