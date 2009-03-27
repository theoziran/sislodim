package br.faculdadeidez.psa.facade;

import java.util.ArrayList;
import java.util.List;

import br.faculdadeidez.psa.apresentacao.managedbean.UsuarioBean;
import br.faculdadeidez.psa.apresentacao.managedbean.ViaturaBean;
import br.faculdadeidez.psa.businesslogic.SetorBusinessLogic;
import br.faculdadeidez.psa.businesslogic.UsuarioBusinessLogic;
import br.faculdadeidez.psa.businesslogic.ViaturaBusinessLogic;
import br.faculdadeidez.psa.db.entity.Usuario;
import br.faculdadeidez.psa.db.entity.Viatura;
import br.faculdadeidez.psa.vo.SetorVO;
import br.faculdadeidez.psa.vo.ViaturaVO;


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
		Usuario ent = new Usuario(bean.getNome(), bean.getLogin(), bean.getSenha(), bean.getId(), bean.getAtivo(),bean.getCpf(), bean.getRg(), bean.getOrgExpeditor());
		
		UsuarioBusinessLogic logicaUsuario = new UsuarioBusinessLogic();
		return logicaUsuario.update(ent);
	}
	
	public String createUsuario(UsuarioBean originalBean){
		UsuarioBean bean = originalBean;
		Usuario ent = new Usuario(bean.getNome(), bean.getLogin(), bean.getSenha(), bean.getCpf(), bean.getRg(), bean.getOrgExpeditor());
		
		UsuarioBusinessLogic logicaUsuario = new UsuarioBusinessLogic();
		return logicaUsuario.create(ent);
	}	

	public List<UsuarioBean> listaUsuariosAtivos(){
		ArrayList<UsuarioBean> usuariosBean = new ArrayList<UsuarioBean>();
		List<Usuario> usuarios;
		UsuarioBusinessLogic logicaUsuario = new UsuarioBusinessLogic();
		usuarios =  logicaUsuario.listarAtivos();
		for (Usuario usuario : usuarios)
			usuariosBean.add(new UsuarioBean(usuario.getId(),usuario.getLogin(),usuario.getNome(),usuario.getSenha(),usuario.getAtivo(), usuario.getTipoPermissao()));
		return usuariosBean;		
	}
	
	public List<UsuarioBean> listaUsuarios(){
		ArrayList<UsuarioBean> usuariosBean = new ArrayList<UsuarioBean>();
		List<Usuario> usuarios;
		UsuarioBusinessLogic logicaUsuario = new UsuarioBusinessLogic();
		usuarios =  logicaUsuario.listar();
		for (Usuario usuario : usuarios)
			usuariosBean.add(new UsuarioBean(usuario.getId(),usuario.getLogin(),usuario.getNome(),usuario.getSenha(),usuario.getAtivo(), usuario.getTipoPermissao()));
		return usuariosBean;		
	}
	
	public List<UsuarioBean> listaUsuariosInativos(){
		ArrayList<UsuarioBean> usuariosBean = new ArrayList<UsuarioBean>();
		List<Usuario> usuarios;
		UsuarioBusinessLogic logicaUsuario = new UsuarioBusinessLogic();
		usuarios =  logicaUsuario.listarInativos();
		for (Usuario usuario : usuarios)
			usuariosBean.add(new UsuarioBean(usuario.getId(),usuario.getLogin(),usuario.getNome(),usuario.getSenha(),usuario.getAtivo(), usuario.getTipoPermissao()));
		return usuariosBean;		
	}
	
	/*
	 * Métodos para os Setores
	 */
	
	public String deleteSetor(SetorVO vo){		
		return new SetorBusinessLogic().delete(vo);
	}
	
	public String updateSetor(SetorVO vo){
		return new SetorBusinessLogic().update(vo);
	}
	
	public String createSetor(SetorVO vo){
		return new SetorBusinessLogic().create(vo);
	}
	
	public List<SetorVO> listarSetores()
	{
		return new SetorBusinessLogic().listar();
	}	
	
	/*
	 * Métodos para as Viaturas
	 */
	
	public String deleteViatura(ViaturaVO vo){		
		return new ViaturaBusinessLogic().delete(vo);
	}
	
	public String updateViatura(ViaturaVO vo){
		return new ViaturaBusinessLogic().update(vo);
	}
	
	public String createViatura(ViaturaVO vo){
		return new ViaturaBusinessLogic().create(vo);
	}
	
	public List<ViaturaVO> listarViaturas()
	{
		return new ViaturaBusinessLogic().listar();
	}	
}
