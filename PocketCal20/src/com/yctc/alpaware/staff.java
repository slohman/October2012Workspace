package com.yctc.alpaware;






import com.yctc.alpaware.PocketCal.Staff;




import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;


       
	        
	public class staff extends SherlockActivity {
		
		
		public Cursor c;
		Context mContext;
		public static final int Main_Menu =0;    
		public static final int Menu_1 = 1;
		

		/** Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle Icicle) {
	        super.onCreate(Icicle);
	        setContentView(R.layout.genericlist);
	        
              AlpaDataBaseHelper mdbh = new AlpaDataBaseHelper(this.getApplicationContext());
	      final SQLiteDatabase db = mdbh.getWritableDatabase(); 
	       
	      
	      
	        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
	        qb.setTables(Staff.STAFF_TABLE_NAME);
	        qb.appendWhere(Staff.STAFF_UNIT + " Like" + "'%Staff%'");
	        String Cols[] = {Staff.STAFF_ID + "," + Staff.STAFF_NAME + "," + Staff.STAFF_POS};
	        int names[] = {android.R.id.text1,android.R.id.text2};
	 		Cursor c = qb.query(db,Cols,null,null,null,null,Staff.DEFAULT_SORT_ORDER);		  
	 		  		
	 		 startManagingCursor(c);
	 	

	 		  	  	 
	 	final ListAdapter adapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,c, 
	 			new String[] {Staff.STAFF_NAME, Staff.STAFF_POS}, 
	 			names);
	    
	 	ListView lv = (ListView)findViewById(android.R.id.list);
	 	lv.setAdapter(adapter);
	    	
	   
	  lv.setOnItemClickListener( new AdapterView.OnItemClickListener() {
	 	public void onItemClick(AdapterView<?> parent, View v, int position, long id)
	 	{
	 	// Display a messagebox.
	 		Bundle b = new Bundle();
	 		b.putLong("pID", id);
	 		Intent myIntent = new Intent(getBaseContext(), Person.class);
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
	    
		  	  	
        
		  	  	
		  	


	            
	         
	       
	    
	      
	        
	    
	    
	
	
	
	
	
	 
