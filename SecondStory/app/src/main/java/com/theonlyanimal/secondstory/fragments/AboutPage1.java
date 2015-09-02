package com.theonlyanimal.secondstory.fragments;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.theonlyanimal.secondstory.R;

public class AboutPage1 extends Fragment {
	
	ImageButton backBtn;
		
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.about_page1, container, false);
		
		// Fonts
		Typeface dinBlack = Typeface.createFromAsset(getActivity().getAssets(), "fonts/din alternate black.ttf");
		
		// Label
		TextView navLabel = (TextView) rootView.findViewById(R.id.about1_title);
		navLabel.setTypeface(dinBlack);
		
		// Buttons
		backBtn = (ImageButton) rootView.findViewById(R.id.about1_back);
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
