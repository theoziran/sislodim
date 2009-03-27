package br.faculdadeidez.psa.apresentacao.managedbean;

import java.util.List;

import br.faculdadeidez.psa.vo.SetorVO;

public class SetorBean extends GenericoBean {
	private List<SetorVO> listaTudo = null;
	private SetorVO setor = new SetorVO();
	private String termoPesquisa;
	
	public SetorVO getSetor() {
		return setor;
	}

	public void setSetor(SetorVO setor) {
		this.setor = setor;
	}	
	
	public List<SetorVO> getListaTudo()
	{	
		if (listaTudo==null || listaTudo.isEmpty() )
			return getFachada().listarSetores();
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
	
	public String create()
	{
		String mensagem = getFachada().createSetor(setor);
		
		if(mensagem.equals("setorExistente")){			
			adicionarMensagem("Setor já existente");			
		}else if (mensagem.equals("problemaInserir")) {			
			adicionarMensagem("Error...");
		}
		return getFachada().createSetor(setor);
	}
	
	public void pesquisar(){
		List<SetorVO> setores = getFachada().pesquisaSetor(getTermoPesquisa());
		setListaTudo(setores);
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
