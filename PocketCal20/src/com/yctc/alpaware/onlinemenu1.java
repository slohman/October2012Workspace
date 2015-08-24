package com.yctc.alpaware;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.AbstractWheelTextAdapter;
import kankan.wheel.widget.adapters.NumericWheelAdapter;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;
import com.yctc.alpaware.OpenTime1.EqWheelAdapter;

import com.yctc.alpaware.OpenTime1.PosWheelAdapter;
import com.yctc.alpaware.OpenTime1.bsWheelAdapter;
import com.yctc.alpaware.OpenTime1.stbyWheelAdapter;
import com.yctc.alpaware.utilities.LongGetCal;

public class onlinemenu1 extends SherlockActivity{

	Context ctx = onlinemenu1.this;
	utilities u = new utilities();
	ProgressDialog pd;
	
	
	 public void onCreate(Bundle Icicle) {
		 super.onCreate(Icicle);
	     setContentView(R.layout.onlinelayout);
	
	     registerReceiver(calrec, new IntentFilter("CalMsg"));
	
	
	// make  listeners here --------
	     
	     
	     
	     Button bEgrid = (Button)findViewById(R.id.begrid);
	     bEgrid.setOnClickListener(new View.OnClickListener() {
		        public void onClick(View view) {
		            Intent egridIntent = new Intent(getBaseContext(), egridmain1.class);	
		        	startActivityForResult(egridIntent, 0);
		        }});
	     
		 Button bWebsite = (Button)findViewById(R.id.bwebsite);
			     bWebsite.setOnClickListener(new View.OnClickListener() {
				        public void onClick(View view) {
				            Intent egridIntent = new Intent(getBaseContext(), pcalbrows1.class);	
				        	startActivityForResult(egridIntent, 0);
				        }});
			     
		Button bGetopentime = (Button)findViewById(R.id.bgetopentime);
					     bGetopentime.setOnClickListener(new View.OnClickListener() {
						        public void onClick(View view) {
						       
									 // try login direct
						       	 String dataOT = u.readuserdata(ctx);
						          	  final String[] logdataOT = dataOT.split(",");
						          
						        
						       	 Bundle bun1 = new Bundle();
						       	     	
						       	     
						       			bun1.putString("id", logdataOT[0].toString());
						       			bun1.putString("pw",  logdataOT[1].toString());
						       			bun1.putString("zl", logdataOT[5].toString().trim());
						       			Intent myIntent23 = new Intent(getBaseContext(), OpenTime1.class);
						       			myIntent23.putExtras(bun1);
						       		    startActivityForResult(myIntent23, 0);
						
						        }});
					     
		Button bStopAuto = (Button)findViewById(R.id.bstopautoopentime);
					bStopAuto.setOnClickListener(new View.OnClickListener() {
						public void onClick(View view) {
							Intent myAlarm1 = new Intent(getApplicationContext(), AlarmReceiver.class);
							PendingIntent recurringAlarm1 = PendingIntent.getBroadcast(onlinemenu1.this, 0, myAlarm1, PendingIntent.FLAG_CANCEL_CURRENT);
							AlarmManager alarms1 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
							alarms1.cancel(recurringAlarm1); 
							Toast.makeText(ctx, "OT Service Cancelled", Toast.LENGTH_LONG).show();
						 }});
					
		Button bViewSavedOpenTime = (Button)findViewById(R.id.bviewsavedopentime);
					bViewSavedOpenTime.setOnClickListener(new View.OnClickListener() {
								public void onClick(View view) {
									Intent myIntent22 = new Intent(getBaseContext(), OpenTimeList.class);
						            startActivityForResult(myIntent22, 0);	
								 }});;
								 
	 
		Button bGetCal = (Button)findViewById(R.id.bgetcalendar);
			bGetCal.setOnClickListener(new View.OnClickListener() {
								   		public void onClick(View view){
								   			
								   			String src="getnew";
								   			loadspinners(src);
								   			
								   		
								   		}});			     
	   							
	 
			Button bviewSavedCal = (Button)findViewById(R.id.bviewsavedcalendar);
			bviewSavedCal.setOnClickListener(new View.OnClickListener() {
		   		public void onClick(View view){
		   			String src="savedcal";
		   		 loadspinners(src);
		   		}});
	 
	 } // -- end 
	 
