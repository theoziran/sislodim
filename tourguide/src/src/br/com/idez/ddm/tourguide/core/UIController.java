package br.com.idez.ddm.tourguide.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Stack;
import java.util.Vector;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import org.xmlpull.v1.XmlPullParserException;

import br.com.idez.ddm.tourguide.PontoEstrategico;
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

	public void setCurrent(Alert alert, Displayable nextDisplayable) {
		this.telas.push(nextDisplayable);
		this.display.setCurrent(alert, nextDisplayable);
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
			e.printStackTrace();
		}
	}

	public void voltar() {
		if (this.telas.size() > 1) {
			this.telas.pop();
			Displayable displayable = (Displayable) this.telas.pop();
			setCurrent(displayable);
		}
	}

	public void saveConfigs(String maxTime, String multimedia, String sound,
			String sync) {
		Record.setConfigMaxTime(maxTime);
		Record.setConfigMultimedia(multimedia);
		Record.setConfigSound(sound);
		Record.setConfigSync(sync);
	}

	public void sincronizar() {

		try {
			InputStream in = TransmissaoDados.getInstance().getSyncXML();
			Vector pontos = Parser.getInstance().parse(in);

			for (int i = 0; i < pontos.size(); i++) {
				PontoEstrategico.addPonto((PontoEstrategico) pontos
						.elementAt(i));
			}

		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}