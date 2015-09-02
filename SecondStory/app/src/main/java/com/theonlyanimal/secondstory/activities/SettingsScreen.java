package com.theonlyanimal.secondstory.activities;


//IMPORTS

import android.os.Bundle;
import android.app.Activity;

import com.theonlyanimal.secondstory.R;

//CLASS
public class SettingsScreen extends Activity {

	// GLOBALS
	//private static final String TAG = "SS_GUIDE";

	
	// LifeCycle
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_stay_still);
		setContentView(R.layout.settings_layout);
	}
 
	@Override
	protected void onPause() {
		overridePendingTransition(R.anim.anim_stay_still, R.anim.anim_slide_out_right);
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}
 
 
} /* EOC */
