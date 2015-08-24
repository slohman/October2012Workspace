package com.yctc.alpaware;

import com.yctc.alpaware.PocketCal.Staff;



import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;

import android.os.Bundle;

import android.view.View;

import android.widget.TextView;


public class Person extends SherlockActivity {

	
	
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle Icicle) {
        super.onCreate(Icicle);
        setContentView(R.layout.person);
        
        
        // get id of person to list from calling activity
        Bundle b = this.getIntent().getExtras();  
        Long personID = b.getLong("pID");
        
        
        AlpaDataBaseHelper mdbh = new AlpaDataBaseHelper(this.getApplicationContext());
	      SQLiteDatabase db = mdbh.getWritableDatabase(); 
	       
	      
	      
	        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
	        qb.setTables(Staff.STAFF_TABLE_NAME);
	        qb.appendWhere(Staff.STAFF_ID + "=" + personID);
	        String Cols[] = {Staff.STAFF_NAME + "," + Staff.STAFF_EXT +","+ Staff.STAFF_POS +","+ Staff.STAFF_EMAIL};
	       
	 		Cursor cursor = qb.query(db,Cols,null,null,null,null,Staff.DEFAULT_SORT_ORDER);		  
	 		  		
	 		 startManagingCursor(cursor);
	 		 cursor.moveToFirst();
	 		//Toast.makeText(getBaseContext(),"data is " + cursor.getColumnIndex("Position") ,Toast.LENGTH_SHORT).show();
	 		 
	 		 TextView tv1 = (TextView)findViewById(R.id.PersName);
	 		 tv1.setText(cursor.getString(cursor.getColumnIndex("Name")));
	 		
	                 
	                 
	 		 TextView tv2 = (TextView)findViewById(R.id.PersPos);
	 		tv2.setText(cursor.getString(cursor.getColumnIndex("Position"))); 
	 		
	 	
	 		final TextView tv3 = (TextView)findViewById(R.id.PersExt);
	 		 final String ext;
	 		 
	 		 
	 		 
	 		ext = cursor.getString(cursor.getColumnIndex("Extension"));
		 		tv3.setText(ext);
		 		
		 		tv3.setOnClickListener(new View.OnClickListener() {
	        	public void onClick(View view) {
	        		
	        		String toDial = ("9017528749,,," + ext);
	        		//String sExt =  (String) tv3.getText();
	        		//String source = "9017528749%20%20";
	        		//Toast.makeText(getBaseContext(),"data is " + toDial ,Toast.LENGTH_SHORT).show();
	        		 Intent call = new Intent(android.content.Intent.ACTION_CALL);
	        		 call.setData( Uri.parse("tel:"+ toDial));
	        		 
	        		 
			 		startActivity(call);
	        		finish();}});
		 		 
		 			final 	TextView tv4 = (TextView)findViewById(R.id.PersEmail);
			 		tv4.setText(cursor.getString(cursor.getColumnIndex("Email")));
			 		
			 		
			 		tv4.setOnClickListener(new View.OnClickListener() {
			 		public void onClick(View view) {
			 		String email = (String) tv4.getText();
			 		Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
	        		String[] recipients = new String[]{email};
	        		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, recipients);
	        		emailIntent.setType("text/plain");
	        		startActivity(Intent.createChooser(emailIntent, "Send mail..."));
}});}
    
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