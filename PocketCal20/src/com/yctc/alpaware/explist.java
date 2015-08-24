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

public class explist extends SherlockActivity{

	public Cursor c;
	Context mContext;
	
	

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle Icicle) {
        super.onCreate(Icicle);
        setContentView(R.layout.explist);
        
        
        Bundle b = this.getIntent().getExtras();  
        String srchDate = b.getString("dID");
        
        
         UserDataHelper mdbh = new UserDataHelper(this.getApplicationContext());
      final SQLiteDatabase db = mdbh.getWritableDatabase(); 
       
      
      
     int names[] = {android.R.id.text1,android.R.id.text2};
 		final Cursor c = db.rawQuery("Select _id, exp_DATE, exp_CITY ||'   '|| exp_AMT ||' '|| exp_CAT ||' ' || exp_COMM  AS Edata from EXP where exp_Date >= '" + srchDate  +  "' order by exp_Date ASC" ,null);		  
 		  		
 		
 	

 		  	  	 
 		final ListAdapter adapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,c, 
	 			new String[] {"exp_DATE","Edata"}, 
	 			names);		  
 		  		
 		 startManagingCursor(c);
 	

 		  	  	 
 
    
 	ListView lv = (ListView)findViewById(android.R.id.list);
 	lv.setAdapter(adapter);
    	
   
  lv.setOnItemClickListener( new AdapterView.OnItemClickListener() {
 	public void onItemClick(AdapterView<?> parent, View v, int position, long id)
 	{
 	// Display a messagebox.
 		Bundle b = new Bundle();
 		c.moveToPosition(position);
 		b.putLong("pID", c.getLong(c.getColumnIndex("_id")));
 		Intent myIntent = new Intent(getBaseContext(),expupdate1.class);
 		myIntent.putExtras(b);
 		
        startActivityForResult(myIntent, 0);
 	//Toast.makeText(getBaseContext(),"item" + id + " checked",Toast.LENGTH_SHORT).show();
 	}
 	});
 	
 	
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
    				
    				Intent myIntent1 = new Intent(getBaseContext(), PocketCal20.class);	
    				startActivityForResult(myIntent1, 0);
    			return true;
    			
    			
  	  			   
  	  			   
  	  		   }
    		
    		
    	
    			return false;

}



}
   
