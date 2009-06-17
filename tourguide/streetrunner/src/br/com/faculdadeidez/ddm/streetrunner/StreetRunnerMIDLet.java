package br.com.faculdadeidez.ddm.streetrunner;

import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import br.com.faculdadeidez.ddm.streetrunner.telas.Splash;

public class StreetRunnerMIDLet extends MIDlet {

	private boolean started = false;
	private UIController controller = null;

	public StreetRunnerMIDLet() {
		Record.init("STREETRUNNER");
		System.out.println("Construindo MIDlet");
	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		// TODO Auto-generated method stub

	}

	protected void pauseApp() {
		// TODO Auto-generated method stub

	}

	protected void startApp() throws MIDletStateChangeException {
		if (!started) {
			this.controller = UIController.createInstance(this);
			controller.setCurrent(Splash.getInstance());
			started = true;
		} else {
			Displayable displayable = controller.getCurrentDisplayable();
			controller.setCurrent(displayable);
		}

	}

}
