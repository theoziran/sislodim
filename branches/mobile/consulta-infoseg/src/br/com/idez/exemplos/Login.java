package br.com.idez.exemplos;

import java.io.IOException;
import java.util.Date;

import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Gauge;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemStateListener;
import javax.microedition.lcdui.TextField;

import br.com.idez.core.UIController;

public class Login extends Form implements CommandListener, ItemStateListener {
	
	private static Login instance;
	
	private TextField tfLogin;
	private TextField tfSenha;
	private DateField df;
	private Gauge gauge;
	private ChoiceGroup cg;
	private ImageItem ii;
	
	private Command cmdExit;
	private Command cmdOk;
	private Command cmdGauge;

	private Login(String title) {
		super(title);
		
		this.tfLogin = new TextField("Login: ", null, 10, TextField.ANY);
		this.tfSenha = new TextField("Senha: ", null, 10, TextField.PASSWORD);
		this.df = new DateField("Calendario", DateField.DATE);
		// configurar a data atual
		this.df.setDate(new Date());
		this.gauge = new Gauge("Volume", true, 10, 2);
		
		this.cg = new ChoiceGroup("Opções", ChoiceGroup.MULTIPLE);// radio
		this.ii = new ImageItem("iDEZ logo", null, ImageItem.LAYOUT_CENTER, "logotipo");
		
		// cria a image
		Image image = null;
		try {
			image = Image.createImage("/unibratec.png");
		} catch (IOException e) {
			//FIXME tratar a exceção
		}
		
		ii.setImage(image);
		// popular o choicegroup
		this.cg.append("Opção 1", null);
		this.cg.append("Opção 2", null);
		this.cg.append("Opção 3", null);
		
		this.cmdExit = new Command("sair", Command.EXIT, 1);
		this.cmdOk = new Command("ok", Command.OK, 1);
		this.cmdGauge = new Command("Alterar", Command.OK, 1);
		
		this.append(tfLogin);
		this.append(tfSenha);
		this.append(df);
		this.append(gauge);
		this.append(cg);
		this.append(ii);
		
		this.addCommand(cmdExit);
		this.addCommand(cmdOk);
		this.addCommand(cmdGauge);
		
		this.setCommandListener(this);
		this.setItemStateListener(this);
	}
	
	public static Login getInstance() {
		if (instance == null) {
			instance = new Login("LOGIN");
		}
		return instance;
	}

	public void commandAction(Command cmd, Displayable disp) {
		
		if (cmd.equals(cmdOk)) {
			// vai no UIController e faz login
			try {	
				UIController.getInstance().login(tfLogin.getString(), tfSenha.getString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (cmd.equals(cmdExit)) {
			// vai no UIController e faz sair
			try {	
				UIController.getInstance().sair();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (cmd.equals(cmdGauge)) {
			gauge.setValue(gauge.getValue()+1);
		}
		
	}

	public void commandAction(Command arg0, Item itm) {
		if (itm instanceof Gauge) {
			Gauge g = (Gauge)itm;
			System.out.println("Novo valor do Gauge = "+g.getValue());
		}
		
	}

	public void itemStateChanged(Item itm) {
		System.out.println("Evento do Item da classe " + itm.getClass().getName());
		if (itm instanceof Gauge) {
			Gauge g = (Gauge)itm;
			System.out.println("Novo valor do Gauge = "+g.getValue());
		}
		
	}

}
