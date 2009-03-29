package br.faculdadeidez.psa.apresentacao.managedbean;

import java.util.List;

import br.faculdadeidez.psa.vo.SetorVO;

public class SetorBean extends GenericoBean {
	private List<SetorVO> listaTudo = null;
	private SetorVO setor = new SetorVO();
	private String termoPesquisa = new String();
	
	public SetorVO getSetor() {
		return setor;
	}

	public void setSetor(SetorVO setor) {
		this.setor = setor;
	}	
	
	public List<SetorVO> getListaTudo()
	{	
		if (listaTudo==null || listaTudo.isEmpty() || getTermoPesquisa().isEmpty() )
			setListaTudo(getFachada().listarSetores());
		else
			setTermoPesquisa(new String());
		return listaTudo;
	}
		
	public String delete()
	{
		SetorVO setorDaVez = (SetorVO)getElementoSelecionado();
		return getFachada().deleteSetor(setorDaVez);
	}
	
	public String update()
	{
		SetorVO setorDaVez = (SetorVO)getElementoSelecionado();
		return getFachada().updateSetor(setorDaVez);
	}
	
	public void create()
	{
		String mensagem = getFachada().createSetor(setor);
		
		if(mensagem.equals("setorExistente")){			
			adicionarMensagem("Setor já existente");			
		} else if (mensagem.equals("problemaInserir")) {			
			adicionarMensagem("Error...");
		} else {						
			setSetor(new SetorVO());			
			redirecionaPagina("adminSetor.st?id=1", mensagem);
		}
	}
	
	public void pesquisar(){
		if (getTermoPesquisa().isEmpty()){
			setListaTudo(getFachada().listarSetores());
		}else{
		List<SetorVO> setores = getFachada().pesquisaSetor(getTermoPesquisa());
		setListaTudo(setores);
		}
	}

	public void setListaTudo(List<SetorVO> listaTudo) {
		this.listaTudo = listaTudo;
	}

	public String getTermoPesquisa() {
		return termoPesquisa;
	}

	public void setTermoPesquisa(String termoPesquisa) {
		this.termoPesquisa = termoPesquisa;
	}
	
}
