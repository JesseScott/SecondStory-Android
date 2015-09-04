package com.theonlyanimal.secondstory.activities;


//IMPORTS

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.graphics.Typeface;

import com.theonlyanimal.secondstory.R;

//CLASS
public class GuideScreen extends Activity {

	// GLOBALS
	//private static final String TAG = "SS_GUIDE";
	
	MediaPlayer player;
	boolean mediaIsPlaying = false;
	boolean mediaHasVolume = true;
	
	ImageButton restartAudio;
	//ImageButton toggleVolume;
	ImageButton backBtn;
	
	Typeface dinBlack, dinMedium;
	TextView navLabel;
	
	
	// LifeCycle
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_stay_still);
		setContentView(R.layout.guide_layout);
		
		// MediaPlayer
		prepAudio();
		startAudio();
		
		// Fonts
		dinBlack = Typeface.createFromAsset(getAssets(), "fonts/din alternate black.ttf");
		//dinMedium = Typeface.createFromAsset(getAssets(), "fonts/din alternate medium.ttf"); 
		
		// Label
		navLabel = (TextView) findViewById(R.id.guide_title);
		navLabel.setTypeface(dinBlack);
		
		// Buttons
		backBtn = (ImageButton) findViewById(R.id.guide_back);
		backBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		restartAudio = (ImageButton) findViewById(R.id.guide_replay);
		restartAudio.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(mediaIsPlaying) {
					player.pause();
					player.seekTo(0);
					player.start();
				}
				else {
					player.seekTo(0);
					player.start();
				}
			}
		});
		/*
		toggleVolume = (ImageButton) findViewById(R.id.guide_toggle);
		toggleVolume.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(mediaHasVolume) {
					player.setVolume(0.0f, 0.0f);
				}
				else {
					player.setVolume(1.0f, 1.0f);
				}
			}
		});
		*/

	}
	
	@Override
	protected void onPause() {
		overridePendingTransition(R.anim.anim_stay_still, R.anim.anim_slide_out_right);
		player.stop();
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}
	
	private void prepAudio() {
		try {
			AssetFileDescriptor descriptor = getApplicationContext().getAssets().openFd("audio/guide.mp3");
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

	// TODO: completion listener
 
 
} /* EOC */
