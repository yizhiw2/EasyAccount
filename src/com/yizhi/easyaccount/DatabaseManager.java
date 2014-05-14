package com.yizhi.easyaccount;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

//数据库实现类
public class DatabaseManager {
	
	private DatabaseHelper helper;
	private SQLiteDatabase db;
	
	public DatabaseManager(Context context) {
		helper = new DatabaseHelper(context);
		db = helper.getReadableDatabase();
	}
	
	 /** 
     * add datas 
     * @param datas 
     */  
    public void add(Data data) {  
        db.beginTransaction();  //开始事务  
        try {  
        	db.execSQL("INSERT INTO account_table VALUES(?, ?, ?, ?)",
                		new Object[]{data.date, data.input, data.output, data.cost});  
            db.setTransactionSuccessful();  //设置事务成功完成  
        } finally {  
            db.endTransaction();    //结束事务  
        }  
    }  
    
    /** 
     * 根据date查找 
     *  
     * @param date 
     * @return 
     */  
    public Data find(Integer date) {  
        SQLiteDatabase db = helper.getWritableDatabase();  
        Cursor cursor = db.rawQuery("select * from account_table where date=?",  
                new String[] { String.valueOf(date)});  
        if (cursor.moveToNext()) {  
            return new Data(cursor.getInt(0), cursor.getFloat(1),  
                    cursor.getFloat(2), cursor.getFloat(3));  
        }  
        return new Data(date);  
    }  
      
    
    //根据date删除
    public boolean delete(int date)
    {
    	SQLiteDatabase db = helper.getWritableDatabase(); 
 
    	db.execSQL("delete from account_table where date = ?",new String[] {String.valueOf(date)});
 
    	return true;
    }
    
    //关闭数据库
    public void closeDB() {  
        db.close();  
    }  
    
    //清除表
    public boolean clear()
    {
    	SQLiteDatabase db = helper.getWritableDatabase();
    	db.execSQL("delete from account_table");
    	return true;
    }
    
    
}
