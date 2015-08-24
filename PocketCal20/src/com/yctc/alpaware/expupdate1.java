package com.yctc.alpaware;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;
import com.yctc.alpaware.PocketCal.EXPLOG;

public class expupdate1 extends SherlockActivity {
	
	private EditText mDateDisplay;
    private Button mPickDate;

    public int mYear;
    public int mMonth;
    public int mDay;
    public String sDay;
    public String sMonth;
    public String sYear;
    public String msg;
    public String selectTyp;
    static final int DATE_DIALOG_ID = 1;

	
	@Override
	
	 
	
	public void onCreate(Bundle savedInstanceState) {
	    
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.expupdate);
	    Bundle b = this.getIntent().getExtras();  
        Long ExpID = b.getLong("pID");
        
        
        UserDataHelper fldbh = new UserDataHelper(this.getApplicationContext());
	      SQLiteDatabase db = fldbh.getWritableDatabase(); 
	    
	      

	      SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
	        qb.setTables(EXPLOG.EXPLOGTABLE_NAME);
	        qb.appendWhere(EXPLOG.EXPLOG_ID + "=" + ExpID);
	        String Cols[] = { EXPLOG.EXPLOG_ID + "," + EXPLOG.EXPLOG_Date + "," + EXPLOG.EXPLOG_Expamt + "," + EXPLOG.EXPLOG_Cat +
	        				"," + EXPLOG.EXPLOG_Comm + "," + EXPLOG.EXPLOG_City };
	       
	        
	 		Cursor cu = qb.query(db,Cols,null,null,null,null,EXPLOG.DEFAULT_SORT_ORDER);		  
	 		 		
	 		 startManagingCursor(cu);
	 		 cu.moveToFirst();
	 		 
	 		 
	    // load fields
	 		 
	 		 updatefields(cu);
	 	  
