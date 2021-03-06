package com.yctc.alpaware;

import java.util.List;

import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;

import android.view.Window;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.HttpAuthHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import android.widget.Toast;
  
@SuppressLint("SetJavaScriptEnabled")
public class pcalbrows extends SherlockActivity
{
	
	 public static final int Main_Menu =0;
	 public static final int EGRID=1;
	 
	 
    final Activity activity = this;
 
  
    @SuppressLint("SetJavaScriptEnabled")
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
    	
    	 
    	super.onCreate(savedInstanceState);
        this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.pcalbrows);
       
        
        WebView webView = (WebView) findViewById(R.id.webviewChr);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        
        webView.getSettings().setSupportZoom(true);  
	    webView.getSettings().setBuiltInZoomControls(true);
	    

	    setContentView(webView);
	    
      
	    String url = "https://pilot.fedex.com";
	    
       
	    
        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress)
            {
                activity.setTitle("Loading...");
                activity.setProgress(progress * 100);
  
                if(progress == 100)
                    activity.setTitle(R.string.app_name);
            }
        });
  
        webView.setWebViewClient(new WebViewClient() {
        	
        	 @Override
             public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
        		
             }// end onReceivedHttpAuthRequest
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl)
            {
               Toast.makeText(pcalbrows.this, description, Toast.LENGTH_LONG).show();
            }
            public void onPageFinished(WebView view, String url) {
                CookieSyncManager.getInstance().sync();
            }
            @SuppressLint("SetJavaScriptEnabled")
			@Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
            	
            	
            	CookieSyncManager.createInstance(pcalbrows.this);
            	CookieSyncManager.getInstance().startSync();

    				       
    				
         		
         		
            //    view.loadUrl(url);
                return false;
            }
        });
        
        	webView.getSettings().setJavaScriptEnabled(true);
	        webView.getSettings().setSupportZoom(true);  
		    webView.getSettings().setBuiltInZoomControls(true);
		    
		    
       

			
			    

			
		
        webView.loadUrl(url);
	
    }
    
    @Override
    public void onBackPressed()
    {
    	
    	WebView webView = (WebView) findViewById(R.id.webviewChr);
        if(webView.canGoBack())
            webView.goBack();
        else
            super.onBackPressed();
    }
   
    
    public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
		
		MenuInflater inflater=getSupportMenuInflater();
			inflater.inflate(R.menu.pfcoptionsmenu, menu);
			return super.onCreateOptionsMenu(menu);
			}

	
  	  
  	  
public boolean onOptionsItemSelected (com.actionbarsherlock.view.MenuItem item ){
 			
			WebView webView = (WebView) findViewById(R.id.webviewChr);
			 WebSettings webSettings = webView.getSettings();
			 webSettings.setJavaScriptEnabled(true);
				 String url = null;
				
				 
			switch (item.getItemId()){
			
			case R.id.mail :
				
				url = "https://pilot.fedex.com/owa/";
				webView.loadUrl(url);
			
			return true;	
			
			
			
		case R.id.checkin:
		
			url = "https://pilot.fedex.com/vips-bin/vipscgi?webckn";
			 webView.loadUrl(url);
		return true;
		
		
		case R.id.jumpseat :
			
			
			
			
			
			 
			 
			 webView.setWebViewClient(new WebViewClient() {
				 
				
		        	
	        	 @Override
	             public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
	        		
	             }// end onReceivedHttpAuthRequest
	            @Override
	            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl)
	            {
	               Toast.makeText(pcalbrows.this, description, Toast.LENGTH_LONG).show();
	            }
	            public void onPageFinished(WebView view, String url) {
	                CookieSyncManager.getInstance().sync();
	            }
	            @SuppressLint("SetJavaScriptEnabled")
				@Override
	            public boolean shouldOverrideUrlLoading(WebView view, String url)
	            {
	            	
	            	 DefaultHttpClient httpClient = AppSettings.getClient();
		   			 Cookie sessionInfo;
		   				List<Cookie> cookies = httpClient.getCookieStore().getCookies();

		   				if (! cookies.isEmpty()){
		   				        CookieSyncManager.createInstance(getApplicationContext());
		   				        CookieManager cookieManager = CookieManager.getInstance();
		   				        CookieManager.getInstance().setAcceptCookie(true);
		   				        cookieManager.removeAllCookie();

		   				        for(Cookie cookie : cookies){
		   				                sessionInfo = cookie;
		   				                String cookieString = sessionInfo.getName() + "=" + sessionInfo.getValue() + "; domain=" + sessionInfo.getDomain();
		   				                cookieManager.setCookie("https://pilot.fedex.com/jumpseat-bin/indexcgi", cookieString);
		   				                CookieSyncManager.getInstance().sync();
		   				        }
		   				}
	         		
		   			
	         
	                return false;
	            }
	        });	 
			
			
			url = "https://pilot.fedex.com/jumpseat-bin/index.cgi";
			
			
			webView.loadUrl(url);
		
			
			return true;
		
		case R.id.calendar :
			
			url = "https://pilot.fedex.com/vips-bin/vipscgi?webcal";
			webView.loadUrl(url);
			
		return true;
		
		case R.id.fltplan :
			url = "https://pilot.fedex.com/fpsweb/releaseByCrew.jsp";
			webView.loadUrl(url);
		return true;	
		
		case R.id.triptrade :
			url = "https://pilot.fedex.com/vips-bin/vipscgi?webmenu?bla";
			webView.loadUrl(url);
		return true;	
		
		case R.id.opentime :
			url = "https://pilot.fedex.com/vips-bin/vipscgi?webdd";
			webView.loadUrl(url);
			return true;
		
		case R.id.refresh :
			
			webView.reload();
			return true;
		
		case R.id.pilotlookup :
			url = "https://pilot.fedex.com/vips-bin/vipscgi?webphi";
			webView.loadUrl(url);
			return true;
		case R.id.back :
			super.onBackPressed();
			
		return true;
		
		case R.id.HomePage :
			url = "https://pilot.fedex.com/home/pilot/index.shtml";
			webView.loadUrl(url);
			return true;
		
		case R.id.home :
			
			
			Intent myIntent3 = new Intent(getBaseContext(), PocketCal20.class);	
			startActivityForResult(myIntent3, 0);
			
		return true;
			}
			return true;	
}}