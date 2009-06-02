package br.faculdadeidez.psa.apresentacao.servlets;

import java.util.EventListener;
import java.util.EventObject;

import org.ajax4jsf.event.PushEventListener;

/**
 * Classe que faz o monitoramento do mapa
 *
 */
public class AtualizaMapa implements Runnable {

	/**
	 * Ouvinte no lado do servidor para execução do ajax
	 */
	PushEventListener listener;

	/**
	 * Propriedade que define se o monitoramento está ativado
	 */
	private boolean enabled = false;

	/**
	 * Thread que faz a atualização de tempos em tempos
	 */
	private Thread thread;

	/**
	 * Método principal da Thread
	 */
	@Override
	public void run() {
		while (thread != null) {
			try {
				listener.onEvent(new EventObject(this));
				Thread.sleep(10000);
			} catch (InterruptedException e) {
			}
		}

	}

	/**
	 * Captura o listener do evento
	 * @param listener EventListener
	 */
	public void addListener(EventListener listener) {
		synchronized (listener) {
			if (this.listener != listener) {
				this.listener = (PushEventListener) listener;
			}
		}
	}


	/**
	 * Inicia a execução da Thread
	 */
	public void start() {
		if (thread == null) {
			thread = new Thread(this);
			thread.setDaemon(true);
			thread.start();
			setEnabled(true);
		}
	}

	/**
	 * Para execução da Thread
	 */
	public void stop() {
		if (thread != null) {
			setEnabled(false);
			thread = null;
		}
	}


	/**
	 * Método getter do atributo enabled
	 * @return boolean
	 */
	public boolean getEnabled() {
		return enabled;
	}

	/**
	 * Método setter do atributo enabled
	 * @param enabled boolean
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}