package br.faculdadeidez.psa.apresentacao.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.faculdadeidez.psa.vo.BairroVO;
import br.faculdadeidez.psa.vo.SetorVO;

public class SetorBean extends GenericoBean {
	private List<SetorVO> listaTudo = null;
	private List<SelectItem> listaItensSetores = null;
	private SetorVO setor = new SetorVO();
	private String termoPesquisa = new String();
	
	public SetorVO getSetor() {
		return setor;
	}

	public void setSetor(SetorVO setor) {
		this.setor = setor;
	}	
	
	public List<SelectItem> getListaItensSetores() {
		List<SelectItem> listaItensSetores = new ArrayList<SelectItem>();
		for(SetorVO setor : getFachada().listarSetores()){
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
		setorDaVez.setBairros(setor.getBairros());
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
			adicionarMensagem("Setor criado com sucesso!");
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
	
	public void setBairrosSetor(List<String> listaBairros){
		List<BairroVO> bairros = new ArrayList<BairroVO>();
		for(String chave : listaBairros){
			bairros.add(getFachada().pesquisaBairro(Integer.parseInt(chave)));
		}
		setor.setBairros(bairros);
	}
	
	public List getBairrosSetor(){
			return new ArrayList();	
	} 
	
}
