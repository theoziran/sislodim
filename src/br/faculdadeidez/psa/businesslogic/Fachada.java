package br.faculdadeidez.psa.businesslogic;

import java.util.ArrayList;
import java.util.List;

import br.faculdadeidez.psa.beans.SetorBean;
import br.faculdadeidez.psa.beans.UsuarioBean;
import br.faculdadeidez.psa.beans.ViaturaBean;
import br.faculdadeidez.psa.entity.Setor;
import br.faculdadeidez.psa.entity.Usuario;
import br.faculdadeidez.psa.entity.Viatura;


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
	
	/*
	 * Métodos para os Usuários
	 */
	
	public String logon(String login, String senha) {
		UsuarioBusinessLogic logicaUsuario = new UsuarioBusinessLogic();
		return logicaUsuario.logon(login, senha);
	}

	public String deleteUsuario(UsuarioBean originalBean){
		UsuarioBean bean = originalBean;
				
		UsuarioBusinessLogic logica = new UsuarioBusinessLogic();
		return logica.delete(bean.getId());
	}
	
	public String updateUsuario(UsuarioBean originalBean){
		UsuarioBean bean = originalBean;
		Usuario ent = new Usuario(bean.getNome(), bean.getLogin(), bean.getSenha(), bean.getId(), bean.getAtivo());
		
		UsuarioBusinessLogic logicaUsuario = new UsuarioBusinessLogic();
		return logicaUsuario.update(ent);
	}
	
	public String createUsuario(UsuarioBean originalBean){
		UsuarioBean bean = originalBean;
		Usuario ent = new Usuario(bean.getNome(), bean.getLogin(), bean.getSenha());
		
		UsuarioBusinessLogic logicaUsuario = new UsuarioBusinessLogic();
		return logicaUsuario.create(ent);
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
	
	/*
	 * Métodos para os Setores
	 */
	
	public String deleteSetor(SetorBean bean){		
		SetorBusinessLogic logica = new SetorBusinessLogic();
		return logica.delete(bean.getCodigo());
	}
	
	public String updateSetor(SetorBean bean){
		Setor ent = new Setor(bean.getCodigo(), bean.getNome());
		
		SetorBusinessLogic logica = new SetorBusinessLogic();
		return logica.update(ent);
	}
	
	public String createSetor(SetorBean bean){
		Setor ent = new Setor(bean.getCodigo(), bean.getNome());
		
		SetorBusinessLogic logica = new SetorBusinessLogic();
		return logica.create(ent);
	}
	
	public List<SetorBean> listarSetores()
	{
		ArrayList<SetorBean> beanLista = new ArrayList<SetorBean>();		
		SetorBusinessLogic logica = new SetorBusinessLogic();
		
		for (Setor objeto : logica.listar())
			beanLista.add(new SetorBean(objeto.getCodigo(), objeto.getNome()));
		
		return beanLista;
	}	
	
	/*
	 * Métodos para as Viaturas
	 */
	
	public String deleteViatura(ViaturaBean bean){		
		ViaturaBusinessLogic logica = new ViaturaBusinessLogic();
		return logica.delete(bean.getCodigo());
	}
	
	public String updateViatura(ViaturaBean bean){
		Viatura ent = new Viatura(bean.getCodigo(), bean.getOcupada());
		
		ViaturaBusinessLogic logica = new ViaturaBusinessLogic();
		return logica.update(ent);
	}
	
	public String createViatura(ViaturaBean bean){
		Viatura ent = new Viatura(bean.getCodigo(), bean.getOcupada());
		
		ViaturaBusinessLogic logica = new ViaturaBusinessLogic();
		return logica.create(ent);
	}
	
	public List<ViaturaBean> listarViaturas()
	{
		ArrayList<ViaturaBean> beanLista = new ArrayList<ViaturaBean>();		
		ViaturaBusinessLogic logica = new ViaturaBusinessLogic();
		
		for (Viatura objeto : logica.listar())
			beanLista.add(new ViaturaBean(objeto.getCodigo(), objeto.getOcupada()));
		
		return beanLista;
	}	
}
