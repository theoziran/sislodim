package br.com.idez.exemplos;

import javax.microedition.lcdui.TextBox;
import javax.microedition.lcdui.TextField;

public class CaixaDeTexto extends TextBox {
	
	private static CaixaDeTexto instance;

	private CaixaDeTexto(String title, String text, int maxSize, int constraints) {
		super(title, text, maxSize, constraints);
	}
	
	public static CaixaDeTexto getInstance() {
		if (instance == null) {
			instance = new CaixaDeTexto("Caixa de Texto", null, 200, TextField.ANY);
		}
		return instance;
	}

}
