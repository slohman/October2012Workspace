package com.yctc.alpaware;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;


       
	        
	public class fltreportlists1 extends SherlockActivity {
		
		
		public Cursor cu;
		Context mContext;
		
		public static final int Main_Menu =0;
		public static final int Menu_1 =1;
		
		
 		
		/** Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle Icicle) {
	        super.onCreate(Icicle);
	        setContentView(R.layout.fltlistblockhours);
	        
	        
	        Bundle bun = this.getIntent().getExtras();  
	        String srchDate = bun.getString("dID");
	        String srchType = bun.getString("dIDD");
	        String qry;
	        
	       
	        
	        
	        UserDataHelper mdbh = new UserDataHelper(this.getApplicationContext());
	      final SQLiteDatabase db = mdbh.getWritableDatabase(); 
	       
	      
	      
	    int names[] = {android.R.id.text1,android.R.id.text2};
	    
	    if (srchType.equals("empty"))
	    {  qry = "Select _id,blk,Date,Flt ||' '|| frm ||' ' ||  dest AS Fdata from fltlog where Date >= '" + srchDate  +  "'  and land <> 'DHD FLT'  order by Date ASC";
	    }else {	
	    qry = "Select _id,blk,Date,Flt ||' '|| frm ||' ' ||  dest AS Fdata from fltlog where Date >= '" + srchDate  +  "'  and land <> 'DHD FLT' and typ like '" + srchType + "' order by Date ASC";
	    }	
	    
	    final Cursor cu = db.rawQuery(qry,null);
	 		cu.moveToFirst();
	 		final Cursor cu1;
	 		if (srchType.equals("empty")) {
	 			 cu1 = db.rawQuery("Select _id,land,date from fltlog where Date >= '" + srchDate  +  "'   and land <> 'DHD FLT'  order by Date ASC" ,null);	
	 		}else {
	 		  cu1 = db.rawQuery("Select _id,land,date from fltlog where Date >= '" + srchDate  +  "'   and land <> 'DHD FLT' and typ like '" + srchType + "'  order by Date ASC" ,null);
	 		}
	 		
	 		cu1.moveToFirst();
	 		int blkHours=0,blkMin=0,iBlockHours=0,iBlockMin=0;
	 		String sblkHours;
	 		String[] sblkTempHr;
	 		 int Clandings=0;
 		 int Flandings = 0;
 		String lnDpilot = "";
	 		
	 		
	 		 for (int i = 0; i <= cu.getCount() - 1;i++) {	
	 		//calc BLK and FLT time here
	 			sblkHours=cu.getString(cu.getColumnIndex("blk"));
	 			sblkTempHr = sblkHours.split(":");
	 			blkHours = Integer.parseInt(sblkTempHr[0]);
	 			blkMin = Integer.parseInt(sblkTempHr[1]);
	 			
	 			
	 			
	 		
	 		lnDpilot= cu1.getString(cu1.getColumnIndex("land"));
	 		
	 		//above gets who did the landing and counts the total up for the time period
	 		
	 			if (lnDpilot.matches("Captain")) {
	 				Clandings = Clandings + 1;
	 				;
	 				
	 			}else {
	 				Flandings = Flandings + 1;
	 				
	 			}
	 			
	 			
	 			
	 			
	 			
	 			
	 			iBlockHours=iBlockHours+blkHours;
	 			iBlockMin=iBlockMin+blkMin;
	 			
	 			
	 			cu1.moveToNext();
	 			cu.moveToNext();
	 		 }
	 		 	int totalBlkMin = (iBlockHours * 60) + iBlockMin;
	 		 	int totalBlkHours = (totalBlkMin/60);
	 		 	totalBlkMin = (totalBlkMin - (totalBlkHours * 60));
	 		 	
	 		 	
	 		 
	 		 	TextView ttl = (TextView)findViewById(R.id.footer);
	 		 	String sTBH,sTBM;
	 		 	
	 		 	sTBH = "" + totalBlkHours;
	 		 	sTBM = "" + totalBlkMin;
	 		 	
	 		 	
	 		 	
	 		    
	 		     
	 		     
	 		      
	 		     if ((totalBlkMin < 10) && (totalBlkHours < 10)){
	 		 	    ttl.setText(ttl.getText().toString()+ " Total Blk Time & Landings: "+  " " +  " " + "0" + sTBH + ":0" + sTBM + "  "+"C:"+ Clandings + "  "+"F:"+ Flandings);
	 		 	      }
	 		 		      if ((totalBlkMin < 10) && (totalBlkHours > 9)){
	 		 		    	ttl.setText(ttl.getText().toString() + " Total Blk Time & Landings: "+  " "  + sTBH + ":0" + sTBM + "  "+"C:"+ Clandings + "  "+"F:"+ Flandings);  
	 		 		  }
	 		 		      if  ((totalBlkMin > 10) && (totalBlkHours > 9)){
	 		 		    	ttl.setText(ttl.getText().toString()  + " Total Blk Time & Landings: "+  " "  + sTBH + ":"  + sTBM + "  "+"C:"+ Clandings + "  "+"F:"+ Flandings); 
	 		 		  }
	 		 		      if ((totalBlkMin > 10) && (totalBlkHours < 10)){
	 		 		    	ttl.setText(ttl.getText().toString() + " Total Blk Time & Landings: "+   " "  + "0" + sTBH + ":"  + sTBM + "  "+"C:"+ Clandings + "  "+"F:"+ Flandings);
	 		 		    	  }
	 		 		      
	 		  	  	 
	 		final ListAdapter adapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,cu, 
		 			new String[] {"Date","Fdata"}, 
		 			names);		  
	 		  		
	 		 startManagingCursor(cu);
	 	
	 
	 		  	  	 
	 
	    
	 	ListView lv = (ListView)findViewById(android.R.id.list);
	 	lv.setAdapter(adapter);
	 	
	 	
	   
	  lv.setOnItemClickListener( new AdapterView.OnItemClickListener() {
	 	public void onItemClick(AdapterView<?> parent, View v, int position, long id)
	 	{
	 	// Display a messagebox.
	 		Bundle b = new Bundle();
	 		cu.moveToPosition(position);
	 		b.putLong("pID", cu.getLong(cu.getColumnIndex("_id")));
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
	   



