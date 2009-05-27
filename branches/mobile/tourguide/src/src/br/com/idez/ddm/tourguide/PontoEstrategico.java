package br.com.idez.ddm.tourguide;

import java.util.Vector;

import br.com.idez.ddm.tourguide.core.Record;

public class PontoEstrategico {
	private int id;
	private String nome;
	private double latitude;
	private double longitude;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (nome.length() > 25) {
			nome = nome.substring(0, 24);
		}
		this.nome = nome;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("ID: " + id + "|");
		sb.append("Nome: " + nome + "|");
		sb.append("Latitude: " + latitude + "|");
		sb.append("Longitude: " + longitude + "|");

		return sb.toString();

	}

	public static boolean addPonto(PontoEstrategico ponto) {
		if (buscaPonto(ponto) == null) {
			Record.addPontoEstrategico(ponto);
			System.out.println("inserindo ponto: " + ponto.getId());
			return true;
		} else {
			// TODO atualizar ponto
			System.out.println("Ponto " + ponto.getId()
					+ " já existe e será atualizado!");
		}
		return false;
	}

	private static PontoEstrategico buscaPonto(PontoEstrategico ponto) {
		return Record.getPontoEstrategico(ponto.getId());
	}

	public static Vector getAllPontos() {
		return Record.getAllPontos();
	}
}
