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
		//设置导航方式
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		//添加tab页
		actionBar.addTab(actionBar.newTab().setText("详细").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("折线").setTabListener(this));
		
	}

	public void onRestoreInstanceState(Bundle savedInstanceState)
	{
		if (savedInstanceState.containsKey(SELECTED_ITEM))
		{
			// 选中前面保存的索引对应的Fragment页
			getActionBar().setSelectedNavigationItem(
					savedInstanceState.getInt(SELECTED_ITEM));
		}
	}
	
	 public void onSaveInstanceState(Bundle outState)
	 {
		 // 将当前选中的Fragment页的索引保存到Bundle中
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
		// 创建一个新的Fragment对象
		Fragment fragment = new DummyFragment();
	
		// 创建一个Bundle对象，用于向Fragment传入参数
		Bundle args = new Bundle();

		args.putInt(DummyFragment.ARG_SELECTION_NUMBER,
				tab.getPosition() + 1);
		// 向fragment传入参数
		fragment.setArguments(args);
		
		// 获取FragmentTransaction对象
		FragmentTransaction ft1 = getFragmentManager()
				.beginTransaction();
		// 使用fragment代替该Activity中的container组件
		ft1.replace(R.id.container, fragment);
		// 提交事务
		ft1.commit();
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
}
