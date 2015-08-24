package com.yctc.alpaware;

import java.io.File;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;


public class tripdelete  extends Activity {
	
	String filename; 

	@Override
    public void onCreate(Bundle Icicle) {
	 super.onCreate(Icicle);
     setContentView(R.layout.tripviewer);
     
     
     
      Bundle b = this.getIntent().getExtras();
  
     final String prg = b.getString("prg");
     final String show = b.getString("show");
    
     
     
     Builder builder =  new AlertDialog.Builder(this);
		
		 builder.setTitle("Are You Sure You Want to Delete This File ?");
		
		  builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
		    	      public void onClick(DialogInterface d, int which) {
		    	    	  AlpaDataBaseHelper mdbh2 = new AlpaDataBaseHelper(tripdelete.this);
					 	     final SQLiteDatabase db2 = mdbh2.getWritableDatabase(); 
		    	 	
		    	 	   d.dismiss();
		    	 	String tsql = "Delete from trips where pairing = '" + prg + "' and showdate = '" + show + "'";
		   			db2.execSQL(tsql );
		   			db2.close();
		   			Intent myIntent = new Intent(getBaseContext(), triplist.class);
	 	 		    startActivityForResult(myIntent, 0);
		    	      }});
		       
		     					
		      builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface d, int which) {
					d.dismiss();
					Intent myIntent = new Intent(getBaseContext(), triplist.class);
	 	 		    startActivityForResult(myIntent, 0);
	 	 		    }});
		 
		     builder.create().show();
	      }
     
   
     
     


}


