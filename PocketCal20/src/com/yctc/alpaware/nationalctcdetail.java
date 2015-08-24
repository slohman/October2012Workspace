package com.yctc.alpaware;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;


public class nationalctcdetail extends SherlockActivity {
	@Override
    public void onCreate(Bundle Icicle) {
        super.onCreate(Icicle);
       setContentView(R.layout.nationalctcdetail);
        
        
        // get id of person to list from calling activity
        Bundle b = this.getIntent().getExtras();  
        Long deptID = b.getLong("pID");
        
        
        AlpaDataBaseHelper mdbh = new AlpaDataBaseHelper(this.getApplicationContext());
        final SQLiteDatabase db = mdbh.getWritableDatabase(); 
         
      
        Cursor cursor = db.rawQuery("Select _id, Email,Department,tel from ALPANational where _id = " + deptID,null);	
 			 
 		  		
 		 startManagingCursor(cursor);
 		 cursor.moveToFirst();
   		 
   		 
	 		  
	                 
	 		
	 		
	 	
	 		final TextView tv1 = (TextView)findViewById(R.id.Department);
	 		 tv1.setText(cursor.getString(cursor.getColumnIndex("Department")));
	 		
	 		 	
		 		 
		 			final TextView tv2 = (TextView)findViewById(R.id.Email);
			 		tv2.setText(cursor.getString(cursor.getColumnIndex("Email")));
			 		tv2.setOnClickListener(new View.OnClickListener() {
				 		public void onClick(View view) {
				 		String email = (String) tv2.getText();
				 		Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
		        		String[] recipients = new String[]{email};
		        		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, recipients);
		        		emailIntent.setType("text/plain");
		        		startActivity(Intent.createChooser(emailIntent, "Send mail..."));
				 		}});
			 		
			 		
			 		final TextView tv3 = (TextView)findViewById(R.id.Tel);
			 		tv3.setText( cursor.getString(cursor.getColumnIndex("tel")));
			 		tv3.setOnClickListener(new View.OnClickListener() {
			 		public void onClick(View view) {
			 			final String toDial;
		        		toDial = tv3.getText().toString();
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


