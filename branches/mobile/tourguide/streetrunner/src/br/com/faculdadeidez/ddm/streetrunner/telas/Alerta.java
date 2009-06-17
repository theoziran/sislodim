package br.com.faculdadeidez.ddm.streetrunner.telas;

import java.io.IOException;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Image;


public class Alerta extends Alert {

	private static Alerta instance;
	private String texto;
	private static Image img;

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		super.setString(texto);
	}

	 private Alerta(String title, String alertText, Image alertImage,AlertType alertType) {
		super(title, alertText, alertImage, alertType);
	}

	private Alerta() {
		super("");
	}

	public static Alerta getInstance() {
		if (instance == null) {
			instance = new Alerta();
		}
		return instance;
	}
	
	public static Alerta getInstance(String title, String alertText) {
		if (instance == null) {
			try {
				img = Image.createImage("/alert.png");
			} catch (IOException e) {
				e.printStackTrace();
			}
			instance = new Alerta(title, alertText, img, AlertType.CONFIRMATION);
		}
		return instance;
	}
	
}
