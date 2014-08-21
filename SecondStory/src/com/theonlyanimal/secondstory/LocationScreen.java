package com.theonlyanimal.secondstory;

import com.theonlyanimal.secondstory.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.content.Context;

// Location-Specific
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationListener;
import android.location.GpsStatus.Listener;
import android.location.GpsStatus.NmeaListener;

// Screen-Specific
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;


public class LocationScreen extends Activity {
	
	// Declarations
	LocationManager locationManager;
	MyLocationListener locationListener;
    Paint paint = new Paint();
	
	// Variables
	float currentLatitude  = 0;
	float currentLongitude = 0;
	float currentAltitude = 0;
	float currentAccuracy  = 0;
	float currentSpeed = 0;
	float currentBearing = 0;
	String currentProvider = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.location_layout);
		locationListener = new MyLocationListener(this);
		setContentView(locationListener);
	}

	
	@Override
	protected void onResume() {
		super.onResume();
		// Acquire a reference to the system Location Manager
		locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		// Register the listener with the Location Manager to receive location updates
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
	}
	
	@Override 
	protected void onPause() {
		super.onPause();
	}
	
	
 
	
	// Class for capturing the GPS data
	class MyLocationListener extends View implements LocationListener {

	  public MyLocationListener(Context context) {
			super(context);
	  }

	  // Define all LocationListener methods
	  public void onLocationChanged(Location location) {
	    // Save new GPS data
	    currentLatitude  = (float)location.getLatitude();
	    currentLongitude = (float)location.getLongitude();
	    currentAltitude = (float)location.getAltitude();
	    currentAccuracy  = (float)location.getAccuracy();
		currentSpeed = (float)location.getSpeed();
		currentBearing = (float)location.getBearing();
	    currentProvider  = location.getProvider();
	  }

	  public void onProviderDisabled (String provider) { 
	    currentProvider = "";
	  }

	  public void onProviderEnabled (String provider) { 
	    currentProvider = provider;
	  }

	  public void onStatusChanged (String provider, int status, Bundle extras) {
	    // Nothing yet...
	  }
	  
	  protected void onDraw(Canvas canvas) {
   		paint.setColor(Color.BLUE);
   		paint.setTextSize(36);
   		canvas.drawText(String.valueOf("OUR LAT IS: " + currentLatitude),  100, 100, paint);
   		canvas.drawText(String.valueOf("OUR LON IS: " + currentLongitude), 100, 200, paint);
   		canvas.drawText(String.valueOf("OUR ALT IS: " + currentAltitude),  100, 300, paint);
   		canvas.drawText(String.valueOf("OUR ACC IS: " + currentAccuracy),  100, 400, paint);
   		canvas.drawText(String.valueOf("OUR SPE IS: " + currentSpeed),     100, 500, paint);
   		canvas.drawText(String.valueOf("OUR BEA IS: " + currentBearing),   100, 600, paint);
   		canvas.drawText(String.valueOf("OUR PRO IS: " + currentProvider),  100, 700, paint);
        invalidate();
	  }
	  
	}
	

}