	 // capture our View elements
        mDateDisplay = (EditText) findViewById(R.id.Exp_date);
        mPickDate = (Button) findViewById(R.id.B_setExpDate);
        
        
        // add a click listener to the button
        mPickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });
        

        
    

       }
	   
	//updates the date we display in the TextView. NEED TO HANDLE the + 1 MONTH here for update
	private void updateDisplay() {
		if (mYear < 99) {
			mYear = mYear + 2000;
		}
		if (mMonth < 10) {
			sMonth = "0" + (mMonth + 1);
		}
		if (mDay < 10) {
			sDay = "0" + mDay;
		}
	    mDateDisplay.setText(
	    		
	    		
	    		
	        new StringBuilder()
	                // Month is 0 based so add 1
	        		
	        		.append(mYear).append("-")	        
	                .append(sMonth).append("-")
	                .append(sDay)
	                ) ;
	   
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		
		
		
	    switch (id) {
	    case DATE_DIALOG_ID:
	    	
	    	EditText dateDisplay = (EditText) findViewById(R.id.Exp_date);
	    	String dateString = dateDisplay.getText().toString();
	    	String[] dateData = dateString.split("-");
	    	int year = Integer.parseInt(dateData[0].toString());
	    	int month = Integer.parseInt(dateData[1].toString());
	    	int day = Integer.parseInt(dateData[2].toString());
	    	
	    	
	        return new DatePickerDialog(this,
	                    mDateSetListener,
	                    year , month, day);
	        
	        
	    }
	    return null;
	}
	// the callback received when the user "sets" the date in the dialog
	private DatePickerDialog.OnDateSetListener mDateSetListener =
	        new DatePickerDialog.OnDateSetListener() {

	            public void onDateSet(DatePicker view, int year,  int month, int day) {
	            	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
	                Calendar c = Calendar.getInstance();
	                 c.set(year, month, day);
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
	        		inflater.inflate(R.menu.expenseupdatemenu, menu);
	        		return super.onCreateOptionsMenu(menu);
	        		}
	        	
	        public boolean onOptionsItemSelected (com.actionbarsherlock.view.MenuItem item ){
	        	Bundle b = this.getIntent().getExtras();  
    	        Long ExpID = b.getLong("pID");
	        	
	        	
    			switch (item.getItemId()){
    			
    			
    			
    			case R.id.home :
    				Intent myIntent2 = new Intent(getBaseContext(), PocketCal20.class);	
    				startActivityForResult(myIntent2, 0);
    				
    			case R.id.back :
    				
    				Intent myIntent1 = new Intent(getBaseContext(), explistout.class);	
    				startActivityForResult(myIntent1, 0);
    			return true;
    			
    			case R.id.updateexp:
    				/* Actions in case that Save Flight is pressed */
    					
    					
    					UserDataHelper mdbh = new UserDataHelper(this.getApplicationContext());
    				       final SQLiteDatabase db = mdbh.getWritableDatabase(); 
    				      
    				      
    				       
    				       EditText dte = (EditText) findViewById(R.id.Exp_date);
    				       EditText amt = (EditText) findViewById(R.id.Exp_amt);
    				       EditText comm = (EditText) findViewById(R.id.Exp_comm);
    				       EditText city = (EditText) findViewById(R.id.Exp_City);
    				       String sdte = dte.getText().toString();
    				       String samt = amt.getText().toString();
    				       String sComm = comm.getText().toString();
    				       String sCity = city.getText().toString();
    				      
    				       
    				       Spinner CAT = (Spinner) findViewById(R.id.Exp_Cat);
    				       int intSpinnerPosCat = CAT.getSelectedItemPosition();
    				       String sCat = CAT.getItemAtPosition(intSpinnerPosCat).toString();
    				     
    				       
    				     
    				     ContentValues updateValues = new ContentValues();
    				     updateValues.put(EXPLOG.EXPLOG_Date, sdte);
    				     updateValues.put(EXPLOG.EXPLOG_Expamt, samt);
    				     updateValues.put(EXPLOG.EXPLOG_Comm,sComm);
    				     updateValues.put(EXPLOG.EXPLOG_Cat, sCat);
    				     updateValues.put(EXPLOG.EXPLOG_City, sCity);
    				    
    				     
    				     
    				     db.update(EXPLOG.EXPLOGTABLE_NAME, updateValues, EXPLOG.EXPLOG_ID + "=" + ExpID , null);

    				 		 msg = ExpID + "  Updated"; 		
    				 		showaction(msg);
    				 		db.close();
    				 		
    				return true;
  	  			   
    			case R.id.deleteexp :
    				/* Actions in case that Delete Contact is pressed */
    					

    					UserDataHelper deletedb = new UserDataHelper(this.getApplicationContext());
    				       final SQLiteDatabase db1 = deletedb.getWritableDatabase(); 
    				       
    				       
    				       db1.delete("exp","_id=?" ,new String[] {Long.toString(ExpID)});
    				       msg = ExpID + "  Deleted"; 		
    				 		showaction(msg);
    				 		clearscrn();
    				 		db1.close();
    				return true;
  	  		   }
    		
    		
    	
    			return false;

}
		
	
	
public void updatefields(Cursor cu){
	EditText expdte1 = (EditText)findViewById(R.id.Exp_date);
	 expdte1.setText(cu.getString(cu.getColumnIndex("exp_DATE")).toString());
	 EditText expamt1 = (EditText)findViewById(R.id.Exp_amt);
	expamt1.setText(cu.getString(cu.getColumnIndex("exp_AMT")).toString());
	EditText expcomm1 = (EditText)findViewById(R.id.Exp_comm);
	expcomm1.setText(cu.getString(cu.getColumnIndex("exp_COMM")).toString());
	EditText expcity1 = (EditText)findViewById(R.id.Exp_City);
	expcity1.setText(cu.getString(cu.getColumnIndex("exp_CITY")).toString());
	
	selectTyp = cu.getString(cu.getColumnIndex("exp_CAT")).toString();
	
	 Spinner sp = (Spinner) findViewById(R.id.Exp_Cat);
	    ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(
	            this, R.array.ExpenseCat, android.R.layout.simple_spinner_item);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    sp.setAdapter(adapter);
	  
	    
	    if (selectTyp.matches("Air Tran")){
	      	 sp.setSelection(0);}
	    if (selectTyp.matches("Ground Tran")){
	      	 sp.setSelection(1);}
	      if (selectTyp.matches("Food")){
		      	 sp.setSelection(2);}
	      if (selectTyp.matches( "Entertainment")){
		      	 sp.setSelection(3);}
	      if (selectTyp.matches("Tips")){
		      	 sp.setSelection(4);}
	      if (selectTyp.matches("Bag Fees")){
		      	 sp.setSelection(5);}
	      if (selectTyp.matches("Parking")){
		      	 sp.setSelection(6);}
	      if (selectTyp.matches("Depature Fees")){
		      	 sp.setSelection(7);}
	      if (selectTyp.matches("Exchange Fees")){
		      	 sp.setSelection(8);}
//	String yr = cu.getString(cu.getColumnIndex("exp_DATE")).substring(0, 4);
//    String mo = cu.getString(cu.getColumnIndex("exp_DATE")).substring(6, 7);
//    String dy = cu.getString(cu.getColumnIndex("exp_DATE")).substring(9, 10);
 //   mYear = Integer.parseInt(yr);
// mMonth = Integer.parseInt(mo);
 //mDay = Integer.parseInt(dy);




}


public void showaction(String msg){
Context context = getApplicationContext();
int duration = Toast.LENGTH_SHORT;

Toast toast = Toast.makeText(context, msg, duration);
toast.show();
}
public void clearscrn(){

	/* Actions in case that Clear is pressed */
	EditText expdte1 = (EditText)findViewById(R.id.Exp_date);
	 expdte1.setText("");
	 EditText expamt1 = (EditText)findViewById(R.id.Exp_amt);
	expamt1.setText("");
	EditText expcomm1 = (EditText)findViewById(R.id.Exp_comm);
	expcomm1.setText("");
	EditText expcity1 = (EditText)findViewById(R.id.Exp_City);
	expcity1.setText("");

}
public  void OnExit(Cursor cu){
cu.close();	
	
}

public String[] formatDate(Calendar c){
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	
     String  dateString = sdf.format(c.getTime());
     String[] dateData = dateString.split("-");
     
	
	return dateData;
	


}

}
