package com.yctc.alpaware;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class OpenTimeList extends SherlockActivity{
	

	Context mContext;

	@Override
	 public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.fltlist);
       
       
        
     
		
	
		

		     
 
	        
	      
	         AlpaDataBaseHelper mdbh = new AlpaDataBaseHelper(this.getApplicationContext());
		      final SQLiteDatabase db = mdbh.getWritableDatabase();
		
		      int names[] = {android.R.id.text1,android.R.id.text2};
		  	final Cursor c = db.rawQuery("Select _id, pairing,New,isDHD, tripdate ||'  '|| DeptTime ||'  '|| Pay  AS Fdata from opentime  order by _id" ,null);		  
		 		  		
		  
		 		 
		 		final ListAdapter  adapter = new MyAdapter(this,android.R.layout.simple_list_item_2,c,	
		 				new String[] {"pairing","Fdata"},names);
		 		
		 		if (c.getCount() > 0 ){
		 		 startManagingCursor(c);
		 		 ListView lv = (ListView)findViewById(android.R.id.list);
		 		 lv.setAdapter(adapter);
		 		}
		 		 
		 		 
		 		 
		 		ListView lv = (ListView)findViewById(android.R.id.list);
		 			lv.setOnItemClickListener( new AdapterView.OnItemClickListener() {
		 			 	public void onItemClick(AdapterView<?> parent, View v, int position, long id)
		 			 	{
		 			 	
		 			 		Bundle b = new Bundle();
		 			 		b.putLong("pID", id);
		 			 		 AlpaDataBaseHelper mdbh = new AlpaDataBaseHelper(OpenTimeList.this);
		 			 	     final SQLiteDatabase db = mdbh.getWritableDatabase(); 
		 			 	     
		 			 	     Cursor cursor = db.rawQuery("Select pairing,tripdate from opentime where _id = " + id ,null);	
		 					  
		 			  		
		 			 		 startManagingCursor(cursor);
		 			 		 cursor.moveToFirst();
		 			 		 String pairingNumber = cursor.getString(cursor.getColumnIndex("pairing"));
		 			 		 String Trip = cursor.getString(cursor.getColumnIndex("TripDate"));
		 			 		 
		 			 		 // Mark Trip as old since the user is viewing it
		 			 		 db.execSQL("update opentime set New = 0 where pairing = '"+pairingNumber+"' and tripdate = '"+ Trip +"'");
		 			 		 
		 			 		 
		 		        	Bundle b1 = new Bundle();
		 		        	b1.putString("prg", pairingNumber + " ");
		 		        	b1.putString("tripdate", Trip);
		 		        	
		 		        	
		 		        	
		 		        	Intent myIntent = new Intent(getBaseContext(), ottripview.class);	
		 		        	myIntent.putExtras(b1);
		 					startActivityForResult(myIntent, 0);
		 			 	
		 			 	}
		 			 	});
	        



	 
	  } // end on create
	
	 
    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }
    
    public void onCreateContextMenu(ContextMenu menu, View v,
            ContextMenuInfo menuInfo) {
super.onCreateContextMenu(menu, v, menuInfo);
android.view.MenuInflater inflater = getMenuInflater();
inflater.inflate(R.layout.opentime_context_menu, menu);
}


@Override
public boolean onContextItemSelected(MenuItem item) {
AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
long data =  info.id; // should be item selected from underlying adapter!

switch (item.getItemId()) {

case R.id.makeup :
	// pass teh data to the makeup activity for submission
	
	return true;
	
	
case R.id.swap :
	// pass data about this trip to the swap activity
	
	
	return true;
}

return false;



}
	
	


	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu){
		
		
	
                			
			MenuInflater inflater=getSupportMenuInflater();
			inflater.inflate(R.menu.otlistmenubar, menu);
			return super.onCreateOptionsMenu(menu);
			}

			public boolean onOptionsItemSelected (com.actionbarsherlock.view.MenuItem item ){
				switch (item.getItemId()){
				
				case R.id.home :
					
					Intent myIntent1 = new Intent(getBaseContext(), PocketCal20.class);	
					startActivityForResult(myIntent1, 0);
				return true;	
					
			case R.id.back :
			
			Intent myIntent2 = new Intent(getBaseContext(), onlinemenu1.class);	
			startActivityForResult(myIntent2, 0);
			
		return true;
			
		}
				return false;
		
		
		
		}
	
	  public class MyAdapter extends SimpleCursorAdapter {
  	    public MyAdapter(Context context, int layout, Cursor c, String[] from, int[] to) {
  	        super(context, layout, c, from, to);
  	    }

  	    @Override
  	    public void bindView(View view, Context context, Cursor cursor) {
  	        super.bindView(view, context, cursor);
  	        if((cursor.getInt(cursor.getColumnIndex("New")) == 1)  && (cursor.getInt(cursor.getColumnIndex("isDHD")) == 0)){
  	        	view.setBackgroundColor(Color.RED);
  	       }
  	        
  	      if((cursor.getInt(cursor.getColumnIndex("New")) == 1)  && (cursor.getInt(cursor.getColumnIndex("isDHD")) == 10)){
	        	view.setBackgroundColor(Color.GREEN);
	       }
  	        
  	        if ((cursor.getInt(cursor.getColumnIndex("New")) == 1) && (cursor.getInt(cursor.getColumnIndex("isDHD")) == 11)) {
  	    	view.setBackgroundColor(Color.MAGENTA);
  	    	
  	    	
  	    }
  	        
  	        
  	        
  	      if ((cursor.getInt(cursor.getColumnIndex("New")) != 1) && (cursor.getInt(cursor.getColumnIndex("isDHD")) == 11))      {
  	         // this will be the default background color: transparent
  	            view.setBackgroundColor(Color.GRAY);
  	    
  	}

  	  
  	  if((cursor.getInt(cursor.getColumnIndex("New")) != 1)  && (cursor.getInt(cursor.getColumnIndex("isDHD")) == 0)){
        	view.setBackgroundColor(Color.BLACK);
       }
  	  
  	 if((cursor.getInt(cursor.getColumnIndex("New")) != 1)  && (cursor.getInt(cursor.getColumnIndex("isDHD")) == 10)){
       	view.setBackgroundColor(Color.DKGRAY);
      }

  	    
}
}
	  
}