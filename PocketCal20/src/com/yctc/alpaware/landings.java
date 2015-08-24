package com.yctc.alpaware;


import java.text.SimpleDateFormat;

import java.util.Calendar;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;


public class landings extends SherlockActivity{
	public static final int Main_Menu =0;    
	public static final int Menu_1 = 1;
	
	private EditText mDateDisplay;
	private Button mPickDate;
	 
	    public int mMonth, mDay, mYear,iTotLandings;
	    public String sMonth,sYear, sDay, msg,sTotLandings,qry;
		String srchDate;
		
	    

	    static final int DATE_DIALOG_ID = 1;
	    
	    
	    @Override
		
		 
		
		public void onCreate(Bundle savedInstanceState) {
		    
			super.onCreate(savedInstanceState);
		    setContentView(R.layout.landings);
		   
		   
		
	        mDateDisplay = (EditText) findViewById(R.id.LandingStartDate);
	        mPickDate = (Button) findViewById(R.id.lDate);
	        final CheckBox c1 = (CheckBox) findViewById(R.id.Capt);
	        final CheckBox c2 = (CheckBox) findViewById(R.id.FO);
	        c1.setChecked(true);
	        
	        c1.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	                c2.setChecked(false);
	            }
	        });
	        c2.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	                c1.setChecked(false);
	            }
	        });
	       
	        mPickDate.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	                showDialog(DATE_DIALOG_ID);
	            }
	        });
	    
	       
	    
	    
	 // get the current date and put it in the date display
        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
       
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sStartDate = sdf.format(c.getTime());
        c.add(Calendar.DAY_OF_YEAR, -90);
        TextView t2  = (TextView)findViewById(R.id.datesrch);
         final String searchDate = sdf.format(c.getTime());
         t2.setText(searchDate);
        
       
        mDateDisplay.setText(sStartDate);
    
    
        final Button s1 = (Button) findViewById(R.id.calcLandings);
        s1.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        		
        		
        		CheckBox c1 = (CheckBox) findViewById(R.id.Capt);
        		
        		CheckBox c2 = (CheckBox) findViewById(R.id.FO);
        		
        		TextView t1  = (TextView) findViewById(R.id.datesrch);
        		
        		String searchDate = t1.getText().toString();
        		
        	  UserDataHelper mdbh = new UserDataHelper(getApplicationContext());
      	      final SQLiteDatabase db = mdbh.getWritableDatabase(); 
      	      
      	      
      	      
      	      
      	      if (c1.isChecked()){
      	    	  c2.setChecked(false);
      	    	 qry = "Select * from fltlog where Date >=  '" + searchDate + "' and land like 'Captain'" ;
      	      }
      	    if (c2.isChecked()){
      	    	c1.setChecked(false);
      	    	 qry = "Select * from fltlog where Date >= '" + searchDate + "'and land like 'FO'" ;  
      	      }
      	     Cursor cu = db.rawQuery(qry ,null);
      	 		cu.moveToFirst();
      	        if (cu.getCount() > 0) {
      	        	
      	        	iTotLandings = cu.getCount();
      	        	
      	        	
      	        } else {
      	        	iTotLandings = 0;
      	        }
      	      TextView ttlLand = (TextView) findViewById(R.id.totlandings);
      	      sTotLandings = Integer.toString(iTotLandings);
      	     ttlLand.setText(sTotLandings);
      		cu.close();
        	}});

       
	    }	    
	    	    
	    
	
	@Override
	protected Dialog onCreateDialog(int id) {
	    switch (id) {
	    case DATE_DIALOG_ID:
	        return new DatePickerDialog(this,
	                    mDateSetListener,
	                    mYear, mMonth, mDay);
	    }
	    return null;
	}
	

	
	
	// the callback received when the user "sets" the date in the dialog
	private DatePickerDialog.OnDateSetListener mDateSetListener =
	        new DatePickerDialog.OnDateSetListener() {

	            public void onDateSet(DatePicker view, int year, 
	                                  int monthOfYear, int dayOfMonth) {
	                mYear = year;
	                mMonth = monthOfYear;
	                mDay = dayOfMonth;
	                
	                Calendar cal = Calendar.getInstance();
	                cal.set(mYear, mMonth, mDay);
	                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	                
	               
	                String sStartDate = sdf1.format(cal.getTime());
	                mDateDisplay.setText(sStartDate);
	                cal.add(Calendar.DAY_OF_YEAR, -90);
	                String searchDate = sdf1.format(cal.getTime());
	                
	                TextView t2  = (TextView)findViewById(R.id.datesrch);
	                t2.setText(searchDate);// sets the invisible search start date field
	                
	                
	                //utilities dt = new utilities();
	                //String dateString = dt.calcDateString(mYear, mMonth, mDay);
	                
	                
	            }
	            
	        };
	
	       	
	
	
	@Override
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu){
    	
    	
    	
    	//	menu.add("Get_Egrid")
    		
    		//	.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
                			
    		MenuInflater inflater=getSupportMenuInflater();
    		inflater.inflate(R.menu.fltreportsmenu, menu);
    		return super.onCreateOptionsMenu(menu);
    		}

    		public boolean onOptionsItemSelected (com.actionbarsherlock.view.MenuItem item ){
    			switch (item.getItemId()){
    			
    			case R.id.home :
    				
    				Intent myIntent1 = new Intent(getBaseContext(), PocketCal20.class);	
    				startActivityForResult(myIntent1, 0);
    			return true;	
    			case R.id.back :
	    			Intent myIntent3 = new Intent(getBaseContext(), fltreports2.class);	
	    			startActivityForResult(myIntent3, 0);
    			return true;	
    		
    			
	
    			}
	return false;


    		}



}

