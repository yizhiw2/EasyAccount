package com.yizhi.easyaccount;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class HowToUse extends Activity{
	
	private TextView textView;
	
	public void onCreate(Bundle  savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.howtouse_layout);
		String text = "\n" +
				"\t\t\t\t1.�������ڿ����ü���ѯ��ǰ�ļ�¼" +
				"�����ں���Ҫ�������ɡ���ť����ѯ���������㵱�����ѡ���ť��\n\n" +
				"\t\t\t\t2.ÿ�¿�����Ӷ������ͽ���\n\n" +
				"\t\t\t\t3.�������� = ���½��� + �������� - ���½���\n\n";
		textView = (TextView)findViewById(R.id.text_howtouse);
		textView.setText(text);
	}
}
