package com.yctc.alpaware;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class usembassy extends SherlockActivity {
	public Cursor c;
	Context mContext;
	
	public static final int Main_Menu =0;    
	public static final int Menu_1 = 1;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle Icicle) {
        super.onCreate(Icicle);
        setContentView(R.layout.staff);
        
          AlpaDataBaseHelper mdbh = new AlpaDataBaseHelper(this.getApplicationContext());
          final SQLiteDatabase db = mdbh.getWritableDatabase(); 
       
      
      
      
       
     int names[] = {android.R.id.text1,android.R.id.text2};
 		Cursor c = db.rawQuery("Select _id, Country,APID from IntEmer where type =" + 2,null);		  
 		  		
 		 startManagingCursor(c);
 	

 		  	  	 
 		final ListAdapter adapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,c, 
	 			new String[] {"Country", "APID"}, 
	 			names);
    
 	ListView lv = (ListView)findViewById(android.R.id.list);
 	lv.setAdapter(adapter);
    	
   
  lv.setOnItemClickListener( new AdapterView.OnItemClickListener() {
 	public void onItemClick(AdapterView<?> parent, View v, int position, long id)
 	{
 	
 		Bundle b = new Bundle();
 		b.putLong("pID", id);
 		Intent myIntent = new Intent(getBaseContext(), usembassydetail.class);
 		myIntent.putExtras(b);
 		
        startActivityForResult(myIntent, 0);
 	
 	}
 	});
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

