package br.com.idez.ddm.tourguide.telas;

import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;

import br.com.idez.ddm.tourguide.core.TourGuideMIDLetController;

public class Inicial {
	private static Inicial instance;
	private Form form;
	private StringItem stTexto;

	public Inicial() {
		this.form = new Form("TourGuide");
		this.stTexto = new StringItem("Mapa com a localização atual...", "");

		this.form.append(stTexto);

		this.form.addCommand(TourGuideMIDLetController.MENU_CMD);
		this.form.addCommand(TourGuideMIDLetController.VOLTAR_CMD);
		this.form.setCommandListener(TourGuideMIDLetController.getInstance());
	}

	public static Inicial getInstance() {
		if (instance == null) {
			instance = new Inicial();
		}
		return instance;
	}

	public Form getForm() {
		return form;
	}
}
