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
			setUsuario(new UsuarioVO());
		}
		
		return mensagem;
	}

	public String delete(){
		UsuarioVO usuarioDaVez = (UsuarioVO) getElementoSelecionado();
		String mensagem = getFachada().deleteUsuario(usuarioDaVez);
		if (mensagem.equals("removido"))
				adicionarMensagem("Deletado com sucesso!");
		return mensagem;
	}
	
	public String update(){
		UsuarioVO usuarioDaVez = (UsuarioVO) getElementoSelecionado();
		String mensagem =  getFachada().updateUsuario(usuarioDaVez);
		if (mensagem.equals("atualizado"))
			adicionarMensagem("Atualizado com sucesso!");
	return mensagem;
	}
	
	public String create(){
		String mensagem = getFachada().createUsuario(getUsuario());
		if(mensagem.equals("usuarioExistente")){
			
			adicionarMensagem("Já existe um usuário com este login");
			
		}else if (mensagem.equals("problemaInserir")) {
			
			adicionarMensagem("Error...");
		}
		adicionarMensagem("Cadastrado com sucesso!");
		return mensagem;	
	}
	


	public UsuarioVO getUsuario() {
		return usuario;
	}


	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}

	public List getListaTudo() {		
		if (listaTudo==null || listaTudo.isEmpty() )
			setListaTudo(getFachada().listaUsuarios());
		return listaTudo;
	}

	public void setListaTudo(List listaTudo) {
		this.listaTudo = listaTudo;
	}	
	
	public void pesquisar(){
		if (getTermoPesquisa().equals("")){
			setListaTudo(getFachada().listaUsuarios());
		}else{
		List<UsuarioVO> usuarios = getFachada().pesquisaUsuario(getTermoPesquisa());
		setListaTudo(usuarios);
		}
	}

	public String getTermoPesquisa() {
		return termoPesquisa;
	}

	public void setTermoPesquisa(String termoPesquisa) {
		this.termoPesquisa = termoPesquisa;
	}


}
