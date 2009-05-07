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

public class RetornaEndereco {

	private String latitude = null;
	private String longitude = null;
	private String xml = null;

	// CONSTANTES
	private final String URL = "http://maps.google.com/maps/geo?output=xml&oe=utf-8&ll=";
	private final String KEY = "&key=ABQIAAAAzmhk1rsDjNgnLZAsYOvQSRSjuaEVZENCKjhga9SCJmC3LvuwVBR8Ioq6jiQaq4b7Ft9EJwXpA4e1nA";

	public RetornaEndereco() {
		// TODO Auto-generated constructor stub
	}

	public RetornaEndereco(String latitude, String longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getURL() {
		return URL;
	}

	public String getKEY() {
		return KEY;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public String receberXml() {
		URL url;
		BufferedReader bf;
		StringBuffer sb;
		String res = null;
		String linha;
		if (!this.getLatitude().equals(null)
				&& !this.getLongitude().equals(null)) {
			String stringUrl = getURL() + getLatitude() + "," + getLongitude()
					+ getKEY();

			try {
				// instanciando a URL
				url = new URL(stringUrl);
				bf = new BufferedReader(new InputStreamReader(url.openStream()));
				sb = new StringBuffer();

				while ((linha = bf.readLine()) != null) {
					sb.append(linha);
				}
				bf.close();
				res = sb.toString();
				this.setXml(res);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}

	public String PercorrerXml(String xml) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		Document doc = null;
		Element elements;
		NodeList nodes;
		String res = null;
		if (!xml.equals(null)) {
			try {
				db = dbf.newDocumentBuilder();
				InputSource is = new InputSource(new ByteArrayInputStream(xml
						.getBytes()));
				doc = db.parse(is);
				elements = doc.getDocumentElement();
				nodes = elements.getElementsByTagName("address");
				for (int i = 0; i < nodes.getLength(); i++) {
					Element element = (Element) nodes.item(i);
					if (i == 0) {
						res = element.getTextContent();
					}
				}
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}

	public String getBairro(String string) {
		String[] str1 = string.split(",");
		String[] str2 = str1[1].trim().split("-");

		for (String string2 : str2) {
			string2 = string2.trim();						
			String aux = string2.trim();
			
			try { 
				Integer.parseInt(aux);
				continue;
			}catch(Exception exc) {
				// não é uma string				
			}
						
			string2 = string2.replace('à', 'a');
			string2 = string2.replace('è', 'e');
			string2 = string2.replace('ì', 'i');
			string2 = string2.replace('ò', 'o');
			string2 = string2.replace('ù', 'u');
			string2 = string2.replace('á', 'a');
			string2 = string2.replace('é', 'e');
			string2 = string2.replace('í', 'i');
			string2 = string2.replace('ó', 'o');
			string2 = string2.replace('ú', 'u');
			string2 = string2.replace('â', 'a');
			string2 = string2.replace('ê', 'e');
			string2 = string2.replace('î', 'i');
			string2 = string2.replace('ô', 'o');
			string2 = string2.replace('û', 'u');
			string2 = string2.replace('ã', 'a');
			string2 = string2.replace('õ', 'o');
			string2 = string2.replace('ä', 'a');
			string2 = string2.replace('ë', 'e');
			string2 = string2.replace('ï', 'i');
			string2 = string2.replace('ö', 'o');
			string2 = string2.replace('ü', 'u');
			string2 = string2.replace('ç', 'c');
			return string2;
		}
		return null;
	}
}
