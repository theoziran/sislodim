package br.com.faculdadeidez.ddm.streetrunner;

import java.io.IOException;
import java.util.Random;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.game.LayerManager;
import javax.microedition.lcdui.game.Sprite;

import br.com.faculdadeidez.ddm.streetrunner.telas.MenuJogo;

public class TiledLayerCanvas extends GameCanvas implements Runnable {

	private static final int MV_VERTICAL = 3;
	private static final int MV_HORIZONTAL = 3;
	private static final int LARGURA_VEICULO = 32;
	private static final int ALTURA_VEICULO = 32;
	private static final String[] PERSONAGENS = { "chinchilla", "donkey",
			"penguin", "skunk", "squirrel", "walrus" };

	private final LayerManager manager;
	private final Graphics g;

	private Sprite personagem;
	private Sprite rua;

	private static String personagemDaVez;

	private Pista pista1;
	private Pista pista2;
	private Pista pista3;

	private int timerPista1 = 10;
	private int timerPista2 = 10;
	private int timerPista3 = 10;

	private boolean finalizado = false;

	protected TiledLayerCanvas(boolean suppressKeyEvents) {
		super(suppressKeyEvents);

		finalizado = false;

		manager = new LayerManager();
		g = getGraphics();

		pista1 = new Pista(Pista.DIREITA_ESQUERDA, LARGURA_VEICULO, getWidth(),
				getHeight() / 2 - 10);
		pista2 = new Pista(Pista.DIREITA_ESQUERDA, LARGURA_VEICULO, getWidth(),
				getHeight() / 2 + 25);
		pista3 = new Pista(Pista.ESQUERDA_DIREITA, LARGURA_VEICULO, getWidth(),
				getHeight() / 2 + 65);

		try {
			criarVeiculos();
			criarPersonagem();
			criarRua();

			manager.append(personagem);
			manager.append(rua);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		Thread currentThread = UIController.getInstance().getGameThread();

		definirTimer();

		pista1.setTimer(timerPista1);
		pista2.setTimer(timerPista2);
		pista3.setTimer(timerPista3);

		pista1.start();
		pista2.start();
		pista3.start();

		while ((currentThread == UIController.getInstance().getGameThread())
				&& (!finalizado)) {
			manager.paint(g, 0, 0);

			movimentarPersonagem();
			if (ganhou() || foiAtropelado()) {
				break;
			}

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			flushGraphics();
		}
		if (finalizado) {
			if (UIController.getInstance().isGanhou()) {
				if (UIController.getInstance().getLevel() < 3) {
					UIController.getInstance().setLevel(
							UIController.getInstance().getLevel() + 1);
					UIController.getInstance().setAtravessou(false);
					UIController.getInstance().iniciarJogo();
				} else {
					System.out.println("ganhou mané");
				}
			} else {
				UIController.getInstance().setAtravessou(false);
				UIController.getInstance().iniciarJogo();
			}
			this.personagem=null;
			System.gc();
		}
	}

	private void finalizarPistas() {
		pista1.setFinalizado(true);
		pista2.setFinalizado(true);
		pista3.setFinalizado(true);
		
	}

	private boolean ganhou() {
		if (UIController.getInstance().isAtravessou()) {
			if (this.personagem.getY() >= 245) {
				finalizarPistas();
				this.finalizado = true;
				System.out.println("Ganhou animal");
				UIController.getInstance().setGanhou(true);
				UIController.getInstance().qbeleza();
				return true;
			}
		}
		if (this.personagem.getY() <= 105)
			UIController.getInstance().setAtravessou(true);
		return false;
	}

	private boolean foiAtropelado() {
		int i;
		for (i = 0; i < this.pista1.getVeiculos().size(); i++) {
			if (((Sprite) this.pista1.getVeiculos().elementAt(i)).collidesWith(
					this.personagem, true)) {
				finalizarPistas();
				this.finalizado = true;

				System.out.println("ronaldo");
				UIController.getInstance().setGanhou(false);
				UIController.getInstance().ronaldo();
				return true;
			}
		}
		for (i = 0; i < this.pista2.getVeiculos().size(); i++) {
			if (((Sprite) this.pista2.getVeiculos().elementAt(i)).collidesWith(
					this.personagem, true)) {
				finalizarPistas();
				this.finalizado = true;

				System.out.println("ronaldo");
				UIController.getInstance().setGanhou(false);
				UIController.getInstance().ronaldo();
				return true;
			}
		}
		for (i = 0; i < this.pista3.getVeiculos().size(); i++) {
			if (((Sprite) this.pista3.getVeiculos().elementAt(i)).collidesWith(
					this.personagem, true)) {
				finalizarPistas();
				this.finalizado = true;

				System.out.println("ronaldo");
				UIController.getInstance().setGanhou(false);
				UIController.getInstance().ronaldo();
				return true;
			}
		}
		return false;
	}
	
	private void criarVeiculos() throws IOException {
		Sprite veiculo = criarVeiculo("car");
		manager.append(veiculo);
		pista1.addVeiculo(veiculo);

		veiculo = criarVeiculo("motorbike");
		manager.append(veiculo);
		pista1.addVeiculo(veiculo);

		veiculo = criarVeiculo("car");
		manager.append(veiculo);
		pista1.addVeiculo(veiculo);

		veiculo = criarVeiculo("car");
		manager.append(veiculo);
		pista2.addVeiculo(veiculo);

		veiculo = criarVeiculo("motorbike");
		manager.append(veiculo);
		pista2.addVeiculo(veiculo);

		veiculo = criarVeiculo("car");
		manager.append(veiculo);
		pista2.addVeiculo(veiculo);

		veiculo = criarVeiculo("car2");
		manager.append(veiculo);
		pista3.addVeiculo(veiculo);

		veiculo = criarVeiculo("motorbike2");
		manager.append(veiculo);
		pista3.addVeiculo(veiculo);

		veiculo = criarVeiculo("car2");
		manager.append(veiculo);
		pista3.addVeiculo(veiculo);

	}

	private Sprite criarVeiculo(String tipo) throws IOException {
		Image img = Image.createImage("/" + tipo + ".png");

		Sprite veiculo = new Sprite(img, LARGURA_VEICULO, ALTURA_VEICULO);
		veiculo.setRefPixelPosition(getWidth() - LARGURA_VEICULO,
				(getHeight() / 2) - 10);
		img = null;
		System.gc();
		return veiculo;
	}

	private void criarRua() throws IOException {
		Image img = Image.createImage("/rua.png");
		this.rua = new Sprite(img, 320, 290);
		this.rua.setRefPixelPosition(0, 0);
		img = null;
		System.gc();
	}

	private void criarPersonagem() throws IOException {
		escolherPersonagem();
		Image img = Image.createImage("/" + personagemDaVez + ".png");
		this.personagem = new Sprite(img, 24, 24);
		this.personagem.setRefPixelPosition((getWidth() - 24) / 2,
				getHeight() - 45);
		img = null;
		System.gc();
	}

	public void keyPressed(int keyCode) {
		if ((keyCode == -7) || (keyCode == -6)) {
			try {
				System.out.println("parando o jogo");
				UIController.getInstance().pararJogo();
				UIController.getInstance().setCurrent(MenuJogo.getInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void escolherPersonagem() {
		Random random = new Random();
		random.setSeed(System.currentTimeMillis());
		int i = Math.abs(random.nextInt()) % PERSONAGENS.length;
		personagemDaVez = PERSONAGENS[i];
	}

	private void movimentarPersonagem() {
		int keyStates = getKeyStates();
		int x = this.personagem.getX();
		int y = this.personagem.getY();
		if ((keyStates == UP_PRESSED) && (y > 80)) {
			this.personagem.setPosition(x, y - MV_VERTICAL);
		} else if ((keyStates == LEFT_PRESSED) && (x > 0)) {
			this.personagem.setPosition(x - MV_HORIZONTAL, y);
		} else if ((keyStates == DOWN_PRESSED)
				&& (y + ALTURA_VEICULO + 3 < getHeight())) {
			this.personagem.setPosition(x, y + MV_VERTICAL);
		} else if ((keyStates == RIGHT_PRESSED)
				&& (x + LARGURA_VEICULO < getWidth())) {
			this.personagem.setPosition(x + MV_HORIZONTAL, y);
		}
	}

	private void definirTimer() {
		int level = UIController.getInstance().getLevel();

		int timerBase = 40 - 10 * level;

		timerPista1 = timerBase - 5;
		timerPista2 = timerBase - 7;
		timerPista3 = timerBase - 2;

		System.out.println("pista1:" + timerPista1);
		System.out.println("pista2:" + timerPista2);
		System.out.println("pista3:" + timerPista3);
	}
}
