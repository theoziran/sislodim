package br.faculdadeidez.psa.apresentacao.managedbean;

import java.util.List;

import br.faculdadeidez.psa.vo.ViaturaVO;

public class ViaturaBean extends GenericoBean {
	private List<ViaturaVO> listaTudo = null;
	private ViaturaVO viatura = new ViaturaVO();
	private String termoPesquisa = new String();

	public ViaturaVO getViatura() {
		return viatura;
	}

	public void setViatura(ViaturaVO viatura) {
		this.viatura = viatura;
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
		String mensagem = getFachada().deleteViatura(viaturaDaVez);
		adicionaMensagemUsuario(mensagem);
		return mensagem;

	}

	public String update() {
		ViaturaVO viaturaDaVez = (ViaturaVO) getElementoSelecionado();
		String mensagem = getFachada().updateViatura(viaturaDaVez);
		adicionaMensagemUsuario(mensagem);
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
			List<ViaturaVO> viaturas = getFachada().pesquisaViatura(
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
		}else if (mensagem.equals("dadoInvalido")) {
			adicionarMensagem("Estes dados não são válidos");
		}else if (mensagem.equals("removido"))
			adicionarMensagem("Deletado com sucesso!");
		else if (mensagem.equals("problemaRemover")) {
			adicionarMensagem("Houve um problema ao tentar remover,\n contacte o administrador");
		}else if (mensagem.equals("atualizado"))
			adicionarMensagem("Atualizado com sucesso!");
		else if (mensagem.equals("viaturaInexistente")) {
			adicionarMensagem("Viatura não existe no banco de dados");
		}else if (mensagem.equals("problemaAtualizar")) {
			adicionarMensagem("Houve um problema ao tentar atualizar,\n contacte o administrador");
		} else { 
			adicionarMensagem(mensagem);
		}
	}

}
