package br.com.idez.ddm.tourguide.core;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

public class TransmissaoDados {

	private static final String syncHost = "http://localhost/tourguide/";
	private static TransmissaoDados instance = null;

	private TransmissaoDados() {

	}

	public static TransmissaoDados getInstance() {
		if (instance == null) {
			instance = new TransmissaoDados();
		}
		return instance;
	}

	public InputStream getSyncXML() throws IOException {

		DataInputStream stream= null;
		try {
			
			HttpConnection conexao = (HttpConnection) Connector.open(syncHost
					+ "PontosEstrategicos.xml");

			conexao.setRequestMethod(HttpConnection.GET);
			stream = conexao.openDataInputStream();
		} catch (Exception e) {
			UIController.excessaoGenerica("Houve um problema com a conexão");
		}
		

		
		
		return stream;
	}

}
