package br.faculdadeidez.psa.businesslogic;

import java.util.ArrayList;
import java.util.List;

import br.faculdadeidez.psa.db.dao.DAOSetor;
import br.faculdadeidez.psa.vo.MensagemValidacaoVO;
import br.faculdadeidez.psa.vo.SetorVO;

public class SetorBusinessLogic {
	public String delete(SetorVO vo){
		try {
			DAOSetor dSetor = new DAOSetor();
			if(dSetor.findByField("codigo", vo.getCodigo()).isEmpty())
				return "setorInexistente";
			SetorVO set = dSetor.find(vo.getCodigo());
			set.setAtivo(false);
			dSetor.update(set);
			return "removido";
		} catch (Exception e) {
			// TODO: handle exception
			return "problemaRemover";
		}
	}
	
	public String update(SetorVO vo){
		try {
			// valida os dados inseridos
			List<MensagemValidacaoVO> erros = validaDados(vo);
			
			if(!MensagemValidacao.isValido(erros))
				return MensagemValidacao.getMensagensValidacao(erros);
			
			DAOSetor dSetor = new DAOSetor();
			if(dSetor.findByField("codigo", vo.getCodigo()).isEmpty() && vo.getCodigo() != null)
				return "setorInexistente";
				
			dSetor.update(vo);
			return "atualizado";
				
		} catch (Exception e) {
			// TODO: handle exception
			return "problemaAtualizar";
		}
	}
	
	public String create(SetorVO setor){
		
		try {
			// valida os dados inseridos
			List<MensagemValidacaoVO> erros = validaDados(setor);
			
			if(!MensagemValidacao.isValido(erros))
				return MensagemValidacao.getMensagensValidacao(erros);
			
			DAOSetor daoSetor = new DAOSetor();
			if(daoSetor.findByField("codigo", setor.getCodigo()).isEmpty() && !daoSetor.existsActiveNomeSetor(setor.getNome()) ){
				setor.setAtivo(true);
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
		return dSetor.findAllActivated();		
	}
	
	public List<SetorVO> pesquisar(String valor){
		DAOSetor dSetor = new DAOSetor();
		List<SetorVO> retorno = dSetor.findByField("nome", valor);
		return retorno;
	}
	
	public List<SetorVO> pesquisarByCodigo(String valor){
		DAOSetor dSetor = new DAOSetor();
		List<SetorVO> retorno = dSetor.findByField("codigo", valor);
		return retorno;
	}
	
	private List<MensagemValidacaoVO> validaDados(SetorVO vo){
		ArrayList<MensagemValidacaoVO> erros = new ArrayList<MensagemValidacaoVO>();
		
		erros.add(new MensagemValidacaoVO("Código", "O código deve ser apenas 4 dígitos", Boolean.valueOf(vo.getCodigo().length() != 4  || Boolean.valueOf(!vo.getCodigo().matches("\\d+")) ))); 
		//FIXME: N ta funcinando 
		//erros.add(new MensagemValidacaoVO("Nome", "O nome é inválido", Boolean.valueOf(!vo.getCodigo().matches("[[:alnum:]]"))));
		
		
		
		return erros;
	}
	
}
