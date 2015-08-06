package com.ac.A207_4.mycalculator;

import android.os.Bundle;
import android.app.Activity;
import android.content.ComponentName;
import android.view.Menu;
import android.widget.TextView;

public class About extends Activity {
	
	private TextView t1;
	private TextView t2;
	private TextView t3;
	private TextView t4;
	private TextView t5;
	private TextView t6;
	private TextView t7;
	private TextView t8;
	private TextView t9;
	private TextView t10;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		
		t1=(TextView)findViewById(R.id.about_tv1);
		t2=(TextView)findViewById(R.id.about_tv2);
		t3=(TextView)findViewById(R.id.about_tv3);
		t4=(TextView)findViewById(R.id.about_tv4);
		t5=(TextView)findViewById(R.id.about_tv5);
		t6=(TextView)findViewById(R.id.about_tv6);
		t7=(TextView)findViewById(R.id.about_tv7);
		t8=(TextView)findViewById(R.id.about_tv8);
		t9=(TextView)findViewById(R.id.about_tv9);
		t10=(TextView)findViewById(R.id.about_tv10);
		
		ComponentName comp2=getIntent().getComponent();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.about, menu);
		return true;
	}

}
