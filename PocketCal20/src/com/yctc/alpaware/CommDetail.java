package com.yctc.alpaware;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;




public class CommDetail extends SherlockActivity {

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle Icicle) {
        super.onCreate(Icicle);
       setContentView(R.layout.commlist);
        
        
        // get id of person to list from calling activity
        Bundle b = this.getIntent().getExtras();  
        Long personID = b.getLong("pID");
        
        
        AlpaDataBaseHelper mdbh = new AlpaDataBaseHelper(this.getApplicationContext());
        final SQLiteDatabase db = mdbh.getWritableDatabase(); 
         
    
        Cursor cursor = db.rawQuery("Select _id, Committee,CommChair,CommEmail from MECCommittees where _id = " + personID,null);	
 			  
 		  		
 		 startManagingCursor(cursor);
 		 cursor.moveToFirst();
   		 
   		 
	 		  
	                 
	 		 TextView tv1 = (TextView)findViewById(R.id.committee);
	 		tv1.setText(cursor.getString(cursor.getColumnIndex("Committee"))); 
	 		
	 	
	 		final TextView tv2 = (TextView)findViewById(R.id.commchair);
	 		 tv2.setText(cursor.getString(cursor.getColumnIndex("CommChair")));
	 		
	 		 	
		 		 
		 			final TextView tv3 = (TextView)findViewById(R.id.commemail);
			 		tv3.setText(cursor.getString(cursor.getColumnIndex("CommEmail")));
			 		
			 		
			 		
			 		
			 		
			 		tv3.setOnClickListener(new View.OnClickListener() {
			 		public void onClick(View view) {
			 		String email = (String) tv3.getText();
			 		Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
	        		String[] recipients = new String[]{email};
	        		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, recipients);
	        		emailIntent.setType("text/plain");
	        		startActivity(Intent.createChooser(emailIntent, "Send mail..."));
}});	        			        			
	        		
    			 				 		
			 		
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
