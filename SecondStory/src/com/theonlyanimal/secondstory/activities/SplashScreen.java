package com.theonlyanimal.secondstory;

// IMPORTS

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.widget.TextView;


// CLASS
public class SplashScreen extends Activity {

	// GLOBALS
	private static final String TAG = "SS_SPLASH";
    Typeface dinBlack, dinMedium;
    TextView splashLabel;
	
	// LifeCycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
        
		// Fonts
		dinBlack = Typeface.createFromAsset(getAssets(), "fonts/din alternate black.ttf");
		dinMedium = Typeface.createFromAsset(getAssets(), "fonts/din alternate medium.ttf"); 
		
		// Label
		splashLabel = (TextView) findViewById(R.id.splash_label);
		splashLabel.setTypeface(dinMedium);
        
        // Timer
		Thread timer = new Thread(){
			@Override
			public void run() {
				try { 
					sleep(2500);
			    	Intent i = new Intent(SplashScreen.this, WelcomeScreen.class);
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
    
    
} /* EOC */
