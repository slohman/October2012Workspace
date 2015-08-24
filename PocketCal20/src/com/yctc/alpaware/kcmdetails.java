package com.yctc.alpaware;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


public class kcmdetails extends Activity{

	 public static final int Main_Menu =0; //no more R.ids   
		
		
		public static final int KCM_MENU=1;
		
		public String cursordata;
	/** Called when the activity is first created. */
@Override
public void onCreate(Bundle Icicle) {
super.onCreate(Icicle);
setContentView(R.layout.kcmdetails);


Bundle bun = this.getIntent().getExtras();  
Long apid = bun.getLong("pID");

AlpaDataBaseHelper mdbh = new AlpaDataBaseHelper(this.getApplicationContext());
final SQLiteDatabase db = mdbh.getWritableDatabase(); 


Cursor cursor = db.rawQuery("Select details from kcm where _id = " + apid,null);	
startManagingCursor(cursor);

cursor.moveToFirst();

     TextView tv = (TextView)findViewById(R.id.kcmdetails) ;
     tv.setText(cursor.getString(cursor.getColumnIndex("details")));




}

public boolean onCreateOptionsMenu(Menu menu){
	menu.add(0,Main_Menu,0,"Main Menu");
	menu.add(0,KCM_MENU,0,"KCM Menu");
	return true;
	}

public boolean onOptionsItemSelected (MenuItem item ){
	switch (item.getItemId()){
	case Main_Menu :
		
		Intent myIntent = new Intent(getBaseContext(), PocketCal20.class);	
		startActivityForResult(myIntent, 0);
		
	return true;
	
case KCM_MENU :
	
	Intent myIntent1 = new Intent(getBaseContext(), KCM.class);	
	startActivityForResult(myIntent1, 0);
	
return true;
}
	return false;
	}
}