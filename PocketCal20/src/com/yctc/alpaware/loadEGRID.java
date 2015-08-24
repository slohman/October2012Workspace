package com.yctc.alpaware;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

public class loadEGRID extends Activity{

	int row=0;
	String tmpEGRIDROW[];
	 String EGRIDROW = null;
	 public static final int Main_Menu =0;
	
	
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loadegrid);
        
        UserDataHelper mdbh = new UserDataHelper(this.getApplicationContext());
	    	 	final SQLiteDatabase db = mdbh.getWritableDatabase();
	
	    	 	
	    	 	
         	         

	    	 	 Button bloadEGRID1 = (Button) findViewById(R.id.loadegriddata1);
	    	     	bloadEGRID1.setOnClickListener(new View.OnClickListener() {
	    	         	public void onClick(View view) {
	    	         row = 1; 		
	    	         		
	    	 
	    	 	UpdateEgridDatabase(db,row);
	    	 	
	    	 	}});
	    	     	 Button bloadEGRID2 = (Button) findViewById(R.id.loadegriddata2);
		    	     	bloadEGRID2.setOnClickListener(new View.OnClickListener() {
		    	         	public void onClick(View view) {
		    	         row = 2; 		
		    	         		
		    	 
		    	 	UpdateEgridDatabase(db,row);
		    	 	
		    	 	}});
						
		    	     	Button bloadEGRID3 = (Button) findViewById(R.id.loadegriddata3);
		    	     	bloadEGRID3.setOnClickListener(new View.OnClickListener() {
		    	         	public void onClick(View view) {
		    	         row = 3; 		
		    	         		
		    	 
		    	 	UpdateEgridDatabase(db,row);
		    	 	
		    	 	}});
		    	     	Button bloadEGRID4 = (Button) findViewById(R.id.loadegriddata4);
		    	     	bloadEGRID4.setOnClickListener(new View.OnClickListener() {
		    	         	public void onClick(View view) {
		    	         row = 4; 		
		    	         		
		    	 
		    	 	UpdateEgridDatabase(db,row);
		    	 	
		    	 	}});
		    	     	Button bloadEGRID5 = (Button) findViewById(R.id.loadegriddata5);
		    	     	bloadEGRID5.setOnClickListener(new View.OnClickListener() {
		    	         	public void onClick(View view) {
		    	         row = 5; 		
		    	         		
		    	 
		    	 	UpdateEgridDatabase(db,row);
		    	 	
		    	 	}});
	      

	 
	    	         	
	    	     	}
	
	
private void UpdateEgridDatabase(final SQLiteDatabase db, final int row ){
	final EditText input = new EditText(this);
		final FrameLayout fl = new FrameLayout(this);  
		 Builder b =  new AlertDialog.Builder(loadEGRID.this);
         b.setView(fl);
         b.setTitle("Input EGRID Data " + row);
		
		input.setGravity(Gravity.CENTER);
		    fl.addView(input, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
		    input.setHint("ABCDEFGHIJ");
		    input.setInputType(1);
		   
		 
	         
	           
		  b.setPositiveButton("OK", new DialogInterface.OnClickListener(){
	  	 	public void onClick(DialogInterface d, int which) {
	  	 		d.dismiss();
	  	 		
	  	 		// get date and execute SQL here....
	  	 		EGRIDROW = input.getText().toString();
	  	 	 if (EGRIDROW.length() ==10){

	  	 	ContentValues rowvalues = new ContentValues();
	  	 	 
	  	 	
	  	 	
	  	 	
	  	 			 rowvalues.put("A",  EGRIDROW.substring(0, 1));
	  	 			 rowvalues.put("B",  EGRIDROW.substring(1, 2));
	  	 			 rowvalues.put("C",  EGRIDROW.substring(2, 3));
	  	 			 rowvalues.put("D",  EGRIDROW.substring(3, 4));
	  	 			 rowvalues.put("E",  EGRIDROW.substring(4, 5));
	  	 			 rowvalues.put("F",  EGRIDROW.substring(5, 6));
	  	 			 rowvalues.put("G",  EGRIDROW.substring(6, 7));
	  	 			 rowvalues.put("H",  EGRIDROW.substring(7,8));
	  	 			 rowvalues.put("I",  EGRIDROW.substring(8, 9));
	  	 			 rowvalues.put("J",  EGRIDROW.substring(9,10));
	  	 			 db.update("egrid", rowvalues,"rowid" + "=" + row , null);
	  	 			 showaction("Row" + row + " entered ");
	  	 	 }else{
	  	 		 showaction("Input Error! Try Again");
	  	 		Intent myIntent = new Intent(getBaseContext(), loadEGRID.class);
		 		startActivityForResult(myIntent, 0);
	  			  
	  			  }}});	  
	  			  
	   					
		b.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface d, int which) {
				d.dismiss();
				Intent myIntent = new Intent(getBaseContext(), filemaintmain.class);
		 		startActivityForResult(myIntent, 0);
				 }});

	   b.create().show();}


   	     	 




public boolean onCreateOptionsMenu(Menu menu){
	menu.add(0,Main_Menu,0,"Main Menu");
	
	return true;
	}

public boolean onOptionsItemSelected (MenuItem item ){
	switch (item.getItemId()){
	case Main_Menu :
		
		Intent myIntent = new Intent(getBaseContext(), PocketCal20.class);	
		startActivityForResult(myIntent, 0);
		
	return true;
	
	}
	return false;
	}
public void showaction(String msg){
	Context context = getApplicationContext();
	int duration = Toast.LENGTH_SHORT;

	Toast toast = Toast.makeText(context, msg, duration);
	toast.show();
	}	






}


	 
	 




	

