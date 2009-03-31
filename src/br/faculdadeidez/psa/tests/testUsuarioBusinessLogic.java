package br.faculdadeidez.psa.tests;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import br.faculdadeidez.psa.businesslogic.UsuarioBusinessLogic;
import br.faculdadeidez.psa.vo.UsuarioVO;

public class testUsuarioBusinessLogic extends TestCase {
	UsuarioVO u;
	UsuarioVO usuarioInvalido;
	UsuarioBusinessLogic ubl = new UsuarioBusinessLogic();
	List<UsuarioVO> listUsuariosValidos = new ArrayList<UsuarioVO>();

	@Override
	protected void setUp() throws Exception {

		u = new UsuarioVO();

		u.setLogin("testLogin1");
		u.setNome("testNameOne");
		u.setSenha("testPassword1");
		u.setCpf("22616487403");
		u.setRg("1234501");
		u.setOrgExpeditor("SSP-PB");
		u.setTipoPermissao(1);
		u.setAtivo(1);

		listUsuariosValidos.add(u);

		u = new UsuarioVO();

		u.setLogin("testLogin2");
		u.setNome("testNameTwo");
		u.setSenha("testPassword2");
		u.setCpf("71741487510");
		u.setRg("1234502");
		u.setOrgExpeditor("SSP-PB");
		u.setAtivo(1);
		u.setTipoPermissao(2);

		listUsuariosValidos.add(u);

		u = new UsuarioVO();

		u.setLogin("testLogin4");
		u.setNome("testNameFour");
		u.setSenha("testPassword4");
		u.setCpf("12345678904");
		u.setRg("1234504");
		u.setOrgExpeditor("SSP-PB");
		u.setAtivo(1);
		u.setTipoPermissao(2);

		listUsuariosValidos.add(u);

		usuarioInvalido = new UsuarioVO();

		usuarioInvalido.setLogin("testLogin3");
		usuarioInvalido.setNome("testNameTres");
		usuarioInvalido.setSenha("testPassword3");
		usuarioInvalido.setCpf("93235080716");
		usuarioInvalido.setRg("1234503");
		usuarioInvalido.setOrgExpeditor("SSP-PB");
		usuarioInvalido.setAtivo(1);
		usuarioInvalido.setTipoPermissao(1);

	}

	public void testCreateValido() {
		/**
		 * Test case - TC1.1.1 - Valores Válidos
		 **/

		assertEquals("inserido", ubl.create(listUsuariosValidos.get(0)));
		assertEquals("inserido", ubl.create(listUsuariosValidos.get(1)));
		assertEquals("inserido", ubl.create(listUsuariosValidos.get(2)));

	}

