package com.yctc.alpaware;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import android.app.IntentService;
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
import android.os.Environment;
import android.text.format.Time;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;


public class OTIntentSvc extends IntentService {
	  
	 private NotificationManager mNM;

	    // Unique Identification Number for the Notification.
	    // We use it on Notification start, and to cancel it.
	 
		
	     int notifID = 1;
	     String msg = "";
	     
	     Context ctx = OTIntentSvc.this;
  		 CookieStore cookieStore = new BasicCookieStore();
  		 HttpContext localContext = new BasicHttpContext();
  		 DefaultHttpClient httpClient = AppSettings.getClient();
  		
  		 Cookie sessionInfo;
	    

	
	
	public OTIntentSvc() {
		super("OTIntentSvc");
		
	}
	
  


	@Override
	protected void onHandleIntent(Intent intent) {

			new refreshConnection().execute("");
    	
    	
	}
    	 private class refreshConnection extends AsyncTask<String, Void, String> {
  		  
  		   
  			@Override
  			protected String doInBackground(String... params) {
  			    // perform long running operation operation
  				  String data1="";
  				  
  				  List<Cookie> cookies = httpClient.getCookieStore().getCookies();

  					if (! cookies.isEmpty()){
  					        CookieSyncManager.createInstance(ctx);
  					        CookieManager cookieManager = CookieManager.getInstance();

  					        for(Cookie cookie : cookies){
  			        	
  					        	   sessionInfo = cookie;
  					                String cookieString = sessionInfo.getName() + "=" + sessionInfo.getValue() + "; domain=" + sessionInfo.getDomain();
  					                cookieManager.setCookie("pilot.fedex.com", cookieString);
  					                CookieSyncManager.getInstance().sync();
  					        }
  					}
  				
  				  try{
  					   HttpPost httpPost1 = new HttpPost("https://pilot.fedex.com/home/pilot/index.shtml");
  					   HttpResponse response1 = httpClient.execute(httpPost1);
  					   localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
  					   
  				       HttpEntity resEntity1 = response1.getEntity();
  				       data1 =  EntityUtils.toString(resEntity1);
  				       resEntity1.consumeContent();
  				      
  				      	    
  				
  				 } catch (IOException e) {
  			     } catch (Exception e) {
  			     }
  				return data1;
  				 }	  
  			
  			 
  			  
  			  @Override
  			  protected void onPostExecute(String result) 
  			  {
  				  
  			       if (result.contains("FedEx Express | Flight Operations"))
  			       {
  			    	 Calendar cal = Calendar.getInstance();
  			    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
  			    	String now = sdf.format(cal.getTime());
  			    	 
  				     String msg="connection refreshed at "+ now;
  			         generateNotification(ctx,msg);
  			    	 
  			       }else{
  			    	 Intent i = new Intent(OTIntentSvc.this, OTIntentSvc.class);
					 PendingIntent pi = PendingIntent.getService(OTIntentSvc.this, 0, i, 0);
					 AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
					 am.cancel(pi); 
  				     String msg="refresh fail. Logon to PFC Again!, Keep Alive Cancelled";
  			         generateNotification(ctx,msg);
  			  		}
  			  }
  			 
  			 
  			  @Override
  			  protected void onPreExecute()
  			  { }
  			 
  			  
  			  @Override
  			  protected void onProgressUpdate(Void... values) 
  			  {}
  		}
  	 
  	 private static void generateNotification(Context context, String message) {
  		 int icon = R.drawable.alpalogo;
  		 
  		    long when = System.currentTimeMillis();
  		    NotificationManager notificationManager = (NotificationManager)
  		    		context.getSystemService(Context.NOTIFICATION_SERVICE);
  		    Notification notification = new Notification(icon, message, when);

  		    String title = context.getString(R.string.app_name);

  		    Intent notificationIntent = new Intent(context,egridmain1.class);
  		    // set intent so it does not start a new activity
  		    notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
  		            Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK );
  		    
  		    notification.flags |= Notification.FLAG_AUTO_CANCEL;
  		    notification.flags|= Notification.DEFAULT_VIBRATE;
  		    PendingIntent intent =
  		            PendingIntent.getActivity(context, 10, notificationIntent, 0);
  		  //  notification.setLatestEventInfo(context, title, message, intent);
  		    notification.setLatestEventInfo(context, title, message, PendingIntent.getActivity(context, 0, new Intent(), 0));


  		    // Play default notification sound
  		  //  notification.defaults |= Notification.DEFAULT_SOUND;

  		 notificationManager.notify(0, notification);      


  		}
  	
		
		
		
		
		
		}	
		
