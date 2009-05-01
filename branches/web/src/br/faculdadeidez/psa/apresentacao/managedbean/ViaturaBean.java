package br.faculdadeidez.psa.apresentacao.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.faculdadeidez.psa.vo.CoordenadaVO;
import br.faculdadeidez.psa.vo.EscalaVO;
import br.faculdadeidez.psa.vo.ViaturaVO;

public class ViaturaBean extends GenericoBean {
	private List<ViaturaVO> listaTudo = null;
	private ViaturaVO viatura = new ViaturaVO();
	private String termoPesquisa = new String();
	private List<SelectItem> listaEscalas;
	private List<SelectItem> listaViaturas;

	public ViaturaVO getViatura() {
		return viatura;
	}

	public void setViatura(ViaturaVO viatura) {
		this.viatura = viatura;
	}

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

	public List<ViaturaVO> getListaTudo() {
		if (listaTudo == null || listaTudo.isEmpty()
				|| getTermoPesquisa().isEmpty())
			setListaTudo(getFachada().listarViaturas());
		else
			setTermoPesquisa(new String());
		return listaTudo;
	}

	public String delete() {
		ViaturaVO viaturaDaVez = (ViaturaVO) getElementoSelecionado();
		viaturaDaVez.setAtivo(false);
		String mensagem = getFachada().updateViatura(viaturaDaVez);
		adicionaMensagemUsuario(mensagem);
		return mensagem;

	}

	public String update() {
		ViaturaVO viaturaDaVez = (ViaturaVO) getElementoSelecionado();
		String mensagem = getFachada().updateViatura(viaturaDaVez);
		adicionarMensagem(mensagem);
		return mensagem;
	}

	public String create() {
		String mensagem = getFachada().createViatura(viatura);
		adicionaMensagemUsuario(mensagem);

		return mensagem;
	}

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

	public void setListaTudo(List<ViaturaVO> listaTudo) {
		this.listaTudo = listaTudo;
	}

	public String getTermoPesquisa() {
		return termoPesquisa;
	}

	public void setTermoPesquisa(String termoPesquisa) {
		this.termoPesquisa = termoPesquisa;
	}

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

	public List<SelectItem> getListaEscalas() {
		listaEscalas = new ArrayList<SelectItem>();
		for (EscalaVO escala : getFachada().listarEscalas()) {
			SelectItem selectItem = new SelectItem();
			selectItem.setLabel(escala.getDataFinal().toString());
			selectItem.setValue(escala.getCodigo());
			listaEscalas.add(selectItem);
		}
		return listaEscalas;
	}

	public void setListaEscalas(List<SelectItem> listaEscalas) {
		this.listaEscalas = listaEscalas;
	}

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

	public void setListaViaturas(List<SelectItem> listaViaturas) {
		this.listaViaturas = listaViaturas;
	}


	public CoordenadaVO getUltimaCoordenada() {
		return getFachada().getUltimaCoordenadaViatura(viatura);
	}

}
