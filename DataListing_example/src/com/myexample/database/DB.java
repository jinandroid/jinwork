package com.deftsoft.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "_db";

	public static final String EMPLOYEE_TABLE = "detail_table";
	public static final String EMP_ID = "id";
	public static final String EMP_NAME = "name";

	public static final String EMP_AGE = "age";

	public DB(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		String CREATE_EMPLYOYEE_TABLE = "CREATE TABLE " + EMPLOYEE_TABLE + "("
				+ EMP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ EMP_NAME + " TEXT ,"
				+ EMP_AGE + " Text " + ")";
		
		db.execSQL(CREATE_EMPLYOYEE_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + EMPLOYEE_TABLE);
		onCreate(db);
	}

}