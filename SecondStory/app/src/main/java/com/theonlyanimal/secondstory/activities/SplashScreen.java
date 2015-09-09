package com.theonlyanimal.secondstory.activities;

// IMPORTS

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.theonlyanimal.secondstory.activities.WelcomeScreen;
import com.theonlyanimal.secondstory.R;
import com.theonlyanimal.secondstory.helpers.Constants;

import java.util.List;


// CLASS
public class SplashScreen extends Activity {

	// GLOBALS
	private static final String TAG = "SS_SPLASH";
    Typeface dinBlack, dinMedium;
    TextView splashLabel;
	
	// LifeCycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
        
		// Fonts
		dinBlack = Typeface.createFromAsset(getAssets(), "fonts/din alternate black.ttf");
		dinMedium = Typeface.createFromAsset(getAssets(), "fonts/din alternate medium.ttf"); 
		
		// Label
		splashLabel = (TextView) findViewById(R.id.splash_label);
		splashLabel.setTypeface(dinMedium);

        // foo
        Log.i(TAG, "Screen Size is " + getSize(this));
        Log.i(TAG, "Screen Density is " + getDensity(this));


        // Parse
        /*
        ParseQuery<ParseObject> query = ParseQuery.getQuery(Constants.kSSClassNameShow);
        query.include("currentEvent");
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (object == null) {
                    Log.d(TAG, "The Current Show Request Failed");
                    navigate(null);
                }
                else {
                    Log.d(TAG, "Retrieved the Current Show");
                    ParseObject ptr = (ParseObject) object.get("currentEvent");
                    if(ptr != null) {
                        String name = ptr.get("name").toString();
                        navigate(name);
                    }
                    else {
                        navigate(null);
                    }
                }
            }
        });
        */



        // Timer
        Thread timer = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    sleep(750);
                    navigate(null);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    finish();
                }
            }
        };
        timer.start();


    }


    private static String getSize(Context context) {
        int screenLayout = context.getResources().getConfiguration().screenLayout;
        screenLayout &= Configuration.SCREENLAYOUT_SIZE_MASK;

        switch (screenLayout) {
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                return "small";
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                return "normal";
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                return "large";
            case Configuration.SCREENLAYOUT_SIZE_XLARGE:
                return "xlarge";
            default:
                return "undefined";
        }
    }

    private static String getDensity(Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        if (density >= 4.0) {
            return "xxxhdpi";
        }
        if (density >= 3.0) {
            return "xxhdpi";
        }
        if (density >= 2.0) {
            return "xhdpi";
        }
        if (density >= 1.5) {
            return "hdpi";
        }
        if (density >= 1.0) {
            return "mdpi";
        }
        return "ldpi";
    }

    private void navigate(String show) {
        Intent i = new Intent(SplashScreen.this, WelcomeScreen.class);
        startActivity(i);
        if(show != null)
        {
            i.putExtra(Constants.kSSSharedPrefsShow, show);
        }
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
        finish();
    }


    
    
} /* EOC */
