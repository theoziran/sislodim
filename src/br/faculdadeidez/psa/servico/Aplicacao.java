package br.faculdadeidez.psa.servico;

public class Aplicacao {
public static void main(String[] args) throws ComparacaoDistanciaException {
	ComparacaoDistancia c = new ComparacaoDistancia();
		c.setOrigem("Santa Rita, PB");
		c.setDestino("-7.097614, -34.834213");
		System.out.println(c.getDistancia());
	}
}
