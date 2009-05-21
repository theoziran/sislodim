package br.com.idez.ddm.tourguide.telas;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
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

		siTexto = new StringItem("Dejesa realmente iniciar a sincronização?",
				null);

		cmdCancelar = new Command("Cancelar", Command.CANCEL, 1);
		addCommand(new Command("Sincronizar", Command.OK, 1));

		this.append("Deseja realmente iniciar a sincronização?");

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
			System.out.println("VOLTAR Selecionado");

			try {
				UIController.getInstance().voltar();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			conectar();
		}
	}

	public void conectar() {
		System.out.println("Criando Thread");
		siTexto = new StringItem("Sincronizando", null);

		new Thread(new Runnable() {

			public void run() {
				try {
					UIController.getInstance().sincronizar();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}).start();

	}

}
