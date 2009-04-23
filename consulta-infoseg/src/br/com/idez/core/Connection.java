package br.com.idez.core;

import java.io.DataInputStream;
import java.io.IOException;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

import br.com.idez.core.request.ConsultaCondutorRequest;
import br.com.idez.core.request.ConsultaOcorrenciaRequest;
import br.com.idez.core.request.ConsultaVeiculoRequest;
import br.com.idez.core.request.IRequest;
import br.com.idez.core.response.ConsultaCondutorResponse;
import br.com.idez.core.response.ConsultaOcorrenciaResponse;
import br.com.idez.core.response.ConsultaVeiculoResponse;
import br.com.idez.core.response.IResponse;

public class Connection extends Thread {
	
	public static final String SERVER_ADDRESS = "localhost";
	public static final String SERVER_PORT = "8080";
	public static final String SERVER_FOLDER_PATH = "/ProjetoDDMServlets";
	public static final String SERVER_PROTOCOL = "http://";
	
	private IRequest req;
	private IResponse resp;
	
	private boolean running = true;
	
	/**
	 * Para o serviço de conexão 
	 * 
	 * @param running
	 */
	public void setRunning(boolean running) {
		this.running = running;
	}

	public void run() {
		
		while (running) {

			synchronized (this) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// realiza a conexão
				String url = SERVER_PROTOCOL + SERVER_ADDRESS + ":" + SERVER_PORT  + SERVER_FOLDER_PATH + this.req.getRequest();
				System.out.println(url);
				String response = doConnect(url);
				System.out.println("response = " + response);
				
				if (this.req instanceof ConsultaOcorrenciaRequest) {
					 this.resp = new ConsultaOcorrenciaResponse(response);
				}
				else if (this.req instanceof ConsultaCondutorRequest) {
					 this.resp = new ConsultaCondutorResponse(response);
				}
				else if (this.req instanceof ConsultaVeiculoRequest) {
					 this.resp = new ConsultaVeiculoResponse(response);
				}
			}
			synchronized (this) {
				this.notifyAll();
			}
		}		
	}	
	
	public IRequest getReq() {
		return req;
	}

	public void setReq(IRequest req) {
		this.req = req;
	}

	public IResponse getResp() {
		return resp;
	}

	public void setResp(IResponse resp) {
		this.resp = resp;
	}

	/**
	 * Método genérico para conexões HTTP em qualquer URL
	 *  
	 * @param url
	 * @return
	 */
	public String doConnect(String url) {
		HttpConnection conn = null;
		DataInputStream stream = null;
		StringBuffer sb = new StringBuffer();
		try {
			conn = (HttpConnection) Connector.open(url);
			conn.setRequestMethod(HttpConnection.GET);
			// pipe de leitura
			stream = conn.openDataInputStream();
			while (stream.available() > 0) {
				char c = (char) stream.read();
				sb.append(c);
				System.out.print(c);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stream != null)
					stream.close();
				if (conn != null)
					conn.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		return sb.toString();
		
	}

}
