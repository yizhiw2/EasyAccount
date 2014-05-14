package com.yizhi.easyaccount;

import android.annotation.SuppressLint;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.ActionBar;

@SuppressLint("NewApi")
public class Statistics extends Activity  implements ActionBar.TabListener {
	
	private static final String SELECTED_ITEM = "selected_item";
	@SuppressLint("NewApi")
	public void onCreate(Bundle  savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.statistic_layout);
		
		final ActionBar actionBar = getActionBar();
		//���õ�����ʽ
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		//���tabҳ
		actionBar.addTab(actionBar.newTab().setText("��ϸ").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("����").setTabListener(this));
		
	}

	public void onRestoreInstanceState(Bundle savedInstanceState)
	{
		if (savedInstanceState.containsKey(SELECTED_ITEM))
		{
			// ѡ��ǰ�汣���������Ӧ��Fragmentҳ
			getActionBar().setSelectedNavigationItem(
					savedInstanceState.getInt(SELECTED_ITEM));
		}
	}
	
	 public void onSaveInstanceState(Bundle outState)
	 {
		 // ����ǰѡ�е�Fragmentҳ���������浽Bundle��
		 outState.putInt(SELECTED_ITEM, 
				 getActionBar().getSelectedNavigationIndex());
	 }
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		// ����һ���µ�Fragment����
		Fragment fragment = new DummyFragment();
	
		// ����һ��Bundle����������Fragment�������
		Bundle args = new Bundle();

		args.putInt(DummyFragment.ARG_SELECTION_NUMBER,
				tab.getPosition() + 1);
		// ��fragment�������
		fragment.setArguments(args);
		
		// ��ȡFragmentTransaction����
		FragmentTransaction ft1 = getFragmentManager()
				.beginTransaction();
		// ʹ��fragment�����Activity�е�container���
		ft1.replace(R.id.container, fragment);
		// �ύ����
		ft1.commit();
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
}
