package com.yctc.alpaware;

import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.app.SherlockActivity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;

import android.widget.EditText;
import android.widget.FrameLayout;



public class acequiplist extends SherlockActivity{

	
	@Override
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.fltlist);
	        
		final FrameLayout fl = new FrameLayout(this);
	  	      final EditText input = new EditText(this);
	
	  	   
		    		
		  	      input.setGravity(Gravity.CENTER);
		  	      fl.addView(input, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
		  	      input.setHint("Input A/C #");
		  	      input.setInputType(3);
		  	           Builder b =  new AlertDialog.Builder(acequiplist.this);
		  	           b.setView(fl);
		  	           b.setTitle("Select A/C #");
		  	           
		  	      
		  	     b.setPositiveButton("OK", new DialogInterface.OnClickListener(){
		  	    	 	public void onClick(DialogInterface d, int which) {
		  	    	 		d.dismiss();
		  	    	 		// get date and execute SQL here....
		  	    	 		int acNum = Integer.parseInt(input.getText().toString());
		  	    	 		Bundle bun = new Bundle();
		  	      			bun.putLong("dID", acNum);
		  	    	 		Intent myIntent = new Intent(getBaseContext(), aclistout.class);
		  	    	 		myIntent.putExtras(bun);
		  	  	 		
		  	              startActivityForResult(myIntent, 0);
		  	    	 													  }
		  	     															}
		  	     					);
		  	     					
		 		b.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
		 				public void onClick(DialogInterface d, int which) {
		 				d.dismiss();
		 				Intent myIntent = new Intent(getBaseContext(), PocketCal20.class);
	  	    	 		
	  	  	 		
	  	              startActivityForResult(myIntent, 0);
		 				 }
		  			  }
		 		                  );
		  	 
		  	     b.create().show();

			 		
		            
		    	};
	
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
		 	    		}
	
}
