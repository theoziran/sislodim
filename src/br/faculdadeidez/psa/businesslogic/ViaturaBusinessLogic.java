package br.faculdadeidez.psa.businesslogic;

import java.util.ArrayList;
import java.util.List;

import br.faculdadeidez.psa.db.dao.DAOViatura;
import br.faculdadeidez.psa.vo.ViaturaVO;

public class ViaturaBusinessLogic {
	public String delete(ViaturaVO vo){
		try {
			DAOViatura dViatura = new DAOViatura();
			if(dViatura.findByField("codigo", vo.getCodigo()).isEmpty())
				return "viaturaInexistente";
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
			// valida os dados inseridos
			List<Boolean> erros = validaDados(vo);
			if (erros.contains(Boolean.valueOf(true)))
				return "dadoInvalido";
			DAOViatura dViatura = new DAOViatura();							
			if(dViatura.findByField("codigo", vo.getCodigo()).isEmpty() && vo.getCodigo() != null)
				return "viaturaInexistente";
			dViatura.update(vo);
			return "atualizado";
		} catch (Exception e) {
			// TODO: handle exception
			return "problemaAtualizar";
		}
	}
	
	public String create(ViaturaVO vo){
		try {
			// valida os dados inseridos
			List<Boolean> erros = validaDados(vo);
			if (erros.contains(Boolean.valueOf(true)))
				return "dadoInvalido";
			DAOViatura dViatura = new DAOViatura();	
			if(dViatura.findByField("codigo", vo.getCodigo()).isEmpty()){	
				dViatura.persist(vo);
				return "inserido";
			}else
				return "viaturaExistente";
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
		List<ViaturaVO> retorno = dViatura.findByField("codigo", valor);
		return retorno;
	}
	
	private List<Boolean> validaDados(ViaturaVO vo){
		ArrayList<Boolean> erros = new ArrayList<Boolean>();
		erros.add(Boolean.valueOf(vo.getCodigo().isEmpty()));
		erros.add(Boolean.valueOf(vo.getCodigo().length() > 4));
		erros.add(Boolean.valueOf(!vo.getCodigo().matches("^[0-9]*$")));
		return erros;
	}
}
