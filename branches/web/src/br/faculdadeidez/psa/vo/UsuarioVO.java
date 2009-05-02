package br.faculdadeidez.psa.vo;


public class UsuarioVO{
	private int id;
	private String nome;
	private String login;
	private String senha;
	private boolean ativo;
	private String cpf;
	private String rg;
	private String orgExpeditor;
	private short tipoPermissao;
		
	public UsuarioVO() {
		 
	}
		
	public UsuarioVO(String nome, String login, String senha, String cpf, String rg, String orgExpeditor, String tipoPermissao) {		
		setSenha(senha);
		setLogin(login);
		setNome(nome);
		setCpf(cpf);
		setRg(rg);
		setOrgExpeditor(orgExpeditor);
		setTipoPermissao(Short.valueOf(tipoPermissao));
	}
	
	public UsuarioVO(String nome, String login, String senha, int id, boolean ativo, String cpf, String rg, String orgExpeditor, String tipoPermissao) {
		setAtivo(ativo);
		setId(id);
		setSenha(senha);
		setLogin(login);
		setNome(nome);
		setCpf(cpf);
		setRg(rg);
		setOrgExpeditor(orgExpeditor);
		setTipoPermissao(Short.valueOf(tipoPermissao));
	}
	
	public short getTipoPermissao() {
		return tipoPermissao;
	}

	public void setTipoPermissao(short tipoPermissao) {
		this.tipoPermissao = tipoPermissao;
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
	public boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
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
