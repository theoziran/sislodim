package br.com.idez.ui;

import java.util.Vector;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;

import br.com.idez.core.UIController;
import br.com.idez.core.xmlparser.ConsultaParser;
import br.com.idez.vo.OcorrenciaVO;
import br.com.idez.vo.PessoaVO;
import br.com.idez.vo.VeiculoVO;

public class ConsultarOcorrenciaResultado extends Form implements CommandListener{
	private static ConsultarOcorrenciaResultado instance;
	
	private Command cmdBack;
	private StringItem lblPlacaVeiculo;
	private StringItem lblProprietario;
	private StringItem lblCPF;
	private TextField txtOcorrencia;
		
	private ConsultarOcorrenciaResultado(String title) {
		super(title);
		
		this.lblPlacaVeiculo = new StringItem("Placa do veículo: ", "");
		this.lblProprietario = new StringItem("Nome: ", "");
		this.lblCPF = new StringItem("CPF: ", "");
		this.txtOcorrencia = new TextField("Ocorrência: ", "", 255, TextField.ANY);
			
		this.append(lblPlacaVeiculo);
		this.append(new ImageItem("", null, ImageItem.LAYOUT_NEWLINE_AFTER, ""));
		this.append(lblProprietario);
		this.append(new ImageItem("", null, ImageItem.LAYOUT_NEWLINE_AFTER, ""));		
		this.append(lblCPF);
		this.append(new ImageItem("", null, ImageItem.LAYOUT_NEWLINE_AFTER, ""));
		this.append(txtOcorrencia);
		
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

			Vector lstOcorrencias = parserXML.getLstOcorrencias();

			StringBuffer str = new StringBuffer();
			for (int i = 0; i < lstOcorrencias.size(); i++) {
				OcorrenciaVO ocorrencia = (OcorrenciaVO) lstOcorrencias
						.elementAt(i);
				str.append(ocorrencia.getOcorrencia());
				str.append("; ");
			}

			this.txtOcorrencia.setString(str.toString());
		} catch (Exception exc) {
			System.out.println("Não foi possível processar o xml: "
					+ exc.getMessage());
		}
	}
	
	public static ConsultarOcorrenciaResultado getInstance() {
		if (instance == null) {
			instance = new ConsultarOcorrenciaResultado("3. Consultar Ocorrências");
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
