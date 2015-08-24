package com.yctc.alpaware;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class jumpseatlistnoncass extends Activity{
	
	public static final int Main_Menu =0; //no more R.ids   
	
	public static final int Jumpseat_MENU=1;
/** Called when the activity is first created. */
@Override
public void onCreate(Bundle Icicle) {
 super.onCreate(Icicle);
 setContentView(R.layout.genericlist);
		 
		 
         AlpaDataBaseHelper mdbh = new AlpaDataBaseHelper(this.getApplicationContext());
 final SQLiteDatabase db = mdbh.getWritableDatabase(); 
 int names[] = {android.R.id.text1,android.R.id.text2};       
 final Cursor c = db.rawQuery("Select _id,airline   from cass where cassstat = 0 order by airline" ,null );
 startManagingCursor(c);
 final ListAdapter adapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,c, 
 			new String[] {"airline", "airline"}, 
 			names);

ListView lv = (ListView)findViewById(android.R.id.list);
lv.setAdapter(adapter);

 lv.setOnItemClickListener( new AdapterView.OnItemClickListener() {
	 	public void onItemClick(AdapterView<?> parent, View v, int position, long id)
	 	{
	 	
	 		Bundle b = new Bundle();
	 		c.moveToPosition(position);
	 		b.putLong("pID", c.getLong(c.getColumnIndex("_id")));
	 		Intent myIntent = new Intent(getBaseContext(),jumpseatdetails.class);
	 		myIntent.putExtras(b);
	 		
            startActivityForResult(myIntent, 0);
	 	
	 	}
	 	});
	 	
	 	
	    }





}

