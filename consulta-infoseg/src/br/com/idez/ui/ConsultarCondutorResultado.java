package br.com.idez.ui;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;

import br.com.idez.core.UIController;

public class ConsultarCondutorResultado extends Form implements CommandListener{
	private static ConsultarCondutorResultado instance;
	
	private Command cmdBack;
	private Command cmdOk;	
	private TextField txtPlacaVeiculo;
	private StringItem lblProprietario;
	private StringItem lblCPF;
	private StringItem lblRG;
	private StringItem lblOrgaoExped;
	private StringItem lblDataNasc;
	private StringItem lblNaturalidade;
	private StringItem lblNomePai;
	private StringItem lblNomeMae;
	private StringItem lblDataValCartCNH;
	private TextField txtOcorrencia;
		
	private ConsultarCondutorResultado(String title, String xml) {
		super(title);
			
		this.txtPlacaVeiculo = new TextField("Placa do veículo: ", null, 7, TextField.ANY);
		this.lblProprietario = new StringItem("Nome: ", "");
		this.lblCPF = new StringItem("CPF: ", "");
		this.lblRG = new StringItem("RG: ", "");
		this.lblOrgaoExped = new StringItem("Órgão Exped: ", "");
		this.lblDataNasc = new StringItem("Data Nasc: ", "");
		this.lblNaturalidade = new StringItem("Naturalidade: ", "");
		this.lblNomePai = new StringItem("Nome do Pai: ", "");
		this.lblNomeMae = new StringItem("Nome da Mãe: ", "");
		this.lblDataValCartCNH = new StringItem("Data de Val. CNH: ", "");
		this.txtOcorrencia = new TextField("Observações: ", "", 255, TextField.UNEDITABLE);
			
		this.append(txtPlacaVeiculo);
		this.append(new ImageItem("", null, ImageItem.LAYOUT_NEWLINE_AFTER, ""));
		this.append(lblProprietario);
		this.append(new ImageItem("", null, ImageItem.LAYOUT_NEWLINE_AFTER, ""));		
		this.append(lblCPF);
		this.append(new ImageItem("", null, ImageItem.LAYOUT_NEWLINE_AFTER, ""));		
		this.append(lblRG);
		this.append(new ImageItem("", null, ImageItem.LAYOUT_NEWLINE_AFTER, ""));		
		this.append(lblOrgaoExped);
		this.append(new ImageItem("", null, ImageItem.LAYOUT_NEWLINE_AFTER, ""));		
		this.append(lblDataNasc);
		this.append(new ImageItem("", null, ImageItem.LAYOUT_NEWLINE_AFTER, ""));		
		this.append(lblNaturalidade);
		this.append(new ImageItem("", null, ImageItem.LAYOUT_NEWLINE_AFTER, ""));		
		this.append(lblNomePai);
		this.append(new ImageItem("", null, ImageItem.LAYOUT_NEWLINE_AFTER, ""));		
		this.append(lblNomeMae);
		this.append(new ImageItem("", null, ImageItem.LAYOUT_NEWLINE_AFTER, ""));		
		this.append(lblDataValCartCNH);
		this.append(new ImageItem("", null, ImageItem.LAYOUT_NEWLINE_AFTER, ""));
		this.append(txtOcorrencia);
		
		this.cmdBack = new Command("Voltar", Command.BACK, 1);
		this.cmdOk = new Command("Ok", Command.OK, 1);
		
		processaXmlRecebido(xml);
		
		this.addCommand(cmdBack);
		this.addCommand(cmdOk);	
		
		this.setCommandListener(this);
	}
	
	private void processaXmlRecebido(String xml) { 
		
	}
	
	public static ConsultarCondutorResultado getInstance(String retornoXML) {
		if (instance == null) {
			instance = new ConsultarCondutorResultado("2. Consultar Condutores", retornoXML);
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
