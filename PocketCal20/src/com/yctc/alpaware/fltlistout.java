package com.yctc.alpaware;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;


public class fltlistout extends SherlockActivity{
	
	
	@Override
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.fltlistout);
		final FrameLayout fl = new FrameLayout(this);
	  	      final EditText input = new EditText(this);
	  	      
	  	      
		Button r1 = (Button) findViewById(R.id.listflights);
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
	  	  
	  	           Builder b =  new AlertDialog.Builder(fltlistout.this);
	  	           b.setView(fl);
	  	           b.setTitle("Date Input");
	  	      
	  	      
	  	     b.setPositiveButton("OK", new DialogInterface.OnClickListener(){
	  	    	 	public void onClick(DialogInterface d, int which) {
	  	    	 		d.dismiss();
	  	    	 		// get date and execute SQL here....
	  	    	 		String repDate = input.getText().toString();
	  	    	 		Bundle bun = new Bundle();
	  	      			bun.putCharSequence("dID", repDate);
	  	    	 		Intent myIntent = new Intent(getBaseContext(), fltlist.class);
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
    		inflater.inflate(R.menu.meclistmenubar, menu);
    		return super.onCreateOptionsMenu(menu);
    		}

    		public boolean onOptionsItemSelected (com.actionbarsherlock.view.MenuItem item ){
    			switch (item.getItemId()){
    			
    			case R.id.home :
    				
    				Intent myIntent1 = new Intent(getBaseContext(), PocketCal20.class);	
    				startActivityForResult(myIntent1, 0);
    			return true;	
    			case R.id.back :
    				
    				Intent myIntent2 = new Intent(getBaseContext(), PocketCal20.class);	
    				startActivityForResult(myIntent2, 0);
    			return true;	
    		
    		
    	}
    			return false;
    		}}
	        	