package br.faculdadeidez.psa.beans;

import br.faculdadeidez.psa.businesslogic.Fachada;

public abstract class GenericoBean {

	private Object elementoSelecionado;
	private String linkEditar;
	private String valorBusca;
	
	Fachada getFachada(){
		return Fachada.getFachada();
	}

	public String getValorBusca() {
		return valorBusca;
	}

	public void setValorBusca(String valorBusca) {
		this.valorBusca = valorBusca;
	}

	public String getLinkEditar() {
		return linkEditar;
	}

	public void setLinkEditar(String linkEditar) {
		this.linkEditar = linkEditar;
	}
	
	public Object getElementoSelecionado() {
		return elementoSelecionado;
	}

	public void setElementoSelecionado(Object elementoSelecionado) {
		this.elementoSelecionado = elementoSelecionado;
	}	
}
