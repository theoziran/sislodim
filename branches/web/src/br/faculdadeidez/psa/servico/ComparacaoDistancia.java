package br.faculdadeidez.psa.servico;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;

public class ComparacaoDistancia {
	Source source = null;
	String origem = null;
	String destino = null;
	StringBuilder url = new StringBuilder(
			"http://maps.google.com/maps?output=html&hl=pt-BR&f=d&source=s_d&saddr=");

	public ComparacaoDistancia() {

	}

	public ComparacaoDistancia(String origem, String destino) {
		this.origem = origem;
		this.destino = destino;
	}

	@SuppressWarnings("deprecation")
	public String getDistancia() throws ComparacaoDistanciaException {
		if (validar() == null) {
			try {
				source = new Source(new URL(this.getURL()));
			} catch (MalformedURLException e) {
				// "003- Problemas na URL";
				throw new ComparacaoDistanciaException(3);
			} catch (IOException e) {
				// "004- Problemas Entrada e Saída";
				throw new ComparacaoDistanciaException(4);
			} catch (Exception e) {
				// Who knows?
				// "005- Problema não identificado";
				throw new ComparacaoDistanciaException(5);
			}
			try {
				Element passo1 = source.findAllElements("div class=dditd id=dditd").get(0);
				Element passo2 = passo1.findAllElements("div").get(0);
				Element passo3 = passo2.findAllElements("b").get(0);

				String endereco = passo3.toString();

				endereco = endereco.replace("&#160;", " ");
				endereco = endereco.replace("<b>", "");
				endereco = endereco.replace("</b>", "");

				return endereco;

			} catch (Exception e) {
				// "006- Endereço não foi encontrado, ou serviço pode ter sido modificado, verifique o source";
				throw new ComparacaoDistanciaException(6);
			}
		} else
			return validar();
	}

	@SuppressWarnings("deprecation")
	private String getURL() {
		this.url.append("&saddr=");
		this.url.append(URLEncoder.encode(getOrigem()));
		this.url.append("&daddr=");
		this.url.append(URLEncoder.encode(getDestino()));
		this.url.append("&btnG=Como+chegar");
		return url.toString();
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	private String validar() throws ComparacaoDistanciaException {
		if (getOrigem() == null) {
			// "001- O campo origem é obrigatório";
			throw new ComparacaoDistanciaException(1);
		} else if (getDestino() == null) {
			// "002- O campo destino é obrigatório";
			throw new ComparacaoDistanciaException(2);
		} else {
			return null;
		}
	}

}