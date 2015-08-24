package com.yctc.alpaware;

import android.app.Activity;

import android.content.Context;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


public class gthoteladdnew extends Activity{

	
	public static final int Save=0; 
	@Override
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.gthoteladdnew);
	  
	        EditText tID = (EditText)findViewById(R.id.cityid);
	        tID.setHint("City ID, XXX");
			 EditText tcityname = (EditText)findViewById(R.id.city);
			 tcityname.setHint("City Name");
		     EditText tstate = (EditText)findViewById(R.id.state);
		     tstate.setHint("State");
		      EditText tramptel = (EditText)findViewById(R.id.ramptel);
		      tramptel.setHint("Ramp Tel1 #");
		      EditText tramptel1 = (EditText)findViewById(R.id.ramptel1);
		      tramptel1.setHint("Ramp Tel #");
		     EditText tramptel2 = (EditText)findViewById(R.id.ramptel2);
		     tramptel2.setHint("Ramp Tel2 #");
		    EditText trampemail = (EditText)findViewById(R.id.rampemail);
		    trampemail.setHint("Ramp Email");
		     EditText thotel = (EditText)findViewById(R.id.hotelname);
		     thotel.setHint("Hotel Name");
		      EditText thotelnum = (EditText)findViewById(R.id.hotelnum);
		      thotelnum.setHint("Hotel #");
		     EditText tgt = (EditText)findViewById(R.id.gtinfo);
		     tgt.setHint("GT Contact #");
		     EditText tnotes = (EditText)findViewById(R.id.notes);
		     tnotes.setHint("Notes");		      	       

	}
	    	public boolean onCreateOptionsMenu(Menu menu){
	    		menu.add(0,Save,1,"Save Record");
	    		
	    		return true;
	    	}
	
	    	public boolean onOptionsItemSelected (MenuItem item){
	    		switch (item.getItemId()){
	    		case Save:
	    			savecity();
	    			return true;
	    		}
	    		return false;


}
	public void savecity(){
		
		 AlpaDataBaseHelper mdbh = new  AlpaDataBaseHelper(this.getApplicationContext());
	      final SQLiteDatabase db = mdbh.getWritableDatabase(); 
	
		 EditText tID = (EditText)findViewById(R.id.cityid);
		 String ID = tID.getText().toString();
	        EditText tcityname = (EditText)findViewById(R.id.city);
	        String cityname = tcityname.getText().toString();
	        EditText tstate = (EditText)findViewById(R.id.state);
	        String state = tstate.getText().toString();
	        EditText tramptel = (EditText)findViewById(R.id.ramptel);
	        String ramptel = tramptel.getText().toString();
	        EditText tramptel1 = (EditText)findViewById(R.id.ramptel1);
	        String ramptel1 = tramptel1.getText().toString();
	        EditText tramptel2 = (EditText)findViewById(R.id.ramptel2);
	        String ramptel2 = tramptel2.getText().toString();
	        EditText trampemail = (EditText)findViewById(R.id.rampemail);
	        String rampemail = trampemail.getText().toString();
	        EditText thotel = (EditText)findViewById(R.id.hotelname);
	        String hotel = thotel.getText().toString();
	        EditText thotelnum = (EditText)findViewById(R.id.hotelnum);
	        String hotelnum = thotelnum.getText().toString();
	        EditText tgt = (EditText)findViewById(R.id.gtinfo);
	        String gt = tgt.getText().toString();
	        EditText tnotes = (EditText)findViewById(R.id.notes);
	        String notes= tnotes.getText().toString();
	
	
	        try {
	        	String SQL = "Insert into  hotel Values (" + null  + ",'" + ID + "'," + "'" + cityname + "'," + "'" + state + 
    			 "'," + "'" + ramptel  + "'," + "'" + rampemail + "'," + "'" + hotel + "'," + "'" + hotelnum + "'," + 
    			"'" + gt + "'," + "'" + notes + "'," + "'" +	 ramptel1 + "'," + "'" +   ramptel2  + "')" ;
	        	db.execSQL(SQL);		  
	        		
	        	 Context ctx = getApplicationContext();
	     		utilities util = new utilities();
	        	util.showaction("Record Added!", ctx);
	        			 		
	        			
	        				}catch (Exception e){
	        					Context ctx = getApplicationContext();
	        				utilities util = new utilities();
	        				util.showaction("Add Record Failed!" + " " + e.getStackTrace(),ctx);
	        				Intent myIntent3 = new Intent(getBaseContext(), ramphotelmain.class);	
	        				startActivityForResult(myIntent3, 0);
	        					
	        				}
	
	
	
	}    	
	
	
	
	
	
	}
