package com.yizhi.easyaccount;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("InlinedApi")
public class MainActivity extends Activity {

	private Button input;
	private Button output;
	private Button dateButton;
	private DatePicker datepicker;
	private Button head01;
	private Button head02;
	private Button count;
	private int year;
	private int month;
	private int day;
	private int date;
	private boolean isDeleteSuccessful = false;

	private EditText text_cost;
	private DatabaseManager database;
	
	//保存收入的和
	private float f_in_sum;
	//保存支出的和
	private float f_out_sum;
	//保存消费值
	private float cost;
	
	public Data data;
	public Data dataPre;
	

    private final static int REQUEST_CODE = 1 ;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	    //获取按钮及文本框
		input = (Button)findViewById(R.id.but_input);
		output = (Button)findViewById(R.id.but_output);
		dateButton = (Button)findViewById(R.id.but_date);	
		datepicker = (DatePicker)findViewById(R.id.datepicker);
		head01 = (Button)findViewById(R.id.head01);
		head02 = (Button)findViewById(R.id.head02);
		count =(Button)findViewById(R.id.button_count);
		text_cost = (EditText)findViewById(R.id.cost);

		//不能键盘修改
		text_cost.setKeyListener(null);

		//添加监听事件
		input.setOnClickListener(new InputButtonListener());
		output.setOnClickListener(new OutputButtonListener());
		dateButton.setOnClickListener(new DateButtonListener());
		count.setOnClickListener(new CountButtonListener());
		head02.setOnClickListener(new Head02ButtonListener());

		/* 获取日期
		 * 月份从0到11变化
		 */
		Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
		date =  year * 100 + month + 1;  //加一保证正常
		//datepicker初始化
		datepicker.init(year, month, day, 
				new  OnDateChangedListener() {
					
					public void onDateChanged(DatePicker view,
							int year, int month,  int day) 
					{
						MainActivity.this.year = year;
						MainActivity.this.month = month;
						MainActivity.this.day = day;
					}
				});
	}
	
	
	//一些事件监听器
	
	class Head02ButtonListener implements View.OnClickListener
	{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			database = new DatabaseManager(getBaseContext());
			Intent intent = new Intent(MainActivity.this, Statistics.class); 
			//启动Intent 的方法，注意是返回结果的方法，不是startActivity(intent)  
            startActivityForResult(intent, REQUEST_CODE); 
			
		}
		
	}
	// 设置日期完成按钮
	class DateButtonListener  implements View.OnClickListener
	{

		@Override
		public void onClick(View v) {	
			date =  year * 100 + month + 1;
		}	
	}
	
	//收入的监听器
	class InputButtonListener implements View.OnClickListener
	{

		@Override
		public void onClick(View v) {
			
			database = new DatabaseManager(getBaseContext());
			Intent intent = new Intent(MainActivity.this, InputActivity.class); 
			//启动Intent 的方法，注意是返回结果的方法，不是startActivity(intent)  
            startActivityForResult(intent, REQUEST_CODE); 
            
		}
	}

	//支出的监听器	
	class OutputButtonListener implements View.OnClickListener
	{

		@Override
		public void onClick(View v) {
			database = new DatabaseManager(getBaseContext());
			Intent intent = new Intent(MainActivity.this, OutputActivity.class); 
			startActivityForResult(intent,REQUEST_CODE);
		}	
	}
	
	/*
	 * 返回数据及处理
	 * (non-Javadoc)
	 * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
	 */
	 @Override  
	 protected void onActivityResult(int requestCode, int resultCode, Intent intent) {  
	     super.onActivityResult(requestCode, resultCode, intent);  
	     //判断类型
	     if(requestCode == REQUEST_CODE){  
	         if(resultCode == InputActivity.RESULT_CODE){  
	        	 f_in_sum = intent.getFloatExtra("f_in_sum", 0f);
	        	 
	        	 /*判断数据库中此记录是否存在
	        	  * 若存在，则取出数据
	        	  * 修改数据
	        	  * 更新数据
	        	  * 
	        	  * 若不存在，则加入数据
	        	  */
	        	 
	        	 //可以封装
	        	 data = database.find(date);
	        	 isDeleteSuccessful = database.delete(date);
	        	 data.input += f_in_sum;
	        	 database.add(data);

	         }  
	         else if(resultCode == OutputActivity.RESULT_CODE){
	        	 f_out_sum = intent.getFloatExtra("f_out_sum", 0f);
	        	 
	        	 /*判断数据库中此记录是否存在
	        	  * 若存在，则取出数据
	        	  * 修改数据
	        	  * 更新数据
	        	  * 
	        	  * 若不存在，则加入数据
	        	  * 
	        	  */
	        	 data = database.find(date);
	        	 isDeleteSuccessful = database.delete(date);
	        	 data.output += f_out_sum;
	        	 database.add(data);
	         }
	         else {
	        	 //退出活动
	        	 	finish();
	         }
      
	     }  
	 }  
	
	//计算总消费监听器
	class CountButtonListener implements View.OnClickListener
	{
		@Override
		public void onClick(View v) {
			database = new DatabaseManager(getBaseContext());
			//数据库操作
			data = database.find(date);
        	isDeleteSuccessful = database.delete(date);
        	
        	//本月消费 = 上一月的结余 + 本月的收入 - 本月的结余
        	dataPre = database.find(date - 1);
        	data.cost = data.input + dataPre.output - data.output;
        	
        	database.add(data);
        	//显示支出于文本框
			String str_cost = ""+data.cost;
			text_cost.setText(str_cost);
		}
		
	}

	//设置菜单
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	//设置菜单响应
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId()) {
        case R.id.menu01:
        	database = new DatabaseManager(getBaseContext());
        	database.clear();
        	text_cost.setText(""+0.0f);
        	Toast.makeText(getBaseContext(), "数据已清除", 500).show();
        	return true;
        case R.id.menu02:
        	Intent intent01 = new Intent(MainActivity.this, HowToUse.class);
        	startActivity(intent01);
        	return true;
        case R.id.menu03:
        	Intent intent02 = new Intent(MainActivity.this, About.class);
        	startActivity(intent02);
            return true;
        default:
            return super.onOptionsItemSelected(item);
    }
		
	}

}
