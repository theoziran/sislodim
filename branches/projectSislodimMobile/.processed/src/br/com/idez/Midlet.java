package br.com.idez;

import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import br.com.idez.core.UIController;
import br.com.idez.ui.FormGPS;

public class Midlet extends MIDlet {

	private UIController controler = null;
	private boolean start;

	public Midlet() {
		
	}

	public void destroyApp(boolean arg0) throws MIDletStateChangeException {
		// TODO Auto-generated method stub

	}

	protected void pauseApp() {

	}

	protected void startApp() throws MIDletStateChangeException {

		if (!start) {
			this.controler = UIController.createInstance(this);
			// no nosso caso vai ser a tela inicial vai ser GPSMain, quando
			// juntar os projetos esta tela inicial vai ser o login
			 controler.setCurrent(FormGPS.getInstance());
			this.start = true;
		} else {

		}

	}

}
