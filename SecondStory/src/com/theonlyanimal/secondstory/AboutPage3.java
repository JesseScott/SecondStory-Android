package com.theonlyanimal.secondstory;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AboutPage3 extends Fragment {
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.about_page3, container, false);

		// Fonts
		Typeface dinBlack = Typeface.createFromAsset(getActivity().getAssets(), "fonts/din alternate black.ttf");
		Typeface dinMedium = Typeface.createFromAsset(getActivity().getAssets(), "fonts/din alternate medium.ttf"); 
		
		// Label
		TextView navLabel = (TextView) rootView.findViewById(R.id.about3_title);
		navLabel.setTypeface(dinBlack);
		
		
		return rootView;
	}

}
