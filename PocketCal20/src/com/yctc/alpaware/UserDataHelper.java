package com.yctc.alpaware;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDataHelper extends SQLiteOpenHelper{

	

	private static final String DATABASE_NAME ="UserData.db";
	private static final int DATABASE_VERSION = 1;

	 public UserDataHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);
	}
}



