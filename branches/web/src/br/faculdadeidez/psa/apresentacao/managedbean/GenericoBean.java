package br.faculdadeidez.psa.apresentacao.managedbean;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.faculdadeidez.psa.facade.Fachada;
/**
 * ManagedBean genérico com os métodos comuns a todos os outros
 * @author Samuel
 *
 */
public abstract class GenericoBean {

	/**
	 * Responsável por armazenar o objeto selecionado em uma lista
	 */
	private Object elementoSelecionado;
	/**
	 * Responsável por guardar o termo pesquisado
	 */
	private String termoPesquisa;
	/**
	 * Método que retorna a fachada
	 * @return Fachada
	 */
	Fachada getFachada(){
		return Fachada.getFachada();
	}

	/**
	 * Método getter do atributo
	 * @return String
	 */
	public String getTermoPesquisa() {
		return termoPesquisa;
	}

	/**
	 * Método setter do atributo
	 * @param termoPesquisa String
	 */
	public void setTermoPesquisa(String termoPesquisa) {
		this.termoPesquisa = termoPesquisa;
	}
	/**
	 * Método que permite atualizar sem bugs
	 * @return Boolean
	 */
	public Boolean canUpdate() {
		FacesContext contexto = FacesContext.getCurrentInstance();		
		if(contexto.getExternalContext().getRequestContentType().indexOf("charset") == -1) { 								
			setTermoPesquisa(new String());
			return true;
		}
		else { 
			return false;
		}
	}


	/**
	 * Método getter do atributo elementoSelecionado
	 * @return Object
	 */
	public Object getElementoSelecionado() {
		return elementoSelecionado;
	}

	/**
	 * Método setter do atributo elementoSelecionado
	 * @param elementoSelecionado Object
	 */
	public void setElementoSelecionado(Object elementoSelecionado) {
		this.elementoSelecionado = elementoSelecionado;
	}
	
	/**
	 * Método para adicionar mensagens informativas
	 * @param mensagem String
	 */
	public void adicionarMensagem ( String mensagem )
    {
    	FacesContext contexto = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage();
        message.setSummary(mensagem);
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        contexto.addMessage(null , message);
    }
	
	/**
	 * Método para adicionar mensagens de erro
	 * @param mensagem String
	 */
	public void adicionarMensagemErro (String mensagem)
    {
    	FacesContext contexto = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage();
        message.setSummary(mensagem);
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        contexto.addMessage(null , message);
    }
}
