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

public class ConsultarCondutorResultado extends Form implements CommandListener {
	private static ConsultarCondutorResultado instance;

	private Command cmdBack;
	private StringItem lblPlacaVeiculo;
	private StringItem lblProprietario;
	private StringItem lblCPF;
	private StringItem lblRG;
	private StringItem lblOrgaoExped;
	private StringItem lblDataNasc;
	private StringItem lblNaturalidade;
	private StringItem lblNomePai;
	private StringItem lblNomeMae;
	private StringItem lblDataValCartCNH;
	private TextField txtObservacoes;

	private ConsultarCondutorResultado(String title) {
		super(title);

		this.lblPlacaVeiculo = new StringItem("Placa do veículo: ", "");
		this.lblProprietario = new StringItem("Nome: ", "");
		this.lblCPF = new StringItem("CPF: ", "");
		this.lblRG = new StringItem("RG: ", "");
		this.lblOrgaoExped = new StringItem("Órgão Exped: ", "");
		this.lblDataNasc = new StringItem("Data Nasc: ", "");
		this.lblNaturalidade = new StringItem("Naturalidade: ", "");
		this.lblNomePai = new StringItem("Nome do Pai: ", "");
		this.lblNomeMae = new StringItem("Nome da Mãe: ", "");
		this.lblDataValCartCNH = new StringItem("Data de Val. CNH: ", "");
		this.txtObservacoes = new TextField("Observações: ", "", 255,TextField.ANY);

		this.append(lblPlacaVeiculo);
		this.append(new ImageItem("", null, ImageItem.LAYOUT_NEWLINE_AFTER,""));
		this.append(lblProprietario);
		this.append(new ImageItem("", null, ImageItem.LAYOUT_NEWLINE_AFTER,""));
		this.append(lblCPF);
		this.append(new ImageItem("", null, ImageItem.LAYOUT_NEWLINE_AFTER,""));
		this.append(lblRG);
		this.append(new ImageItem("", null, ImageItem.LAYOUT_NEWLINE_AFTER,""));
		this.append(lblOrgaoExped);
		this.append(new ImageItem("", null, ImageItem.LAYOUT_NEWLINE_AFTER,""));
		this.append(lblDataNasc);
		this.append(new ImageItem("", null, ImageItem.LAYOUT_NEWLINE_AFTER,""));
		this.append(lblNaturalidade);
		this.append(new ImageItem("", null, ImageItem.LAYOUT_NEWLINE_AFTER,""));
		this.append(lblNomePai);
		this.append(new ImageItem("", null, ImageItem.LAYOUT_NEWLINE_AFTER,""));
		this.append(lblNomeMae);
		this.append(new ImageItem("", null, ImageItem.LAYOUT_NEWLINE_AFTER,""));
		this.append(lblDataValCartCNH);
		this.append(new ImageItem("", null, ImageItem.LAYOUT_NEWLINE_AFTER,""));
		this.append(txtObservacoes);

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
			this.lblRG.setText(pessoa.getRg());
			this.lblOrgaoExped.setText(pessoa.getOrgaoExped());
			this.lblDataNasc.setText(pessoa.getDataNasc());
			this.lblNaturalidade.setText(pessoa.getNaturalidade());
			this.lblNomePai.setText(pessoa.getNomePai());
			this.lblNomeMae.setText(pessoa.getNomeMae());
			this.lblDataValCartCNH.setText(pessoa.getCnh().getDataValidade());
			this.txtObservacoes.setString(pessoa.getCnh().getObservacoes());

			VeiculoVO veiculo = parserXML.getVeiculo();
			this.lblPlacaVeiculo.setText(veiculo.getPlaca());
		} catch (Exception exc) {
			System.out.println("Não foi possível processar o xml: "
					+ exc.getMessage());
		}
	}

	public static ConsultarCondutorResultado getInstance() {
		if (instance == null) {
			instance = new ConsultarCondutorResultado("2. Consultar Condutores");
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
