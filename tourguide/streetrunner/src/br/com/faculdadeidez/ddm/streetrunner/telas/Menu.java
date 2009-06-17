package br.com.faculdadeidez.ddm.streetrunner.telas;

import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;

import br.com.faculdadeidez.ddm.streetrunner.UIController;

public class Menu extends List implements CommandListener {

	private static Menu instance;
	private Command cmdOk;

	private Menu(String title, int listType) {
		super(title, listType);
		this.cmdOk = new Command("OK", Command.OK, 1);
		this.append("Iniciar Jogo", null);
		this.append("Continuar Jogo", null);
		this.append("Configurações", null);
		this.append("Sair", null);
		this.addCommand(cmdOk);
		this.setCommandListener(this);
	}

	public static Menu getInstance() {
		if (instance == null) {
			instance = new Menu("MENU", Choice.IMPLICIT);
		}
		return instance;
	}

	public void commandAction(Command cmd, Displayable displayable) {
		try {
			if (cmd.equals(cmdOk)) {
				if (displayable instanceof Menu) {
					Menu menu = (Menu) displayable;
					switch (menu.getSelectedIndex()) {
					case 0: {
						UIController.getInstance().setLevel(1);
						UIController.getInstance().iniciarJogo();
						System.out.println("Iniciar selecionado");
						break;
					}
					case 1: {
						System.out.println("Continuar jogo selecionado");

						break;
					}
					case 2: {
						System.out.println("Configurações selecionado");

						break;
					}
					case 3: {
						System.out.println("Sair selecionado");
						UIController.getInstance().sair();
						break;
					}
					default: {
						System.out.println("Opção Indefinida");
					}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
