package com.theonlyanimal.secondstory;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class TutorialPage1 extends Fragment {
	
	
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
		
		return rootView;
	}


}
