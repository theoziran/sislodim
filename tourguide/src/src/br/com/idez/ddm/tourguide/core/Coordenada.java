package br.com.idez.ddm.tourguide.core;

import java.util.Timer;
import java.util.TimerTask;

import javax.microedition.location.Coordinates;
import javax.microedition.location.Criteria;
import javax.microedition.location.Location;
import javax.microedition.location.LocationException;
import javax.microedition.location.LocationProvider;

import br.com.idez.ddm.tourguide.telas.Alerta;


public class Coordenada extends TimerTask {

	private LocationProvider provider;
	private static Criteria criteria = null;
	private Location location = null;
	private Coordinates coordinates;
	private Timer time;
	
	private String latitude;
	private String longitude;
	

	public Coordenada() {
		this.time = new Timer();
		time.schedule(this, 5000, 5000);
	}

	public static Criteria getCriteria() {
		if (Coordenada.criteria == null) {
			Coordenada.criteria = new Criteria();
		}
		return Coordenada.criteria;
	}
	
	public static void setCriteria(Criteria criteria) {
		Coordenada.criteria = criteria;
	}

	public LocationProvider getLocationProvider() {
		return provider;
	}

	public void setLocationProvider(LocationProvider provider) {
		this.provider = provider;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
	
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = Double.toString(latitude);
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = Double.toString(longitude);
	}

	public void pegarCoordenada(){
		try {
			provider = LocationProvider.getInstance(Coordenada.getCriteria());
			location = provider.getLocation(10);
		} catch (LocationException e) {
			try {
				UIController.getInstance().setCurrent(Alerta.getInstance("Erro","Falha na Conexão com os Satélites"));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		coordinates = location.getQualifiedCoordinates();

		setLatitude(coordinates.getLatitude());
		setLongitude(coordinates.getLongitude());
		System.out.print("LA:" + latitude);
		System.out.println(" LO:" + longitude);
		System.out.println();

	}

	public void run() {
		pegarCoordenada();
	}
	
}
