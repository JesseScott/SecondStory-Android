package com.theonlyanimal.secondstory;

// IMPORTS

import com.theonlyanimal.secondstory.R;
import android.os.Bundle;
import android.util.Log;
import android.app.Activity;
import android.content.Intent;


// CLASS
public class SplashScreen extends Activity {

	// GLOBALS
	private static final String TAG = "SecondStory";
	
	// LifeCycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
        
        // Timer
		Thread timer = new Thread(){
			@Override
			public void run() {
				//super.run();
				try { 
					// Time for 5 seconds
					sleep(5000);
					Intent i = new Intent("android.intent.action.WELCOME");
					startActivity(i);
				}
				catch(InterruptedException e) {
					e.printStackTrace();
				}
				finally {
					Log.v(TAG, " - Ending The Splash Screen - ");
					finish();
				}
			}
		};
		timer.start();
    }
	
}
