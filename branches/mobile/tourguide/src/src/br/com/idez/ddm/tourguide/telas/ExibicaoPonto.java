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

public class ExibicaoPonto extends Form implements CommandListener {

	private static ExibicaoPonto instance;

	private StringItem siTexto;
	private ImageItem iiPontoEstrategico;

	private Command cmdVoltar;

	private ExibicaoPonto(String title) {
		super(title);

		siTexto = new StringItem("Farol do Cabo Branco", null);

		iiPontoEstrategico = new ImageItem(null, null, ImageItem.LAYOUT_CENTER,
				"ponto estrategico");

		// cria a image
		Image image = null;
		try {
			image = Image.createImage("/farol.png");
		} catch (IOException e) {
			image = null;
		}
		iiPontoEstrategico.setImage(image);

		cmdVoltar = new Command("Voltar", Command.BACK, 1);

		this.append(iiPontoEstrategico);
		this.append(siTexto);

		this.addCommand(cmdVoltar);

		this.setCommandListener(this);
	}

	public static ExibicaoPonto getInstance() {
		if (instance == null) {
			instance = new ExibicaoPonto("Ponto Estratégico");
		}
		return instance;
	}

	public void commandAction(Command cmd, Displayable displayable) {
		if (cmd.equals(cmdVoltar)) {
			System.out.println("VOLTAR Selecionado");

			try {
				UIController.getInstance().voltar();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
