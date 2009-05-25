package br.faculdadeidez.psa.apresentacao.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.faculdadeidez.psa.vo.EscalaVO;
import br.faculdadeidez.psa.vo.ViaturaVO;

public class EscalaBean extends GenericoBean {
	/**
	 * Responsável por mostrar os objetos na tabela genérica
	 */
	private List<EscalaVO> listaTudo = null;
	/**
	 * Responsável por representar a entidade Escala
	 */
	private EscalaVO escala = new EscalaVO();
	/**
	 * Método getter do atributo escala
	 * @return EscalaVO
	 */
	public EscalaVO getEscala() {
		return escala;
	}

	/**
	 * Método setter do atributo escala
	 * @param escala EscalaVO
	 */
	public void setEscala(EscalaVO escala) {
		this.escala = escala;
	}

	/**
	 * Método getter do atributo listaTudo
	 * @return List<EscalaVO>
	 */
	public List<EscalaVO> getListaTudo() {
		if (listaTudo == null || canUpdate())
			setListaTudo(getFachada().listarEscalas());
		return listaTudo;
	}

	/**
	 * Método que envia os dados para deletar a escala
	 * @return String
	 */
	public String delete() {
		EscalaVO escalaDaVez = (EscalaVO) getElementoSelecionado();
		String mensagem = getFachada().deleteEscala(escalaDaVez);
		adicionaMensagemUsuario(mensagem);
		
		// força atualização
		setListaTudo(null);
		
		return mensagem;
	}

	/**
	 * Método que envia os dados a serem atualizados
	 * @return String
	 */
	public String update() {
		EscalaVO escalaDaVez = (EscalaVO) getElementoSelecionado();
		escalaDaVez.setViaturas(escala.getViaturas());		
		String mensagem = getFachada().updateEscala(escalaDaVez);
		adicionaMensagemUsuario(mensagem);		
		setElementoSelecionado(null);
		
		// força atualização
		setListaTudo(null);
		
		return mensagem;
	}

	/**
	 * Método que envia os dados para escala ser cadastrada
	 * @return
	 */
	public String create() {			
		String mensagem = getFachada().createEscala(escala);
		setEscala(new EscalaVO());
		adicionaMensagemUsuario(mensagem);
		return mensagem;
	}

	/**
	 * Método que envia os dados para pesquisar nas escalas cadastradas
	 */
	public void pesquisar() {		
		if (getTermoPesquisa() == "") {
			setListaTudo(getFachada().listarEscalas());
		} else {
			int codigoSetor = 0;
			
			// validação
			try { 
				codigoSetor = Integer.parseInt(getTermoPesquisa());
			}
			catch(Exception exc) {
				// ocorreu um erro tentando converter, este não é um número válido
				adicionarMensagemErro("Este não é um código de escala válido!");
				
				// por padrão, lista tudo
				setListaTudo(getFachada().listarEscalas());
				return;
			}
			
			List<EscalaVO> escalas = getFachada().pesquisaEscala(codigoSetor);
			if (escalas.isEmpty())
				adicionarMensagem("Nenhuma escala foi encontrada!");
			else {
				if (escalas.size() > 1)
					adicionarMensagem("Foram encontrados " + escalas.size()
							+ " resultados para a busca por "
							+ getTermoPesquisa());
				else
					adicionarMensagem("Foi encontrado " + escalas.size()
							+ " resultado para a busca por "
							+ getTermoPesquisa());
				setListaTudo(escalas);
			}
		}
	}

	/**
	 * Método setter do atributo listaTudo
	 * @param listaTudo
	 */
	public void setListaTudo(List<EscalaVO> listaTudo) {
		this.listaTudo = listaTudo;
	}

	/**
	 * Método que adiciona uma mensagem a ser mostrada para o usuário
	 * @param mensagem String
	 */
	private void adicionaMensagemUsuario(String mensagem) {
		if (mensagem.equals("escalaExistente")) {
			adicionarMensagem("Escala já existe");
		} else if (mensagem.equals("problemaInserir")) {
			adicionarMensagem("Houve um problema ao tentar criar a escala,\n contacte o administrador");
		} else if (mensagem.equals("inserido")) {
			adicionarMensagem("Escala criada com sucesso!");
			setEscala(new EscalaVO());
		} else if (mensagem.equals("dadoInvalido")) {
			adicionarMensagem("Estes dados não são válidos");
		} else if (mensagem.equals("removido"))
			adicionarMensagem("Deletado com sucesso!");
		else if (mensagem.equals("problemaRemover")) {
			adicionarMensagem("Houve um problema ao tentar remover,\n contacte o administrador");
		} else if (mensagem.equals("atualizado"))
			adicionarMensagem("Atualizado com sucesso!");
		else if (mensagem.equals("escalaInexistente")) {
			adicionarMensagem("Escala não existe no banco de dados");
		} else if (mensagem.equals("problemaAtualizar")) {
			adicionarMensagem("Houve um problema ao tentar atualizar,\n contacte o administrador");
		} else if (mensagem.equals("datafim_ant_dataini")) {
			adicionarMensagemErro("Não é possível cadastrar uma escala com data final anterior a data inicial.");
		} else if (mensagem.equals("dataini_ant_dataatual")) { 
			adicionarMensagemErro("Não é possível cadastrar uma escala com data inicial anterior a atual.");
		} else if (mensagem.startsWith("mesma_viatura_escalas_diferentes")) {
			String codigoViatura = mensagem.substring(mensagem.indexOf('|')+1);
			adicionarMensagemErro("A viatura '"+ codigoViatura +"' já está definida em outra escala entre esta data inicial e final.");
		} else {
			adicionarMensagem(mensagem);
		}
	}

	/**
	 * Método que define as viaturas de uma escala
	 * @param listaViaturas
	 */
	public void setViaturasEscala(List<String> listaViaturas) {
		List<ViaturaVO> viaturas = new ArrayList<ViaturaVO>();
		for (String chave : listaViaturas) {
			viaturas.add(getFachada().pesquisaViatura(chave));
		}
		escala.setViaturas(viaturas);
	}
	/**
	 * Método que retorna as viaturas da escala
	 * @return List<String>
	 */
	public List<String> getViaturasEscala() {
		if (getElementoSelecionado() != null) {
			List<String> listaAtuais = new ArrayList<String>();
			List<ViaturaVO> viaturas = ((EscalaVO) getElementoSelecionado())
					.getViaturas();

			for (ViaturaVO viaturaVO : viaturas) {
				listaAtuais.add(String.valueOf(viaturaVO.getCodigo()));
			}
			return listaAtuais;
		}
		return null;
	}
	/**
	 * Método que retorna uma lista de SelectItem contendo as viaturas escaladas para aquele turno
	 * @return List<SelectItem>
	 */
	public List<SelectItem> getListaViaturas() {
		List<SelectItem> listItensViaturas = new ArrayList<SelectItem>();
		SelectItem selectItem;
		List<ViaturaVO> viaturas = getFachada().pesquisarViaturasEscalaTurno();

		if (viaturas.size() > 0) {
			for (ViaturaVO viatura : viaturas) {
				selectItem = new SelectItem(); 
				selectItem.setLabel(viatura.getCodigo());
				selectItem.setValue(viatura.getCodigo());
				listItensViaturas.add(selectItem);
			}
		} else {
			selectItem = new SelectItem();
			selectItem.setLabel("Nenhuma viatura escalada");
			selectItem.setValue("x");
			listItensViaturas.add(selectItem);
		}
		return listItensViaturas;
	}

}
