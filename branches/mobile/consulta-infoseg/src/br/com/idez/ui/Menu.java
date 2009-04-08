package br.com.idez.ui;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;

import br.com.idez.core.UIController;

public class Menu extends List implements CommandListener {
	
	private static Menu instance;
	private Command cmdExit;
	private Command cmdOk;
	
	
	private Menu(String title, int listType) {
		super(title, listType);
		this.cmdExit = new Command("Exit", Command.BACK, 1);
		this.cmdOk = new Command("Ok", Command.OK, 1);
		
		this.append("1. Consultar Veículos", null);
		this.append("2. Consultar Condutores", null);
		this.append("3. Consultar Ocorrências", null);
		
		this.addCommand(cmdExit);
		this.addCommand(cmdOk);
		this.setCommandListener(this);
	}
	
	public static Menu getInstance() {
		if (instance == null) {
			instance = new Menu("MENU", List.IMPLICIT);
		}
		return instance;
	}

	public void commandAction(Command cmd, Displayable disp) {
		try {
			if (cmd.equals(cmdOk)) {
				//Consultar veículos
				if(this.getSelectedIndex()==0){
					UIController.getInstance().setCurrent(ConsultarVeiculo.getInstance());
				//Consultar condutores	
				}else if(this.getSelectedIndex()==1){
					UIController.getInstance().setCurrent(ConsultarCondutor.getInstance());
				//Consultar ocorrências	
				}else if(this.getSelectedIndex()==2){
					UIController.getInstance().setCurrent(ConsultarOcorrencia.getInstance());					
				}
			} else if (cmd.equals(cmdExit)) {
				UIController.getInstance().sair();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
