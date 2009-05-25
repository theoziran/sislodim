package br.faculdadeidez.psa.apresentacao.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.faculdadeidez.psa.vo.BairroVO;
import br.faculdadeidez.psa.vo.SetorVO;
/**
 * ManagedBean de Setor
 *
 */
public class SetorBean extends GenericoBean {
	/**
	 * Responsável pela lista de setores
	 */
	private List<SetorVO> listaTudo = null;
	/**
	 * Representa a entidade Setor
	 */
	private SetorVO setor = new SetorVO();

	/**
	 * Método getter do atributo setor
	 * @return SetorVO
	 */
	public SetorVO getSetor() {
		return setor;
	}

	/**
	 * Método setter do atributo setor
	 * @param setor SetorVO
	 */
	public void setSetor(SetorVO setor) {
		this.setor = setor;
	}

	/**
	 * Método que retorna uma lista de SelectItem contendo os setores
	 * @return List<SelectItem>
	 */
	public List<SelectItem> getListaItensSetores() {
		List<SelectItem> listaItensSetores = new ArrayList<SelectItem>();
		for (SetorVO setor : getFachada().listarSetores()) {
			SelectItem selectItem = new SelectItem();
			selectItem.setLabel(setor.getNome());
			selectItem.setValue(setor.getCodigo());

			listaItensSetores.add(selectItem);
		}
		return listaItensSetores;
	}

	/**
	 * Método getter do atributo listaTudo
	 * @return List<SetorVO>
	 */
	public List<SetorVO> getListaTudo() {
		if (listaTudo == null || canUpdate())
			setListaTudo(getFachada().listarSetores());
										
		return listaTudo;
	}

	/**
	 * Método que passa o setor a ser deletado
	 * @return String
	 */
	public String delete() {
		SetorVO setorDaVez = (SetorVO) getElementoSelecionado();
		String mensagem = getFachada().deleteSetor(setorDaVez);
		adicionaMensagemUsuario(mensagem);
		
		// força atualização
		setListaTudo(null);
		
		return mensagem;
	}

	/**
	 * Método que passa o setor a ser atualizado
	 * @return String
	 */
	public String update() {
		SetorVO setorDaVez = (SetorVO) getElementoSelecionado();
		setorDaVez.setBairros(setor.getBairros());
		setElementoSelecionado(null);
		
		String mensagem = getFachada().updateSetor(setorDaVez);
		adicionaMensagemUsuario(mensagem);
		
		return mensagem;
	}

	/**
	 * Método que passa o setor a ser criado
	 * @return String
	 */
	public String create() {
		String mensagem = getFachada().createSetor(setor);

		if (mensagem.equals("setorExistente")) {
			adicionarMensagem("Setor já existente");
		} else if (mensagem.equals("problemaInserir")) {
			adicionarMensagem("Error...");
		} else {
			setSetor(new SetorVO());
			adicionarMensagem("Setor criado com sucesso!");
		}
		return mensagem;
	}

	/**
	 * Método que passa os dados de setor para ser pesquisado nos setores cadastrados
	 */
	public void pesquisar() {
		if (getTermoPesquisa().isEmpty()) {
			setListaTudo(getFachada().listarSetores());
		} else {
			
			List<SetorVO> setores = getFachada().pesquisaSetor(getTermoPesquisa());
			if (setores.isEmpty())
				adicionarMensagem("Nenhum setor foi encontrado!");
			else {
				if (setores.size() > 1)
					adicionarMensagem("Foram encontrados " + setores.size()
							+ " resultados para a busca por '"
							+ getTermoPesquisa() + "'");
				else
					adicionarMensagem("Foi encontrado " + setores.size()
							+ " resultado para a busca por '"
							+ getTermoPesquisa() + "'");
				setListaTudo(setores);		
			}
		}
	}

	/**
	 * Método setter do atributo listaTudo
	 * @param listaTudo List<SetorVO>
	 */
	public void setListaTudo(List<SetorVO> listaTudo) {
		this.listaTudo = listaTudo;
	}

	/**
	 * Método que seta os bairros em um setor
	 * @param listaBairros List<String>
	 */
	public void setBairrosSetor(List<String> listaBairros) {
		List<BairroVO> bairros = new ArrayList<BairroVO>();
		for (String chave : listaBairros) {
			bairros.add(getFachada().pesquisaBairro(Integer.parseInt(chave)));
		}
		setor.setBairros(bairros);
	}

	/**
	 *  Método que retorna os bairros de um setor
	 * @return List<String>
	 */
	public List<String> getBairrosSetor() {
		if (getElementoSelecionado() != null) {
			List<String> listaAtuais = new ArrayList<String>();
			List<BairroVO> bairros = ((SetorVO) getElementoSelecionado())
					.getBairros();

			for (BairroVO bairroVO : bairros) {
				listaAtuais.add(String.valueOf(bairroVO.getCodigo()));
			}
			return listaAtuais;
		}
		return null;
	}

	/**
	 * Método que adicona mensagem ao usuário
	 * @param mensagem String
	 */
	private void adicionaMensagemUsuario(String mensagem) {
		if (mensagem.equals("removido"))
			adicionarMensagem("Deletado com sucesso!");
		else if (mensagem.equals("problemaRemover")) {
			adicionarMensagem("Houve um problema ao tentar remover,\n contacte o administrador");
		} else if (mensagem.equals("atualizado")) { 
			adicionarMensagem("Atualizado com sucesso!");
		} else {
			adicionarMensagem(mensagem);
		}
	}	
}
