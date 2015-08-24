package com.yctc.alpaware;


import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.FrameLayout;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;


public class acequipnew extends SherlockActivity{

@Override
	
	 
	
	public void onCreate(Bundle savedInstanceState) {
	    
		super.onCreate(savedInstanceState);
	
	// create default Aircraft with everything false
	      
  AlpaDataBaseHelper mdbh = new AlpaDataBaseHelper(this.getApplicationContext());
    final SQLiteDatabase db = mdbh.getWritableDatabase();
	
	
    final FrameLayout fl = new FrameLayout(this);
      final EditText input = new EditText(this);

   
  		
	      input.setGravity(Gravity.CENTER);
	      fl.addView(input, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
	      input.setHint("Input A/C #");
	      input.setInputType(3);
	           Builder b =  new AlertDialog.Builder(acequipnew.this);
	           b.setView(fl);
	           b.setTitle("Enter New A/C #");
	           
	      
	     b.setPositiveButton("OK", new DialogInterface.OnClickListener(){
	    	 	public void onClick(DialogInterface d, int which) {
	    	 		d.dismiss();
	    	 		
	    	 		// get date and execute SQL here....
	    	 		int acNum = Integer.parseInt(input.getText().toString());
	    	 		
	    	 		try{
	    	 			 db.execSQL("Insert into ACDATA Values (" + null  + ", " +  acNum + "," + null  + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + ","  + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + null + " ) ");
	    	 		utilities util = new utilities();
	  	 		      Context ctx = getApplicationContext();
	    	 		util.showaction("Default A/C created",ctx);
	    	 		Intent  myIntent = new Intent(getBaseContext(), PocketCal20.class);
	    	 		startActivityForResult(myIntent, 0);
	    	 		
	    	 		}catch (Exception e){
	    	 			utilities util = new utilities();
		  	 		      Context ctx = getApplicationContext();
		    	 		util.showaction("Failed to Create New A/C",ctx);}
	    	 		}});
	     					
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
	    				
	    				super.onBackPressed();
	    			return true;	
	    		
	    		
	    	}
	    			return false;
	    		} 
   
	}