package br.faculdadeidez.psa.vo;


public class RotaPercorridaVO {

	private boolean eSetor;
	private String bairro;
	private ViaturaVO viatura;
	private String data;
	private String horario;
	
	
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

	public void setData(String string) {
		this.data = string;
	}

	public boolean isESetor() {
		return eSetor;
	}


	public void setESetor(boolean setor) {
		eSetor = setor;
	}

	public String getHorario() {
		return horario;
	}
	
	public String getCodViatura(){
		return viatura.getCodigo();
	}

	public String getData() {
		return data;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

}
