package com.yctc.alpaware;


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;


import java.util.GregorianCalendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class eightn24a extends Activity{
	
	String sblkHours,InDate,InTime,OutTime;
	
	public static final int Main_Menu =0;    
	public static final int Menu_1 = 1;
	 public void onCreate(Bundle Icicle) {
	        super.onCreate(Icicle);
	        setContentView(R.layout.eightn24);
	        
	        
	        Bundle b = this.getIntent().getExtras();
	        
	       
	    
	         InDate = b.getString("Dte");
	         InTime = b.getString("IN");
	         OutTime=b.getString("OUT");
	        
	        
	        
	        
	        final Context ctx = this;
	         
	         String[] sInTime;
	         String[] sOutTime;
	         String[] sDate;
	         sInTime = InTime.split(":");
	         sOutTime = OutTime.split(":");
	         sDate = InDate.split("-");
	         int iInHrs = Integer.parseInt(sInTime[0]);
	         int iOutHrs = Integer.parseInt(sOutTime[0]);
	         int iYr = Integer.parseInt(sDate[0]);
	         int iMo = Integer.parseInt(sDate[1]);
	         int iDay = Integer.parseInt(sDate[2]);
	         
	         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	         GregorianCalendar cal = new GregorianCalendar();
	         
	         EditText etsrchDate = (EditText) findViewById(R.id.blkdtinput);
	  	        EditText etsrchtime = (EditText) findViewById(R.id.blktimeinput);
	         if (iInHrs < iOutHrs){
	        	 // next day add i day to the date
	        
	         int newMonth = iMo -1;
	         cal.set(GregorianCalendar.MONTH,newMonth);
	         cal.set(GregorianCalendar.DAY_OF_MONTH,iDay);
	         cal.set(GregorianCalendar.YEAR,iYr);
	         cal.add(GregorianCalendar.DAY_OF_MONTH, 1);
	         
	         InDate = sdf.format(cal.getTime());
	         
	         
	         
	         	}else{
	         		
	        int newMonth = iMo -1;
		    cal.set(GregorianCalendar.MONTH,(newMonth));
		    cal.set(GregorianCalendar.DAY_OF_MONTH,iDay);
		    cal.set(GregorianCalendar.YEAR,iYr);
		    InDate = sdf.format(cal.getTime());
	         }
	
	       // Bundle bun = this.getIntent().getExtras();  
	   
	        
  	        
  	     
  	       

     
      
     
      etsrchDate.setText(InDate);
      etsrchtime.setText(InTime);
	    
	       
	        
	         final Button s1 = (Button) findViewById(R.id.calc8n24button);
	        
	    	 s1.setOnClickListener(new View.OnClickListener() {
	         	public void onClick(View view) {
	         		
	         	   String srchDate, srchTime ;
	         	  EditText etsrchDate = (EditText) findViewById(R.id.blkdtinput);
	    	        EditText etsrchtime = (EditText) findViewById(R.id.blktimeinput); 
	    	       final DecimalFormat timeFormat = new DecimalFormat("00");
	    	        srchDate = etsrchDate.getText().toString();
	    		        srchTime = etsrchtime.getText().toString();
	    		        
	    		   utilities u = new utilities();
	          
	         String hoursRemaining = u.calc3032in7(1,8,srchDate,srchTime,ctx);      
	    		        
	         TextView BLKR = (TextView) findViewById(R.id.blkremaining);
	     	BLKR.setText(hoursRemaining);	        
	    		        
	         	}});
	        	    
	
	final Button s2 = (Button) findViewById(R.id.btn30n7);
	 s2.setOnClickListener(new View.OnClickListener() {
     	public void onClick(View view) {
     		
     	   String srchDate, srchTime ;
     	  EditText etsrchDate = (EditText) findViewById(R.id.blkdtinput);
	        EditText etsrchtime = (EditText) findViewById(R.id.blktimeinput); 
	       final DecimalFormat timeFormat = new DecimalFormat("00");
	        srchDate = etsrchDate.getText().toString();
		        srchTime = etsrchtime.getText().toString();
		        
		   utilities u = new utilities();
      
     String hoursRemaining = u.calc3032in7(7,30,srchDate,srchTime,ctx);      
		        
     TextView BLKR = (TextView) findViewById(R.id.blkremaining);
 	BLKR.setText(hoursRemaining);	        
		        
     	}});
	 
	 final Button s3 = (Button) findViewById(R.id.btn32n7);
	 s3.setOnClickListener(new View.OnClickListener() {
     	public void onClick(View view) {
     		
     	   String srchDate, srchTime ;
     	  EditText etsrchDate = (EditText) findViewById(R.id.blkdtinput);
	        EditText etsrchtime = (EditText) findViewById(R.id.blktimeinput); 
	       final DecimalFormat timeFormat = new DecimalFormat("00");
	        srchDate = etsrchDate.getText().toString();
		        srchTime = etsrchtime.getText().toString();
		        
		   utilities u = new utilities();
      
     String hoursRemaining = u.calc3032in7(7,32,srchDate,srchTime,ctx);      
		        
     TextView BLKR = (TextView) findViewById(R.id.blkremaining);
 	BLKR.setText(hoursRemaining);	        
		        
     	}});
     
}
	 
	 
	 

public boolean onCreateOptionsMenu(Menu menu){
				menu.add(0,Main_Menu,0,"Main Menu");
				menu.add(0,Menu_1,0,"LogBook Menu");
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
		


	


}

