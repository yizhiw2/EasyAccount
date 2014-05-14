package com.yizhi.easyaccount;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.URLSpan;
import android.widget.TextView;

public class About extends Activity{
	
	private TextView textView;
	private TextView textView02;
	
	public void onCreate(Bundle  savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_layout);
		
		textView = (TextView)findViewById(R.id.about);
		textView02 = (TextView)findViewById(R.id.abort02);
		
		String text = "\n" +
				"\t\t\t\t名称：简易记账\n\n" +
				"\t\t\t\t版本：1.0.0\n\n" +
				"\t\t\t\t作者：yizhi\n\n" +
				"\n\n\t\t\t\t关注微博：";
		textView.setText(text);
		
		//创建一个 SpannableString对象  
        SpannableString sp = new SpannableString("@忆之Yizhi");  
        //设置超链接  
        sp.setSpan(new URLSpan("http://weibo.com/2414729900/profile?rightmod=1&wvr=5&mod=personinfo"), 0, 8,  
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  
        textView.append(sp);  
        //设置可点击方法
        textView.setMovementMethod(LinkMovementMethod.getInstance());
	}
}
