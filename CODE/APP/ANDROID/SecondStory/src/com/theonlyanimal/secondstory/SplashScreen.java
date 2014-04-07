package com.theonlyanimal.secondstory;

// IMPORTS
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.app.Activity;


// CLASS
public class SplashScreen extends Activity {

	
	// LifeCycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_layout);
    }
	
}
