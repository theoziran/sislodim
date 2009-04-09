package br.faculdadeidez.psa.vo;

public class MensagemValidacaoVO {
	private String campo;
	private String mensagem;
	private Boolean invalido;
	
	public MensagemValidacaoVO() { 
		
	}
	
	public MensagemValidacaoVO(String campo, String mensagem, Boolean invalido) { 
		this.campo = campo;
		this.mensagem = mensagem;
		this.invalido = invalido;
	}
	
	public String getCampo() {
		return campo;
	}
	
	public void setCampo(String campo) {
		this.campo = campo;
	}
	
	public String getMensagem() {
		return mensagem;
	}
	
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public void setInvalido(Boolean invalido) { 
		this.invalido = invalido;
	}
	
	public Boolean getInvalido() { 
		return invalido;
	}
}
