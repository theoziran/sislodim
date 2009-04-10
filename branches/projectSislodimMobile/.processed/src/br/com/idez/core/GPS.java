package br.com.idez.core;

import javax.microedition.location.Coordinates;
import javax.microedition.location.Criteria;
import javax.microedition.location.Location;
import javax.microedition.location.LocationException;
import javax.microedition.location.LocationProvider;

import br.com.idez.http.TransmissaoDados;

public class GPS extends Thread {

	private static Criteria criteria = null;
	private LocationProvider provider = null;
	private Location location = null;
	private Coordinates coordinates = null;
	private double latitude;
	private double longitude;

	public GPS() {

	}

	public static Criteria getCriteria() {
		if (GPS.criteria == null) {
			GPS.criteria = new Criteria();
		}
		return GPS.criteria;
	}

	public static void setCriteria(Criteria criteria) {
		GPS.criteria = criteria;
	}

	public LocationProvider getProvider() {
		return provider;
	}

	public void setProvider(LocationProvider provider) {
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

	public void run() {
		try {
			while(true){
				this.sleep(5000);
				localizacaoGPS();
				
			}
		} catch (LocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void localizacaoGPS() throws LocationException,
			InterruptedException, Exception {
		provider = LocationProvider.getInstance(GPS.getCriteria());

		location = provider.getLocation(NORM_PRIORITY);

		coordinates = location.getQualifiedCoordinates();
		if (coordinates == null) {
			throw new Exception("Latitude e Longitude não encontrada..");
		}
		setLatitude(coordinates.getLatitude());
		setLongitude(coordinates.getLongitude());
		if(getLatitude() != 0.0 && getLongitude() != 0.0 ){
			System.out.println(coordinates.getLatitude());
			System.out.println(coordinates.getLongitude());
			TransmissaoDados.getInstance().enviarDados(getLongitude(), getLatitude(),1);
		}
		
	}

}
