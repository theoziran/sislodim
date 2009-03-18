package br.faculdadeidez.psa.beans;

public class UsuarioBean extends GenericoBean{
	private int id;
	private String nome;
	private String login;
	private String senha;
	
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
	
	public String logon() {
		return getFachada().logon(getNome(), getSenha());
	}

	public String delete(){
		return getFachada().delete(getNome(), getLogin(), getSenha(), getId());
	}
	
	public String update(){
		return getFachada().update(getNome(), getLogin(), getSenha(), getId());
	}
	
	public String create(){
		return getFachada().create(getNome(), getLogin(), getSenha());
	}
}
