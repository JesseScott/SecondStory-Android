package com.theonlyanimal.secondstory;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

public class FTPService extends Service {
	
	private MyThread mythread;
	public boolean isRunning = false;
	long interval = 3000;
	
	// Defines a custom Intent action
    public static final String BROADCAST_ACTION = "com.theonlyanimal.secondstory.FTPService.BROADCAST";
    public static final String EXTENDED_DATA_STATUS = "com.theonlyanimal.secondstory.FTPService.STATUS";
	
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		mythread = new MyThread(interval);
		String status = "";
		Intent localIntent = new Intent(BROADCAST_ACTION).putExtra(EXTENDED_DATA_STATUS, status);
	    LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
	}
	
	@Override
	public synchronized void onDestroy() {
		super.onDestroy();
		if(!isRunning){
			mythread.interrupt();
			//myythread.stop();
			mythread = null;
		}
	}
	
	@Override
	public synchronized int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);
		if(!isRunning){
			mythread.start();
			isRunning = true;
		}
		return Service.START_NOT_STICKY;
	}
	
	class MyThread extends Thread {
		long interval;
		public MyThread(long interval){
			this.interval = interval;
		}
		
		@Override
		public void run(){
			while(isRunning){
				System.out.println("Service running");
				try {
					fileDownload();
					Thread.sleep(interval);
				} catch (InterruptedException e) {
					isRunning = false;
					e.printStackTrace();
				}
			}
			//Toast.makeText(FTPService.this.getBaseContext(), "2nd Story has downloaded all content", Toast.LENGTH_LONG).show();
			isRunning = false;
			Log.d("SS", "CONTENT DONE");
			Constants.downloadedAllVideos = true;
		}
		
		private void fileDownload() {
			if(checkInternetConnection()){
				boolean get = new DownloadFromFTP().ftpDownload("", "");
				Log.d("SS", "getting ?  " + get);
				// Stop Thread ???
				//mythread.interrupt();
				//myythread.stop();
				//mythread = null;
			}
		}
	}
	private boolean checkInternetConnection() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isAvailable() && cm.getActiveNetworkInfo().isConnected()) {
			return true;
		}
		else {
			return false;
		}
	}
}