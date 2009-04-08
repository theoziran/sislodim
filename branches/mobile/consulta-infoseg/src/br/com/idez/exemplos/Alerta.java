package br.com.idez.exemplos;

import java.io.IOException;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Image;

public class Alerta extends Alert {
	
	private static Alerta instance;
	private static Image img;

	public Alerta(String title, String alertText, Image alertImage, AlertType alertType) {
		super(title, alertText, alertImage, alertType);
	}

	public static Alerta getInstance() {
		if (instance == null) {
			try {
				img = Image.createImage("/alert.png");
			} catch (IOException e) {
				e.printStackTrace();
			}
			instance = new Alerta("Erro", "Opção não implementada", img, AlertType.CONFIRMATION);
		}
		return instance;
	}

}
