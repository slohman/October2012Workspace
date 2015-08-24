package com.yctc.alpaware;

import android.app.Activity;
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

public class freqs extends SherlockActivity{

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.freq);
	    
	    
	    Button btnListAll = (Button)findViewById(R.id.btnListall);
	    btnListAll.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View view) {
	           // launch listview that selects all espn freqs
	        	Bundle b = new Bundle();
	        	b.putString("Type", "ListAll");
	        	b.putString("State", "No Filter");
	        	Intent myIntent2 = new Intent(getBaseContext(), FreqListAll.class);	
	        	myIntent2.putExtras(b);
				startActivityForResult(myIntent2, 0);
	        }});;
	        
	        
		    Button btnFilter = (Button)findViewById(R.id.btnByState);
		    btnFilter.setOnClickListener(new View.OnClickListener() {
		        public void onClick(View view) {
		           // launch listview that filters by State input from Dialog box
		        	
		     
			
			  	   
				    		
		        	 Builder builder =  new AlertDialog.Builder(freqs.this);
	      			 FrameLayout fl = new FrameLayout(freqs.this); 
	      			final EditText input = new EditText(freqs.this);
	      		    input.setGravity(Gravity.CENTER);
	      			      fl.addView(input, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
	      			      input.setHint("Enter State ID (CA)");
	      			     
	      			          
	      			       builder.setView(fl);
	      			       builder.setTitle("Enter 2-Letter State ID");
	      			          
	      			        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
	      			    	      public void onClick(DialogInterface d, int which) {
	      			    	    	
	      			    	 	   String state = input.getText().toString();
	      			    	 	   d.dismiss();
	      			    		   
	      			    		 Bundle bun = new Bundle();
	 		  	      			bun.putString("Type", "Filter");
	 		  	      			bun.putString("State", state);
	 		  	    	 		Intent myIntent = new Intent(getBaseContext(), FreqListAll.class);
	 		  	    	 		myIntent.putExtras(bun);
	 		  	  	 		    startActivityForResult(myIntent, 0);
	      			    		
	      			          }});
	      			        
	      			        
				  	   builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
			 				public void onClick(DialogInterface d, int which) {
			 				d.dismiss();
			 				Intent myIntent = new Intent(getBaseContext(), freqs.class);
		  	    	 		 startActivityForResult(myIntent, 0);
			 				 }});
			  	 
			  	     builder.create().show();
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
    				
    			super.onBackPressed();
    			return true;
    			
    			
  	  			   
  	  			   
  	  		   }
    		
    		
    	
    			return false;

}	
}

	
		
		        	
		  

	
