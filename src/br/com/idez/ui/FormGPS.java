package br.com.idez.ui;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.TextField;

import br.com.idez.core.GPS;
import br.com.idez.core.UIController;

public class FormGPS extends Form implements CommandListener {

	public static FormGPS instance;
	private Command cmdExit;
	private Command cmdStart;
	private TextField tfLatitude;
	private TextField tfLongitude;

	private FormGPS(String title) {
		super(title);
		cmdExit = new Command("exit", Command.EXIT, 1);
		cmdStart = new Command("start", Command.OK, 1);
		//tfLatitude = new TextField("Latitude:", null, 15, TextField.ANY);
		//tfLongitude = new TextField("Longitude:", null, 15, TextField.ANY);

		addCommand(cmdExit);
		addCommand(cmdStart);

		setCommandListener(this);
	}

	private FormGPS(String title, Item[] item) {
		super(title, item);

	}

	public static FormGPS getInstance() {
		if (instance == null) {
			instance = new FormGPS("Ativando GPS...");
		}
		return instance;
	}

	public void commandAction(Command cmd, Displayable displayable) {
		if (cmd.equals(cmdExit)) {
			if (displayable instanceof FormGPS) {
				try {
					UIController.getInstance().sair();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
				}
			}
		}
		if (cmd.equals(cmdStart)) {
			if (displayable instanceof FormGPS) {
				this.removeCommand(cmdStart);
				/*GPS gps =*/ new GPS();
				//gps.run();
				//tfLatitude.setString(Double.toString(gps.getLatitude()));
				//tfLongitude.setString(Double.toString(gps.getLongitude()));
				//this.append(tfLatitude);
				//this.append(tfLongitude);
				this.append("Pesquisando satelites...");
			}
		}

	}

	// public void startGps() {
	// try {
	// provaider = LocationProvider.getInstance(FormGPS.getCriteria());
	// System.out.println(provaider);
	// if (provaider != null) {
	//
	// location = provaider.getLocation(5);
	// System.err.println(location);
	// coordinates = location.getQualifiedCoordinates();
	// System.out.println(coordinates);
	// if (coordinates != null) {
	// System.out.println(coordinates.getAltitude());
	// System.out.println(coordinates.getLatitude());
	// this.append("Latitude: " + coordinates.getLatitude());
	// this.append("Longitude: " + coordinates.getLongitude());
	// }
	//
	// this.append("buscando Gps....");
	// }
	// } catch (LocationException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

}
