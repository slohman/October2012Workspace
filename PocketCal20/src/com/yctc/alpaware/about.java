package com.yctc.alpaware;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class about extends Activity{
	 public static final int Main_Menu =0;
	
	@Override
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.about);
	        
	        
	        TextView tv1 = (TextView)findViewById(R.id.tvaboutpcal);
	        tv1.setText(R.string.about);
}
	
	public boolean onCreateOptionsMenu(Menu menu){
				menu.add(0,Main_Menu,0,"Main Menu");
				
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