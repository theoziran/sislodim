package br.faculdadeidez.psa.apresentacao.managedbean;

import java.util.List;

import br.faculdadeidez.psa.vo.UsuarioVO;

public class UsuarioBean extends GenericoBean {
	private UsuarioVO usuario = new UsuarioVO();
	private String termoPesquisa;

	private List listaTudo;

	public UsuarioBean() {
		setLinkEditar("cadastroUsuario");
	}
	

	public String logon() {
		String mensagem=getFachada().logon(getUsuario().getLogin(), getUsuario().getSenha());
		if (mensagem.equals("logado")){
			adicionarMensagem("Logado com sucesso");
		}
		return mensagem;
	}

	public String delete(){
		UsuarioVO usuarioDaVez = (UsuarioVO) getElementoSelecionado();
		return getFachada().deleteUsuario(usuarioDaVez);
	}
	
	public String update(){
		UsuarioVO usuarioDaVez = (UsuarioVO) getElementoSelecionado();
		return getFachada().updateUsuario(usuarioDaVez);
	}
	
	public String create(){
		String mensagem = getFachada().createUsuario(getUsuario());
		
		if(mensagem.equals("usuarioExistente")){
			
			adicionarMensagem("Já existe um usuário com este login");
			
		}else if (mensagem.equals("problemaInserir")) {
			
			adicionarMensagem("Error...");
		}
		return mensagem;	
	}
	
	public void pesquisar(){
		List<UsuarioVO> usuarios = getFachada().pesquisa(getTermoPesquisa());
		setListaTudo(usuarios);
	}

	public List getListaTudo() {
		if (listaTudo==null)
			System.out.println("lista nula");else
		if (listaTudo.isEmpty())
			System.out.println("lista vazia");else
		if (!listaTudo.equals(getFachada().listaUsuarios()))
			System.out.println("lista diferente");
		
		if (listaTudo==null || listaTudo.isEmpty() )
			setListaTudo(getFachada().listaUsuarios());
		return listaTudo;
	}

	public void setListaTudo(List listaTudo) {
		this.listaTudo = listaTudo;
	}	
	

	public String getTermoPesquisa() {
		return termoPesquisa;
	}

	public void setTermoPesquisa(String termoPesquisa) {
		this.termoPesquisa = termoPesquisa;
	}


	public UsuarioVO getUsuario() {
		return usuario;
	}


	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}
}
