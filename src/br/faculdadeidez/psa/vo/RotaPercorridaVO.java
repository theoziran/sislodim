package br.faculdadeidez.psa.vo;

import java.util.Date;

import br.faculdadeidez.psa.util.FormataDatas;

public class RotaPercorridaVO {

	private boolean eSetor;
	private String bairro;
	private ViaturaVO viatura;
	private Date data;
	private String dataFormatada;
	private String horario;
	private FormataDatas fd = new FormataDatas();
	
	public String getBairro() {
		return bairro;
	}


	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public ViaturaVO getViatura() {
		return viatura;
	}

	public void setViatura(ViaturaVO viatura) {
		this.viatura = viatura;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public boolean isESetor() {
		return eSetor;
	}


	public void setESetor(boolean setor) {
		eSetor = setor;
	}

	public String getDataFormatada() {
		return (fd.getDia(data)+"/"+fd.getMes(data));
	}

	public String getHorario() {
		return fd.getHorario(data);
	}
	
}
