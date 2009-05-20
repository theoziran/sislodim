package br.com.idez.core.xmlparser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Vector;

import org.kxml2.io.KXmlParser;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import br.com.idez.vo.CNHVO;
import br.com.idez.vo.OcorrenciaVO;
import br.com.idez.vo.PessoaVO;
import br.com.idez.vo.VeiculoVO;

public class ConsultaParser {
	private PessoaVO pessoa;
	private VeiculoVO veiculo;
	private Vector lstOcorrencias;

	public PessoaVO getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaVO pessoa) {
		this.pessoa = pessoa;
	}

	public VeiculoVO getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(VeiculoVO veiculo) {
		this.veiculo = veiculo;
	}

	public Vector getLstOcorrencias() {
		return lstOcorrencias;
	}

	public void setLstOcorrencias(Vector lstOcorrencias) {
		this.lstOcorrencias = lstOcorrencias;
	}

	/**
	 * Processa a consulta no servidor e preenche os objetos necessários
	 * 
	 * @param xml
	 */
	public ConsultaParser(String xml) {
		processaXMLRecebido(xml);
	}

	private void processaXMLRecebido(String xml) {
		// constroi o kxml parser
		XmlPullParser parser = new KXmlParser();
		try {
			System.out.println("Começando a preencher..");

			// configura o parser para ler do arquivo xml
			parser.setInput(getInputStream(xml));

			// raiz -> consulta
			parser.nextTag();
			parser.require(XmlPullParser.START_TAG, null, "consulta");

			// início -> pessoa
			parser.nextTag();
			parser.require(XmlPullParser.START_TAG, null, "pessoa");

			pessoa = new PessoaVO();

			parser.nextTag();
			parser.require(XmlPullParser.START_TAG, null, "nome");
			pessoa.setNome(parser.nextText());

			parser.nextTag();

			if (parser.getName().equals("cpf")) {
				parser.require(XmlPullParser.START_TAG, null, "cpf");
				pessoa.setCpf(parser.nextText());

				parser.nextTag();
			}

			if (parser.getName().equals("rg")) {
				parser.require(XmlPullParser.START_TAG, null, "rg");
				pessoa.setRg(parser.nextText());
				parser.nextTag();
			}

			if (parser.getName().equals("orgaoExped")) {
				parser.require(XmlPullParser.START_TAG, null, "orgaoExped");
				pessoa.setOrgaoExped(parser.nextText());
				parser.nextTag();
			}

			if (parser.getName().equals("dataNasc")) {
				parser.require(XmlPullParser.START_TAG, null, "dataNasc");
				pessoa.setDataNasc(parser.nextText());
				parser.nextTag();
			}

			if (parser.getName().equals("naturalidade")) {
				parser.require(XmlPullParser.START_TAG, null, "naturalidade");
				pessoa.setNaturalidade(parser.nextText());
				parser.nextTag();
			}

			if (parser.getName().equals("nomePai")) {
				parser.require(XmlPullParser.START_TAG, null, "nomePai");
				pessoa.setNomePai(parser.nextText());
				parser.nextTag();
			}

			if (parser.getName().equals("nomeMae")) {
				parser.require(XmlPullParser.START_TAG, null, "nomeMae");
				pessoa.setNomeMae(parser.nextText());
				parser.nextTag();
			}

			// início -> CNH da pessoa
			if (parser.getName().equals("cnh")) {
				parser.require(XmlPullParser.START_TAG, null, "cnh");

				CNHVO cnh = new CNHVO();

				parser.nextTag();
				parser.require(XmlPullParser.START_TAG, null, "codigo");
				cnh.setCodigo(parser.nextText());

				parser.nextTag();
				parser.require(XmlPullParser.START_TAG, null, "dataValidade");
				cnh.setDataValidade(parser.nextText());

				parser.nextTag();
				parser.require(XmlPullParser.START_TAG, null, "observacoes");
				cnh.setObservacoes(parser.nextText());

				parser.nextTag();
				parser.require(XmlPullParser.END_TAG, null, "cnh");

				pessoa.setCnh(cnh);

				parser.nextTag();
			}			
			// fim <- CNH da pessoa

			parser.require(XmlPullParser.END_TAG, null, "pessoa");
			parser.nextTag();
			// fim <- pessoa
			
			// início -> veículo
			if (parser.getName().equals("veiculo")) {
				System.out.println("Encontrei o veículo...");
				
				parser.require(XmlPullParser.START_TAG, null, "veiculo");
				
				veiculo = new VeiculoVO();

				parser.nextTag();
				parser.require(XmlPullParser.START_TAG, null, "placa");
				veiculo.setPlaca(parser.nextText());

				parser.nextTag();
				parser.require(XmlPullParser.START_TAG, null, "ano");
				veiculo.setAno(Integer.parseInt(parser.nextText()));

				parser.nextTag();
				parser.require(XmlPullParser.START_TAG, null, "modelo");
				veiculo.setModelo(parser.nextText());

				parser.nextTag();
				parser.require(XmlPullParser.START_TAG, null, "anoFabricacao");
				veiculo.setAnoFrabricacao(Integer.parseInt(parser.nextText()));

				parser.nextTag();
				parser.require(XmlPullParser.START_TAG, null, "informacoes");
				veiculo.setInformacoes(parser.nextText());

				parser.nextTag();
				parser.require(XmlPullParser.END_TAG, null, "veiculo");

				parser.nextTag();
			}
			// fim <- veículo

			// início -> ocorrências
			if (parser.getName().equals("ocorrencias")) {
				System.out.println("Encontrei ocorrências...");
				
				parser.require(XmlPullParser.START_TAG, null, "ocorrencias");

				lstOcorrencias = new Vector();

				// percorre a lista de ocorrências
				while (parser.nextTag() != XmlPullParser.END_TAG) {
					parser.require(XmlPullParser.START_TAG, null, null);

					String name = parser.getName();
					String text = parser.nextText();

					System.out.println(name + " -> " + text);

					// preenche a ocorrência na lista
					OcorrenciaVO oc = new OcorrenciaVO();
					oc.setOcorrencia(text);
					lstOcorrencias.addElement(oc);

					parser.require(XmlPullParser.END_TAG, null, name);
				}
				
				parser.require(XmlPullParser.END_TAG, null, "ocorrencias");
				
				parser.nextTag();
			}
			// fim <- ocorrências

			parser.require(XmlPullParser.END_TAG, null, "consulta");

			System.out.println("Terminei de preencher");
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Reader getInputStream(String xml) throws IOException {
		byte[] xmlByteArray = xml.getBytes();
		ByteArrayInputStream xmlStream = new ByteArrayInputStream(xmlByteArray);
		return new InputStreamReader(xmlStream);
	}
}
