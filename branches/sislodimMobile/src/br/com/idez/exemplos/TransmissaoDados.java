package br.com.idez.exemplos;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

public class TransmissaoDados {
	/**
	 * Lembrando que esse método é temporário até que seja apresentado um
	 * framework para trasmissão de dados que abrange transmissão HTTP O
	 * framework será aprensentado ou desenvolvido na próxima aula de
	 * Desenvolvimento para dispositivos móveis (09/04/2009) Alterar os tipos para
	 * o que melhor adequar
	 */
	
	/**
	 * Essa classes foi testa com um script php e funcionou corretamente no nosso caso
	 * só precisa substituir o endereço da URL
	 */
	private static final String servletHost = "http://localhost/servlet.php?";

	public void enviarDados(int longitude, int latitude, int viatura) throws IOException {
		/** 
		 * Nesse momento peço para o JME abrir uma conexão pra mim, é o JME
		 * intercede com o device para minha comunicação
		 */
		HttpConnection conexao = (HttpConnection) Connector.open(servletHost+"longitude="+longitude+"&latitude="+latitude+"&viatura="+viatura);

		// Define o tipo de requisição
		conexao.setRequestMethod(HttpConnection.GET);

		/**
		 * Retorno a saída da requisição em um inputStream caso queira fazer
		 * algum tratamento
		 */
		InputStream inputStream = conexao.openInputStream();

	}
	

}
