package br.faculdadeidez.psa.businesslogic;

import java.util.List;

import br.faculdadeidez.psa.db.dao.DAOCidade;
import br.faculdadeidez.psa.vo.CidadeVO;
/**
 * Classe que implementa regras de negócio referente a Entidade Cidade
 * Abstrai a camada de persistencia JPA e realiza validações de negócio 
 */
public class CidadeBusinessLogic {
	
	/**
	 * Método para deletar um objeto Cidade
	 * @param CidadeVO vo -> objeto a ser removido do banco
	 * @return String -> indica sucesso ou falha 
	 */
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
	
	/**
	 * Método para atualizar um objeto Cidade
	 * @param CidadeVo vo
	 * @return String
	 */
	public String update(CidadeVO vo){
		try {
			DAOCidade dCidade = new DAOCidade();							
			dCidade.update(vo);
			return "atualizado";
		} catch (Exception e) {
			 
			return "problemaAtualizar";
		}
	}
	/**
	 * Método para criar um objeto Cidade
	 * @param CidadeVO vo
	 * @return String
	 */
	public String create(CidadeVO vo){
		try {
			DAOCidade dCidade = new DAOCidade();
			dCidade.persist(vo);
			return "inserido";
		} catch (Exception e) {
			 
			return "problemaInserir";
		}
	}
	
	/**
	 * Método para listar todos os Cidade
	 * @return List<CidadeVO>
	 */
	public List<CidadeVO> listar(){
		DAOCidade dCidade = new DAOCidade();
		return dCidade.findAll();		
	}
	
	/**
	 * Método para buscar um objeto Cidade id
	 * @param int chave 
	 * @return CidadeVO
	 */
	public CidadeVO find(int chave){
		return new DAOCidade().find(chave);
	}
	
	/**
	 * Método para buscar um objeto Cidade por nome
	 * @param String nome
	 * @return CidadeVO
	 */
	public CidadeVO findNome(String nome){
		DAOCidade dCidade = new DAOCidade();
		return dCidade.findByField("nome", nome).get(0);
	}
}
