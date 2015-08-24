package com.yctc.alpaware;


import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;

public class accidentdisplay extends SherlockActivity {

	
	@Override
    public void onCreate(Bundle Icicle) {
        super.onCreate(Icicle);
       setContentView(R.layout.accidentdisplay);
        
        
        // get id of person to list from calling activity
        Bundle b = this.getIntent().getExtras();  
        int docID =  b.getInt("pID");
        
        switch (docID) {
        	
        
        case  1:
        TextView tv = (TextView)findViewById(R.id.tV1);
        tv.setText(R.string.one);
        break;
        case 2:
        	TextView tv1 = (TextView)findViewById(R.id.tV1);
        tv1.setText(R.string.two);
        break;
        case 3:
        	TextView tv3 = (TextView)findViewById(R.id.tV1);
        tv3.setText(R.string.three);
        break;
        case 4:
        	TextView tv4 = (TextView)findViewById(R.id.tV1);
        tv4.setText(R.string.four);
        break;
        case 5:
        	TextView tv5 = (TextView)findViewById(R.id.tV1);
        tv5.setText(R.string.five);
        break;}
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
