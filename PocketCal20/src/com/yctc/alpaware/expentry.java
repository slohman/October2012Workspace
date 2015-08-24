package com.yctc.alpaware;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.actionbarsherlock.view.MenuInflater;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;



public class expentry extends SherlockActivity{

	
	private EditText mDateDisplay;
    private Button mPickDate;

    public int mYear;
    public int mMonth;
    public int mDay;
    public String sMonth;
    public String sYear;
    public String sDay;
    public String msg;
    
    utilities expmsg = new utilities();
    static final int DATE_DIALOG_ID = 1;
	

	
	@Override
	
	 
	
	public void onCreate(Bundle savedInstanceState) {
	    
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.expentry);
	    // capture our View elements
       
	    Button cam = (Button) findViewById(R.id.B_PIX);
        cam.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	    int mYr;
            	    int mMn;
            	    int mDy;
            	    int msec;
            	    String  sFilename = null;
            	   
            	File expdir =  new File(Environment.getExternalStorageDirectory().toString() + "/Expenses/");;
            	
            	if( expdir.canWrite() == false) {
            		 expdir.mkdirs();
            		}
            	
            	
            	    final Calendar cal = Calendar.getInstance();
            	    mYr = cal.get(Calendar.YEAR);
                    mMn = cal.get(Calendar.MONTH) + 1;
                    mDy = cal.get(Calendar.DAY_OF_MONTH);
                    msec = cal.get(Calendar.SECOND);
                    
            	    sFilename = String.valueOf(mYr)+String.valueOf(mMn)+String.valueOf(mDy)+String.valueOf(msec) + ".jpg";
            	  //  sFilename =  Integer.toString(filename);
            	    
            	    
            	    
            	    Intent imageCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                  imageCaptureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
          Uri.fromFile(new File(expdir +"/" + sFilename)));
                  startActivityForResult(imageCaptureIntent, 1);

            }});
            
	    	  
	   
	    	   
	    Spinner s1 = (Spinner) findViewById(R.id.Exp_Cat);
	    ArrayAdapter<?> adapter1 = ArrayAdapter.createFromResource(
	            this, R.array.ExpenseCat, android.R.layout.simple_spinner_item);
	    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    s1.setAdapter(adapter1);
	    
	 // capture our View elements
        mDateDisplay = (EditText) findViewById(R.id.Exp_date);
        mPickDate = (Button) findViewById(R.id.B_setExpDate);
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
	
	

	@Override
	protected Dialog onCreateDialog(int id) {
	    switch (id) {
	    case DATE_DIALOG_ID:
	    	
	    	Calendar cal = Calendar.getInstance();
	    	  int m1Year;
	    	   int m1Month;
	    	  int m1Day;
	    	
	    	
	         m1Year = cal.get(Calendar.YEAR);
	         m1Month = cal.get(Calendar.MONTH);
	         m1Day = cal.get(Calendar.DAY_OF_MONTH);
	    	
	    	
	        return new DatePickerDialog(this,
	                    mDateSetListener,
	                    m1Year, m1Month, m1Day);
	    }
	    return null;
	}
	// the callback received when the user "sets" the date in the dialog
	private DatePickerDialog.OnDateSetListener mDateSetListener =
	        new DatePickerDialog.OnDateSetListener() {

	            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
	            	
	            	
	            	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		                Calendar c = Calendar.getInstance();
		                 c.set(year, monthOfYear, dayOfMonth);
		                String dateString[] = formatDate(c);
		                
		             	String year1= dateString[0].trim();
		            	String month1 = dateString[1].trim();
		            	String day1 = dateString[2].trim();
		               
		            	String sDate = year1+ "-" + month1 + "-" + day1;
		                
		               
		                mDateDisplay.setText(sDate);
	                
	            }
	            
	        };
	
	
			
			
	        public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu){
	        	
	        	
	        	
	        	//	menu.add("Get_Egrid")
	        		
	        		//	.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
	                    			
	        		MenuInflater inflater=getSupportMenuInflater();
	        		inflater.inflate(R.menu.expsubmenu, menu);
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
	        			
	        			case R.id.expmaint :
	        				Intent myIntent3 = new Intent(getBaseContext(), explogmaint.class);	
	        				startActivityForResult(myIntent3, 0);
	        		
	        			case R.id.expsave : 	
	    	    			
	    	    			

	      	  			  UserDataHelper mdbh = new UserDataHelper(this.getApplicationContext());
	      	  		       final SQLiteDatabase db = mdbh.getWritableDatabase(); 
	      	  		       
	      	    			EditText expdte = (EditText)findViewById(R.id.Exp_date);
	      	    			String sExpDte = expdte.getText().toString();
	      	    			EditText expamt = (EditText)findViewById(R.id.Exp_amt);
	      	    			String sExpAmt = expamt.getText().toString();
	      	    			EditText expCity = (EditText)findViewById(R.id.Exp_City);
	      	    			String sExpCity = expCity.getText().toString();
	      	    			Spinner ExpCat = (Spinner) findViewById(R.id.Exp_Cat);
	      	    			int intSpinnerPosExp = ExpCat.getSelectedItemPosition();
	      	  		        String sExpCat = ExpCat.getItemAtPosition(intSpinnerPosExp).toString();
	      	  		        EditText expcomm = (EditText)findViewById(R.id.Exp_comm);
	      	  		        String sExpComm = expcomm.getText().toString();
	      	  		        
	      	  		   try{     
	      	  		      db.execSQL("Insert into EXP Values (" + null  + ", " + "'" + sExpDte + "','" + sExpAmt + "','" + sExpCat + "','" + sExpComm + "','" + sExpCity + "')");		  
	      	  			msg = "Exp Added";
	      	  			 Context ctx = getApplicationContext();
	      	  			expmsg.showaction(msg,ctx);
	      	    		
	      	    		return true;
	      	  		 
	      	  		   }catch (Exception e){
	      	  			 Context ctx = getApplicationContext();
	      	  			 expmsg.showaction("Database is not installed/or is damaged",ctx);
	      					Intent myIntent4 = new Intent(getBaseContext(),explogmaint.class);	
	      					startActivityForResult(myIntent4, 0);
	      				return false;
	      	  		   }
	      	  		
	        			
	        			case R.id.expclear :
		    			/* Actions in case that Clear is pressed */
		    			EditText expdte1 = (EditText)findViewById(R.id.Exp_date);
		    			 expdte1.setText("");
		    			 EditText expamt1 = (EditText)findViewById(R.id.Exp_amt);
		    			expamt1.setText("");
		    			EditText expcomm1 = (EditText)findViewById(R.id.Exp_comm);
		    			expcomm1.setText("");
		    			EditText expcity1 = (EditText)findViewById(R.id.Exp_City);
		    			expcity1.setText("");
		  		       
		    			return true;
	      	  			   
	      	  			   
	      	  		   }
	        		
	        		
	        	
	        			return false;
	        		}

	        public String[] formatDate(Calendar c){
	        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        	
	        	
	             String  dateString = sdf.format(c.getTime());
	             String[] dateData = dateString.split("-");
	             
	        	
	        	return dateData;
	        	


	        }

}	
