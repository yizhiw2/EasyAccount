package com.yizhi.easyaccount;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.util.Log;

//数据的处理及求和
public class DataProcessor {
	private float sum = 0f; 
	public DataProcessor(List<String> list) {
		
		//设置迭代器
		Iterator<String> it = list.iterator();
		
		List<Float> list2 = new ArrayList<Float>();

		while(it.hasNext())
		{
			//获取edittext内容并转换为float
			float f = Float.valueOf( it.next() );
			list2.add(f);
		}
		//设置迭代器
		Iterator<Float> it2 = list2.iterator();
		
		//求和
		while(it2.hasNext())
		{
			setSum(getSum() + it2.next());
		}
	
	}
	//getter setter 方法
	public float getSum() {
		return sum;
	}
	public void setSum(float sum) {
		this.sum = sum;
	}
}
