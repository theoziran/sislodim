package br.com.faculdadeidez.ddm.streetrunner;

import java.util.Vector;

import javax.microedition.lcdui.game.Sprite;

public class Pista extends Thread {
	public static final int ESQUERDA_DIREITA = 1;
	public static final int DIREITA_ESQUERDA = 2;

	private Vector veiculos;
	private int sentido;
	private int timer;
	private int larguraVeiculo;
	private int larguraTela;
	private int altura;
	private boolean finalizado = false;

	public Pista() {
		veiculos = new Vector();
		this.finalizado = false;
		this.sentido = ESQUERDA_DIREITA;
		this.larguraTela = 320;
		this.larguraVeiculo = 32;
		this.altura = 120;
	}

	public Pista(int sentido, int larguraVeiculo, int larguraTela, int altura) {
		veiculos = new Vector();
		this.finalizado = false;
		this.sentido = sentido;
		this.larguraTela = larguraTela;
		this.larguraVeiculo = larguraVeiculo;
		this.altura = altura;
	}

	public int getLarguraVeiculo() {
		return larguraVeiculo;
	}

	public void setLarguraVeiculo(int largura_veiculo) {
		this.larguraVeiculo = largura_veiculo;
	}

	public int getLarguraTela() {
		return larguraTela;
	}

	public void setLarguraTela(int largura_tela) {
		this.larguraTela = largura_tela;
	}

	public void addVeiculo(Sprite veiculo) {
		int qtdVeiculos = veiculos.size();
		if (qtdVeiculos > 0) {
			Sprite ultimoVeiculo = (Sprite) veiculos.elementAt(qtdVeiculos - 1);
			if (sentido == Pista.DIREITA_ESQUERDA) {
				int x = ultimoVeiculo.getX() + larguraVeiculo + 70;
				veiculo.setPosition(x, altura);
			} else if (sentido == Pista.ESQUERDA_DIREITA) {
				int x = ultimoVeiculo.getX() - 70 - larguraVeiculo;
				veiculo.setPosition(x, altura);
			}
		}
		veiculos.addElement(veiculo);
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	public int getSentido() {
		return sentido;
	}

	public void setSentido(int sentido) {
		this.sentido = sentido;
	}

	public int getTimer() {
		return timer;
	}

	public void setTimer(int timer) {
		this.timer = timer;
	}

	public void run() {
		while (!finalizado) {
			atualizarPosicoes();
			try {
				sleep(timer);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.veiculos=null;
	}

	private void atualizarPosicoes() {
		if (this.sentido == DIREITA_ESQUERDA) {
			for (int i = 0; i < veiculos.size(); i++) {
				Sprite veiculo = (Sprite) veiculos.elementAt(i);
				int x;

				x = veiculo.getX();
				if ((x + this.larguraVeiculo) < 0) {
					x = larguraTela;
				}

				veiculo.setPosition(x - 1, altura);
			}
		} else if (this.sentido == ESQUERDA_DIREITA) {
			for (int i = 0; i < veiculos.size(); i++) {
				Sprite veiculo = (Sprite) veiculos.elementAt(i);
				int x;

				x = veiculo.getX();
				if (x > larguraTela) {
					x = 0;
				}

				veiculo.setPosition(x + 1, altura);
			}
		}
		
	}

	public Vector getVeiculos() {
		return this.veiculos;
	}
}
