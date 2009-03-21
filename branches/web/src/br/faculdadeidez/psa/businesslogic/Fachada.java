package br.faculdadeidez.psa.businesslogic;

import java.util.ArrayList;
import java.util.List;

import br.faculdadeidez.psa.beans.UsuarioBean;
import br.faculdadeidez.psa.entity.Usuario;


public class Fachada {
	
	private static Fachada INSTANCE;
	
	private Fachada (){
		
	}
	
	public static Fachada getFachada(){
		if (INSTANCE==null){
			INSTANCE = new Fachada();
			return INSTANCE;
		}
		return INSTANCE;
	}
	
	public String logon(String login, String senha) {
		UsuarioBusinessLogic logicaUsuario = new UsuarioBusinessLogic();
		return logicaUsuario.logon(login, senha);
	}

	public String delete(int id){
		UsuarioBusinessLogic logicaUsuario = new UsuarioBusinessLogic();
		return logicaUsuario.delete(id);
	}
	
	public String update(String nome, String login, String senha, int id, int ativo){
		UsuarioBusinessLogic logicaUsuario = new UsuarioBusinessLogic();
		return logicaUsuario.update(nome, login, senha, id, ativo);
	}
	
	public String create(String nome, String login, String senha){
		UsuarioBusinessLogic logicaUsuario = new UsuarioBusinessLogic();
		return logicaUsuario.create(nome, login, senha);
	}
	

	public List<UsuarioBean> listaUsuariosAtivos(){
		ArrayList<UsuarioBean> usuariosBean = new ArrayList<UsuarioBean>();
		List<Usuario> usuarios;
		UsuarioBusinessLogic logicaUsuario = new UsuarioBusinessLogic();
		usuarios =  logicaUsuario.listarAtivos();
		for (Usuario usuario : usuarios)
			usuariosBean.add(new UsuarioBean(usuario.getId(),usuario.getLogin(),usuario.getNome(),usuario.getSenha(),usuario.getAtivo()));
		return usuariosBean;
		
	}
	
	public List<UsuarioBean> listaUsuarios(){
		ArrayList<UsuarioBean> usuariosBean = new ArrayList<UsuarioBean>();
		List<Usuario> usuarios;
		UsuarioBusinessLogic logicaUsuario = new UsuarioBusinessLogic();
		usuarios =  logicaUsuario.listar();
		for (Usuario usuario : usuarios)
			usuariosBean.add(new UsuarioBean(usuario.getId(),usuario.getLogin(),usuario.getNome(),usuario.getSenha(),usuario.getAtivo()));
		return usuariosBean;
		
	}
	
	public List<UsuarioBean> listaUsuariosInativos(){
		ArrayList<UsuarioBean> usuariosBean = new ArrayList<UsuarioBean>();
		List<Usuario> usuarios;
		UsuarioBusinessLogic logicaUsuario = new UsuarioBusinessLogic();
		usuarios =  logicaUsuario.listarInativos();
		for (Usuario usuario : usuarios)
			usuariosBean.add(new UsuarioBean(usuario.getId(),usuario.getLogin(),usuario.getNome(),usuario.getSenha(),usuario.getAtivo()));
		return usuariosBean;
		
	}
}
