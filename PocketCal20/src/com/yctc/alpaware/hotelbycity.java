package com.yctc.alpaware;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;

import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.view.Gravity;

import android.widget.EditText;
import android.widget.FrameLayout;


public class hotelbycity extends Activity{
	

	

	@Override
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.genericlist);
	        
		final FrameLayout fl = new FrameLayout(this);
	  	      final EditText input = new EditText(this);
	
	  	   
		    		
		  	      input.setGravity(Gravity.CENTER);
		  	      fl.addView(input, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
		  	      input.setHint("Input Ramp ID");
		  	      input.setInputType(4096);
		  	           Builder b =  new AlertDialog.Builder(hotelbycity.this);
		  	           b.setView(fl);
		  	           b.setTitle("Input Ramp 3-Letter ID");
		  	           
		  	      
		  	     b.setPositiveButton("OK", new DialogInterface.OnClickListener(){
		  	    	 	public void onClick(DialogInterface d, int which) {
		  	    	 		d.dismiss();
		  	    	 		// get date and execute SQL here....
		  	    	 		String HID = input.getText().toString();
		  	    	 		Bundle bun = new Bundle();
		  	      			bun.putString("hID", HID);
		  	    	 		Intent myIntent = new Intent(getBaseContext(),hotellistout.class);
		  	    	 		myIntent.putExtras(bun);
		  	  	 		
		  	              startActivityForResult(myIntent, 0);
		  	    	 													  }
		  	     															}
		  	     					);
		  	     					
		 		b.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
		 				public void onClick(DialogInterface d, int which) {
		 				d.dismiss();
		 				Intent myIntent = new Intent(getBaseContext(), ramphotelmain.class);
	  	    	 		
	  	  	 		
	  	              startActivityForResult(myIntent, 0);
		 				 }
		  			  }
		 		                  );
		  	 
		  	     b.create().show();

			 		
		            
		    	};
	
	
	
}

