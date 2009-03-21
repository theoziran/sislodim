package br.faculdadeidez.psa.businesslogic;



import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.faculdadeidez.psa.db.DAOUsuario;
import br.faculdadeidez.psa.entity.Usuario;


public class UsuarioBusinessLogic {

	private final Usuario convertUsuario(String nome, String login, String senha, int id, int ativo){
		Usuario user = new Usuario();
		user.setLogin(login);
		user.setNome(nome);
		user.setSenha(senha);
		user.setId(id);
		user.setAtivo(ativo);
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
					if (obj.getAtivo()==1){
						FacesContext context = FacesContext.getCurrentInstance();  
						HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
						session.setAttribute("userLogin", true);
						System.out.println("Logado com sucesso");
						return "logado";
					}
				}
			}
			throw new Exception();
		} catch (Exception e) {
			System.out.println("Usuário não existe ou senha incorreta");
			return "naoEncontrado";
		}
	}

	public String delete(int id){
		try {
			DAOUsuario dUsuario = new DAOUsuario();
			Usuario user = dUsuario.find( id );
			user.setAtivo(0);
			dUsuario.update(user);
			return "removido";
		} catch (Exception e) {
			// TODO: handle exception
			return "problemaRemover";
		}
	}
	
	public String update(String nome, String login, String senha, int id, int ativo){
		try {
			DAOUsuario dUsuario = new DAOUsuario();
			Usuario user = convertUsuario(nome, login, senha, id, ativo);
			if (user.getSenha().equals("")){
				user.setSenha(dUsuario.find(user.getId()).getSenha());
			}
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
			user.setAtivo(1);
			dUsuario.persist(user);
			return "inserido";
		} catch (Exception e) {
			// TODO: handle exception
			return "problemaInserir";
		}
	}
	

	public List listar(){
		DAOUsuario dUsuario = new DAOUsuario();
		List usuarios = dUsuario.findAll();
		return usuarios;
		
	}
	
	public List listarAtivos(){
		DAOUsuario dUsuario = new DAOUsuario();
		List usuarios = dUsuario.findAllActive();
		return usuarios;
		
	}

	
	public List listarInativos(){
		DAOUsuario dUsuario = new DAOUsuario();
		List usuarios = dUsuario.findAllInactive();
		return usuarios;
		
	}

	
}
