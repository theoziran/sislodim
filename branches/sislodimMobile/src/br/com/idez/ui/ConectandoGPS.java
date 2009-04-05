/**
 * 
 */
package br.com.idez.ui;

import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Item;

/**
 * @author ricardo
 * 
 */
public class ConectandoGPS extends Form {

	private static ConectandoGPS instance = null;

	/**
	 * @param title
	 */
	private ConectandoGPS(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public static ConectandoGPS getInstance(String title) {
		if (instance == null) {
			instance = new ConectandoGPS(title);
		}

		return instance;
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public ConectandoGPS(String arg0, Item[] arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}
