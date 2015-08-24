package com.yctc.alpaware;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class hotellistall extends Activity{

	
	
		
		public Cursor c;
		Context mContext;
		
		public static final int Main_Menu =0;
		public static final int GHOT_menu = 1;
		public static final int GHOTMAINT_menu = 2;

		/** Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle Icicle) {
	        super.onCreate(Icicle);
	        setContentView(R.layout.genericlist);
	        
	        
	        
	        
	        
	        AlpaDataBaseHelper mdbh = new  AlpaDataBaseHelper(this.getApplicationContext());
	      final SQLiteDatabase db = mdbh.getWritableDatabase(); 
	       
	      
	      
	     int names[] = {android.R.id.text1,android.R.id.text2};
	 		final Cursor c = db.rawQuery("Select _id, ID, City ||' '|| Hotel ||' ' || hotelnum as Hdata from hotel order by ID " ,null);		  
	 		  		
	 		
	 	

	 		  	  	 
	 		final ListAdapter adapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,c, 
		 			new String[] {"ID","Hdata"}, 
		 			names);		  
	 		  		
	 		 startManagingCursor(c);
	 	

	 		  	  	 
	 
	    
	 	ListView lv = (ListView)findViewById(android.R.id.list);
	 	lv.setAdapter(adapter);
	    	
	   
	  lv.setOnItemClickListener( new AdapterView.OnItemClickListener() {
	 	public void onItemClick(AdapterView<?> parent, View v, int position, long id)
	 	{
	 	// Display a messagebox.
	 		Bundle b = new Bundle();
	 		c.moveToPosition(position);
	 		b.putInt("pID", c.getInt(c.getColumnIndex("_id")));
	 		Intent myIntent = new Intent(getBaseContext(),gthoteldisplay.class);
	 		myIntent.putExtras(b);
	 		
            startActivityForResult(myIntent, 0);
	 	//Toast.makeText(getBaseContext(),"item" + id + " checked",Toast.LENGTH_SHORT).show();
	 	}
	 	});
	 	
	 	
	    }
	    
	
	 public boolean onCreateOptionsMenu(Menu menu){
				menu.add(0,Main_Menu,0,"Main Menu");
				menu.add(0,GHOT_menu,0,"Hotel Main Menu");
				menu.add(0,GHOTMAINT_menu,0,"Ramp/Hotel Maint Menu");
				return true;
				}

			public boolean onOptionsItemSelected (MenuItem item ){
				switch (item.getItemId()){
				case Main_Menu :
					
					Intent myIntent = new Intent(getBaseContext(), PocketCal20.class);	
					startActivityForResult(myIntent, 0);
					
				return true;
				
				case GHOT_menu :
					
					 myIntent = new Intent(getBaseContext(), ramphotelmain.class);	
					startActivityForResult(myIntent, 0);
					
				return true;
				
				case GHOTMAINT_menu :
	
					myIntent = new Intent(getBaseContext(), filemaintmain.class);	
					startActivityForResult(myIntent, 0);
	
					return true;
				}
				return false;
				}}
	   




