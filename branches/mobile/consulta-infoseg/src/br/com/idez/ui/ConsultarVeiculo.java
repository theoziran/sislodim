package br.com.idez.ui;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;

import br.com.idez.core.UIController;

public class ConsultarVeiculo extends Form implements CommandListener{
	private static ConsultarVeiculo instance;
	
	private Command cmdBack;
	private Command cmdOk;		
	
	private ConsultarVeiculo(String title) {
		super(title);
		this.append("Digite a placa do veículo que deseja pesquisar: ");
		this.append(new TextField("Placa do veículo: ",null,6,TextField.ANY));
		
		this.cmdBack = new Command("Voltar", Command.BACK, 1);
		this.cmdOk = new Command("Ok", Command.OK, 1);	

		this.addCommand(cmdBack);
		this.addCommand(cmdOk);
		
		this.setCommandListener(this);
	}
	
	public static ConsultarVeiculo getInstance() {
		if (instance == null) {
			instance = new ConsultarVeiculo("Consultar veículo");
		}
		return instance;		
	}
	
	public void commandAction(Command cmd, Displayable disp) {
		
		if (cmd.equals(cmdOk)) {
			/**
			 * Consulta informações do veículo em um serviço
			 */
			try {	
				System.out.println("Iniciando consulta de veículo...");
				//UIController.getInstance().login(tfLogin.getString(), tfSenha.getString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (cmd.equals(cmdBack)) {
			/**
			 * Volta a aplicação para a tela anterior, que nesse caso é o menu
			 */
			try {	
				UIController.getInstance().voltar();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}		

}
