package br.com.idez.core;

import java.util.Stack;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import br.com.idez.Midlet;

public class UIController {

	private static UIController instance;
	private Display disp;
	private MIDlet midlet;
	private Stack tela;

	private UIController(MIDlet midlet) {
		// TODO Auto-generated constructor stub
		this.midlet = midlet;
		this.disp = Display.getDisplay(midlet);
		this.tela = new Stack();
	}

	public static UIController createInstance(Midlet midlet) {
		if (instance == null) {
			instance = new UIController(midlet);
			System.out.println("UIController iniciado");
		}
		return instance;
	}

	public static UIController getInstance() throws Exception {
		if (instance == null) {
			throw new Exception("UIControler não iniciado");
		}
		return instance;
	}

	public void setCurrent(Displayable displayable) {
		this.tela.push(displayable);
		this.disp.setCurrent(displayable);
	}

	public void sair() {

		try {
			((Midlet) this.midlet).destroyApp(false);
		} catch (MIDletStateChangeException e) {

		}
		((Midlet) this.midlet).notifyDestroyed();

	}

}
