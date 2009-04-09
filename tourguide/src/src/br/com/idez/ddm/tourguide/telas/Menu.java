package br.com.idez.ddm.tourguide.telas;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;

import br.com.idez.ddm.tourguide.core.UIController;

public class Menu extends List implements CommandListener {

	private static Menu instance;
	private Command cmdVoltar;
	private Command cmdOk;

	private Menu(String title, int listType) {
		super(title, listType);
		this.cmdVoltar = new Command("Voltar", Command.BACK, 1);
		this.cmdOk = new Command("OK", Command.OK, 1);

		this.append("Sincronizar Dados", null);
		this.append("Configurações", null);
		this.append("Exibir Ponto Estratégico", null);

		this.addCommand(cmdVoltar);
		this.addCommand(cmdOk);

		this.setCommandListener(this);
	}

	public static Menu getInstance() {
		if (instance == null) {
			instance = new Menu("MENU", List.IMPLICIT);
		}
		return instance;
	}

	public void commandAction(Command cmd, Displayable displayable) {
		try {
			if (cmd.equals(cmdVoltar)) {
				// vai no UIController e volta a tela
				System.out.println("VOLTAR selecionado");
				UIController.getInstance().voltar();
			} else if (cmd.equals(cmdOk)) {
				if (displayable instanceof Menu) {
					Menu menu = (Menu) displayable;
					switch (menu.getSelectedIndex()) {
					case 0: {
						// TODO direcionar para a sicnronização
						System.out.println("Sincronizar selecionado");

						UIController.getInstance().setCurrent(
								Sincronizacao.getInstance());

						break;
					}
					case 1: {
						// TODO direcionar para a configuração
						System.out.println("Configurar selecionado");

						UIController.getInstance().setCurrent(
								Configuracao.getInstance());
						break;
					}
					case 2: {
						// TODO direcionar para a configuração
						System.out.println("Exibir ponto estratégico");

						UIController.getInstance().setCurrent(
								ExibicaoPonto.getInstance());
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
