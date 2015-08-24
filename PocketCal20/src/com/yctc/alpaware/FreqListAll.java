package com.yctc.alpaware;

import android.app.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;

public class FreqListAll extends SherlockActivity {
	
	
	
	
	public static final int Main_Menu =1;
	
	
	@Override
	 public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.fltlist);
       
       
       Bundle b = this.getIntent().getExtras();  
       String srchType = b.getString("Type");
       String sdata = b.getString("State").toUpperCase();
       
       
	
         AlpaDataBaseHelper mdbh = new AlpaDataBaseHelper(this.getApplicationContext());
	      final SQLiteDatabase db = mdbh.getWritableDatabase();
	
	      int names[] = {android.R.id.text1,android.R.id.text2};
	  	  
	 		  		
	 	 if (srchType.equals("ListAll")) {
	 	 
	 		
	 		final Cursor c = db.rawQuery("Select _id, State, Branding ||'  '|| Market ||'  '|| Notes  AS Fdata from ESPN  order by _id" ,null);	
	 		 final ListAdapter  adapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,c,	
	 				new String[] {"State","Fdata"},names);		  
	 		  		
	 		 startManagingCursor(c);
	 		 ListView lv = (ListView)findViewById(android.R.id.list);
	 		 lv.setAdapter(adapter);
		 }
			 
		if (srchType.equals("Filter")) {	 
				final Cursor c = db.rawQuery("Select _id, State, Branding ||'  '|| Market ||'  '|| Notes  AS Fdata from ESPN  where State  Like '" + sdata  +  "' " + " order by _id",null);	
			 final ListAdapter  adapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,c,	
		 				new String[] {"State","Fdata"},names);		  
		 		  		
		 		 startManagingCursor(c);
		 		 ListView lv = (ListView)findViewById(android.R.id.list);
		 		 lv.setAdapter(adapter); 
			 
		 }	 
			 
	}		 
public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu){
    	
    	
    	
    	//	menu.add("Get_Egrid")
    		
    		//	.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
                			
    		MenuInflater inflater=getSupportMenuInflater();
    		inflater.inflate(R.menu.expmainmenu, menu);
    		return super.onCreateOptionsMenu(menu);
    		}
    	
    
    
    
    
    public boolean onOptionsItemSelected (com.actionbarsherlock.view.MenuItem item ){
    			switch (item.getItemId()){
    			
    			
    			
    			case R.id.home :
    				Intent myIntent2 = new Intent(getBaseContext(), PocketCal20.class);	
    				startActivityForResult(myIntent2, 0);
    				
    			case R.id.back :
    				
    				Intent myIntent1 = new Intent(getBaseContext(), freqs.class);	
    				startActivityForResult(myIntent1, 0);
    			return true;
    			
    			
  	  			   
  	  			   
  	  		   }
    		
    		
    	
    			return false;

}	
	
	
	

}
