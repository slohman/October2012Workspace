
package com.yctc.alpaware;




import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

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




public class fltentry extends SherlockActivity{
	private EditText mDateDisplay;
    private Button mPickDate;

    public int mYear;
    public int mMonth;
    public int mDay;
    public String sMonth;
    public String sYear;
    public String sDay;
    public String msg;
    

    static final int DATE_DIALOG_ID = 1;
    utilities fltmsg = new utilities();
  
	
	@Override
	
	 
	
	public void onCreate(Bundle savedInstanceState) {
	    
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.fltlog);
	    // capture our View elements
	   
	    EditText etFLT = (EditText) findViewById(R.id.Et_in);
	       etFLT.setOnFocusChangeListener(new OnFocusChangeListener()    {
	    	   public void onFocusChange(View v, boolean hasFocus) {
	    	   // TODO Auto-generated method stub
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
	     Spinner s1 = (Spinner) findViewById(R.id.Et_land);
	    ArrayAdapter<?> adapter1 = ArrayAdapter.createFromResource(
	            this, R.array.pilots, android.R.layout.simple_spinner_item);
	    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    s1.setAdapter(adapter1);  
	    
        Spinner s = (Spinner) findViewById(R.id.Et_typ);
	    ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(
	            this, R.array.aircraft, android.R.layout.simple_spinner_item);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    s.setAdapter(adapter);
	    
	    
	    
	 // capture our View elements
        mDateDisplay = (EditText) findViewById(R.id.Et_outdate);
        mPickDate = (Button) findViewById(R.id.B_pDate);
        // add a click listener to the button
        mPickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });
        
     
        // get the current date and sets and initial value into the text field mDateDisplay
        final Calendar c = Calendar.getInstance();
       String dateString[] = formatDate(c);
       
     	String year= dateString[0].trim();
    	String month = dateString[1].trim();
    	String day = dateString[2].trim();
    	
    	String sDate = year+ "-" + month + "-" + day;
       
        mDateDisplay.setText(sDate);
    
    

       }
	   
	//updates the date we display in the TextView
	
	    		
	       
	

