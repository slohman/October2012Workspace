package com.yctc.alpaware;

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

import com.yctc.alpaware.PocketCal.EXPLOG;

public class expupdate extends Activity {
	
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
	        return new DatePickerDialog(this,
	                    mDateSetListener,
	                    mYear , mMonth, mDay);
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
	                
	                updateDisplay();
	                
	            }
	            
	        };
	
	       	
	
	
	    public static final int Update_exp=0; //no more R.ids   
		public static final int Delete_exp = 1;
		public static final int Home_screen = 2;
		
		
		
		
		
		
		
		
		
		
		
		
		public boolean onCreateOptionsMenu(Menu menu){
		menu.add(0,Update_exp,0,"Update/Edit Exp");
		menu.add(0,Delete_exp,0,"Delete Exp");
		menu.add(0,Home_screen,0,"Expense Main");
		
		
		return true;
		}

	public boolean onOptionsItemSelected ( MenuItem item ){
		Bundle b = this.getIntent().getExtras();  
        Long ExpID = b.getLong("pID");
		
		switch (item.getItemId()){
		
		

		case Update_exp:
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
		
		case Delete_exp :
		/* Actions in case that Delete Contact is pressed */
			

			UserDataHelper deletedb = new UserDataHelper(this.getApplicationContext());
		       final SQLiteDatabase db1 = deletedb.getWritableDatabase(); 
		       
		       
		       db1.delete("exp","_id=?" ,new String[] {Long.toString(ExpID)});
		       msg = ExpID + "  Deleted"; 		
		 		showaction(msg);
		 		clearscrn();
		 		db1.close();
		return true;
		
		case Home_screen :	
	        
			Intent myIntent = new Intent(this.getBaseContext(), PocketCal20.class);
              startActivityForResult(myIntent, 0);
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
	String yr = cu.getString(cu.getColumnIndex("exp_DATE")).substring(0, 4);
    String mo = cu.getString(cu.getColumnIndex("exp_DATE")).substring(6, 7);
    String dy = cu.getString(cu.getColumnIndex("exp_DATE")).substring(9, 10);
mYear = Integer.parseInt(yr);
 mMonth = Integer.parseInt(mo);
 mDay = Integer.parseInt(dy);




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
}









	
	








