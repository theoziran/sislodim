package br.faculdadeidez.psa.servico;

import java.io.IOException;
import java.net.MalformedURLException;

public class Aplicacao {
public static void main(String[] args) throws ComparacaoDistanciaException {

  RetornaEndereco re = new RetornaEndereco("-7.1761","-34.8624");
  System.out.println(re.getBairro(re.PercorrerXml(re.receberXml())));
	
	
//	ComparacaoDistancia c = new ComparacaoDistancia();
//		c.setOrigem("-7.1761 -34.8624");
//		c.setDestino("geisel, jo�o pessoa, paraiba");
//		try {
//			System.out.println(c.getDistancia());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			System.err.println(e.getMessage());
//		}
//		c.setOrigem("-7.119769,-34.823678");
//		c.setDestino("-7.097614,-34.834213");
//		
//		System.out.println(c.getDistancia());
//		c.setOrigem("-7.183055,-34.839642");
//		c.setDestino("rua maria noemi de souza holanda, mangabeira");
//		
//		System.out.println(c.getDistancia());
	}
}
