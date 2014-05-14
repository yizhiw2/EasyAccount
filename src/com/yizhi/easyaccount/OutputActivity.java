package com.yizhi.easyaccount;

import java.util.ArrayList;
import java.util.List;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class OutputActivity extends Activity{
	
	//定义
	private Button out_cancel;
	private Button out_save;
	private EditText out_cash;
	private EditText out_bank;
	private EditText out_zhifubao;
	private EditText out_yuebao;
	private EditText out_caifutong;
	private EditText out_other;
	
	List<String> edit_output;
	
	public final static int RESULT_CODE = 3; 
	
	public void onCreate(Bundle  savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.output_layout);
		
		// 获取按钮及文本框
		out_save = (Button)findViewById(R.id.out_save);
		out_cancel =  (Button)findViewById(R.id.out_cancel);
		out_cash = (EditText)findViewById(R.id.out_cash); 
		out_bank = (EditText)findViewById(R.id.out_bank);
		out_zhifubao = (EditText)findViewById(R.id.out_zhifubao);
		out_yuebao = (EditText)findViewById(R.id.out_yuebao);
		out_caifutong = (EditText)findViewById(R.id.out_caifutong);
		out_other = (EditText)findViewById(R.id.out_other);
		
		//设置监听事件
		out_cancel.setOnClickListener(new OutCancelListener());
		out_save.setOnClickListener(new OutSaveListener());	
	}
	
	//实现监听
	
	//取消操作
	class OutCancelListener implements View.OnClickListener
	{
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(OutputActivity.this, MainActivity.class); 
			startActivity(intent);
			finish();
		}
				
	}
	
	//保存操作
	class OutSaveListener implements View.OnClickListener
	{

		@Override
		public void onClick(View v) {
			//获取数据
			edit_output = init_output(); 
			//得到总支出
			float f_out_sum = new DataProcessor(edit_output).getSum();
			//返回数据到主活动
			Intent intent = new Intent(); 
			intent.putExtra("f_out_sum", f_out_sum); 
			setResult(RESULT_CODE , intent);
			finish();// 结束当前Activity的生命周期  
		}		
	}
	
	//初始化edittext内容
		public List<String> init_output() {
			List<String> l = new ArrayList<String>();
			ifAndAdd(l,out_cash.getText().toString());			
			ifAndAdd(l,out_bank.getText().toString());			
			ifAndAdd(l,out_zhifubao.getText().toString());			
			ifAndAdd(l,out_yuebao.getText().toString());			
			ifAndAdd(l,out_caifutong.getText().toString());			
			ifAndAdd(l,out_other.getText().toString());			
			return l;	
		}
		public  void ifAndAdd(List<String> l, String s)
		{
			if(s.equals(""))
			{
				l.add("0");
			}
			else {
				l.add(s);
			}
		}
}
