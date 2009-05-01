package br.faculdadeidez.psa.servico;

public class Aplicacao {
public static void main(String[] args)  {
	ComparacaoDistancia c = new ComparacaoDistancia();
		c.setOrigem("Santa Rita, PB");
		c.setDestino("João dfadsfadfadfa fsdf sd fadsfasdf ds f, PB");
		try {
			System.out.println(c.getDistancia());
		} catch (ComparacaoDistanciaException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
}
