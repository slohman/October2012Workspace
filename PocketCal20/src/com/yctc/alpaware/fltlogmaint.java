package com.yctc.alpaware;

import java.io.BufferedWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;




import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;




public class fltlogmaint extends SherlockActivity {
	
	String sMsg,result;
	
	utilities util = new utilities();
	 public static final int Main_Menu =0;
	 public static final int Maint_Menu = 1;
	 
	 
	 File dataPath = Environment.getDataDirectory();
	 File sd = new File(Environment.getExternalStorageDirectory().toString() + "/PocketCalDatabases/");
	File DataDir = new File(Environment.getDataDirectory().toString() + "/data/com.yctc.alpaware/databases/");
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fltlogmaint);
        
        
       
	
	
	
	Button sendcsv = (Button) findViewById(R.id.sendcsv);
	sendcsv.setOnClickListener(new View.OnClickListener() {
    	public void onClick(View view) {
    		try {
				sendcsv();
			} catch (IOException e) {e.printStackTrace();
			} 
    	}});
	
}
	
	
	
        public void sendcsv()throws IOException {
        //select all database records then build SD file
        	String fltdata;
        	
        	
        	UserDataHelper mdbh = new UserDataHelper(this.getApplicationContext());
   	        final SQLiteDatabase db = mdbh.getWritableDatabase(); 
   	       	final Cursor c = db.rawQuery("Select  * from fltlog  order by Date ASC" ,null);		  
   	 		startManagingCursor(c);
   	 		c.moveToFirst();
   	 		fltdata="_id,Date,Flt,AC,frm,dest,out,off,onn,inn,blk,bflt,land,typ,comm,cargo" + "\r\n";
   	 	for (int i = 0; i <= c.getCount() - 1;i++) {
   	 		 fltdata=fltdata + c.getString(c.getColumnIndex("_id")) + "," +
   	 		c.getString(c.getColumnIndex("Date")) +  "," +
   	 	    c.getString(c.getColumnIndex("Flt")) +  "," +
   	 	    c.getString(c.getColumnIndex("AC")) +  "," +
   	 	    c.getString(c.getColumnIndex("frm")) +  "," +
   	 	    c.getString(c.getColumnIndex("dest")) +  "," +
   	 	    c.getString(c.getColumnIndex("out")) + "," +
   	 	   
   	 	   
   	 	    c.getString(c.getColumnIndex("inn")) +  "," +
   	 	    c.getString(c.getColumnIndex("blk")) + "," +
   	 	   
   	 	    c.getString(c.getColumnIndex("land")) + "," +
   	 	    c.getString(c.getColumnIndex("typ")) + "," +
   	 	    c.getString(c.getColumnIndex("comm")) + "," +
   	 	    c.getString(c.getColumnIndex("cargo")) + "\r\n"; 		
   	 		c.moveToNext();
   	 	}	
   	 try {
   	    File sd = Environment.getExternalStorageDirectory();
   	    
   	    if (sd.canWrite()){
   	        File csvfile = new File(sd + "/PocketCalDatabases/", "fltlog.csv");
   	        FileWriter gpxwriter = new FileWriter(csvfile);
   	        BufferedWriter out = new BufferedWriter(gpxwriter);
   	        out.write(fltdata);
   	        out.close();
   	    }
   	} catch (IOException e) {
   	    Log.e("file", "Could not write file " + e.getMessage());
   	}
   	String email = (String) "";
	Intent sendIntent = new Intent(Intent.ACTION_SEND);
	sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Copy of FLtlog DB");
	String[] recipients = new String[]{email};
	sendIntent.putExtra(android.content.Intent.EXTRA_EMAIL, recipients);
	
	sendIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse
		("file://"+ sd +"/PocketCalDatabases/fltlog.csv"));
	sendIntent.setType("plain/text"); 
	startActivity(Intent.createChooser(sendIntent, "Send Mail ...."));
	
        }
        
        
       
public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu){
	    	
	    	
	    	
	    	//	menu.add("Get_Egrid")
	    		
	    		//	.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
	                			
	    		MenuInflater inflater=getSupportMenuInflater();
	    		inflater.inflate(R.menu.fltreportsmenu, menu);
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
            	
	  