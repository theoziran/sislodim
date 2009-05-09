package br.com.idez.ui;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextField;

import br.com.idez.core.GPS;
import br.com.idez.core.UIController;

public class FormConfiguracao extends Form implements CommandListener {

	public static FormConfiguracao instance;
	private Command cmdCancel;
	private Command cmdOK;
	private TextField tfLatitude;
	private TextField tfLongitude;
	private ChoiceGroup cgLista;
	private Alert alert = null;
	private FormConfiguracao(String nome) {
		super(nome);
		cmdCancel = new Command("Cancelar", Command.EXIT, 1);
		cmdOK = new Command("Selecionar", Command.OK, 1);
		cgLista = new ChoiceGroup("Selecione o intervalo de envio:",ChoiceGroup.EXCLUSIVE);
		
		cgLista.append("30 Segundos", null);
		cgLista.append("45 Segundos", null);
		cgLista.append("60 Segundos", null);
		cgLista.append("90 Segundos", null);
		addCommand(cmdCancel);
		addCommand(cmdOK);

		append(cgLista);
		
		setCommandListener(this);
	}

	public static FormConfiguracao getInstance() {
		if (instance == null) {
			instance = new FormConfiguracao("Configurações");
		}
		return instance;
	}

	public void commandAction(Command cmd, Displayable displayable) {
		if (cmd.equals(cmdCancel)) {
			if (displayable instanceof FormConfiguracao) {
				try {
					UIController.getInstance().sair();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if (cmd.equals(cmdOK)) {
			showAlert();
			try {
					UIController.getInstance().salvarConfiguracao(tempoSelecionado());
				} catch (Exception e) {
					TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	
	private String tempoSelecionado(){
		String tempo = null;
		switch(cgLista.getSelectedIndex()){
			case 0:
				tempo = "30";
				break;

			case 1: 
				tempo = "45";
				break;
				
			case 2: 
				tempo = "60";
				break;
				
			case 3: 
				tempo = "90";
				break;
				
		}
		
		return tempo;
		
		
	}
	
	private void showAlert(){
		this.alert = new Alert("O tempo foi alterado para "+tempoSelecionado()+" segundos");
		try {
			UIController.getInstance().setCurrent(alert, this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
