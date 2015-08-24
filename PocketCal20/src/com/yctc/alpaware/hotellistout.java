package com.yctc.alpaware;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;

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

public class hotellistout extends SherlockActivity{

	
	
		
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
	        
	        
	        Bundle b = this.getIntent().getExtras();  
	        String HotelID = b.getString("hID");
	        
	        
	        AlpaDataBaseHelper mdbh = new  AlpaDataBaseHelper(this.getApplicationContext());
	      final SQLiteDatabase db = mdbh.getWritableDatabase(); 
	       
	      
	      
	     int names[] = {android.R.id.text1,android.R.id.text2};
	 		final Cursor c = db.rawQuery("Select _id, ID, City ||' '|| Hotel ||' ' || hotelnum as Hdata from hotel where ID Like '%" + HotelID  +  "%' " ,null);		  
	 		  		
	 		
	 	

	 		  	  	 
	 		final ListAdapter adapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,c, 
		 			new String[] {"ID","Hdata"}, 
		 			names);		  
	 		  		
	 		 startManagingCursor(c);
	 	

	 		  	  	 
	 
	    
	 	ListView lv = (ListView)findViewById(android.R.id.list);
	 	lv.setAdapter(adapter);
	 	
	 	
	    	
	   
	  lv.setOnItemClickListener( new AdapterView.OnItemClickListener() {
	 	public void onItemClick(AdapterView<?> parent, View v, int position, long id)
	 	{
	 
	 		Bundle b = new Bundle();
	 		c.moveToPosition(position);
	 		b.putInt("pID", c.getInt(c.getColumnIndex("_id")));
	 		
	 		Intent myIntent = new Intent(getBaseContext(),gthoteldisplay.class);
	 		myIntent.putExtras(b);
	 		
            startActivityForResult(myIntent, 0);
	 	
	 	}
	 	});
	 	
	 	
	    }
	    
	
public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu){
	    	
	    	
	    	
	    	//	menu.add("Get_Egrid")
	    		
	    		//	.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
	                			
	    		MenuInflater inflater=getSupportMenuInflater();
	    		inflater.inflate(R.menu.rampsubmenu, menu);
	    		return super.onCreateOptionsMenu(menu);
	    		}

	    		public boolean onOptionsItemSelected (com.actionbarsherlock.view.MenuItem item ){
	    			switch (item.getItemId()){
	    			
	    			case R.id.home :
	    				
	    				Intent myIntent1 = new Intent(getBaseContext(), PocketCal20.class);	
	    				startActivityForResult(myIntent1, 0);
	    			return true;	
	    			case R.id.back :
	    				
	    				super.onBackPressed();
	    			return true;	
	    		
	    		
	    	}
	    			return false;
	    		}}
	   




