package br.com.faculdadeidez.ddm.streetrunner.telas;

import java.io.IOException;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import br.com.faculdadeidez.ddm.streetrunner.UIController;

public class Splash extends Canvas implements Runnable {

	private static Splash instance;
	private Image imagem;
	private volatile boolean liberar = false;

	public Splash() {
		setFullScreenMode(true);
		try {
			imagem = Image.createImage("/splash.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new Thread(this).start();
	}

	public static Splash getInstance() {
		if (instance == null) {
			instance = new Splash();
		}
		return instance;
	}

	protected void paint(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		g.setColor(0x00FFFFFF);
		g.fillRect(0, 0, width, height);


		if (imagem != null) {
			g.drawImage(imagem, width / 2, height / 2, Graphics.VCENTER
					| Graphics.HCENTER);
			imagem = null;
		}

	}

	public void run() {
		synchronized (this) {
			try {
				wait(3000L);
			} catch (InterruptedException e) {
			}
			liberar();
		}

	}

	private void liberar() {
		if (!liberar) {
			liberar = true;
			try {
				UIController.getInstance().setCurrent(Menu.getInstance());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
