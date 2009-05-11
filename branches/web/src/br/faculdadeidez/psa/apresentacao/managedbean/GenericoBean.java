package br.faculdadeidez.psa.apresentacao.managedbean;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.faculdadeidez.psa.facade.Fachada;

public abstract class GenericoBean {

	private Object elementoSelecionado;
	private String linkEditar;
	private String termoPesquisa;
	
	Fachada getFachada(){
		return Fachada.getFachada();
	}

	public String getTermoPesquisa() {
		return termoPesquisa;
	}

	public void setTermoPesquisa(String termoPesquisa) {
		this.termoPesquisa = termoPesquisa;
	}
	
	public Boolean canUpdate() {
		/* A seguir a maior cambiarra de todos os tempos!!! Mas foi a única forma que consegui
		* encontrar para resolver isto. O problema é que a variável do termo de pesquisa
		* deve ser limpa apenas na primeira vez que a página é carregada, e não quando há uma
		* requisição ajax, como estava acontecendo. 
		*/ 
		FacesContext contexto = FacesContext.getCurrentInstance();		
		if(contexto.getExternalContext().getRequestContentType().indexOf("charset") == -1) { 								
			setTermoPesquisa(new String());
			return true;
		}
		else { 
			return false;
		}
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
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        contexto.addMessage(null , message);
    }
	
	public void adicionarMensagemErro (String mensagem)
    {
    	FacesContext contexto = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage();
        message.setSummary(mensagem);
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        contexto.addMessage(null , message);
    }
}
