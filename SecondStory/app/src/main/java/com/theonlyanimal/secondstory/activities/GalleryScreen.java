package com.theonlyanimal.secondstory.activities;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.theonlyanimal.secondstory.R;
import com.theonlyanimal.secondstory.ar.FullscreenPlayback;
import com.theonlyanimal.secondstory.ar.FullscreenYoutubePlayback;
import com.theonlyanimal.secondstory.fragments.GalleryFragment;
import com.theonlyanimal.secondstory.helpers.Constants;


public class GalleryScreen extends FragmentActivity {

    private static final int NUM_PAGES = 8; // TODO: get real number (7) (Parse)
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    Intent intentToPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_layout);

        mPager = (ViewPager) findViewById(R.id.gallery_pager);
        mPagerAdapter = new GalleryPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }


    private class GalleryPagerAdapter extends FragmentStatePagerAdapter implements GalleryFragment.OnMovieSelectedListener {
        public GalleryPagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            GalleryFragment frag = GalleryFragment.create(position);
            frag.setListener(this);
            return frag;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        @Override
        public void onMovieSelected(int index) {
            fireVideo(index);
        }
    }

    public void fireVideo(int which) {

        //check if user wants to stream
        if(getApplicationContext().getSharedPreferences("com.theonlyanimal.secondstory", Context.MODE_PRIVATE).getBoolean("com.theonlyanimal.secondstory.stream", false)){
            //lets start youtube video
            intentToPreview = new Intent(GalleryScreen.this, FullscreenYoutubePlayback.class);
        }
        else {
            //if downloaded check if videos already downloaded
            if(Constants.downloadedAllVideos){
                //show from storage
                intentToPreview = new Intent(GalleryScreen.this, FullscreenPlayback.class);
            }
            else{
                //if not yet downloaded show from youtube
                intentToPreview = new Intent(GalleryScreen.this, FullscreenYoutubePlayback.class);
            }
        }

        intentToPreview.putExtra("movieName", getMovieName(which));
        intentToPreview.putExtra("shouldPlayImmediately", true);
        intentToPreview.setAction(android.content.Intent.ACTION_VIEW);
        GalleryScreen.this.startActivityForResult(intentToPreview, 1);
        //startActivity(intentToPreview);
    }

    public void checkEvent() {
        // TODO: Parse this
    }

    public String getMovieName(int index /*, String event */) {
        String mMovieName = "";
        String MEDIA_PATH = "";
        String SD_PATH = Environment.getExternalStorageDirectory().getPath();
        MEDIA_PATH = SD_PATH + "/SecondStory/dareu/media/"; // TODO: getIntent

        switch (index) { // TODO: smart index
//            case 0: // Beef
//                mMovieName = MEDIA_PATH + "beef.mp4";
//                break;
            case 1: // Pennies
                mMovieName = MEDIA_PATH + "exhibita.mp4";
                break;
            case 2: // Sweeping
                mMovieName = MEDIA_PATH + "leaving.mp4";
                break;
            case 3: // Copper
                mMovieName = MEDIA_PATH + "letter pt1.mp4";
                break;
            case 4: // Shrooms
                mMovieName = MEDIA_PATH + "letter pt2.mp4";
                break;
            case 5: // Umbrellas
                mMovieName = MEDIA_PATH + "plant.mp4";
                break;
            case 6: // Alley
                mMovieName = MEDIA_PATH + "portability.mp4";
                break;
            case 7: // Bicycles
                mMovieName = MEDIA_PATH + "stall.mp4";
                break;
//            case 9: // Gun
//                mMovieName = MEDIA_PATH + "gun.mp4";
//                break;
            default:
                mMovieName = MEDIA_PATH + "";
        }
        return mMovieName;
    }

}
