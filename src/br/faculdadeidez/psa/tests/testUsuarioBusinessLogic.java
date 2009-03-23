package br.faculdadeidez.psa.tests;
import br.faculdadeidez.psa.businesslogic.UsuarioBusinessLogic;
import br.faculdadeidez.psa.entity.Usuario;
import junit.framework.TestCase;


public class testUsuarioBusinessLogic extends TestCase {

	public void testCreate(){
		//TODO adaptar os campos de usuário a nova implementação
		
		Usuario u = new Usuario();
		u.setId(1);
		u.setLogin("testLogin1");
		u.setNome("testName1");
		u.setSenha("testPassword1");
		u.setAtivo(1);
		
		UsuarioBusinessLogic ubl = new UsuarioBusinessLogic();
		assertEquals("inserido", ubl.create(u));
	}
	
	public void testLogon(){
		
	}
}
