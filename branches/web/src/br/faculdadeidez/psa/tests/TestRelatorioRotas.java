package br.faculdadeidez.psa.tests;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import junit.framework.TestCase;
import br.faculdadeidez.psa.businesslogic.RotaPercorridaBusinessLogic;
import br.faculdadeidez.psa.vo.CoordenadaVO;
import br.faculdadeidez.psa.vo.RotaPercorridaVO;

public class TestRelatorioRotas extends TestCase{
	
	protected Date dataInicial;
	protected Date dataFinal;
	
	@Override
	protected void setUp() throws Exception {
		this.dataInicial = new Date(2008,4,7,21,15);
		this.dataFinal = new Date(2009,5,22,23,59);
	}
	
	public void testGeraRelatorioViaturaNoSetorValidos(){
		RotaPercorridaBusinessLogic rotasBl = new RotaPercorridaBusinessLogic();
		
		List<RotaPercorridaVO> list = rotasBl.listar(this.dataInicial, this.dataFinal, true);
		assertEquals(3, list.size());

		list = rotasBl.listar(this.dataInicial, this.dataFinal, false);
		assertEquals(1, list.size());
	}
	
	public void testListarCoordenadas() {
		RotaPercorridaBusinessLogic rotasBL = new RotaPercorridaBusinessLogic();
		
		Vector<CoordenadaVO> coordenadas = (Vector<CoordenadaVO>)rotasBL.listarNoSetor();
		Vector<CoordenadaVO> coordenadasFora = (Vector<CoordenadaVO>)rotasBL.listarForaDoSetor();
		
		assertEquals(1, coordenadas.size());
		assertEquals(3, coordenadasFora.size());	
	}
}
