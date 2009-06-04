package br.faculdadeidez.psa.tests;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import br.faculdadeidez.psa.businesslogic.ViaturaBusinessLogic;
import br.faculdadeidez.psa.vo.ViaturaVO;

/**
 * Classe de teste responsável por garantir o funcionamento de ViaturaBusinessLogic
 */
public class testViaturaBusinessLogic extends TestCase {
	
	/**
	 * Objeto utilizado para garantir os testes 
	 */
	private ViaturaVO v;
	
	/**
	 * Resposável por simular o funcionamento dos testes
	 */
	private ViaturaBusinessLogic vbl = new ViaturaBusinessLogic();
	
	/**
	 * Objeto utilizado para garantir os testes 
	 */
	private ArrayList<ViaturaVO> listViaturasValidas = new ArrayList<ViaturaVO>();
	
	
	/**
	 * Método para intanciar as propriedades
	 */
	@Override
	protected void setUp() throws Exception {
		this.listViaturasValidas.add(new ViaturaVO("1234",false, false));
		this.listViaturasValidas.add(new ViaturaVO("1235",true, true));
	}
	
	/**
	 * Test case - TC1.1.1 - Valores Válidos
	 */
	public void testCreateValidos() {
		assertEquals("inserido", vbl.create(this.listViaturasValidas.get(0)));
		assertEquals("inserido", vbl.create(this.listViaturasValidas.get(1)));
	}
	
	/**
	 * Test case - TC1.1.2 - Valores Nulos
	 */
	public void testCreateNulos() {
		// codigo nulo
		v = new ViaturaVO(null,false, false);
		assertEquals("problemaInserir", vbl.create(v));

		/*// ocupada nulo
		v.setCodigo("5555");
		v.setOcupada(null);
		assertEquals("problemaInserir", vbl.create(v));*/
	}
	
	/**
	 * Test case - TC1.1.3 - Valores Existentes
	 */
	public void testCreateValoresExistentes() {
		// codigo duplicado
		v = new ViaturaVO("1234",false, false);
		assertEquals("viaturaExistente", vbl.create(v));
	}
	
	
	/**
	 * Test case - TC1.1.4
	 */
	public void testCreateInvalidos() {
		// codigo inválido
		// string vazia
		v = new ViaturaVO("",false, false);
		assertEquals("O código é obrigatório", vbl.create(v));

		// string.length > 4
		v.setCodigo("12345");
		assertEquals("O código deve ser menor que 4 dígitos", vbl.create(v));
		
		// caracteres especiais
		v.setCodigo("#$%*");
		assertEquals("O código deve ser apenas dígitos", vbl.create(v));
		
	}
	
	/**
	 * Testa o método delete do ViaturaBusinessLogic
	 */
	public void testDelete() {
		assertEquals("removido", vbl.delete(listViaturasValidas.remove(1)));
		v = new ViaturaVO("9999",false, false);
		assertEquals("viaturaInexistente", vbl.delete(v));
	}
	
	/**
	 * Testa o método pesquisar do ViaturaBusinessLogic
	 */
	public void testUpdate() {
		List<ViaturaVO> ls;

		{
			/**
			 * Test case - TC1.3.1
			 **/
			ls = vbl.pesquisar("1234");
			v = ls.get(0);
			v.setAtivo(true);
			assertEquals("atualizado", vbl.update(v));

		}

		{
			/**
			 * Test case - TC1.3.2
			 **/
			ls = vbl.pesquisar("9999");
			if (ls.isEmpty())
				v = null;
			assertEquals("viaturaInexistente", vbl.update(new ViaturaVO("9999",false, false)));
		}

		{
			/**
			 * Test case - TC1.3.3
			 **/

			ls = vbl.pesquisar("1234");
			v = ls.get(0);

			// codigo nulo
			v.setCodigo(null);
			assertEquals("problemaAtualizar", vbl.update(v));

			/*// ocupada nulo
			v.setCodigo("1234");
			v.setOcupada(null);
			assertEquals("problemaAtualizar", vbl.update(v));*/
		}

		{
			/**
			 * Test case - TC1.3.5
			 **/

			ls = vbl.pesquisar("1234");
			v = ls.get(0);

			// codigo inválido
			// string vazia
			v.setCodigo("");
			assertEquals("O código é obrigatório", vbl.update(v));

			// codigo inválido
			// string.length > 4
			v.setCodigo("12345");
			assertEquals("O código deve ser menor que 4 dígitos", vbl.create(v));
			
			// codigo inválido			
			// caracteres especiais
			v.setCodigo("#$%&");
			assertEquals("O código deve ser apenas dígitos", vbl.update(v));
		}
	}
	
	/**
	 * Testa o método listar do ViaturaBusinessLogic
	 */
	public void testListar() {
		assertEquals(2, vbl.listar().size());
		assertEquals(1, vbl.listarAtivos().size());
	}
	
	/**
	 * Testa o método pesquisar do ViaturaBusinessLogic
	 */
	public void testPesquisar() {
		List<ViaturaVO> ls;

		ls = vbl.pesquisar("1234");
		assertEquals(1, ls.size());

		ls = vbl.pesquisar("9999");
		assertEquals(0, ls.size());

	}
}
