package br.com.idez.ddm.tourguide.telas;

import java.io.IOException;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.StringItem;

import br.com.idez.ddm.tourguide.core.UIController;

public class Inicial extends Form implements CommandListener {

	private static Inicial instance;

	private StringItem siTexto;
	private ImageItem iiMapa;

	private Command cmdMenu;
	private Command cmdSair;

	private Inicial(String title) {
		super(title);

		siTexto = new StringItem("Mapa com a localiza��o atual", null);

		iiMapa = new ImageItem(null, null, ImageItem.LAYOUT_CENTER,
				"localiza��o atual");

		// cria a imagem
		Image image = null;
		try {
			image = Image.createImage("/mapa.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		iiMapa.setImage(image);

		cmdMenu = new Command("Menu", Command.OK, 1);
		cmdSair = new Command("Sair", Command.EXIT, 0);

		this.append(iiMapa);
		this.append(siTexto);

		this.addCommand(cmdMenu);
		this.addCommand(cmdSair);

		this.setCommandListener(this);
	}

	public static Inicial getInstance() {
		if (instance == null) {
			instance = new Inicial("Tour Guide");
		}
		return instance;
	}

	public void commandAction(Command cmd, Displayable displayable) {
		if (cmd.equals(cmdMenu)) {
			try {
				UIController.getInstance().setCurrent(
						Menu.getInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("MENU selecionado");
		} else if (cmd.equals(cmdSair)) {
			// vai no UIController e faz sair
			try {
				UIController.getInstance().sair();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}
