package br.faculdadeidez.psa.businesslogic;

import java.util.ArrayList;
import java.util.List;

import br.faculdadeidez.psa.db.dao.DAOSetor;
import br.faculdadeidez.psa.vo.MensagemValidacaoVO;
import br.faculdadeidez.psa.vo.SetorVO;
/**
* Classe que implementa regras de negÛcio referente a Entidade Setor
* Abstrai a camada de persistencia JPA e realiza validaÁıes de negÛcio 
*/
public class SetorBusinessLogic {
	/**
	 * MÈtodo para deletar um objeto Setor
	 * @param SetorVO vo -> objeto a ser removido do banco
	 * @return String -> indica sucesso ou falha 
	 */
	public String delete(SetorVO vo){
		try {
			DAOSetor dSetor = new DAOSetor();
			if(dSetor.findByField("codigo", String.valueOf(vo.getCodigo())).isEmpty())
				return "setorInexistente";
			SetorVO set = dSetor.find(vo.getCodigo());
			set.setAtivo(false);
			dSetor.update(set);
			return "removido";
		} catch (Exception e) {
			 
			return "problemaRemover";
		}
	}
	
	/**
	 * MÈtodo para atualizar um objeto Setor
	 * @param SetorVo vo
	 * @return String
	 */
	public String update(SetorVO vo){
		try {
			// valida os dados inseridos
			List<MensagemValidacaoVO> erros = validaDados(vo);
			
			if(!MensagemValidacao.isValido(erros))
				return MensagemValidacao.getMensagensValidacao(erros);
			
			DAOSetor dSetor = new DAOSetor();
			if(dSetor.findByField("codigo", String.valueOf(vo.getCodigo())).isEmpty())
				return "setorInexistente";
				
			dSetor.update(vo);
			return "atualizado";
				
		} catch (Exception e) {
			 
			return "problemaAtualizar";
		}
	}
	
	/**
	 * MÈtodo para criar um objeto Setor
	 * @param SetorVO vo
	 * @return String
	 */
	public String create(SetorVO setor){
		
		try {
			// valida os dados inseridos
			List<MensagemValidacaoVO> erros = validaDados(setor);
			
			if(!MensagemValidacao.isValido(erros))
				return MensagemValidacao.getMensagensValidacao(erros);
			
			DAOSetor daoSetor = new DAOSetor();
			if(daoSetor.findByField("codigo", String.valueOf(setor.getCodigo())).isEmpty() && daoSetor.findByField("nome", setor.getNome()).isEmpty()){ //!daoSetor.existsActiveNomeSetor(setor.getNome()) ){
				setor.setAtivo(true);
				daoSetor.persist(setor);
				return "inserido";
			}else
				return "setorExistente";

		} catch (Exception e) {
			 
			return "problemaInserir";
		}
	}
	
	/**
	 * MÈtodo para buscar um objeto Setor id
	 * @param int chave 
	 * @return SetorVO
	 */
	public SetorVO find(int chave) {
		return new DAOSetor().find(chave);
	}
	
	/**
	 * MÈtodo para listar todos os Setores
	 * @return List<SetorVO>
	 */
	public List<SetorVO> listar(){
		DAOSetor dSetor = new DAOSetor();
		return dSetor.findAll();		
	}
	
	/**
	 * MÈtodo para listar todos setores ativos
	 * @return List<SetorVO>
	 */
	public List<SetorVO> listarAtivos(){
		DAOSetor dSetor = new DAOSetor();
		return dSetor.findAllActivated();		
	}
	
	/**
	 * MÈtodo para listar setores por valor
	 * @param String valor
	 * @return List<SetorVO>
	 */
	public List<SetorVO> pesquisar(String valor){
		DAOSetor dSetor = new DAOSetor();
		List<SetorVO> retorno = dSetor.findByName(valor);
		return retorno;
	}
	
	/**
	 * MÈtodo de validaÁ„o;
	 * -Garante que o nome n„o contÈm caracteres especiais
	 * -Garante que o nome foi preenchido
	 * -Garante que os bairros possuem setor 
	 * @param Setorvo
	 * @return List<MensagemValidacaoVO>
	 */
	private List<MensagemValidacaoVO> validaDados(SetorVO vo){
		ArrayList<MensagemValidacaoVO> erros = new ArrayList<MensagemValidacaoVO>();
		
		erros.add(new MensagemValidacaoVO("Nome", "O nome È inv·lido", Boolean.valueOf(!vo.getNome().matches("^[0-9a-zA-Z¡¬√¿«… Õ”‘’⁄‹·‚„‡ÁÈÍÌÛÙı˙¸\\s]*$"))));
		erros.add(new MensagemValidacaoVO("Nome", "O nome n„o foi preenchido", Boolean.valueOf(vo.getNome().length() == 0)));
		erros.add(new MensagemValidacaoVO("Bairros", "O setor n„o possui bairros", Boolean.valueOf(vo.getBairros().size()==0)));
		return erros;
	}
	
}
