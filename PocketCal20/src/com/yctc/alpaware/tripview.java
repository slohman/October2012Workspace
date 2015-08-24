package com.yctc.alpaware;


import java.util.List;

import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.HttpAuthHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class tripview extends Activity{
	
	final Activity activity = this;
	
	@Override
    public void onCreate(Bundle Icicle) {
	 super.onCreate(Icicle);
     setContentView(R.layout.tripviewer);
     
      WebView wv = (WebView)findViewById(R.id.tripviewer);
     wv.getSettings().setJavaScriptEnabled(true);
      
      wv.getSettings().setBuiltInZoomControls(true);
      String tripdata = null;
     
     Bundle b = this.getIntent().getExtras();
    
     AlpaDataBaseHelper mdbh = new AlpaDataBaseHelper(this.getApplicationContext());
		final SQLiteDatabase db = mdbh.getWritableDatabase(); 
     
    // File SDCardRoot = Environment.getExternalStorageDirectory();
		String Pairing = b.getString("prg");
		String tripDate = b.getString("tripdate");
		
		Cursor c = db.rawQuery("select details from trips where pairing = '" + Pairing + "' and tripdate = '" + tripDate + "'", null);
 
   
		c.moveToFirst();
		if (c.getCount() == 1)
		{
		
		tripdata = c.getString(c.getColumnIndex("details"));
		String url = tripdata;
		url = tripdata;
		
		 wv.setWebChromeClient(new WebChromeClient() {
	            public void onProgressChanged(WebView view, int progress)
	            {
	                activity.setTitle("Loading...");
	                activity.setProgress(progress * 100);
	  
	                if(progress == 100)
	                    activity.setTitle(R.string.app_name);
	            }
	        });
	  
	        wv.setWebViewClient(new WebViewClient() {
	        	
	        	 @Override
	             public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
	        		 
	        		 String[] usernamePassword = view.getHttpAuthUsernamePassword(host, realm);
	                int x = 0;
	                 if ( x > 3) {
	                     Toast.makeText(getBaseContext(), "Login Failed. Please Try Again.", Toast.LENGTH_LONG).show();
	                     x++;
	                 } else {
	                     handler.proceed(usernamePassword[0], usernamePassword[1]);
	                 }// end else
	             }// end onReceivedHttpAuthRequest
	            @Override
	            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl)
	            {
	                // Handle the error
	            }
	  
	            @Override
	            public boolean shouldOverrideUrlLoading(WebView view, String url)
	            {
	            	
	            	 DefaultHttpClient httpClient = AppSettings.getClient();
	             	

	         		Cookie sessionInfo;
	         		List<Cookie> cookies = httpClient.getCookieStore().getCookies();

	         		if (! cookies.isEmpty()){
	         		        CookieSyncManager.createInstance(getApplicationContext());
	         		        CookieManager cookieManager = CookieManager.getInstance();
	         		       cookieManager.removeAllCookie();

	         		        for(Cookie cookie : cookies){
	         		                sessionInfo = cookie;
	         		                String cookieString = sessionInfo.getName() + "=" + sessionInfo.getValue() + "; domain=" + sessionInfo.getDomain();
	         		                cookieManager.setCookie("https://pilot.fedex.com", cookieString);
	         		                CookieSyncManager.getInstance().sync();
	         		        }
	         		}
	         		  view.getSettings().setJavaScriptEnabled(true);
	         	        view.getSettings().setSupportZoom(true);  
	         		    view.getSettings().setBuiltInZoomControls(true);
	         		    
	         		   
	         		   Intent i = new Intent(getBaseContext(),  pcalbrows.class);
		    			i.putExtra("URL", "https://pilot.fedex.com/"+ url);
		    			i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			    				 startActivity(i);
//	                view.loadDataWithBaseURL("https://pilot.fedex.com", url, "text/html", "UTF-8", "");
	                return true;
	            }
	        });
	        DefaultHttpClient httpClient = AppSettings.getClient();
	    	

			Cookie sessionInfo;
			List<Cookie> cookies = httpClient.getCookieStore().getCookies();

			if (! cookies.isEmpty()){
			        CookieSyncManager.createInstance(getApplicationContext());
			        CookieManager cookieManager = CookieManager.getInstance();
			        cookieManager.removeAllCookie();

			        for(Cookie cookie : cookies){
			                sessionInfo = cookie;
			                String cookieString = sessionInfo.getName() + "=" + sessionInfo.getValue() + "; domain=" + sessionInfo.getDomain();
			                cookieManager.setCookie("https://pilot.fedex.com", cookieString);
			                CookieSyncManager.getInstance().sync();
			        }
			}
	        
	       
	    
		
		 
		wv.loadDataWithBaseURL("", tripdata, "text/html", "UTF-8", "");
		}else{
		wv.loadDataWithBaseURL("","too many trips found","text/html", "utf-8","");
		}
     
 

	
}}
