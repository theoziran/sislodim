package br.com.idez.core;

import java.util.Stack;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;

import br.com.idez.DDMMIDLet;
import br.com.idez.core.request.ConsultaCondutorRequest;
import br.com.idez.core.request.ConsultaOcorrenciaRequest;
import br.com.idez.core.request.ConsultaVeiculoRequest;
import br.com.idez.core.request.IRequest;
import br.com.idez.core.response.ConsultaCondutorResponse;
import br.com.idez.core.response.ConsultaOcorrenciaResponse;
import br.com.idez.core.response.ConsultaVeiculoResponse;
import br.com.idez.core.response.IResponse;
import br.com.idez.ui.ConsultarCondutorResultado;
import br.com.idez.ui.ConsultarOcorrenciaResultado;
import br.com.idez.ui.ConsultarVeiculoResultado;
import br.com.idez.ui.Splash;
import br.com.idez.ui.util.Alerta;

public class UIController {
	
	// padrão singleton
	private static UIController instance;
	
	// dislay e midlet para gerenciamento das telas
	private Display disp;
	private MIDlet midlet;

	// pilha de telas
	private Stack telas;

	// connection
	private Connection conn;
	private IRequest req;
	private IResponse resp;
	
	// dispatcher
	private Dispatcher dispatcher;
	
	private UIController(MIDlet midlet) {
		this.midlet = midlet;
		this.disp = Display.getDisplay(midlet);
		this.telas = new Stack();
		
		this.conn = new Connection();
		this.conn.start();
		
		this.dispatcher = new Dispatcher();
		this.dispatcher.start();
	}
	
	/**
	 * Para uso do midlet
	 * 
	 * @param midlet
	 * @return UIController instance
	 */
	public static UIController createInstance(MIDlet midlet) {
		if (instance == null) {
			instance = new UIController(midlet);
		}
		return instance;
	}
	
	/**
	 * Para uso das telas
	 * 
	 * @return UIController instance
	 * @throws Exception
	 */
	public static UIController getInstance() throws Exception {
		if (instance == null) {
			throw new Exception("Instancia não criada");
		}
		return instance;
	}

	/**
	 * Coloca no display a tela passada como parâmetro e guarda a tela na pilha
	 * de telas, para que as operações de "voltar" funcionem corretamente.
	 * 
	 * @param displayable
	 */
	public void setCurrent(Displayable displayable) {
		if(displayable instanceof Splash)
		{
			/* -> não faz nada!!
			 * 
			 * Quando a tela é do tipo Splash, não é necessário adicionar na lista
			 * de telas ativas
			 * 
			 */
		}
		else { 		
			this.telas.push(displayable);
		}
		
		this.disp.setCurrent(displayable);
	}
	
	public void consultaOcorrencia(String placa) { 
		this.req = new ConsultaOcorrenciaRequest(placa);		
		execute();
	}
	
	public void consultaVeiculo(String placa) { 
		this.req = new ConsultaVeiculoRequest(placa);		
		execute();
	}
	
	public void consultaCondutor(String identificacaoCNH) { 
		this.req = new ConsultaCondutorRequest(identificacaoCNH);		
		execute();
	}
	
	/**
	 * Sai da aplicação
	 * 
	 */
	public void sair() {
		((DDMMIDLet)midlet).destroyApp(false);
		((DDMMIDLet)midlet).notifyDestroyed();
	}
	
	/**
	 * Vai na pilha de telas, retira a tela atual e coloca tela anterior no display.
	 * 
	 */
	public void voltar() {
		if (this.telas.size() > 1) {
			this.telas.pop();// retira a tela corrent
			Displayable displayable = (Displayable)this.telas.pop(); // retira a tela anterior
			if(displayable instanceof Alerta) displayable = (Displayable)this.telas.pop(); // se a tela anterior foi uma alerta, remove também
			
			setCurrent(displayable);
		}
	}
	
	public Displayable getCurrentDisplayable() {
		return (Displayable)this.telas.pop();
	}	
	
	/*
	 * Métodos e classes responsáveis pelo processamento multi-thread
	 */
	
	private void execute() {
		setCurrent(Alerta.getInstance("Processando...", 0));
		
		synchronized (dispatcher) {
			dispatcher.notifyAll();
		}
	}
	
	class Dispatcher extends Thread {
		
		private boolean running = true;
		
		public void run() {
			while (running) {
				synchronized (this) {
					try {
						this.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					resp = performConnection();
					
					System.out.println("resp = " + resp);
					
					// verifica se nenhuma resposta foi retornada
					if(resp.getResponse().equals("0")) { 
						System.out.println("Nenhum registro foi encontrado");
						setCurrent(Alerta.getInstance("Nenhum registro foi encontrado", 1));
					}
					else if(resp.getResponse().equals(""))  { 
						System.out.println("Não foi possível realizar a pesquisa");
						setCurrent(Alerta.getInstance("Não foi possível realizar a pesquisa", 2));
					}
					else {
						// a consulta retornou dados
						System.out.println("Encontrou dados: " + resp.getResponse());
						
						try { 						
							// verificação para cada tipo de resposta
							if (resp instanceof ConsultaOcorrenciaResponse) {
								setCurrent(ConsultarOcorrenciaResultado.getInstance());
								ConsultarOcorrenciaResultado.getInstance().processaXml(resp.getResponse());
							}
							else if(resp instanceof ConsultaCondutorResponse) { 
								setCurrent(ConsultarCondutorResultado.getInstance());							
								ConsultarCondutorResultado.getInstance().processaXml(resp.getResponse());
							}
							else if(resp instanceof ConsultaVeiculoResponse) {
								setCurrent(ConsultarVeiculoResultado.getInstance());
								ConsultarVeiculoResultado.getInstance().processaXml(resp.getResponse());
							}
						}
						catch(Exception exc) {
							setCurrent(Alerta.getInstance("Não foi possível carregar esta informação", 2));
							exc.printStackTrace();
						}
					}
				}
				
			}
		}

		private IResponse performConnection() {
			synchronized (conn) {
				conn.setReq(req);
				System.out.println("performing connection...");
				conn.notifyAll();
				try {
					conn.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return conn.getResp();
		}
	}
}
