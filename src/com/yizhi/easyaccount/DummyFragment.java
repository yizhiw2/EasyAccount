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
	 * �÷����ķ���ֵ����ΪFragment��ʾ��View��ΪTab���ݵ�View��
	 * ��ҿ��������ﶨ�岼������ʾTab�Ĳ�ͬҳ��
	 * �����ҾͲ�д���ӵ��ˣ�ֱ�ӷ��صĶ���һ��textView
	 */
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		TextView textView = new TextView(getActivity());
		//��ȡ������Fragmentʱ����Ĳ�������������жϣ��Ӷ��Լ��趨��ͬ�Ĳ��������أ�
		Bundle bundle = getArguments();
		textView.setGravity(Gravity.CENTER_HORIZONTAL);
		textView.setText(bundle.getInt(ARG_SELECTION_NUMBER)+"");
		textView.setTextSize(40);
	
		//����TextView
		return textView;
	}
}
