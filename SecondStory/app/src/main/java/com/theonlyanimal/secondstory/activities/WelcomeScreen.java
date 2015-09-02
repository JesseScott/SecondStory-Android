package com.theonlyanimal.secondstory.activities;

//IMPORTS

import java.io.File;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;
import android.preference.PreferenceManager;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;

import com.theonlyanimal.secondstory.helpers.Constants;
import com.theonlyanimal.secondstory.helpers.StorageHelper;
import com.theonlyanimal.secondstory.ftp.FTPService;
import com.theonlyanimal.secondstory.R;


// ACTIVITY CLASS

public class WelcomeScreen extends Activity {

	// GLOBALS
	private static final String TAG = "SS_WELCOME";
	private static final String APP_ID = "7f9de1a2655e56f6c60c798cc7d2cdec";
	private static final String SSID = "PHStheatre";
	private static final String PWD = "2ndst0ry";
	private static final String SD_DIRECTORY = "//sdcard//SecondStory/BloodAlley";
	private static final String MEDIA_DIRECTORY = "//sdcard//SecondStory/BloodAlley/MEDIA/";
	private static final String LOG_DIRECTORY = "//sdcard//SecondStory/BloodAlley/LOGS/";
    public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
    
    private ProgressDialog progress;
    StorageHelper storage;
    private double 	needsThisMuchSpace = 0.5;
    
    boolean manuallyPreppedDevice;
	
	// LifeCycle
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// UI
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome_layout);
		
		// Progress Dialog
        progress = ProgressDialog.show(this, "Checking Settings", "", true);
        
        // SD
        storage = new StorageHelper();
        
        // MANUAL
        Constants.downloadedAllVideos = true;
        manuallyPreppedDevice = true;

        
        // Delay Check
		Thread timer = new Thread(){
			@Override
			public void run() {
				//super.run();
				try { 
					sleep(500);	
					progress.dismiss();
				}
				catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		timer.start();
		
		if(checkForSD()) {
			if(checkForContent()) {
				setContentPrefs(true, false);
				progress.dismiss();
				moveOn();
			}
			else {
				progress.dismiss();
				showStreamOrDownloadDialog();
			}
		}
		else {
			Toast.makeText(getApplicationContext(), "SD Card isn't available. Unable to cache/download content. Will stream for now.", Toast.LENGTH_LONG).show();
			setContentPrefs(false, true);
			progress.dismiss();
			moveOn();
		}

		
	} /* onCreate() */
	
	
	@Override
	protected void onResume() {
		super.onResume();		
	}
	
	
	private void moveOn() {
		Intent intent = new Intent(WelcomeScreen.this, MenuScreen.class);
		startActivity(intent);
		finish();
	}
	
	
	private void setContentPrefs(boolean has, boolean will) {
		if(has) {
			Constants.downloadedAllVideos = true;
		}
		else {
			Constants.downloadedAllVideos = false;
		}
		if(will) {
			SharedPreferences prefs = getSharedPreferences("com.theonlyanimal.secondstory", Context.MODE_PRIVATE);
	     	prefs.edit().putBoolean("com.theonlyanimal.secondstory.stream", true).apply();
		}
		else {
			SharedPreferences prefs = getSharedPreferences("com.theonlyanimal.secondstory", Context.MODE_PRIVATE);
	     	prefs.edit().putBoolean("com.theonlyanimal.secondstory.stream", false).apply();
		}
	}

	
	// WiFi
	public void connectToWifi() {
		// Configuration
		WifiConfiguration config = new WifiConfiguration();
		config.SSID = "\"" + SSID + "\"";
		config.preSharedKey = "\"" + PWD + "\""; // WPA2
		
		// Manager
		WifiManager manager = (WifiManager)this.getSystemService(Context.WIFI_SERVICE);
		
		if(manager.isWifiEnabled() == false) {
			manager.setWifiEnabled(true);
			Toast.makeText(this, "Turning On WiFi", Toast.LENGTH_SHORT).show();
		}

		int networkID = -1;
		networkID = manager.addNetwork(config);		
		if(networkID < 0) {
			Toast.makeText(this, "Couldn't Connect To SecondStory Network", Toast.LENGTH_SHORT).show();
		}
		else {
			Toast.makeText(this, "Added SecondStory Network To Saved Networks", Toast.LENGTH_SHORT).show();
		}
		
		manager.enableNetwork(networkID, true);
		manager.saveConfiguration();
		manager.reconnect();
		
		Toast.makeText(this, "Connecting To SecondStory Network", Toast.LENGTH_SHORT).show();
	}
	
	
	public void askForWifi() {
		// Alert
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		// Title
		builder.setTitle("About To Download Content");
		builder.setMessage("This app requires 500mb of content - can we turn on your WiFi and connect to our network?");
		builder.setCancelable(false);
		// Buttons
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		               // User clicked OK button
		        	   Log.v(TAG, " - User Said YES! - ");
		        	   Toast.makeText(getApplicationContext(), "Thanks!", Toast.LENGTH_SHORT).show();
		        	   connectToWifi();
		           }
		       });
		builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	   Toast.makeText(getApplicationContext(), "Ok.... We Warned You.", Toast.LENGTH_SHORT).show();
		           }
		       });

		// Show
		AlertDialog dialog = builder.create();
		dialog.show();
	}
	
    

	private void showStreamOrDownloadDialog(){
		// Alert
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		// Title
		builder.setTitle("Content Doesn't Exist");
		builder.setMessage("this app requires custom content - you can download it to your device or stream it.");
		builder.setCancelable(false);
		
		// Buttons
		builder.setPositiveButton("Download", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	   getFiles();
		        	   setContentPrefs(false, false);
		           }
		       });
		builder.setNegativeButton("Stream", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	   setContentPrefs(false, true);
			           moveOn();
		           }
		       });

		// Show
		AlertDialog dialog = builder.create();
		dialog.show();
	}
	

	private boolean checkForSD() {
		if(storage.isExternalStorageAvailableAndWriteable()) {
			return true;
		}
		else {
			return false;
		}
	}
	

	private boolean checkForSpace() {
		if(storage.getSpaceOnExternalStorage() > needsThisMuchSpace) {
			return true;
		}
		else {
			return false;
		}
	}
		

	public boolean checkForContent() {
		try{
			final File base_directory = new File(SD_DIRECTORY);
			final File media_directory = new File(MEDIA_DIRECTORY);
			final File log_directory = new File(LOG_DIRECTORY);
			if(media_directory.exists()) {
				if(media_directory.isDirectory()) {
					File[] listOfFiles = media_directory.listFiles();
					if(listOfFiles.length < 2) {
						return false;
					}
					else {
						return true;
					}
				} 
				else {
					base_directory.mkdirs();
					media_directory.mkdirs();
					log_directory.mkdirs();
					return false;
				}
			} 
			else {
				base_directory.mkdirs();
				media_directory.mkdirs();
				log_directory.mkdirs();
				return false;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	// Download Files
	public void getFiles() {
		if(checkForSpace()) {
			Toast.makeText(getApplicationContext(), "Downloading Files", Toast.LENGTH_SHORT).show();
			Intent i= new Intent(WelcomeScreen.this,FTPService.class);
			startService(i);
			moveOn();
		}
		else {
			Toast.makeText(getApplicationContext(), "Not Enough Space On Your Device. Please Make 500mb Available.", Toast.LENGTH_LONG).show();
		}
	}
	
} /* EOC */