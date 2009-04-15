package br.com.idez.ddm.tourguide.core;

import java.util.Stack;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;

public class TourGuideMIDLetController implements CommandListener {

	private static TourGuideMIDLetController instance;
	private Display display;
	private Stack telas = new Stack();

	private TourGuideMIDLetController() {
	}

	public static TourGuideMIDLetController getInstance() {
		if (instance == null) {
			instance = new TourGuideMIDLetController();
		}
		return instance;
	}

	public static final Command MENU_CMD = new Command("Menu", Command.OK, 0);
	public static final Command VOLTAR_CMD = new Command("Voltar",
			Command.BACK, 1);

	public void commandAction(Command cmd, Displayable disp) {
		if (cmd.equals(MENU_CMD)) {
			// TODO implementar acesso ao menu
			System.out.println("MENU Selecionado");
		}
		if (cmd.equals(VOLTAR_CMD)) {
			telas.pop();
			Displayable d = (Displayable) telas.pop();
			setCurrent(d);
		}

	}

	public void setCurrent(Displayable tela) {
		telas.push(tela);
		display.setCurrent(tela);
	}

	public void setDisplay(Display display) {
		this.display = display;
	}

}
