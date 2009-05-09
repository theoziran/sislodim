package br.faculdadeidez.psa.servico;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class GoogleMapsDistance {

	private String origem = null;
	private String destino = null;
	private final String URL = "http://maps.google.com/maps?output=kml&&hl=pt-BR&f=d&source=s_d";
	private final String SADDR = "&saddr=";
	private final String DADDR = "&daddr=";

	// private final String TYPE = "output=kml";

	public GoogleMapsDistance(String origem, String destino) {
		this.setOrigem(origem);
		this.setDestino(destino);
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem.replace(" ", "+");
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino.replace(" ", "+");
	}

	public String getURL() {
		return URL;
	}

	public String getSADDR() {
		return SADDR;
	}

	public String getDADDR() {
		return DADDR;
	}

	public String getKmlDistance() {
		URL url = null;
		BufferedReader bf;
		StringBuffer sb;
		String res = null;
		String linha;
		try {

			url = new URL(this.getURL() + this.getSADDR() + this.getOrigem()
					+ this.getDADDR() + this.getDestino());
			bf = new BufferedReader(new InputStreamReader(url.openStream()));
			sb = new StringBuffer();

			while ((linha = bf.readLine()) != null) {
				sb.append(linha);
			}
			bf.close();
			res = sb.toString();
		} catch (MalformedURLException e) {
			res = null;
		} catch (IOException e) {
			res = null;
		}
		return res;
	}

	public GoogleMaps retornaDistancia(String kml) {
		GoogleMaps maps = new GoogleMaps();

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		Document doc = null;
		Element elements;
		NodeList nodes;
		String res = null;
		if (!kml.equals(null) && kml.length() > 0) {
			try {
				db = dbf.newDocumentBuilder();
				InputSource is = new InputSource(new ByteArrayInputStream(kml
						.getBytes()));
				doc = db.parse(is);
				elements = doc.getDocumentElement();
				nodes = elements.getElementsByTagName("description");
				for (int i = 0; i < nodes.getLength(); i++) {
					Element element = (Element) nodes.item(i);
					res = (String) element.getTextContent();
					if (res.trim().length() > 0) {

						res = res.trim();

					}
				}
				res = res.replace('à', 'a');
				res = res.replace('è', 'e');
				res = res.replace('ì', 'i');
				res = res.replace('ò', 'o');
				res = res.replace('ù', 'u');
				res = res.replace('á', 'a');
				res = res.replace('é', 'e');
				res = res.replace('í', 'i');
				res = res.replace('ó', 'o');
				res = res.replace('ú', 'u');
				res = res.replace('â', 'a');
				res = res.replace('ê', 'e');
				res = res.replace('î', 'i');
				res = res.replace('ô', 'o');
				res = res.replace('û', 'u');
				res = res.replace('ã', 'a');
				res = res.replace('õ', 'o');
				res = res.replace('ä', 'a');
				res = res.replace('ë', 'e');
				res = res.replace('ï', 'i');
				res = res.replace('ö', 'o');
				res = res.replace('ü', 'u');
				res = res.replace('ç', 'c');

				res = res.replaceAll("[a-zA-Z]+:", "");
				res = res.trim();
				res = res.replaceAll("[a-zA-Z]+", "");
				res = res.trim();
				res = res.replaceAll("&#160;", "");
				res = res.trim();

				String[] split = res.split(" ");

				maps.setDistancia(split[0]);

			} catch (ParserConfigurationException e) {
				maps.setMsgErro("Erro");
			} catch (SAXException e) {
				maps.setMsgErro("Erro");
			} catch (IOException e) {
				maps.setMsgErro("Erro");
			}
		} else {
			maps.setMsgErro("Erro");
		}

		return maps;
	}

	public static void main(String[] args) {
		GoogleMapsDistance distance = new GoogleMapsDistance(
				"rua praia de ponta negra, joão pessoa, paraiba",
				"Rua Jeová Lins, joão pessoa, paraiba");
		GoogleMaps maps = distance.retornaDistancia(distance.getKmlDistance());
		System.out.println(maps.getDistancia());
	}
}
