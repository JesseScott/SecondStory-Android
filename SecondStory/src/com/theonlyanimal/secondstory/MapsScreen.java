package com.theonlyanimal.secondstory;


//IMPORTS

import android.os.Bundle;
import android.view.View;
import android.app.Activity;

//CLASS
public class MapsScreen extends Activity {

	// GLOBALS
	private static final String TAG = "SS_GUIDE";

	
	// LifeCycle
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
		setContentView(R.layout.maps_layout);

	}
 

 
 
} /* EOC */
