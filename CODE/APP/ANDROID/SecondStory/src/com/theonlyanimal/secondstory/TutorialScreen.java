package com.theonlyanimal.secondstory;

// IMPORTS

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;



// CLASS
public class TutorialScreen extends FragmentActivity {

	// GLOBALS
	private static final int NUM_PAGES = 5;
	private ViewPager pager; // animation handler
	private PagerAdapter adapter; // provides pages to pager
	
	
	// LifeCycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorial_layout);
        
        // Instantiate Pager & Adapter
        pager = (ViewPager) findViewById(R.id.pager);
        adapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
    }
    
    @Override
    public void onBackPressed() {
    	if(pager.getCurrentItem() == 0) {
    		super.onBackPressed();
    	}
    	else {
    		pager.setCurrentItem(pager.getCurrentItem() -1);
    	}
    }
    
    // Pager Adapter SubClass
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
    	
    	// Constructor
    	public ScreenSlidePagerAdapter(FragmentManager fm) {
    		super(fm);
    	}
    	
    	@Override
    	public Fragment getItem(int position) {
    		return new ScreenSlidePageFragment();
    	}
    	
    	@Override
    	public int getCount() {
    		return NUM_PAGES;
    	}
    	
    	
    } /* EOC */
    
	
} /* EOC */
