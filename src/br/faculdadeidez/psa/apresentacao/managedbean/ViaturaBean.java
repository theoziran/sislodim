package br.faculdadeidez.psa.apresentacao.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.faculdadeidez.psa.vo.CoordenadaVO;
import br.faculdadeidez.psa.vo.ViaturaVO;

/**
 * ManagedBean de viatura
 *
 */
public class ViaturaBean extends GenericoBean {
	/**
	 * Responsável pelos elementos da lista
	 */
	private List<ViaturaVO> listaTudo = null;
	/**
	 * Representa a entidade viatura
	 */
	private ViaturaVO viatura = new ViaturaVO();
		
	/**
	 * Método getter do atributo viatura
	 * @return ViaturaVO
	 */
	public ViaturaVO getViatura() {
		return viatura;
	}

	/**
	 * Método setter do atributo viatura
	 * @param viatura ViaturaVO
	 */
	public void setViatura(ViaturaVO viatura) {
		this.viatura = viatura;
	}

	/**
	 * Método que retorna uma lista de SelectItem contendo as viaturas cadastradas
	 * @return List<SelectItem>
	 */
	public List<SelectItem> getListViaturas() {
		List<SelectItem> listViaturas = new ArrayList<SelectItem>();
		for (ViaturaVO viatura : getFachada().listarViaturas()) {
			SelectItem selectItem = new SelectItem();
			selectItem.setLabel(viatura.getCodigo());
			selectItem.setValue(viatura.getCodigo());
			listViaturas.add(selectItem);
		}
		return listViaturas;
	}

	/**
	 * Método getter do atributo listaTudo
	 * @return List<ViaturaVO>
	 */
	public List<ViaturaVO> getListaTudo() {
		if (listaTudo == null || canUpdate())
			setListaTudo(getFachada().listarViaturas());
		return listaTudo;
	}

	/**
	 * Método que envia os dados da viatura ser deletada
	 * @return String
	 */
	public String delete() {
		ViaturaVO viaturaDaVez = (ViaturaVO) getElementoSelecionado();
		String mensagem = getFachada().deleteViatura(viaturaDaVez);
		adicionaMensagemUsuario(mensagem);
		
		// força atualização
		setListaTudo(null);
		
		return mensagem;
	}

	/**
	 * Método que envia os dados da viatura a ser atualizada
	 * @return String
	 */
	public String update() {
		ViaturaVO viaturaDaVez = (ViaturaVO) getElementoSelecionado();
		String mensagem = getFachada().updateViatura(viaturaDaVez);
		adicionarMensagem(mensagem);
		return mensagem;
	}

	/**
	 * Método que envia os dados para o cadastro de viatura
	 * @return String
	 */
	public String create() {
		String mensagem = getFachada().createViatura(viatura);
		adicionaMensagemUsuario(mensagem);

		return mensagem;
	}

	/**
	 * Método que envia os dados a serem pesquisados nas  viaturas cadastradas
	 */
	public void pesquisar() {
		if (getTermoPesquisa().isEmpty()) {
			setListaTudo(getFachada().listarViaturas());
		} else {
			List<ViaturaVO> viaturas = getFachada().pesquisaViaturas(
					getTermoPesquisa());
			if (viaturas.isEmpty())
				adicionarMensagem("Nenhuma viatura foi encontrada!");
			else {
				if (viaturas.size() > 1)
					adicionarMensagem("Foram encontrados " + viaturas.size()
							+ " resultados para a busca por "
							+ getTermoPesquisa());
				else
					adicionarMensagem("Foi encontrado " + viaturas.size()
							+ " resultado para a busca por "
							+ getTermoPesquisa());
				setListaTudo(viaturas);
			}

		}
	}

	/**
	 * Método setter do atributo listaTudo
	 * @param listaTudo List<ViaturaVO>
	 */
	public void setListaTudo(List<ViaturaVO> listaTudo) {
		this.listaTudo = listaTudo;
	}

	/**
	 * Método para adicionar mensagem a ser mostrada ao usuário
	 * @param mensagem String
	 */
	private void adicionaMensagemUsuario(String mensagem) {
		if (mensagem.equals("viaturaExistente")) {
			adicionarMensagem("Viatura já existe");
		} else if (mensagem.equals("problemaInserir")) {
			adicionarMensagem("Houve um problema ao tentar criar a viatura,\n contacte o administrador");
		} else if (mensagem.equals("inserido")) {
			adicionarMensagem("Viatura criada com sucesso!");
			setViatura(new ViaturaVO());
		} else if (mensagem.equals("dadoInvalido")) {
			adicionarMensagem("Estes dados não são válidos");
		} else if (mensagem.equals("removido")
				|| mensagem.equals("atualizadoDeletado"))
			adicionarMensagem("Deletado com sucesso!");
		else if (mensagem.equals("problemaRemover")) {
			adicionarMensagem("Houve um problema ao tentar remover,\n contacte o administrador");
		} else if (mensagem.equals("atualizado"))
			adicionarMensagem("Atualizado com sucesso!");
		else if (mensagem.equals("viaturaInexistente")) {
			adicionarMensagem("Viatura não existe no banco de dados");
		} else if (mensagem.equals("problemaAtualizar")) {
			adicionarMensagem("Houve um problema ao tentar atualizar,\n contacte o administrador");
		} else {
			adicionarMensagem(mensagem);
		}
	}

	/**
	 * Método que retorna a lista de viaturas escaladas no turno
	 * @return List<SelectItem>
	 */
	public List<SelectItem> getListaViaturas() {
		List<SelectItem> listItensViaturas = new ArrayList<SelectItem>();
		for(ViaturaVO viatura : getFachada().pesquisarViaturasEscalaTurno()){
			SelectItem selectItem = new SelectItem();
			selectItem.setLabel(viatura.getCodigo());
			selectItem.setValue(viatura.getCodigo());
			listItensViaturas.add(selectItem);
		}
		return listItensViaturas;
	}



	/**
	 * Retorna a última coordenada enviada pela viatura
	 * @return CoordenadaVO
	 */
	public CoordenadaVO getUltimaCoordenada() {
		return getFachada().getUltimaCoordenadaViatura(viatura);
	}

}
