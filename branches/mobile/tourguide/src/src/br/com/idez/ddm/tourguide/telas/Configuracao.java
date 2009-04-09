package br.com.idez.ddm.tourguide.telas;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;

import br.com.idez.ddm.tourguide.core.UIController;

public class Configuracao extends Form implements CommandListener {

	private static Configuracao instance;

	private TextField tfConfig1;
	private TextField tfConfig2;
	private TextField tfConfig3;

	private Command cmdSalvar;
	private Command cmdVoltar;

	private Configuracao(String title) {
		super(title);

		tfConfig1 = new TextField("Config1", null, 2, TextField.ANY);
		tfConfig2 = new TextField("Config2", null, 4, TextField.NUMERIC);
		tfConfig3 = new TextField("Config3", null, 2, TextField.DECIMAL);

		cmdSalvar = new Command("Salvar", Command.OK, 1);
		cmdVoltar = new Command("Voltar", Command.BACK, 1);

		this.append(tfConfig1);
		this.append(tfConfig2);
		this.append(tfConfig3);

		this.addCommand(cmdSalvar);
		this.addCommand(cmdVoltar);

		this.setCommandListener(this);

	}

	public static Configuracao getInstance() {
		if (instance == null) {
			instance = new Configuracao("Configuração");
		}
		return instance;
	}

	public void commandAction(Command cmd, Displayable displayable) {
		if (cmd.equals(cmdSalvar)) {
			// TODO implementar salvar configurações
			System.out.println("Salvando configurações");

		} else if (cmd.equals(cmdVoltar)) {
			System.out.println("VOLTAR selecionado");

			try {
				UIController.getInstance().voltar();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
