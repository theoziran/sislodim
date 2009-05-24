package br.faculdadeidez.psa.apresentacao.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.faculdadeidez.psa.vo.BairroVO;

public class BairroBean extends GenericoBean {
	/**
	 * Responsável por representar a entidade Bairro
	 */
	private BairroVO bairro = new BairroVO();
	/**
	 * Responsável por mostrar os objetos na tabela genérica
	 */
	private List<BairroVO> listaTudo;

	/**
	 * FIXME nome do método difere do atributo
	 * @return
	 */
	public BairroVO getSetor() {
		return bairro;
	}

	/**
	 * FIXME nome do método difere do atributo
	 * @return
	 */
	public void setSetor(BairroVO bairro) {
		this.bairro = bairro;
	}

	/**
	 * Método que retorna uma lista de selectItem contendo os bairros cadastrados
	 * @return List<SelectItem>
	 */
	public List<SelectItem> getListBairros() {
		List<SelectItem> listBairros = new ArrayList<SelectItem>();
		for (BairroVO bairro : getFachada().listarBairros()) {
			SelectItem selectItem = new SelectItem();
			selectItem.setLabel(bairro.getNome());
			selectItem.setValue(String.valueOf(bairro.getCodigo()));
			listBairros.add(selectItem);
		}
		return listBairros;
	}
	
   /**
    * Método getter do atributo listaTudo
    * @return List<BairroVO>
    */
	public List<BairroVO> getListaTudo() {

		if (listaTudo == null || listaTudo.isEmpty())
			setListaTudo(getFachada().listarBairros());
		return listaTudo;
	}


	/**
	 * Método setter do atributo listaTudo
	 * @param bairros Lista de bairros a serem setados
	 */
	public void setListaTudo(List<BairroVO> bairros) {
		this.listaTudo = bairros;
	}

}
