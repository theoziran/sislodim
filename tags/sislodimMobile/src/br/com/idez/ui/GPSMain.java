package br.com.idez.ui;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Item;

import br.com.idez.core.UIController;

public class GPSMain extends Form implements CommandListener {

	private Command cmdSair, cmdBuscar, cmdBack;
	private static GPSMain instance = null;

	private GPSMain(String title) {
		super(title);
		this.cmdSair = new Command("sair", Command.EXIT, 1);
		this.cmdBuscar = new Command("burcar gps", Command.OK, 2);
		this.cmdBack = new Command("back", Command.BACK, 3);

		this.addCommand(cmdSair);
		this.addCommand(cmdBuscar);
		this.addCommand(cmdBack);

		this.setCommandListener(this);
	}

	private GPSMain(String title, Item[] item) {
		super(title, item);

		this.cmdSair = new Command("sair", Command.EXIT, 1);
		this.cmdBuscar = new Command("burcar gps", Command.OK, 2);
		this.cmdBack = new Command("back", Command.BACK, 3);

		this.addCommand(cmdSair);
		this.addCommand(cmdBuscar);
		this.addCommand(cmdBack);

		this.setCommandListener(this);
	}

	public static GPSMain getInstance() {
		if (instance == null) {
			instance = new GPSMain("Projeto GPS DDM");
			System.out.println("GPS iniciado");
		}
		return instance;
	}

	public void commandAction(Command cmd, Displayable disp) {
		if (cmd.equals(cmdSair)) {
			try {
				if (disp instanceof GPSMain) {
					UIController.getInstance().sair();
				}
			} catch (Exception e) {
			}
		} else if (cmd.equals(cmdBuscar)) {

		} else if (cmd.equals(cmdBack)) {

		}
	}

}
