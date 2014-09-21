package com.theonlyanimal.secondstory;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


public class AboutPage3 extends Fragment {
	
	ImageButton backBtn;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.about_page3, container, false);

		// Fonts
		Typeface dinBlack = Typeface.createFromAsset(getActivity().getAssets(), "fonts/din alternate black.ttf");
		//Typeface dinMedium = Typeface.createFromAsset(getActivity().getAssets(), "fonts/din alternate medium.ttf"); 
		
		// Label
		TextView navLabel = (TextView) rootView.findViewById(R.id.about3_title);
		navLabel.setTypeface(dinBlack);
		
		// Buttons
		backBtn = (ImageButton) rootView.findViewById(R.id.about3_back);
		backBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent("android.intent.action.MENU");
				startActivity(i);
			}
		});
		
		
		return rootView;
	}

}
