package br.faculdadeidez.psa.tests;

import br.faculdadeidez.psa.businesslogic.UsuarioBusinessLogic;
import br.faculdadeidez.psa.db.DAOUsuario;
import br.faculdadeidez.psa.entity.Usuario;
import junit.framework.TestCase;

public class testUsuarioBusinessLogic extends TestCase {

	public void testCreate() {
		Usuario u = new Usuario();
		UsuarioBusinessLogic ubl = new UsuarioBusinessLogic();

		{
			/**
			 * Test case - TC1.1.1
			 **/

			u.setId(1);
			u.setLogin("testLogin1");
			u.setNome("testName1");
			u.setSenha("testPassword1");
			u.setCpf("22616487403");
			u.setRg("1234501");
			u.setOrgExpeditor("SSP-PB");
			u.setAtivo(1);

			assertEquals("inserido", ubl.create(u));

			u.setId(2);
			u.setLogin("testLogin2");
			u.setNome("testName2");
			u.setSenha("testPassword2");
			u.setCpf("71741487510");
			u.setRg("1234502");
			u.setOrgExpeditor("SSP-PB");
			u.setAtivo(1);

			assertEquals("inserido", ubl.create(u));
		}

		{
			/**
			 * Test case - TC1.1.2
			 **/

			u.setId(3);
			u.setLogin(null);
			u.setNome("testName3");
			u.setSenha("testPassword3");
			u.setCpf("93235080716");
			u.setRg("1234503");
			u.setOrgExpeditor("SSP-PB");
			u.setAtivo(1);

			assertEquals("problemaInserir", ubl.create(u));

			u.setId(4);
			u.setLogin("testLogin4");
			u.setNome(null);
			u.setSenha("testPassword4");
			u.setCpf("26151813600");
			u.setRg("1234504");
			u.setOrgExpeditor("SSP-PB");
			u.setAtivo(1);

			assertEquals("problemaInserir", ubl.create(u));

			u.setId(5);
			u.setLogin("testLogin5");
			u.setNome("testName5");
			u.setSenha(null);
			u.setCpf("14419888407");
			u.setRg("1234505");
			u.setOrgExpeditor("SSP-PB");
			u.setAtivo(1);

			assertEquals("problemaInserir", ubl.create(u));

			u.setId(6);
			u.setLogin("testLogin6");
			u.setNome("testName6");
			u.setSenha("testPassword6");
			u.setCpf(null);
			u.setRg("1234506");
			u.setOrgExpeditor("SSP-PB");
			u.setAtivo(1);

			assertEquals("problemaInserir", ubl.create(u));
		}
	}

	public void testLogon() {

	}
}
