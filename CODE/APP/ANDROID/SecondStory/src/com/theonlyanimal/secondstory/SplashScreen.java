package com.theonlyanimal.secondstory;

// IMPORTS
import android.os.Bundle;
import android.app.Activity;


// CLASS
public class SplashScreen extends Activity {

	
	// LifeCycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
    }
	
}
