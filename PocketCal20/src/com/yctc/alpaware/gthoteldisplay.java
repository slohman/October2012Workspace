package com.yctc.alpaware;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;

public class gthoteldisplay extends Activity{

	public static final int Main_Menu =0;
	 public static final int GT_Menu = 1;
	 public static final int Delete_Current = 2;
	 public static final int Update_Current=3;
     public int	 recNum;
   

	@Override
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.ghhoteldisplay);
	        
	        Bundle b = this.getIntent().getExtras();  
	       
	        recNum = b.getInt("pID");
	        
	        AlpaDataBaseHelper mdbh = new  AlpaDataBaseHelper(this.getApplicationContext());
		      final SQLiteDatabase db = mdbh.getWritableDatabase(); 
	
		      String qry = "Select * from hotel where _id = " + recNum ;
		 		final Cursor c = db.rawQuery(qry,null);		  
		 		startManagingCursor(c);
		 		c.moveToFirst();
		 		LoadScreen(c);
	
		 		 final Button brampTel = (Button) findViewById(R.id.ramptel);
		 		 brampTel.setOnClickListener(new View.OnClickListener() {
		 		    	public void onClick(View view) {
		 		    		    		 
		 		    		final String toDial;
			        		toDial = brampTel.getText().toString();
			        		Intent call = new Intent(android.content.Intent.ACTION_CALL);
			        		call.setData( Uri.parse("tel:"+ toDial));
			        		startActivity(call);
			        		finish();
			        		}});  
		 		 final Button brampTel1 = (Button) findViewById(R.id.ramptel1);
		 		 brampTel1.setOnClickListener(new View.OnClickListener() {
		 		    	public void onClick(View view) {
		 		    		    		 
		 		    		final String toDial;
			        		toDial = brampTel.getText().toString();
			        		Intent call = new Intent(android.content.Intent.ACTION_CALL);
			        		call.setData( Uri.parse("tel:"+ toDial));
			        		startActivity(call);
			        		finish();
			        		}});  
		 		 final Button brampTel2 = (Button) findViewById(R.id.ramptel2);
		 		 brampTel2.setOnClickListener(new View.OnClickListener() {
		 		    	public void onClick(View view) {
		 		    		    		 
		 		    		final String toDial;
			        		toDial = brampTel.getText().toString();
			        		Intent call = new Intent(android.content.Intent.ACTION_CALL);
			        		call.setData( Uri.parse("tel:"+ toDial));
			        		startActivity(call);
			        		finish();
			        		}});  
		 		final Button bhotelNum = (Button) findViewById(R.id.hotelnum);
		 		 bhotelNum.setOnClickListener(new View.OnClickListener() {
		 		    	public void onClick(View view) {
		 		    		    		 
		 		    		final String toDial;
			        		toDial = bhotelNum.getText().toString();
			        		Intent call = new Intent(android.content.Intent.ACTION_CALL);
			        		call.setData( Uri.parse("tel:"+ toDial));
			        		startActivity(call);
			        		finish();
			        		}}); 
		 		final Button bGT = (Button) findViewById(R.id.gtinfo);
		 		 bGT.setOnClickListener(new View.OnClickListener() {
		 		    	public void onClick(View view) {
		 		    		    		 
		 		    		final String toDial;
			        		toDial =bGT.getText().toString();
			        		Intent call = new Intent(android.content.Intent.ACTION_CALL);
			        		call.setData( Uri.parse("tel:"+ toDial));
			        		startActivity(call);
			        		finish();
			        		}}); 
	
	}
	
	
		 		public void LoadScreen(Cursor c){
		 			
		 		TextView tvid = (TextView)findViewById(R.id.cityid);
		 		TextView tvcity = (TextView)findViewById(R.id.city);
		 		
		 		Button tvrampTel = (Button)findViewById(R.id.ramptel);
		 		Button tvrampTel1 = (Button)findViewById(R.id.ramptel1);
		 		Button tvrampTel2 = (Button)findViewById(R.id.ramptel2);
		 		TextView tvrampEmail = (TextView)findViewById(R.id.rampemail);
		 		TextView tvhotelName = (TextView)findViewById(R.id.hotelname);
		 		Button tvhotelNum= (Button)findViewById(R.id.hotelnum);
		 		Button tvgt = (Button)findViewById(R.id.gtinfo);
		 		TextView tvnotes = (TextView)findViewById(R.id.notes);
		 			
		 		tvid.setText(c.getString(c.getColumnIndex("ID")));
		 		
		 		String City = (c.getString(c.getColumnIndex("City")));
		 		String State = (c.getString(c.getColumnIndex("State")));
		 		tvcity.setText(City + ", " + State);
		 			
		 		tvrampTel.setText(c.getString(c.getColumnIndex("Ramptel")));	
		 		tvrampTel1.setText(c.getString(c.getColumnIndex("Ramptel1")));
		 		tvrampTel2.setText(c.getString(c.getColumnIndex("Ramptel2")));
		 		tvrampEmail.setText(c.getString(c.getColumnIndex("Email")));	
		 		tvhotelName.setText(c.getString(c.getColumnIndex("Hotel")));
		 		tvhotelNum.setText(c.getString(c.getColumnIndex("Hotelnum")));
		 		tvgt.setText(c.getString(c.getColumnIndex("GT")));	
		 		tvnotes.setText(c.getString(c.getColumnIndex("Notes")));	
		 		
		 		}
	
	  public boolean onCreateOptionsMenu(Menu menu){
				menu.add(0,Main_Menu,0,"Main Menu");
				menu.add(0,GT_Menu,0,"Back");
				
				
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
			
				
					
		}
				return false;
			}


		
			
	
}
