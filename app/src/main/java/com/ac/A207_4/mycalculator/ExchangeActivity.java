package com.ac.A207_4.mycalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/*
 * 该类为实现货币汇率计算的界面
 */
public class ExchangeActivity extends Activity
{

	public Spinner spinner1, spinner2;
	private EditText editText;
	private Button btn1;
	private TextView textView;
//	private ImageButton imageButton;
	private TextView result;
	private TextView source;
	private TextView target;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exchange);
		spinner1 = (Spinner) findViewById(R.id.spinner1); // 分别获取各个组件的ID，并为其设置监听事件
		spinner2 = (Spinner) findViewById(R.id.spinner2);
		editText = (EditText) findViewById(R.id.edit);
		textView = (TextView) findViewById(R.id.output);
		btn1 = (Button) findViewById(R.id.ensure);
		target = (TextView)this.findViewById(R.id.target);
		source = (TextView)this.findViewById(R.id.src);
		result = (TextView)this.findViewById(R.id.result);
		// btn2=(Button)findViewById(R.id.back); //与主界面联系的返回按钮
	//	imageButton = (ImageButton) findViewById(R.id.back1);
		editText.setInputType(EditorInfo.TYPE_CLASS_PHONE);
		spinner1.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id)
			{

				// 拿到被选择项的值
				String str1 = (String) spinner1.getSelectedItem(); // 得到选中的第一种货币种类
			} // 即要转换的货币种类

			@Override
			public void onNothingSelected(AdapterView<?> arg0)
			{
				//当用户没有进行选择时，弹出toast给予相应提示
				Toast.makeText(ExchangeActivity.this, "没有选择项目", Toast.LENGTH_SHORT)
						.show();
			}
		});
		
		spinner2.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id)
			{

				// 拿到被选择项的值
				String str2 = (String) spinner2.getSelectedItem(); // 得到选中的第二种货币种类
			} // 即要转换成的某个货币种类

			public void onNothingSelected(AdapterView<?> arg0)
			{
				Toast.makeText(ExchangeActivity.this, "没有选择项目", Toast.LENGTH_SHORT)
						.show();
				// TODO Auto-generated method stub
			}
		});
		
		btn1.setOnClickListener(new OnClickListener()
		{

			public void onClick(View v)
			{

				if (TextUtils.isEmpty(editText.getText()))
				{
					Toast.makeText(ExchangeActivity.this, "请输入不为空的正确数据",
							Toast.LENGTH_SHORT).show();
				}

				else
				{
					double result = 0;
					String str3 = spinner1.getSelectedItem().toString(); // str4表示转换的币种
					String str4 = spinner2.getSelectedItem().toString(); // str4表示转换目标币种
					double num = Double.parseDouble(editText.getText()
							.toString()); // 接收输入框里输入的值
					// String isempty=editText.getText().toString();
					// if (TextUtils.isEmpty(editText.getText())) {
					// btn1.setEnabled(false);
					//
					//
					// } else {
					// btn1.setEnabled(true);
					// return ;
					// }//判断转换的币种，
					// 选择对应的货比转换
					/*
					 * 以下为汇率计算主要逻辑实现
					 */
					if ("美元".equals(str3))
					{
						if (str4.equals("美元"))
						{
							result = num;
						}
						else if (str4.equals("人民币"))
						{
							result = num * 6.02;
						}
						else if (str4.equals("澳元"))
						{
							result = num * 1.3695;
						}
						else if (str4.equals("欧元"))
						{
							result = num * 0.9102;

						}
						else if (str4.equals("英镑"))
						{
							result = num * 0.64;
						}
						else if (str4.equals("日元"))
						{
							result = num * 123.92;
						}
						else if (str4.equals("韩元"))
						{
							result = num * 1165.67;
						}
						else if (str4.equals("港元"))
						{
							result = num * 7.7528;
						}

						textView.setText(String.valueOf(result));
					}
					if ("人民币".equals(str3))
					{
						if (str4.equals("人民币"))
						{
							result = num;
						}
						else if (str4.equals("美元"))
						{
							result = num * 0.1611;
						}
						else if (str4.equals("澳元"))
						{
							result = num * 0.2207;
						}
						else if (str4.equals("欧元"))
						{
							result = num * 0.1467;
						}
						else if (str4.equals("英镑"))
						{
							result = num * 0.1031;
						}
						else if (str4.equals("日元"))
						{
							result = num * 19.9688;
						}
						else if (str4.equals("韩元"))
						{
							result = num * 187.5513;
						}
						else if (str4.equals("港元"))
						{
							result = num * 1.2485;
						}

						textView.setText(String.valueOf(result));

					}
					if ("澳元".equals(str3))
					{
						if (str4.equals("人民币"))
						{
							result = num * 4.5311;
						}
						else if (str4.equals("美元"))
						{
							result = num * 0.7298;
						}
						else if (str4.equals("澳元"))
						{
							result = num;
						}
						else if (str4.equals("欧元"))
						{
							result = num * 0.6646;

						}
						else if (str4.equals("英镑"))
						{
							result = num * 0.4671;
						}
						else if (str4.equals("日元"))
						{
							result = num * 90.4806;
						}
						else if (str4.equals("韩元"))
						{
							result = num * 849.8156;
						}
						else if (str4.equals("港元"))
						{
							result = num * 5.6572;
						}

						textView.setText(String.valueOf(result));
					}
					if ("欧元".equals(str3))
					{
						if (str4.equals("人民币"))
						{
							result = num * 6.8172;
						}
						else if (str4.equals("美元"))
						{
							result = num * 1.098;
						}
						else if (str4.equals("澳元"))
						{
							result = num * 1.5056;
						}
						else if (str4.equals("欧元"))
						{
							result = num;

						}
						else if (str4.equals("英镑"))
						{
							result = num * 0.7029;
						}
						else if (str4.equals("日元"))
						{
							result = num * 136.1849;
						}
						else if (str4.equals("韩元"))
						{
							result = num * 1278.7967;
						}
						else if (str4.equals("港元"))
						{
							result = num * 8.5115;
						}

						textView.setText(String.valueOf(result));
					}
					if ("英镑".equals(str3))
					{
						if (str4.equals("人民币"))
						{
							result = num * 9.698;
						}
						else if (str4.equals("美元"))
						{
							result = num * 1.562;
						}
						else if (str4.equals("澳元"))
						{
							result = num * 2.1418;
						}
						else if (str4.equals("欧元"))
						{
							result = num * 1.4266;

						}
						else if (str4.equals("英镑"))
						{
							result = num;
						}
						else if (str4.equals("日元"))
						{
							result = num * 193.7349;
						}
						else if (str4.equals("韩元"))
						{
							result = num * 1819.1989;
						}
						else if (str4.equals("港元"))
						{
							result = num * 12.1083;
						}

						textView.setText(String.valueOf(result));
					}
					if ("日元".equals(str3))
					{
						if (str4.equals("人民币"))
						{
							result = num * 0.0501;
						}
						else if (str4.equals("美元"))
						{
							result = num * 0.0081;
						}
						else if (str4.equals("澳元"))
						{
							result = num * 0.0111;
						}
						else if (str4.equals("欧元"))
						{
							result = num * 0.0073;

						}
						else if (str4.equals("英镑"))
						{
							result = num * 0.0052;
						}
						else if (str4.equals("日元"))
						{
							result = num;
						}
						else if (str4.equals("韩元"))
						{
							result = num * 9.3901;
						}
						else if (str4.equals("港元"))
						{
							result = num * 0.0625;
						}

						textView.setText(String.valueOf(result));
					}
					if ("韩元".equals(str3))
					{
						if (str4.equals("人民币"))
						{
							result = num * 0.0053;
						}
						else if (str4.equals("美元"))
						{
							result = num * 0.0009;
						}
						else if (str4.equals("澳元"))
						{
							result = num * 0.0012;
						}
						else if (str4.equals("欧元"))
						{
							result = num * 0.0008;

						}
						else if (str4.equals("英镑"))
						{
							result = num * 0.0005;
						}
						else if (str4.equals("日元"))
						{
							result = num * 0.1065;
						}
						else if (str4.equals("韩元"))
						{
							result = num;
						}
						else if (str4.equals("港元"))
						{
							result = num * 0.0067;
						}

						textView.setText(String.valueOf(result));
					}
					if ("港元".equals(str3))
					{
						if (str4.equals("人民币"))
						{
							result = num * 0.8009;
						}
						else if (str4.equals("美元"))
						{
							result = num * 0.129;
						}
						else if (str4.equals("澳元"))
						{
							result = num * 0.1769;
						}
						else if (str4.equals("欧元"))
						{
							result = num * 0.1175;

						}
						else if (str4.equals("英镑"))
						{
							result = num * 0.0826;
						}
						else if (str4.equals("日元"))
						{
							result = num * 16.0002;
						}
						else if (str4.equals("韩元"))
						{
							result = num * 150.2438;
						}
						else if (str4.equals("港元"))
						{
							result = num;
						}

						textView.setText(String.valueOf(result));
					}
				}
			}

		});

//		imageButton.setOnClickListener(new OnClickListener()
//		{
//
//			public void onClick(View v)
//			{
//
//				Intent intent = new Intent(ExchangeActivity.this, MainActivity.class); // 通过intent传递
//				startActivity(intent);
//				
//				finish();
//			}
//		});

	}
	@Override
	public void onBackPressed()
	{
		Intent intent = new Intent(ExchangeActivity.this, MainActivity.class);
		startActivity(intent);
		finish();
	}

}
