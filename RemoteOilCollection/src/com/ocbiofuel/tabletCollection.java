package com.ocbiofuel;


import java.text.SimpleDateFormat;
import java.util.Date;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;

import android.os.Bundle;
import android.text.format.Time;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;










public class tabletCollection extends SherlockActivity{

	
	 ActionBar actionBar;
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainlayout);
	
		actionBar = getSupportActionBar();
		
		
		
		EditText tDate = (EditText)findViewById(R.id.etDate);
		EditText tTime = (EditText)findViewById(R.id.etTime);
		EditText tType = (EditText)findViewById(R.id.etType);
		EditText tManifest = (EditText)findViewById(R.id.etManifest);
		EditText tGenName = (EditText)findViewById(R.id.etgenName);
		EditText tGenAddress = (EditText)findViewById(R.id.etgenAddr);
		EditText tRecFac = (EditText)findViewById(R.id.etRecFacAddress);
		EditText tTotIkg = (EditText)findViewById(R.id.etTotalIKG);
		Spinner sMethod = (Spinner)findViewById(R.id.spMethod);
		
		EditText tCcap = (EditText)findViewById(R.id.etcontCap);
		EditText tPfill = (EditText)findViewById(R.id.etpFill);
		EditText tTransName = (EditText)findViewById(R.id.etTransName);
		EditText tTransSig = (EditText)findViewById(R.id.etTransSig);
		EditText tGenRep = (EditText)findViewById(R.id.etGenRep);
		EditText tGenSig = (EditText)findViewById(R.id.etGenSig);
		EditText tComm = (EditText)findViewById(R.id.etComm);
		 
	
	
}

		
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu){
		MenuInflater inflater=getSupportMenuInflater();
		inflater.inflate(R.menu.mainmenu, menu);
			
			
			return true;
			}

public boolean onOptionsItemSelected (com.actionbarsherlock.view.MenuItem item ){
				switch (item.getItemId()){
				
			
				case R.id.addCollection :
					//populate date,time,type and Manifest
					// get current system date and copy to date filed
					EditText tDate = (EditText)findViewById(R.id.etDate);
					EditText tTime = (EditText)findViewById(R.id.etTime);
					
					Time today = new Time(Time.getCurrentTimezone());
					today.setToNow();
					String Date;
					String Time;
					
					Date = today.month + "/" + today.monthDay + "/" + today.year;
					Time = today.format("%k:%M:%S");
					
					
					
					
					tDate.setText(Date);
					tTime.setText(Time);
					
					
					return true;
				case R.id.clearForm:
				//	clear data from form
				clearForm();
				
				return true;
				
				case R.id.print:
				// print rec ticket to bluetooth printer	
					
				return true;
				case R.id.upLoad:
				//send this collection data to server	
					
				return true;
				
				

					
				case R.id.signaturePrint:
					//Capture signature for print of Manifest.Store to device for upload to SQL BLOB field
						
					return true;
			
		}
				return false;
			}	
	
	
	public void clearForm(){
		
		
		
	}
	
	public void loadspinners() {
		
		 Spinner s1 = (Spinner) findViewById(R.id.spMethod);
		 
		    ArrayAdapter<?> adapter1 = ArrayAdapter.createFromResource(
		            this, R.array.methods, android.R.layout.simple_spinner_item);
		    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		    s1.setAdapter(adapter1);
		
		
		
		
		
	}
	
}