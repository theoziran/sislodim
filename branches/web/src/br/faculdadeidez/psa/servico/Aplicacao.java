package br.faculdadeidez.psa.servico;

import java.io.IOException;
import java.net.MalformedURLException;

public class Aplicacao {
public static void main(String[] args) throws ComparacaoDistanciaException {
	ComparacaoDistancia c = new ComparacaoDistancia();
		c.setOrigem("-7.1761 -34.8624");
		c.setDestino("geisel, joão pessoa, paraiba");
		try {
			System.out.println(c.getDistancia());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
		c.setOrigem("-7.119769,-34.823678");
		c.setDestino("-7.097614,-34.834213");
		
		System.out.println(c.getDistancia());
	}
}
