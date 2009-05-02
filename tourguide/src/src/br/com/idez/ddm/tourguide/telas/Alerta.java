package br.com.idez.ddm.tourguide.telas;

import javax.microedition.lcdui.Alert;

public class Alerta extends Alert {

	private static Alerta instance;
	private String texto;

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		super.setString(texto);
	}

	// private Alerta(String title, String alertText, Image alertImage,
	// AlertType alertType) {
	// super(title, alertText, alertImage, alertType);
	// }

	private Alerta() {
		super("");
	}

	public static Alerta getInstance() {
		if (instance == null) {
			instance = new Alerta();
		}
		return instance;
	}
}
