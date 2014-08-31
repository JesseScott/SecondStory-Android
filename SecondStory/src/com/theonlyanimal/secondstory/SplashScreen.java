package com.theonlyanimal.secondstory;

// IMPORTS

import android.os.Bundle;
import android.util.Log;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


// CLASS
public class SplashScreen extends Activity {

	// GLOBALS
	private static final String TAG = "SS_Splash";
    ProgressDialog progress;

	
	// LifeCycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(0, 0);
        setContentView(R.layout.splash_layout);
        
        // Progress Dialog
        //progress = ProgressDialog.show(this, "Checking Settings", "this will just take a second", true);
        
        // Timer
		Thread timer = new Thread(){
			@Override
			public void run() {
				//super.run();
				try { 
					sleep(2500);
					//checkSettings();
					//Intent i = new Intent("android.intent.action.MENU");
		    		Intent i = new Intent(SplashScreen.this, MenuScreen.class);
					startActivity(i);
		    		finish();
				}
				catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		timer.start();
    
    }
    
    protected void checkSettings() {

    	SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        boolean hasContent = settings.getBoolean("hasContent", false);
        boolean hasSeenWaltkthru = settings.getBoolean("hasSeenWalkthru", false);
        boolean isSharedDevice = settings.getBoolean("isSharedDevice", false);        
        
        if(hasContent && hasSeenWaltkthru && !isSharedDevice) {
        	progress.dismiss();
        	Intent i = new Intent("android.intent.action.VIDEO");
			startActivity(i);
			finish();
        }
        else if(!hasContent) {
        	progress.dismiss();
        	Intent i = new Intent("android.intent.action.WELCOME");
    		startActivity(i);
    		finish();
        }
        else if(!hasSeenWaltkthru) {
        	progress.dismiss();
        	Intent i = new Intent("android.intent.action.WELCOME");
    		startActivity(i);
    		finish();
        }
        else if(isSharedDevice) {
        	progress.dismiss();
        	Intent i = new Intent("android.intent.action.WELCOME");
    		startActivity(i);
    		finish();
        }	
    }
    
    
} /* EOC */
