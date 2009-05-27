package br.com.idez.ddm.tourguide.core;

import java.io.InputStream;
import java.util.Stack;
import java.util.Vector;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import br.com.idez.ddm.listener.landmarks.PontosMarcados;
import br.com.idez.ddm.tourguide.PontoEstrategico;
import br.com.idez.ddm.tourguide.TourGuideMIDLet;
import br.com.idez.ddm.tourguide.telas.Alerta;

public class UIController {

	private static UIController instance;

	private MIDlet midlet;
	private static Display display;

	private static Stack telas = new Stack();

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

	public static UIController getInstance() throws Exception {
		if (instance == null) {
			throw new Exception("Instancia não criada");
		}
		return instance;
	}

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
			((TourGuideMIDLet) midlet).destroyApp(false);
			((TourGuideMIDLet) midlet).notifyDestroyed();
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

	public void saveConfigs(String maxTime, String multimedia, String sound,
			String sync) {
		Record.setConfigMaxTime(maxTime);
		Record.setConfigMultimedia(multimedia);
		Record.setConfigSound(sound);
		Record.setConfigSync(sync);
	}

	public void sincronizar(boolean automatica) {

		try {
			InputStream in = TransmissaoDados.getInstance().getSyncXML();
			Vector pontos = Parser.getInstance().parse(in);
			PontoEstrategico pontoAtual;
			PontosMarcados pontosMarcados = new PontosMarcados();
			for (int i = 0; i < pontos.size(); i++) {
				pontoAtual = (PontoEstrategico) pontos.elementAt(i);
				if (PontoEstrategico.addPonto(pontoAtual))
					pontosMarcados.createLandmark(String.valueOf(pontoAtual
							.getId()), pontoAtual.getNome(), pontoAtual);
			}
			// exibe um alerta de sincronização completada se a sincronização
			// automatica não estiver ativa
			if (!automatica) {
				Alerta.getInstance().setTitle("Sincronização");
				Alerta.getInstance().setTexto(
						"Sincronização completada com sucesso");
				Alerta.getInstance().setType(AlertType.INFO);
				setCurrent(Alerta.getInstance());
				voltar();
				voltar();
			}
				
		} catch (Exception e) {
			excessaoGenerica("Houve um problema com a transmissão de dados");
		}
	}

	public static void excessaoGenerica(String mensagem) {

		Alerta alertaMensagem = Alerta.getInstance();
		alertaMensagem.setTexto(mensagem);
		UIController.telas.push(alertaMensagem);
		UIController.display.setCurrent(alertaMensagem);
	}
}