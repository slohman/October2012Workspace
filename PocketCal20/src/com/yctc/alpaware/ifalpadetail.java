package com.yctc.alpaware;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;


public class ifalpadetail extends SherlockActivity{
	 public static final int Main_Menu =0;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle Icicle) {
        super.onCreate(Icicle);
       setContentView(R.layout.ifalpadetail);
        
        
        // get id of person to list from calling activity
        Bundle b = this.getIntent().getExtras();  
        Long countryID = b.getLong("pID");
        
        
        AlpaDataBaseHelper mdbh = new AlpaDataBaseHelper(this.getApplicationContext());
        final SQLiteDatabase db = mdbh.getWritableDatabase(); 
         
      
        Cursor cursor = db.rawQuery("Select _id, Country,Association,Tel from IntEmer where _id = " + countryID,null);	
 			  
 		  		
 		 startManagingCursor(cursor);
 		 cursor.moveToFirst();
   		 
   		 
	 		  
	                 
	 		
	 		
	 		
	 	
	 		final TextView tv2 = (TextView)findViewById(R.id.Country);
	 		 tv2.setText(cursor.getString(cursor.getColumnIndex("Country")));
	 		
	 		 	
		 		 
		 			final TextView tv3 = (TextView)findViewById(R.id.Association);
			 		tv3.setText(cursor.getString(cursor.getColumnIndex("Association")));
			 		
			 		//final TextView tv1 = (TextView)findViewById(R.id.Telformat);
			 		
			 		final TextView tv4 = (TextView)findViewById(R.id.Phone);
			 		tv4.setText(cursor.getString(cursor.getColumnIndex("Tel")));
			 		
			 		
			 		tv4.setOnClickListener(new View.OnClickListener() {
			 		public void onClick(View view) {
			 			final String toDial;
		        		toDial = tv4.getText().toString();
		        		
		        		//Toast.makeText(getBaseContext(),"data is " + toDial ,Toast.LENGTH_SHORT).show();
		        		 Intent call = new Intent(android.content.Intent.ACTION_CALL);
		        		 call.setData( Uri.parse("tel:"+ toDial));
		        		 
		        		 
				 		startActivity(call);
		        		finish();}});
 		 		
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

