package br.faculdadeidez.psa.apresentacao.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.faculdadeidez.psa.vo.BairroVO;

public class BairroBean extends GenericoBean {
	private BairroVO bairro = new BairroVO();
	private List<BairroVO> listaTudo;

	public BairroVO getSetor() {
		return bairro;
	}

	public void setSetor(BairroVO bairro) {
		this.bairro = bairro;
	}
	
	public List<SelectItem> getListBairros(){
		List<SelectItem> listBairros = new ArrayList<SelectItem>();
		for(BairroVO bairro : getFachada().listarBairros()){
			SelectItem selectItem = new SelectItem();
			selectItem.setLabel(bairro.getNome());
			selectItem.setValue(String.valueOf(bairro.getCodigo()));
			listBairros.add(selectItem);
		}
		return listBairros;
	}
	
	
	public List<BairroVO> getListaTudo() {

		if (listaTudo == null || listaTudo.isEmpty()
				)
			setListaTudo(getFachada().listarBairros());
		return listaTudo;
	}
	
	public void setListBairros(){
		
	}
	public void setListaTudo(List<BairroVO> bairros){
		this.listaTudo= bairros;
		
	}





}
