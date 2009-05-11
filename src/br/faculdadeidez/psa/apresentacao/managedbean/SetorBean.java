package br.faculdadeidez.psa.apresentacao.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.lifecycle.Lifecycle;
import javax.faces.model.SelectItem;
import javax.faces.webapp.FacesServlet;

import org.richfaces.function.RichFunction;

import br.faculdadeidez.psa.vo.BairroVO;
import br.faculdadeidez.psa.vo.EscalaVO;
import br.faculdadeidez.psa.vo.SetorVO;

public class SetorBean extends GenericoBean {
	private List<SetorVO> listaTudo = null;
	private List<SelectItem> listaItensSetores = null;
	private SetorVO setor = new SetorVO();

	public SetorVO getSetor() {
		return setor;
	}

	public void setSetor(SetorVO setor) {
		this.setor = setor;
	}

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

	public void setListaItensSetores(List<SelectItem> lista) {
		this.listaItensSetores = lista;
	}

	public List<SetorVO> getListaTudo() {
		if (listaTudo == null || canUpdate())
			setListaTudo(getFachada().listarSetores());
										
		return listaTudo;
	}

	public String delete() {
		SetorVO setorDaVez = (SetorVO) getElementoSelecionado();
		String mensagem = getFachada().deleteSetor(setorDaVez);
		adicionaMensagemUsuario(mensagem);
		
		// força atualização
		setListaTudo(null);
		
		return mensagem;
	}

	public String update() {
		SetorVO setorDaVez = (SetorVO) getElementoSelecionado();
		setorDaVez.setBairros(setor.getBairros());
		setElementoSelecionado(null);
		
		String mensagem = getFachada().updateSetor(setorDaVez);
		adicionaMensagemUsuario(mensagem);
		
		return mensagem;
	}

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

	public void setListaTudo(List<SetorVO> listaTudo) {
		this.listaTudo = listaTudo;
	}

	public void setBairrosSetor(List<String> listaBairros) {
		List<BairroVO> bairros = new ArrayList<BairroVO>();
		for (String chave : listaBairros) {
			bairros.add(getFachada().pesquisaBairro(Integer.parseInt(chave)));
		}
		setor.setBairros(bairros);
	}

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
