package com.theonlyanimal.ar;

import android.os.Bundle;
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
	
	public static final String API_KEY = "AIzaSyB-AuNZSDst3VMYKF5CTm4JZfXgLQ5eCq0";

	//http://youtu.be/<VIDEO_ID>
	public static final String VIDEO_ID = "N0L1xyy8tqA";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/** attaching layout xml **/
		setContentView(R.layout.fullscreen_youtube_layout);

		/** Initializing YouTube player view **/
		YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player);
		youTubePlayerView.initialize(API_KEY, this);

	}
	
	@Override
	public void onInitializationFailure(Provider arg0,
			YouTubeInitializationResult arg1) {
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
			player.cueVideo(VIDEO_ID);
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
