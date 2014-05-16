package com.theonlyanimal.secondstory;

//IMPORTS

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.os.AsyncTask;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import com.theonlyanimal.secondstory.StorageHelper;


// ACTIVITY CLASS

public class WelcomeScreen extends Activity {

	// GLOBALS
	private static final String TAG = "SecondStory";
	private static final String SD_DIRECTORY = "//sdcard//SecondStory";
    public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
    private ProgressDialog progressDialog;
	
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
						
						// How Many Files Are There ?
						File[] listOfFiles = directory.listFiles();
						Log.v(TAG, "Directory has " + listOfFiles.length + " Files");

						// If There's None
						if(listOfFiles.length == 0) {
							// Get 'Em
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
					builder.setTitle("Content Doesn't Exist");
					builder.setMessage("This App Requires Custom Content - We Need To Make A Directory & Download Some Content To It - Ok ?");
					
					// Buttons
					builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					           public void onClick(DialogInterface dialog, int id) {
					               // User clicked OK button
					        	   Log.v(TAG, " - User Said YES! - ");
					        	   
					        	   // Make The Directory
					        	   directory.mkdirs();
					        	   
					        	   // Get The Files
					        	   getFiles();
					        	   
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

	// Show Progress
	@Override
	protected Dialog onCreateDialog(int id) {
		switch(id) {
			case DIALOG_DOWNLOAD_PROGRESS:
				progressDialog = new ProgressDialog(this);
				progressDialog.setMessage("Downloading files...");
				progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				progressDialog.setCancelable(false);
				progressDialog.show();
				
				return progressDialog;
				
			default:
				return null;
		}
	}
	
	
	/*
	 * ASYNC FTP CLASS 
	 */

	class DownloadHelper extends AsyncTask<String, Integer, String> {
		
		private static final String TAG = "FTP";
		private final FTPClient ftp;
		private static final String username = "ghostlight"; 
		private static final String password = "gh0st_l1ght"; 
		private static final String hostname = "ftp.memelab.ca"; 
		private static final int port = 21; 

		
		// Constructor
		DownloadHelper() {
			
			ftp = new FTPClient();
			
		}
		
		private Boolean setupFTP() {
			
			// Connect To Server
			if(connectToFTP()) {
				Log.v(TAG, "Connected");
			}
			else {
				Log.v(TAG, "Couldnt Connect");
				return false;
			}
			
			// Login
			if(loginToFTP()) {
				Log.v(TAG, "Logged In");
			}
			else {
				Log.v(TAG, "Couldnt Login");
				return false;
			}
			
			// Navigate To Correct Path
			if(navigateToPath()) {
				Log.v(TAG, "Navigated");
			}
			else {
				Log.v(TAG, "Couldnt Navigate");
				return false;
			}
			
			// Set To Binary File Type 
			try {
				ftp.setFileType(FTP.BINARY_FILE_TYPE);
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
			
			// Passive Mode For Firewall
			ftp.enterLocalPassiveMode();
			
			// If No Errors... 
			return true;
		}
		
		private Boolean connectToFTP() {
			try {
				ftp.connect(hostname, port);
				return true;
			} catch (SocketException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}
		
		private Boolean loginToFTP() {
			try {
				ftp.login(username, password);
				return true;
			} catch (SocketException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}
		
		private Boolean navigateToPath() {
			try {
				ftp.changeWorkingDirectory(" /public_html/jessescott/storage/android");
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}
		
		private void getFilesFromFTP() {
		     try {
		    	FTPFile[] files = ftp.listFiles();
				int count = 0;
				int totalFiles = files.length;
		    	for (FTPFile f : files) {

					// Log Names
					Log.v("FTP", f.toFormattedString());
					
					// Set Path
					String storagePath = "//sdcard//SecondStory//";
					String fileName = storagePath + f.getName();
				    OutputStream output = new BufferedOutputStream(new FileOutputStream(fileName));

				    // Get Files
	                ftp.retrieveFile(fileName, output);
	                
	                // Update Progress
	                publishProgress((int) ((count / (float) totalFiles) * 100));
	                count++;
	                
	                // Close Buffer
	                output.close();
				 }
		     } 
		     catch (IOException e) {
				e.printStackTrace();
		     }
		     finally {
				 // Logout + Disconnect
				try {
					ftp.logout();
					ftp.disconnect();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
		     }
		}
		
		@SuppressWarnings("deprecation")
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showDialog(DIALOG_DOWNLOAD_PROGRESS);
		}


		@Override
		protected String doInBackground(String... params) {
			try {
				if(setupFTP()) {
					getFilesFromFTP();
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		protected void onProgressUpdate(Integer... progress) {
			 progressDialog.setProgress(progress[0]);
		}

		@SuppressWarnings("deprecation")
		protected void onPostExecute(String unused) {
			dismissDialog(DIALOG_DOWNLOAD_PROGRESS);
		}
		


	} /* EOC */

	
	
	
} /* EOC */