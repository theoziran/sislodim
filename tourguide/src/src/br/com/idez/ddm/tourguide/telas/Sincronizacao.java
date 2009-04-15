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

public class Sincronizacao extends Form implements CommandListener {

	private static Sincronizacao instance;

	private StringItem siTexto;
	private ImageItem iiLoading;

	private Command cmdCancelar;

	public Sincronizacao(String title) {
		super(title);

		siTexto = new StringItem("Sincronizando...", null);

		iiLoading = new ImageItem(null, null, ImageItem.LAYOUT_CENTER,
				"loading");

		// cria a image
		Image image = null;
		try {
			image = Image.createImage("/loading.png");
		} catch (IOException e) {
			// FIXME tratar a exceção
		}
		iiLoading.setImage(image);

		cmdCancelar = new Command("Cancelar", Command.CANCEL, 1);

		this.append(iiLoading);
		this.append(siTexto);

		this.addCommand(cmdCancelar);

		this.setCommandListener(this);
	}

	public static Sincronizacao getInstance() {
		if (instance == null) {
			instance = new Sincronizacao("Sincronização");
		}
		return instance;
	}

	public void commandAction(Command cmd, Displayable displayable) {
		if (cmd.equals(cmdCancelar)) {
			// TODO implementar cancelamento de sincronização
			System.out.println("CANCELAR Selecionado");

			try {
				UIController.getInstance().voltar();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
