package com.theonlyanimal.secondstory;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ScreenSlidePageFragment extends Fragment {
	
	private static int pageIndex;
	private static final String TAG = "SecondStory";

	
	ScreenSlidePageFragment(int id) {
		pageIndex = id;
		Log.v(TAG, "Page Index is " + pageIndex);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		int resID = -1;

		switch(pageIndex) {
			case 1:
				resID = R.layout.tutorial_page1;
				break;
			case 2:
				resID = R.layout.tutorial_page2;
				break;
			case 3:
				resID = R.layout.tutorial_page3;
				break;
			default:
				resID = R.layout.tutorial_page1;
				break;
		}
		
		Log.v(TAG, "Res Index is " + resID);
		ViewGroup rootView = (ViewGroup) inflater.inflate(resID, container, false);
		
		return rootView;
	}


}
