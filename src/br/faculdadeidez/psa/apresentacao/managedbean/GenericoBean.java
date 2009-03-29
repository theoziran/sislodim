package br.faculdadeidez.psa.apresentacao.managedbean;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import br.faculdadeidez.psa.facade.Fachada;

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
	
	public void adicionarMensagem ( String mensagem )
    {
    	FacesContext contexto = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage();
        message.setSummary(mensagem);
        contexto.addMessage(null , message );
    }
	
	public void redirecionaPagina(String pagina, String message){
		FacesContext contexto = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse)contexto.getExternalContext().getResponse();
		
		try {
			response.sendRedirect(pagina);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
