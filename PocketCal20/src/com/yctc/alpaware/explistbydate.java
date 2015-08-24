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
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;


	
public class explistbydate extends SherlockActivity{

	
			public Cursor c;
			Context mContext;
			
			public float amount,totalamount;
			public String grandtotal,subtotal;
			

			/** Called when the activity is first created. */
		    @Override
		    public void onCreate(Bundle Icicle) {
		        super.onCreate(Icicle);
		        setContentView(R.layout.explistbydate);
		        
		        
		        Bundle b = this.getIntent().getExtras();  
		        String srchDate = b.getString("dID");
		        
		        
	             UserDataHelper mdbh = new UserDataHelper(this.getApplicationContext());
		      final SQLiteDatabase db = mdbh.getWritableDatabase(); 
		       
		      
		      
		     int names[] = {android.R.id.text1,android.R.id.text2};
		 		final Cursor c = db.rawQuery("Select _id, exp_AMT, exp_DATE ||' '|| exp_CAT ||' ' || exp_COMM AS EXP from exp where exp_Date >= '" + srchDate  +  "' order by exp_Date ASC" ,null);		  
		 		  		
		 		c.moveToFirst();
		 		if (c.getCount() == -1) {
		 			// null recordset  exit to main page with notification
		 			Toast.makeText(this, "No Expenses are saved in the database", Toast.LENGTH_LONG).show();
		 			Intent myIntent = new Intent(getBaseContext(),PocketCal20.class);
		 			startActivityForResult(myIntent, 0);
		 			}

		 		  	  	 
		 		final ListAdapter adapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,c, 
			 			new String[] {"EXP","exp_AMT"}, 
			 			names);		  
		 		  		
		 		 
		 	
		 		 totalamount=0;
		 		 
		 		 
		 		 
		 		 for (int i = 0; i <= c.getCount() - 1;i++) {
		 			 subtotal = c.getString(c.getColumnIndex("exp_AMT"));
		 	 		amount =  Float.parseFloat(subtotal);
		 	
 		 	totalamount = totalamount + amount;
 		 	
 		 	c.moveToNext();
		 		 }
 		 	grandtotal = Float.toString(totalamount);   
 		 	TextView ttl = (TextView)findViewById(R.id.footer);
		    ttl.setText(grandtotal);
		    
		    
		 	ListView lv = (ListView)findViewById(android.R.id.list);
		 	lv.setAdapter(adapter);
		    	
		   
		  lv.setOnItemClickListener( new AdapterView.OnItemClickListener() {
		 	public void onItemClick(AdapterView<?> parent, View v, int position, long id)
		 	{
		 	
		 		Bundle b = new Bundle();
		 		c.moveToPosition(position);
		 		b.putLong("pID", c.getLong(c.getColumnIndex("_id")));
		 		Intent myIntent = new Intent(getBaseContext(),expupdate.class);
		 		myIntent.putExtras(b);
		 		
	            startActivityForResult(myIntent, 0);
		 	
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
		   




