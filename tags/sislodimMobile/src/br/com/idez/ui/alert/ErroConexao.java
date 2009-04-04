package br.com.idez.ui.alert;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Gauge;
import javax.microedition.lcdui.Image;

public class ErroConexao extends Alert implements CommandListener {

	private Gauge gauge;

	public ErroConexao(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public ErroConexao(String title, String alertText, Image alertImage,
			AlertType alertType) {
		super(title, alertText, alertImage, alertType);
		// TODO Auto-generated constructor stub

		this.gauge = new Gauge("conectando", false, 20, 0);
		for (int i = 0; i < 20; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			gauge.setValue(i);
		}
	}

	public void commandAction(Command arg0, Displayable arg1) {
		// TODO Auto-generated method stub

	}

}
