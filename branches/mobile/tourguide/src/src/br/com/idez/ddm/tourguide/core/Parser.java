package br.com.idez.ddm.tourguide.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;

import org.kxml2.io.KXmlParser;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import br.com.idez.ddm.tourguide.PontoEstrategico;

public class Parser {

	private static Parser instance = null;

	private KXmlParser xmlParser = null;

	private Parser() {
		xmlParser = new KXmlParser();
	}

	public static Parser getInstance() {
		if (instance == null) {
			instance = new Parser();
		}
		return instance;
	}

	public Vector parse(InputStream entrada) throws XmlPullParserException,
			IOException {

		Vector pontos = new Vector();

		xmlParser.setInput(new InputStreamReader(entrada));
		xmlParser.nextTag();

		// Posiciona na tag <pontos>
		xmlParser.require(XmlPullParser.START_TAG, null, "pontos");

		while (xmlParser.nextTag() != XmlPullParser.END_TAG) {
			// Posiciona na tag <ponto>
			xmlParser.require(XmlPullParser.START_TAG, null, "ponto");

			String attributeName = xmlParser.getAttributeName(0);
			String attributeValue = xmlParser.getAttributeValue(0);

			System.out.println("--> ID = " + attributeValue);

			if (attributeName.equals("id")) {
				pontos.addElement(parsePonto(attributeValue));
			} else {
				System.out.println("atributo não esperado: " + attributeValue);
			}
			xmlParser.require(XmlPullParser.END_TAG, null, "ponto");
		}
		
		xmlParser.require(XmlPullParser.END_TAG, null, "pontos");
		xmlParser.next();
		xmlParser.require(XmlPullParser.END_DOCUMENT, null, null);

		return pontos;
	}

	private PontoEstrategico parsePonto(String id)
			throws XmlPullParserException, IOException {

		PontoEstrategico ponto = new PontoEstrategico();

		ponto.setId(Integer.parseInt(id));

		while (xmlParser.nextTag() != XmlPullParser.END_TAG) {
			xmlParser.require(XmlPullParser.START_TAG, null, null);

			String name = xmlParser.getName();
			String text = xmlParser.nextText();

			System.out.println("----->" + name + ": " + text);

			if (name.equals("nome"))
				ponto.setNome(text);
			else if (name.equals("lat"))
				ponto.setLatitude(Double.parseDouble(text));
			else if (name.equals("long"))
				ponto.setLongitude(Double.parseDouble(text));

			xmlParser.require(XmlPullParser.END_TAG, null, name);
		}
		return ponto;
	}

}
