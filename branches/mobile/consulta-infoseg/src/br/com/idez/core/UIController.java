package br.com.idez.core;

import java.util.Stack;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;

import br.com.idez.DDMMIDLet;
import br.com.idez.ui.Menu;

public class UIController {
	
	// padrão singleton
	private static UIController instance;
	
	// dislay e midlet para gerenciamento das telas
	private Display disp;
	private MIDlet midlet;

	// pilha de telas
	private Stack telas;
	
	private UIController(MIDlet midlet) {
		this.midlet = midlet;
		this.disp = Display.getDisplay(midlet);
		this.telas = new Stack();
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
		this.telas.push(displayable);
		this.disp.setCurrent(displayable);
	}
	
	/**
	 * Vai na entidade de autenticação e realiza o login
	 * 
	 * @param login
	 * @param senha
	 */
	public void login(String login, String senha) {
		if (login.equals("admin") && senha.equals("123")) {// login bem sucedido
			// TODO registra o login
			// FIXME alterar para autenticar no servidor web
			setCurrent(Menu.getInstance());
		} else {	
			// TODO Alerta login inválido
		}
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
			setCurrent(displayable);
		}
	}
	
	public Displayable getCurrentDisplayable() {
		return (Displayable)this.telas.pop();
	}
}
