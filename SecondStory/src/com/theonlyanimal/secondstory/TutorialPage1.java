package com.theonlyanimal.secondstory;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class TutorialPage1 extends Fragment {
	
	private static final String TAG = "SS_FRAG_1";
	MediaPlayer player;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.tutorial_page1, container, false);
		
		// Text
		TextView txt = (TextView) rootView.findViewById(R.id.textView_tutPg1);  
		Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/din/din.ttf");  
		txt.setTypeface(font); 
		
		// Button
		Button skipButton = (Button) rootView.findViewById(R.id.button_skipTutPg1);
		skipButton.setTypeface(font);
		skipButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.v("SecondStory", "Clicked Page 1");
				//Start Tracking Screen
				Intent i = new Intent("android.intent.action.VIDEO");
				startActivity(i);
			}
		});
		
		Log.v(TAG, "OnCreateView");
		// MediaPlayer
		try {
			AssetFileDescriptor descriptor = getActivity().getAssets().openFd("audio/audio_guide.mp3");
			player = new MediaPlayer();
			player.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
			player.prepare();
			player.start();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return rootView;
	}
	
    public void OnAttach(Activity frag){
        super.onAttach(frag);
        
        Log.v(TAG, "OnAttach");

    }


}
