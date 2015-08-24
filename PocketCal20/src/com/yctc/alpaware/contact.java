package com.yctc.alpaware;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;


public class contact extends SherlockActivity{
	
	
	protected static final String ContentURI = null;

	@Override
	 public void onCreate(Bundle Icicle) {
	        super.onCreate(Icicle);
	        setContentView(R.layout.officecontactdata);
	        
	        
	        TextView tphonemain = (TextView)findViewById(R.id.tphonemain);
			tphonemain.setOnClickListener(new View.OnClickListener() {
					public void onClick(View view) {
						String toDial = ("9017528749");
		        		
		        		 Intent call = new Intent(android.content.Intent.ACTION_CALL);
		        		 call.setData( Uri.parse("tel:"+ toDial));
		        		 
		        		 
				 		startActivity(call);
		        		finish();}});
			final TextView tphonealt = (TextView)findViewById(R.id.tphonealt);
			tphonealt.setOnClickListener(new View.OnClickListener() {
					public void onClick(View view) {
						String toDial = tphonealt.getText().toString();
		        		
		        		 Intent call = new Intent(android.content.Intent.ACTION_CALL);
		        		 call.setData( Uri.parse("tel:"+ toDial));
		        		 
		        		 
				 		startActivity(call);
		        		finish();}});
			
			final TextView taspen = (TextView)findViewById(R.id.tphoneaspen);
			taspen.setOnClickListener(new View.OnClickListener() {
					public void onClick(View view) {
						String toDial = taspen.getText().toString();
		        		
		        		 Intent call = new Intent(android.content.Intent.ACTION_CALL);
		        		 call.setData( Uri.parse("tel:"+ toDial));
		        		 
		        		 
				 		startActivity(call);
		        		finish();}});
			final TextView taspen1 = (TextView)findViewById(R.id.tphoneaspen1);
			taspen1.setOnClickListener(new View.OnClickListener() {
					public void onClick(View view) {
						String toDial = taspen1.getText().toString();
		        		
		        		 Intent call = new Intent(android.content.Intent.ACTION_CALL);
		        		 call.setData( Uri.parse("tel:"+ toDial));
		        		 
		        		 
				 		startActivity(call);
		        		finish();}});
			final TextView tfax = (TextView)findViewById(R.id.tofficefax);
			tfax.setOnClickListener(new View.OnClickListener() {
					public void onClick(View view) {
						String toDial = tfax.getText().toString();
		        		
		        		 Intent call = new Intent(android.content.Intent.ACTION_CALL);
		        		 call.setData( Uri.parse("tel:"+ toDial));
		        		 
		        		 
				 		startActivity(call);
		        		finish();}});
			final TextView tweb = (TextView)findViewById(R.id.tweb);
			tweb.setOnClickListener(new View.OnClickListener() {
					public void onClick(View view) {
						String toDial = tweb.getText().toString();
		        		
						{
							Uri uri = Uri.parse( toDial );
							startActivity( new Intent( Intent.ACTION_VIEW, uri ) );
						}

		        		 
		        		 
				 		
		        		finish();}});

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
