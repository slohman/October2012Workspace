package com.yctc.alpaware;


import com.yctc.alpaware.PocketCal.GTHOT;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;




public class gthotelupdate extends Activity {
	
	
	public static final int Main_Menu =0;
	public static final int GT_Menu = 1;
	public static final int Update_Current=2;
	
	
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.gtupdatedisplay);
	        
	        
	        Bundle b = this.getIntent().getExtras();  
	        Long irecID = b.getLong("pID");
     
     
      
	        UserDataHelper mdbh = new  UserDataHelper(this.getApplicationContext());
		      final SQLiteDatabase db = mdbh.getWritableDatabase();
	      
	 		final Cursor c = db.rawQuery("Select _id,ID,City,state,RampTel,RampTel1,RampTel2,EMAIL,Hotel,HotelNum,GT,Notes from hotel where _id = " + irecID ,null);		  
	 		startManagingCursor(c);
	 		c.moveToFirst();
	 		LoadScreen(c);
	
	
	
	
}
	 public void LoadScreen(Cursor c){
		 	TextView tvrecID = (TextView)findViewById(R.id.recid);
	 		EditText etid = (EditText)findViewById(R.id.upcityid);
	 		EditText etcity = (EditText)findViewById(R.id.upcity);
	 		EditText etstate = (EditText)findViewById(R.id.upstate);
	 		EditText etrampTel = (EditText)findViewById(R.id.upramptel);
	 		EditText etrampTel1 = (EditText)findViewById(R.id.upramptel1);
	 		EditText etrampTel2 = (EditText)findViewById(R.id.upramptel2);
	 		EditText etrampEmail = (EditText)findViewById(R.id.uprampemail);
	 		EditText ethotelName = (EditText)findViewById(R.id.uphotelname);
	 		EditText ethotelNum= (EditText)findViewById(R.id.uphotelnum);
	 		EditText etgt = (EditText)findViewById(R.id.upgtinfo);
	 		EditText etnotes = (EditText)findViewById(R.id.upnotes);
	 		tvrecID.setText(c.getString(c.getColumnIndex("_id")));	
	 		etid.setText(c.getString(c.getColumnIndex("ID")));
	 		etcity.setText((c.getString(c.getColumnIndex("City"))));
	 		etstate.setText((c.getString(c.getColumnIndex("state"))));
	 		etrampTel.setText(c.getString(c.getColumnIndex("RampTel")));	
	 		etrampTel1.setText(c.getString(c.getColumnIndex("RampTel1")));
	 		etrampTel2.setText(c.getString(c.getColumnIndex("RampTel2")));
	 		etrampEmail.setText(c.getString(c.getColumnIndex("EMAIL")));	
	 		ethotelName.setText(c.getString(c.getColumnIndex("Hotel")));
	 		ethotelNum.setText(c.getString(c.getColumnIndex("HotelNum")));
	 		etgt.setText(c.getString(c.getColumnIndex("GT")));	
	 		etnotes.setText(c.getString(c.getColumnIndex("Notes")));	
	 }		
	 		  
	 public boolean onCreateOptionsMenu(Menu menu){
					menu.add(0,Main_Menu,0,"Main Menu");
					menu.add(0,GT_Menu,0,"GT Main Menu");
					menu.add(0,Update_Current,0,"Update Current Record");
					return true;
					}

				
	 public boolean onOptionsItemSelected (MenuItem item ){
					switch (item.getItemId()){
					case Main_Menu :
						
						Intent myIntent = new Intent(getBaseContext(), PocketCal20.class);	
						startActivityForResult(myIntent, 0);
					return true;
				
					case GT_Menu :
					
					Intent myIntent1 = new Intent(getBaseContext(), ramphotelmain.class);	
					startActivityForResult(myIntent1, 0);
				return true;
				
					
					case Update_Current:
						
						
						TextView tvrecID = (TextView)findViewById(R.id.recid);
				 		EditText etid = (EditText)findViewById(R.id.upcityid);
				 		EditText etcity = (EditText)findViewById(R.id.upcity);
				 		EditText etstate = (EditText)findViewById(R.id.upstate);
				 		EditText etrampTel = (EditText)findViewById(R.id.upramptel);
				 		EditText etrampTel1 = (EditText)findViewById(R.id.upramptel1);
				 		EditText etrampTel2 = (EditText)findViewById(R.id.upramptel2);
				 		EditText etrampEmail = (EditText)findViewById(R.id.uprampemail);
				 		EditText ethotelName = (EditText)findViewById(R.id.uphotelname);
				 		EditText ethotelNum= (EditText)findViewById(R.id.uphotelnum);
				 		EditText etgt = (EditText)findViewById(R.id.upgtinfo);
				 		EditText etnotes = (EditText)findViewById(R.id.upnotes);
				 		
				 		String recID = tvrecID.getText().toString();
				 		int irecID = Integer.parseInt(recID);
				 		
				 		String setID= etid.getText().toString();
				 		String scity  = etcity.getText().toString();
				 		String sstate = etstate.getText().toString();
				 		String sramptel = etrampTel.getText().toString();
				 		String sramptel1 = etrampTel1.getText().toString();
				 		String sramptel2 = etrampTel2.getText().toString();
				 		String semail = etrampEmail.getText().toString();	
				 		String shotelName = ethotelName.getText().toString();
				 		String shotelNum = ethotelNum.getText().toString();
				 		String sGT = etgt.getText().toString();	
				 		String snotes = etnotes.getText().toString();
				 		
				 		
						ContentValues updateValues = new ContentValues();
					     updateValues.put(GTHOT.GTHOT_rID,recID) ;
					     updateValues.put(GTHOT.GTHOT_ID, setID);
					     updateValues.put(GTHOT.GTHOT_CITY, scity);
					     updateValues.put(GTHOT.GTHOT_STATE, sstate);
					     updateValues.put(GTHOT.GTHOT_RAMPTEL, sramptel);
					     updateValues.put(GTHOT.GTHOT_RAMPTEL1, sramptel1);
					     updateValues.put(GTHOT.GTHOT_RAMPTEL2, sramptel2);
					     updateValues.put(GTHOT.GTHOT_EMAIL, semail);
					     updateValues.put(GTHOT.GTHOT_HOTEL, shotelName);
					     updateValues.put(GTHOT.GTHOT_HOTELNUM,shotelNum );
					     updateValues.put(GTHOT.GTHOT_GT, sGT);
					     updateValues.put(GTHOT.GTHOT_NOTES, snotes);
					     
					    
					      
					      try {
					    	  
					     UserDataHelper mdbh = new  UserDataHelper(this.getApplicationContext());
					      final SQLiteDatabase db = mdbh.getWritableDatabase();
					     db.update(GTHOT.GTHOTTABLE_NAME, updateValues, GTHOT.GTHOT_rID + "=" + irecID , null);
					     utilities util = new utilities();
					     Context ctx = getApplicationContext();
					     util.showaction("Record " + irecID + " updated!", ctx);
					      } catch (Exception e) {
					    	  utilities util = new utilities();
							  Context ctx = getApplicationContext();
							  util.showaction("Record " + irecID + " update Failed!", ctx);
					    	  
					      }
					      
					 	
	  	    	 		
	  	    	 		return  true;
					}	
			
					return false;
				}
				
		
}
