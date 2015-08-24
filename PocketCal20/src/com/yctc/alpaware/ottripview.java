package com.yctc.alpaware;

import java.io.File;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.webkit.WebView;

public class ottripview extends Activity{
	
	@Override
	public void onCreate(Bundle Icicle) {
	 super.onCreate(Icicle);
     setContentView(R.layout.tripviewer);
     
      WebView wv = (WebView)findViewById(R.id.tripviewer);
      wv.getSettings().setJavaScriptEnabled(true);
      wv.getSettings().setBuiltInZoomControls(true);
     
     Bundle b = this.getIntent().getExtras();
    
     AlpaDataBaseHelper mdbh = new AlpaDataBaseHelper(this.getApplicationContext());
		final SQLiteDatabase db = mdbh.getWritableDatabase(); 

		String Pairing = b.getString("prg").trim();
		String tripDate = b.getString("tripdate").trim();
		String SQL="select details from opentime where pairing = '" + Pairing + "' and TripDate = '" + tripDate + "'";
		Cursor c = db.rawQuery(SQL, null);
   
   
		c.moveToFirst();
		
		
		String tripdata = c.getString(c.getColumnIndex("details"));
		 File file = new File (tripdata);
		 wv.loadDataWithBaseURL("https://pilot.fedex.com", tripdata, "text/html", "UTF-8", "");
		
     c.close();
    
     
     
     
	

}

}
