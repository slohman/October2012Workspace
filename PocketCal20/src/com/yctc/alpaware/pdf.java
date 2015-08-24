package com.yctc.alpaware;

import java.io.File;





import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

import android.widget.Button;

import android.view.View;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;
import android.content.Intent;


public class pdf extends SherlockActivity {


	public static final int Main_Menu =0;


@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.pdf);
    
    
    Button fdx = (Button) findViewById(R.id.fedex);
    fdx.setOnClickListener(new View.OnClickListener() {
    	public void onClick(View view) {
    		

    		
    		String s = "FedEx.pdf";
    		ShowFile(s);
    		
    			
    	}});
    
    Button travelinfo = (Button) findViewById(R.id.travelguide);
    travelinfo.setOnClickListener(new View.OnClickListener() {
    	public void onClick(View view) {
    		

    		
    		String s = "Travel_Info.pdf";
    		
    		ShowFile(s);
    		
    			
    	}});
    
    Button ftdt = (Button) findViewById(R.id.ftdt);
    ftdt.setOnClickListener(new View.OnClickListener() {
    	public void onClick(View view) {
    			String s = "FTDTv2.pdf";
    			ShowFile(s);
 }});
    Button contract = (Button) findViewById(R.id.contract);
    contract.setOnClickListener(new View.OnClickListener() {
    	public void onClick(View view) {
    			String s = "Contract_Info.pdf";
    			ShowFile(s);
 }});
    Button jmp = (Button) findViewById(R.id.jmpguide);
    jmp.setOnClickListener(new View.OnClickListener() {
    	public void onClick(View view) {
    			String s = "Jumpseat_Guide.pdf";
    			ShowFile(s);
 }});
    

    
}
    void ShowFile(String s) {
    	
    	File sd = new File(Environment.getExternalStorageDirectory().toString() + "/cba/");
    	Intent intent = new Intent();
    	 intent.setAction(android.content.Intent.ACTION_VIEW);
    	 File file = new File(sd + "/" + s);
    	 intent.setDataAndType(Uri.fromFile(file), "application/pdf");  
    	 startActivity(intent);
    }
    
public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu){
		
		
		
		//	menu.add("Get_Egrid")
			
			//	.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
                			
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
}
