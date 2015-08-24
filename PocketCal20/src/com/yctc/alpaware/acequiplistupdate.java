package com.yctc.alpaware;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

public class acequiplistupdate extends Activity{

	public long ACnumber;
	public String  ACNUM;
	public String hdr;
	 public static final int Main_Menu =0; 
	protected static final Context Context = null;



	public void onCreate(Bundle Icicle) {
        super.onCreate(Icicle);
        setContentView(R.layout.acequiplistupdate);
        Bundle bun = this.getIntent().getExtras();  
         ACnumber = bun.getLong("dID");
        ACNUM = String.valueOf(ACnumber);
        
        
        
        Button bACNUM = (Button) findViewById(R.id.lTailNum);
        bACNUM.setText(ACNUM);
        
        final Button bType = (Button) findViewById(R.id.lTYP);
        bType.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String msg = "ac_TYP";
                 hdr = "Update A/C Equip";
                
                UpdAcData(msg,hdr);
            }});
        
       
        
        final Button bEFB = (Button) findViewById(R.id.lEFB);
        bEFB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String msg = "ac_EFB";
                 hdr= "Enter 1(true), 0(false)";
                UpdAcData(msg,hdr);
            }});
        
        final Button bRAAS = (Button) findViewById(R.id.lRAAS);
        bRAAS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String msg = "ac_RAAS";
                 hdr= "Enter 1(true), 0(false)";
                UpdAcData(msg,hdr);
            }});
        
        final Button bCREST = (Button) findViewById(R.id.lCrest);
        bCREST.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String msg = "ac_CREST";
                 hdr= "Enter 1(true), 0(false)";
                UpdAcData(msg,hdr);
            }});
        
        final Button bFANS = (Button) findViewById(R.id.lFANS);
        bFANS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String msg = "ac_FANS";
                 hdr= "Enter 1(true), 0(false)";
                UpdAcData(msg,hdr);
            }});
        
        final Button bSOLIDBULK = (Button) findViewById(R.id.lBULK);
        bSOLIDBULK.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String msg = "ac_SOLIDBULK";
                 hdr= "Enter 1(true), 0(false)";
                UpdAcData(msg,hdr);
            }});
        
        final Button bHUD= (Button) findViewById(R.id.lHUD);
        bHUD.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String msg = "ac_HUD";
                 hdr= "Enter 1(true), 0(false)";
                UpdAcData(msg,hdr);
            }});
        final Button bFSS= (Button) findViewById(R.id.lFSS);
        bFSS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String msg = "ac_FSS";
                 hdr= "Enter 1(true), 0(false)";
                UpdAcData(msg,hdr);
            }});
        
        final Button bACHART= (Button) findViewById(R.id.lACHART);
        bACHART.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String msg = "ac_ANIMAL";
                 hdr= "Enter 1(true), 0(false)";
                UpdAcData(msg,hdr);
            }});
        
        final Button bSAT= (Button) findViewById(R.id.lSAT);
        bSAT.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String msg = "ac_SAT";
                 hdr= "Enter 1(true), 0(false)";
                UpdAcData(msg,hdr);
            }});
        
        final Button bHFDLINK= (Button) findViewById(R.id.lHFDLINK);
        bHFDLINK.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String msg = "ac_HFDLK";
                 hdr= "Enter 1(true), 0(false)";
                UpdAcData(msg,hdr);
            }});
        
        final Button bC3= (Button) findViewById(R.id.lCATIII);
        bC3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String msg = "ac_Cat3";
                 hdr= "Enter 1(true), 0(false)";
                UpdAcData(msg,hdr);
            }});
        
        final Button bEGPWS= (Button) findViewById(R.id.lEGPWS);
        bEGPWS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String msg = "ac_EGPWS";
                 hdr= "Enter 1(true), 0(false)";
                UpdAcData(msg,hdr);
            }});
        
        final Button bRVSM= (Button) findViewById(R.id.lRVSM);
        bRVSM.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String msg = "ac_RVSM";
                 hdr= "Enter 1(true), 0(false)";
                UpdAcData(msg,hdr);
            }});
        final Button bGPS = (Button) findViewById(R.id.lGPS);
        bGPS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String msg = "ac_GPS";
                 hdr= "Enter 1(true), 0(false)";
                UpdAcData(msg,hdr);
            }});
        final Button bHF = (Button) findViewById(R.id.lHF);
        bHF.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String msg = "ac_HF";
                 hdr= "Enter 1(true), 0(false)";
                UpdAcData(msg,hdr);
            }});
        final Button bVNAV = (Button) findViewById(R.id.lVNAV);
        bVNAV.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String msg = "ac_VNAV";
                 hdr= "Enter 1(true), 0(false)";
                UpdAcData(msg,hdr);
            }});
        
        final Button bINTL = (Button) findViewById(R.id.lINTLOK);
        bINTL.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String msg = "ac_INTLOK";
                 hdr= "Enter 1(true), 0(false)";
                UpdAcData(msg,hdr);
            }});
        
        final Button bASIA = (Button) findViewById(R.id.lASIANOPS);
        bASIA.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String msg = "ac_ASIANOPS";
                 hdr= "Enter 1(true), 0(false)";
                UpdAcData(msg,hdr);
            }});
        final Button bRNAV = (Button) findViewById(R.id.lRNAV);
        bRNAV.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String msg = "ac_RNAV";
                 hdr= "Enter 1(true), 0(false)";
                UpdAcData(msg,hdr);
            }});
        final Button bEUROPS = (Button) findViewById(R.id.lEUROPS);
        bEUROPS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String msg = "ac_EUROPS";
                 hdr= "Enter 1(true), 0(false)";
                UpdAcData(msg,hdr);
            }});
        final Button bSBYGEN = (Button) findViewById(R.id.lSBYGEN);
        bSBYGEN.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String msg = "ac_SBYGEN";
                 hdr= "Enter 1(true), 0(false)";
                UpdAcData(msg,hdr);
            }});
        final Button bCOMM = (Button) findViewById(R.id.txtAComm);
        bCOMM.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String msg = "ac_COMM";
                 hdr= "Enter Comments";
                UpdAcData(msg,hdr);
            }});
            
            final Button bNext = (Button) findViewById(R.id.bmainmenu);
            bNext.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                	Intent myIntent = new Intent(getBaseContext(), PocketCal20.class);	
	 				startActivityForResult(myIntent, 0);
                }});
            
	}
           public boolean onCreateOptionsMenu(Menu menu){
			menu.add(0,Main_Menu,0,"Main Menu");
			
			return true;
			}

		public boolean onOptionsItemSelected ( MenuItem item ){
			
			switch (item.getItemId()){
			case Main_Menu :
				
				Intent myIntent = new Intent(getBaseContext(), PocketCal20.class);	
				startActivityForResult(myIntent, 0);
				
			return true;
			
}
			return false;
}
	 public void  UpdAcData(final String msg, String Title){
		 final FrameLayout fl = new FrameLayout(this);
 	      final EditText input = new EditText(this);
 	      	  input.setGravity(Gravity.CENTER);
	  	      fl.addView(input, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
	  	      input.setHint("Input " + msg);
	  	      input.setInputType(1);
	  	  
	  	           Builder b =  new AlertDialog.Builder(acequiplistupdate.this);
	  	           b.setView(fl);
	  	           b.setTitle(Title);
	  	           AlpaDataBaseHelper mdbh = new AlpaDataBaseHelper(this.getApplicationContext());
	  	    	   final SQLiteDatabase db = mdbh.getWritableDatabase();
	  	      
	  	     b.setPositiveButton("OK", new DialogInterface.OnClickListener(){
	  	    	
	  	    	 	public void onClick(DialogInterface d, int which) {
	  	    	 		d.dismiss();
	  	    	 		
	  	    	 		Button bACNUM = (Button) findViewById(R.id.lTailNum);
	  	    	 		String ACNUM = bACNUM.getText().toString();
	  	    	 		String acEquipData = input.getText().toString();
	  	    	 		ContentValues updateValues = new ContentValues();
	  	    	 		updateValues.put(msg, acEquipData);
	  	    	 		db.update("ACDATA", updateValues,"ac_NUM" + "=" + ACNUM , null);
	  	    	 		ShowMsg("A/C Equipment List Updated");
	  		  }

					});
	  	     					
	 		b.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
	 				public void onClick(DialogInterface d, int which) {
	 				d.dismiss();
	 				Intent myIntent = new Intent(getBaseContext(), PocketCal20.class);	
	 				
	 				startActivityForResult(myIntent, 0);}
	 					  });
	  	 
	  	     b.create().show();

		 		
	            
	    	}


	 
public void ShowMsg(String msg){
	Context context = getApplicationContext();
	int duration = Toast.LENGTH_SHORT;

	Toast toast = Toast.makeText(context, msg, duration);
	toast.show();
	}
}

