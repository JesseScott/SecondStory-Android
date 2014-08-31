package com.theonlyanimal.secondstory;


//IMPORTS

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

//CLASS
public class InstanceScreen extends Activity {

	// GLOBALS
	private static final String TAG = "SS_INSTANCE";
	private static Context context;
	String[] instances;

	
	// LifeCycle
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
		setContentView(R.layout.instance_layout);
		context = this;
		
		// ListView
		ListView locations = (ListView) findViewById(R.id.instance_listview);
		instances = getResources().getStringArray(R.array.instances_array);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.listview_item, R.id.listview_item_label, instances);
		locations.setAdapter(adapter);
		locations.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent i = new Intent("android.intent.action.TUTORIAL");
				i.putExtra("instance", position);
				startActivity(i);
			};
		});

	}
 
} /* EOC */
