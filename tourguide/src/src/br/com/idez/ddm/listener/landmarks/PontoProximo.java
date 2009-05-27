package br.com.idez.ddm.listener.landmarks;

import java.io.IOException;
import java.util.Enumeration;

import javax.microedition.location.Coordinates;
import javax.microedition.location.Landmark;
import javax.microedition.location.LandmarkStore;
import javax.microedition.location.Location;
import javax.microedition.location.LocationListener;
import javax.microedition.location.LocationProvider;
import javax.microedition.location.ProximityListener;

import br.com.idez.ddm.tourguide.telas.ExibicaoPonto;

public class PontoProximo implements ProximityListener, LocationListener {

	public void monitoringStateChanged(boolean arg0) {

	}

	public void proximityEvent(Coordinates coordenadas, Location location) {
		// TODO Executar o método quando se aproxima da localização
		Landmark lm = findNearestLandMark(coordenadas);
		System.out.println("Aviso: Você está próximo ao landmark "+lm.getDescription()+" com as coordenadas "+ coordenadas.getLatitude()+coordenadas.getLongitude());
		System.out.println("Landmark "+lm.getDescription()+" com as coordenadas "+ lm.getQualifiedCoordinates().getLatitude()+lm.getQualifiedCoordinates().getLongitude()+" está a "+lm.getQualifiedCoordinates().distance(coordenadas));
		ExibicaoPonto.getInstance().exibirPonto(lm.getName());
	}

	public void locationUpdated(LocationProvider LcProvider, Location location) {
		
	}

	public void providerStateChanged(LocationProvider arg0, int arg1) {
		
	}
	
	 public Landmark findNearestLandMark(Coordinates coord)
	    {
	        Landmark landmark = null;

	        double latRadius = 0.1;
	        double lonRadius = 0.1;

	        float dist = 25;
	        LandmarkStore store = LandmarkStore.getInstance("pontosEstrategicos");
	       

	        try
	        {
	            // Generate enumeration of Landmarks that are close to coord.
	            Enumeration enu = store.getLandmarks(null, coord.getLatitude()
	                    - latRadius, coord.getLatitude() + latRadius, coord
	                    .getLongitude() - lonRadius, coord.getLongitude() 
	                    + lonRadius);
	            float tmpDist;
	            Landmark tmpLandmark = null;

	            while (enu.hasMoreElements())
	            {
	                tmpLandmark = (Landmark) enu.nextElement();
	                tmpDist = tmpLandmark.getQualifiedCoordinates().distance(coord);

	                if (tmpDist < dist)
	                {
	                    landmark = tmpLandmark;
	                }
	            }
	        }
	        catch (IOException ioe)
	        {
	            // I/O error happened when accessing the landmark store.
	            return null;
	        }

	        return landmark;
	    }

}
