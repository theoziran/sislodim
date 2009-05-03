package br.faculdadeidez.psa.apresentacao.managedbean;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.faces.model.SelectItem;

import br.faculdadeidez.psa.vo.EscalaVO;
import br.faculdadeidez.psa.vo.ViaturaVO;

public class EscalaBean extends GenericoBean {
	private List<EscalaVO> listaTudo = null;
	private EscalaVO escala = new EscalaVO();
	private String termoPesquisa = "";
	private List<SelectItem> listaViaturas = null;

	public EscalaVO getEscala() {
		return escala;
	}

	public void setEscala(EscalaVO escala) {
		this.escala = escala;
	}

	public List<EscalaVO> getListaTudo() {
		if (listaTudo == null || listaTudo.isEmpty()
				|| getTermoPesquisa().isEmpty())
			setListaTudo(getFachada().listarEscalas());
		else
			setTermoPesquisa("");
		return listaTudo;
	}

	public String delete() {
		EscalaVO escalaDaVez = (EscalaVO) getElementoSelecionado();
		String mensagem = getFachada().deleteEscala(escalaDaVez);
		adicionaMensagemUsuario(mensagem);
		return mensagem;

	}

	public String update() {
		EscalaVO escalaDaVez = (EscalaVO) getElementoSelecionado();
		escalaDaVez.setViaturas(escala.getViaturas());		
		String mensagem = getFachada().updateEscala(escalaDaVez);
		adicionaMensagemUsuario(mensagem);		
		setElementoSelecionado(null);		
		return mensagem;
	}

	public String create() {
		String mensagem = getFachada().createEscala(escala);
		setEscala(new EscalaVO());
		adicionaMensagemUsuario(mensagem);
		return mensagem;
	}

	public void pesquisar() {
		if (getTermoPesquisa() == "") {
			setListaTudo(getFachada().listarEscalas());
		} else {
			List<EscalaVO> escalas = getFachada().pesquisaEscala(
					Integer.parseInt(getTermoPesquisa()));
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

	public void setListaTudo(List<EscalaVO> listaTudo) {
		this.listaTudo = listaTudo;
	}

	public String getTermoPesquisa() {
		return termoPesquisa;
	}

	public void setTermoPesquisa(String termoPesquisa) {
		this.termoPesquisa = termoPesquisa;
	}

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
		} else {
			adicionarMensagem(mensagem);
		}
	}

	public void setViaturasEscala(List<String> listaViaturas) {
		List<ViaturaVO> viaturas = new ArrayList<ViaturaVO>();
		for (String chave : listaViaturas) {
			viaturas.add(getFachada().pesquisaViatura(chave));
		}
		escala.setViaturas(viaturas);
	}
	
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
			selectItem.setLabel("viatura(s) inexistente(s)");
			selectItem.setValue("x");
			listItensViaturas.add(selectItem);
		}
		return listItensViaturas;
	}

	public void setListaViaturas(List<SelectItem> listaViaturas) {
		this.listaViaturas = listaViaturas;
	}
}
