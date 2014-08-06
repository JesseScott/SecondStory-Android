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
	private static final String TAG = "SS_TUTORIAL";
	private static final int NUM_PAGES = 3;
	private ViewPager pager; 
	private PagerAdapter adapter; 
	
	
	// LifeCycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorial_layout);
        
        // Instantiate Pager & Adapter
        pager = (ViewPager) findViewById(R.id.pager);
        adapter = new TutorialPagerAdapter(getSupportFragmentManager());
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
    private class TutorialPagerAdapter extends FragmentStatePagerAdapter {
    	
    	// Constructor
    	public TutorialPagerAdapter(FragmentManager fm) {
    		super(fm);
    	}
    	
    	@Override
    	public Fragment getItem(int position) {
    		Fragment fragment;
    		switch(position) {
    			case 0:
    				fragment = new TutorialPage1();
    			break;
    			case 1:
    				fragment = new TutorialPage2();
    			break;
    			case 2:
    				fragment = new TutorialPage3();
    			break;
    			default:
    				fragment = new TutorialPage1();
    			break;
    		}
    		return fragment;
    	}
    	
    	@Override
    	public int getItemPosition(Object object) {
    		return POSITION_NONE;
    	}
    	
    	@Override
    	public int getCount() {
    		return NUM_PAGES;
    	}
    	
    } /* EOC */
    
	
} /* EOC */
