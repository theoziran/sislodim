package br.com.faculdadeidez.ddm.streetrunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.Stack;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import br.com.faculdadeidez.ddm.streetrunner.telas.Alerta;

public class UIController {

	private static UIController instance;

	private MIDlet midlet;
	private static Display display;

	private static Stack telas = new Stack();

	/* GAME PROPERTIES */
	private static Thread gameThread = null;
	private int level = 0;
	private boolean atravessou = false;
	private static boolean ganhou = false;

	public boolean isGanhou() {
		return ganhou;
	}

	public void setGanhou(boolean ganhou) {
		UIController.ganhou = ganhou;
	}

	public Thread getGameThread() {
		return gameThread;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
		System.out.println("novo level: " + level);
	}

	private UIController(MIDlet midlet) {
		this.midlet = midlet;
		UIController.display = Display.getDisplay(midlet);
		UIController.telas = new Stack();
	}

	public static UIController createInstance(MIDlet midlet) {
		if (instance == null) {
			instance = new UIController(midlet);
		}
		return instance;
	}

	public static UIController getInstance() {
		if (instance == null) {
			// throw new Exception("Instancia não criada");
		}
		return instance;
	}

	/* MÉTODOS DE CONTROLE DAS TELAS */

	public void setCurrent(Displayable displayable) {
		UIController.telas.push(displayable);
		UIController.display.setCurrent(displayable);
	}

	public void setCurrent(Alert alert, Displayable nextDisplayable) {
		UIController.telas.push(nextDisplayable);
		UIController.display.setCurrent(alert, nextDisplayable);
	}

	public void setDisplay(Display display) {
		UIController.display = display;
	}

	public Displayable getCurrentDisplayable() {
		return (Displayable) UIController.telas.pop();
	}

	public void sair() {
		try {
			((StreetRunnerMIDLet) midlet).destroyApp(false);
			((StreetRunnerMIDLet) midlet).notifyDestroyed();
		} catch (MIDletStateChangeException e) {
			e.printStackTrace();
		}
	}

	public void voltar() {
		if (UIController.telas.size() > 1) {
			UIController.telas.pop();
			Displayable displayable = (Displayable) UIController.telas.pop();
			setCurrent(displayable);
		}
	}

	/* MÉTODOS DE ACESSO A RECORD STORE */

	public void saveConfigs(String sound) {
		Record.setConfigSound(sound);
	}

	public void saveLevel() {
		Record.setLevel(String.valueOf(getLevel()));
	}

	/* MÉTODOS DE CONTROLE DO JOGO */

	public void iniciarJogo() {
		TiledLayerCanvas canvas = new TiledLayerCanvas(true);
		gameThread = new Thread(canvas);
		setCurrent(canvas);
		gameThread.start();
	}

	public void pararJogo() {
		gameThread = null;
	}

	public static void excessaoGenerica(String mensagem) {
		Alerta alertaMensagem = Alerta.getInstance();
		alertaMensagem.setTexto(mensagem);
		UIController.telas.push(alertaMensagem);
		UIController.display.setCurrent(alertaMensagem);
	}

	public void setAtravessou(boolean atravessou) {
		this.atravessou = atravessou;
	}

	public boolean isAtravessou() {
		return atravessou;
	}

	public void ronaldo() {
		Thread som = new Thread(new Runnable() {
			public void run() {
				Player player;
				InputStream is = getClass().getResourceAsStream("/ronaldo.wav");
				try {
					player = Manager.createPlayer(is, "audio/x-wav");
					player.realize();
					player.prefetch();
					player.start();

				} catch (IOException e) {
					e.printStackTrace();
				} catch (MediaException e) {
					e.printStackTrace();
				}

			}
		});
		som.start();

	}

	public void qbeleza() {
		Thread som = new Thread(new Runnable() {

			public void run() {
				Player player;
				InputStream is = getClass().getResourceAsStream("/qbeleza.wav");
				try {
					player = Manager.createPlayer(is, "audio/x-wav");
					player.realize();
					player.prefetch();
					player.start();

				} catch (IOException e) {
					e.printStackTrace();
				} catch (MediaException e) {
					e.printStackTrace();
				}

			}
		});
		som.start();
	}
}