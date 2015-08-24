package com.yctc.alpaware;

import com.actionbarsherlock.view.MenuInflater;


import android.content.Intent;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import com.actionbarsherlock.app.SherlockActivity;


public class mec extends SherlockActivity {

	
	
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.mec);
	        
	              
	        
	        Button boff = (Button) findViewById(R.id.officers);
	        boff.setOnClickListener(new View.OnClickListener() {
	        	public void onClick(View view) {
	        		 Intent myIntent = new Intent(view.getContext(), meclist.class);
	                 startActivityForResult(myIntent, 0);
}});;

			Button brep = (Button) findViewById(R.id.reps);
				brep.setOnClickListener(new View.OnClickListener() {
					public void onClick(View view) {
						Intent myIntent = new Intent(view.getContext(), repslist.class);
						startActivityForResult(myIntent, 0);
}});;
			Button bevp = (Button) findViewById(R.id.bevp);
					bevp.setOnClickListener(new View.OnClickListener() {
							public void onClick(View view) {
								Intent myIntent = new Intent(view.getContext(), evp1.class);
								startActivityForResult(myIntent, 0);
							}});;
			Button bcomm = (Button) findViewById(R.id.meccomm);
			bcomm.setOnClickListener(new View.OnClickListener() {
							public void onClick(View view) {
								Intent myIntent = new Intent(view.getContext(), committee.class);
								startActivityForResult(myIntent, 0);
									}});;
									
			
							
			Button bstaff = (Button) findViewById(R.id.staff);
			bstaff.setOnClickListener(new View.OnClickListener() {
						public void onClick(View view) {
						Intent myIntent = new Intent(view.getContext(), staff.class);
						startActivityForResult(myIntent, 0);
									}});;	
									
			Button baccidentinfo = (Button) findViewById(R.id.accidentincident);
			baccidentinfo.setOnClickListener(new View.OnClickListener() {
						public void onClick(View view) {
						Intent myIntent = new Intent(view.getContext(), accidentinfo.class);
						startActivityForResult(myIntent, 0);
															}});;
									
			Button bnat = (Button) findViewById(R.id.nationalctc);
			bnat.setOnClickListener(new View.OnClickListener() {
						public void onClick(View view) {
						Intent myIntent = new Intent(view.getContext(), nationalctc.class);
						startActivityForResult(myIntent, 0);
															}});;
									
				Button bcontact = (Button)findViewById(R.id.officeinfo);
				bcontact.setOnClickListener(new View.OnClickListener() {
						public void onClick(View view) {
						Intent myIntent = new Intent(view.getContext(), contact.class);
						startActivityForResult(myIntent, 0);
															}});;						
}
	 public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu){
			
			
			
			//	menu.add("Get_Egrid")
				
				//	.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
	                			
				MenuInflater inflater=getSupportMenuInflater();
				inflater.inflate(R.menu.meclistmenubar, menu);
				
				
				return super.onCreateOptionsMenu(menu);
				}

	public boolean onOptionsItemSelected (com.actionbarsherlock.view.MenuItem item ){
					switch (item.getItemId()){
					
					case R.id.home :
						
						Intent myIntent1 = new Intent(getBaseContext(), PocketCal20.class);	
						startActivityForResult(myIntent1, 0);
					return true;	
					case R.id.back :
						
						Intent myIntent2 = new Intent(getBaseContext(), PocketCal20.class);	
						startActivityForResult(myIntent2, 0);
					return true;	
				
				
			}
					return false;
				}	
}
	 