	 public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu){
			MenuInflater inflater=getSupportMenuInflater();
			inflater.inflate(R.menu.mainmenu, menu);
			
			
			
	            			
			
			return true;
			}	
	    
	    public boolean onOptionsItemSelected (com.actionbarsherlock.view.MenuItem item ){
			
			switch (item.getItemId()){
			
			case R.id.home :
				
				 Intent myIntent = new Intent(ctx, PocketCal20.class);
		        startActivityForResult(myIntent, 0);
			return true;
			case R.id.back :
				
				super.onBackPressed();
			return true;
			
			}
			return false;
	
}
	    
	    
	    private  BroadcastReceiver calrec = new BroadcastReceiver(){
			@Override
			
			public void onReceive(Context c, Intent i) {
				String calData = null;
				
				 calData = i.getExtras().getString("MSG"); // get the OT Data string from the http Client
				 
				/// send the string to processing method
				 
				 if (calData.contains("Complete")){
					 pd.dismiss();
					 String[] data = calData.split(",");
					 String month = data[1].trim();
					 Bundle bun = new Bundle();
			        	bun.putString("AId", "viewCal"); 
				 	    bun.putString("mnth", month);
				 	    
				 	    
				 	 
				 	   Intent myIntent1 = new Intent(onlinemenu1.this,  triplist.class);
				 	   myIntent1.putExtras(bun);
					   myIntent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					   startActivity(myIntent1);	 
					 
				 }else{
				 pd = ProgressDialog.show(ctx, "Calendar Progress", calData, true, false);
			} 
				 
				 
}};


