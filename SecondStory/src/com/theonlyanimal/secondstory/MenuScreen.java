package com.theonlyanimal.secondstory;


//IMPORTS

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;



//CLASS
public class MenuScreen extends Activity {

	// GLOBALS
	private static final String TAG = "SS_MENU";
	boolean readyForLiveView = false;

	
	// LifeCycle
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
		setContentView(R.layout.menu_layout);
		
		Button guideBtn = (Button) findViewById(R.id.menu_guide_btn);
		guideBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent("android.intent.action.GUIDE");
	    		startActivity(i);
			}
		});
		
		Button mapsBtn = (Button) findViewById(R.id.menu_maps_btn);
		mapsBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent("android.intent.action.MAPS");
	    		startActivity(i);
			}
		});
		
		Button settingsBtn = (Button) findViewById(R.id.menu_settings_btn);
		settingsBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent("android.intent.action.SETTINGS");
	    		startActivity(i);
			}
		});
		
		Button aboutBtn = (Button) findViewById(R.id.menu_about_btn);
		aboutBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent("android.intent.action.ABOUT");
	    		startActivity(i);
			}
		});
		
		Button liveBtn = (Button) findViewById(R.id.menu_live_btn);
		liveBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(readyForLiveView) {
					Intent i = new Intent("android.intent.action.VIDEO");
		    		startActivity(i);
		    		finish();
				}
				else {
					Toast.makeText(getApplicationContext(), "youre not ready for the big leagues yet, son", Toast.LENGTH_LONG).show();
				}
			}
		});


	}
 

 
 
} /* EOC */
