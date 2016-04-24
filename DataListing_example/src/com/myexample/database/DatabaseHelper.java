package com.deftsoft.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DatabaseHelper {

	static DB sqLiteDataBaseManager;
	static Context mContext;
	private static DatabaseHelper helper;
	private static SQLiteDatabase sqLiteDatabase;

	@SuppressWarnings("static-access")
	public DatabaseHelper(Context context) {
		this.mContext = context;
		sqLiteDataBaseManager = new DB(mContext);
	}

	public static DatabaseHelper getHelper() {
		if (null == helper) {
			helper = new DatabaseHelper(mContext);
		}
		return helper;
	}

	public void open() {
		sqLiteDatabase = sqLiteDataBaseManager.getWritableDatabase();
	}

	public void close() {
		sqLiteDataBaseManager.close();
	}

	public void insertRecord(Bean beanData) {	
		open();
		
		ContentValues values = new ContentValues();
		values.put(DB.EMP_NAME, beanData.getName());
		values.put(DB.EMP_AGE, beanData.getAge());

		long insert = sqLiteDatabase.insert(DB.EMPLOYEE_TABLE, null, values);
		
		Log.i("insert data", " " +insert);  // no of rows
		close();	        
	}
	
	public void deleteAll(ArrayList<Bean> mylist) 
	{
		open();
		sqLiteDatabase.delete(DB.EMPLOYEE_TABLE, null, null);
		close();
	}
	
	public void deleteParticularEmployee(int id) 
	{
		open();
		
		Log.i("ANDROID id ", "" +DB.EMP_ID + "=" + id);		
		sqLiteDatabase.delete(DB.EMPLOYEE_TABLE, DB.EMP_ID + "=" + id, null); 
				
		close();
	}
	
	public ArrayList<Bean> getAllEmployee()
	{
		open();
		String strimg = null ;
		ArrayList<Bean> mList=new ArrayList<Bean>();
		String selectQuery = "SELECT * FROM " + DB.EMPLOYEE_TABLE; 
		Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) 
		{
			do {
				Bean myBean=new Bean();
				myBean.setId(cursor.getInt(0));
				myBean.setName(cursor.getString(1));
				myBean.setAge(cursor.getString(2));
   			    mList.add(myBean);
             Log.i("mylist dataa ",""  + mList.toString().trim());

			} while (cursor.moveToNext());
		}
		close();
		return mList;
	}
	
/*	public void updateData(Bean beanData) 
	{
		open();
		ContentValues values = new ContentValues();
		values.put(DB.EMP_NAME, beanData.getName());
		values.put(DB.EMP_AGE, beanData.getAge());

		close();
	}
	*/
	
/*	public ArrayList<Bean> getParticularEmployee(String name) {
		open();
		
		String strimg = null;
		ArrayList<Bean> mList = new ArrayList<Bean>();
		String selectQuery = "SELECT * FROM " + DB.EMPLOYEE_TABLE + " where " + DB.EMP_NAME + " like \"%" + name + "%\"";

		Log.i("selected", "  " + selectQuery);
		Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				Bean beanData = new Bean();
				beanData.setId(cursor.getInt(0));
				beanData.setName(cursor.getString(1));
				beanData.setAge(cursor.getString(2));
			
				mList.add(beanData);
				
			} while (cursor.moveToNext());
		}
		close();
		return mList;
	}
	*/
	

}