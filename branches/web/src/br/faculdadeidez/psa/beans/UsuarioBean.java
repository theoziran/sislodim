package br.faculdadeidez.psa.beans;

import java.util.List;

public class UsuarioBean extends GenericoBean {
	private int id;
	private String nome;
	private String login;
	private String senha;
	private int ativo;
	private List listaTudo;
	private int tipoLista;	

	public UsuarioBean() {
		setLinkEditar("editaUsuario");
	}
	
	public UsuarioBean(int id, String login, String nome, String senha, int ativo ) {
		setAtivo(ativo);
		setLinkEditar("editaUsuario");
		setId(id);
		setLogin(login);
		setNome(nome);
		setSenha(senha);
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

	public int getTipoLista() {
		return tipoLista;
	}

	public void setTipoLista(int tipoLista) {
		this.tipoLista = tipoLista;
	}
	
	public String logon() {
		return getFachada().logon(getLogin(), getSenha());
	}

	public String delete(){
		UsuarioBean usuarioDaVez = (UsuarioBean) getElementoSelecionado();
		return getFachada().deleteUsuario(usuarioDaVez);
	}
	
	public String update(){
		UsuarioBean usuarioDaVez = (UsuarioBean) getElementoSelecionado();
		return getFachada().updateUsuario(usuarioDaVez);
	}
	
	public String create(){
		return getFachada().createUsuario(this);
	}

	public List getListaTudo() {
		if (listaTudo==null || listaTudo.isEmpty() || (!listaTudo.equals(getFachada().listaUsuarios())))
			setListaTudo(getFachada().listaUsuarios());
		return listaTudo;
	}

	public void setListaTudo(List listaTudo) {
		this.listaTudo = listaTudo;
	}	
	
	public void atualizarLista(){
		if (getTipoLista()==1)
			listarAtivos();
			else if(getTipoLista()==2)
				listarInativos();
			else
				listarTudo();
				
	}
	
	public void listarTudo(){
		setListaTudo(getFachada().listaUsuarios());
	}

	public void listarAtivos(){
		setListaTudo(getFachada().listaUsuariosAtivos());
	}
	
	public void listarInativos(){
		setListaTudo(getFachada().listaUsuarios());
	}
}
