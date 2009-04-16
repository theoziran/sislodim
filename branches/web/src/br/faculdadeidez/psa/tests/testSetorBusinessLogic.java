package br.faculdadeidez.psa.tests;

import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import br.faculdadeidez.psa.businesslogic.SetorBusinessLogic;
import br.faculdadeidez.psa.vo.SetorVO;

public class testSetorBusinessLogic extends TestCase{
	private SetorVO s;
	private SetorBusinessLogic sbl = new SetorBusinessLogic();
	private ArrayList<SetorVO> listSetoresValidos = new ArrayList<SetorVO>();
		
	protected void setUp() throws Exception {
		this.listSetoresValidos.add(new SetorVO("Setor Tal",true));
		this.listSetoresValidos.add(new SetorVO("Outro Setor",true));
		this.listSetoresValidos.add(new SetorVO("Setor N Ativo",true));
	}
	
	public void testCreateValidos() {
		/**
		 * Test case - TC1.1.1 - Valores Válidos
		 **/
		assertEquals("inserido", sbl.create(this.listSetoresValidos.get(0)));
		assertEquals("inserido", sbl.create(this.listSetoresValidos.get(1)));
		assertEquals("inserido", sbl.create(this.listSetoresValidos.get(2)));
	}
	
	public void testCreateNulos() {
		/**
		 * Test case - TC1.1.2 - Valores Nulos
		 **/
		// codigo nulo
		s = new SetorVO("Codigo Nulo",true);
		assertEquals("problemaInserir", sbl.create(s));
		
		// nome nulo
		s = new SetorVO(null,true);
		assertEquals("problemaInserir", sbl.create(s));
	}

	public void testCreateValoresExistentes() {
		/**
		 * Test case - TC1.1.3 - Valores Existentes
		 **/
		// codigo duplicado
		s = new SetorVO("Test Existente",true);
		assertEquals("setorExistente", sbl.create(s));
		
		// nome duplicado
		s = new SetorVO("Setor Tal",true);
		assertEquals("setorExistente", sbl.create(s));
		
		//nome duplicado,
		//vide testCreateDuplicateSetor()
		
	}
	
	public void testCreateDuplicateSetor(){
		List<SetorVO> ls;
		ls = sbl.pesquisarByCodigo("1111");
		s = ls.get(0);
		s.setAtivo(false);
		assertEquals("atualizado", sbl.update(s));
		
		//nome duplicado,
		//mas o setor existente no banco n está ativo
		//obs: isso n se aplica ao "codigo" pq o mesmo é PRIMARY KEY
		s = new SetorVO("Setor N Ativo",true);
		assertEquals("inserido", sbl.create(s));
	}

	
	public void testCreateInvalidos() {
		/**
		 * Test case - TC1.1.4
		 **/
		// codigo inválido
		// lenght < 4
		s = new SetorVO("Setor Codigo Invalido",true);
		assertEquals("O código deve ser apenas 4 dígitos, ", sbl.create(s));

		/*// codigo inválido
		// length > 4
		s.setCodigo("10000");
		assertEquals("O código deve ser apenas 4 dígitos, ", sbl.create(s));
		
		// codigo inválido
		// caracteres especiais
		s.setCodigo("#$%*");
		assertEquals("O código deve ser apenas 4 dígitos, ", sbl.create(s));
		
		// codigo inválido
		// String de caracteres normais
		s.setCodigo("ABCD");
		assertEquals("O código deve ser apenas 4 dígitos, ", sbl.create(s));
	
		// codigo inválido
		// String Vazia
		s.setCodigo("");
		assertEquals("O código deve ser apenas 4 dígitos, ", sbl.create(s));
		*/
//		// nome inválido
//		// String numerico
//		s = new SetorVO("9999","32413541",true);
//		assertEquals("O nome é inválido, ", sbl.create(s));
//		
//		// nome inválido
//		// String vazia 
//		s.setNome("");
//		assertEquals("O nome é inválido, ", sbl.create(s));
//
//		// nome inválido
//		// Caracteres especiais 
//		s.setNome("@#$%");
//		assertEquals("O nome é inválido, ", sbl.create(s));

	}
	
	public void testDelete() {
		assertEquals("removido", sbl.delete(listSetoresValidos.remove(1)));
		s = new SetorVO("Setor Inexistente", true);
		assertEquals("setorInexistente", sbl.delete(s));
	}

	public void testUpdate() {
		List<SetorVO> ls;

		{
			/**
			 * Test case - TC1.3.1
			 **/
			ls = sbl.pesquisarByCodigo("1234");
			s = ls.get(0);
			assertEquals("atualizado", sbl.update(s));

		}

		{
			/**
			 * Test case - TC1.3.2
			 **/
			ls = sbl.pesquisarByCodigo("9999");
			if (ls.isEmpty())
				s = null;
			assertEquals("setorInexistente", sbl.update(new SetorVO("Setor Inexistente",false)));
		}

		{
			/**
			 * Test case - TC1.3.3
			 **/

			ls = sbl.pesquisarByCodigo("1234");
			s = ls.get(0);
/*
			// codigo nulo
			s.setCodigo(null);
			assertEquals("problemaAtualizar", sbl.update(s));

			// nome nulo
			s.setCodigo("1234");
			s.setNome(null);
			assertEquals("problemaAtualizar", sbl.update(s));
*/
			// ativo nulo
			s.setNome("Ativo Nulo");
			s.setAtivo(null);
			assertEquals("problemaAtualizar", sbl.update(s));
			
		}

		{
			/**
			 * Test case - TC1.3.5
			 **/

			ls = sbl.pesquisarByCodigo("1234");
			s = ls.get(0);
			
		/*	// codigo inválido
			// lenght < 4
			s.setCodigo("999");
			assertEquals("O código deve ser apenas 4 dígitos, ", sbl.update(s));

			// codigo inválido
			// length > 4
			s.setCodigo("10000");
			assertEquals("O código deve ser apenas 4 dígitos, ", sbl.update(s));
			
			// codigo inválido
			// caracteres especiais
			s.setCodigo("#$%*");
			assertEquals("O código deve ser apenas 4 dígitos, ", sbl.update(s));
			
			// codigo inválido
			// String de caracteres normais
			s.setCodigo("ABCD");
			assertEquals("O código deve ser apenas 4 dígitos, ", sbl.update(s));
		
			// codigo inválido
			// String Vazia
			s.setCodigo("");
			assertEquals("O código deve ser apenas 4 dígitos, ", sbl.update(s));
			*/
//			// nome inválido
//			// String numerico
//			s.setCodigo("9999");
//			s.setNome("32413541");
//			assertEquals("nomeSetorInvalido", sbl.update(s));
//			
//			// nome inválido
//			// String vazia 
//			s.setNome("");
//			assertEquals("nomeSetorInvalido", sbl.update(s));
//
//			// nome inválido
//			// Caracteres especiais 
//			s.setNome("@#$%");
//			assertEquals("nomeSetorInvalido", sbl.update(s));
			
		}
	}
	
	public void testListar() {
		assertEquals(2, sbl.listarAtivos().size());
		assertEquals(4, sbl.listar().size());
	}

	public void testPesquisar() {
		List<SetorVO> ls;

		ls = sbl.pesquisarByCodigo("1234");
		assertEquals(1, ls.size());

		ls = sbl.pesquisarByCodigo("9999");
		assertEquals(0, ls.size());
		
		ls = sbl.pesquisar("Setor Tal");
		assertEquals(1, ls.size());

		ls = sbl.pesquisar("Setor que n existe");
		assertEquals(0, ls.size());
		
	}

}
