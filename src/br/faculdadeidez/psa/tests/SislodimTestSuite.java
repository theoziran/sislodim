package br.faculdadeidez.psa.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class SislodimTestSuite {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for br.faculdadeidez.psa.tests");
		//$JUnit-BEGIN$
		suite.addTestSuite(testUsuarioBusinessLogic.class);
		suite.addTestSuite(testViaturaBusinessLogic.class);
		//$JUnit-END$
		return suite;
	}

}
