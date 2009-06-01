package br.faculdadeidez.psa.vo;

/**
 * Classe responsável por transportar objetoMensagemValidacao
 * entre as camadas.
 */
public class MensagemValidacaoVO {
	
	/**
	 * Propriedade provada campo
	 */
	private String campo;
	
	/**
	 * Propriedade privada mensagem
	 */
	private String mensagem;
	
	/**
	 * Propriedade privada invalido
	 */
	private Boolean invalido;
	
	/**
	 * Construtor da classe
	 */
	public MensagemValidacaoVO() { 
		
	}
	
	/**
	 * Sobrecarga do construtor padrão 
	 * @param String campo
	 * @param String mensagem
	 * @param boolean invalido
	 */
	public MensagemValidacaoVO(String campo, String mensagem, Boolean invalido) { 
		this.campo = campo;
		this.mensagem = mensagem;
		this.invalido = invalido;
	}
	
	/**
	 * Método getter da propriedade campo
	 * @return String campo
	 */
	public String getCampo() {
		return campo;
	}
	
	/**
	 * Método setter da propriedade campo
	 * @param String campo
	 */
	public void setCampo(String campo) {
		this.campo = campo;
	}
	
	/**
	 * Método getter da propriedade mensagem
	 * @return String mensagem
	 */
	public String getMensagem() {
		return mensagem;
	}
	
	/***
	 * Método setter da propriedade mensagem
	 * @param String mensagem
	 */
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	/**
	 * Método setter da propriedade invalido
	 * @param boolean invalido
	 */
	public void setInvalido(Boolean invalido) { 
		this.invalido = invalido;
	}
	
	/**
	 * Método getter da propriedade invalido
	 * @return boolean invalido
	 */
	public Boolean getInvalido() { 
		return invalido;
	}
}
