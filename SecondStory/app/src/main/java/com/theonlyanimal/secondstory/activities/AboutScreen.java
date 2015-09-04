package com.theonlyanimal.secondstory.activities;

// IMPORTS

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.theonlyanimal.secondstory.R;

import com.theonlyanimal.secondstory.fragments.AboutFragment;


// CLASS
public class AboutScreen extends FragmentActivity {

	// GLOBALS
	private static final String TAG = "SS_ABOUT";
	private static final int NUM_PAGES = 8; // TODO: get real number (7) (Parse)
	private ViewPager mPager;
	private PagerAdapter mPagerAdapter;

	// LifeCycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_stay_still);
        setContentView(R.layout.about_layout);

		mPager = (ViewPager) findViewById(R.id.about_pager);
		mPagerAdapter = new AboutPagerAdapter(getSupportFragmentManager());
		mPager.setAdapter(mPagerAdapter);
    }

    
    // Pager Adapter SubClass

	private class AboutPagerAdapter extends FragmentStatePagerAdapter {
		public AboutPagerAdapter(android.support.v4.app.FragmentManager fm) {
			super(fm);
		}

		@Override
		public android.support.v4.app.Fragment getItem(int position) {
			AboutFragment frag = AboutFragment.create(position);
			return frag;
		}

		@Override
		public int getCount() {
			return NUM_PAGES;
		}
	}
    
	
} /* EOC */
