package br.com.idez.ddm.tourguide.telas;

import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;

import br.com.idez.ddm.tourguide.core.Record;
import br.com.idez.ddm.tourguide.core.UIController;

public class Configuracao extends Form implements CommandListener {

	private static Configuracao instance;

	private ChoiceGroup cgSons;
	private ChoiceGroup cgSync;
	private ChoiceGroup cgMultimedia;
	private TextField tfMaxTimeExecution;

	private Command cmdSalvar;
	private Command cmdVoltar;

	private Configuracao(String title) {
		super(title);

		cgSons = new ChoiceGroup("Sons", ChoiceGroup.EXCLUSIVE);
		cgSons.append("ON", null);
		cgSons.append("OFF", null);

		if (Record.getConfigSound().equals("ON")) {
			cgSons.setSelectedIndex(0, true);
		} else {
			cgSons.setSelectedIndex(1, true);
		}

		cgSync = new ChoiceGroup("Sincronização Automática",
				ChoiceGroup.EXCLUSIVE);
		cgSync.append("ON", null);
		cgSync.append("OFF", null);

		if (Record.getConfigSync().equals("ON")) {
			cgSync.setSelectedIndex(0, true);
		} else {
			cgSync.setSelectedIndex(1, true);
		}

		cgMultimedia = new ChoiceGroup("Conteúdo Multimídia",
				ChoiceGroup.EXCLUSIVE);
		cgMultimedia.append("ON", null);
		cgMultimedia.append("OFF", null);

		if (Record.getConfigMultimedia().equals("ON")) {
			cgMultimedia.setSelectedIndex(0, true);
		} else {
			cgMultimedia.setSelectedIndex(1, true);
		}

		tfMaxTimeExecution = new TextField("Tempo Máximo (segundos) ", null, 2,
				TextField.NUMERIC);
		tfMaxTimeExecution.setString(Record.getConfigMaxTime());

		cmdSalvar = new Command("Salvar", Command.OK, 1);
		cmdVoltar = new Command("Voltar", Command.BACK, 1);

		this.append(cgSons);
		this.append(cgSync);
		this.append(cgMultimedia);
		this.append(tfMaxTimeExecution);

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
			saveConfigs();
			// Alerta.getInstance();
			// Alerta.setTexto("Configurações Salvas com sucesso");
			// Alerta.setTipo(AlertType.INFO);
			// Alerta.getInstance().setTitulo("Configurações");

			try {
				UIController.getInstance().setCurrent(Alerta.getInstance());
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
		Record.setConfigMaxTime(this.tfMaxTimeExecution.getString());
		Record.setConfigMultimedia(this.cgMultimedia
				.getString(this.cgMultimedia.getSelectedIndex()));
		Record.setConfigSound(this.cgSons.getString(this.cgSons
				.getSelectedIndex()));
		Record.setConfigSync(this.cgSync.getString(this.cgSync
				.getSelectedIndex()));
	}
}
