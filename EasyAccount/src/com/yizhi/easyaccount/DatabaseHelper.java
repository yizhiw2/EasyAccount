package com.yizhi.easyaccount;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//数据库辅助类

public class DatabaseHelper extends SQLiteOpenHelper {
	
	
	private static final String NAME = "account.db"; //数据库名称
	private static final int VERSION = 1;   //数据库版本
	
	//构建器
	public DatabaseHelper(Context context) {
		super(context, NAME, null, VERSION);
	}
	
	//数据库第一次被创建时onCreate会被调用  
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
