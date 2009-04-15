package br.com.idez.ddm.tourguide.telas;

import java.io.IOException;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Image;

public class Alerta extends Alert {

	private static Alerta instance;
	private static Image img;
	private static String texto, titulo="Erro";
	private static AlertType tipo;

	public static String getTexto() {
		return texto;
	}

	public static void setTexto(String texto) {
		Alerta.texto = texto;
	}

	public static String getTitulo() {
		return titulo;
	}

	public static void setTitulo(String titulo) {
		Alerta.titulo = titulo;
	}

	public static AlertType getTipo() {
		return tipo;
	}

	public static void setTipo(AlertType tipo) {
		Alerta.tipo = tipo;
	}

	public Alerta(String title, String alertText, Image alertImage,
			AlertType alertType) {
		super(title, alertText, alertImage, alertType);
	}

	public static Alerta getInstance() {
		if (instance == null) {
			try {
				img = Image.createImage("/alert.png");
			} catch (IOException e) {
				e.printStackTrace();
			}
			instance = new Alerta(getTitulo(), "Erro", img, AlertType.INFO);
		}
		return instance;
	}
}
