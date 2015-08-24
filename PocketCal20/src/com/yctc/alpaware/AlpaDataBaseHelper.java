package com.yctc.alpaware;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.SQLException;



 public class AlpaDataBaseHelper   extends SQLiteOpenHelper{


	 
	  
	  
	   private AlpaDataBaseHelper myDBHelper;
	   
	   public String DbaseName;
	  
	   private final Context myContext;
	  
	   public AlpaDataBaseHelper(Context context) {
	     super(context, "PocketCal.db", null, 2);
	     this.myContext = context;
	   }
	  
	   @Override
	   public void onCreate(SQLiteDatabase db) {
	   }
	  
	   @Override
	   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	   }
	  
	   public AlpaDataBaseHelper open() throws SQLException {
		   
	      myDBHelper = new AlpaDataBaseHelper(myContext);
	      @SuppressWarnings("unused")
		SQLiteDatabase db = myDBHelper.getWritableDatabase();
	      return this;
	   }
	 }