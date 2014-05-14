package com.yizhi.easyaccount;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

@SuppressLint("NewApi")
public class DummyFragment extends Fragment {
	
	public static final String ARG_SELECTION_NUMBER = "selection_number";
	
	/**
	 * 该方法的返回值将作为Fragment显示的View即为Tab内容的View，
	 * 大家可以在这里定义布局来显示Tab的不同页面
	 * 这里我就不写复杂的了，直接返回的都是一个textView
	 */
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		TextView textView = new TextView(getActivity());
		//获取创建该Fragment时传入的参数（这里可以判断，从而自己设定不同的布局来返回）
		Bundle bundle = getArguments();
		textView.setGravity(Gravity.CENTER_HORIZONTAL);
		textView.setText(bundle.getInt(ARG_SELECTION_NUMBER)+"");
		textView.setTextSize(40);
	
		//返回TextView
		return textView;
	}
}
