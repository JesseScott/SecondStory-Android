package com.theonlyanimal.secondstory;


//IMPORTS

import net.hockeyapp.android.FeedbackManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.app.Activity;

//CLASS
public class FeedbackScreen extends Activity {

	// GLOBALS
	private static final String TAG = "SS_GUIDE";
	private static final String HOCKEY_APP_ID = "7f9de1a2655e56f6c60c798cc7d2cdec";
	
	private Button feedbackBtn;
	private ImageButton backBtn;

	
	// LifeCycle
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_stay_still);
		setContentView(R.layout.feedback_layout);
		
		// Buttons
		backBtn = (ImageButton) findViewById(R.id.feedback_back);
		backBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		feedbackBtn = (Button) findViewById(R.id.feedback_btn);
		feedbackBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showFeedbackActivity();
			}
		});
		
	}
 
	@Override
	protected void onPause() {
		overridePendingTransition(R.anim.anim_stay_still, R.anim.anim_slide_out_right);
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}
	
	public void showFeedbackActivity() {
		  FeedbackManager.register(this, HOCKEY_APP_ID, null);
		  FeedbackManager.showFeedbackActivity(this);
	}
 
 
} /* EOC */
