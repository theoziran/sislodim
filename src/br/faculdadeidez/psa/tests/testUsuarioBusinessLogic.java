package br.faculdadeidez.psa.tests;

import br.faculdadeidez.psa.businesslogic.UsuarioBusinessLogic;
import br.faculdadeidez.psa.db.entity.Usuario;
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

			u.setLogin("testLogin3");
			u.setNome("testName3");
			u.setSenha("testPassword3");
			u.setCpf("93235080716");
			u.setRg("1234503");
			u.setOrgExpeditor("SSP-PB");
			u.setAtivo(1);

			// login nulo
			u.setLogin(null);
			assertEquals("problemaInserir", ubl.create(u));

			// nome nulo
			u.setLogin("testLogin3");
			u.setNome(null);
			assertEquals("problemaInserir", ubl.create(u));

			// senha nula
			u.setNome("testName3");
			u.setSenha(null);
			assertEquals("problemaInserir", ubl.create(u));

			// cpf nulo
			u.setSenha("testPassword3");
			u.setCpf(null);
			assertEquals("problemaInserir", ubl.create(u));

			// rg nulo
			u.setCpf("93235080716");
			u.setRg(null);
			assertEquals("problemaInserir", ubl.create(u));

			// orgao expedidor nulo
			u.setRg("1234503");
			u.setOrgExpeditor(null);
			assertEquals("problemaInserir", ubl.create(u));

		}

		{
			/**
			 * Test case - TC1.1.3
			 **/

			u = new Usuario();

			u.setLogin("testLogin3");
			u.setNome("testName3");
			u.setSenha("testPassword3");
			u.setCpf("93235080716");
			u.setRg("1234503");
			u.setOrgExpeditor("SSP-PB");
			u.setAtivo(1);

			// login duplicado
			u.setLogin("testLogin1");
			assertEquals("usuarioExistente", ubl.create(u));

			// cpf duplicado
			u.setLogin("testLogin3");
			u.setCpf("26151813600");
			assertEquals("cpfExistente", ubl.create(u));

			// rg duplicado
			u.setCpf("93235080716");
			u.setRg("1234501");
			u.setOrgExpeditor("SSP-PB");
			assertEquals("rgExistente", ubl.create(u));
		}
	}
}
