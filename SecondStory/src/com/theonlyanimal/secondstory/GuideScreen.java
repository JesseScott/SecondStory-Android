package com.theonlyanimal.secondstory;


//IMPORTS

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.res.AssetFileDescriptor;

//CLASS
public class GuideScreen extends Activity {

	// GLOBALS
	private static final String TAG = "SS_GUIDE";
	MediaPlayer player;
	boolean mediaIsPlaying = false;
	
	// LifeCycle
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_stay_still);
		setContentView(R.layout.guide_layout);
		
		// MediaPlayer
		try {
			AssetFileDescriptor descriptor = getApplicationContext().getAssets().openFd("audio/audio_guide.mp3");
			player = new MediaPlayer();
			player.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
			player.prepare();
			player.start();
			mediaIsPlaying = true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}

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