public void loadspinners(String src){
	 
 	
    final String sSrc = src;
    final Dialog dialogSearchByDate = new Dialog(this);
    dialogSearchByDate.setContentView(R.layout.getcalendar_dialog);
    dialogSearchByDate.setTitle("Enter Month & Year to Get");

    
    final ArrayList<String> mnth = new ArrayList<String>();
    
    mnth.add("Jan");
    mnth.add("Feb");
    mnth.add("Mar");
    mnth.add("Apr");
    mnth.add("May");
    mnth.add("Jun");
    mnth.add("Jul");
    mnth.add("Aug");
    mnth.add("Sep");
    mnth.add("Oct");
    mnth.add("Nov");
    mnth.add("Dec");
   
    
    final ArrayList<String> yr = new ArrayList<String>();
    
   
    yr.add("13");
    yr.add("14");
     yr.add("15");
     yr.add("16");
     yr.add("17");
     yr.add("18");
     yr.add("19");
      yr.add("20");
      yr.add("21");
      yr.add("22");
    
   
    
   
    //get current yr
      
        Calendar c = Calendar.getInstance();  
	    SimpleDateFormat yr_date = new SimpleDateFormat("MMM");
	 	String sMonth = yr_date.format(c.getTime());
	 	
	 	SimpleDateFormat month_date = new SimpleDateFormat("yy");
	 	String sYear = month_date.format(c.getTime());
    
    final WheelView WVmonth = (WheelView) dialogSearchByDate.findViewById(R.id.mnth);
    int iM = mnth.indexOf(sMonth);
    WVmonth.setViewAdapter(new mnthWheelAdapter(this,mnth));
    WVmonth.setCurrentItem(iM);
    
    
    final WheelView WVyear = (WheelView) dialogSearchByDate.findViewById(R.id.block_month);
    int iY = yr.indexOf(sYear);
    WVyear.setViewAdapter(new yearWheelAdapter(this,yr));
    WVyear.setCurrentItem(iY);
    
    

    
  
    
   
   
    
    
    
    
    Button settings = (Button)dialogSearchByDate.findViewById(R.id.dialogGetOpenTime);
		settings.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
			
				
			//save parameters to text fields
				
			
			
				
			
              
           	  final String s_Month = mnth.get(WVmonth.getCurrentItem());
           	  final String s_Yr = yr.get(WVyear.getCurrentItem());
           	  
           	  String Month = s_Month+s_Yr;
           	  
           	
	 	    
	 	    
	 	   if (sSrc.matches("savedcal"))
			{
	 		   
	 		  Bundle bun = new Bundle();
	         	bun.putString("AId", "viewCal"); 
		 	    bun.putString("mnth", Month);
           	  
           	  Intent myIntent = new Intent(getApplicationContext(), triplist.class);
              myIntent.putExtras(bun);
              startActivityForResult(myIntent,0);
             
            
			}
	 	  if (sSrc.matches("getnew"))
			{
	 		    Context ct = getApplicationContext();
		     	String data2 = u.readuserdata(ct);
		     	final String[] logdata2 = data2.split(",");
		     	String UID = logdata2[0].toString(); 
	 		   pd = ProgressDialog.show(ctx, "Calendar Progress", "Downloading Calendar Data!", true, false);
		        
				
    			LongGetCal LGC = u.new LongGetCal(onlinemenu1.this);
    			LGC.execute(UID,Month);	
         	   
			}
	 	   
	 	    dialogSearchByDate.dismiss();
	 	   
	 	   
			}});
		
		
		
		  Button cx = (Button)dialogSearchByDate.findViewById(R.id.dialogCancel1);
	 		cx.setOnClickListener(new View.OnClickListener() {
	 			@Override
	 			public void onClick(View v) {
				
				dialogSearchByDate.dismiss();
	 			    
	 			}});
           	 
           	  
  dialogSearchByDate.show();         	
           	 
}

public class yearWheelAdapter extends AbstractWheelTextAdapter {
 	
    ArrayList<String> yr;

    //An object of this class must be initialized with an array of Date type
    protected yearWheelAdapter(Context context, ArrayList<String> yr) {
    //Pass the context and the custom layout for the text to the super method
          super(context, R.layout.wheel_item_time);
          this.yr = yr;
    }

    @Override
 public View getItem(int index, View cachedView, ViewGroup parent) {
          View view = super.getItem(index, cachedView, parent);
          TextView pos = (TextView) view.findViewById(R.id.time_item);

          //Format the date (Name of the day / number of the day)
          
          //Assign the text
          
          pos.setTextSize(26);
          pos.setText(yr.get(index));
         
          
          return view;
    }
   
    @Override
    public int getItemsCount() {
          return yr.size();
    }

    @Override
    protected CharSequence getItemText(int index) {
          return "";
    }

}

public class mnthWheelAdapter extends AbstractWheelTextAdapter {
 	
    ArrayList<String> mnth;

    //An object of this class must be initialized with an array of Date type
    protected mnthWheelAdapter(Context context, ArrayList<String> mnth) {
    //Pass the context and the custom layout for the text to the super method
          super(context, R.layout.wheel_item_time);
          this.mnth = mnth;
    }

    @Override
 public View getItem(int index, View cachedView, ViewGroup parent) {
          View view = super.getItem(index, cachedView, parent);
          TextView pos = (TextView) view.findViewById(R.id.time_item);

          //Format the date (Name of the day / number of the day)
          
          //Assign the text
          pos.setTextSize(26);
          pos.setText(mnth.get(index));
         
          
          return view;
    }
   
    @Override
    public int getItemsCount() {
          return mnth.size();
    }

    @Override
    protected CharSequence getItemText(int index) {
          return "";
    }

}

}
	    

