package com.theonlyanimal.secondstory.application;

import android.app.Application;

import com.parse.ParseACL;
import com.parse.ParseAnalytics;
import com.parse.Parse;
import com.parse.ParseCrashReporting;


public class SecondStory extends Application {

	private static final String TAG                 = "SS_APP";
	public static String PARSE_APP_ID               = "wNJe92RjX5LznjcIFRz94jQ6zg2APdhHpW4EzXKQ";
	public static String PARSE_CLIENT_KEY           = "O5xx4wPtLvZPpgyxU8cM5BHCzPPIaM4pYP42jhp0";

	@Override
	public void onCreate() {
		super.onCreate();

        // Parse
        Parse.enableLocalDatastore(getApplicationContext());
        ParseCrashReporting.enable(this);
        Parse.initialize(this, PARSE_APP_ID, PARSE_CLIENT_KEY);
        ParseAnalytics.trackAppOpenedInBackground(null);
        ParseACL defaultACL = new ParseACL();
        ParseACL.setDefaultACL(defaultACL, true);
        // installation ??
		
	}
	
} /* eoc */
