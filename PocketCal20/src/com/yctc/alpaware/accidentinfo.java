package com.yctc.alpaware;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;



public class accidentinfo extends SherlockActivity {
	public static final int Main_Menu =0;    
	public static final int Menu_1 = 1;
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.accident);
	        
	        
	        Button bA1 = (Button) findViewById(R.id.bA1);
			bA1.setOnClickListener(new View.OnClickListener() {
							public void onClick(View view) {
								Bundle b = new Bundle();
						 		b.putInt("pID",1);
						 		Intent myIntent = new Intent(getBaseContext(), accidentdisplay.class);
						 		myIntent.putExtras(b);
						 		startActivityForResult(myIntent, 0);
											}});;
	 
			Button bA2 = (Button) findViewById(R.id.bA2);
			bA2.setOnClickListener(new View.OnClickListener() {
					public void onClick(View view) {
					Bundle b = new Bundle();
					b.putInt("pID",2);
					Intent myIntent = new Intent(getBaseContext(), accidentdisplay.class);
					myIntent.putExtras(b);
					startActivityForResult(myIntent, 0);
}});;	
Button bA3 = (Button) findViewById(R.id.bA3);
bA3.setOnClickListener(new View.OnClickListener() {
		public void onClick(View view) {
		Bundle b = new Bundle();
		b.putInt("pID",3);
		Intent myIntent = new Intent(getBaseContext(), accidentdisplay.class);
		myIntent.putExtras(b);
		startActivityForResult(myIntent, 0);
}});;
Button bA4 = (Button) findViewById(R.id.bA4);
bA4.setOnClickListener(new View.OnClickListener() {
		public void onClick(View view) {
		Bundle b = new Bundle();
		b.putInt("pID",4);
		Intent myIntent = new Intent(getBaseContext(), accidentdisplay.class);
		myIntent.putExtras(b);
		startActivityForResult(myIntent, 0);
}});;
Button bA5 = (Button) findViewById(R.id.bA5);
bA5.setOnClickListener(new View.OnClickListener() {
		public void onClick(View view) {
		Bundle b = new Bundle();
		b.putInt("pID",5);
		Intent myIntent = new Intent(getBaseContext(), accidentdisplay.class);
		myIntent.putExtras(b);
		startActivityForResult(myIntent, 0);
}});;
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
