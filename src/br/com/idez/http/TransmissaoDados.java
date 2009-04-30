package br.com.idez.http;

import java.io.DataInputStream;
import java.io.IOException;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

public class TransmissaoDados {
	/**
	 * Lembrando que esse método é temporário até que seja apresentado um
	 * framework para trasmissão de dados que abrange transmissão HTTP O
	 * framework será aprensentado ou desenvolvido na próxima aula de
	 * Desenvolvimento para dispositivos móveis (09/04/2009) Alterar os tipos
	 * para o que melhor adequar
	 */

	/**
	 * Essa classes foi testa com um script php e funcionou corretamente no
	 * nosso caso só precisa substituir o endereço da URL
	 */
	private static final String servletHost = "http://localhost:8080/Sislodim/MIDServlet?";
	private static TransmissaoDados instance = null;

	private TransmissaoDados() {

	}

	public static TransmissaoDados getInstance() {
		if (instance == null) {
			instance = new TransmissaoDados();
		}
		return instance;
	}

	public void enviarDados(double longitude, double latitude, String viatura)
			throws IOException {
		/**
		 * Nesse momento peço para o JME abrir uma conexão pra mim, é o JME
		 * intercede com o device para minha comunicação
		 */
		 HttpConnection conexao = (HttpConnection) Connector.open(servletHost
		 + "longitude=" + longitude + "&latitude=" + latitude
		 + "&codVtr=" + viatura);
		

		// Define o tipo de requisição
		conexao.setRequestMethod(HttpConnection.GET);
		DataInputStream stream = conexao.openDataInputStream();
		String content = conexao.getHeaderField("Content-Type");
		System.out.println("Content-Type: " + content);

		String agent = conexao.getHeaderField("User-Agent");
		System.out.println("User-Agent: " + agent);

		String server = conexao.getHeaderField("Server");
		System.out.println("Server: " + server);

		System.out.println("Response: " + conexao.getResponseMessage());
		System.out.println("Response Code: " + conexao.getResponseCode());

		/**
		 * Retorno a saída da requisição em um inputStream caso queira fazer
		 * algum tratamento
		 */
		// InputStream inputStream = conexao.openInputStream();
		// System.out.println(inputStream.toString());
	}

}
