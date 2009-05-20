package br.com.idez.ui;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;

import br.com.idez.core.UIController;
import br.com.idez.core.xmlparser.ConsultaParser;
import br.com.idez.vo.PessoaVO;
import br.com.idez.vo.VeiculoVO;

public class ConsultarVeiculoResultado extends Form implements CommandListener{
	private static ConsultarVeiculoResultado instance;
	
	private Command cmdBack;
	private StringItem lblPlacaVeiculo;
	private StringItem lblProprietario;
	private StringItem lblCPF;
	private StringItem lblModelo;
	private StringItem lblAno;
	private StringItem lblDataFabricacao;
	private TextField txtOutrasInfo;
		
	private ConsultarVeiculoResultado(String title) {
		super(title);
					
		this.lblPlacaVeiculo = new StringItem("Placa do veículo: ", "");
		this.lblProprietario = new StringItem("Nome: ", "");
		this.lblCPF = new StringItem("CPF: ", "");
		this.lblModelo = new StringItem("Modelo: ", "");
		this.lblAno = new StringItem("Ano: ", "");
		this.lblDataFabricacao = new StringItem("Fabricação: ", "");
		this.txtOutrasInfo = new TextField("Outras informações: ", "", 255, TextField.ANY);
			
		this.append(lblPlacaVeiculo);
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
				
		this.addCommand(cmdBack);
		
		this.setCommandListener(this);
	}
	
	public void processaXml(String xml) { 
		try {
			ConsultaParser parserXML = new ConsultaParser(xml);

			PessoaVO pessoa = parserXML.getPessoa();
			this.lblProprietario.setText(pessoa.getNome());
			this.lblCPF.setText(pessoa.getCpf());
			
			VeiculoVO veiculo = parserXML.getVeiculo();
			this.lblPlacaVeiculo.setText(veiculo.getPlaca());
			this.lblModelo.setText(veiculo.getModelo());	
			this.lblAno.setText(String.valueOf(veiculo.getAno()));
			this.lblDataFabricacao.setText(String.valueOf(veiculo.getAnoFrabricacao()));
			
			this.txtOutrasInfo.setString(veiculo.getInformacoes());
		} catch (Exception exc) {
			System.out.println("Não foi possível processar o xml: "
					+ exc.getMessage());
		}
	}
	
	public static ConsultarVeiculoResultado getInstance() {
		if (instance == null) {
			instance = new ConsultarVeiculoResultado("1. Consultar Veículos");
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
