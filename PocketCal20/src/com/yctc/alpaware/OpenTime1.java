package com.yctc.alpaware;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;

import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.AbstractWheelTextAdapter;
import kankan.wheel.widget.adapters.NumericWheelAdapter;



import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;




import android.annotation.SuppressLint;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;


import android.app.ProgressDialog;

import android.content.Context;



import android.content.Intent;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;



import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;


import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import android.widget.Toast;



import android.widget.Spinner;


@SuppressLint("SimpleDateFormat")
public class OpenTime1 extends SherlockActivity {

	
		
		
		
	
	 CookieStore cookieStore = new BasicCookieStore();
	 HttpContext localContext = new BasicHttpContext();
	 utilities util = new utilities();
	 HttpEntity tripDetailEntity;
	 String tripDetails,grid,uid,pwd,base,equip,Month,stDate,enDate,seat,sessId,redirect,ImagePath,zl,UPDMIN;
	 Context ctx= this;
	 ProgressDialog pd;
	String[] botlinks;
	String nCTL = null;  // bot code holder
	DefaultHttpClient httpClient = AppSettings.getClient();
	utilities u = new utilities();
	 int notifID = 1;
	 Cookie sessionInfo;
	 boolean newTrips = false;
	 

	 @Override
	    public void onCreate(Bundle Icicle) {
		 super.onCreate(Icicle);
	     setContentView(R.layout.opentimelayout);
	     
	       //get bundle and read grid code
	       Bundle b = this.getIntent().getExtras();  
	        
	         uid = b.getString("id");
  	        pwd = b.getString("pw");
  	        zl = b.getString("zl");
  	        
  	     //  loadTextViews();
  	     loadspinners();
 	
  	     
  	        
  	       
  	        
	 }	     
	 @Override
	 public void onStop() {
		 super.onStop();
		 
	
	}
	 @Override
	 public void onStart() {
		 super.onStart();
		
	}
	 public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu){
			
			
			
			
			
			MenuInflater inflater=getSupportMenuInflater();
			inflater.inflate(R.menu.otimemenubar, menu);
			return super.onCreateOptionsMenu(menu);
			}

			public boolean onOptionsItemSelected (com.actionbarsherlock.view.MenuItem item ){
				switch (item.getItemId()){
				case R.id.clearOpenTime :
					
					clearOpenTimeData();
					Toast.makeText(ctx,"Open Time Data Cleared", Toast.LENGTH_LONG).show();
					
				return true;	
				case R.id.home :
					
					Intent myIntent1 = new Intent(getBaseContext(), PocketCal20.class);	
					startActivityForResult(myIntent1, 0);
				return true;
				case R.id.back :
					Intent myIntent2 = new Intent(getBaseContext(), onlinemenu1.class);	
					startActivityForResult(myIntent2, 0);
				return true;	
				
				
				case R.id.egridmain :
					Intent myIntent4 = new Intent(getBaseContext(), egridmain1.class);	
					startActivityForResult(myIntent4, 0);
					
		}
				return false;
			}	
	 
	 public void loadspinners(){
		 
		 
		 
	//	 Spinner s1 = (Spinner) findViewById(R.id.image);
	//	    ArrayAdapter<?> adapter1 = ArrayAdapter.createFromResource(
	//	            this, R.array.imageYN, android.R.layout.simple_spinner_item);
	//	    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	//	    s1.setAdapter(adapter1);
		 
		    
		 	String[] userinfo = readuserdata().split(",");
		 	String BASE = userinfo[2];
		 	String EQ = userinfo[3];
		 	String POS = userinfo[4];
		 	
		    
		    final Dialog dialogSearchByDate = new Dialog(this);
		    dialogSearchByDate.setContentView(R.layout.opentime_dialog);
		    dialogSearchByDate.setTitle("Set Open Time Parameters");
	
		    
		    final ArrayList<String> seat = new ArrayList<String>();
		    
		    seat.add("Cap");
		    seat.add("FO");
		    
		    final ArrayList<String> equipment = new ArrayList<String>();
		    
		    equipment.add("11");
		    equipment.add("77");
		    equipment.add("30");
		    equipment.add("67");
		    equipment.add("57");
		    
		    final ArrayList<String> base = new ArrayList<String>();
		   
		    base.add("MEM");
		    base.add("LAX");
		    base.add("ANC");
		    base.add("HKG");
		    base.add("EUR");
		    
		    final ArrayList<String> stby = new ArrayList<String>();
		    stby.add("No");
		    stby.add("Yes");
		   
		    
		   
		    
		    
		    final WheelView pos = (WheelView) dialogSearchByDate.findViewById(R.id.seat);
		    int iseat = seat.indexOf(POS);
		    pos.setViewAdapter(new PosWheelAdapter(this,seat));
		    pos.setCurrentItem(iseat);
		    
		    
		    final WheelView eq = (WheelView) dialogSearchByDate.findViewById(R.id.wheelequip);
		    int ieq = equipment.indexOf(EQ);
		    eq.setViewAdapter(new EqWheelAdapter(this,equipment));
		    eq.setCurrentItem(ieq);
		    
		    final WheelView bs = (WheelView) dialogSearchByDate.findViewById(R.id.base);
		    int ibase = base.indexOf(BASE);
		    bs.setViewAdapter(new bsWheelAdapter(this,base));
		    bs.setCurrentItem(ibase);
		    
		    final WheelView sb = (WheelView)dialogSearchByDate.findViewById(R.id.standby);
		    sb.setViewAdapter(new stbyWheelAdapter(this,stby));
		
		    
		  
		    
		   
		    final WheelView days = (WheelView) dialogSearchByDate.findViewById(R.id.days);
		    final NumericWheelAdapter hourAdapter = new NumericWheelAdapter(this, 1, 45);
		    hourAdapter.setItemResource(R.layout.wheel_item_time);
		    hourAdapter.setItemTextResource(R.id.time_item);
		    
		    days.setViewAdapter(hourAdapter);
		    days.setCurrentItem(29);
		    
		    final WheelView min = (WheelView) dialogSearchByDate.findViewById(R.id.mnth);
		    final NumericWheelAdapter minAdapter = new NumericWheelAdapter(this, 00, 20);
		    minAdapter.setItemResource(R.layout.wheel_item_time);
		    minAdapter.setItemTextResource(R.id.time_item);
		    min.setViewAdapter(minAdapter);
		    min.setCurrentItem(0);
		    min.setVisibleItems(5);
		    
		    
		    
		    
		    Button settings = (Button)dialogSearchByDate.findViewById(R.id.dialogGetOpenTime);
	 		settings.setOnClickListener(new View.OnClickListener() {
	 			@Override
	 			public void onClick(View v) {
					
	 				
	 			//save parameters to text fields
	 				
	 			
	 			
	 				
	 				 String RunAsSvc;
		              String isby;
		              int daysForward = Integer.parseInt(hourAdapter.getItemText(days.getCurrentItem()).toString());
		           	  String[] srchDates =  getSpinnerDates(daysForward);
		              final String startDate = srchDates[0];
		   	          final String endDate = srchDates[1];
		           	  String[] Data = null;
		           	  Data = new String[10];
		           	  Data[0] = uid;
		           	  Data[1] = pwd;
		           	  Data[2] = zl;
		           	//  TextView TVbase = (TextView)findViewById(R.id.tvOTBase);
		           //   TextView TVequip = (TextView)findViewById(R.id.tvOTEquipment);
		           //   TextView TVseat = (TextView)findViewById(R.id.tvOTPosition);
		           //   TextView SvcMin = (TextView)findViewById(R.id.tvRunAsSvc);
		              
		              String UpdateInterval = minAdapter.getItemText(min.getCurrentItem()).toString();
		              
		             int updMin = Integer.parseInt(UpdateInterval);
		              if (updMin > 0){
		            	  RunAsSvc = "true";
		              }  else {
		            	  RunAsSvc = "false";
		              }
		             
		              String sby = stby.get(sb.getCurrentItem());
		              
		              if (sby.equals("Yes")){
		            	  isby = "true";
		              } else {
		            	  isby = "false";
		              }
		              
		           	  final String BASE = base.get(bs.getCurrentItem());
		           	  final String EQ = equipment.get(eq.getCurrentItem());
		           	  final String SEAT = seat.get(pos.getCurrentItem());
		           	  
		           	  long milsec = (updMin)*60000;
		           	
		           	  
		           	 
		           	  
		           	
		           	 
				
					 
					 if (RunAsSvc.equals("false"))
					 {
						
					new LongGetOpen().execute(startDate,endDate,BASE,EQ,SEAT,isby);
					 }else{
						 Calendar calendar = Calendar.getInstance();
				            calendar.setTimeInMillis(System.currentTimeMillis());
				            calendar.add(Calendar.SECOND, 10);
					
						 
						 
							Intent myAlarm1 = new Intent(OpenTime1.this, AlarmReceiver.class);
							myAlarm1.putExtra("OP", "OpenTime");
							myAlarm1.putExtra("STARTDATE", startDate);
							myAlarm1.putExtra("ENDDATE", endDate);
							myAlarm1.putExtra("BASE", BASE);
							myAlarm1.putExtra("EQUIP", EQ);
							myAlarm1.putExtra("SEAT", SEAT);
							myAlarm1.putExtra("SBY", isby);
							
							PendingIntent recurringAlarm1 = PendingIntent.getBroadcast(OpenTime1.this, 0, myAlarm1, PendingIntent.FLAG_CANCEL_CURRENT);
							AlarmManager alarms1 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
							
							alarms1.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), milsec, recurringAlarm1);
							
							Intent i = new Intent(OpenTime1.this, onlinemenu1.class);
							i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							Toast.makeText(OpenTime1.this, "Open Schedules Every " + updMin , Toast.LENGTH_LONG).show();
							startActivity(i);
					 }	     
					
	 				
	 				dialogSearchByDate.dismiss();
	 			  
	 			    
	 			    
	 			}});
	 		
	 		  Button cx = (Button)dialogSearchByDate.findViewById(R.id.dialogCancel1);
		 		cx.setOnClickListener(new View.OnClickListener() {
		 			@Override
		 			public void onClick(View v) {
						
		 			dialogSearchByDate.dismiss();
		 			// return to onlinemenu here  
		 			Intent myIntent2 = new Intent(getBaseContext(), onlinemenu1.class);	
					startActivityForResult(myIntent2, 0);  
		 			    
		 			}});
		    
		    
		 dialogSearchByDate.show();
		    
		   
		    
		 
		 
	 }
	 
	 public class PosWheelAdapter extends AbstractWheelTextAdapter {
		 	
	       ArrayList<String> seats;

	       //An object of this class must be initialized with an array of Date type
	       protected PosWheelAdapter(Context context, ArrayList<String> seats) {
	       //Pass the context and the custom layout for the text to the super method
	             super(context, R.layout.wheel_item_time);
	             this.seats = seats;
	       }

	       @Override
	    public View getItem(int index, View cachedView, ViewGroup parent) {
	             View view = super.getItem(index, cachedView, parent);
	             TextView pos = (TextView) view.findViewById(R.id.time_item);

	             //Format the date (Name of the day / number of the day)
	             
	             //Assign the text
	             pos.setText(seats.get(index));
	            
	             
	             return view;
	       }
	      
	       @Override
	       public int getItemsCount() {
	             return seats.size();
	       }

	       @Override
	       protected CharSequence getItemText(int index) {
	             return "";
	       }

	}

	 public class EqWheelAdapter extends AbstractWheelTextAdapter {
		 	
	       ArrayList<String> equipment;

	       //An object of this class must be initialized with an array of Date type
	       protected EqWheelAdapter(Context context, ArrayList<String> equipment) {
	       //Pass the context and the custom layout for the text to the super method
	             super(context, R.layout.wheel_item_time);
	             this.equipment = equipment;
	       }

	       @Override
	    public View getItem(int index, View cachedView, ViewGroup parent) {
	             View view = super.getItem(index, cachedView, parent);
	             TextView equip = (TextView) view.findViewById(R.id.time_item);

	             //Format the date (Name of the day / number of the day)
	             
	             //Assign the text
	             equip.setText(equipment.get(index));
	            
	            
	            
	            
	             return view;
	       }
	      
	       @Override
	       public int getItemsCount() {
	             return equipment.size();
	       }

	       @Override
	       protected CharSequence getItemText(int index) {
	             return "";
	       }

	} 
	 
	 public class bsWheelAdapter extends AbstractWheelTextAdapter {
		 	
	       ArrayList<String> bases;

	       //An object of this class must be initialized with an array of Date type
	       protected bsWheelAdapter(Context context, ArrayList<String> bases) {
	       //Pass the context and the custom layout for the text to the super method
	             super(context, R.layout.wheel_item_time);
	             this.bases = bases;
	       }

	       @Override
	    public View getItem(int index, View cachedView, ViewGroup parent) {
	             View view = super.getItem(index, cachedView, parent);
	             TextView bas = (TextView) view.findViewById(R.id.time_item);

	             //Format the date (Name of the day / number of the day)
	             
	             //Assign the text
	             bas.setText(bases.get(index));
	             
	            
	            
	            
	             return view;
	       }
	      
	       @Override
	       public int getItemsCount() {
	             return bases.size();
	       }

	       @Override
	       protected CharSequence getItemText(int index) {
	             return "";
	       }

	} 
	 
	 public class stbyWheelAdapter extends AbstractWheelTextAdapter {
		 	
	       ArrayList<String> sby;

	       //An object of this class must be initialized with an array of Date type
	       protected stbyWheelAdapter(Context context, ArrayList<String> sby) {
	       //Pass the context and the custom layout for the text to the super method
	             super(context, R.layout.wheel_item_time);
	             this.sby = sby;
	       }

	       @Override
	    public View getItem(int index, View cachedView, ViewGroup parent) {
	             View view = super.getItem(index, cachedView, parent);
	             TextView bas = (TextView) view.findViewById(R.id.time_item);

	             //Format the date (Name of the day / number of the day)
	             
	             //Assign the text
	             bas.setText(sby.get(index));
	             
	            
	            
	            
	             return view;
	       }
	      
	       @Override
	       public int getItemsCount() {
	             return sby.size();
	       }

	       @Override
	       protected CharSequence getItemText(int index) {
	             return "";
	       }

	} 
	 
	 
	 @SuppressLint("SimpleDateFormat")
	public String[] getSpinnerDates(int daysForward){
		
		 String[] spinDates = null;
		  
		
	
		    SimpleDateFormat sdf = new SimpleDateFormat("ddMMMyy");
		    Calendar c = Calendar.getInstance();
		    String startdate = sdf.format(c.getTime());
		    
		    
		    c.setTime(c.getTime());
			c.add(Calendar.DATE, daysForward);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
			SimpleDateFormat sdf1 = new SimpleDateFormat("ddMMMyy");
			String output = sdf1.format(c.getTime()); 
   
   
   spinDates  = new String[2];
   spinDates[0] = startdate;
   spinDates[1] = output;
   return spinDates;
		   
		    

		
		 
		
	 }
	 public String IntMonthToString(int month){
		 
		 switch (month) {
		 case 1 :
			 Month = "Jan";
			 return Month;
	
		 case 2 :
			 Month = "Feb";
			 return Month;
		 case 3:
			 Month = "Mar";
			 return Month;
		 case 4 :
			 Month = "Apr";
			 return Month;
	
		 case 5 :
			 Month = "May";
			 return Month;
		 case 6:
			 Month = "Jun";
			 return Month;	 
		 case 7 :
			 Month = "Jul";
			 return Month;
	
		 case 8 :
			 Month = "Aug";
			 return Month;
		 case 9:
			 Month = "Sep";
			 return Month;		 
		 case 10 :
			 Month = "Oct";
			 return Month;
	
		 case 11 :
			 Month = "Nov";
			 return Month;
		 case 12:
			 Month = "Dec";
			 return Month;
		 
		  }
		return null;
		
	 }

	 
	public void loadTextViews() {
		
		 String data = readuserdata();
 	      final String[] logdata2 = data.split(",");
 	        String base = logdata2[2];
 	        String eq = logdata2[3];
 	        String pos = logdata2[4];
 	        
 	        TextView tBase = (TextView)findViewById(R.id.tvOTBase);
 	        TextView tEquipment = (TextView)findViewById(R.id.tvOTEquipment);
 	        TextView tSeat = (TextView)findViewById(R.id.tvOTPosition);
 	        
 	        tBase.setText(base.toString());
 	        tEquipment.setText(eq);
 	        tSeat.setText(pos);
		
		
		
		
		
	}



