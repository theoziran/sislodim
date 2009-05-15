/**
 * 
 */
package br.faculdadeidez.psa.tests;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import br.faculdadeidez.psa.businesslogic.EscalaBusinessLogic;
import br.faculdadeidez.psa.businesslogic.SetorBusinessLogic;
import br.faculdadeidez.psa.businesslogic.ViaturaBusinessLogic;
import br.faculdadeidez.psa.vo.EscalaVO;
import br.faculdadeidez.psa.vo.SetorVO;
import br.faculdadeidez.psa.vo.ViaturaVO;
import junit.framework.TestCase;

/**
 * @author Edmilson Ferreira
 * @author Mario Dias
 * @author Pablo Trajano
 *
 */
public class testEscalaBussinessLogic extends TestCase {
	
	private SetorBusinessLogic sbl = new SetorBusinessLogic();

	private Vector<SetorVO> listSetores = (Vector<SetorVO>) sbl.listar();
	
	private ViaturaBusinessLogic vbl = new ViaturaBusinessLogic();

	private Vector<ViaturaVO> listViaturas = (Vector<ViaturaVO>) vbl.listar();
	
	private EscalaVO e;
	private EscalaBusinessLogic ebl = new EscalaBusinessLogic();

	private List<EscalaVO> listEscalasValidos = new ArrayList<EscalaVO>();
		
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		/**
		 * Criando uma lista de Escalas
		 */
		this.listEscalasValidos.add(new EscalaVO(1, listSetores.get(0), new Date(),new Date(), listViaturas, true));
		this.listEscalasValidos.add(new EscalaVO(2, listSetores.get(1), new Date(),new Date(), listViaturas, true));
		this.listEscalasValidos.add(new EscalaVO(3, listSetores.get(2), new Date(),new Date(), listViaturas, true));
	}
	
	/**
	 * Test method for {@link br.faculdadeidez.psa.businesslogic.EscalaBusinessLogic#create(br.faculdadeidez.psa.vo.EscalaVO)}.
	 * 
	 * Test Case - TC 10.1.1 (Testando valores válidos)
	 */
	public void testCreateValidos() { 
		/**
		 * Criando Escalas 
		 */
		assertEquals("inserido", ebl.create(this.listEscalasValidos.get(0)));
		assertEquals("inserido", ebl.create(this.listEscalasValidos.get(1)));
		assertEquals("inserido", ebl.create(this.listEscalasValidos.get(2)));		
	}
	
	/**
	 * Test method for {@link br.faculdadeidez.psa.businesslogic.EscalaBusinessLogic#create(br.faculdadeidez.psa.vo.EscalaVO)}.
	 * 
	 * Test Case - TC 10.1.4 (Testando valores inválidos)
	 */
	public void testCreateInvalido() {
		/**
		 * boolean Ativo
		 * 
		 * Tipo primitivo, não pode ser setado para valores diverentes de true ou false.
		 */
		
		/**
		 * Data Inicial
		 * 
		 * Data inicial menor que a atual.
		 */
		Date data = new Date();
		
		data.setDate(data.getDate()-1);
		
		e = new EscalaVO(5, listSetores.get(0), data, new Date(), listViaturas, true);
		assertEquals("problemaInserir", ebl.create(e));
		
		/**
		 * Data Final
		 * 
		 * Data final menor que a inicial.
		 */
		e = new EscalaVO(5, listSetores.get(0), new Date(), data, listViaturas, true);
		assertEquals("problemaInserir", ebl.create(e));

	}
	
	
	/**
	 * Test method for {@link br.faculdadeidez.psa.businesslogic.EscalaBusinessLogic#create(br.faculdadeidez.psa.vo.EscalaVO)}.
	 * 
	 * Test Case - TC 10.1.2 (Testando valores nulos)
	 */
	public void testCreateNulos() {
		/**
		 * int Código
		 * Tipo primitivo, não pode ser setado para null.
		 *
		 * e = new EscalaVO(null, listSetores.get(0), new Date(),new Date(), listViaturas, true);
		 * assertEquals("problemaInserir", ebl.create(e));
		 */

		/**
		 * Data Inicial
		 */
		e = new EscalaVO(5, listSetores.get(0), null,new Date(), listViaturas, true);
		assertEquals("problemaInserir", ebl.create(e));
		
		/**
		 * Data Final
		 */
		e = new EscalaVO(5, listSetores.get(0), new Date(), null, listViaturas, true);
		assertEquals("problemaInserir", ebl.create(e));
		
		/**
		 * Viatura
		 */
		e = new EscalaVO(5, null, new Date(), new Date(), listViaturas, true);
		assertEquals("problemaInserir", ebl.create(e));
		
		/**
		 * Escala
		 */
		e = new EscalaVO(5, listSetores.get(0), new Date(), new Date(), null, true);
		assertEquals("problemaInserir", ebl.create(e));
	}
	
	/**
	 * Test method for {@link br.faculdadeidez.psa.businesslogic.EscalaBusinessLogic#delete(br.faculdadeidez.psa.vo.EscalaVO)}.
	 * 
	 *  Test Case - TC 10.1.3 (Testando exclusão)
	 */
	public void testDelete() {
		
		/**
		 * Exclusão válida
		 */
		List<EscalaVO> le; 
		le = ebl.pesquisar(3);
		assertEquals("removido", ebl.delete(le.get(0)));
		
		/**
		 * Exclusão inválida
		 */
		e =  new EscalaVO(10, listSetores.get(0), new Date(), new Date(), listViaturas, true);
		assertEquals("problemaRemover", ebl.delete(e));
	}
	
	/**
	 * Test method for {@link br.faculdadeidez.psa.businesslogic.EscalaBusinessLogic#update(br.faculdadeidez.psa.vo.EscalaVO)}.
	 * 
	 * Test Case - TC 
	 */
	public void testUpdateValido() {
	   e = listEscalasValidos.get(0);
	   e.setSetor(listSetores.get(1));
	   assertEquals("atualizado", ebl.update(e));
	
	   Date data = new Date();
	   data.setDate(data.getDate()+1);
	   e.setDataInicial(data);
	   assertEquals("atualizado", ebl.update(e));
	   
	   e.setDataFinal(data);
	   assertEquals("atualizado", ebl.update(e));
	   
	   listViaturas.remove(0);
	   e.setViaturas(listViaturas);
	   assertEquals("atualizado", ebl.update(e));
	}
	
	/**
	 * Test method for {@link br.faculdadeidez.psa.businesslogic.EscalaBusinessLogic#update(br.faculdadeidez.psa.vo.EscalaVO)}.
	 */
	public void testUpdateInvalido() {
		Date data = new Date();
		data.setDate(data.getDate()-1);
		e = listEscalasValidos.get(1);
		e.setDataInicial(data);
		assertEquals("problemaAtualizar", ebl.update(e));
		
		e.setDataInicial(new Date());
		e = listEscalasValidos.get(1);
		e.setDataFinal(data);
		assertEquals("problemaAtualizar", ebl.update(e));
		
		}
	/**
	 * Test method for {@link br.faculdadeidez.psa.businesslogic.EscalaBusinessLogic#update(br.faculdadeidez.psa.vo.EscalaVO)}.
	 */
	public void testUpdateNulo() {
		e = listEscalasValidos.get(2);
		e.setDataFinal(null);
		assertEquals("problemaAtualizar", ebl.update(e));
	
		e.setDataFinal(new Date());
		e.setDataInicial(null);
		assertEquals("problemaAtualizar", ebl.update(e));
		
		e.setDataInicial(new Date());
		e.setSetor(null);
		assertEquals("problemaAtualizar", ebl.update(e));
		
		e.setSetor(listSetores.get(0));
		e.setViaturas(null);
		assertEquals("problemaAtualizar", ebl.update(e));
	}

	/**
	 * Test method for {@link br.faculdadeidez.psa.businesslogic.EscalaBusinessLogic#pesquisar(int)}.
	 */
	public void testPesquisar() {
		List<EscalaVO> ls;

		ls = ebl.pesquisar(1);
		
		assertEquals(1, ls.size());
	}

	/**
	 * Test method for {@link br.faculdadeidez.psa.businesslogic.EscalaBusinessLogic#listarViaturasEscala(br.faculdadeidez.psa.vo.EscalaVO)}.
	 */
	public void testListarViaturasEscala() {
		assertTrue(listEscalasValidos.size()-1 == ebl.listar().size());
	}

	/**
	 * Test method for {@link br.faculdadeidez.psa.businesslogic.EscalaBusinessLogic#getViaturasEscalaTurno()}.
	 */
	public void testGetViaturasEscalaTurno() {
		assertEquals(3, listViaturas.size());
		assertEquals(3, ebl.getViaturasEscalaTurno().size());
		assertTrue(listViaturas.size() == ebl.getViaturasEscalaTurno().size());
	}

}
