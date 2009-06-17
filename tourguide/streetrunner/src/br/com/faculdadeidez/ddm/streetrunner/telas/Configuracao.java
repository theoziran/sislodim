package br.com.faculdadeidez.ddm.streetrunner.telas;

import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;

import br.com.faculdadeidez.ddm.streetrunner.Record;
import br.com.faculdadeidez.ddm.streetrunner.UIController;

public class Configuracao extends Form implements CommandListener {

	private static Configuracao instance;

	private ChoiceGroup cgSons;

	private Command cmdSalvar;
	private Command cmdVoltar;

	private Configuracao(String title) {
		super(title);

		cgSons = new ChoiceGroup("Sons", Choice.EXCLUSIVE);
		cgSons.append("ON", null);
		cgSons.append("OFF", null);

		if (Record.getConfigSound().equals("ON")) {
			cgSons.setSelectedIndex(0, true);
		} else {
			cgSons.setSelectedIndex(1, true);
		}

		cmdSalvar = new Command("Salvar", Command.OK, 1);
		cmdVoltar = new Command("Voltar", Command.BACK, 1);

		this.append(cgSons);

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

			try {
				saveConfigs();
				UIController.getInstance().setCurrent(Alerta.getInstance(),
						this);
				UIController.getInstance().voltar();
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println("Configurações salvas");
			// TODO exibir mensagem de confirmação

		} else if (cmd.equals(cmdVoltar)) {
			System.out.println("VOLTAR selecionado");

			try {
				UIController.getInstance().voltar();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void saveConfigs() {
		String sound;
		sound = cgSons.getString(cgSons.getSelectedIndex());
		try {
			UIController.getInstance().saveConfigs(sound);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Alerta.getInstance().setTitle("Configurações");
		Alerta.getInstance().setTexto("Configurações Salvas com sucesso");
		Alerta.getInstance().setType(AlertType.INFO);
	}

	public ChoiceGroup getCgSons() {
		return cgSons;
	}

}