	@Override
	protected Dialog onCreateDialog(int id) {
	    switch (id) {
	    case DATE_DIALOG_ID:
	    	
	    	Calendar c = Calendar.getInstance();
	    	
	    	
	    	
	    	mYear = (c.get(Calendar.YEAR));
	    	mMonth = c.get(Calendar.MONTH);
	    	mDay =c.get(Calendar.DAY_OF_MONTH);
	    	
	        return new DatePickerDialog(this, mDateSetListener,  mYear, mMonth , mDay );
	    }
	    return null;
	}
	// the callback received when the user "sets" the date in the dialog
	private DatePickerDialog.OnDateSetListener mDateSetListener =
	        new DatePickerDialog.OnDateSetListener() {

	            public void onDateSet(DatePicker view, int year, 
	                                  int monthOfYear, int dayOfMonth) {
	            
	                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	                final Calendar c = Calendar.getInstance();
	                c.set(year, monthOfYear, dayOfMonth);
	                String  dateString = sdf.format(c.getTime());
	                mDateDisplay.setText(dateString);
	            
  }
	            
	        };
	
	       	
	
	
	
		
		
		
		String sTailNo = "";
		String sfltno = "";
		String soutdate,sindate,soutmonth,soutyr,soutdy,sinmonth,sindy,sinyr;
		String sFROM,sTO, sOUT,sIN,sBLK,sLAND,sTYP,sCOMM,sCARGO;
		
		
		
		
		
		
		
public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu){
	    	
	    	
	    	
	    	//	menu.add("Get_Egrid")
	    		
	    		//	.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
	                			
	    		MenuInflater inflater=getSupportMenuInflater();
	    		inflater.inflate(R.menu.fltmenu, menu);
	    		return super.onCreateOptionsMenu(menu);
	    		}

	    		public boolean onOptionsItemSelected (com.actionbarsherlock.view.MenuItem item ){
	    			switch (item.getItemId()){
	    			
	    			case R.id.home :
	    				
	    				Intent myIntent1 = new Intent(getBaseContext(), PocketCal20.class);	
	    				startActivityForResult(myIntent1, 0);
	    			return true;	
	    			case R.id.back :
	    				Intent myIntent11 = new Intent(getBaseContext(), PocketCal20.class);	
	    				startActivityForResult(myIntent11, 0);
	    			return true;	
	    		
	    			
		
		
		case R.id.saveflt :
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
		       Spinner LAND = (Spinner) findViewById(R.id.Et_land);
		       int intSpinnerPosLAND = LAND.getSelectedItemPosition();
		       sLAND = LAND.getItemAtPosition(intSpinnerPosLAND).toString();
		       Spinner TYP = (Spinner) findViewById(R.id.Et_typ);
		       int intSpinnerPosTYP = TYP.getSelectedItemPosition();
		       sTYP = TYP.getItemAtPosition(intSpinnerPosTYP).toString();
		      
		      
		    
		      
		       
		       String sOUTIME = sOUT.substring(0,2) +":"+ sOUT.substring(2,4);
		      
		       String sINTIME = sIN.substring(0,2) + ":" + sIN.substring(2,4);
		      
		       if (sOUTIME.length() < 4) { sOUTIME = "00:00";};
		      
		       if (sINTIME.length() < 4) { sINTIME = "00:00";};
		       
		       
		     int iCARGO = 0;
		     if (sCARGO.length() < 1) {
		    	 iCARGO = 0;
		     }else {
		    	  iCARGO = Integer.parseInt(sCARGO.trim());
		    	  
		     }
		     if (sCOMM.length() < 1) {
		    	 sCOMM="";
		     }
try {
db.execSQL("Insert into fltlog Values (" + null  + ", " + "'" + soutdate + "','" + sfltno + "','" + sTailNo + "','" + sFROM + "','" +  sTO + "','" + sOUTIME + "','"  + sINTIME + "','" + BLK.getText().toString() + "','" + sLAND + "','" + sTYP + "','" + sCOMM + "','" + iCARGO + "')");		  
	msg = "Flight " + sfltno + " Added";
	 Context ctx = getApplicationContext();
	fltmsg.showaction(msg,ctx);
		 		
		return true;
			}catch (Exception e){
				 Context ctx = getApplicationContext();
				fltmsg.showaction("Database is not installed/or is damaged",ctx);
				Intent myIntent3 = new Intent(getBaseContext(), fltlogmaint.class);	
				startActivityForResult(myIntent3, 0);
				return false;
			}
		
		case R.id.clearFlight :
		/* Actions in case that Delete Contact is pressed */
			EditText sOUT = (EditText) findViewById(R.id.Et_out);
		    sOUT.setText("");
		     
		      EditText sIN = (EditText) findViewById(R.id.Et_in);
		      sIN.setText("");
		      EditText sfltno = (EditText) findViewById(R.id.Et_flt);
		      sfltno.setText("");
		      EditText sAC = (EditText) findViewById(R.id.Et_tail);
		      sAC.setText("");
		      EditText sFROM = (EditText) findViewById(R.id.Et_frm);
		      sFROM.setText("");
		      EditText sTO = (EditText) findViewById(R.id.Et_to);
		      sTO.setText("");
		      TextView sBLK = (TextView) findViewById(R.id.Et_blk);
		      sBLK.setText("");
		     
		      EditText sCARGO = (EditText) findViewById(R.id.Et_cargo);
		      sCARGO.setText("");
		      EditText sCOMM = (EditText) findViewById(R.id.Et_comm);
		      sCOMM.setText("");
				
			

		return true;
		
			
	
			
	}
		return false;



	}

	public void updateTimes(){
		 EditText OUT = (EditText) findViewById(R.id.Et_out);
	     
	       EditText IN = (EditText) findViewById(R.id.Et_in);
		 sOUT = OUT.getText().toString();
	       
	       sIN = IN.getText().toString();
		if ((sOUT.length() > 3) &&  (sIN.length() > 3)){
	       
	     // Need to determine date of block in ??????
	       
	       
	       int iBlockHours, iBlockMin;
	       iBlockMin = 0;
	     
	     String sOUThr, sOUTmin;
	     String sINhr, sINmin;
	     
	     sOUTmin = (OUT.getText().toString().substring(2, 4));
	     sOUThr = (OUT.getText().toString().substring(0,2));
	    
	     sINhr = (IN.getText().toString().substring(0,2));
	     sINmin = (IN.getText().toString().substring(2,4));
	     int iOUThr=0,iOUTmin=0,iINhr=0,iINmin=0;
	      iOUThr = Integer.parseInt(sOUThr );
	      iOUTmin = Integer.parseInt(sOUTmin );
	      
	      iINhr = Integer.parseInt(sINhr );
	      iINmin = Integer.parseInt(sINmin);
	     
	     
	     
	     if (iINhr < iOUThr){  // We blocked in the Next Day
	    	 iINhr += 24;
	       }
	     
	    
	     
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
	      if  ((iBlockMin > 9) && (iBlockHours > 9)){
		    	  BLK.setText(iBlockHours + ":" + iBlockMin); 
	      }
	      if ((iBlockMin > 9) && (iBlockHours < 10)){
	    	    	  BLK.setText("0" + iBlockHours + ":" + iBlockMin); 
	      }
	    		  
	    		  
	
	      
	     
    
	}	else{
	// Do Nothing	
	}
}
	
public String[] formatDate(Calendar c){
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	
     String  dateString = sdf.format(c.getTime());
     String[] dateData = dateString.split("-");
     
	
	return dateData;
	


}
	
	

 


}








	
	





