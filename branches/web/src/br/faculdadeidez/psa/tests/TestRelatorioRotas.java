package br.faculdadeidez.psa.tests;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import junit.framework.TestCase;
import br.faculdadeidez.psa.businesslogic.RotaPercorridaBusinessLogic;
import br.faculdadeidez.psa.vo.CoordenadaVO;
import br.faculdadeidez.psa.vo.RotaPercorridaVO;
/**
 * Classe de teste responsável por garantir o funcionamento dos relatorios
 */
public class TestRelatorioRotas extends TestCase{
	
	/**
	 * Objeto utilizado para garantir os testes 
	 */
	protected Calendar dataInicial = Calendar.getInstance(new Locale("pt","br"));
	
	/**
	 * Objeto utilizado para garantir os testes 
	 */
	protected Calendar dataFinal = Calendar.getInstance(new Locale("pt","br"));
	
	/**
	 * Método para instanciar as propriedades
	 */
	@Override
	protected void setUp() throws Exception {
		this.dataInicial.set(2008-1900,5-1,7,21,15);
		this.dataFinal.set(2009-1900,5-1,22,23,59);
	}
	
//	public void testGeraRelatorioViaturaNoSetorValidos(){
//		RotaPercorridaBusinessLogic rotasBl = new RotaPercorridaBusinessLogic();
		
////		List<RotaPercorridaVO> list = rotasBl.listar(this.dataInicial, this.dataFinal, true);
//		assertEquals(3, list.size());
//
//		list = rotasBl.listar(this.dataInicial, this.dataFinal, false);
//		assertEquals(1, list.size());
//	}
	
	/**
	 * Método que garante a veracidade dos dados do relatorio
	 */
	public void testListarCoordenadas() {
		RotaPercorridaBusinessLogic rotasBL = new RotaPercorridaBusinessLogic();
		
		Vector<CoordenadaVO> coordenadas = (Vector<CoordenadaVO>)rotasBL.listarNoSetor();
		Vector<CoordenadaVO> coordenadasFora = (Vector<CoordenadaVO>)rotasBL.listarForaDoSetor();
		
		assertEquals(1, coordenadas.size());
		assertEquals(3, coordenadasFora.size());	
	}
}
