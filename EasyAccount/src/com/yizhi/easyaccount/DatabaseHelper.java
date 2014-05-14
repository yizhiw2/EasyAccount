package com.yizhi.easyaccount;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//���ݿ⸨����

public class DatabaseHelper extends SQLiteOpenHelper {
	
	
	private static final String NAME = "account.db"; //���ݿ�����
	private static final int VERSION = 1;   //���ݿ�汾
	
	//������
	public DatabaseHelper(Context context) {
		super(context, NAME, null, VERSION);
	}
	
	//���ݿ��һ�α�����ʱonCreate�ᱻ����  
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE IF NOT EXISTS account_table" +
				" (date integer primary key," +
				" input single, output single," +
				"cost single)"); 
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		  db.execSQL("DROP TABLE IF EXISTS account_table"); 
		  onCreate(db); 
	}
}
