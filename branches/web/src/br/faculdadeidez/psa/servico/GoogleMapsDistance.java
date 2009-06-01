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
/**
 * 
 *Classe responsável por retornar a distância entre dois pontos 
 */
public class GoogleMapsDistance {

	/**
	 * Propriedade que guarda o valor da origem
	 */
	private String origem = null;
	/**
	 * Propriedade que guarda o valor do destino
	 */
	private String destino = null;
	/**
	 * URL do serviço da google
	 */
	private final String URL = "http://maps.google.com/maps?output=kml&&hl=pt-BR&f=d&source=s_d";
	/**
	 * Propriedade que representa o nome da variável passada por GET que guarda o valor da origem
	 */
	private final String SADDR = "&saddr=";
	/**
	 * Propriedade que representa o nome da variável passada por GET que guarda o valor da destino
	 */
	private final String DADDR = "&daddr=";


	/**
	 * Construtor da classe que recebe a origem e o destino a ser calculado
	 * @param origem String
	 * @param destino String
	 */
	public GoogleMapsDistance(String origem, String destino) {
		this.setOrigem(origem);
		this.setDestino(destino);
	}

	/**
	 * Método getter do atributo origem
	 * @return String
	 */
	public String getOrigem() {
		return origem;
	}

	/**
	 * Método setter do atributo origgem
	 * @param origem String
	 */
	public void setOrigem(String origem) {
		this.origem = origem.replace(" ", "+");
	}

	/**
	 * Método getter do atributo destino
	 * @return String
	 */
	public String getDestino() {
		return destino;
	}

	/**
	 * Método setter do atributo destino
	 * @param destino String
	 */
	public void setDestino(String destino) {
		this.destino = destino.replace(" ", "+");
	}

	/**
	 * Método getter do atributo URL
	 * @return String
	 */
	public String getURL() {
		return URL;
	}


	/**
	 * Método que retorna o xml com os dados da distância entre dois pontos
	 * @return String
	 */
	private String getKmlDistance() {
		URL url = null;
		BufferedReader bf;
		StringBuffer sb;
		String res = null;
		String linha;
		try {

			url = new URL(this.getURL() + this.SADDR + this.getOrigem()
					+ this.DADDR + this.getDestino());
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

	/**
	 * Método que trata o xml recebido
	 * @return GoogleMaps
	 */
	public GoogleMaps retornaDistancia() {
        GoogleMaps maps = new GoogleMaps();
        String kml = getKmlDistance();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        Document doc = null;
        Element elements;
        NodeList nodes;
        String res = null;
        try {

                if (!kml.equals(null) && kml.length() > 0) {
                        try {
                                db = dbf.newDocumentBuilder();
                                InputSource is = new InputSource(new ByteArrayInputStream(
                                                kml.getBytes()));
                                doc = db.parse(is);
                                elements = doc.getDocumentElement();
                                nodes = elements.getElementsByTagName("address");
                                for (int j = 0; j < nodes.getLength(); j++) {
                                        Element element1 = (Element) nodes.item(j);
                                        // recolhe o endereço de origem
                                        if (j == 0 && element1.getNodeName() == "address") {
                                                try {
                                                        String[] end = element1.getTextContent().split(
                                                                        ",");
                                                        // percorre pelos node ate chegar na tag
                                                        // coordenadas
                                                        String[] coord = element1.getNextSibling()
                                                                        .getNextSibling().getFirstChild()
                                                                        .getTextContent().split(",");

                                                        maps.setLongitudeOrigem(coord[0].trim());
                                                        maps.setLatitudeOrigem(coord[1].trim());

                                                        maps.setEnderecoCompletoOrigem(end[0].trim());
                                                        maps.setMunicipioOrigem(end[1].trim());
                                                        if (end[2].trim()
                                                                        .matches("^[0-9]{5}-[0-9]{3}$")) {
                                                                maps.setCepOrigem(end[2].trim());
                                                        } else if (end[2].trim().equals("Brasil")) {
                                                                maps.setPaisOrigem(end[2].trim());
                                                        }

                                                        if (end[3].trim().equals("Brasil")) {
                                                                maps.setPaisOrigem(end[3].trim());
                                                        }
                                                } catch (Exception e) {

                                                }
                                        }

                                        if (j == 1 && element1.getNodeName() == "address") {
                                                try {

                                                        String[] end = element1.getTextContent().split(
                                                                        ",");
                                                        // percorre pelos node ate chegar na tag
                                                        // coordenadas
                                                        String[] coord = element1.getNextSibling()
                                                                        .getNextSibling().getFirstChild()
                                                                        .getTextContent().split(",");

                                                        maps.setLongitudeDestino(coord[0].trim());
                                                        maps.setLatitudeDestino(coord[1].trim());

                                                        maps.setEnderecoCompletoDestino(end[0].trim());
                                                        maps.setMunicipioDestino(end[1].trim());

                                                        if (end[2].trim()
                                                                        .matches("^[0-9]{5}-[0-9]{3}$")) {
                                                                maps.setCepDestino(end[2].trim());
                                                        } else if (end[2].trim().equals("Brasil")) {
                                                                maps.setPaisDestino(end[2].trim());
                                                        } else {
                                                                maps.setPaisDestino(end[3].trim());
                                                        }

                                                        if (end[3].trim().equals("Brasil")) {
                                                                maps.setPaisOrigem(end[3].trim());
                                                        }
                                                } catch (Exception e) {

                                                }
                                        }
                                }

                                nodes = elements.getElementsByTagName("description");
                                for (int i = 0; i < nodes.getLength(); i++) {
                                        Element element2 = (Element) nodes.item(i);
                                        res = (String) element2.getTextContent();
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
                                res = res.replaceAll("&#160;", " ");
          
                                res = res.replace(')', ' ');
                                res = res.trim();
                                
                                String[] split = res.split(" ");
                                
                                maps.setDistancia(split[0].replace(',', '.') + " " + split[1]);
                                
                                try {
                                	int divisor = 1;
                                	
                                	if(split[1].toLowerCase().equals("km")) { 
                                		divisor = 1;
                                	} else if(split[1].toLowerCase().equals("m")) { 
                                		divisor = 1000;
                                	}            	
                                	
                                	maps.setDistanciaReal(Double.parseDouble(split[0].replace(',', '.')) / divisor);
                                }
                                catch(Exception exc) { 
                                	maps.setDistanciaReal(0.0);
                                }
                                
                                maps.setTempo(split[3] + " " + split[4]);

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
        } catch (Exception e) {
                // TODO: handle exception
        }
        return maps;
}


	
}
