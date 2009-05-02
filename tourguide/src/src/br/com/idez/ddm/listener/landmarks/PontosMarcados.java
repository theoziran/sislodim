package br.com.idez.ddm.listener.landmarks;

import java.io.IOException;
import java.util.Vector;

import javax.microedition.location.AddressInfo;
import javax.microedition.location.Coordinates;
import javax.microedition.location.Landmark;
import javax.microedition.location.LandmarkException;
import javax.microedition.location.LandmarkStore;
import javax.microedition.location.LocationException;
import javax.microedition.location.LocationProvider;
import javax.microedition.location.ProximityListener;
import javax.microedition.location.QualifiedCoordinates;

public class PontosMarcados {

	LandmarkStore pontos;

	public PontosMarcados() {
		// TODO Auto-generated constructor stub
		createLandmarkStore();
		// FIXME recupera os dados e cadastra os pontos
		Vector pontosCadastrados = new Vector();
	}

	private void createLandmarkStore() {

		try {
			pontos = LandmarkStore.getInstance("pontosEstrategicos");
		} catch (NullPointerException npe) {
		}

		if (pontos == null) {
			try {
				LandmarkStore.createLandmarkStore("pontosEstrategicos");
			} catch (IllegalArgumentException iae) {
			} catch (IOException e) {
			} catch (LandmarkException e) {
			}

			pontos = LandmarkStore.getInstance("STORE");
		}
	}

	private void createLandmark(String nomeLandmark, String descricaoLandmark,
			Ponto ponto) {

		AddressInfo address = new AddressInfo();
		// FIXME adiciona os campos no AddressInfo

		// FIXME recupera as coordenadas
		QualifiedCoordinates coord = null;
		Landmark lmk = new Landmark(nomeLandmark, descricaoLandmark, coord,
				address);

		try {
			pontos.addLandmark(lmk, "Categoria default");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void sincronizarPontos(Vector pontosCadastrados) {
		// FIXME conecta ao servidor para trazer os dados

	}

	private void adicionarListener(Landmark landmark) {

		createProximityListener(landmark.getQualifiedCoordinates());
	}

	public void createProximityListener(Coordinates coordinates) {
		ProximityListener listener = new PontoProximo();
		try {
			LocationProvider.addProximityListener(listener, coordinates, 25);
		} catch (LocationException e) {
			e.printStackTrace();
		}
	}

}
