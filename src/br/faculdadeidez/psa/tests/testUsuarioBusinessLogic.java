package br.faculdadeidez.psa.tests;

import br.faculdadeidez.psa.businesslogic.UsuarioBusinessLogic;
import br.faculdadeidez.psa.db.DAOUsuario;
import br.faculdadeidez.psa.entity.Usuario;
import junit.framework.TestCase;

public class testUsuarioBusinessLogic extends TestCase {

	public void testCreate() {
		Usuario u;
		UsuarioBusinessLogic ubl = new UsuarioBusinessLogic();

		{
			/**
			 * Test case - TC1.1.1
			 **/

			u = new Usuario();

			u.setLogin("testLogin1");
			u.setNome("testName1");
			u.setSenha("testPassword1");
			u.setCpf("22616487403");
			u.setRg("1234501");
			u.setOrgExpeditor("SSP-PB");
			u.setAtivo(1);

			assertEquals("inserido", ubl.create(u));

			u = new Usuario();

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

			u = new Usuario();

			u.setLogin(null);
			u.setNome("testName3");
			u.setSenha("testPassword3");
			u.setCpf("93235080716");
			u.setRg("1234503");
			u.setOrgExpeditor("SSP-PB");
			u.setAtivo(1);

			assertEquals("problemaInserir", ubl.create(u));

			u = new Usuario();

			u.setLogin("testLogin4");
			u.setNome(null);
			u.setSenha("testPassword4");
			u.setCpf("26151813600");
			u.setRg("1234504");
			u.setOrgExpeditor("SSP-PB");
			u.setAtivo(1);

			assertEquals("problemaInserir", ubl.create(u));

			u = new Usuario();

			u.setLogin("testLogin5");
			u.setNome("testName5");
			u.setSenha(null);
			u.setCpf("14419888407");
			u.setRg("1234505");
			u.setOrgExpeditor("SSP-PB");
			u.setAtivo(1);

			assertEquals("problemaInserir", ubl.create(u));

			u = new Usuario();

			u.setLogin("testLogin6");
			u.setNome("testName6");
			u.setSenha("testPassword6");
			u.setCpf(null);
			u.setRg("1234506");
			u.setOrgExpeditor("SSP-PB");
			u.setAtivo(1);

			assertEquals("problemaInserir", ubl.create(u));

			u = new Usuario();

			u.setLogin("testLogin7");
			u.setNome("testName7");
			u.setSenha("testPassword7");
			u.setCpf("93235080716");
			u.setRg(null);
			u.setOrgExpeditor("SSP-PB");
			u.setAtivo(1);

			assertEquals("problemaInserir", ubl.create(u));

			u = new Usuario();

			u.setLogin("testLogin8");
			u.setNome("testName8");
			u.setSenha("testPassword8");
			u.setCpf("93235080716");
			u.setRg("1234508");
			u.setOrgExpeditor(null);
			u.setAtivo(1);

			assertEquals("problemaInserir", ubl.create(u));

			u = new Usuario();

			u.setLogin("testLogin9");
			u.setNome("testName9");
			u.setSenha("testPassword9");
			u.setCpf("93235080716");
			u.setRg(null);
			u.setOrgExpeditor(null);
			u.setAtivo(1);

			assertEquals("problemaInserir", ubl.create(u));
		}

		{
			/**
			 * Test case - TC1.1.3
			 **/

			// login duplicado
			u = new Usuario();

			u.setLogin("testLogin1");
			u.setNome("testName10");
			u.setSenha("testPassword10");
			// TODO alterar cpf
			u.setCpf("93235080716");
			u.setRg("1234510");
			u.setOrgExpeditor("SSP-PB");
			u.setAtivo(1);

			assertEquals("usuarioExistente", ubl.create(u));

			// cpf duplicado
			u = new Usuario();

			u.setLogin("testLogin11");
			u.setNome("testName11");
			u.setSenha("testPassword11");
			u.setCpf("26151813600");
			u.setRg("1234511");
			u.setOrgExpeditor("SSP-PB");
			u.setAtivo(1);

			assertEquals("cpfExistente", ubl.create(u));

			// rg duplicado
			u = new Usuario();

			u.setLogin("testLogin12");
			u.setNome("testName12");
			u.setSenha("testPassword12");
			u.setCpf("14419888407");
			u.setRg("1234501");
			u.setOrgExpeditor("SSP-PB");
			u.setAtivo(1);

			assertEquals("rgExistente", ubl.create(u));
		}
	}

	public void testLogon() {

	}
}
