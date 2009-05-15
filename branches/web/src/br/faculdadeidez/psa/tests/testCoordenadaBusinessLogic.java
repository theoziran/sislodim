package br.faculdadeidez.psa.tests;

import java.util.Date;
import java.util.Vector;

import br.faculdadeidez.psa.businesslogic.CoordenadasBusinessLogic;
import br.faculdadeidez.psa.businesslogic.ViaturaBusinessLogic;
import br.faculdadeidez.psa.servico.GoogleMaps;
import br.faculdadeidez.psa.servico.GoogleMapsDistance;
import br.faculdadeidez.psa.vo.CoordenadaVO;
import br.faculdadeidez.psa.vo.ViaturaVO;
import junit.framework.TestCase;

public class testCoordenadaBusinessLogic extends TestCase {

	private ViaturaBusinessLogic vbl = new ViaturaBusinessLogic();
	private Vector<ViaturaVO> listvaitura = (Vector<ViaturaVO>) vbl.pesquisar("1111");
	
	private CoordenadaVO c;
	private CoordenadasBusinessLogic cbl = new CoordenadasBusinessLogic();
	
	public void testCreate() {
		c = new CoordenadaVO("-7.096987","-34.834377",listvaitura.get(0),new Date() ,true,1,true);
		assertEquals("inserido", cbl.create(c));
	}

	public void testGetUltimaCoordenadaViatura() {
		assertEquals("-7.096987", cbl.getUltimaCoordenadaViatura(listvaitura.get(0)).getLatitude());
	}

	public void testListarCoordenadasNaoVerificadas() {
		assertEquals(2, cbl.listarCoordenadasNaoVerificadas().size());
	}


	public void testCalculaViaturaMaisProxima() {
		GoogleMaps gm = cbl.calculaViaturaMaisProxima("Av. Pres. Afonso Pena - Bessa João Pessoa - PB, Brazil");
		assertEquals(gm.getEnderecoCompletoDestino()
					, "Av. Gov. Flavio Ribeiro Coutinho");
	}

}
