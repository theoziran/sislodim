package br.com.idez.ddm.listener.landmarks;

import java.io.IOException;
import java.util.Enumeration;
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

import br.com.idez.ddm.tourguide.PontoEstrategico;

public class PontosMarcados {

	LandmarkStore pontos;

	public PontosMarcados() {
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

			pontos = LandmarkStore.getInstance("pontosEstrategicos");
		}
		existsCategory("categoriaDefault");
		try {
			pontos.addCategory("categoriaDefault");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createLandmark(String nomeLandmark, String descricaoLandmark,
			PontoEstrategico ponto) {

		AddressInfo address = new AddressInfo();
		// FIXME adicionar os campos no AddressInfo
		address.setField(AddressInfo.BUILDING_NAME, ponto.getNome());

		// FIXME recupera as coordenadas
		QualifiedCoordinates coord = new QualifiedCoordinates(ponto.getLatitude(),ponto.getLongitude(),0,20,20);
		Landmark lmk = new Landmark(nomeLandmark, descricaoLandmark, coord,
				address);
		try {
			pontos.addLandmark(lmk, "categoriaDefault");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		adicionarListener(lmk);

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
	
	private boolean existsCategory(String nomeCategoria){
		Enumeration categorias = pontos.getCategories();
		
		  while (categorias.hasMoreElements()){
	           if (nomeCategoria.equals(categorias.nextElement())){
	        	   return true;
	        	   
	           };
	           System.out.println(categorias.nextElement());
	        }  
		
		return false;
	}

}
