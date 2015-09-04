package com.theonlyanimal.secondstory.activities;


//IMPORTS

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.app.Activity;
import android.graphics.Typeface;
import android.widget.Toast;

import com.parse.ParseObject;
import com.theonlyanimal.secondstory.R;
import com.theonlyanimal.secondstory.helpers.Constants;

//CLASS
public class FeedbackScreen extends Activity {

	// GLOBALS
	private static final String TAG = "SS_GUIDE";

	private Button feedbackBtn;
	private ImageButton backBtn;
	private EditText nameField, emailField, messageField;
	TextView navLabel;
	Typeface dinBlack, dinMedium;

	
	// LifeCycle
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_stay_still);
		setContentView(R.layout.feedback_layout);
		
		// Fonts
		dinBlack = Typeface.createFromAsset(getAssets(), "fonts/din alternate black.ttf");
		dinMedium = Typeface.createFromAsset(getAssets(), "fonts/din alternate medium.ttf"); 
		
		// Label
		navLabel = (TextView) findViewById(R.id.feedback_title);
		navLabel.setTypeface(dinBlack);
		
		// Buttons
		backBtn = (ImageButton) findViewById(R.id.feedback_back);
		backBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		feedbackBtn = (Button) findViewById(R.id.feedback_btn);
		feedbackBtn.setTypeface(dinMedium);
		feedbackBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
                submitFeedback();
			}
		});

		// EditText
        nameField = (EditText) findViewById(R.id.feedback_name);

        emailField = (EditText) findViewById(R.id.feedback_email);

        messageField = (EditText) findViewById(R.id.feedback_message);
		
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
	
	private void submitFeedback() {

        ParseObject feedback = new ParseObject(Constants.kSSClassNameFeedback);
        feedback.put(Constants.kSSFeedbackFieldName, nameField.getText().toString());
        feedback.put(Constants.kSSFeedbackFieldEmail, emailField.getText().toString());
        feedback.put(Constants.kSSFeedbackFieldMessage, messageField.getText().toString());
        feedback.saveEventually();

        Log.v(TAG, "Feedback Submitted");

        nameField.setText("");
        emailField.setText("");
        messageField.setText("");

        Toast.makeText(this, "Thank You for your feedback!", Toast.LENGTH_SHORT).show();
	}
 
 
} /* EOC */
