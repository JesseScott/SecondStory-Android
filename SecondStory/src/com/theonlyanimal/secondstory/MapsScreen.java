package com.theonlyanimal.secondstory;


//IMPORTS

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.app.Activity;
import android.graphics.Typeface;

//CLASS
public class MapsScreen extends Activity {

	// GLOBALS
	private static final String TAG = "SS_GUIDE";
	ImageButton backBtn;
	TextView navLabel;
	Typeface dinBlack, dinMedium;

	
	// LifeCycle
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_stay_still);
		setContentView(R.layout.maps_layout);
		
		// Fonts
		dinBlack = Typeface.createFromAsset(getAssets(), "fonts/din alternate black.ttf");
		dinMedium = Typeface.createFromAsset(getAssets(), "fonts/din alternate medium.ttf"); 
		
		// Label
		navLabel = (TextView) findViewById(R.id.maps_title);
		navLabel.setTypeface(dinBlack);
		
		// Buttons
		backBtn = (ImageButton) findViewById(R.id.maps_back);
		backBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
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
