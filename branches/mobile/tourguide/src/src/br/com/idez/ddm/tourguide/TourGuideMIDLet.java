package br.com.idez.ddm.tourguide;

import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import br.com.idez.ddm.tourguide.core.Record;
import br.com.idez.ddm.tourguide.core.UIController;
import br.com.idez.ddm.tourguide.telas.Inicial;

public class TourGuideMIDLet extends MIDlet {

	private boolean started = false;
	private UIController controller = null;

	public TourGuideMIDLet() {
		Record.init("TOURGUIDE");
		System.out.println("Construindo MIDlet");
	}

	public void destroyApp(boolean arg0) throws MIDletStateChangeException {
		System.out.println("Destruindo MIDlet");
	}

	protected void pauseApp() {

	}

	protected void startApp() throws MIDletStateChangeException {
		System.out.println("Iniciando MIDlet");

		/*
		 * C�digo para testar o Parser XML
		 */
		// InputStream in = getClass().getResourceAsStream(
		// "/PontosEstrategicos.xml");
		//
		// try {
		// Parser.getInstance().parse(in);
		// } catch (XmlPullParserException e) {
		// e.printStackTrace();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		
		// Se acontecer um evendo de re-inicializa��o (pauseApp invocado) o
		// MIDLet volta ao estado corrente.
		if (!started) {
			this.controller = UIController.createInstance(this);
			controller.setCurrent(Inicial.getInstance());
			started = true;
		} else {
			Displayable displayable = controller.getCurrentDisplayable();
			controller.setCurrent(displayable);
		}
	}
}
