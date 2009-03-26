package br.faculdadeidez.psa.vo;

import java.io.Serializable;

public class UsuarioVO implements Serializable {
	private int id;
	private String nome;
	private String login;
	private String senha;
	private int ativo;
	private String cpf;
	private String rg;
	private String orgExpeditor;
	private int tipoPermissao;
		
	public int getTipoPermissao() {
		return tipoPermissao;
	}

	public void setTipoPermissao(int tipoPermissao) {
		this.tipoPermissao = tipoPermissao;
	}

	public UsuarioVO() {
		// TODO Auto-generated constructor stub
	}
		
	public UsuarioVO(String nome, String login, String senha, String cpf, String rg, String orgExpeditor) {		
		setSenha(senha);
		setLogin(login);
		setNome(nome);
		setCpf(cpf);
		setRg(rg);
		setOrgExpeditor(orgExpeditor);
	}
	
	public UsuarioVO(String nome, String login, String senha, int id, int ativo, String cpf, String rg, String orgExpeditor) {
		setAtivo(ativo);
		setId(id);
		setSenha(senha);
		setLogin(login);
		setNome(nome);
		setCpf(cpf);
		setRg(rg);
		setOrgExpeditor(orgExpeditor);
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public int getAtivo() {
		return ativo;
	}
	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getOrgExpeditor() {
		return orgExpeditor;
	}

	public void setOrgExpeditor(String orgExpeditor) {
		this.orgExpeditor = orgExpeditor;
	}	
}
