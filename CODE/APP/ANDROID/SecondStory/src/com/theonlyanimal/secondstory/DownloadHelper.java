package com.theonlyanimal.secondstory;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;



/**
 * 
 * Checks A Directory And Downloads Contents To The External Storage Of The Device.
 * 
 */

public class DownloadHelper extends AsyncTask {
	
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
	
	public Boolean setupFTP() {
		
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
	
	public void getFilesFromFTP() {
	     try {
	    	FTPFile[] files = ftp.listFiles();
			for (FTPFile f : files) {
				//Log.v("FTP", f.getRawListing());
				Log.v("FTP", f.toFormattedString());
				
				String storagePath = "//sdcard//SecondStory//";
				String fileName = storagePath + f.getName();
				
			    OutputStream output = new BufferedOutputStream(new FileOutputStream(fileName));

                ftp.retrieveFile(fileName, output);

                output.close();
				
			 }

	     } 
	     catch (IOException e) {
			e.printStackTrace();
	     }
	     finally {
			 // Logout
			try {
				ftp.logout();
				ftp.disconnect();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
	     }
	}

	@Override
	protected Boolean doInBackground(Object... params) {
		try {
			
			if(setupFTP()) {
				getFilesFromFTP();
			}
			
			
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

	
	

} /* EOC */
