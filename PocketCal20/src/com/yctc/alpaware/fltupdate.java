package com.yctc.alpaware;


	import java.util.Calendar;

import com.yctc.alpaware.PocketCal.FLTLOG;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.Bundle;
import android.text.InputType;

import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;


public class fltupdate extends SherlockActivity {
	
		private EditText mDateDisplay;
	    private Button mPickDate;

	    public int mYear;
	    public int mMonth;
	    public int mDay;
	    public String sDay;
	    public String sMonth;
	    public String sYear;
	    public String msg;
	    public String selectLand;
	    public String selectTyp;
	    static final int DATE_DIALOG_ID = 1;
	    
		
	    
	    public static final int Update_flt=0;   
		public static final int Delete_flt = 1;
		public static final int FltLog_Main= 2;
		public static final int Main_Menu = 3;
		public static final int eightn24 = 4;
		
		
		
		
		String sTailNo = "";
		String sfltno = "";
		String soutdate,sindate,soutmonth,soutyr,soutdy,sinmonth,sindy,sinyr;
		String sFROM,sTO, sOUT,sIN,sBLK,sBFLT,sLAND,sTYP,sCOMM,sCARGO;

	
		
		
		
		
		
		
		@Override
		public void onCreate(Bundle savedInstanceState) {
			
		super.onCreate(savedInstanceState);
		    setContentView(R.layout.fltlupdate1);
		    Bundle b = this.getIntent().getExtras();  
	        Long FlightID = b.getLong("pID");
	        
	        
	        UserDataHelper fldbh = new UserDataHelper(this.getApplicationContext());
		      SQLiteDatabase db = fldbh.getWritableDatabase(); 
		    
		      

		      SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		        qb.setTables(FLTLOG.FLTLOGTABLE_NAME);
		        qb.appendWhere(FLTLOG.FLTLOG_ID + "=" + FlightID);
		        String Cols[] = { FLTLOG.FLTLOG_ID + "," + FLTLOG.FLTLOG_Date + "," + FLTLOG.FLTLOG_flt + "," + FLTLOG.FLTLOG_ac +
		        				"," + FLTLOG.FLTLOG_from + "," + FLTLOG.FLTLOG_to + "," + FLTLOG.FLTLOG_out + 
		        				"," +  FLTLOG.FLTLOG_in +
		        				"," + FLTLOG.FLTLOG_blk + ","  + FLTLOG.FLTLOG_land +
		        				"," + FLTLOG.FLTLOG_typ + "," + FLTLOG.FLTLOG_cargo + "," + FLTLOG.FLTLOG_comm};
		       
		        
		 		Cursor cu = qb.query(db,Cols,null,null,null,null,FLTLOG.DEFAULT_SORT_ORDER);		  
		 		 		
		 		 startManagingCursor(cu);
		 		 cu.moveToFirst();
		 		 
		 		 
		    // load fields
		 		 
		 		 updatefields(cu);
		 		 
		 		 // set keyboard input type
		 		EditText out = (EditText) findViewById(R.id.Et_out);	
		 		out.setInputType(InputType.TYPE_TEXT_VARIATION_URI);
		 		EditText in = (EditText) findViewById(R.id.Et_in);	
		 		in.setInputType(InputType.TYPE_TEXT_VARIATION_URI);
		 		
		 		
		 		
		 		
		    EditText etFLT = (EditText) findViewById(R.id.Et_in);
		       etFLT.setOnFocusChangeListener(new OnFocusChangeListener()    {
		    	   public void onFocusChange(View v, boolean hasFocus) {
		    	   
		    	   if (hasFocus==true)
		    	   {
		    	     //Do nothing
		    	   }
		    	   else
		    	   {
		    	      updateTimes();
		    	   }
		    	 }
		    	     });
		       
	        Spinner s = (Spinner) findViewById(R.id.Et_typ);
		    ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(
		            this, R.array.aircraft, android.R.layout.simple_spinner_item);
		    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		    s.setAdapter(adapter);
		    
		    if (selectTyp.matches("757")){
		      	 s.setSelection(0);}
		      if (selectTyp.matches("MD11")){
			      	 s.setSelection(1);}
		      if (selectTyp.matches( "MD10")){
			      	 s.setSelection(2);}
		      if (selectTyp.matches("&&&")){
			      	 s.setSelection(3);}
		      if (selectTyp.matches("A300")){
			      	 s.setSelection(4);}
		      if (selectTyp.matches("A310")){
			      	 s.setSelection(5);}
		      if (selectTyp.matches("767")){
			      	 s.setSelection(6);}
		     
		    
		    Spinner s1 = (Spinner) findViewById(R.id.Et_land);
		    ArrayAdapter<?> adapter1 = ArrayAdapter.createFromResource(
		            this, R.array.pilots, android.R.layout.simple_spinner_item);
		    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		    s1.setAdapter(adapter1);
		   
		      
		      if (selectLand.matches("Captain")){
		      	 s1.setSelection(0);}
		      if (selectLand.matches("FO")){
			      	 s1.setSelection(1);}
		      if (selectLand.matches("DHD FLT")){
			      	 s1.setSelection(2);}
		    
		 // capture our View elements
	        mDateDisplay = (EditText) findViewById(R.id.Et_outdate);
	        mPickDate = (Button) findViewById(R.id.B_pDate);
	        // add a click listener to the button
	        mPickDate.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	                showDialog(DATE_DIALOG_ID);
	            }
	        });
	     
	    

	       }
		   
		
		

		@Override
		protected Dialog onCreateDialog(int id) {
		    switch (id) {
		    case DATE_DIALOG_ID:
		        return new DatePickerDialog(this,
		                    mDateSetListener,
		                    mYear , mMonth, mDay);
		    }
		    return null;
		}
		
		private DatePickerDialog.OnDateSetListener mDateSetListener =
		        new DatePickerDialog.OnDateSetListener() {

		            public void onDateSet(DatePicker view, int year, 
		                                  int monthOfYear, int dayOfMonth) {
		            	Calendar cal = Calendar.getInstance();
		    	    	
		    	    	
		    	    	
		   	         mYear = cal.get(Calendar.YEAR);
		   	         mMonth = cal.get(Calendar.MONTH) + 1;
		   	         mDay = cal.get(Calendar.DAY_OF_MONTH);
		   	         mDateDisplay.setText(mYear + "-" + mMonth + "-" + mDay);
		                
		              
		                
		            }
		            
		        };
		
		       	
		
		
		   			
			
			
			
		        public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu){
			    	
			    	
			    	
			    	//	menu.add("Get_Egrid")
			    		
			    		//	.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
			                			
			    		MenuInflater inflater=getSupportMenuInflater();
			    		inflater.inflate(R.menu.fltupdatemenu, menu);
			    		return super.onCreateOptionsMenu(menu);
			    		}
		
			
			
			
			public boolean onOptionsItemSelected (com.actionbarsherlock.view.MenuItem item ){
    			

		
			Bundle b = this.getIntent().getExtras();  
	        Long FlightID = b.getLong("pID");
			
			switch (item.getItemId()){
			
			case R.id.home :
				Intent myIntent3 = new Intent(this.getBaseContext(),PocketCal20.class);
			
	              startActivityForResult(myIntent3, 0);
	              
	              return true;
	              
			case R.id.back :
				
				super.onBackPressed();
				
				return true;
				

			case R.id.saveflt:
			/* Actions in case that Save Flight is pressed */
				updateTimes();
				
				UserDataHelper mdbh = new UserDataHelper(this.getApplicationContext());
			       final SQLiteDatabase db = mdbh.getWritableDatabase(); 
			      
			      
			      EditText OUT = (EditText) findViewById(R.id.Et_out);
			      EditText IN = (EditText) findViewById(R.id.Et_in);
			       EditText outdte = (EditText) findViewById(R.id.Et_outdate);
			       EditText fltno = (EditText) findViewById(R.id.Et_flt);
			       EditText AC = (EditText) findViewById(R.id.Et_tail);
			       EditText FROM = (EditText) findViewById(R.id.Et_frm);
			       EditText TO = (EditText) findViewById(R.id.Et_to);
			       TextView BLK = (TextView) findViewById(R.id.Et_blk);
			       
			       EditText CARGO = (EditText) findViewById(R.id.Et_cargo);
			       EditText COMM = (EditText) findViewById(R.id.Et_comm);
			       soutdate = outdte.getText().toString();
			       sfltno = fltno.getText().toString();
			       sTailNo = AC.getText().toString();
			       sFROM = FROM.getText().toString();
			       sTO = TO.getText().toString();
			       sOUT = OUT.getText().toString();
			       sIN = IN.getText().toString();
			       sCOMM = COMM.getText().toString();
			       sCARGO = CARGO.getText().toString();
			       sBLK = BLK.getText().toString();
			       
			       Spinner LAND = (Spinner) findViewById(R.id.Et_land);
			       int intSpinnerPosLAND = LAND.getSelectedItemPosition();
			       sLAND = LAND.getItemAtPosition(intSpinnerPosLAND).toString();
			       Spinner TYP = (Spinner) findViewById(R.id.Et_typ);
			       int intSpinnerPosTYP = TYP.getSelectedItemPosition();
			       sTYP = TYP.getItemAtPosition(intSpinnerPosTYP).toString();
			       
			     
			    
			       
			       
			       //String sOUTIME = sOUT.substring(0,2) +":"+ sOUT.substring(2,4);
			       
			      			   
			     int iCARGO ;
			     if (sCARGO.length() < 1) {
			    	 iCARGO = 0;
			     }else {
			    	  iCARGO = Integer.parseInt(sCARGO);
			     }
			     if (sCOMM.length() < 1) {
			    	 sCOMM="";
			     }
			     
			     ContentValues updateValues = new ContentValues();
			     updateValues.put(FLTLOG.FLTLOG_Date, soutdate);
			     updateValues.put(FLTLOG.FLTLOG_flt, sfltno);
			     updateValues.put(FLTLOG.FLTLOG_ac, sTailNo);
			     updateValues.put(FLTLOG.FLTLOG_from, sFROM);
			     updateValues.put(FLTLOG.FLTLOG_to, sTO);
			     updateValues.put(FLTLOG.FLTLOG_out, sOUT);
			     updateValues.put(FLTLOG.FLTLOG_in, sIN);
			     updateValues.put(FLTLOG.FLTLOG_blk,sBLK );
			     updateValues.put(FLTLOG.FLTLOG_land, sLAND);
			     updateValues.put(FLTLOG.FLTLOG_typ, sTYP);
			     updateValues.put(FLTLOG.FLTLOG_cargo, iCARGO);
			     updateValues.put(FLTLOG.FLTLOG_comm, sCOMM);
			     
			     
			     db.update(FLTLOG.FLTLOGTABLE_NAME, updateValues, FLTLOG.FLTLOG_ID + "=" + FlightID , null);
			     utilities util = new utilities();
			     Context ctx = getApplicationContext();
			 		 msg = FlightID + "  Updated"; 		
			 		util.showaction(msg,ctx);
			 		
			return true;
			
			case R.id.deleteFlight :
			/* Actions in case that Delete Contact is pressed */
				

				UserDataHelper deletedb = new UserDataHelper(this.getApplicationContext());
			       final SQLiteDatabase db1 = deletedb.getWritableDatabase(); 
			       
			       
			       db1.delete("fltlog","_id=?" ,new String[] {Long.toString(FlightID)});
			        util = new utilities();
				      ctx = getApplicationContext();
				 		 msg = FlightID + "  Updated"; 		
				 		util.showaction(msg,ctx);
			       
			     
			 		clearscrn();
			return true;
			
			
	              
			
		
		case R.id.eightn24thisflt :	
			Bundle b1 = new Bundle();
	 		EditText etdate = (EditText) findViewById(R.id.Et_outdate);
	 		EditText etIn = (EditText) findViewById(R.id.Et_in);
	 		EditText etOUT = (EditText) findViewById(R.id.Et_out);
	 		b1.putCharSequence("Dte", etdate.getText().toString());
	 		b1.putCharSequence("IN",etIn.getText().toString());
	 		b1.putCharSequence("OUT",etOUT.getText().toString());
	 		Intent myIntent2 = new Intent(this.getBaseContext(),eightn24a.class);
			myIntent2.putExtras(b1);
              startActivityForResult(myIntent2, 0);
              
              return true;
	}
			return false;



		}

		public void updateTimes(){
			 EditText OUT = (EditText) findViewById(R.id.Et_out);
		     
		       EditText IN = (EditText) findViewById(R.id.Et_in);
			 sOUT = OUT.getText().toString();
		      
		       sIN = IN.getText().toString();
			if ((sOUT.length() > 3)  &&(sIN.length() > 3)){
		       
		     
		       
		       
		       int iBlockHours, iBlockMin;
		       iBlockMin = 0;
		      
		       
		      
		      //OUT
		    // String  sOUThr = sOUT.substring(0,2);
		     int iOUThr = Integer.parseInt(sOUT.substring(0,2));
		    // String sOUTmin = sOUT.substring(3,5);
		     int iOUTmin = Integer.parseInt(sOUT.substring(3,5));
		     //OFF
		    
		     //IN
		    // String sINhr = sIN.substring(0, 2);
		     int iINhr = Integer.parseInt(sIN.substring(0, 2));
		     if (iINhr < iOUThr){  // We blocked in the Next Day
		    	 iINhr = iINhr + 24;
		    	 
		     }
		     
		    // String sINmin = sIN.substring(3, 5);
		     int iINmin = Integer.parseInt( sIN.substring(3, 5));
		     //ON
		     
		     
		    
		     int iBlockoutMin = (iOUThr * 60);
		     iBlockoutMin = iBlockoutMin + iOUTmin;
		     
		    
		     
		     int iBlockinMin = (iINhr * 60);
		     iBlockinMin = iBlockinMin + iINmin;
		     
		     TextView BLK = (TextView) findViewById(R.id.Et_blk);
		      
		     		     
		     
		     int iBlockMinutes = (iBlockinMin - iBlockoutMin);
		      iBlockHours = (iBlockMinutes/ 60);
		      iBlockMin = iBlockMinutes - (iBlockHours * 60);
		     
		      if ((iBlockMin < 10) && (iBlockHours < 10)){
		    	  BLK.setText("0" + iBlockHours + ":0" + iBlockMin);  
		      }
		      if ((iBlockMin < 10) && (iBlockHours > 9)){
		    	  BLK.setText(iBlockHours + ":0" + iBlockMin);  
		      }
		      if  ((iBlockMin > 10) && (iBlockHours > 9)){
			    	  BLK.setText(iBlockHours + ":" + iBlockMin); 
		      }
		      if ((iBlockMin > 9) && (iBlockHours < 10)){
		    	    	  BLK.setText("0" + iBlockHours + ":" + iBlockMin); 
		      }
		    		  
		    		  
		    
		      
		     
	    
		}	else{
		// Do Nothing	
		}
	}
		
