package br.faculdadeidez.psa.tests;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.faculdadeidez.psa.businesslogic.RotaPercorridaBusinessLogic;
import br.faculdadeidez.psa.vo.CoordenadaVO;
import br.faculdadeidez.psa.vo.RotaPercorridaVO;

import junit.framework.TestCase;

public class TestRelatorioRotas extends TestCase{
	
	protected List<Date> listDataInicial;
	protected List<Date> listDataFinal;
	
	
	public TestRelatorioRotas() {
		this.listDataInicial = new ArrayList<Date>();
		this.listDataFinal = new ArrayList<Date>();
	}
	
	@Override
	protected void setUp() throws Exception {
		Date data = new Date();
		
		data.setYear(2009);
		data.setMonth(5);
		data.setDate(8);
		data.setHours(21);
		data.setMinutes(15);		
		listDataInicial.add(data);
		data.setDate(10);
		data.setHours(0);
		data.setMinutes(45);
		listDataInicial.add(data);
		data.setHours(3);
		data.setMinutes(15);
		listDataInicial.add(data);
		
		data.setYear(2009);
		data.setMonth(5);
		data.setDate(22);
		data.setHours(23);
		data.setMinutes(59);
		listDataFinal.add(data);
		data.setDate(6);
		listDataFinal.add(data);
		data.setMonth(10);
		listDataFinal.add(data);
				
		
	}
	
	public void testGeraRelatorioViaturaNoSetorValidos(){
		RotaPercorridaBusinessLogic rotasBl = new RotaPercorridaBusinessLogic();
		List<RotaPercorridaVO> list = new ArrayList<RotaPercorridaVO>();
		list.addAll(rotasBl.listar(listDataInicial.get(0), listDataFinal.get(0), false));
		assertTrue(!list.isEmpty());
	}
	
	
}
