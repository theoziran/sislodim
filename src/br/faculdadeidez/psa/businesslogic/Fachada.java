package br.faculdadeidez.psa.businesslogic;


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
	
	public String logon(String nome, String senha) {
		UsuarioBusinessLogic logicaUsuario = new UsuarioBusinessLogic();
		return logicaUsuario.logon(nome, senha);
	}

	public String delete(String nome, String login, String senha, int id){
		UsuarioBusinessLogic logicaUsuario = new UsuarioBusinessLogic();
		return logicaUsuario.delete(nome, login, senha, id);
	}
	
	public String update(String nome, String login, String senha, int id){
		UsuarioBusinessLogic logicaUsuario = new UsuarioBusinessLogic();
		return logicaUsuario.update(nome, login, senha, id);
	}
	
	public String create(String nome, String login, String senha){
		UsuarioBusinessLogic logicaUsuario = new UsuarioBusinessLogic();
		return logicaUsuario.create(nome, login, senha);
	}

}
