package com.yctc.alpaware;

import java.io.BufferedWriter;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;

import com.actionbarsherlock.view.MenuInflater;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;




public class explogmaint extends SherlockActivity{
	
	 public static final int Main_Menu =0;
	 public static final int Maint_Menu = 1;
	 String dbase="EXPDATA.db";
	
	String sMsg,result;
	String DBPath = "/data/data/com.yctc.alpaware/databases/";
	
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.explogmaint);
        
     
	
	    	
	
	 Button bCopyCSV = (Button) findViewById(R.id.sendcsvexp);
		bCopyCSV.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View view) {
	    		    		 
	    		try {
					sendcsv(dbase);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	    	}}); } 
	       
          
        public void sendcsv(String dbase)throws IOException {
        //select all database records then build SD file
        	String fltdata;
        	File sd = Environment.getExternalStorageDirectory();
        	
        	UserDataHelper mdbh = new UserDataHelper(this.getApplicationContext());
   	        final SQLiteDatabase db = mdbh.getWritableDatabase(); 
   	       
   	      
   	      
   	     
   	 		final Cursor c = db.rawQuery("Select  * from EXP  order by exp_Date ASC" ,null);		  
   	 		startManagingCursor(c);
   	 		
   	 		if (c.getCount()< 1 ){
   	 			
   	 			result = "No records on File";
   	 		}else{
   	 		
   	 		c.moveToFirst();
   	 		fltdata="_id,Date,Amount,Category,Comments" + "\r\n";
   	 	for (int i = 0; i <= c.getCount()-1;i++) {
   	 		 fltdata=fltdata + c.getString(c.getColumnIndex("_id")) + "," +
   	 		c.getString(c.getColumnIndex("exp_DATE")) +  "," +
   	 	    c.getString(c.getColumnIndex("exp_AMT")) +  "," +
   	 	    c.getString(c.getColumnIndex("exp_CAT")) +  "," +
   	 	c.getString(c.getColumnIndex("exp_COMM"))  + "," +
   	 	    c.getString(c.getColumnIndex("exp_CITY")) +   "\r\n"; 		
   	 		c.moveToNext();
   	 	}	
   	 try {
   		 sd = Environment.getExternalStorageDirectory();
   	   
   	    if (sd.canWrite()){
   	        File csvfile = new File(sd + "/PocketCalDatabases/", "explog.csv");
   	        FileWriter gpxwriter = new FileWriter(csvfile);
   	        BufferedWriter out = new BufferedWriter(gpxwriter);
   	        out.write(fltdata);
   	        out.close();
   	    }
   	} catch (IOException e) {
   		utilities util = new utilities();
   		Context ctx = getApplicationContext();
   		util.showaction( " Could not write file to PocketCalDatabses Dir " ,ctx);
   	}
   	String email = (String) "";
	Intent sendIntent = new Intent(Intent.ACTION_SEND);
	sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Copy of Explog DB");
	String[] recipients = new String[]{email};
	sendIntent.putExtra(android.content.Intent.EXTRA_EMAIL, recipients);
	
	sendIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse
			("file://" + sd + "/PocketCalDatabases/explog.csv"));
	sendIntent.setType("plain/text"); 
	startActivity(Intent.createChooser(sendIntent, "Send Mail ...."));
   	 		}
   	 		
        } // end of method
  
        public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu){
	    	
	    	
	    	
	    	//	menu.add("Get_Egrid")
	    		
	    		//	.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
	                			
	    		MenuInflater inflater=getSupportMenuInflater();
	    		inflater.inflate(R.menu.expmainmenu, menu);
	    		return super.onCreateOptionsMenu(menu);
	    		}
	    	
	    
	    
	    
	    
	    public boolean onOptionsItemSelected (com.actionbarsherlock.view.MenuItem item ){
	    			switch (item.getItemId()){
	    			
	    			
	    			
	    			case R.id.home :
	    				Intent myIntent2 = new Intent(getBaseContext(), PocketCal20.class);	
	    				startActivityForResult(myIntent2, 0);
	    				
	    			case R.id.back :
	    				
	    				Intent myIntent1 = new Intent(getBaseContext(), PocketCal20.class);	
	    				startActivityForResult(myIntent1, 0);
	    			return true;
	    			
	    			
	  	  			   
	  	  			   
	  	  		   }
	    		
	    		
	    	
	    			return false;

	}
        
         }
