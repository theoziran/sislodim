package br.com.faculdadeidez.ddm.streetrunner.telas;

import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;

import br.com.faculdadeidez.ddm.streetrunner.UIController;

public class MenuJogo extends List implements CommandListener {

	private static MenuJogo instance;
	private Command cmdVoltar;
	private Command cmdOk;

	private MenuJogo(String title, int listType) {
		super(title, listType);
		this.cmdVoltar = new Command("Voltar", Command.BACK, 1);
		this.cmdOk = new Command("OK", Command.OK, 1);
		this.append("Salvar Jogo", null);
		this.append("Sair", null);
		this.addCommand(cmdVoltar);
		this.addCommand(cmdOk);
		this.setCommandListener(this);
	}

	public static MenuJogo getInstance() {
		if (instance == null) {
			instance = new MenuJogo("MENU", Choice.IMPLICIT);
		}
		return instance;
	}

	public void commandAction(Command cmd, Displayable displayable) {
		try {
			if (cmd.equals(cmdVoltar)) {
				// TODO reiniciar thread do jogo
				System.out.println("VOLTAR selecionado");
				UIController.getInstance().voltar();
				UIController.getInstance().iniciarJogo();
			} else if (cmd.equals(cmdOk)) {
				if (displayable instanceof MenuJogo) {
					MenuJogo menu = (MenuJogo) displayable;
					switch (menu.getSelectedIndex()) {
					case 0: {
						UIController.getInstance().saveLevel();
						UIController.getInstance().voltar();
						UIController.getInstance().iniciarJogo();
						break;
					}
					case 1: {
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
