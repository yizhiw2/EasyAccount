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
	
	//��������ĺ�
	private float f_in_sum;
	//����֧���ĺ�
	private float f_out_sum;
	//��������ֵ
	private float cost;
	
	public Data data;
	public Data dataPre;
	

    private final static int REQUEST_CODE = 1 ;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	    //��ȡ��ť���ı���
		input = (Button)findViewById(R.id.but_input);
		output = (Button)findViewById(R.id.but_output);
		dateButton = (Button)findViewById(R.id.but_date);	
		datepicker = (DatePicker)findViewById(R.id.datepicker);
		head01 = (Button)findViewById(R.id.head01);
		head02 = (Button)findViewById(R.id.head02);
		count =(Button)findViewById(R.id.button_count);
		text_cost = (EditText)findViewById(R.id.cost);

		//���ܼ����޸�
		text_cost.setKeyListener(null);

		//��Ӽ����¼�
		input.setOnClickListener(new InputButtonListener());
		output.setOnClickListener(new OutputButtonListener());
		dateButton.setOnClickListener(new DateButtonListener());
		count.setOnClickListener(new CountButtonListener());
		head02.setOnClickListener(new Head02ButtonListener());

		/* ��ȡ����
		 * �·ݴ�0��11�仯
		 */
		Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
		date =  year * 100 + month + 1;  //��һ��֤����
		//datepicker��ʼ��
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
	
	
	//һЩ�¼�������
	
	class Head02ButtonListener implements View.OnClickListener
	{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			database = new DatabaseManager(getBaseContext());
			Intent intent = new Intent(MainActivity.this, Statistics.class); 
			//����Intent �ķ�����ע���Ƿ��ؽ���ķ���������startActivity(intent)  
            startActivityForResult(intent, REQUEST_CODE); 
			
		}
		
	}
	// ����������ɰ�ť
	class DateButtonListener  implements View.OnClickListener
	{

		@Override
		public void onClick(View v) {	
			date =  year * 100 + month + 1;
		}	
	}
	
	//����ļ�����
	class InputButtonListener implements View.OnClickListener
	{

		@Override
		public void onClick(View v) {
			
			database = new DatabaseManager(getBaseContext());
			Intent intent = new Intent(MainActivity.this, InputActivity.class); 
			//����Intent �ķ�����ע���Ƿ��ؽ���ķ���������startActivity(intent)  
            startActivityForResult(intent, REQUEST_CODE); 
            
		}
	}

	//֧���ļ�����	
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
	 * �������ݼ�����
	 * (non-Javadoc)
	 * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
	 */
	 @Override  
	 protected void onActivityResult(int requestCode, int resultCode, Intent intent) {  
	     super.onActivityResult(requestCode, resultCode, intent);  
	     //�ж�����
	     if(requestCode == REQUEST_CODE){  
	         if(resultCode == InputActivity.RESULT_CODE){  
	        	 f_in_sum = intent.getFloatExtra("f_in_sum", 0f);
	        	 
	        	 /*�ж����ݿ��д˼�¼�Ƿ����
	        	  * �����ڣ���ȡ������
	        	  * �޸�����
	        	  * ��������
	        	  * 
	        	  * �������ڣ����������
	        	  */
	        	 
	        	 //���Է�װ
	        	 data = database.find(date);
	        	 isDeleteSuccessful = database.delete(date);
	        	 data.input += f_in_sum;
	        	 database.add(data);

	         }  
	         else if(resultCode == OutputActivity.RESULT_CODE){
	        	 f_out_sum = intent.getFloatExtra("f_out_sum", 0f);
	        	 
	        	 /*�ж����ݿ��д˼�¼�Ƿ����
	        	  * �����ڣ���ȡ������
	        	  * �޸�����
	        	  * ��������
	        	  * 
	        	  * �������ڣ����������
	        	  * 
	        	  */
	        	 data = database.find(date);
	        	 isDeleteSuccessful = database.delete(date);
	        	 data.output += f_out_sum;
	        	 database.add(data);
	         }
	         else {
	        	 //�˳��
	        	 	finish();
	         }
      
	     }  
	 }  
	
	//���������Ѽ�����
	class CountButtonListener implements View.OnClickListener
	{
		@Override
		public void onClick(View v) {
			database = new DatabaseManager(getBaseContext());
			//���ݿ����
			data = database.find(date);
        	isDeleteSuccessful = database.delete(date);
        	
        	//�������� = ��һ�µĽ��� + ���µ����� - ���µĽ���
        	dataPre = database.find(date - 1);
        	data.cost = data.input + dataPre.output - data.output;
        	
        	database.add(data);
        	//��ʾ֧�����ı���
			String str_cost = ""+data.cost;
			text_cost.setText(str_cost);
		}
		
	}

	//���ò˵�
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	//���ò˵���Ӧ
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId()) {
        case R.id.menu01:
        	database = new DatabaseManager(getBaseContext());
        	database.clear();
        	text_cost.setText(""+0.0f);
        	Toast.makeText(getBaseContext(), "���������", 500).show();
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
