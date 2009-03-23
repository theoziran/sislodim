package br.faculdadeidez.psa.tests;

import br.faculdadeidez.psa.businesslogic.UsuarioBusinessLogic;
import br.faculdadeidez.psa.entity.Usuario;
import junit.framework.TestCase;

public class testUsuarioBusinessLogic extends TestCase {

	public void testCreate() {
		Usuario u = new Usuario();
		u.setId(1);
		u.setLogin("testLogin1");
		u.setNome("testName1");
		u.setSenha("testPassword1");
		u.setCpf("12345678901");
		u.setRg("1234567");
		u.setOrgExpeditor("SSP-PB");
		u.setAtivo(1);

		UsuarioBusinessLogic ubl = new UsuarioBusinessLogic();
		assertEquals("inserido", ubl.create(u));
	}

	public void testLogon() {

	}
}
