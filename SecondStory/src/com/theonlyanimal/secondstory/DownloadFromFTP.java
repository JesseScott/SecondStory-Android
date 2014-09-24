package com.theonlyanimal.secondstory;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import android.text.format.Time;
import android.util.Log;

public class DownloadFromFTP {
	
	private static final String MEDIA_DIRECTORY = "//sdcard//SecondStory/BloodAlley/MEDIA/";
	private static final String LOG_DIRECTORY = "//sdcard//SecondStory/BloodAlley/LOGS/";
	
	public boolean ftpDownload(String srcFilePath,String desFileName) {
		
		boolean status = false;
		final String username = "ghostlight"; 
		final String password = "gh0st_l1ght"; 
		final String hostname = "ftp.memelab.ca"; 
		final int port = 21; 
		
		String SFTPWORKINGDIR = "/public_html/jessescott/storage/android";
		
		FTPClient ftp = new FTPClient();
		try {
			ftp.connect(hostname, port);
			ftp.login(username, password);
			
			ftp.changeWorkingDirectory(SFTPWORKINGDIR);
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			ftp.setBufferSize(1024);
			ftp.enterLocalPassiveMode();
			
			OutputStream output = null;
			FTPFile[] files = ftp.listFiles();
			
			// Set Log Directory & File
			File logFile = new File(LOG_DIRECTORY + "logfile.txt");
			Log.v("FTP", "LOG should exist at " + logFile.getAbsolutePath());
			if(!logFile.exists()) {
				try {
					logFile.createNewFile();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			for (FTPFile f : files) {
				// Log Names
				Log.v("FTP", f.toFormattedString());
				
				// Set Path
				String remoteFile = f.getName();
				Log.v("FTP", "REMOTE file " + remoteFile);
				String localFile = MEDIA_DIRECTORY;
				localFile += remoteFile;
				Log.v("FTP", " is being put in LOCAL path " + localFile);
			    output = new BufferedOutputStream(new FileOutputStream(localFile));
			    
				// Set Time
			    Time now = new Time(Time.getCurrentTimezone());
			    now.setToNow();
				
				// Set Logger
			    BufferedWriter logger = new BufferedWriter(new FileWriter(logFile, true)); 
			    logger.append("Download for " + localFile + " started at " + now.format("%k:%M:%S"));
			    logger.newLine();
			    Long startTime = System.currentTimeMillis();
			    
			    // HTTP
			    System.setProperty("http.keepAlive", "false");

			    // Get Files
                Boolean success = ftp.retrieveFile(remoteFile, output);
                status = success;
                if(success) {
                	Log.v("FTP", "SUCCESS");
                	now.setToNow();
                	Long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
				    logger.append("Download for " + localFile + " finished at " + now.format("%k:%M:%S"));
				    logger.newLine();
				    logger.append("for an elapsedTime of " + elapsedTime + " seconds");
				    logger.newLine();
				    logger.newLine();
				    
                }
                
                // Close Logger
                logger.flush();
                logger.close();
                
                // Close Buffer 
                if(ftp != null) {
					output.close();
				}
			 }

			ftp.logout();
			ftp.disconnect();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}


}
