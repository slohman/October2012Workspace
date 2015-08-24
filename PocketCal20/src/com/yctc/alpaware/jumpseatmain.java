package com.yctc.alpaware;

import java.io.File;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

public class jumpseatmain extends SherlockActivity{

	
	
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jumpsetmain);
        
        
        Button viewjmpseat = (Button) findViewById(R.id.viewjupseatinfo);
        viewjmpseat.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        		
        		String s = "Jumpseat_Guide.pdf";
        		ShowFile(s);
        		
           	}}); 
        
        Button casdata = (Button) findViewById(R.id.casdatabase);
        casdata.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        		Intent myIntent = new Intent(getBaseContext(), jumpseatlist.class);
	    	 	startActivityForResult(myIntent, 0);
           	}});  
        
        Button noncasdata = (Button) findViewById(R.id.noncass);
        noncasdata.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        		Intent myIntent = new Intent(getBaseContext(), jumpseatlistnoncass.class);
	    	 	startActivityForResult(myIntent, 0);
           	}});
        
       
        
        
        
        
}
	
public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu){
		
		
		
                			
			MenuInflater inflater=getSupportMenuInflater();
			inflater.inflate(R.menu.filemaintmenubar, menu);
			return super.onCreateOptionsMenu(menu);
			}

			public boolean onOptionsItemSelected (com.actionbarsherlock.view.MenuItem item ){
				switch (item.getItemId()){
				
				case R.id.home :
					
					Intent myIntent1 = new Intent(getBaseContext(), PocketCal20.class);	
					startActivityForResult(myIntent1, 0);
				return true;	
					
			
			
		}
				return false;
			}	
	private   void ShowFile(String s) {
		
		
		Intent intent = new Intent();
		 intent.setAction(android.content.Intent.ACTION_VIEW);
		 File sd = new File(Environment.getExternalStorageDirectory().toString() + "/cba/");
		 File file = new File(sd + "/" + s);
		 intent.setDataAndType(Uri.fromFile(file), "application/pdf");  
		 startActivity(intent);
	}
}