	public void testCreateNulos() {
		/**
		 * Test case - TC1.1.2 - Valores Nulos
		 **/

		u = usuarioInvalido;

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

	public void testCreateValoresExistentes() {
		/**
		 * Test case - TC1.1.3 - Valores Existentes
		 **/

		u = usuarioInvalido;

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

	public void testCreateInvalidos() {
		/**
		 * Test case - TC1.1.4
		 **/

		u = usuarioInvalido;

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

	public void testDelete() {
		List<UsuarioVO> ls;

		/**
		 * Test case - TC1.2.1
		 **/
		ls = ubl.pesquisar("testLogin2");
		u = ls.get(0);
		assertEquals("removido", ubl.delete(u.getId()));

		/**
		 * Test case - TC1.2.3
		 **/
		assertEquals("usuarioInexistente", ubl.delete(99999));
	}

	public void testUpdate() {
		List<UsuarioVO> ls;

		{
			/**
			 * Test case - TC1.3.1
			 **/
			ls = ubl.pesquisar("testLogin1");
			u = ls.get(0);
			assertEquals("atualizado", ubl.update(u));

		}

		{
			/**
			 * Test case - TC1.3.2
			 **/
			ls = ubl.pesquisar("testLogin3");
			if (ls.isEmpty())
				u = null;
			assertEquals("usuarioInexistente", ubl.update(u));
		}

		{
			/**
			 * Test case - TC1.3.3
			 **/

			ls = ubl.pesquisar("testLogin1");
			u = ls.get(0);

			// login nulo
			u.setLogin(null);
			assertEquals("problemaAtualizar", ubl.update(u));

			// nome nulo
			u.setLogin("testLogin3");
			u.setNome(null);
			assertEquals("problemaAtualizar", ubl.update(u));

			// senha nula
			u.setNome("testName3");
			u.setSenha(null);
			assertEquals("problemaAtualizar", ubl.update(u));

			// cpf nulo
			u.setSenha("testPassword3");
			u.setCpf(null);
			assertEquals("problemaAtualizar", ubl.update(u));

			// rg nulo
			u.setCpf("93235080716");
			u.setRg(null);
			assertEquals("problemaAtualizar", ubl.update(u));

			// orgao expedidor nulo
			u.setRg("1234503");
			u.setOrgExpeditor(null);
			assertEquals("problemaAtualizar", ubl.update(u));

		}

		{
			/**
			 * Test case - TC1.3.4
			 **/

			ls = ubl.pesquisar("testLogin4");
			u = ls.get(0);

			// login duplicado
			u.setLogin("testLogin1");
			assertEquals("usuarioExistente", ubl.update(u));

			// cpf duplicado
			u.setLogin("testLogin4");
			u.setCpf("22616487403");
			assertEquals("cpfExistente", ubl.update(u));

			// rg duplicado
			u.setCpf("12345678904");
			u.setRg("1234501");
			u.setOrgExpeditor("SSP-PB");
			assertEquals("rgExistente", ubl.update(u));
		}

		{
			/**
			 * Test case - TC1.3.5
			 **/

			ls = ubl.pesquisar("testLogin1");
			u = ls.get(0);

			// login inválido
			// string vazia
			u.setLogin("");
			assertEquals("dadoInvalido", ubl.update(u));

			// string numérica
			u.setLogin("123456");
			assertEquals("dadoInvalido", ubl.update(u));

			u.setLogin("testLogin3");

			// nome inválido
			// string vazia
			u.setNome("");
			assertEquals("dadoInvalido", ubl.update(u));

			// string numérica
			u.setNome("123456");
			assertEquals("dadoInvalido", ubl.update(u));

			// caracteres especiais
			u.setNome("joao%* da silva");
			assertEquals("dadoInvalido", ubl.update(u));

			u.setNome("testName3");

			// rg inválido
			// string vazia
			u.setRg("");
			assertEquals("dadoInvalido", ubl.update(u));

			// string com texto
			u.setRg("testRg3");
			assertEquals("dadoInvalido", ubl.update(u));

			// caracteres especiais
			u.setRg("$gsdf%");
			assertEquals("dadoInvalido", ubl.update(u));

			u.setRg("1234503");

			// orgão expedidor inválido
			// string vazia
			u.setOrgExpeditor("");
			assertEquals("dadoInvalido", ubl.update(u));

			// string numérica
			u.setOrgExpeditor("12345");
			assertEquals("dadoInvalido", ubl.update(u));

			u.setOrgExpeditor("SSP-PB");

			// cpf inválido
			// string vazia
			u.setCpf("");
			assertEquals("dadoInvalido", ubl.update(u));

			// string numérica
			u.setCpf("testCpf1");
			assertEquals("dadoInvalido", ubl.update(u));

			// caracteres especiais
			u.setCpf("234*¬");
			assertEquals("dadoInvalido", ubl.update(u));

			// tipo de permissao inválido
			u.setOrgExpeditor("93235080716");
			u.setTipoPermissao(0);
			assertEquals("dadoInvalido", ubl.update(u));
		}
	}

	public void testListar() {
		List<UsuarioVO> ls;

		ls = ubl.listar();
		assertEquals(3, ls.size());
	}

	public void testPesquisar() {
		List<UsuarioVO> ls;

		ls = ubl.pesquisar("testLogin1");
		assertEquals(1, ls.size());

		ls = ubl.pesquisar("12345678904");
		assertEquals(1, ls.size());

		ls = ubl.pesquisar("testLogin3");
		assertEquals(0, ls.size());

	}

	public void testLogon() {
		assertEquals("logado", ubl.logon("testLogin1", "testPassword1"));
		assertEquals("naoEncontrado", ubl.logon("testLogin2", "testPassword2"));
		assertEquals("naoEncontrado", ubl.logon("testLogin1", "123456"));
		assertEquals("camposEmBranco", ubl.logon(null, "testPassword1"));
		assertEquals("camposEmBranco", ubl.logon("", "testPassword1"));
		assertEquals("camposEmBranco", ubl.logon("testLogin1", null));
		assertEquals("camposEmBranco", ubl.logon("testLogin1", ""));

	}
}
