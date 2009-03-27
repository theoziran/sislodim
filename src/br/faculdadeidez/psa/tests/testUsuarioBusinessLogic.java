package br.faculdadeidez.psa.tests;

import junit.framework.TestCase;
import br.faculdadeidez.psa.businesslogic.UsuarioBusinessLogic;
import br.faculdadeidez.psa.vo.UsuarioVO;

public class testUsuarioBusinessLogic extends TestCase {

	public void testCreate() {
		UsuarioVO u;
		UsuarioBusinessLogic ubl = new UsuarioBusinessLogic();

		{
			/**
			 * Test case - TC1.1.1
			 **/

			u = new UsuarioVO();

			u.setLogin("testLogin1");
			u.setNome("testName1");
			u.setSenha("testPassword1");
			u.setCpf("22616487403");
			u.setRg("1234501");
			u.setOrgExpeditor("SSP-PB");
			u.setTipoPermissao(1);
			u.setAtivo(1);

			assertEquals("inserido", ubl.create(u));

			u = new UsuarioVO();

			u.setLogin("testLogin2");
			u.setNome("testName2");
			u.setSenha("testPassword2");
			u.setCpf("71741487510");
			u.setRg("1234502");
			u.setOrgExpeditor("SSP-PB");
			u.setAtivo(1);
			u.setTipoPermissao(2);
			assertEquals("inserido", ubl.create(u));
		}

		{
			/**
			 * Test case - TC1.1.2
			 **/

			u = new UsuarioVO();

			u.setLogin("testLogin3");
			u.setNome("testName3");
			u.setSenha("testPassword3");
			u.setCpf("93235080716");
			u.setRg("1234503");
			u.setOrgExpeditor("SSP-PB");
			u.setAtivo(1);
			u.setTipoPermissao(1);
			
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

			u = new UsuarioVO();

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
			u.setCpf("22616487403");
			assertEquals("cpfExistente", ubl.create(u));

			// rg duplicado
			u.setCpf("93235080716");
			u.setRg("1234501");
			u.setOrgExpeditor("SSP-PB");
			assertEquals("rgExistente", ubl.create(u));
		}

		{
			/**
			 * Test case - TC1.1.4
			 **/

			u = new UsuarioVO();

			u.setLogin("testLogin3");
			u.setNome("testName3");
			u.setSenha("testPassword3");
			u.setCpf("93235080716");
			u.setRg("1234503");
			u.setOrgExpeditor("SSP-PB");
			u.setTipoPermissao(2);
			u.setAtivo(1);

			// login inválido
			// string vazia
			u.setLogin("");
			assertEquals("dadoInvalido", ubl.create(u));

			// string numérica
			u.setLogin("123456");
			assertEquals("dadoInvalido", ubl.create(u));

			u.setLogin("testLogin3");

			// nome inválido
			// string vazia
			u.setNome("");
			assertEquals("dadoInvalido", ubl.create(u));

			// string numérica
			u.setNome("123456");
			assertEquals("dadoInvalido", ubl.create(u));

			// caracteres especiais
			u.setNome("joao%* da silva");
			assertEquals("dadoInvalido", ubl.create(u));

			u.setNome("testName3");

			// rg inválido
			// string vazia
			u.setRg("");
			assertEquals("dadoInvalido", ubl.create(u));

			// string com texto
			u.setRg("testRg3");
			assertEquals("dadoInvalido", ubl.create(u));

			// caracteres especiais
			u.setRg("$gsdf%");
			assertEquals("dadoInvalido", ubl.create(u));

			u.setRg("1234503");

			// orgão expedidor inválido
			// string vazia
			u.setOrgExpeditor("");
			assertEquals("dadoInvalido", ubl.create(u));

			// string numérica
			u.setOrgExpeditor("12345");
			assertEquals("dadoInvalido", ubl.create(u));

			u.setOrgExpeditor("SSP-PB");

			// cpf inválido
			// string vazia
			u.setCpf("");
			assertEquals("dadoInvalido", ubl.create(u));

			// string numérica
			u.setCpf("testCpf1");
			assertEquals("dadoInvalido", ubl.create(u));

			// caracteres especiais
			u.setCpf("234*¬");
			assertEquals("dadoInvalido", ubl.create(u));

			// tipo de permissao inválido
			u.setOrgExpeditor("93235080716");
			u.setTipoPermissao(0);
			assertEquals("dadoInvalido", ubl.create(u));
		}
	}
}
