package br.com.idez.ui.alert;

import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Gauge;

public class Conectando extends Form {

	private Gauge gauge;
	private static Conectando instance = null;

	private Conectando(String title) {
		super(title);
		this.gauge = new Gauge("tentando conectar...", false, 20, 0);
		incrementarGauge(this.gauge);

	}

	private void incrementarGauge(Gauge gauge) {
		if (gauge instanceof Gauge) {
			for (int i = 0; i < 20; i++) {
				gauge.setValue(i);
				if (i == 19) {
					i = 0;
				}
			}
		}

	}

	public static Conectando getInstance() {
		if (instance == null) {
			instance = new Conectando("Conectando");
		}
		return instance;
	}

}
