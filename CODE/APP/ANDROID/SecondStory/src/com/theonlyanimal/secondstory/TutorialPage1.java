package com.theonlyanimal.secondstory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class TutorialPage1 extends Fragment {
	
	Button skipButton;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.tutorial_page1, container, false);
		
		skipButton = (Button) rootView.findViewById(R.id.button_skipTutPg1);
		skipButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.v("SecondStory", "Clicked Page 1");
				// Start Tracking Screen
				//Intent i = new Intent("android.intent.action.XXX");
				//startActivity(i);
			}
		});
		
		return rootView;
	}


}
