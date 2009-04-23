package br.com.idez;

import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import br.com.idez.core.UIController;
import br.com.idez.ui.Menu;

public class DDMMIDLet extends MIDlet {
	
	private boolean started = false;
	UIController controller = null;

	public DDMMIDLet() {
		System.out.println("Carregando o midlet...");
	}

	public void destroyApp(boolean arg0) {
		// TODO Auto-generated method stub

	}

	protected void pauseApp() {
		// TODO Auto-generated method stub

	}

	protected void startApp() throws MIDletStateChangeException {
		System.out.println("Executando o midlet...");
		
		// Se acontecer um evendo de re-inicialização (pauseApp invocado) o
		// MIDLet volta ao estado corrente.
		if (!started) {
			this.controller = UIController.createInstance(this);
			controller.setCurrent(Menu.getInstance());
			//controller.setCurrent(Alerta.getInstance("Processando...", 0));		
			started = true;
		} else {
			Displayable displayable = controller.getCurrentDisplayable();
			controller.setCurrent(displayable);
		}
		
	}

}
