package com.ac.A207_4.mycalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity
{
	public ImageView imageView1, imageView2, imageView3;


	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		Animation animation1 = AnimationUtils
				.loadAnimation(this, R.anim.myanim);
		Animation animation2 = AnimationUtils.loadAnimation(this,
				R.anim.myanim2);
		Animation animation3 = AnimationUtils.loadAnimation(this,
				R.anim.myanim3);
		imageView1 = (ImageView) findViewById(R.id.basic);
		imageView2 = (ImageView) findViewById(R.id.bmi);
		imageView3 = (ImageView) findViewById(R.id.huilv);
		imageView1.startAnimation(animation1);
		imageView2.startAnimation(animation2);
		imageView3.startAnimation(animation3);
		imageView3.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{

				Intent intent = new Intent(MainActivity.this,
						ExchangeActivity.class);
				startActivity(intent);
				finish();
			}
		});

		this.imageView2.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(MainActivity.this, Bmi_tz.class);
				startActivity(intent);
				finish();
			}
		});

		this.imageView1.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(MainActivity.this, Calculator.class);
				startActivity(intent);
				finish();
			}
		});

	}

}
