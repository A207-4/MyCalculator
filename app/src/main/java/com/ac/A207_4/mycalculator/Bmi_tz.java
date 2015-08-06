package com.ac.A207_4.mycalculator;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import android.R.string;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Bmi_tz extends Activity
{
	private EditText field_height;
	private EditText field_weight; // ////
	private RadioButton rb1;
	private RadioButton rb2;
	private String sex;

	private String note, bheight, bweight;
	private TextView tv_result;
	private TextView huayTextView;
	private Button button_save;
	String str;

	LinearLayout layout1;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle icicle)
	{
		super.onCreate(icicle);
		setContentView(R.layout.main);

		findView();
		OnClickListener();
	}

	private void OnClickListener()
	{
		// TODO Auto-generated method stub
		button_save.setOnClickListener(calcBMI);
		rb1.setOnClickListener(sex1);
		rb2.setOnClickListener(sex2);
	}

	private void findView()
	{
		// TODO Auto-generated method stub
		field_height = (EditText) findViewById(R.id.et1);
		field_weight = (EditText) findViewById(R.id.et2);// /////
		button_save = (Button) findViewById(R.id.button01);
		rb1 = (RadioButton) findViewById(R.id.rbutton1);
		rb2 = (RadioButton) findViewById(R.id.rbutton2);
		tv_result = (TextView) findViewById(R.id.result);
		layout1 = (LinearLayout) findViewById(R.id.linearLayout1);
		huayTextView = (TextView) findViewById(R.id.huanying);
		// tv_result=(TextView)findViewById(R.id.);
	}

	private RadioButton.OnClickListener sex1 = new View.OnClickListener()
	{

		@Override
		public void onClick(View arg0)
		{
			// TODO Auto-generated method stub
			huayTextView.setText("先生您好！欢迎您测量BMI.！");
			layout1.setBackgroundResource(R.drawable.myboy);
		}
	};

	private RadioButton.OnClickListener sex2 = new View.OnClickListener()
	{

		@Override
		public void onClick(View arg0)
		{
			// TODO Auto-generated method stub
			huayTextView.setText("女士您好！欢迎您测量BMI.！");
			layout1.setBackgroundResource(R.drawable.mygirl);
		}
	};

	private Button.OnClickListener calcBMI = new View.OnClickListener()
	{

		@Override
		public void onClick(View v)
		{
			// TODO Auto-generated method stub
			// 取得输入框的内容

			note = huayTextView.getText().toString();
			bheight = field_height.getText().toString();
			bweight = field_weight.getText().toString();
			if (bheight.equals("") || bweight.equals(""))
			{
				Toast.makeText(Bmi_tz.this, "请输入身高和体重", Toast.LENGTH_LONG)
						.show();
			}
			else
			{
				double height = Double.parseDouble(field_height.getText()
						.toString());
				double weight2 = Double.parseDouble(field_weight.getText()
						.toString());

				if (height > 10 && height < 280 && weight2 > 0 && weight2 < 300)
				{
					Intent intent = new Intent(Bmi_tz.this, Result.class);
					// 将要传递的值附加到Intent对象
					intent.putExtra("field_height", height);
					intent.putExtra("field_weight", weight2);
					intent.putExtra("huayTextView", note);
					// intent.putExtra("rb1", sex1);
					// intent.putExtra("rbt2", sex2);

					// 启动该Intent对象，实现跳转
					startActivity(intent);
				}
				else
				{
					tv_result.setText("请输入合理的身高和体重!!!谢谢！！！");
				}
			}
		}

		// 两个activity间跳转关系

		// } else {
		// Toast.makeText(Bmi_tz.this,
		// "请完整输入您的身高和体重！！！",Toast.LENGTH_LONG).show();
		// }

		// 两个activity间跳转关系
		// Intent intent=new Intent(Bmi_tz.this,Result.class);

		// 将要传递的值附加到Intent对象
		// intent.putExtra("field_height", height);
		// intent.putExtra("field_weight", weight2);

		// 启动该Intent对象，实现跳转
		// startActivity(intent);

	};

	public void onBackPressed()
	{
		Intent intent = new Intent(Bmi_tz.this, MainActivity.class);
		startActivity(intent);
		finish();
	}
}
