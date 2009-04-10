package br.faculdadeidez.psa.facade;

import java.util.List;

import br.faculdadeidez.psa.businesslogic.BairroBusinessLogic;
import br.faculdadeidez.psa.businesslogic.SetorBusinessLogic;
import br.faculdadeidez.psa.businesslogic.UsuarioBusinessLogic;
import br.faculdadeidez.psa.businesslogic.ViaturaBusinessLogic;
import br.faculdadeidez.psa.vo.BairroVO;
import br.faculdadeidez.psa.vo.SetorVO;
import br.faculdadeidez.psa.vo.UsuarioVO;
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

	public String deleteUsuario(UsuarioVO userVO){
				
		UsuarioBusinessLogic logica = new UsuarioBusinessLogic();
		return logica.delete(userVO.getId());
	}
	
	public String updateUsuario(UsuarioVO userVO){
		
		UsuarioBusinessLogic logicaUsuario = new UsuarioBusinessLogic();
		return logicaUsuario.update(userVO);
	}
	
	public String createUsuario(UsuarioVO userVO){
		UsuarioBusinessLogic logicaUsuario = new UsuarioBusinessLogic();
		return logicaUsuario.create(userVO);
	}	


	
	public List<UsuarioVO> listaUsuarios(){
		UsuarioBusinessLogic logicaUsuario = new UsuarioBusinessLogic();
		return  logicaUsuario.listarAtivos();
		
	}
	
	
	
	public List<UsuarioVO> pesquisaUsuario(String nome){
		UsuarioBusinessLogic logicaUsuario = new UsuarioBusinessLogic();
		return  logicaUsuario.pesquisar(nome);
		
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
		return new SetorBusinessLogic().listarAtivos();
	}	
	
	public List<SetorVO> pesquisaSetor(String nome){
		SetorBusinessLogic logicaSetor = new SetorBusinessLogic();
		return  logicaSetor.pesquisar(nome);
		
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
	
	public List<ViaturaVO> pesquisaViatura(String nome){
		ViaturaBusinessLogic logicaViatura = new ViaturaBusinessLogic();
		return logicaViatura.pesquisar(nome);
	}
	
	/**
	 * Início da parte dos bairros
	 * 
	 * @return lista de bairros cadastrados
	 */
	public List<BairroVO> listarBairros(){
		BairroBusinessLogic logicaBairro = new BairroBusinessLogic();
		return logicaBairro.listar();
	}
	
	public BairroVO pesquisaBairro(int chave){
		return new BairroBusinessLogic().find(chave);
	}
}