public void updatefields(Cursor cu){
	
	EditText OUT = (EditText) findViewById(R.id.Et_out);
    OUT.setText(cu.getString(cu.getColumnIndex("out")));
    
    
      EditText IN = (EditText) findViewById(R.id.Et_in);
      IN.setText(cu.getString(cu.getColumnIndex("inn")));
      EditText outdte = (EditText) findViewById(R.id.Et_outdate);
      outdte.setText(cu.getString(cu.getColumnIndex("Date")));
      String yr = cu.getString(cu.getColumnIndex("Date")).substring(0, 4);
      String mo = cu.getString(cu.getColumnIndex("Date")).substring(6, 7);
      String dy = cu.getString(cu.getColumnIndex("Date")).substring(9, 10);
      EditText fltno = (EditText) findViewById(R.id.Et_flt);
      fltno.setText(cu.getString(cu.getColumnIndex("Flt")));
      EditText AC = (EditText) findViewById(R.id.Et_tail);
      AC.setText(cu.getString(cu.getColumnIndex("AC")));
      EditText FROM = (EditText) findViewById(R.id.Et_frm);
      FROM.setText(cu.getString(cu.getColumnIndex("frm")));
      EditText TO = (EditText) findViewById(R.id.Et_to);
      TO.setText(cu.getString(cu.getColumnIndex("dest")));
      TextView BLK = (TextView) findViewById(R.id.Et_blk);
      BLK.setText(cu.getString(cu.getColumnIndex("blk")));
      
      EditText CARGO = (EditText) findViewById(R.id.Et_cargo);
      CARGO.setText(cu.getString(cu.getColumnIndex("cargo")));
      EditText COMM = (EditText) findViewById(R.id.Et_comm);
      COMM.setText(cu.getString(cu.getColumnIndex("comm")));
     selectLand = cu.getString(cu.getColumnIndex("land")).toString();
     selectTyp = cu.getString(cu.getColumnIndex("typ")).toString();
     
    mYear = Integer.parseInt(yr);
     mMonth = Integer.parseInt(mo);
     mDay = Integer.parseInt(dy);

  
	

}

public void clearscrn(){
	
	//clear the screen
	EditText OUT = (EditText) findViewById(R.id.Et_out);
    OUT.setText("");
    
      EditText IN = (EditText) findViewById(R.id.Et_in);
      IN.setText("");
      EditText outdte = (EditText) findViewById(R.id.Et_outdate);
      outdte.setText("");
      EditText fltno = (EditText) findViewById(R.id.Et_flt);
      fltno.setText("");
      EditText AC = (EditText) findViewById(R.id.Et_tail);
      AC.setText("");
      EditText FROM = (EditText) findViewById(R.id.Et_frm);
      FROM.setText("");
      EditText TO = (EditText) findViewById(R.id.Et_to);
      TO.setText("");
      TextView BLK = (TextView) findViewById(R.id.Et_blk);
      BLK.setText("");
      
      EditText CARGO = (EditText) findViewById(R.id.Et_cargo);
      CARGO.setText("");
      EditText COMM = (EditText) findViewById(R.id.Et_comm);
      COMM.setText("");
	
	
	
}

}








		
		







