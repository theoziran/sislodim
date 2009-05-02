package br.com.idez.ddm.listener.landmarks;

import javax.microedition.location.Coordinates;
import javax.microedition.location.Location;
import javax.microedition.location.LocationListener;
import javax.microedition.location.LocationProvider;
import javax.microedition.location.ProximityListener;

public class PontoProximo implements ProximityListener, LocationListener {

	public void monitoringStateChanged(boolean arg0) {
		// TODO Auto-generated method stub

	}

	public void proximityEvent(Coordinates coordenadas, Location location) {
		// TODO Executa o método quando se aproxima da localização
	}

	public void locationUpdated(LocationProvider LcProvider, Location location) {
		// TODO Auto-generated method stub
		
	}

	public void providerStateChanged(LocationProvider arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

}
