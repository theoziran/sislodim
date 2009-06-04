package br.faculdadeidez.psa.tests;

import junit.framework.Test;
import junit.framework.TestSuite;
/**
 * Responsável por criar a suite de testes
 */
public class SislodimTestSuite {
	
	/**
	 * Método que instancia uma suite de testes
	 * @return TestSuite
	 */
	public static Test suite() {
		TestSuite suite = new TestSuite("Test for br.faculdadeidez.psa.tests");
		//$JUnit-BEGIN$
		//suite.addTestSuite(testUsuarioBusinessLogic.class);
		//suite.addTestSuite(testViaturaBusinessLogic.class);
		//suite.addTestSuite(testSetorBusinessLogic.class);
		suite.addTestSuite(TestRelatorioRotas.class);
		//$JUnit-END$
		return suite;
	}

}
