package com.yctc.alpaware;




import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Messenger;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;






	public class egridmain1 extends SherlockActivity{
		
		Messenger myService = null;
		boolean isBound;


	     String LoginResult;

		 public static final int Main_Menu =0;
		 public static final int New_Result=1;
		 public static final int Go = 2;
		 public static final int Clear = 3;
		 public static final int goAuto = 4;
		 public static final int clos = 5;
		 public static final int Get_Egrid = 6;
		 public boolean SessionStarted = false;
		 
		 WebView myWebView;  
		  
			
		
		 String Col1;
		 int row1;
		 Drawable d;
		 Bitmap b;
		 Bitmap bm = null;
		 
		 String Month = "";
		 String action="";
		 String imagePath="imagePath";
		  
	      String sessionId="sessID",gridcode="aaa";
	      String redirect="/pfc",  tripDetails="details";
	      public static Cookie cookie = null;
	      Timer t = new Timer();
	      ActionBar actionBar;
		
		 Context ctx = egridmain1.this;
		 ProgressDialog pd;
		

		    
		
		 
		 
		
		 HttpContext localContext = new BasicHttpContext();
		 HttpPost httpPost = new HttpPost();
		 utilities u = new utilities();
		 CookieStore cookieStore = new BasicCookieStore();
		 HttpClient  client = new DefaultHttpClient();
		DefaultHttpClient httpClient = AppSettings.getClient();
		 Cookie sessionInfo;
		int count = 0;
		
		@Override
	 public void onCreate(Bundle Icicle) {
			 super.onCreate(Icicle);
		     setContentView(R.layout.egridresult1);
		     
		   actionBar = getSupportActionBar();
		   registerReceiver(egridrec, new IntentFilter("MSG_TO_EGRIDMAIN1"));
		   
		   
			
		 	     
		     
		     Button A = (Button) findViewById(R.id.A);
	         A.setOnClickListener(new View.OnClickListener() {
	             public void onClick(View view) {
	                 Col1="A";
	           
	                 row1=0;
	             }});;
	        Button B = (Button) findViewById(R.id.B);
	        B.setOnClickListener(new View.OnClickListener() {
	              public void onClick(View view) {
	              Col1="B";
	           
	              row1=0;
	                 }});;
	         Button C = (Button) findViewById(R.id.C);
	         C.setOnClickListener(new View.OnClickListener() {
	                     public void onClick(View view) {
	                      Col1="C";
	                     
	                         row1=0;
	                     }});;
	         Button D = (Button) findViewById(R.id.D);
	         D.setOnClickListener(new View.OnClickListener() {
	                      public void onClick(View view) {
	                      Col1="D";
	                     
	                      row1=0;
	                         }});;
	        Button E = (Button) findViewById(R.id.E);
	        E.setOnClickListener(new View.OnClickListener() {
	                      public void onClick(View view) {
	                      Col1="E";
	                   
	                      row1=0;
	                             }});;
	        Button F = (Button) findViewById(R.id.F);
	        F.setOnClickListener(new View.OnClickListener() {
	                      public void onClick(View view) {
	                      Col1="F";
	                     
	                      row1=0;
	                                 }});;
	        Button G = (Button) findViewById(R.id.G);
	        G.setOnClickListener(new View.OnClickListener() {
	                      public void onClick(View view) {
	                      Col1="G";
	                      row1=0;
	        }});;
	        Button H = (Button) findViewById(R.id.H);
	        H.setOnClickListener(new View.OnClickListener() {
	                     public void onClick(View view) {
	                     Col1="H";
	                  
	                     row1=0;
	        }});;
	        Button I = (Button) findViewById(R.id.I);
	        I.setOnClickListener(new View.OnClickListener() {
	                     public void onClick(View view) {
	                     Col1="I";
	                     row1=0;
	        }});;
	        Button J = (Button) findViewById(R.id.J);
	        J.setOnClickListener(new View.OnClickListener() {
	                     public void onClick(View view) {
	                     Col1="J";
	                     
	                     row1=0;
	        }});;
	        Button b1 = (Button) findViewById(R.id.num1);
	        b1.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) {
	                row1=1;
	                count=count+1;
	                calcGrid(Col1,row1,count);
	                
	            }});;
	       Button b2 = (Button) findViewById(R.id.num2);
	       b2.setOnClickListener(new View.OnClickListener() {
	             public void onClick(View view) {
	             row1=2;
	             count=count+1;
	             calcGrid(Col1,row1,count);
	                }});;
	        Button b3 = (Button) findViewById(R.id.num3);
	        b3.setOnClickListener(new View.OnClickListener() {
	                    public void onClick(View view) {
	                       row1=3;
	                       count=count+1;
	                       calcGrid(Col1,row1,count);
	                    }});;
	        Button b4 = (Button) findViewById(R.id.num4);
	        b4.setOnClickListener(new View.OnClickListener() {
	                     public void onClick(View view) {
	                    row1=4;
	                    count=count+1;
	                    calcGrid(Col1,row1,count);
	                        }});;
	       Button b5 = (Button) findViewById(R.id.num5);
	       b5.setOnClickListener(new View.OnClickListener() {
	                     public void onClick(View view) {
	                     row1=5;
	                     count=count+1;
	                     calcGrid(Col1,row1,count);
	                            }});

	       TextView res = (TextView)findViewById(R.id.coderesult);
	       
	       res.setOnClickListener(new View.OnClickListener() {
	        	  public void onClick(View view) {
	        		  TextView res1 = (TextView)findViewById(R.id.coderesult);
	        		  res1.setBackgroundColor(Color.BLACK);
	        		String manGridCode = res1.getText().toString();
	        		 String data2 = readuserdata();
				  	  final String[] logdata2 = data2.split(",");
				   String ip = imagePath;
					     String sID = sessionId;
					
			        new LongLoginWithEgrid().execute(logdata2[0].toString(),manGridCode,ip,sID);
	        		  
	        		  
	        		  
	        		  
	        		  
	        	  }
	       });
	          
	          TextView fgc = (TextView)findViewById(R.id.tvEgridConvert);
	          fgc.setOnClickListener(new View.OnClickListener() {
	        	  public void onClick(View view) {
	        		  TextView gridResults = (TextView)findViewById(R.id.tvEgridConvert);
	        		  EditText finalGridCode = (EditText)findViewById(R.id.coderesult);
						
						// send data to computeEgrid
						String OCRdata = gridResults.getText().toString();
						String autoresults = computeEgrid(OCRdata);
						finalGridCode.setText(autoresults);
					//start session and login with egrid, then launch onlinelauncher page
						  String data2 = readuserdata();
					  	  final String[] logdata2 = data2.split(",");
					  	EditText etgrid = (EditText)findViewById(R.id.coderesult);
						 String grid = etgrid.getText().toString();
						 
						 
						
					     String ip = imagePath;
					     String sID = sessionId;
					
			        		new LongLoginWithEgrid().execute(logdata2[0].toString(),grid,ip,sID);
					     
				}});
	          
	          
	         
	          
	          
		 } 
		

		@Override
		protected void onResume() {
			super.onResume();
		registerReceiver(egridrec, new IntentFilter("Action_Login"));
		
		}
		@Override
		protected void onPause() {
			super.onPause();
		}
		@Override
		protected void onStop(){
		unregisterReceiver(egridrec);
		super.onStop();
		}
	 
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
					
				MenuInflater inflater=getSupportMenuInflater();
					inflater.inflate(R.menu.egridmenubar, menu);
					return super.onCreateOptionsMenu(menu);
					}

			
		  	  
		  	  
	 public boolean onOptionsItemSelected (com.actionbarsherlock.view.MenuItem item ){
		 			
		 
		 
					switch (item.getItemId()){
					
					case R.id.getEgrid  :
						
						 new LongOperationGetEGRID().execute();
						  
					
					return true;	
				
				case R.id.clearCode :
					
					TextView res = (TextView)findViewById(R.id.coderesult);
					res.setBackgroundColor(Color.BLACK);
					count=0;
					res.setText("");
					
				return true;
				


				case R.id.back :
					super.onBackPressed();
					
				return true;
				case R.id.home :
					
					
					Intent myIntent3 = new Intent(getBaseContext(), PocketCal20.class);	
					startActivityForResult(myIntent3, 0);
					
				return true;
				
				
					}
					return false;			
					
			
					
	}
		
		  	  	



	

	private void  calcGrid(String Col1, int row, int cnt){
		// read data from egrid table with or statements
		TextView res = (TextView)findViewById(R.id.coderesult);
		
		if (cnt > 3){
			res.setText("");
			res.setBackgroundColor(Color.BLACK);
			count = 0;
		}
		
		if (cnt <= 3){
		
		UserDataHelper mdbh = new UserDataHelper(egridmain1.this.getApplicationContext());
	    final SQLiteDatabase db = mdbh.getWritableDatabase();
		
		final Cursor c = db.rawQuery("Select " + Col1 + " from egrid where _id = " + row1  ,null);
		if (c.getCount() > 0) {
			
			
			
		
		c.moveToFirst();
		String Result =  c.getString(0).toString();
		
		
		
		
		res.setText ( res.getText().toString() +  Result) ;
		if (cnt==3){
			res.setBackgroundColor(Color.GREEN);
			
		}
		
		c.close();
		db.close();
		}else{
			
			utilities util = new utilities();
			util.showaction("Please load EGIRD or restore USER DB from SD",egridmain1.this);
			finish();}
		
		}	
		
	}



	////////////  initial login and get egrid image

	private class LongOperationGetEGRID extends AsyncTask<String, Void, Bitmap> {
	 
		  @Override
		  protected Bitmap doInBackground(String... params) {
		    // perform long running operation operation
			  
			
				
				
				bm = startSession();
					return bm;
			
				
			
			
			 }
			 
		  
		 
		 
		  @Override
	       protected void onPostExecute(Bitmap result) {
		    // execution of result of Long time consuming operation
			 pd.dismiss();
			  
		     ImageView iv = (ImageView)findViewById(R.id.EGRIDVw);
			
			 iv.setImageBitmap(result);
			
	         utilities u = new utilities();
	         String data2 = readuserdata();
	      	  final String[] logdata2 = data2.split(",");
	    
	   		int BWlvl = Integer.parseInt(logdata2[6].toString());
	   		
			 
			 
			 String egridCode = null;
			 if (result != null){
				egridCode = u.convertBM(result,BWlvl);
			 }else{
				 Intent myIntent2 = new Intent(getBaseContext(), PocketCal20.class);	
					startActivityForResult(myIntent2, 0);
			 }
			 
			 TextView eg = (TextView)findViewById(R.id.tvEgridConvert);
			 eg.setText(egridCode);	 
			
			
			 
	 }
		 
		 
		  @Override
		  protected void onPreExecute() {
		  // Things to be done before execution of long running operation. For example showing ProgessDialog
			  pd = ProgressDialog.show(ctx, "Loading EGRID Image...","please wait", true, false);
		  }
		 
		  
		  @Override
		  protected void onProgressUpdate(Void... values) {
		      // Things to be done while execution of long running operation is in progress. For example updating ProgessDialog
			 
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
	public String getImageUrl(String pfcResponse){
		String url2 = null;
		Document doc = Jsoup.parse(pfcResponse);
	   	Elements media = doc.select("[src]");
	   	 
	   	
	   	  for (org.jsoup.nodes.Element src : media) {  // get path of EGRID image from webpage
	   		  
	   		  String attrval = src.attributes().toString();
	             if (attrval.contains("captcha")){
	           	  
	           	  attrval = attrval.substring(6);
	           	  attrval = attrval.substring(0,attrval.length()-1);
	           	  
	           	  
	           	    String 	  url1 = "https://pilot.fedex.com" + attrval;
	           	    int imagelen = url1.indexOf("jpeg", 0);
	           	     url2 = url1.substring(0, imagelen + 4);
	             								}
	             
	   	  											}
		return url2;
		

	}
	public String getSessionId(String pfcResponse){
		
		Document doc = Jsoup.parse(pfcResponse);
	   	Elements cred = doc.select("[name]");
	   	 
	   	
	   	  for (org.jsoup.nodes.Element id : cred) {  // get path of EGRID image from webpage
	   		  
	   		  String attrval = id.attributes().toString();
	             if (attrval.contains("sessionID")){
	           	  int st = attrval.indexOf("value=") + 7;
	           	 
	           	  
	           //	  attrval = attrval.substring(6);
	           	  attrval = attrval.substring(st,attrval.length()-1);
	           	  sessionId = attrval;
	           	  	}
	             }
		return sessionId;}
	////////////

	


	/// login routines //////
	public boolean isNetworkAvailable() {
	    ConnectivityManager cm = (ConnectivityManager) 
	    	getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo networkInfo = cm.getActiveNetworkInfo();
	    // if no network is available networkInfo will be null
	    // otherwise check if we are connected
	    if (networkInfo != null && networkInfo.isConnected()) {
	        return true;
	    }
	    return false;
	} 
	
	

	public String computeEgrid(String codePairs){
		
		String[] pairs = codePairs.split(" ");

		String Col1 = null;
		int row = 0;
		String Result = "";
		
		UserDataHelper mdbh = new UserDataHelper(egridmain1.this.getApplicationContext());
	    final SQLiteDatabase db = mdbh.getWritableDatabase();
		
		for (int i = 0 ; i<=2;i++){
			
			Col1 = pairs[i].substring(0,1);
			row = Integer.parseInt(pairs[i].substring(1,2));
			
			Cursor c = db.rawQuery("Select " + Col1 + " from egrid where _id = " + row  ,null);
			if (c.getCount() > 0) {
			
			c.moveToFirst();
			Result =  Result + c.getString(0).toString();

		}
			Result = Result.trim();
		c.close();	
		
	}
		
		db.close();
		return Result;
	}

	private class LongLoginWithEgrid extends AsyncTask<String, Void, String> {
		 
		  @Override
		  protected String doInBackground(String... params) {
		    // perform long running operation operation
			  
			  
			  String  uid = params[0];
			  String grd = params[1];
			  String imageP = params[2];
			  String sessID = params[3];
			  String redir = "https://pilot.fedex.com/";
			  
			 
			 
			 //////
			  
			  try {
			   
				
					
			       HttpPost httpPost = new HttpPost("https://pilot.fedex.com/TwoFactorAuthentication/AuthenticationController/secondfactor");
				    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(5);

				    nameValuePairs.add(new BasicNameValuePair("userId", uid));
				    nameValuePairs.add(new BasicNameValuePair("imagePath", imageP));
				    nameValuePairs.add(new BasicNameValuePair("redirectPath",redir));
				    nameValuePairs.add(new BasicNameValuePair("sessionID", sessID));
				    nameValuePairs.add(new BasicNameValuePair("gridResponse", grd));
				    // Encode the entities to UTF-8
				    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
				    // Response from the Http Request
				    
					
				    HttpResponse response2 = httpClient.execute(httpPost);
				    localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
				    
			       HttpEntity resEntity2 = response2.getEntity();
			       String data2 =  EntityUtils.toString(resEntity2);
			       resEntity2.consumeContent();
			       
			       
			  //////////////////////////////////////     
			   if (!data2.contains("SSO Login"))
			   {
				   SessionStarted = true;
				   
			      return "Login Success" + "," + uid;
			   }else{
				   String errmsg = "Egrid Login failed";
			    	 return errmsg;
			   }
			 ///////////////////////////////////////////  
			   
			         				
			   
			    //////////////////////////////////////////////  
			     } catch (IOException e) {
			           Log.e("Exception", "IOException", e);
			     } catch (Exception e) {
			           Log.e("Exception", "General Exception", e);
			    } finally {}
				/////////////////////////////////////////////// 
			  SessionStarted = true;
			   return "Login Success" + "," + uid;      

		    
		  }
		 
		 
		  @Override
		  protected void onPostExecute(String result) {
		    // execution of result of Long time consuming operation
			// parse data and write to file
			  pd.dismiss();
			// launch the selection menu
			  
			  String LoginResult[] = result.split(",");
			 
			 
				 
				 if (LoginResult[0].toString().equals("Login Success")){
				Toast.makeText(ctx,"Session Started", Toast.LENGTH_LONG).show();	 
			    Intent myIntent = new Intent(getBaseContext(), onlinemenu1.class);
			   
	            
	            startActivityForResult(myIntent, 0);
				 }	 
		 }
		 
		 //////// Start routines for Various PFC Fucntions /////////////////////////////
		  @Override
		  protected void onPreExecute() {
		  // Things to be done before execution of long running operation. For example showing ProgessDialog
			  
			  pd = ProgressDialog.show(ctx, "Logging in, Starting Session ","please wait", true, false);
		  
		  }
		 
		  
		  @Override
		  protected void onProgressUpdate(Void... values) {
		      // Things to be done while execution of long running operation is in progress. For example updating ProgessDialog
		   }
	}


private Bitmap startSession() {
		
		  if (isNetworkAvailable()){
		  
		   try {
			 	
		
		 
		  
		  String data = readuserdata();
		         if (data != "File Missing")   { // check for user data
		 
			  String[] logdata = data.split(",");
		  final String uid = logdata[0];
		  final String pwd = logdata[1];
		  

		  HttpPost httpPost1 = new HttpPost("https://pilot.fedex.com/index.html");
		   
		   localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
		  
		   HttpResponse response = httpClient.execute(httpPost1);
		   // HttpEntity resEntity = response.getEntity();
		   String data1 = EntityUtils.toString(response.getEntity());
		  //  resEntity.consumeContent();
		
		   
		 
		
			if (data1.contains("Secure Login"))
		 //  if (uid.length() > 2)
			
			{
			 //// post username and password to pfc to get egrid link page
			

				 
			     HttpPost httpPost = new HttpPost("https://pilot.fedex.com/TwoFactorAuthentication/AuthenticationController/firstfactor");
			     List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			     nameValuePairs.add(new BasicNameValuePair("userId", uid));
				 nameValuePairs.add(new BasicNameValuePair("password", pwd));
				 httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
				
				 localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
				  HttpResponse response1 = httpClient.execute(httpPost);
			     HttpEntity resEntity1 = response1.getEntity();
			     String pfcResponse =  EntityUtils.toString(resEntity1);
			     resEntity1.consumeContent();
			
			  
				         String ImageURL = getImageUrl(pfcResponse);
			             sessionId = getSessionId(pfcResponse);
			             redirect = "https://pilot.fedex.com/redirect/";
			             imagePath = ImageURL;
			             
			             
			         	HttpUriRequest request = new HttpGet(ImageURL);
			           	HttpResponse response3 = httpClient.execute(request);
			           	
			           	// long contentLenght = response.getEntity().getContentLength();
			             BufferedHttpEntity bufferedHttpEntity = new BufferedHttpEntity(response3.getEntity());
			             
			             
			            Bitmap   bm = BitmapFactory.decodeStream(bufferedHttpEntity.getContent());
			           
			            return bm;
			            
			}      
			            
		         		} else {
			 
		         			Intent myIntent1 = new Intent(getBaseContext(), setUserPwd.class);
		         			startActivityForResult(myIntent1, 0);
		         		}
		         	
		   
		   			} catch (IOException e) {
		         		Log.e("Exception", "IOException", e);
		         	}
		        
		
		
		 			
		
		  	}
		return null;
		  
	
	}

	
	
	
	
private  BroadcastReceiver egridrec = new BroadcastReceiver(){
		@Override
		
		public void onReceive(Context c, Intent i) {
			String data = null;
			
			 data = i.getExtras().getString("msg"); // get the OT Data string from the http Client
			/// send the string to processing method
			
			Toast.makeText(egridmain1.this,data, Toast.LENGTH_LONG).show();
		}};
		


		    
		    
		    
  public void syncCookies()
		    
		    {
		    	
		    	List<Cookie> cookies = httpClient.getCookieStore().getCookies();

				if (! cookies.isEmpty()){
				        
				        CookieManager cookieManager = CookieManager.getInstance();

				        for(Cookie cookie : cookies){
		        	
				        	   sessionInfo = cookie;
				                String cookieString = sessionInfo.getName() + "=" + sessionInfo.getValue() + "; domain=" + sessionInfo.getDomain();
				                cookieManager.setCookie("pilot.fedex.com", cookieString);
				                
				        }
				}
		    }
		    

		    



	}// end class

