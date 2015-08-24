package com.yctc.alpaware;



import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;

public class aclistbyfleet extends SherlockActivity{
	


	@Override
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.fltlist);
	        
	        Bundle b = this.getIntent().getExtras();  
	        final String acFleetTyp = b.getString("dID");
	
	 AlpaDataBaseHelper mdbh = new AlpaDataBaseHelper(this.getApplicationContext());
	      final SQLiteDatabase db = mdbh.getWritableDatabase();
	
	      int names[] = {android.R.id.text1,android.R.id.text2};
	 		final Cursor c = db.rawQuery("Select _id, ac_TYP, ac_NUM  from ACDATA where ac_TYP  like  " + "'%" + acFleetTyp + "%'"  ,null);		  
	 		  		
	 	 		  	  	 
	 		final ListAdapter  adapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,c,	new String[] {"ac_TYP","ac_NUM"},names);		  
	 		  		
	 		 startManagingCursor(c);
	 	
	 		 if (c.getCount() < 1) {
	 			 
	 			 
	 			 utilities util = new utilities();
	 	    	 Context ctx = getApplicationContext();
	 	    	 util.showaction("Type not on file !", ctx);
	 	    	 
	 	    	 Intent myIntent = new Intent(getBaseContext(), PocketCal20.class);
	    	 	 startActivityForResult(myIntent, 0);
	 		 }
	 		  	  	 
	 
	    
	 	ListView lv = (ListView)findViewById(android.R.id.list);
	 	lv.setAdapter(adapter);
	    	
	   
	  lv.setOnItemClickListener( new AdapterView.OnItemClickListener() {
	 	public void onItemClick(AdapterView<?> parent, View v, int position, long id)
	 	{
	 	
	 		Bundle b = new Bundle();
	 		c.moveToPosition(position);
	 		b.putLong("dID", c.getLong(c.getColumnIndex("ac_NUM")));
	 		Intent myIntent = new Intent(getBaseContext(),aclistout.class);
	 		myIntent.putExtras(b);
	 		
            startActivityForResult(myIntent, 0);
	 	
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
	    				
	    				Intent myIntent2 = new Intent(getBaseContext(), PocketCal20.class);	
	    				startActivityForResult(myIntent2, 0);
	    			return true;	
	    		
	    		
	    	}
	    			return false;
	    		}   
}
