package br.faculdadeidez.psa.vo;

import java.util.Date;

public class RotaPercorridaVO {

	private String bairro;
	private ViaturaVO viatura;
	private Date data;

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

}
