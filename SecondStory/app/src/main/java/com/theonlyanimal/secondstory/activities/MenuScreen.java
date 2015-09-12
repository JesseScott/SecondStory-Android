package com.theonlyanimal.secondstory.activities;


//IMPORTS

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;

import com.theonlyanimal.secondstory.R;


//CLASS
public class MenuScreen extends Activity {

	// GLOBALS
	//private static final String TAG = "SS_MENU";
	boolean readyForLiveView = true;
	Typeface dinBlack, dinMedium;

	
	// LifeCycle

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_stay_still);
		setContentView(R.layout.menu_layout);

		// Fonts
		dinBlack = Typeface.createFromAsset(getAssets(), "fonts/din alternate black.ttf");
		dinMedium = Typeface.createFromAsset(getAssets(), "fonts/din alternate medium.ttf");
		
		Button galleryBtn = (Button) findViewById(R.id.menu_gallery_btn);
        galleryBtn.setTypeface(dinMedium);
        galleryBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent("android.intent.action.GALLERY");
	    		startActivity(i);
			}
		});

		Button feedbackBtn = (Button) findViewById(R.id.menu_feedback_btn);
		feedbackBtn.setTypeface(dinMedium);
		feedbackBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent("android.intent.action.FEEDBACK");
	    		startActivity(i);
			}
		});

		Button creditsBtn = (Button) findViewById(R.id.menu_credits_btn);
        creditsBtn.setTypeface(dinMedium);
        creditsBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//Intent i = new Intent("android.intent.action.ABOUT");
				//startActivity(i);
			}
		});

		Button liveBtn = (Button) findViewById(R.id.menu_live_btn);
		liveBtn.setTypeface(dinMedium);
		liveBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(readyForLiveView) {
					Intent i = new Intent("android.intent.action.VIDEO");
		    		startActivity(i);
		    		//finish();
				}
				else {
					Toast.makeText(getApplicationContext(), "youre not ready for the big leagues yet, son", Toast.LENGTH_LONG).show();
				}
			}
		});
	}
 
	@Override
    public void onBackPressed() {
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
		finish();
        super.onBackPressed();
    }

 
 
} /* EOC */
