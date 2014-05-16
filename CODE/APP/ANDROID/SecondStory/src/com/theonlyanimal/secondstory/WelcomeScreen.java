package com.theonlyanimal.secondstory;

//IMPORTS

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;

import java.io.File;

import com.theonlyanimal.secondstory.StorageHelper;
import com.theonlyanimal.secondstory.DownloadHelper;

//CLASS
public class WelcomeScreen extends Activity {

	// GLOBALS
	private static final String TAG = "SecondStory";
	private static final String SD_DIRECTORY = "//sdcard//SecondStory";
	
	// LifeCycle
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome_layout);
		
		// Do We Have Content ?
		checkStorage();

		
	} /* onCreate() */
	
	// Check If Directory Exists
	public void checkStorage() {
		
		// Variables
		
		StorageHelper storage = new StorageHelper();
		
		// Check For SD Card
		if(storage.isExternalStorageAvailableAndWriteable()) {
			Log.v(TAG, " - SD EXISTS - CHECKING FOR CONTENT - ");

			// Check For Custom Directory
			try{

				final File directory = new File(SD_DIRECTORY);
				
				if(directory.exists()) {
					Log.v(TAG, " - Path Exists - ");
					if(directory.isDirectory()) {
						Log.v(TAG, " - And Its A Directory - ");
						
						// 
						File[] listOfFiles = directory.listFiles();
						Log.v(TAG, "Directory has " + listOfFiles.length + " Files");

						if(listOfFiles.length == 0) {
							getFiles();
						}
						
					} 
					else {
						Log.v(TAG, " - But Its Not Directory - ");
					}
				} 
				else {
					Log.v(TAG, " - Path Doesnt Exist - ");
					
					// Alert
					AlertDialog.Builder builder = new AlertDialog.Builder(this);
					
					// Title
					builder.setTitle("Folder Doesn't Exist");
					builder.setMessage("This App Requires A Custom Folder To Store Content - Can We Make One ?");
					
					// Buttons
					builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					           public void onClick(DialogInterface dialog, int id) {
					               // User clicked OK button
					        	   Log.v(TAG, " - User Said YES! - ");
					        	   directory.mkdirs();
					           }
					       });
					builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
					           public void onClick(DialogInterface dialog, int id) {
					               // User cancelled the dialog - WERE GOING HOME
					        	   Log.v(TAG, " - User Said No :( - ");
					        	   Intent intent = new Intent(Intent.ACTION_MAIN);
					        	   intent.addCategory(Intent.CATEGORY_HOME);
					        	   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					        	   startActivity(intent);
					        	   finish();
					           }
					       });

					// Show
					AlertDialog dialog = builder.create();
					dialog.show();
					
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}


		}
		else {
			Log.v(TAG, " - SD ISNT AVAILABLE - ALERTING USER - ");
		}
			
	} /* checkStorage() */
	
	// Download Files
	public void getFiles() {
		Log.v(TAG, " - getFiles() - ");
		DownloadHelper downloadHelper = new DownloadHelper(); 
		downloadHelper.execute();
		
		
	}

} /* WelcomeScreen */