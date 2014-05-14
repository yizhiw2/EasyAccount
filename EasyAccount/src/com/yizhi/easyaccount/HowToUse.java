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
				"\t\t\t\t1.调节日期可设置及查询以前的记录" +
				"（调节后须要点击“完成”按钮，查询则点击“计算当月消费”按钮）\n\n" +
				"\t\t\t\t2.每月可以添加多次收入和结余\n\n" +
				"\t\t\t\t3.当月消费 = 上月结余 + 当月收入 - 当月结余\n\n";
		textView = (TextView)findViewById(R.id.text_howtouse);
		textView.setText(text);
	}
}
