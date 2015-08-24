package com.yctc.alpaware;



	



	import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

	import android.database.sqlite.SQLiteDatabase;
	

	import android.os.Bundle;

	import android.view.View;
	
	import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
	
	import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;
import android.widget.SimpleCursorAdapter;




	       
		        
	public class repslist extends SherlockActivity {
		
			public Cursor c;
			Context mContext;
			
			

			/** Called when the activity is first created. */
		    @Override
		    public void onCreate(Bundle Icicle) {
		        super.onCreate(Icicle);
		        setContentView(R.layout.staff);
		        
	              AlpaDataBaseHelper mdbh = new AlpaDataBaseHelper(this.getApplicationContext());
		      final SQLiteDatabase db = mdbh.getWritableDatabase(); 
		       
		      
		      
		    
		       
		     int names[] = {android.R.id.text1,android.R.id.text2};
		try { 		
		     
		     Cursor c = db.rawQuery("Select _id, Name, Position || ', ' ||  Unit || ' Block Rep ' || Block AS JOB from officers Where Block > 0 and Block < 50",null);		  
		 	 startManagingCursor(c);
		 	 
		 	final ListAdapter adapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,c, 
		 			new String[] {"Name", "JOB"}, 
		 			names);
	    
	 	ListView lv = (ListView)findViewById(android.R.id.list);
	 	lv.setAdapter(adapter);
	 	
	 	 lv.setOnItemClickListener( new AdapterView.OnItemClickListener() {
			 	public void onItemClick(AdapterView<?> parent, View v, int position, long id)
			 	{
			 	
			 		Bundle b = new Bundle();
			 		b.putLong("pID", id);
			 		Intent myIntent = new Intent(getBaseContext(), MECRepPerson.class);
			 		myIntent.putExtras(b);
			 		
		            startActivityForResult(myIntent, 0);
		            
			 	}}); 
		    
		}catch (Exception e) {
			utilities util = new utilities();
			Context ctx = getApplicationContext();
			util.showaction("Database is not installed/or is damaged", ctx);
			Intent myIntent3 = new Intent(getBaseContext(), filemaintmain.class);	
			startActivityForResult(myIntent3, 0);
			}
		 		  	  	 
		 		
		    	
		   
		 
		 	//Toast.makeText(getBaseContext(),"item" + id + " checked",Toast.LENGTH_SHORT).show();
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
		 	

