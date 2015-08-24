package com.yctc.alpaware;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import com.commonsware.cwac.wakeful.WakefulIntentService;


public class OTWakefulSvc extends WakefulIntentService {

	public OTWakefulSvc() {
		super("OTWakefulSvc");
		
	}

	 int notifID = 1;
     String msg = "";
     
     Context ctx = OTWakefulSvc.this;
	 CookieStore cookieStore = new BasicCookieStore();
	 HttpContext localContext = new BasicHttpContext();
	 DefaultHttpClient httpClient = AppSettings.getClient();
	
	 Cookie sessionInfo;
    utilities u = new utilities();
    OpenTime1 ot = new OpenTime1();
    boolean NewTrips = false;
	
	
	
	
	
	@Override
	protected void doWakefulWork(Intent intent) {
		
		String startDate =  intent.getStringExtra("STARTDATE");
		String endDate =  intent.getStringExtra("ENDDATE");
		String base =  intent.getStringExtra("BASE");
		String equip =  intent.getStringExtra("EQUIP");
		String seat =  intent.getStringExtra("SEAT");
		String sby = intent.getStringExtra("STBY");
		
		
	
	
	new LongGetOpen().execute(startDate,endDate,base,equip,seat, sby);
		
		
		

		
	}
	private class LongGetOpen extends AsyncTask<String, Void, String> {
		 
		 
		String data1 = null;

		 
		  @Override
		  protected String doInBackground(String... params) {
		    // perform long running operation operation
			  
			  String startDate = params[0];
			  String endDate = params[1];
			  String base  = params[2];
			  String equip = params[3];
			  String seat = params[4];
			  String stby = params[5];
			
			  
			  
			  
			 
			   try{
			  
				   syncCookies();
			  HttpPost httpPost = new HttpPost("https://pilot.fedex.com/vips-bin/vipscgi?webdd");
			    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(7);

			    nameValuePairs.add(new BasicNameValuePair("n001", base));
			    nameValuePairs.add(new BasicNameValuePair("n002", equip)); 
			    nameValuePairs.add(new BasicNameValuePair("n003", seat));
			    nameValuePairs.add(new BasicNameValuePair("n004", startDate)); 
			    nameValuePairs.add(new BasicNameValuePair("n005", endDate)); 
			    nameValuePairs.add(new BasicNameValuePair("n006", "Open+Time+Only")); 
			    if (stby.equals("CHECKED"))
			    {
			    nameValuePairs.add(new BasicNameValuePair("n007", stby));
			    }
			    
			    
			    // Encode the entities to UTF-8
			    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
			    // Response from the Http Request
			    
				
			    HttpResponse response1 = httpClient.execute(httpPost);
			    localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
			  
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
				    if (stby.equals("CHECKED"))
				    {
				    nameValuePairs.add(new BasicNameValuePair("n007", stby));
				    }
				    nameValuePairs1.add(new BasicNameValuePair("nCTL", botCode.trim()));	
				    nameValuePairs1.add(new BasicNameValuePair("nTRN", "webdd   "));
				    nameValuePairs1.add(new BasicNameValuePair("n999", " Submit "));
				    
		    	  
		    	   httpPost1.setEntity(new UrlEncodedFormEntity(nameValuePairs1, "UTF-8"));
				    HttpResponse response2 = httpClient.execute(httpPost1);
				    localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
				   
			       HttpEntity resEntity2 = response2.getEntity();
			        data1 =  EntityUtils.toString(resEntity2);
			       resEntity2.consumeContent();	
			     
			   	}
		  

			  } catch (IOException e) {
		      } catch (Exception e) {
		      }
			  
