package com.yizhi.easyaccount;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.util.Log;

//���ݵĴ������
public class DataProcessor {
	private float sum = 0f; 
	public DataProcessor(List<String> list) {
		
		//���õ�����
		Iterator<String> it = list.iterator();
		
		List<Float> list2 = new ArrayList<Float>();

		while(it.hasNext())
		{
			//��ȡedittext���ݲ�ת��Ϊfloat
			float f = Float.valueOf( it.next() );
			list2.add(f);
		}
		//���õ�����
		Iterator<Float> it2 = list2.iterator();
		
		//���
		while(it2.hasNext())
		{
			setSum(getSum() + it2.next());
		}
	
	}
	//getter setter ����
	public float getSum() {
		return sum;
	}
	public void setSum(float sum) {
		this.sum = sum;
	}
}
