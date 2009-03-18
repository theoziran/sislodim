package br.faculdadeidez.psa.beans;

import br.faculdadeidez.psa.businesslogic.Fachada;

public class GenericoBean {

	Fachada getFachada(){
		return Fachada.getFachada();
	}
	
}
