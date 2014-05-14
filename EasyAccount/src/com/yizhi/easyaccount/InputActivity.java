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
import android.widget.Toast;

public class InputActivity extends Activity{
	
	//��������
	private Button in_cancel;
	private Button in_save;
	private EditText in_cash;
	private EditText in_bank;
	private EditText in_zhifubao;
	private EditText in_yuebao;
	private EditText in_caifutong;
	private EditText in_other;
	
	List<String> edit_input;
	
	public final static int RESULT_CODE = 2; 
	
	public void onCreate(Bundle  savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.input_layout);
		
		//��ȡ��ť���ı���
		in_cancel = (Button)findViewById(R.id.in_cancel); 
		in_save = (Button)findViewById(R.id.in_save);
		in_cash = (EditText)findViewById(R.id.in_cash);
		in_bank = (EditText)findViewById(R.id.in_bank);
		in_zhifubao =(EditText)findViewById(R.id.in_zhifubao);;
		in_yuebao = (EditText)findViewById(R.id.in_yuebao);
		in_caifutong = (EditText)findViewById(R.id.in_caifutong);
		in_other = (EditText)findViewById(R.id.in_other);
		
		//���ü�����
		in_cancel.setOnClickListener(new InCancelListener());
		in_save.setOnClickListener(new InSaveListener());
		
	}
	
	//ʵ�ּ���
	
	//ȡ������
	class InCancelListener implements View.OnClickListener
	{

		@Override
		public void onClick(View v) {
			Intent intent = new Intent(InputActivity.this, MainActivity.class); 
			startActivity(intent);
			finish();
		}	
	}
	
	//�������
	class InSaveListener implements View.OnClickListener
	{
		@Override
		public void onClick(View v) {
			
			//��ȡ���������
			edit_input = init_input(); 
			
			//ȡ��������
			float f_in_sum = new DataProcessor(edit_input).getSum();
			
			//�������ݵ����
			Intent intent = new Intent(); 
			intent.putExtra("f_in_sum", f_in_sum); 
			setResult(RESULT_CODE , intent);
			finish();// ������ǰActivity����������  
		}
		
	}
	
	//��ʼ��edittext����
	//���Է�װ
	public List<String> init_input() {
		List<String> l = new ArrayList<String>();
		ifAndAdd(l,in_cash.getText().toString());
		ifAndAdd(l,in_bank.getText().toString());
		ifAndAdd(l,in_zhifubao.getText().toString());
		ifAndAdd(l,in_yuebao.getText().toString());
		ifAndAdd(l,in_caifutong.getText().toString());	
		ifAndAdd(l,in_other.getText().toString());
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
