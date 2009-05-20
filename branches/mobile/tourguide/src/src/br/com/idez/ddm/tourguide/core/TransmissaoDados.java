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

	public InputStream getSyncXML()
			throws IOException {
		/**
		 * Nesse momento peço para o JME abrir uma conexão pra mim, é o JME
		 * intercede com o device para minha comunicação
		 */
		 HttpConnection conexao = (HttpConnection) Connector.open(syncHost+"PontosEstrategicos.xml");
		

		// Define o tipo de requisição
		conexao.setRequestMethod(HttpConnection.GET);
		DataInputStream stream = conexao.openDataInputStream();

		return stream;
		
		// String content = conexao.getHeaderField("Content-Type");
		// System.out.println("Content-Type: " + content);
		//
		// String agent = conexao.getHeaderField("User-Agent");
		// System.out.println("User-Agent: " + agent);
		//
		// String server = conexao.getHeaderField("Server");
		// System.out.println("Server: " + server);
		//
		// System.out.println("Response: " + conexao.getResponseMessage());
		// System.out.println("Response Code: " + conexao.getResponseCode());

		/**
		 * Retorno a saída da requisição em um inputStream caso queira fazer
		 * algum tratamento
		 */
		// InputStream inputStream = conexao.openInputStream();
		// System.out.println(inputStream.toString());
	}

}
