package com.theonlyanimal.secondstory;


//IMPORTS

import com.theonlyanimal.ar.FullscreenPlayback;
import com.theonlyanimal.ar.FullscreenYoutubePlayback;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Typeface;

//CLASS
public class MapsScreen extends Activity {

	// GLOBALS
	private static final String TAG = "SS_GUIDE";
	TextView navLabel;
	Typeface dinBlack, dinMedium;
	
	ImageView frame, preview, background;
	TextView label;
	String caption;
	boolean showPreview = false;
	
	ImageButton backBtn;
	ImageButton cameraBtn;
	ImageButton beef, pennies, sweeping, copper, shrooms, umbrellas, alley, bicycles, gun;
	
	MediaPlayer player;
	boolean mediaHasPlayed = false;
	boolean mediaIsPlaying = false;
	boolean mediaHasVolume = true;	
	boolean cameFromFullscreen = false;
	int whichVideo = 0;
	Intent intentToPreview;
	
	// LifeCycle
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_stay_still);
		setContentView(R.layout.maps_layout);
		
		// Fonts
		dinBlack = Typeface.createFromAsset(getAssets(), "fonts/din alternate black.ttf");
		dinMedium = Typeface.createFromAsset(getAssets(), "fonts/din alternate medium.ttf"); 
		
		// MediaPlayer
		prepAudio();
		if(savedInstanceState == null) {
			startAudio();
			mediaHasPlayed = true;
		}
		
		// Labels
		navLabel = (TextView) findViewById(R.id.maps_title);
		navLabel.setTypeface(dinBlack);
		label = (TextView) findViewById(R.id.movie_label);
		label.setTypeface(dinMedium);
		
		// BG
		background = (ImageView) findViewById(R.id.maps_bg_img);
		
		// Preview
		frame = (ImageView) findViewById(R.id.movie_frame);
		
		preview = (ImageView) findViewById(R.id.movie_preview);
		if(!showPreview) {
			frame.setVisibility(View.INVISIBLE);
			preview.setVisibility(View.INVISIBLE);
			label.setVisibility(View.INVISIBLE);
		}
		preview.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				Log.v(TAG, "Here Not There");
				fireVideo(whichVideo);
				return false;
			}
		});
		
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
		
		cameraBtn = (ImageButton) findViewById(R.id.maps_camera);
		cameraBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				frame.setVisibility(View.INVISIBLE);
				preview.setVisibility(View.INVISIBLE);
				label.setVisibility(View.INVISIBLE);
				Intent i = new Intent("android.intent.action.VIDEO");
	    		startActivity(i);
				//finish();
			}
		});
		
		beef = (ImageButton) findViewById(R.id.pin_beef);
		beef.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				togglePreview(1);
			}
		});
		
		pennies = (ImageButton) findViewById(R.id.pin_pennies);
		pennies.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				togglePreview(2);
			}
		});
		
		sweeping = (ImageButton) findViewById(R.id.pin_sweeping);
		sweeping.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				togglePreview(3);
			}
		});
		
		copper = (ImageButton) findViewById(R.id.pin_copper);
		copper.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				togglePreview(4);
			}
		});
		
		shrooms = (ImageButton) findViewById(R.id.pin_shrooms);
		shrooms.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				togglePreview(5);
			}
		});
		
		umbrellas = (ImageButton) findViewById(R.id.pin_umbrellas);
		umbrellas.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				togglePreview(6);
			}
		});
		
		alley = (ImageButton) findViewById(R.id.pin_alley);
		alley.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				togglePreview(7);
			}
		});
		
		bicycles = (ImageButton) findViewById(R.id.pin_bicycles);
		bicycles.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				togglePreview(8);
			}
		});
		
		gun = (ImageButton) findViewById(R.id.pin_gun);
		gun.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				togglePreview(9);
			}
		});
		
	}
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		savedInstanceState.putBoolean("played", mediaHasPlayed);
		Log.d(TAG, "Saved");
	}
	
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
	    super.onRestoreInstanceState(savedInstanceState);
	    Log.d(TAG, "Resumed");
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
	
	private void togglePreview(int pin) {	
		whichVideo = pin;
		showPreview = !showPreview;
		
		if(!showPreview) {
			frame.setVisibility(View.INVISIBLE);
			preview.setVisibility(View.INVISIBLE);
			label.setVisibility(View.INVISIBLE);
		}
		else if(showPreview) {
			frame.setVisibility(View.VISIBLE);
			preview.setVisibility(View.VISIBLE);
			label.setVisibility(View.VISIBLE);
			switch (pin) {
				case 1: // Beef
					preview.setImageResource(R.drawable.beef);
					caption = "Beef Tongue";
					break;
				case 2: // Pennies
					preview.setImageResource(R.drawable.pennies);
					caption = "Pennies";
					break;
				case 3: // Sweeping
					preview.setImageResource(R.drawable.sweeping);
					caption = "Sweeping";
					break;
				case 4: // Copper
					preview.setImageResource(R.drawable.copper);
					caption = "Copper Thief";
					break;
				case 5: // Shrooms
					preview.setImageResource(R.drawable.shrooms);
					caption = "Macrame";
					break;
				case 6: // Umbrellas
					preview.setImageResource(R.drawable.umbrellas);
					caption = "Umbrellas";
					break;
				case 7: // Alley
					preview.setImageResource(R.drawable.alley);
					caption = "Blood Alley";
					break;
				case 8: // Bicycles
					preview.setImageResource(R.drawable.bicycles);
					caption = "Bicycles";
					break;
				case 9: // Gun
					preview.setImageResource(R.drawable.gun);
					caption = "Gun";
					break;
				default:
					preview.setImageResource(0);
					caption = "";
			}
			
			// Set Label
			label.setText(caption.toUpperCase());
		}
	}
	
	public void fireVideo(int which) {
  
        	//check if user wants to stream
        	if(getApplicationContext().getSharedPreferences("com.theonlyanimal.secondstory", Context.MODE_PRIVATE).getBoolean("com.theonlyanimal.secondstory.stream", false)){
        		//lets start youtube video
        		intentToPreview = new Intent(MapsScreen.this, FullscreenYoutubePlayback.class);
        	}
        	else {
        		//if downloaded check if videos already downloaded
        		if(Constants.downloadedAllVideos){
        			//show from storage
        			intentToPreview = new Intent(MapsScreen.this, FullscreenPlayback.class);
        		}
        		else{
        			//if not yet downloaded show from youtube
        			intentToPreview = new Intent(MapsScreen.this, FullscreenYoutubePlayback.class);
        		}
        		
        	}
        	
            String mMovieName = "";
            String MEDIA_PATH = "";
            String SD_PATH = Environment.getExternalStorageDirectory().getPath();
            MEDIA_PATH = SD_PATH + "/SecondStory/BloodAlley/MEDIA/";

    		switch (which) {
			case 1: // Beef
				mMovieName = MEDIA_PATH + "beef.mp4";
				break;
			case 2: // Pennies
				mMovieName = MEDIA_PATH + "pennies.mp4";
				break;
			case 3: // Sweeping
				mMovieName = MEDIA_PATH + "sweeping.mp4";
				break;
			case 4: // Copper
				mMovieName = MEDIA_PATH + "copperthief.mp4";
				break;
			case 5: // Shrooms
				mMovieName = MEDIA_PATH + "shrooms.mp4";
				break;
			case 6: // Umbrellas
				mMovieName = MEDIA_PATH + "umbrellas.mp4";
				break;
			case 7: // Alley
				mMovieName = MEDIA_PATH + "bloodalley.mp4";
				break;
			case 8: // Bicycles
				mMovieName = MEDIA_PATH + "bicycles.mp4";
				break;
			case 9: // Gun
				mMovieName = MEDIA_PATH + "gun.mp4";
				break;
			default:
				mMovieName = MEDIA_PATH + "";
		}
        	
    		intentToPreview.putExtra("movieName", mMovieName);
    		intentToPreview.putExtra("shouldPlayImmediately", true);
        	intentToPreview.setAction(android.content.Intent.ACTION_VIEW);
        	MapsScreen.this.startActivityForResult(intentToPreview, 1);
      }


 
} /* EOC */
