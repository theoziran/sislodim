package br.com.faculdadeidez.ddm.streetrunner;

import java.io.IOException;
import java.util.Random;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.game.LayerManager;
import javax.microedition.lcdui.game.Sprite;
import javax.microedition.lcdui.game.TiledLayer;

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

	private TiledLayer background;
	private Sprite personagem;
	private Sprite rua;

	private static String personagemDaVez;
	private static int velocidade = 2;

	private Pista pista1;
	private Pista pista2;
	private Pista pista3;

	protected TiledLayerCanvas(boolean suppressKeyEvents) {
		super(suppressKeyEvents);
		manager = new LayerManager();
		g = getGraphics();

		pista1 = new Pista(Pista.DIREITA_ESQUERDA, LARGURA_VEICULO, getWidth());
		pista1.setTimer(10);

		pista2 = new Pista(Pista.DIREITA_ESQUERDA, LARGURA_VEICULO, getWidth());
		pista2.setTimer(7);

		pista3 = new Pista(Pista.ESQUERDA_DIREITA, LARGURA_VEICULO, getWidth());
		pista3.setTimer(12);

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
		pista1.start();
		pista2.start();
		pista3.start();
		while (currentThread == UIController.getInstance().getGameThread()) {
			manager.paint(g, 0, 0);
			// checarcolisao

			movimentarPersonagem();
			transito();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			flushGraphics();
		}
	}

	private void criarVeiculos() throws IOException {
		Sprite veiculo = criarVeiculo("car");
		manager.append(veiculo);
		pista1.addVeiculo(veiculo);

		veiculo = criarVeiculo("car");
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

		veiculo = criarVeiculo("car");
		manager.append(veiculo);
		pista2.addVeiculo(veiculo);

		veiculo = criarVeiculo("motorbike");
		manager.append(veiculo);
		pista2.addVeiculo(veiculo);

		veiculo = criarVeiculo("car");
		manager.append(veiculo);
		pista2.addVeiculo(veiculo);

		veiculo = criarVeiculo("car");
		manager.append(veiculo);
		pista3.addVeiculo(veiculo);

		veiculo = criarVeiculo("car");
		manager.append(veiculo);
		pista3.addVeiculo(veiculo);

		veiculo = criarVeiculo("motorbike");
		manager.append(veiculo);
		pista3.addVeiculo(veiculo);

		veiculo = criarVeiculo("car");
		manager.append(veiculo);
		pista3.addVeiculo(veiculo);

	}

	private Sprite criarVeiculo(String tipo) throws IOException {
		Image img = Image.createImage("/" + tipo + ".png");

		Sprite veiculo = new Sprite(img, LARGURA_VEICULO, ALTURA_VEICULO);
		veiculo.setRefPixelPosition(getWidth() - LARGURA_VEICULO,
				(getHeight() / 2) - 10);
		return veiculo;
	}

	private void criarRua() throws IOException {
		Image img = Image.createImage("/rua.png");
		this.rua = new Sprite(img, 320, 290);
		this.rua.setRefPixelPosition(0, 0);
	}

	private void criarPersonagem() throws IOException {
		escolherPersonagem();
		Image img = Image.createImage("/" + personagemDaVez + ".png");
		this.personagem = new Sprite(img, LARGURA_VEICULO, ALTURA_VEICULO);
		this.personagem.setRefPixelPosition((getWidth() - LARGURA_VEICULO) / 2,
				getHeight() - 45);
	}

	private void criaBackground() throws IOException {
		Image img = Image.createImage("/tiledlayer1.png");
		this.background = new TiledLayer(5, 8, img, 320, 290);

		int[] cells = { 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 1, 1,
				1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
				2 };
		for (int i = 0; i < cells.length; i++) {
			int column = i % 5;
			int row = (i - column) / 5;
			this.background.setCell(column, row, cells[i]);
		}

		this.background.setPosition(getWidth() / 10, getHeight() / 10);
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
		System.out.println(i);
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

	private void transito() {
		// int carX, motoX;
		// carX = this.car.getX();
		// if ((carX + LARGURA_VEICULO) < 0) {
		// carX = getWidth();
		// }
		// if ((motoX + LARGURA_VEICULO) < 0) {
		// motoX = getWidth();
		// }
		//
		// this.car.setPosition(carX - getVelocidade(), this.car.getY());
	}

	public static int getVelocidade() {
		return velocidade;
	}

	public static void setVelocidade(int velocidade) {
		TiledLayerCanvas.velocidade = velocidade;
	}

}
