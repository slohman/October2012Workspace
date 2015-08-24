package com.yctc.alpaware;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ramphotelmain extends SherlockActivity{


	
	


	  @Override
	    public void onCreate(Bundle Icicle) {
	        super.onCreate(Icicle);
	        setContentView(R.layout.gthotlaunch);
	
	
	
	        Button bBycity= (Button)findViewById(R.id.findhotelByCity);
	         bBycity.setOnClickListener(new View.OnClickListener() {
	  	            public void onClick(View view) {
	  	                Intent myIntent = new Intent(view.getContext(), hotelbycity.class);
	  	                startActivityForResult(myIntent, 0);
	  	            }});;
	  	            
	  	        

	  		        Button blistAll= (Button)findViewById(R.id.listall);
	  		         blistAll.setOnClickListener(new View.OnClickListener() {
	  		  	            public void onClick(View view) {
	  		  	                Intent myIntent = new Intent(view.getContext(), hotellistall.class);
	  		  	                startActivityForResult(myIntent, 0);
	  		  	            }});;
	  		  	            
	  		  	        Button baddnew= (Button)findViewById(R.id.addnewHotel);
		  		         baddnew.setOnClickListener(new View.OnClickListener() {
		  		  	            public void onClick(View view) {
		  		  	                Intent myIntent = new Intent(view.getContext(), gthoteladdnew.class);
		  		  	                startActivityForResult(myIntent, 0);
		  		  	            }});;
	  		  	                
		 	  	      	 	  	            
}
	  public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu){
	    	
	    	
	    	
	    	//	menu.add("Get_Egrid")
	    		
	    		//	.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
	                			
	    		MenuInflater inflater=getSupportMenuInflater();
	    		inflater.inflate(R.menu.rampmenu, menu);
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
	    		}
}



