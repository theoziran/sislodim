package br.com.idez.ui.util;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.StringItem;

import br.com.idez.core.UIController;

public class Alerta extends Form implements CommandListener{
	private static Alerta instance;
	
	private Command cmdBack;
	private ImageItem imgAlerta;
	private StringItem lblTitulo;
	private String titulo;
	private int tipo;
	
	public void setImage(String imagem) { 
		try {
			this.imgAlerta.setImage(Image.createImage(imagem));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setTitulo(String titulo) { 
		this.titulo = titulo;
		atualizaValores();
	}
	
	public void setTipo(int tipo) { 
		this.tipo = tipo;
		atualizaValores();
	}
	
	private Alerta() {
		super("");
		
		this.setTitle(this.titulo);
		
		this.cmdBack = new Command("Voltar", Command.BACK, 1);
		this.imgAlerta = new ImageItem("", null, ImageItem.LAYOUT_CENTER, titulo);
		this.lblTitulo = new StringItem("", "");
		this.lblTitulo.setLayout(StringItem.LAYOUT_CENTER);
		
		this.append(new ImageItem("", Image.createImage(1, 50), ImageItem.LAYOUT_NEWLINE_AFTER, ""));
		this.append(imgAlerta);
		this.append(new ImageItem("", null, ImageItem.LAYOUT_NEWLINE_AFTER, ""));
		this.append(lblTitulo);
		
		this.addCommand(cmdBack);
				
		this.setCommandListener(this);
	}
	
	private void atualizaValores() { 
		this.lblTitulo.setText(this.titulo);
		
		try {		
			if(this.tipo == 0) { 
				// se o tipo de alerta é de alerta
				this.imgAlerta.setImage(Image.createImage("/ajax-loader.png"));
			}
			else if(this.tipo == 2) { 
				// se o tipo de alerta é de erro
				this.imgAlerta.setImage(Image.createImage("/alert.png"));	
			}
			else {
				// para todos os outros, exibe a imagem de alerta
				this.imgAlerta.setImage(Image.createImage("/alert_64x64.png"));
			}		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Alerta getInstance(String mensagem, int tipo) {
		if (instance == null) {
			instance = new Alerta();
		}
		
		instance.setTitulo(mensagem);
		instance.setTipo(tipo);
		
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
