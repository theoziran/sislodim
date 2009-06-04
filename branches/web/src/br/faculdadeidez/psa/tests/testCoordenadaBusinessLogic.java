package br.faculdadeidez.psa.tests;

import java.util.Calendar;
import java.util.Vector;

import junit.framework.TestCase;
import br.faculdadeidez.psa.businesslogic.CoordenadasBusinessLogic;
import br.faculdadeidez.psa.businesslogic.ViaturaBusinessLogic;
import br.faculdadeidez.psa.servico.GoogleMaps;
import br.faculdadeidez.psa.vo.CoordenadaVO;
import br.faculdadeidez.psa.vo.ViaturaVO;

/**
 * Classe de teste responsável por garantir o funcionamento de CoordenadaBusinessLogic
 */
public class testCoordenadaBusinessLogic extends TestCase {

	/**
	 * Objeto utilizado para garantir os testes 
	 */
	private ViaturaBusinessLogic vbl = new ViaturaBusinessLogic();
	
	/**
	 * Objeto utilizado para garantir os testes
	 */
	private Vector<ViaturaVO> listvaitura = (Vector<ViaturaVO>) vbl.pesquisar("1111");
	
	/**
	 * Objeto utilizado para garantir os testes
	 */
	private CoordenadaVO c;
	
	/**
	 * Resposável por simular o funcionamento dos testes
	 */
	private CoordenadasBusinessLogic cbl = new CoordenadasBusinessLogic();
	
	/**
	 * Método para testar o método create de CoordenadasBusinessLogic
	 * 	-Se retorna a string passada por parâmetro foi aprovado no teste
	 */
	public void testCreate() {
		c = new CoordenadaVO("-7.096987","-34.834377",listvaitura.get(0),Calendar.getInstance() ,true,1,true);
		assertEquals("inserido", cbl.create(c));
	}

	/**
	 * Método para testar o método getUltimaCoordenadaViatura de CoordenadasBusinessLogic
	 * 	-Se retorna a string passada por parâmetro foi aprovado no teste
	 */
	public void testGetUltimaCoordenadaViatura() {
		assertEquals("-7.096987", cbl.getUltimaCoordenadaViatura(listvaitura.get(0)).getLatitude());
	}
	
	/**
	 * Método para testar o método listarCoordenadasNaoVerificada de CoordenadasBusinessLogic
	 * 	-Se retorna o valor passada por parâmetro foi aprovado no teste
	 */
	public void testListarCoordenadasNaoVerificadas() {
		assertEquals(2, cbl.listarCoordenadasNaoVerificadas().size());
	}

	/**
	 * Método para testar o método calculaViaturaMaisProxima de CoordenadasBusinessLogic
	 * 	-Se retorna o valor passada por parâmetro foi aprovado no teste
	 */
	public void testCalculaViaturaMaisProxima() {
		GoogleMaps gm = cbl.calculaViaturaMaisProxima("Av. Pres. Afonso Pena - Bessa João Pessoa - PB, Brazil");
		assertEquals(gm.getEnderecoCompletoDestino()
					, "Av. Gov. Flavio Ribeiro Coutinho");
	}

}
