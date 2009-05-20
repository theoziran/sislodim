package br.com.idez.ui;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;

import br.com.idez.core.UIController;
import br.com.idez.ui.util.Alerta;

public class ConsultarCondutor extends Form implements CommandListener{
	private static ConsultarCondutor instance;

	private Command cmdBack;
	private Command cmdOk;
	private TextField txtIdentificacaoCNH;
	
	private ConsultarCondutor(String title) {
		super(title);
		this.append("Digite o número de registro da CNH: ");

		this.txtIdentificacaoCNH = new TextField("Identificação CNH: ", null, 11, TextField.ANY);		
		this.cmdBack = new Command("Voltar", Command.BACK, 1);
		this.cmdOk = new Command("Ok", Command.OK, 1);
		
		this.append(txtIdentificacaoCNH);
		
		this.addCommand(cmdBack);
		this.addCommand(cmdOk);
		
		this.setCommandListener(this);
		
	}
	
	public static ConsultarCondutor getInstance() {
		if (instance == null) {
			instance = new ConsultarCondutor("2. Consultar Condutores");
		}
		return instance;		
	}
	
	public void commandAction(Command cmd, Displayable disp) {
		
		if (cmd.equals(cmdOk)) {
			/**
			 * Consulta informações do condutor em um serviço
			 */
			try {	
				System.out.println("Iniciando consulta de condutor...");
				
				if(this.txtIdentificacaoCNH.getString().equals("")) {
					UIController.getInstance().setCurrent(Alerta.getInstance("Você deve informar o CNH do condutor", 1));				
				}
				else { 				
					UIController.getInstance().consultaCondutor(this.txtIdentificacaoCNH.getString());
				}
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
