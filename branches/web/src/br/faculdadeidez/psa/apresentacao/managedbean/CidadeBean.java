package br.faculdadeidez.psa.apresentacao.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.faculdadeidez.psa.vo.CidadeVO;

/**
 * ManagedBean de cidade
 *
 */
public class CidadeBean extends GenericoBean {


	/**
	 * Construtor default da classe
	 */
	public CidadeBean() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Método que retorna uma lista de selectItem contendo as cidades cadastradas
	 * @return List<SelectItem>
	 */
	public List<SelectItem> getListCidades() {
		List<SelectItem> listCidades = new ArrayList<SelectItem>();
		for (CidadeVO cidade : getFachada().listarCidades()) {
			SelectItem selectItem = new SelectItem();
			selectItem.setLabel(cidade.getNome());
			selectItem.setValue(String.valueOf(cidade.getNome()));
			listCidades.add(selectItem);
		}
		return listCidades;
	}

}
