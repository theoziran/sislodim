package br.com.idez.ddm.tourguide.telas;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;

import br.com.idez.ddm.tourguide.core.*;



public class GPS extends Form implements CommandListener{

	public static GPS instance;
	private Command cmdExit;
	
	private GPS(String title) {
		super(title);
		cmdExit = new Command("exit", Command.EXIT, 1);
		addCommand(cmdExit);
		new Coordenada();
		this.setCommandListener(this);
	}

	public static GPS getInstance() {
		if (instance == null) {
			instance = new GPS("Ativando GPS...");
		}
		return instance;
	}

	public void commandAction(Command cmd, Displayable displayable) {
		if (cmd.equals(cmdExit)) {
			if (displayable instanceof GPS) {
				try {
					UIController.getInstance().sair();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}
