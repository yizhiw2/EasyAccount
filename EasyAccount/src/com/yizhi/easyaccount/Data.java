package com.yizhi.easyaccount;

//�������ݵ���
public class Data {
	
	public int date;
	public float input;
	public float output;
	public float cost;
	
	//һЩ���췽��
	public Data(int date, float input, float output, float cost)
	{
		this.date = date;
		this.input = input;
		this.output = output;
		this.cost = cost;
	}
	
	public Data()
	{
		this.date = 0;
		this.input = 0f;
		this.output = 0f;
		this.cost = 0f;
	}
	public Data(int date)
	{
		this.date = date;
		this.input = 0f;
		this.output = 0f;
		this.cost = 0f;
	}
}
