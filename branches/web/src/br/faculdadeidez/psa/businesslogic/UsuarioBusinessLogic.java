package br.faculdadeidez.psa.businesslogic;


import java.util.List;

import br.faculdadeidez.psa.db.DAOUsuario;
import br.faculdadeidez.psa.entity.Usuario;

public class UsuarioBusinessLogic {

	private final Usuario convertUsuario(String nome, String login, String senha, int id){
		Usuario user = new Usuario();
		user.setLogin(login);
		user.setNome(nome);
		user.setSenha(senha);
		user.setId(id);
		return user;
		
	}
	
	private final Usuario convertUsuario(String nome, String login, String senha){
		Usuario user = new Usuario();
		user.setLogin(login);
		user.setNome(nome);
		user.setSenha(senha);
		return user;
		
	}
	
	public String logon(String login, String senha) {
		try {
			DAOUsuario dUsuario = new DAOUsuario();
			List<Usuario> usuarios = dUsuario.findByField("login", login);
			if (usuarios.isEmpty())
				throw new Exception();
			for (Usuario obj : usuarios) {
				if (obj.getSenha().equals(senha)){
					return "logado";
				}
			}
			throw new Exception();
		} catch (Exception e) {
			System.out.println("Usuário não existe ou senha incorreta");
			return "naoEncontrado";
		}
	}

	public String delete(String nome, String login, String senha, int id){
		try {
			DAOUsuario dUsuario = new DAOUsuario();
			Usuario user = convertUsuario(nome, login, senha, id);
			dUsuario.remove(user);
			return "removido";
		} catch (Exception e) {
			// TODO: handle exception
			return "problemaRemover";
		}
	}
	
	public String update(String nome, String login, String senha, int id){
		try {
			DAOUsuario dUsuario = new DAOUsuario();
			Usuario user = convertUsuario(nome, login, senha, id);
			dUsuario.update(user);
			return "atualizado";
		} catch (Exception e) {
			// TODO: handle exception
			return "problemaAtualizar";
		}
	}
	
	public String create(String nome, String login, String senha){
		try {
			DAOUsuario dUsuario = new DAOUsuario();
			Usuario user = convertUsuario(nome, login, senha);
			dUsuario.persist(user);
			return "inserido";
		} catch (Exception e) {
			// TODO: handle exception
			return "problemaInserir";
		}
	}
	
	public void chargeUsuarios(List usuarios){
			DAOUsuario daoUsuario = new DAOUsuario();
			usuarios = daoUsuario.findAll();
	}
	
}
