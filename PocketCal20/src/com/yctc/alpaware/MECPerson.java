package com.yctc.alpaware;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.telephony.PhoneNumberUtils;

import com.yctc.alpaware.PocketCal.OFFICERSMEC;
import com.yctc.alpaware.PocketCal.Staff;

public class MECPerson extends Activity {

	
	public static final int Main_Menu =0;    
	public static final int Menu_1 = 1;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle Icicle) {
        super.onCreate(Icicle);
        setContentView(R.layout.mecperson);
        
        
        // get id of person to list from calling activity
        Bundle b = this.getIntent().getExtras();  
        Long personID = b.getLong("pID");
        
        
       AlpaDataBaseHelper mdbh = new AlpaDataBaseHelper(this.getApplicationContext());
	      SQLiteDatabase db = mdbh.getWritableDatabase(); 
	       
	      
	      
	        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
	        qb.setTables(OFFICERSMEC.OFFICERSMEC_TABLE_NAME);
	        qb.appendWhere(OFFICERSMEC.OFFICERSMEC_ID + "=" + personID);
	        String Cols[] = {OFFICERSMEC.OFFICERSMEC_ID + "," + OFFICERSMEC.OFFICERSMEC_NAME +","+ OFFICERSMEC.OFFICERSMEC_POSITION
	        				+","+ OFFICERSMEC.OFFICERSMEC_TEL +","+ OFFICERSMEC.OFFICERSMEC_EMAIL +"," + OFFICERSMEC.OFFICERSMEC_PICTURE};
	       
	 		Cursor cursor = qb.query(db,Cols,null,null,null,null,Staff.DEFAULT_SORT_ORDER);		  
	 		 
	 		
	 		try {
	 		 startManagingCursor(cursor);
	 		 cursor.moveToFirst();
	 		//Toast.makeText(getBaseContext(),"data is " + cursor.getColumnIndex("_id") ,Toast.LENGTH_SHORT).show();
	 		 
	 		 ImageView myImage = (ImageView) findViewById(R.id.ipersonimage);
	      	byte[] blob = cursor.getBlob(cursor.getColumnIndex("Picture"));
	 		Bitmap bmp = BitmapFactory.decodeByteArray(blob, 0, blob.length); 
           myImage.setImageBitmap(bmp); 
	 		
            
                 
	                 
	 		 TextView tv1 = (TextView)findViewById(R.id.PersName);
	 		tv1.setText(cursor.getString(cursor.getColumnIndex("Name"))); 
	 		
	 	
	 		final TextView tv2 = (TextView)findViewById(R.id.PersPos);
	 		 tv2.setText(cursor.getString(cursor.getColumnIndex("Position")));
	 		 
	 		final TextView tv3 = (TextView)findViewById(R.id.PersExt);
	 		tv3.setText(cursor.getString(cursor.getColumnIndex("Tel")));
	 		 
	 		 
	 		 
	 		 
	 		
		 		
		 		
		 		tv3.setOnClickListener(new View.OnClickListener() {
	        	public void onClick(View view) {
	        		final String toDial;
	        		
	        		
	        		
	        		if ( tv3.getText().toString().length() < 5) {
	        			
	        			toDial = "901-752-8749,,,,#,,"  + tv3.getText().toString();
	        			
	        		//Toast.makeText(getBaseContext(),"data is " + toDial ,Toast.LENGTH_SHORT).show();
	        		 Intent call = new Intent(android.content.Intent.ACTION_CALL);
	        		 call.setData( Uri.parse("tel:"+  toDial));
	        		 
	        		 
			 		startActivity(call);
			 		//finish();
	        		}else{
	        			toDial = tv3.getText().toString();
	        			 Intent call = new Intent(android.content.Intent.ACTION_CALL);
		        		 call.setData( Uri.parse("tel:"+  toDial));
		        		 
		        		 startActivity(call);
		        	//	 finish();
	        			
	        		}
	        		}});
	 		 
	 		
		 		 
		 			final 	TextView tv4 = (TextView)findViewById(R.id.PersEmail);
			 		tv4.setText(cursor.getString(cursor.getColumnIndex("Email")));
			 		
			 		
			 		tv4.setOnClickListener(new View.OnClickListener() {
			 		public void onClick(View view) {
			 		String email = (String) tv4.getText();
			 		Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
	        		String[] recipients = new String[]{email};
	        		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, recipients);
	        		emailIntent.setType("text/plain");
	        		startActivity(Intent.createChooser(emailIntent, "Send mail..."));}});

	 		}catch (Exception e) {
    
	 			showaction("Database is not installed/or is damaged");
				Intent myIntent3 = new Intent(getBaseContext(), filemaintmain.class);	
				startActivityForResult(myIntent3, 0);
    
    
}
 
    
    
    
    
    }
    
    
    

public boolean onCreateOptionsMenu(Menu menu){
				menu.add(0,Main_Menu,0,"Main Menu");
				menu.add(0,Menu_1,0,"MEC Menu");
				return true;
				}

public boolean onOptionsItemSelected (MenuItem item ){
	switch (item.getItemId()){
	case Main_Menu :
		
		Intent myIntent = new Intent(getBaseContext(), PocketCal20.class);	
		startActivityForResult(myIntent, 0);
		
	return true;   

case Menu_1:

Intent myIntent4 = new Intent(getBaseContext(), mec.class);
	 startActivityForResult(myIntent4, 0);


	}
return false;
}
public void showaction(String msg){
				Context context = getApplicationContext();
				int duration = Toast.LENGTH_LONG;

				Toast toast = Toast.makeText(context, msg, duration);
				toast.show();
				}


}

