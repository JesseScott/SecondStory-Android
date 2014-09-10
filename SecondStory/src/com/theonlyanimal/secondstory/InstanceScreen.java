package com.theonlyanimal.secondstory;


//IMPORTS

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;

//CLASS
public class InstanceScreen extends Activity {

	// GLOBALS
	private static final String TAG = "SS_INSTANCE";
	private static Context context;
	String[] instances;
	ImageButton backBtn;
	Button firstChoice, secondChoice, choose;
	TextView navLabel;
	Typeface dinBlack, dinMedium;

	
	// LifeCycle
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_stay_still);
		setContentView(R.layout.instance_layout);
		context = this;
		
		// ListView
		/*
		ListView locations = (ListView) findViewById(R.id.instance_listview);
		instances = getResources().getStringArray(R.array.instances_array);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.listview_item, R.id.listview_item_label, instances);
		locations.setAdapter(adapter);
		locations.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent i = new Intent("android.intent.action.ABOUT");
				i.putExtra("instance", position);
				startActivity(i);
			};
		});
		*/
		
		// Fonts
		dinBlack = Typeface.createFromAsset(getAssets(), "fonts/din alternate black.ttf");
		dinMedium = Typeface.createFromAsset(getAssets(), "fonts/din alternate medium.ttf"); 
		
		// Label
		navLabel = (TextView) findViewById(R.id.instance_title);
		navLabel.setTypeface(dinBlack);
		
		// Buttons
		backBtn = (ImageButton) findViewById(R.id.instance_back);
		backBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		choose = (Button) findViewById(R.id.instance_btn_choose);
		choose.setTypeface(dinMedium);
		
		firstChoice = (Button) findViewById(R.id.instance_btn_1);
		firstChoice.setTypeface(dinBlack);
		firstChoice.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent("android.intent.action.ABOUT");
				i.putExtra("instance", 1);
				startActivity(i);
			}
		});
		
		secondChoice = (Button) findViewById(R.id.instance_btn_2);
		secondChoice.setTypeface(dinBlack);
		secondChoice.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent("android.intent.action.ABOUT");
				i.putExtra("instance", 2);
				startActivity(i);
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
		overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_stay_still);
	}
 
} /* EOC */
