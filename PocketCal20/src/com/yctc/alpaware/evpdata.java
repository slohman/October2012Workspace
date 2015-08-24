package com.yctc.alpaware;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;
import com.yctc.alpaware.PocketCal.MEC;


public class evpdata extends SherlockActivity{

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle Icicle) {
        super.onCreate(Icicle);
       setContentView(R.layout.mecreplist);
        
        
        // get id of person to list from calling activity
        Bundle b = this.getIntent().getExtras();  
        Long personID = b.getLong("pID");
        
        
        AlpaDataBaseHelper mdbh = new AlpaDataBaseHelper(this.getApplicationContext());
	      SQLiteDatabase db = mdbh.getWritableDatabase(); 
	       
	      
	      
	        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
	        qb.setTables(MEC.MEC_TABLE_NAME);
	        qb.appendWhere(MEC.MEC_ID + "=" + personID);
	        String Cols[] = {MEC.MEC_ID + "," + MEC.MEC_NAME +","+ MEC.MEC_POSITION
	        				+","+ MEC.MEC_TEL +","+ MEC.MEC_EMAIL +"," + MEC.MEC_PICTURE +"," + MEC.MEC_LEC + "," + MEC.MEC_BLOCK};
	       
	 		Cursor cursor = qb.query(db,Cols,null,null,null,null,MEC.DEFAULT_SORT_ORDER);		  
	 		  		
	 		 startManagingCursor(cursor);
	 		 cursor.moveToFirst();
	 		//Toast.makeText(getBaseContext(),"data is " + cursor.getColumnIndex("_id") ,Toast.LENGTH_SHORT).show();
	 		 
	 		 ImageView myImage = (ImageView) findViewById(R.id.iRepimage);
	      	byte[] blob = cursor.getBlob(cursor.getColumnIndex("Picture"));
	 		Bitmap bmp = BitmapFactory.decodeByteArray(blob, 0, blob.length); 
           myImage.setImageBitmap(bmp); 
	 		
            
                 
	                 
	 		 TextView tv1 = (TextView)findViewById(R.id.RepName);
	 		tv1.setText(cursor.getString(cursor.getColumnIndex("Name"))); 
	 		
	 	
	 		final TextView tv2 = (TextView)findViewById(R.id.RepPos);
	 		 tv2.setText(cursor.getString(cursor.getColumnIndex("Position")) );
	 		
	 		
	 		
	 		 
	 		final TextView tv3 = (TextView)findViewById(R.id.RepTel);
	 		tv3.setText(cursor.getString(cursor.getColumnIndex("Tel")));
	 		tv3.setOnClickListener(new View.OnClickListener() {
	        	public void onClick(View view) {
	        		final String toDial;
	        		toDial = tv3.getText().toString();
	        		
	        		
	        		 Intent call = new Intent(android.content.Intent.ACTION_CALL);
	        		 call.setData( Uri.parse("tel:"+ toDial));
	        		 
	        		 
			 		startActivity(call);
	        		finish();}});
	 		 
	 		
		 		 
		 			final 	TextView tv4 = (TextView)findViewById(R.id.RepEmail);
			 		tv4.setText(cursor.getString(cursor.getColumnIndex("Email")));
			 		
			 		
			 		tv4.setOnClickListener(new View.OnClickListener() {
			 		public void onClick(View view) {
			 		String email = (String) tv4.getText();
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
			}}
