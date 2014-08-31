package com.theonlyanimal.secondstory;

// IMPORTS

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;



// CLASS
public class AboutScreen extends FragmentActivity {

	// GLOBALS
	private static final String TAG = "SS_ABOUT";
	private static final int NUM_PAGES = 3;
	private ViewPager pager; 
	private PagerAdapter adapter; 
	private static int instance;
	
	
	// LifeCycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_stay_still);
        setContentView(R.layout.about_layout);
        
        // Get Instance
        if(getIntent().getExtras() != null){
        	instance = getIntent().getExtras().getInt("instance");
        	Log.d(TAG, "Instance is " + instance);
        }
        
        // Instantiate Pager & Adapter
        pager = (ViewPager) findViewById(R.id.pager);
        adapter = new AboutPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
    }
    
	@Override
	protected void onPause() {
		overridePendingTransition(R.anim.anim_stay_still, R.anim.anim_slide_out_right);
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_stay_still);
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
    private class AboutPagerAdapter extends FragmentStatePagerAdapter {
    	
    	// Constructor
    	public AboutPagerAdapter(FragmentManager fm) {
    		super(fm);
    	}
    	
    	@Override
    	public Fragment getItem(int position) {
    		Fragment fragment;
    		switch(position) {
    			case 0:
    				fragment = new AboutPage1();
    			break;
    			case 1:
    				fragment = new AboutPage2();
    			break;
    			case 2:
    				fragment = new AboutPage3();
    			break;
    			default:
    				fragment = new AboutPage1();
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
