package br.com.idez.ddm.tourguide.telas;

import java.io.IOException;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.StringItem;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import br.com.idez.ddm.tourguide.core.UIController;

public class Sincronizacao extends Form implements CommandListener {

	private static Sincronizacao instance;

	private StringItem siTexto;
	private ImageItem iiLoading;

	private Command cmdCancelar;

	public Sincronizacao(String title) {
		super(title);

		siTexto = new StringItem("Dejesa realmente iniciar a sincronização?", null);

//		iiLoading = new ImageItem(null, null, ImageItem.LAYOUT_CENTER,
//				"loading");
//
//		// cria a image
//		Image image = null;
//		try {
//			image = Image.createImage("/loading.png");
//		} catch (IOException e) {
//			// FIXME tratar a exceção
//		}
//		iiLoading.setImage(image);

		cmdCancelar = new Command("Cancelar", Command.CANCEL, 1);
		addCommand(new Command("Sincronizar", Command.OK, 1));
		
		//this.append(iiLoading);
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}else {
				conectar();
			}
	}
	public void conectar() {
		System.out.println("Criando Thread");
		siTexto = new StringItem("Sincronizando", null);
		
        new Thread(new Runnable() {

            public void run() {

            	append("\nConectando");

                String url = ("http://devmobile.blog.br/testeconexao.php");

                HttpConnection http = null;

                InputStream inStream = null;

                try {

                    http = (HttpConnection) Connector.open(url, Connector.READ_WRITE, true);

                    append("\nConexão criada!");

                    http.setRequestMethod(HttpConnection.GET);

                    http.setRequestProperty("Connection", url);

                    append("\nPegando response code");

                    int rc = http.getResponseCode();

                    append("\nResponse code: "+ rc);

                    if (rc == HttpConnection.HTTP_OK) {

                        inStream = http.openInputStream();

                        System.out.println("InputStream aberta");

                        byte[] data = new byte[512];

                        int count;

                        ByteArrayOutputStream returnData = new ByteArrayOutputStream();

                        while ((count = inStream.read(data)) > -1)

                            if (count > 0)

                                returnData.write(data, 0, count);

                        String respStr = new String(returnData.toByteArray());

                        returnData.close();

                        append("\nResposta do servidor: " + respStr);

                    } else 

                    	append("\nErro " + rc + " na resposta do servidor: " + http.getResponseMessage() + ".");

                } catch (Exception e) {

                	System.out.println("Exception: " + e.getMessage());

                }

                try { inStream.close(); } catch (Exception e) {}

                try { http.close(); } catch (Exception e) {}

                append("\nFinalizado!");

            }

        }).start();

    }


}