public String readuserdata(){
	String usrdata = null;
	 FileInputStream fis = null;
        try {
			fis = openFileInput("PcalData");
        	} catch (FileNotFoundException e) {
			
		   
			return "File Missing";
		}
			 StringBuffer fileContent = new StringBuffer("");

		        byte[] buffer = new byte[1024];
		        
		        
		        	@SuppressWarnings("unused")
					int length = 0;
					try {
						while ((length = fis.read(buffer)) != -1) {
						    fileContent.append(new String(buffer));
						}
					} catch (IOException e) {
						return "File Missing";
					}
					
					usrdata = (fileContent.toString());
				    
		      
        return usrdata;
		
	
}



@SuppressWarnings({ "unused", "deprecation" })
private static void generateNotification(Context context, String message) {
    int icon = R.drawable.alpalogo;
    long when = System.currentTimeMillis();
    NotificationManager notificationManager = (NotificationManager)
            context.getSystemService(Context.NOTIFICATION_SERVICE);
    Notification notification = new Notification(icon, message, when);

    String title = context.getString(R.string.app_name);

    Intent notificationIntent = new Intent(context, PocketCal20.class);
    // set intent so it does not start a new activity
    notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
            Intent.FLAG_ACTIVITY_SINGLE_TOP);
    PendingIntent intent =
            PendingIntent.getActivity(context, 0, notificationIntent, 0);
    notification.setLatestEventInfo(context, title, message, intent);
    notification.flags |= Notification.FLAG_AUTO_CANCEL;

    // Play default notification sound
    notification.defaults |= Notification.DEFAULT_SOUND;

    // Vibrate if vibrate is enabled
    notification.defaults |= Notification.DEFAULT_VIBRATE;
    notificationManager.notify(0, notification);      

}

	
	
	 private class LongGetOpen extends AsyncTask<String, Void, String> {
		 
		 
		 

		 
		  @Override
		  protected String doInBackground(String... params) {
		    // perform long running operation operation
			  String data1=null;
			  String startDate = params[0];
			  String endDate = params[1];
			  String base  = params[2];
			  String equip = params[3];
			  String seat = params[4];
			  String sby = params[5];
			  String sbyrq = "";
			  
			  if (sby.equals("true")){
				  sbyrq = "CHECKED";
			  } 
			  
			  
			 // String RunAsSvc = params[5];
			//  String interval = params[6];
			  
			  try{
			  
				String myUserAgent = "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2";
				
				//String headerH = "n001=" + base + "&"+ "n002=" + equip + "&" + "n003=" + seat + "&" + "n004=" + startDate + "&" + "n005=" + endDate + "&" + "n006=" + "Open Time Only" + "n007=on";
				
			  HttpPost httpPost = new HttpPost("https://pilot.fedex.com/vips-bin/vipscgi?webdd");
			  httpPost.setHeader("User-Agent", myUserAgent);
			    ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(8); 

			    nameValuePairs.add(new BasicNameValuePair("n001", base));
			    nameValuePairs.add(new BasicNameValuePair("n002", equip)); 
			   nameValuePairs.add(new BasicNameValuePair("n003", seat));
			    nameValuePairs.add(new BasicNameValuePair("n004", startDate)); 
			    nameValuePairs.add(new BasicNameValuePair("n005", endDate)); 
			    nameValuePairs.add(new BasicNameValuePair("n006", "Open Time Only")); 
			    if (sbyrq.equals("CHECKED"))
			    {
			    nameValuePairs.add(new BasicNameValuePair("n007", "on"));
			    }
			    	
			    nameValuePairs.add(new BasicNameValuePair( "n999"," Submit "));
			    
			   //  Encode the entities to UTF-8
			    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8));
			    // Response from the Http Request
			    
			    
				syncCookies();
			    localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
				HttpResponse response1 = httpClient.execute(httpPost);
			    
			    
		       HttpEntity resEntity1 = response1.getEntity();
		       data1 =  EntityUtils.toString(resEntity1);
		       resEntity1.consumeContent();	
		       
		       // check for bot and repost if necessary
		    
		      String src = "otrq";
		      String botCode = Botchecker(data1,src);
		       if (botCode != null)
		       {
		    	   
		    	   // get bot code
		    	 
				
		    	   data1=null;

			        HttpPost httpPost1 = new HttpPost("https://pilot.fedex.com/vips-bin/vipscgi?webdd");
				    List<NameValuePair> nameValuePairs1 = new ArrayList<NameValuePair>(10);

				    nameValuePairs1.add(new BasicNameValuePair("n001", base));// base
				    nameValuePairs1.add(new BasicNameValuePair("n002", equip)); //equip
				    nameValuePairs1.add(new BasicNameValuePair("n003", seat)); //seat
				    nameValuePairs1.add(new BasicNameValuePair("n004", startDate)); // start date
				    nameValuePairs1.add(new BasicNameValuePair("n005", endDate)); //end date
				    nameValuePairs1.add(new BasicNameValuePair("n006", "Open Time Only")); 
				    if (sbyrq.equals("CHECKED"))
				    {
				    nameValuePairs1.add(new BasicNameValuePair("n007", "on"));
				    }
				    nameValuePairs1.add(new BasicNameValuePair("nCTL", botCode.trim()));	
				    nameValuePairs1.add(new BasicNameValuePair("nTRN", "webdd"));
				    nameValuePairs1.add(new BasicNameValuePair("n999", "Submit"));
				    
		    	  
		    	   httpPost1.setEntity(new UrlEncodedFormEntity(nameValuePairs1, "UTF-8"));
				    HttpResponse response2 = httpClient.execute(httpPost1);
				    localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
				    syncCookies();
			       HttpEntity resEntity2 = response2.getEntity();
			        data1 =  EntityUtils.toString(resEntity2);
			       resEntity2.consumeContent();	
			     
			   	}
		  

			  } catch (IOException e) {
		      } catch (Exception e) {
		      }
			  
			  boolean results = storeOpenTime(data1,base,equip,seat);
			  if (!results){
				    String result = "failed";
				    return result;
			  }
			  		String result = "Success";
			  		return result;
		  	}	    

		  
		  @Override
		  protected void onPostExecute(String result) 
		  {
			  
			if (result.equals("Success")){
				pd.dismiss();
				
				if (newTrips) {
					
					generateOTNotification(ctx,"New Open Time Trips","true");
				Intent i = new Intent(OpenTime1.this, OpenTimeList.class);
				i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(i);
				} else {
				//	generateOTNotification(ctx," No New Open Time Trips","false");
			Intent i = new Intent(OpenTime1.this, OpenTimeList.class);
			i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(i);
			}
				
			
			}else{
				
				generateOTNotification(OpenTime1.this,"OT Update Failed","true");
				
				
			}
			
		  } 
		  @Override
		  protected void onPreExecute()
		  {
			  pd = ProgressDialog.show(ctx, "Progress", "Getting Open Time Data...", true, false);
		  
		  }
		 
		  
		  @Override
		  protected void onProgressUpdate(Void... values) 
		  {
		      // Things to be done while execution of long running operation is in progress. For example updating ProgessDialog
		   }
	}

	public  boolean storeOpenTime(String OpenTimeData, String base, String equip, String seat){
		
		//parse and load data to triplist activity 
	 
	 boolean success  = false;
     int status = 0;

	AlpaDataBaseHelper mdbh = new AlpaDataBaseHelper(OpenTime1.this);

	SQLiteDatabase db1 = mdbh.getWritableDatabase(); 

    db1.execSQL("Delete from Opentime where base <> '" + base +"'"); // clear any base data that's not in the current query
    
		
		
		 if (OpenTimeData != null) { 
			 int startAt = OpenTimeData.indexOf("<!-- OpenTrip");
			 int endAt = OpenTimeData.indexOf("<!-- E N D   O F   M A I N   C O N T E N T -->");
			 
			 if (startAt == -1) {
				startAt = endAt; 
			 }
	         String CalData = OpenTimeData.substring(startAt, endAt);
			
	                	  
	         String[] events = 	CalData.split("\n");
	                	
		 
		 
		 String pairing = null,showdate = null, showtime = null, enddate = null, endtime = null, Pay = null, tripdate,RV,Type;
		 String isDHD = "0"; String isbDHD = "0";
		
		  
		 
		 int lines=(events.length - 3);
		 // build array of new trips to check if they have been assigned
		 
		int tripcount = 0;
		
		// count the number of trips first to dimension the array
		 for (int x = 0;x<lines; x++){
			 if(events[x].toString().contains("OpenTrip")){
				 tripcount = tripcount +1 ;
			 }
		 }
		 
		 
		 
		 
		 ArrayList<String> newOTtripsList = new ArrayList<String>();// holds new trips in list Array
		 ArrayList<String> oldOTTripsList = new ArrayList<String>();// hold current trips in List Array
		 String[] newOTtrips = new String[tripcount];// array to hold data of new trips to insert into the sql lite db
		 
		////// get existing OT table data
		 
			String tsql = "select pairing,tripdate from opentime";
				Cursor cu = db1.rawQuery(tsql,null );
				int tripcnt = cu.getCount();
				cu.moveToFirst();
				// load into List
				
				for (int z = 0;z < tripcnt ; z++ )
				{
					String prg = cu.getString(cu.getColumnIndex("pairing"));
					String tripdte = cu.getString(cu.getColumnIndex("TripDate"));
					
					oldOTTripsList.add(prg+";"+tripdte);
					cu.moveToNext();
					
					
				}
				if (!cu.isClosed())
				{
					cu.close();
				}
				
				
				List<String> tripsToDelete = new ArrayList<String>(oldOTTripsList);
				
		/////////////////
				
				///Get the new  OT trip List
				
				
				 for (int x = 0;x<lines; x++){
					 
					 if(events[x].toString().contains("OpenTrip")){
						 // split the line into array elements
						 String[] OTdata = events[x].toString().split("\\|");
						 
					
						pairing = OTdata[1].toString();
					    tripdate = OTdata[5].substring(0, OTdata[5].indexOf("/") );
					    
					    newOTtripsList.add(pairing+";"+tripdate);
					    
					 }
				 }
				 List<String> OrigOTtrips = new ArrayList<String>(newOTtripsList);  // copy the original new trips before changing it
				newOTtripsList.removeAll(oldOTTripsList);  // removes all trips from new list that are in the oldTripsList	
				oldOTTripsList.removeAll(OrigOTtrips);
	// add the new newOTtripsList to the opentime db
				
	 if (!newOTtripsList.isEmpty())
	 {  // do all this stuff for the trips in the list
		 
	 int NewTripCount = 0;
	
	 for (int  x = 0;x<lines; x++){
		  String[] OTdata = events[x].toString().split("\\|");
		 
				 
				 if (OTdata[0].toString().contains("OpenTrip"))
				 {
					 
					
					 
					 
					 
					
					pairing =OTdata[1];;
				    tripdate = OTdata[4];
				    
				    RV = OTdata[2];
					Type = OTdata[3];
					String showdate1 = OTdata[5].substring(0, 7);
					String showtime1 = OTdata[5].substring(OTdata[5].indexOf("/") + 1,OTdata[5].indexOf("/") + 5);
					
					
					showdate = OTdata[4];
					showtime = showtime1;
					
					String enddate1 = OTdata[6].substring(0, 7);
					String endtime1 = OTdata[6].substring(OTdata[6].indexOf("/") + 1,OTdata[6].indexOf("/") + 5);
					enddate = enddate1;
					endtime = endtime1;
					Pay = OTdata[7];
					String id = pairing+";"+tripdate;
					
					if (newOTtripsList.contains(id))
					{
						newOTtrips[NewTripCount] = pairing+","+tripdate+","+RV+","+Type+","+showdate+","+showtime+","+enddate+","+endtime+","+Pay;
						NewTripCount++;
				 }
	      }
	 }

	 
	 
	 
	 for (int  x = 0;x < NewTripCount; x++)
	 {
				
				 
				 		String[] tripInfo = newOTtrips[x].split(",");
				 	
				 		pairing = tripInfo[0];
				 		tripdate = tripInfo[1];
				 		RV = tripInfo[2];
				 		Type = tripInfo[3];
				 		showdate = tripInfo[4];
				 		showtime = tripInfo[5];
				 		enddate = tripInfo[6];
				 		endtime = tripInfo[7];
				 		Pay = tripInfo[8].trim();
				 		
				 	
				 	
				 	 String TripDetails = getTripDetails(pairing,tripdate,base,equip,seat); // pass to ex and get trip deatils for database//
					 // parse out the data
					
				 	int StartAt = TripDetails.indexOf("<!--  B E G I N   M A I N   C O N T E N T  -->");
					int EndAt = TripDetails.indexOf("<!-- E N D   O F   M A I N   C O N T E N T -->");
					// look for botcheck code //
					
					
					String tripdata = TripDetails.substring(StartAt, EndAt);
					//String TripId = pairing + " " + showdate;
					
					// get DHD info
					
					int dhdstart = TripDetails.indexOf("!-- Flight|");
					int dhdend = TripDetails.indexOf("<!-- E N D   O F   M A I N   C O N T E N T -->");
					
					String legs = TripDetails.substring(dhdstart, dhdend);
					
					String[] activities = legs.split("\n");
					int legcount = activities.length;
					status = 1; //this is a new trip so mark the status true =1
					
					
					
						String[] firstlegDetails = activities[0].split("\\|");
						String[] lastlegDetails = activities[legcount-2].split("\\|");
					
					  if (firstlegDetails[3].isEmpty()){
						 
						 isDHD = "0";
					 } else {
						 isDHD = "1";
					 }
					  if (lastlegDetails[3].isEmpty()){
							 
							 isbDHD = "0";
						 } else {
							 isbDHD = "1";
						 }
					  String DHDstatus = isDHD + isbDHD;
						
					  try {
				    	   db1.execSQL("Insert into opentime Values (" + null  + ", " + "'" + pairing + "','" + RV + "','" + Type + "','" + tripdate + "','" +  showdate+ "','" +  showtime  + "','" + enddate + "','" + endtime + "','" + Pay + "','" + status + "','" + tripdata +"','" + base + "','" + DHDstatus + "')");		  
				    	 
				    	   	  success=true;
				       				}catch (Exception e){
				       				
				    	   			success=false;
				    	   			}
					 
				 	}
				if (NewTripCount > 0) {
				newTrips = true;
				} else {
				newTrips = false;
				}
				 	

		 }// there's no new trips to add so skip to here
		 // delet out trips that are in the local db but not in the newOTtripsList Array
	 success=true;
	 
	 
	     for (int i=0;i<oldOTTripsList.size(); i++)
	     {
	    	 String deleteData = tripsToDelete.get(i).toString();
	    	 int commaAtpos  = deleteData.indexOf(";");
	    	 String tripPrg = deleteData.substring(0, commaAtpos);
	    	 String tripDate = deleteData.substring(commaAtpos+1, deleteData.length());
	    	 db1.execSQL("delete from opentime where pairing ='"+tripPrg+"' and tripdate = '"+tripDate+"'");
	     }
	     
	     
	    
		 }
	    db1.close();
	    
		return success;
	}
	@SuppressWarnings("deprecation")
	private static void generateOTNotification(Context context, String message, String sound) {
	    int icon = R.drawable.alpalogo;
	    long when = System.currentTimeMillis();
	    NotificationManager notificationManager = (NotificationManager)
	            context.getSystemService(Context.NOTIFICATION_SERVICE);
	    Notification notification = new Notification(icon, message, when);

	    String title = context.getString(R.string.app_name);

	    Intent notificationIntent = new Intent(context, OpenTimeList.class);
	    // set intent so it does not start a new activity
	    notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
	            Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
	    PendingIntent intent =
	            PendingIntent.getActivity(context, 100, notificationIntent, 0);
	    notification.setLatestEventInfo(context, title, message, intent);
	    notification.flags |= Notification.FLAG_AUTO_CANCEL;

	    // Play default notification sound
	    if (sound.equals("true")){
	    notification.defaults |= Notification.DEFAULT_SOUND;
	    }
	    // Vibrate if vibrate is enabled
	    notification.defaults |= Notification.DEFAULT_VIBRATE;
	    notificationManager.notify(0, notification);      

	}   
	
	
	 public String Botchecker(String pageData, String src){
			
			String LinkToDigits;
			String botCode = null ;
			Bitmap bm = null;
			// search the string for occurance of ....  Please enter the control code shown below
			
			if (pageData.contains("Please enter the control code shown below to submit your request")) {
				
				// this a botpage
				if (src.equals("otrq")){
				LinkToDigits = "/vips-bin/vipscgi?webdgts??webdd";
				}else{
				LinkToDigits = "/vips-bin/vipscgi?webdgts??webtr";	
				}
				// go get the digits and display in alert dialog
				
				
				

				
				
					 	
						try {
						    HttpPost httpPost1 = new HttpPost("https://pilot.fedex.com/" + LinkToDigits);
						    HttpResponse response = httpClient.execute(httpPost1);
						    syncCookies();
							localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
						    HttpEntity resEntity = response.getEntity();
						    String data1 = EntityUtils.toString(resEntity); //  this string should have links to digits
						    resEntity.consumeContent();
			
						    Document doc = Jsoup.parse(data1);
						    Elements media = doc.select("[SRC]");
					   	 	
					   	 	
						    
					   	  StringBuilder sb = new StringBuilder();
					   	  for (org.jsoup.nodes.Element src1 : media) {  // get path of EGRID image from webpage
					   		  
					   		  String attrval = src1.attributes().toString();
					             if (attrval.contains("/vipsfiles/bots/")){
					           	  
					           	  attrval = attrval.substring(attrval.length() - 11);
					           	  attrval = attrval.substring(0,attrval.length()- 1);
					           	 String ImageURL = "https://pilot.fedex.com/vipsfiles/bots/" + attrval.trim();
					         
					           	HttpUriRequest request = new HttpGet(ImageURL);
					           	HttpResponse response1 = httpClient.execute(request);
					           
					             BufferedHttpEntity bufferedHttpEntity = new BufferedHttpEntity(response1.getEntity());

					              bm = BitmapFactory.decodeStream(bufferedHttpEntity.getContent());
					            
					                
					                String digitOCR =  u.readBotCode(bm);
					                  
					                  sb.append(digitOCR.toString());
					            }
					             
					   	  } // end for loop
					   	
					   	  botCode = sb.toString();
					   	 
					   	  return botCode;
						
						
						
						} catch (ClientProtocolException e) {
							return "Protocol Exception";
							} catch (IOException e) {
							return "IOException";
							}
						
			
						} else {
							
						return null;

						}
			
			
			
		}
	 public String getTripDetails(String tripnum,String dte, String base, String equip, String seat){

			

			// tripnum shoud be like 83Sep12
			
			String data2 = readuserdata();
		  	final String[] logdata2 = data2.split(",");
		  	
		  	String zululocal = logdata2[5].trim();
		  	String tripDetails = "No String";
		  	
		  	
		  	
		  
		  	try {
			String postString = "https://pilot.fedex.com/vips-bin/vipscgi?webtr?"+ tripnum +"?"+base.trim()+"?"+ equip + "?" + dte + "?N?Y?"+zululocal+"?";
		    HttpPost httpPost3 = new HttpPost(postString); // gets scheduled month details
		    HttpResponse hResponse1 = httpClient.execute(httpPost3);
		    
		    HttpEntity resEntity = hResponse1.getEntity();
		    tripDetails = EntityUtils.toString(resEntity);
		   resEntity.consumeContent();
		  
		   
		   String src = "triprq";
			  String botCode = Botchecker(tripDetails,src);
			  
			  if (botCode != null){
				  // resubmit with botcode
				 
				   
			  HttpPost httpPost2 = new HttpPost("https://pilot.fedex.com/vips-bin/vipscgi?webtr"); // + tripnum.trim()+"?" + base.trim()+"?"+equip.trim()+"?"+dte.trim()+"?"+"N?Y?"+botCode.toString()+"?"+ zululocal + "?");
			   List<NameValuePair> nameValuePairs2 = new ArrayList<NameValuePair>(9);

			    nameValuePairs2.add(new BasicNameValuePair("n001", tripnum.trim()));// base
			    nameValuePairs2.add(new BasicNameValuePair("n002", base.trim())); //equip
			    nameValuePairs2.add(new BasicNameValuePair("n003", equip.trim())); //equip
			    nameValuePairs2.add(new BasicNameValuePair("n004", dte.trim())); // trip date
			    nameValuePairs2.add(new BasicNameValuePair("n005", "Recap Format")); 
			   nameValuePairs2.add(new BasicNameValuePair("n006", "on")); 
			   
			   nameValuePairs2.add(new BasicNameValuePair("nCTL", botCode.toString()));	
			    nameValuePairs2.add(new BasicNameValuePair("nTRN", "webtr   "));
			    nameValuePairs2.add(new BasicNameValuePair("n999", " Submit "));
			    
			  
			    httpPost2.setEntity(new UrlEncodedFormEntity(nameValuePairs2, "UTF-8"));
			    HttpResponse response2 = httpClient.execute(httpPost2);
			    syncCookies();
			    localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
		        HttpEntity resEntity2 = response2.getEntity();
		        tripDetails =  EntityUtils.toString(resEntity2);
		        resEntity2.consumeContent();	
			   
			   }
			   
			   
				      
				      
				      
		  	} catch (IOException e) {
		           Log.e("Exception", "IOException", e);
		     } catch (Exception e) {
		           Log.e("Exception", "General Exception", e);      
			}
		  	
		  	
			return tripDetails;
		  	
		}  	
public void syncCookies()
	    
	    {
	    	
	    	List<Cookie> cookies = httpClient.getCookieStore().getCookies();

			if (! cookies.isEmpty()){
			        CookieSyncManager.createInstance(getApplicationContext());
			        CookieManager cookieManager = CookieManager.getInstance();

			        for(Cookie cookie : cookies){
	        	
			        	   sessionInfo = cookie;
			                String cookieString = sessionInfo.getName() + "=" + sessionInfo.getValue() + "; domain=" + sessionInfo.getDomain();
			                cookieManager.setCookie("pilot.fedex.com", cookieString);
			                CookieSyncManager.getInstance().sync();
			        }
			}
	    	
	    	
	    	
	    }	

public void clearOpenTimeData() {
	
	AlpaDataBaseHelper mdbh = new AlpaDataBaseHelper(OpenTime1.this);

	SQLiteDatabase db1 = mdbh.getWritableDatabase(); 
	 db1.execSQL("delete from opentime");
	 db1.close();
	 
	
	
	
	
	
}
} // end Open Time ACtivity