			  boolean results = storeOpenTime(data1, base,equip,seat);
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
				if (NewTrips) {
					
					generateOTNotification(ctx,"New Open Time Trips","true","true");
					Intent i = new Intent(ctx, OpenTimeList.class);
					i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(i);
				} else {
					generateOTNotification(ctx," No New Open Time Trips","false","false");
				Intent i = new Intent(ctx, OpenTimeList.class);
				i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(i);
			}
			
			}else{
				 Intent i = new Intent(ctx, OTWakefulSvc.class);
				 PendingIntent pi = PendingIntent.getService(ctx, 0, i, 0);
				 AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
				 am.cancel(pi); 
				generateOTNotification(ctx,"OT Update Failed, Service Stopped","true","true");
				
				
			}
			
		  } 
		  @Override
		  protected void onPreExecute()
		  {
		
		  
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


	AlpaDataBaseHelper mdbh = new AlpaDataBaseHelper(ctx);

	final SQLiteDatabase db1 = mdbh.getWritableDatabase(); 


		
		
		 if (OpenTimeData != null) { 
			 int startAt = OpenTimeData.indexOf("<!--  B E G I N   M A I N   C O N T E N T  -->");
			 int endAt = OpenTimeData.indexOf("<!-- E N D   O F   M A I N   C O N T E N T -->");
	         String CalData = OpenTimeData.substring(startAt, endAt);
			
	                	  
	         String[] events = 	CalData.split("\n");
	                	
		 
		 
		 String pairing = null,showdate = null, showtime = null, enddate = null, endtime = null, Pay = null, tripdate,RV,Type;
		
		  
		 
		 int lines=(events.length - 3);
		 // build array of new trips to check if they have been assigned
		 
		int tripcount = 0;
		
		// count the number of trips first to dimension the array
		 for (int x = 0;x<lines; x++){
			 if(events[x].toString().contains("webtr")){
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
				
				List<String> tripsToDelete = new ArrayList<String>(oldOTTripsList);
				
		/////////////////
				
				///Get the new  OT trip List
				
				
				 for (int x = 0;x<lines; x++){
					 
					 if(events[x].toString().contains("webtr")){
						int tripstart = events[x].indexOf(">");
					 	int	tripend = events[x].indexOf("</");
						pairing = events[x].substring(tripstart + 1 , tripend).trim();
					    tripdate = events[x].substring(tripend + 13, tripend + 18).trim();
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
				 
				 if(events[x].toString().contains("webtr"))
				 {
					int tripstart = events[x].indexOf(">");
				 	int	tripend = events[x].indexOf("</");
					pairing = events[x].substring(tripstart + 1 , tripend).trim();
				    tripdate = events[x].substring(tripend + 13, tripend + 18).trim();
				    RV = events[x].substring(tripend + 5,tripend + 7 ).trim();
					Type = events[x].substring(tripend + 8, tripend + 12);
					showdate = events[x].substring(tripend + 19 , tripend + 24);
					showtime = events[x].substring(tripend + 25, tripend + 29);
					enddate = events[x].substring(tripend + 30, tripend + 35);
					endtime = events[x].substring(tripend + 36, tripend + 40);
					Pay = events[x].substring(tripend + 41, tripend + 46);
					String id = pairing+";"+tripdate;
					
					if (newOTtripsList.contains(id))
					{
						newOTtrips[NewTripCount] = pairing+","+tripdate+","+RV+","+Type+","+showdate+","+showtime+","+enddate+","+endtime+","+Pay;
						NewTripCount++;
				 }//else{ //delee it from Stored Database. It's no Longer open
				//	 String SQL = "delete from opentime where pairing = '" + pairing + "' and tripdate = '"+ tripdate + "'";
				//	 db1.execSQL(SQL); // deletes trip from open time
				// }
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
				 		Pay = tripInfo[8];
				 		
				 	
				 	
				 	 String TripDetails = getTripDetails(pairing,tripdate,base,equip,seat); // pass to ex and get trip deatils for database//
					 // parse out the data
					
				 	int StartAt = TripDetails.indexOf("<!--  B E G I N   M A I N   C O N T E N T  -->");
					int EndAt = TripDetails.indexOf("<!-- E N D   O F   M A I N   C O N T E N T -->");
					// look for botcheck code //
					
					
					String tripdata = TripDetails.substring(StartAt, EndAt);
					//String TripId = pairing + " " + showdate;
					
					 int status = 1; //this is a new trip so mark the status true =1
				
					
					  try {
				    	   db1.execSQL("Insert into opentime Values (" + null  + ", " + "'" + pairing + "','" + RV + "','" + Type + "','" + tripdate + "','" +  showdate+ "','" +  showtime  + "','" + enddate + "','" + endtime + "','" + Pay + "','" + status + "','" + tripdata +"','" + base + "')");		  
				    	   	
				    	   	  success=true;
				       				}catch (Exception e){
				       					
				    	   			success=false;
				    	   			}
					 
				 	}
				 		
			if (NewTripCount > 0)
			{
				NewTrips = true;
			} else {
				
				NewTrips = false;
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
	private static void generateOTNotification(Context context, String message, String sound, String vibrate) {
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
	    if (vibrate.equals("true")) {
	    notification.defaults |= Notification.DEFAULT_VIBRATE;	
	    }
	    
	    notificationManager.notify(0, notification);      

	}   
	@SuppressWarnings("deprecation")
	private static void generateOT1Notification(Context context, String message, String sound) {
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
	
 
 

  
  public String getTripDetails(String tripnum,String dte){

  	

  	// tripnum shoud be like 83Sep12
  	
  	String data2 = readuserdata();
    	final String[] logdata2 = data2.split(",");
    	String base = logdata2[2].trim();
     	String equip= logdata2[3].trim();
    	String zululocal = logdata2[5].trim();
    	String tripDetails = "";
    	
    	DefaultHttpClient httpClient = AppSettings.getClient();
    	
    
    	try {
  	String postString = "https://pilot.fedex.com/vips-bin/vipscgi?webtr?"+ tripnum +"?"+base.trim()+"?"+ equip + "?" + dte + "?N?Y?"+zululocal+"?";
      HttpPost httpPost3 = new HttpPost(postString); // gets scheduled month details
      HttpResponse hResponse1 = httpClient.execute(httpPost3);
      
      HttpEntity resEntity = hResponse1.getEntity();
      tripDetails = EntityUtils.toString(resEntity);
     resEntity.consumeContent();
    
     
     String src = "triprq";
  	  String botCode = ot.Botchecker(tripDetails,src);
  	  
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
}
