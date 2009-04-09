package br.com.idez.ddm.tourguide.core;

import java.util.Stack;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import br.com.idez.ddm.tourguide.TourGuideMIDLet;

public class UIController {

	private static UIController instance;

	private MIDlet midlet;
	private Display display;

	private Stack telas = new Stack();

	private UIController(MIDlet midlet) {
		this.midlet = midlet;
		this.display = Display.getDisplay(midlet);
		this.telas = new Stack();
	}

	public static UIController createInstance(MIDlet midlet) {
		if (instance == null) {
			instance = new UIController(midlet);
		}
		return instance;
	}

	public static UIController getInstance() throws Exception {
		if (instance == null) {
			throw new Exception("Instancia não criada");
		}
		return instance;
	}

	public void setCurrent(Displayable displayable) {
		this.telas.push(displayable);
		this.display.setCurrent(displayable);
	}

	public void setDisplay(Display display) {
		this.display = display;
	}

	public Displayable getCurrentDisplayable() {
		return (Displayable) this.telas.pop();
	}

	public void sair() {
		try {
			((TourGuideMIDLet) midlet).destroyApp(false);
			((TourGuideMIDLet) midlet).notifyDestroyed();

		} catch (MIDletStateChangeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void voltar() {
		if (this.telas.size() > 1) {
			this.telas.pop();
			Displayable displayable = (Displayable)this.telas.pop();
			setCurrent(displayable);
		}
	}
}
