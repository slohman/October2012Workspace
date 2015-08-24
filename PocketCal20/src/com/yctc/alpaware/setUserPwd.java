package com.yctc.alpaware;





import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class setUserPwd extends SherlockActivity{
	
	    
        
        
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setuserpwd);
        
       // read to see if data is present
        
        EditText uid = (EditText)findViewById(R.id.setUID);
        EditText pwd = (EditText)findViewById(R.id.setPWD);
        EditText base = (EditText)findViewById(R.id.etBase);
        EditText equip = (EditText)findViewById(R.id.etEq);
        EditText seat = (EditText)findViewById(R.id.seat);
        EditText ZL = (EditText)findViewById(R.id.tzorlocal);
        EditText BW = (EditText)findViewById(R.id.BWlvl);
        EditText KA = (EditText)findViewById(R.id.etkadelay);
        
        
   uid.setSelectAllOnFocus(true);     
   pwd.setSelectAllOnFocus(true);
   base.setSelectAllOnFocus(true);
   equip.setSelectAllOnFocus(true);
   seat.setSelectAllOnFocus(true);
   ZL.setSelectAllOnFocus(true); 
   BW.setSelectAllOnFocus(true);
   KA.setSelectAllOnFocus(true); 
        
      
	}
	
	
	public void saveuserdata(String username, String password, String Base, String equip, String seat, String Zlocal, String BWL, String KAD)  {
	
	
		String FILENAME = "PcalData";
		String data = username+","+password+","+Base+","+equip+","+seat+ ","+Zlocal+","+BWL+","+KAD+",";
		

		FileOutputStream fos;
		try {
			fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
		
		fos.write(data.getBytes());
		fos.close();

		utilities util = new utilities();
		util.showaction("Data Saved", this);
	
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
		
		}
	}
	
	public String readuserdata(){
		String usrdata = null;
		 FileInputStream fis = null;
	        try {
				fis = openFileInput("PcalData");
				
				 StringBuffer fileContent = new StringBuffer("");

			        byte[] buffer = new byte[1024];
			        
			        try {
			        	@SuppressWarnings("unused")
						int length = 0;
						while ((length = fis.read(buffer)) != -1) {
						    fileContent.append(new String(buffer));
						}
						
						usrdata = (fileContent.toString());
					    
			        } catch (IOException e) {
						return null;
					}
				
				
				
			} catch (FileNotFoundException e) {
				
				
				return null;
			}
	        return usrdata;
			
		
	}
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu){
		MenuInflater inflater=getSupportMenuInflater();
		inflater.inflate(R.menu.userdatamenu, menu);
		return super.onCreateOptionsMenu(menu);
		}

		public boolean onOptionsItemSelected (com.actionbarsherlock.view.MenuItem item ){
			switch (item.getItemId()){
			
			case R.id.home :
				
				Intent myIntent1 = new Intent(getBaseContext(), PocketCal20.class);	
				startActivityForResult(myIntent1, 0);
			
			
				return true;
			case R.id.egridmain :
				Intent myIntent4 = new Intent(getBaseContext(), egridmain1.class);	
				startActivityForResult(myIntent4, 0);
				return true;
				
			case R.id.savedata :
				String UID, PWD,BASE,EQ, SEAT,Zlocal,BWL,KAD;
            	 
            	 EditText uid = (EditText)findViewById(R.id.setUID);
                 EditText pwd = (EditText)findViewById(R.id.setPWD);
                 EditText base = (EditText)findViewById(R.id.etBase);
                 EditText equip = (EditText)findViewById(R.id.etEq);
                 EditText seat = (EditText)findViewById(R.id.seat);
                 EditText ZL = (EditText)findViewById(R.id.tzorlocal);
                 EditText BW = (EditText)findViewById(R.id.BWlvl);
                 EditText KA = (EditText)findViewById(R.id.etkadelay);
                 
                 UID = uid.getText().toString();
                 PWD = pwd.getText().toString();
                 BASE = base.getText().toString();
                 EQ = equip.getText().toString();
                 SEAT = seat.getText().toString();
                 Zlocal = ZL.getText().toString();
                 BWL = BW.getText().toString();
                 KAD = KA.getText().toString();
                
                 
            
        saveuserdata(UID,PWD, BASE, EQ, SEAT, Zlocal,BWL,KAD);
        
        return true;
        
        
			case R.id.readuserdata:
				
				
	             
	       
	            
	        String udata = readuserdata();
	        if (udata == null){
	        	Toast.makeText(setUserPwd.this, "No Saved User Data,Please Enter Your Data", Toast.LENGTH_LONG).show();
	        				  }else{
	        EditText uid1 = (EditText)findViewById(R.id.setUID);
                 EditText pwd1 = (EditText)findViewById(R.id.setPWD);
                 EditText base1 = (EditText)findViewById(R.id.etBase);
                 EditText equip1 = (EditText)findViewById(R.id.etEq);
                 EditText seat1 = (EditText)findViewById(R.id.seat);
                 EditText ZL1 = (EditText)findViewById(R.id.tzorlocal);
                 EditText BW1 = (EditText)findViewById(R.id.BWlvl);
                 EditText KA1 = (EditText)findViewById(R.id.etkadelay);	
	        			String[] userdata = udata.split(",");
	        			uid1.setText(userdata[0].toString());
	        			pwd1.setText(userdata[1].toString().trim());
	        			base1.setText(userdata[2].toString().trim());
	        			equip1.setText(userdata[3].toString().trim());
	        			seat1.setText(userdata[4].toString().trim());
	        			ZL1.setText(userdata[5].toString().trim());
	        			BW1.setText(userdata[6].toString().trim());
	        			KA1.setText(userdata[7].toString().trim());
	        				  }
				return true;		
	}
			return false;
		}
	
}
