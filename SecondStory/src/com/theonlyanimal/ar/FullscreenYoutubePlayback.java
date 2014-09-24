package com.theonlyanimal.ar;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.ErrorReason;
import com.google.android.youtube.player.YouTubePlayer.PlaybackEventListener;
import com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;
import com.theonlyanimal.secondstory.R;

public class FullscreenYoutubePlayback extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{
	
	public static final String API_KEY = "AIzaSyC6yA82mS3VWCWXZQZ_zgiSC_lItLukSfY";

	//http://youtu.be/<VIDEO_ID>
	private String mMovieName = "";
	public static String VIDEO_ID_PLAYABLE = "";
	
	public static final String VIDEO_ID_BEEF = "N0L1xyy8tqA";
	public static final String VIDEO_ID_BICYCLES = "-zHUf_d3tUM"; //5npVxU_FMxg
	public static final String VIDEO_ID_ALLEY = "-zHUf_d3tUM";
	public static final String VIDEO_ID_COPPER = "ZaM6fWMAu-Q";
	public static final String VIDEO_ID_GUN = "VqKuwHpkI4o";
	public static final String VIDEO_ID_PENNIES = "UA_T7eHy8mM";
	public static final String VIDEO_ID_SHROOMS = "YDkUmpZYcXc";
	public static final String VIDEO_ID_SWEEPING = "F4BiLpAxFTs";
	public static final String VIDEO_ID_UMBRELLAS = "vwLm44Og9xU";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/** attaching layout xml **/
		setContentView(R.layout.fullscreen_youtube_layout);
		
		/** Getting Movie Name **/
		mMovieName = getIntent().getStringExtra("movieName");
		Log.v("SS YT", "Name is " + mMovieName);
		determineVideoID(mMovieName);

		/** Initializing YouTube player view **/
		YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player);
		youTubePlayerView.initialize(API_KEY, this);

	}
	
	@Override
	public void onInitializationFailure(Provider arg0, YouTubeInitializationResult arg1) {
		Toast.makeText(this, "Failured to Initialize!", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean wasRestored) {
		/** add listeners to YouTubePlayer instance **/
		player.setPlayerStateChangeListener(playerStateChangeListener);
		player.setPlaybackEventListener(playbackEventListener);
		player.setFullscreen(true);
		
		/** Start buffering **/
		if (!wasRestored) {
			player.cueVideo(VIDEO_ID_PLAYABLE);
		}
		
	}
	
	private void determineVideoID(String name) {
		
		// Trim
		String delimit = "/storage/emulated/0/SecondStory/BloodAlley/MEDIA/";
		String trim = name.substring(delimit.length(), name.length() -4);
		Log.v("SS YT", "Trimmed: " + trim);
		
		if(trim.compareTo("beef") == 0) {
			VIDEO_ID_PLAYABLE = VIDEO_ID_BEEF;
		}
		else if(trim.compareTo("bicycles") == 0) {
			VIDEO_ID_PLAYABLE = VIDEO_ID_BICYCLES;
		}
		else if(trim.compareTo("bloodalley") == 0) {
			VIDEO_ID_PLAYABLE = VIDEO_ID_ALLEY;
		}
		else if(trim.compareTo("copperthief") == 0) {
			VIDEO_ID_PLAYABLE = VIDEO_ID_COPPER;
		}
		else if(trim.compareTo("gun") == 0) {
			VIDEO_ID_PLAYABLE = VIDEO_ID_GUN;
		}
		else if(trim.compareTo("pennies") == 0) {
			VIDEO_ID_PLAYABLE = VIDEO_ID_PENNIES;
		}
		else if(trim.compareTo("shrooms") == 0) {
			VIDEO_ID_PLAYABLE = VIDEO_ID_SHROOMS;
		}
		else if(trim.compareTo("sweeping") == 0) {
			VIDEO_ID_PLAYABLE = VIDEO_ID_SWEEPING;
		}
		else if(trim.compareTo("umbrellas") == 0) {
			VIDEO_ID_PLAYABLE = VIDEO_ID_UMBRELLAS;
		}
		else  {
			VIDEO_ID_PLAYABLE = "hrXwOT765Yk"; // trailer
		}

	}
	
	private PlaybackEventListener playbackEventListener = new PlaybackEventListener() {

		@Override
		public void onBuffering(boolean arg0) {

		}

		@Override
		public void onPaused() {

		}

		@Override
		public void onPlaying() {

		}

		@Override
		public void onSeekTo(int arg0) {

		}

		@Override
		public void onStopped() {

		}

	};

	private PlayerStateChangeListener playerStateChangeListener = new PlayerStateChangeListener() {

		@Override
		public void onAdStarted() {

		}

		@Override
		public void onError(ErrorReason arg0) {
			Log.d("SS YT", "Error: " + arg0);
		}

		@Override
		public void onLoaded(String arg0) {

		}

		@Override
		public void onLoading() {
		}

		@Override
		public void onVideoEnded() {

		}

		@Override
		public void onVideoStarted() {

		}
	};
}
