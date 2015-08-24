package com.yctc.alpaware;


import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;


import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.app.SherlockActivity;


public class aclistout extends SherlockActivity{

	
	public Cursor c;
	Context mContext;
	String ACEQUIPMENT="";
	String tmpACEQUIP="";
	public int ACnumber;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle Icicle) {
        super.onCreate(Icicle);
        setContentView(R.layout.acequipment);
        
        
        Bundle b = this.getIntent().getExtras();  
       final long ACNUM = b.getLong("dID");
        
        
       AlpaDataBaseHelper mdbh = new AlpaDataBaseHelper(this.getApplicationContext());
      final SQLiteDatabase db = mdbh.getWritableDatabase(); 
	        
      final Cursor c = db.rawQuery("Select * from ACDATA where ac_NUM =" + ACNUM,null );
      startManagingCursor(c);
      
      if (c.getCount() <1){
    	 utilities util = new utilities();
    	 Context ctx = getApplicationContext();
    	 util.showaction("A/C not on file !", ctx);
    	
    	 
    	 
  	    	 		Intent myIntent = new Intent(getBaseContext(), PocketCal20.class);
  	    	 		 startActivityForResult(myIntent, 0);
  	 
      }else{ 

      TextView acequip = (TextView) findViewById(R.id.tvacequipment);
     final Button bAC = (Button) findViewById(R.id.bacTYP);
     final Button bACNUM = (Button) findViewById(R.id.bacNUM);
      
      
      c.moveToFirst();
     
      
      
     bACNUM.setText(c.getString(c.getColumnIndex("ac_NUM")));
      bAC.setText(c.getString(c.getColumnIndex("ac_TYP")));
       
      for (int i = 1; i <= c.getColumnCount() - 1;i++) {
    	  int colstatus = c.getInt(i);
    	  	if (colstatus == 1 ){
    	  		int fieldlen = c.getColumnName(i).length();
    	  		tmpACEQUIP = c.getColumnName(i).substring(3,fieldlen);
    	  		
    	  		ACEQUIPMENT +=  tmpACEQUIP; 
    	  		ACEQUIPMENT += "\r\n";
    	  	}
     
			
		}
     ACEQUIPMENT += (c.getString(c.getColumnIndex("ac_COMM")));
      acequip.setText(ACEQUIPMENT);
      
      
     
    
        
}
    }   
    
    public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu){
	    	
	    
	                			
	    		MenuInflater inflater=getSupportMenuInflater();
	    		inflater.inflate(R.menu.acmenu, menu);
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
	    			
	    			case R.id.acmenu:
	    				
	    				Intent myIntent4 = new Intent(getBaseContext(), PocketCal20.class);	
	    				startActivityForResult(myIntent4, 0);
	    				
	    			return true;
	    			
	    			case R.id.addac :
	    				
	    				AlpaDataBaseHelper mdbh = new AlpaDataBaseHelper(this.getApplicationContext());
	    			    final SQLiteDatabase db = mdbh.getWritableDatabase();
	    				
	    				
	    			    final FrameLayout fl = new FrameLayout(this);
	    			      final EditText input = new EditText(this);

	    			   
	    			  		
	    				      input.setGravity(Gravity.CENTER);
	    				      fl.addView(input, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
	    				      input.setHint("Input A/C #");
	    				      input.setInputType(3);
	    				           Builder b =  new AlertDialog.Builder(aclistout.this);
	    				           b.setView(fl);
	    				           b.setTitle("Enter New A/C #");
	    				           
	    				      
	    				     b.setPositiveButton("OK", new DialogInterface.OnClickListener(){
	    				    	 	public void onClick(DialogInterface d, int which) {
	    				    	 		d.dismiss();
	    				    	 		// get date and execute SQL here....
	    				    	 		int acNum = Integer.parseInt(input.getText().toString());
	    				    	 		 db.execSQL("Insert into ACDATA Values (" + null  + ", " +  acNum + "," + null  + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + ","  + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + null + " ) ");
	    				    	 		  utilities util = new utilities();
	    				  	 		      Context ctx = getApplicationContext(); 
	    				    	 		util.showaction("Default A/C" + acNum + " created",ctx);
	    				    	 		db.close();
	    				    	 		  }});
	    				     					
	    					b.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
	    							public void onClick(DialogInterface d, int which) {
	    							d.dismiss();
	    							Intent myIntent5 = new Intent(getBaseContext(), PocketCal20.class);
	    			    	 		
	    			  	 		
	    			              startActivityForResult(myIntent5, 0);
	    							 }
	    						  }
	    					                  );
	    				 	     b.create().show();

	    				
	    			return true;
	    				
	    			
	    			
	    			case R.id.viewOther:
	    				
	    				Intent myIntent6 = new Intent(getBaseContext(), acequiplist.class);	
	    				startActivityForResult(myIntent6, 0);
	    				
	    			return true;
	    			
	    			case R.id.delac:
	    				
	    				AlpaDataBaseHelper mdbh1 = new AlpaDataBaseHelper(this.getApplicationContext());
	   			     SQLiteDatabase db1 = mdbh1.getWritableDatabase();
	   			     Button bACNUM = (Button) findViewById(R.id.bacNUM);
	   				int acNum = Integer.parseInt(bACNUM.getText().toString());
	   				try{
	   				db1.execSQL("Delete from  ACDATA where ac_NUM = " + acNum );
	   				  utilities util = new utilities();
	     	 		      Context ctx = getApplicationContext();
	   				util.showaction("A/C " + acNum + " Deleted",ctx);	
	   					db1.close();
	   					Intent myIntent7 = new Intent(getBaseContext(),PocketCal20.class);
	   		      		startActivityForResult(myIntent7, 0);
	   				
	   				}catch (Exception e) {
	   					 utilities util = new utilities();
	   	  	 		      Context ctx = getApplicationContext();
	   					util.showaction("A/C " + acNum + "Not on File/Not Deleted",ctx);	
	   					
	   				}
	    				
	    			return true;
	    			
	    			case R.id.updac :
	    				Button bACNUM1 = (Button) findViewById(R.id.bacNUM);
	    				int acNumber = Integer.parseInt(bACNUM1.getText().toString());
	    				Bundle bun = new Bundle();
	    	    		bun.putLong("dID", acNumber);
	    	      		Intent myIntent8 = new Intent(getBaseContext(), acequiplistupdate.class);
	    	      		myIntent8.putExtras(bun);
	    	 	 		 startActivityForResult(myIntent8, 0);
	    	      	
	    			return true;
	    		
	    	}
	    			return false;
	    		}
			
		
}

