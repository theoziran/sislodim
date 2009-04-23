package br.com.idez.ui;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;

import br.com.idez.core.UIController;

public class ConsultarVeiculoResultado extends Form implements CommandListener{
	private static ConsultarVeiculoResultado instance;
	
	private Command cmdBack;
	private Command cmdOk;	
	private TextField txtPlacaVeiculo;
	private StringItem lblProprietario;
	private StringItem lblCPF;
	private StringItem lblModelo;
	private StringItem lblAno;
	private StringItem lblDataFabricacao;
	private TextField txtOutrasInfo;
		
	private ConsultarVeiculoResultado(String title, String xml) {
		super(title);
					
		this.txtPlacaVeiculo = new TextField("Placa: ", null, 7, TextField.ANY);
		this.lblProprietario = new StringItem("Nome: ", "");
		this.lblCPF = new StringItem("CPF: ", "");
		this.lblModelo = new StringItem("Modelo: ", "");
		this.lblAno = new StringItem("Ano: ", "");
		this.lblDataFabricacao = new StringItem("Fabricação: ", "");
		this.txtOutrasInfo = new TextField("Outras informações: ", "", 255, TextField.UNEDITABLE);
			
		this.append(txtPlacaVeiculo);
		this.append(new ImageItem("", null, ImageItem.LAYOUT_NEWLINE_AFTER, ""));
		this.append(lblProprietario);
		this.append(new ImageItem("", null, ImageItem.LAYOUT_NEWLINE_AFTER, ""));		
		this.append(lblCPF);
		this.append(new ImageItem("", null, ImageItem.LAYOUT_NEWLINE_AFTER, ""));		
		this.append(lblModelo);
		this.append(new ImageItem("", null, ImageItem.LAYOUT_NEWLINE_AFTER, ""));		
		this.append(lblAno);
		this.append(new ImageItem("", null, ImageItem.LAYOUT_NEWLINE_AFTER, ""));		
		this.append(lblDataFabricacao);		
		this.append(new ImageItem("", null, ImageItem.LAYOUT_NEWLINE_AFTER, ""));
		this.append(txtOutrasInfo);
		
		this.cmdBack = new Command("Voltar", Command.BACK, 1);
		this.cmdOk = new Command("Ok", Command.OK, 1);
		
		processaXmlRecebido(xml);
		
		this.addCommand(cmdBack);
		this.addCommand(cmdOk);	
		
		this.setCommandListener(this);
	}
	
	private void processaXmlRecebido(String xml) { 
		
	}
	
	public static ConsultarVeiculoResultado getInstance(String retornoXML) {
		if (instance == null) {
			instance = new ConsultarVeiculoResultado("1. Consultar Veículos", retornoXML);
		}
				
		return instance;		
	}
	
	public void commandAction(Command cmd, Displayable disp) {
		
		if (cmd.equals(cmdBack)) {
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
