package br.faculdadeidez.psa.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import junit.framework.TestCase;
import br.faculdadeidez.psa.businesslogic.BairroBusinessLogic;
import br.faculdadeidez.psa.businesslogic.SetorBusinessLogic;
import br.faculdadeidez.psa.vo.BairroVO;
import br.faculdadeidez.psa.vo.SetorVO;

public class testSetorBusinessLogic extends TestCase{
	private SetorVO s;
	private SetorBusinessLogic sbl = new SetorBusinessLogic();
	private ArrayList<SetorVO> listSetoresValidos = new ArrayList<SetorVO>();
	
	private BairroBusinessLogic bbl = new BairroBusinessLogic();
	private Vector<BairroVO> listBairros = (Vector<BairroVO>) bbl.listar();
		
	protected void setUp() throws Exception {
		this.listSetoresValidos.add(new SetorVO("Setor Tal",true, listBairros));
		this.listSetoresValidos.add(new SetorVO("Outro Setor",true, listBairros));
		this.listSetoresValidos.add(new SetorVO("Setor N Ativo",true, listBairros));
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
		// nome nulo
		s = new SetorVO(null,true, new ArrayList<BairroVO>());
		assertEquals("problemaInserir", sbl.create(s));
		
		// bairros nulos
		s = new SetorVO("Setor teste",true, null);
		assertEquals("problemaInserir", sbl.create(s));
	}

	public void testCreateValoresExistentes() {
		/**
		 * Test case - TC1.1.3 - Valores Existentes
		 **/
		// nome duplicado
		s = new SetorVO("Setor Tal",true, listBairros);
		assertEquals("setorExistente", sbl.create(s));
	}
	
	/*public void testCreateDuplicatedSetor(){
		List<SetorVO> ls;
		ls = sbl.pesquisar("Setor Tal");
		s = ls.get(0);
		s.setAtivo(false);
		assertEquals("atualizado", sbl.update(s));
		
		//nome duplicado,
		//mas o setor existente no banco n está ativo
		//obs: isso n se aplica ao "codigo" pq o mesmo é PRIMARY KEY
		s = new SetorVO("Setor N Ativo",true);
		assertEquals("inserido", sbl.create(s));
	}*/

	
	public void testCreateInvalidos() {
		/**
		 * Test case - TC1.1.4
		 **/
//		// nome inválido
//		// String numerico
//		s = new SetorVO(9999","32413541",true);
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
		List<SetorVO> ls; 
		ls = sbl.pesquisar("Outro Setor");
		assertEquals("removido", sbl.delete(ls.get(0)));
		
		s = new SetorVO(999,"Setor q n existe",true, listBairros);
		assertEquals("setorInexistente", sbl.delete(s));
	}

	public void testUpdate() {
		List<SetorVO> ls;

		{
			/**
			 * Test case - TC1.3.1
			 **/
			ls = sbl.pesquisar("Setor Tal");
			s = ls.get(0);
			assertEquals("atualizado", sbl.update(s));

		}

		{
			/**
			 * Test case - TC1.3.2
			 **/
			ls = sbl.pesquisar("Setor que n existe");
			if (ls.isEmpty())
				s = null;
			assertEquals("setorInexistente", sbl.update(new SetorVO("Setor Inexistente",false)));
		}

		{
			/**
			 * Test case - TC1.3.3
			 **/

			ls = sbl.pesquisar("Setor Tal");
			s = ls.get(0);

			// nome nulo
			s.setNome(null);
			assertEquals("problemaAtualizar", sbl.update(s));

			// ativo nulo
			s.setNome("Ativo Nulo");
			s.setAtivo(null);
			assertEquals("problemaAtualizar", sbl.update(s));
			
			// bairros nulo
			s.setAtivo(true);
			s.setBairros(null);
			assertEquals("problemaAtualizar", sbl.update(s));

		}

		{
			/**
			 * Test case - TC1.3.5
			 **/

			ls = sbl.pesquisar("Setor Tal");
			s = ls.get(0);
			
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
		
		ls = sbl.pesquisar("Setor Tal");
		assertEquals(1, ls.size());

		ls = sbl.pesquisar("Setor que n existe");
		assertEquals(0, ls.size());
	}

}
