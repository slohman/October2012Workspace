package com.yctc.alpaware;

import com.actionbarsherlock.view.MenuInflater;

import android.app.Activity;

import android.content.Intent;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;

public class iemain extends SherlockActivity {

	public static final int Main_Menu =0; 

	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.iemain);
	        
	              
	        
	        Button bifalpa = (Button) findViewById(R.id.ifalpaemer);
	        bifalpa.setOnClickListener(new View.OnClickListener() {
	        	public void onClick(View view) {
	        		 Intent myIntent = new Intent(view.getContext(), ifalpa.class);
	                 startActivityForResult(myIntent, 0);
}});;

			Button busemb = (Button) findViewById(R.id.usembassy);
				busemb.setOnClickListener(new View.OnClickListener() {
					public void onClick(View view) {
						Intent myIntent = new Intent(view.getContext(), usembassy.class);
						startActivityForResult(myIntent, 0);
}});;

			Button bir = (Button) findViewById(R.id.intemerresponse);
				bir.setOnClickListener(new View.OnClickListener() {
						public void onClick(View view) {
							Intent myIntent = new Intent(view.getContext(), ieresponse.class);
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
						
						super.onBackPressed();
					return true;	
				
				
			}
					return false;
				}	
}
