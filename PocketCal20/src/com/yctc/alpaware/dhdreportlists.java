package com.yctc.alpaware;

import android.app.ListActivity;
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
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;


       
	        
	public class dhdreportlists extends SherlockActivity {

		public Cursor c;
		Context mContext;
		
		

		/** Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle Icicle) {
	        super.onCreate(Icicle);
	        setContentView(R.layout.fltlistblockhours);
	        
	        
	        Bundle bun = this.getIntent().getExtras();  
	        String srchDate = bun.getString("dID");
	        
	        
              UserDataHelper mdbh = new UserDataHelper(this.getApplicationContext());
	      final SQLiteDatabase db = mdbh.getWritableDatabase(); 
	       
	      
	      
	     int names[] = {android.R.id.text1,android.R.id.text2};
	 		final Cursor c = db.rawQuery("Select _id,blk, Date, Flt ||' '|| frm ||' ' || dest AS Fdata from fltlog where Date >= '" + srchDate  +  "' and land = 'DHD FLT' order by Date ASC"  ,null);
	 		c.moveToFirst();
	 		int blkHours=0,blkMin=0,iBlockHours=0,iBlockMin=0;
	 		String sblkHours;
	 		String[] sblkTempHr;
	 		
	 		 for (int i = 0; i <= c.getCount() - 1;i++) {	
	 		//calc BLK and FLT time here
	 			sblkHours=c.getString(c.getColumnIndex("blk"));
	 			sblkTempHr = sblkHours.split(":");
	 			blkHours = Integer.parseInt(sblkTempHr[0]);
	 			blkMin = Integer.parseInt(sblkTempHr[1]);
	 			
	 			
	 			
	 			
	 			iBlockHours=iBlockHours+blkHours;
	 			iBlockMin=iBlockMin+blkMin;
	 			
	 			
	 			
	 			c.moveToNext();
	 		 }
	 		 	int totalBlkMin = (iBlockHours * 60) + iBlockMin;
	 		 	int totalBlkHours = (totalBlkMin/60);
	 		 	totalBlkMin = (totalBlkMin - (totalBlkHours * 60));
	 		 	
	 		 	
	 		 
	 		 	TextView ttl = (TextView)findViewById(R.id.footer);
	 		 	String sTBH,sTBM;
	 		 	
	 		 	sTBH = "" + totalBlkHours;
	 		 	sTBM = "" + totalBlkMin;
	 		 	
	 		 	
	 		 	
	 		    
	 		     
	 		     
	 		      
	 		     if ((totalBlkMin < 10) && (totalBlkHours < 10)){
	 		 	    ttl.setText(ttl.getText().toString()+ " Total Blk Time: "+  " " +  " " + "0" + sTBH + ":0" + sTBM);
	 		 	      }
	 		 		      if ((totalBlkMin < 10) && (totalBlkHours > 9)){
	 		 		    	ttl.setText(ttl.getText().toString() + " Total Blk Time: "+  " "  + sTBH + ":0" + sTBM);  
	 		 		  }
	 		 		      if  ((totalBlkMin > 10) && (totalBlkHours > 9)){
	 		 		    	ttl.setText(ttl.getText().toString()  + " Total Blk Time: "+  " "  + sTBH + ":"  + sTBM); 
	 		 		  }
	 		 		      if ((totalBlkMin > 10) && (totalBlkHours < 10)){
	 		 		    	ttl.setText(ttl.getText().toString() + " Total Blk Time: "+   " "  + "0" + sTBH + ":"  + sTBM);
	 		 		    	  }
	 		  	  	 
	 		final ListAdapter adapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,c, 
		 			new String[] {"Date","Fdata"}, 
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
	 		b.putLong("pID", c.getLong(c.getColumnIndex("_id")));
	 		Intent myIntent = new Intent(getBaseContext(),fltupdate.class);
	 		myIntent.putExtras(b);
	 		
            startActivityForResult(myIntent, 0);
	 	//Toast.makeText(getBaseContext(),"item" + id + " checked",Toast.LENGTH_SHORT).show();
	 	}
	 	});
	 	
	 	
	    }
public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu){
	    	
	    	
	    	
	    	//	menu.add("Get_Egrid")
	    		
	    		//	.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
	                			
	    		MenuInflater inflater=getSupportMenuInflater();
	    		inflater.inflate(R.menu.fltreportsmenu, menu);
	    		return super.onCreateOptionsMenu(menu);
	    		}

	    		public boolean onOptionsItemSelected (com.actionbarsherlock.view.MenuItem item ){
	    			switch (item.getItemId()){
	    			
	    			case R.id.home :
	    				
	    				Intent myIntent1 = new Intent(getBaseContext(), PocketCal20.class);	
	    				startActivityForResult(myIntent1, 0);
	    			return true;	
	    			case R.id.back :
		    			Intent myIntent3 = new Intent(getBaseContext(), fltreports2.class);	
		    			startActivityForResult(myIntent3, 0);
	    			return true;	
	    		
	    			
		
	    			}
		return false;


	    		}

    
	}
	   




