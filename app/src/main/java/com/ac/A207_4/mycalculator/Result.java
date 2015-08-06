package com.ac.A207_4.mycalculator;      

import java.text.DecimalFormat;

import android.os.Bundle;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Result extends Activity { 
	
	//声明TextView对象
	private TextView show;
	private EditText showHeight;
	private EditText showWeight;
	private EditText bmi;
	private Button bt;
	private TextView TISHI;
	LinearLayout layout2;  
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);  
		
		//获取TextView对象
		show=(TextView)findViewById(R.id.result2);
		showHeight=(EditText)findViewById(R.id.height_show_number);
		showWeight=(EditText)findViewById(R.id.weight_show_number);
		bt=(Button)findViewById(R.id.about_button);
		TISHI=(TextView)findViewById(R.id.tishi);
		
		bmi=(EditText)findViewById(R.id.BMI_show_number);
		layout2=(LinearLayout)findViewById(R.id.linearlayout2);
		//取得启动该Activity的Intent对象
		Intent intent=getIntent(); 
		
		
		//取出Intent中的附加数据
		
		double get_height=intent.getDoubleExtra("field_height",0);
		double get_weight=intent.getDoubleExtra("field_weight",0);
		String show2=intent.getStringExtra("huayTextView") ;
		double after_get_height=(get_height/100)*(get_height/100);
		
		double BMI=floatCtrl(get_weight/after_get_height);
			
		//给EditText赋值
		TISHI.setText(""+show2);
		showHeight.setText(""+get_height); 
		showWeight.setText(""+get_weight);  
		bmi.setText(""+BMI);
		
		if (BMI<16) {
			
			show.setText("您已过度瘦弱！会影响健康偶~！     建议您适当增肥！");
			layout2.setBackgroundResource(R.drawable.overshou);
			
		}
		else if (BMI<18.5) {
			show.setText("您偏瘦偶，稍微增下肥吧~               你将更加美丽哦~"); 
			layout2.setBackgroundResource(R.drawable.littleshou);
		}
		else if (BMI<25) {
			show.setText("您的身材太标准啦！请继续保持！");
			layout2.setBackgroundResource(R.drawable.biaozhun);
		}
		else if (BMI<30) {
			show.setText("啊偶，你有点偏胖哦~                                注意加强锻炼！"); 
			layout2.setBackgroundResource(R.drawable.littlepang);
		}
		else if (BMI<35) {
			show.setText("亲，您不小心已经加入了肥胖的行列了，一定要多加锻炼啊！");
			layout2.setBackgroundResource(R.drawable.feipang);
		}
		else if (BMI<40) {
			show.setText("什么？！您已经                                                         重度肥胖了!!!!!!                      我好伤心啊~");
			layout2.setBackgroundResource(R.drawable.zhongdupang);
		}
		else {
			show.setText("您确定您是来自地球，再不减肥，你就OUT了！！！");
			layout2.setBackgroundResource(R.drawable.chaopangyi);
		}
	
		
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ComponentName comp2=new ComponentName(Result.this,About.class);
				Intent intent=new Intent();
				intent.setComponent(comp2);
				startActivity(intent);
			}
		});
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_result, menu);
		return true;
	}
	public double floatCtrl(double n)
	{
		// NumberFormat format=NumberFormat.getInstance(); //创建一个格式化类f
		// format.setMaximumFractionDigits(18); //设置小数位的格式
		DecimalFormat format = new DecimalFormat("0.##");
		return Double.parseDouble(format.format(n));
	}


}
