package br.com.idez.ddm.tourguide.core;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import br.com.idez.ddm.tourguide.telas.Inicial;

public class TourGuideMIDLet extends MIDlet {

	private Display display;
	private TourGuideMIDLetController controller;

	public TourGuideMIDLet() {
		this.display = Display.getDisplay(this);
	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		// TODO Auto-generated method stub

	}

	protected void pauseApp() {
		// TODO Auto-generated method stub

	}

	protected void startApp() throws MIDletStateChangeException {
		controller = TourGuideMIDLetController.getInstance();
		controller.setDisplay(display);
		Inicial inicial = Inicial.getInstance();
		controller.setCurrent(inicial.getForm());

	}

}
