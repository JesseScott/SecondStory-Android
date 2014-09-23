package com.theonlyanimal.secondstory;


//IMPORTS

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Typeface;

//CLASS
public class MapsScreen extends Activity {

	// GLOBALS
	private static final String TAG = "SS_GUIDE";
	ImageButton backBtn;
	TextView navLabel;
	Typeface dinBlack, dinMedium;
	
	MediaPlayer player;
	boolean mediaHasPlayed = false;
	boolean mediaIsPlaying = false;
	boolean mediaHasVolume = true;
	
	boolean cameFromFullscreen = false;

	
	// LifeCycle
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v(TAG, "SIS is " + savedInstanceState);
		overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_stay_still);
		setContentView(R.layout.maps_layout);
		
		// Get Activity Lifecycle
		cameFromFullscreen = getIntent().getBooleanExtra("cameFromFullscreen", false);
		
		// Fonts
		dinBlack = Typeface.createFromAsset(getAssets(), "fonts/din alternate black.ttf");
		dinMedium = Typeface.createFromAsset(getAssets(), "fonts/din alternate medium.ttf"); 
		
		// MediaPlayer
		prepAudio();
		Log.v(TAG, "played ?" + mediaHasPlayed);
		if(savedInstanceState == null) {
			startAudio();
			mediaHasPlayed = true;
		}
		
		// Label
		navLabel = (TextView) findViewById(R.id.maps_title);
		navLabel.setTypeface(dinBlack);
		
		// Buttons
		backBtn = (ImageButton) findViewById(R.id.maps_back);
		backBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MapsScreen.this, MenuScreen.class);
				startActivity(i);
				finish();
			}
		});
		
	}
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		savedInstanceState.putBoolean("played", mediaHasPlayed);
		Log.v(TAG, "Saved");
	}
	
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
	    super.onRestoreInstanceState(savedInstanceState);
	    Log.v(TAG, "Resumed");
	    mediaHasPlayed = savedInstanceState.getBoolean("played");
	}
	
	@Override
    public void onBackPressed() {
		Intent i = new Intent(MapsScreen.this, MenuScreen.class);
		startActivity(i);
		//finish();
        super.onBackPressed();
    }
 
	@Override
	protected void onPause() {
		overridePendingTransition(R.anim.anim_stay_still, R.anim.anim_slide_out_right);
		player.stop();
		//finish();
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}
	
	private void prepAudio() {
		try {
			AssetFileDescriptor descriptor = getApplicationContext().getAssets().openFd("audio/map.mp3");
			player = new MediaPlayer();
			player.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
			player.prepare();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void startAudio() {
		player.setVolume(1.0f, 1.0f);
		mediaHasVolume = true;
		player.start();
		mediaIsPlaying = true;
	}

 
 
} /* EOC */
