package br.com.idez.ddm.listener.landmarks;

import javax.microedition.location.Coordinates;
import javax.microedition.location.Location;
import javax.microedition.location.LocationListener;
import javax.microedition.location.LocationProvider;
import javax.microedition.location.ProximityListener;

public class PontoProximo implements ProximityListener, LocationListener {

	public void monitoringStateChanged(boolean arg0) {

	}

	public void proximityEvent(Coordinates coordenadas, Location location) {
		// TODO Executar o método quando se aproxima da localização
		System.out.println("ta proximo veih");
	}

	public void locationUpdated(LocationProvider LcProvider, Location location) {
		
	}

	public void providerStateChanged(LocationProvider arg0, int arg1) {
		
	}

}
