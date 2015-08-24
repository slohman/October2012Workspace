package com.yctc.alpaware;


import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;

public class explistout extends SherlockActivity{

	@Override
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.explistout);
		final FrameLayout fl = new FrameLayout(this);
	  	      final EditText input = new EditText(this);
	  	      
	  	      
		Button r1 = (Button) findViewById(R.id.listexps);
	    r1.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View view) {
	    		// dialog to get base search date here
	    		
	  	      input.setGravity(Gravity.CENTER);
	  	      fl.addView(input, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
	  	      input.setHint("Enter Start Date yyyy-mm-dd");
	  	      
	  	    Calendar c = Calendar.getInstance();
	  	    SimpleDateFormat month_date = new SimpleDateFormat("yyyy-MM-dd");
  		 	String monthDate = month_date.format(c.getTime());
  		 	input.setText(monthDate);
	  	  
	  	           Builder b =  new AlertDialog.Builder(explistout.this);
	  	           b.setView(fl);
	  	           b.setTitle("Date Input");
	  	      
	  	      
	  	     b.setPositiveButton("OK", new DialogInterface.OnClickListener(){
	  	    	 	public void onClick(DialogInterface d, int which) {
	  	    	 		d.dismiss();
	  	    	 		// get date and execute SQL here....
	  	    	 		String repDate = input.getText().toString();
	  	    	 		Bundle bun = new Bundle();
	  	      			bun.putCharSequence("dID", repDate);
	  	    	 		Intent myIntent = new Intent(getBaseContext(), explist.class);
	  	    	 		myIntent.putExtras(bun);
	  	  	 		
	  	              startActivityForResult(myIntent, 0);
	  	    	 													  }
	  	     															}
	  	     					);
	  	     					
	 		b.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
	 				public void onClick(DialogInterface d, int which) {
	 				d.dismiss();
	 																  }
	  	
	 																		  }
	 		                  );
	  	 
	  	     b.create().show();

		 		
	            
	    	}});
	    
	    Button r2 = (Button) findViewById(R.id.expbyCity);
	    r2.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View view) {
	    		// dialog to get base search date here
	    		
	  	      input.setGravity(Gravity.CENTER);
	  	      fl.addView(input, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
	  	      input.setHint("Enter City Code");
	  	      
	  	   
	  	  
	  	           Builder b =  new AlertDialog.Builder(explistout.this);
	  	           b.setView(fl);
	  	           b.setTitle("City Input");
	  	      
	  	      
	  	     b.setPositiveButton("OK", new DialogInterface.OnClickListener(){
	  	    	 	public void onClick(DialogInterface d, int which) {
	  	    	 		d.dismiss();
	  	    	 		// get date and execute SQL here....
	  	    	 		String srchCity = input.getText().toString();
	  	    	 		Bundle bun = new Bundle();
	  	      			bun.putCharSequence("dID", srchCity);
	  	    	 		Intent myIntent = new Intent(getBaseContext(), explistbycity.class);
	  	    	 		myIntent.putExtras(bun);
	  	  	 		
	  	              startActivityForResult(myIntent, 0);
	  	    	 													  }
	  	     															}
	  	     					);
	  	     					
	 		b.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
	 				public void onClick(DialogInterface d, int which) {
	 				d.dismiss();
	 																  }
	  	
	 																		  }
	 		                  );
	  	 
	  	     b.create().show();

		 		
	            
	    	}});
		}
	
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
    				
    				Intent myIntent3 = new Intent(getBaseContext(), explistout.class);	
    				startActivityForResult(myIntent3, 0);
    			return true;
    			
    			
  	  			   
  	  			   
  	  		   }
    		
    		
    	
    			return false;

}
}