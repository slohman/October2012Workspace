package com.yctc.alpaware;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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




import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.Config;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;

import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.EditText;
import android.widget.Toast;




import com.googlecode.tesseract.android.TessBaseAPI;
import com.jabistudio.androidjhlabs.filter.DespeckleFilter;

import com.jabistudio.androidjhlabs.filter.ReduceNoiseFilter;
import com.jabistudio.androidjhlabs.filter.SharpenFilter;

import com.jabistudio.androidjhlabs.filter.util.AndroidUtils;


public class utilities extends Activity{
	
	HttpContext localContext = new BasicHttpContext();
	 CookieStore cookieStore = new BasicCookieStore();

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
       
    }
        
	
	Context ctx = this;
	
	String response;
	final File sd = new File(Environment.getExternalStorageDirectory().toString() + "/PocketCalDatabases//" );
 //final File DataDir = new File(Environment.getDataDirectory().toString() + "/data/com.yctc.alpaware/databases/");

	


	@SuppressWarnings("resource")
	public String saveDB(String dbase, Context ctx) throws IOException { 
		File sd = new File(Environment.getExternalStorageDirectory().toString() + "/PocketCalDatabases/");
		//final File DataDir = new File(Environment.getDataDirectory().toString()+"/databases/");
	try {	
		if (sd.canWrite() == true){
			//do Nothing
		}else{
			sd.mkdirs();}
			
			File currentDB =  new File(ctx.getDatabasePath(dbase).toString());
			File backupDB = new File(sd,  dbase);
			FileChannel	src = new FileInputStream(currentDB).getChannel();
			FileChannel	dst = new FileOutputStream(backupDB).getChannel();
		
			dst.transferFrom(src, 0, src.size());
			response = dbase + " Saved to SD Card";
			
			src.close();
			dst.close();
			
			
			
			} catch (IOException e) {
				
				response = dbase + " Save Failed";
			}
				
			
			return response;

		  	 		};
 

		  	 		

public String  restoreDB(String dbase, Context ctx) throws IOException {
	String result = "";
	try {

		//File DataDir = new File(Environment.getDataDirectory().toString()+"/databases/");
		if (sd.canRead()) {
		File currentDB = new File(ctx.getDatabasePath(dbase).toString());
		File SDsrc = new File(sd, dbase);
		if (SDsrc.exists()== true) {
		FileChannel src1 = new FileInputStream(SDsrc).getChannel();
		FileChannel dst1 = new FileOutputStream(currentDB).getChannel();
		dst1.transferFrom(src1, 0, src1.size());
		src1.close();
		dst1.close();
		
		result=dbase + " Restored";
		}}

		} catch (Exception e) {
			result= dbase + " Restore failed";
		}
	return result;

}

public String  restoreDBUpload(String dbase, Context ctx) throws IOException {
	String result = "";
	try {

		File sd1 = new File(Environment.getExternalStorageDirectory().toString() + "/PocketCalDatabasesUpload/");
		// File DataDir1 = new File(Environment.getDataDirectory().toString() + "/data/com.yctc.alpaware/databases/");
		File DataDir1 = new File(getFilesDir().getParent()+"/databases/");
		if (sd.canWrite()) {
		File currentDB = new File(DataDir1, dbase);
		File SDsrc = new File(sd1, dbase);
		if (SDsrc.exists()== true) {
		FileChannel src1 = new FileInputStream(SDsrc).getChannel();
		FileChannel dst1 = new FileOutputStream(currentDB).getChannel();
		dst1.transferFrom(src1, 0, src1.size());
		src1.close();
		dst1.close();
		result=dbase + " Restored";
		}}

		} catch (Exception e) {
			result= dbase + " Restore failed";
		}
	return result;

}
public void showaction(String msg, Context ctx){
	
	int duration = Toast.LENGTH_SHORT;

	Toast toast = Toast.makeText(ctx, msg, duration);
	toast.show();
	}	




// HTTPS handlers
public String NewinstallDB(String dbase, Context ctx){    
	// install files to system if they don't already exist
	
	String result = "";
	try {

		
		if (sd.canWrite()) {
		File currentDB = new File(ctx.getFilesDir().toString() , dbase);
		File SDsrc = new File(sd, dbase);
		if (SDsrc.exists()== true) {
		FileChannel src1 = new FileInputStream(SDsrc).getChannel();
		FileChannel dst1 = new FileOutputStream(currentDB).getChannel();
		dst1.transferFrom(src1, 0, src1.size());
		src1.close();
		dst1.close();
		result=dbase + " Installed";
		}}

		} catch (Exception e) {
			result= dbase + " Restore failed";
		}
	return result;}




public String readEGRID(Bitmap bm){
	File myDir = new File(Environment.getExternalStorageDirectory().toString() + "/tessdata/");

	
	
	TessBaseAPI baseApi = new TessBaseAPI();
	baseApi.init(myDir.toString(), "eng"); // myDir + "/tessdata/eng.traineddata" must be present
	

	
	baseApi.setVariable("tessedit_char_whitelist","ABCDEFGHIJ12345,[]");
	baseApi.setVariable("tessedit_char_blacklist","KLMNOPQRSTUVWXYZ|={}()-_");
	baseApi.setVariable("textord_min_linesize", "1.5");
	baseApi.setVariable("language_model_penalty_non_freq_dict_word", ".11");
	
	
	
	Config frmt = bm.getConfig();
	
	if (frmt.toString() != "ARGB_8888"){
	Bitmap ARGB = JPEGtoRGB888(bm); 
	
	baseApi.setImage(ARGB);
	
	
	String recognizedText = baseApi.getUTF8Text(); // Log or otherwise display this string...
	baseApi.end();
	

	
	String EgridCode = decodeEgrid(recognizedText);
		
	return EgridCode;
	

	
	
	
	}else{
		
		baseApi.setImage(bm);
		
		
		String recognizedText = baseApi.getUTF8Text(); // Log or otherwise display this string...
		baseApi.end();
		//String[] egridpairs = recognizedText.split(" ");

		
		String EgridCode = decodeEgrid(recognizedText);
			
		return EgridCode;
		
	}

	
	

}

public Bitmap JPEGtoRGB888(Bitmap img)
{
        int numPixels = img.getWidth()* img.getHeight();
        int[] pixels = new int[numPixels];

    //Get JPEG pixels.  Each int is the color values for onepixel.
        img.getPixels(pixels, 0, img.getWidth(), 0, 0, img.getWidth(),
img.getHeight());

    //Create a Bitmap of the appropriate format.
    Bitmap result = Bitmap.createBitmap(img.getWidth(),
img.getHeight(), Config.ARGB_8888);

    //Set RGB pixels.
    result.setPixels(pixels, 0, result.getWidth(), 0, 0,
result.getWidth(), result.getHeight());
    return result;
} 
public String convertBM(Bitmap bitmap,int BWlvl) {
	

	
	

	
	// for test read egrid from sd card
  Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
  
    // replace all pixel with red value less than 200 with white and > 200 with black
    // then OCR the image.
  				newBitmap.setDensity(400);
  				String[][] pxdata = new String[bitmap.getWidth()][bitmap.getHeight()];
				for (int x = 0;x<bitmap.getWidth();x++){
					for (int y = 0;y < bitmap.getHeight() ;y++){
				int colorR = bitmap.getPixel(x, y);
					int Red = Color.red(colorR);
					int Green = Color.green(colorR);
					int Blue = Color.blue(colorR);
					
					pxdata[x][y] = Red+","+Green+","+Blue;
				    double score = (Red*2.5) + (Green * 2) + (Blue * .5);
					     if (score < BWlvl){
							newBitmap.setPixel(x, y, Color.WHITE);
						    }else{
							newBitmap.setPixel(x, y, Color.BLACK);
						}
					   }
					
					
				}
				
	
				
	
				int w = newBitmap.getWidth();
				int h = newBitmap.getHeight();  
				 
				ReduceNoiseFilter rn = new ReduceNoiseFilter();
				SharpenFilter sf = new SharpenFilter();
				DespeckleFilter ds = new DespeckleFilter();
				
				
				int[] src1 = AndroidUtils.bitmapToIntArray(newBitmap);
				int[] rednoise = rn.filter(src1, w, h);
				int [] sharp = sf.filter(rednoise, w, h);
				int [] despek = ds.filter(sharp, w, h);
				
				
				
				Bitmap newBitmap_rn = Bitmap.createBitmap(despek, w, h, Config.ARGB_8888);
				Bitmap scaled = Bitmap.createScaledBitmap(newBitmap_rn,340, 100, true);
				
				
                //
	//	Calendar c = Calendar.getInstance(); 
	//	int seconds = c.get(Calendar.SECOND);
	//	saveBoxImage(scaled,"bitmapScaled" + seconds +".jpg");
			
				
			String EgridText = readEGRID(scaled);
				newBitmap_rn.recycle();
				scaled.recycle();
			//	scaled.recycle();
				return EgridText;

	
}

public String saveBoxImage(Bitmap bm, String filename){
	
	// save b&W image to sd card 
	
	File myDir = new File(Environment.getExternalStorageDirectory().toString() + "/tessdata/");
	if (myDir.canWrite() == true){
		//do Nothing
	}else{sd.mkdirs();}
	
File EgridImg = new File(myDir,  filename);
		 FileOutputStream fos = null;
	     try {
	         fos = new FileOutputStream(EgridImg);
	         bm.compress(Bitmap.CompressFormat.JPEG,100, fos);

	         fos.flush();
	         fos.close();
	         response = "Image Saved";
	      
	     }catch (FileNotFoundException e) {

	     } catch (Exception e) {

	     }
	     
	     return response;

		  	 		};
		  	 		
		  	 	
public String readBotCode(Bitmap bm){
	
		  	 			File myDir = new File(Environment.getExternalStorageDirectory().toString() + "/tessdata/");
		  	 			Bitmap BM =JPEGtoRGB888(bm);
		  	 			TessBaseAPI baseApi = new TessBaseAPI();
		  	 			baseApi.init(myDir.toString(), "eng"); // myDir + "/tessdata/eng.traineddata" must be present
		  	 			
		  	 			baseApi.setVariable("tessedit_char_whitelist","ABCDEFGHIJ1234567890");
		  	 			baseApi.setPageSegMode(10); // sets recognise character only mode!
		  	 			baseApi.setImage(BM);
		  	 			
		  	 			String recognizedText = baseApi.getUTF8Text(); // Log or otherwise display this string...
		  	 			baseApi.end();
		  	 			
		  	 			return recognizedText;
		  	 			
		  	 			

} 					
 public String decodeEgrid(String result){
	 
     result = result.replace(" ", "");
     result = result.replace(",","");
   // result= result.replace("[","");
   // result = result.replace("]", "");
	 
	
			
			String group1 = result.substring(1, 3);
			String group2 = result.substring(5,7);
			String group3 = result.substring(9,11);
			
			String allCodes = group1+group2+group3;
			
			
	
		    String code = null;
		    Character c = null;
		    StringBuilder sb = new StringBuilder(allCodes);
		    boolean isOdd = false;
		    String CodeError = null;
		    
		    for (int x = 0 ; x < 6;x++)
		    {
		    	
		    	if ((x % 2) == 0) {
		    	    isOdd = false;
		    	}else {
		    	    isOdd = true;
		    	}
		    	
		    	
		    	c = sb.charAt(x);
		    	if (!isOdd){
		    		// Character should be a letter at even positions
		    		if (Character.isLetter(c)){
		    			// good no problem
		    			
		    		}else{
		    			// error in string
		    			CodeError = "error";
		    			
		    		}
		    	}
		    	if (isOdd){
		    		// Character should be a digit
		    		if (Character.isDigit(c)){
		    			// good no problem
		    			
		    		}else{
		    			// error in string
		    			if (c.equals("i")){
		    				sb.replace(x, x, "1");
		    		}
		    			
		    		}
		    	
		    	
		    	
		    }
		    
		    
		
		
 }
	code=sb.substring(0, 2).toString() + " " + sb.substring(2, 4).toString() + " " + sb.substring(4, 6).toString();	    
 return code;
 }
 
 public String readuserdata(Context ctx){
		String usrdata = null;
		 FileInputStream fis ;
		
	        try {
				fis = ctx.openFileInput("PcalData");
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
 
 
 public class LongGetCal extends AsyncTask<String, Void, String> {
	 
	 
	 private Context mContext;

	    public LongGetCal(Context context) {
	        mContext = context;
	    }
	    
	    
	 String Month = "";
	 CookieStore cookieStore = new BasicCookieStore();
	 HttpContext localContext = new BasicHttpContext();
	 
	 utilities u = new utilities();
	 DefaultHttpClient httpClient = AppSettings.getClient();
	 
	 
	  @Override
	  protected String doInBackground(String... params) {
	    // perform long running operation operation
		  String user = params[0].toString();
		  Month = params[1].toString();
		  String data1 = null;
		  
		  HttpPost httpPost1 = new HttpPost("https://pilot.fedex.com/vips-bin/vipscgi?webad?"+user+"?"+ Month.trim() + "?????Z?S"); // gets scheduled month details
		    HttpResponse response;
		    
			try {
				response = httpClient.execute(httpPost1);
				localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
				
				HttpEntity resEntity = response.getEntity();
				data1 = EntityUtils.toString(resEntity);
				resEntity.consumeContent();
		    
		    if (data1.length() > 30) {
		    	
		    	Intent i = new Intent();
			    i.setAction("CalMsg");
			    i.putExtra("MSG", "Cal Data Downloaded !");
			    mContext.sendBroadcast(i);
			    
			    
			    
			    
		    	getTripDataFromCalDetail(data1,Month,mContext);
		    	
	 }
			
			} catch (ClientProtocolException e) {
			} catch (IOException e) {
			}
			return data1;
	  
	  }	  
	
	 
	  
	  @Override
	  protected void onPostExecute(String result) 
	  {
		  
		  
		  Intent i = new Intent();
		  i.setAction("CalMsg");
		    i.putExtra("MSG", "Completed,"+ Month);
		    mContext.sendBroadcast(i);	
		    
		    
		   
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
 
 public  void getTripDataFromCalDetail(String calData, String Month, Context ctx){
		
		//parse and load data to triplist activity 

	AlpaDataBaseHelper mdbh = new AlpaDataBaseHelper(ctx);
	final SQLiteDatabase db = mdbh.getWritableDatabase(); 
	String tsql = "Delete from trips where bidMonth = '" + Month + "'";
	db.execSQL(tsql );
	db.close();


		
		
		 if (calData != null) { 
			 int startAt = calData.indexOf("<!-- Activity|");
			 int endAt = calData.indexOf("<!-- E N D   O F   M A I N   C O N T E N T -->");
	         String CalData = calData.substring(startAt, endAt);
			
	                	  
	         String[] events = 	CalData.split("\n");
	         
	                	
		 
		  //parse array here and load calendar StrDate Show EndDate Etim Blok  Pay
		 String pairing = null,showdate = null, showtime = null, enddate = null, endtime = null, Blok = null,  Pay = null, tripdate;
		 String actType = null, TripDetails = null,tripdata=null;
		 //String[] tripData = new String[events.length];
		 
		 
	//	 </FORM>
	//         	 
	//	 <!-- Activity|  258   |  CAR  |  TRP |  |  MEM  |  67  |  CAP  |  REG  | |  $ |  04JAN15  |  2343  | 11JAN15 | 2052 | 1607 | 4402 | | 13NOV14 1807| -->
	//	 <!-- Activity| 1029 | BLK | ASG | | MEM | 67 | CAP | REG | | | 05JAN15 | 0730 | 02FEB15 | 0729 | | 7425 | | 18DEC14 1808| -->
	//	 <!-- Activity| 1222 | JMP | J/S | | | | | | | | 14JAN15 | 0215 | 14JAN15 | 0640 | | | SAN-MEM Seat Reserved | 16JAN15 1901| -->
	//	 <!-- Activity| 56 | FLT | TRP | | MEM | 67 | CAP | REG | | $ | 14JAN15 | 0842 | 18JAN15 | 2052 | 1458 | 2851 | | 18DEC14 1808| -->
	//	 <!-- Activity|1369|JMP|J/S||||||||20JAN15|0217|20JAN15|0644|||SNA-MEM Seat Reserved|16JAN15 1901| -->
	//	 <!-- Activity|TRC|LST|TRC||MEM|67|CAP|REG|||20JAN15|1600|20JAN15|2230|||VKS/MV Sess# 6/RTSCMS|22DEC14 1709| -->
	//	 <!-- Activity|CMV1|TRN|||||||||20JAN15|1600|20JAN15|2230||||16JAN15 1901| -->
	//	 <!-- Activity|44|FLT|TRP||MEM|67|CAP|REG||$|25JAN15|2335|30JAN15|2052|1252|3117||18DEC14 1808| -->
	//	 <!------------------------------------------->
	//	 <!-- E N D   O F   M A I N   C O N T E N T -->
	//	 <!------------------------------------------->
		
		 
		 int lines=(events.length);
		 for (int x = 0;x < lines; x++) {
			
			 	if(events[x].toString().contains("<!-- Activity")){
			 		// parse relevant data from text
			 		tripdata = "";
			 		String[] act = events[x].split("\\|");
			 		
			 	//	int pairingStart = events[x].indexOf("</A>") -4;
			 	//	int pairingEnd = pairingStart + 4;
			 		///pairing = events[x].substring(pairingStart, pairingEnd).trim();
			 		pairing = act[1].toString();
			 		//int tripdateStart = events[x].lastIndexOf("?") + 1;
			 		actType = act[2].toString();
			 		
			 		
			 		//tripdate = events[x].substring(tripdateStart,tripdateStart + 7);
			 		tripdate = act[11].toString();
			 		
			 		//showdate = events[x].substring(pairingEnd + 37, pairingEnd + 44).trim();
			 		
			 		showdate = act[11].toString();
			 		//showtime = events[x].substring(pairingEnd + 45, pairingEnd + 49).trim();
			 		showtime = act[12].toString();
			 		
			 		// check showtime for being within 60 minutes of 0000Z
			 		int shwtime = Integer.parseInt(showtime);
			 		
			 		if (shwtime > 2300){
			 			//advance the day use calendar function to catch end of month issues
			 			String dt = showdate;  // Start date
			 			SimpleDateFormat sdf = new SimpleDateFormat("ddMMMyy");
			 			Calendar c = Calendar.getInstance();
			 			try {
							c.setTime(sdf.parse(dt));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			 			c.add(Calendar.DATE, 1);  // number of days to add
			 			  // dt is now the new date
			 			tripdate = sdf.format(c.getTime());
			 		}
			 		
			 		
			 		enddate = act[13].toString();
			 	//	enddate = events[x].substring(pairingEnd + 50, pairingEnd + 57).trim();
			 		endtime = act[14].toString();
			 		//endtime = events[x].substring(pairingEnd + 58, pairingEnd + 62).trim();
			 	    Blok = act[14].toString();
			 		//Blok = events[x].substring(pairingEnd + 63, pairingEnd + 68).trim();
			 		Pay = act[15].toString();
			 		//Pay = events[x].substring(pairingEnd + 69, pairingEnd + 74).trim();
			 	
			 	final SQLiteDatabase db1 = mdbh.getWritableDatabase(); 	
			 		
			 	if (actType.equals("CAR")) {
				 	 TripDetails = getTripDetails(pairing,tripdate,ctx);
				 	}
			 	if (actType.equals("FLT")) {
			 	 TripDetails = getTripDetails(pairing,tripdate,ctx);
			 	}
			 	if (actType.equals("BLK")) {
			 		TripDetails = getLineDetails(pairing,tripdate,ctx);	
			 	}
				 // parse out the data
				if (actType.equals("FLT")){
			 	int StartAt = TripDetails.indexOf("<!--  B E G I N   M A I N   C O N T E N T  -->");
				int EndAt = TripDetails.indexOf("<!-- Trip|Recap");
				tripdata = TripDetails.substring(StartAt, EndAt - 1);
				tripdata = tripdata.replace("'", " ");
				}
				if (actType.equals("CAR")){
				 	int StartAt = TripDetails.indexOf("<!--  B E G I N   M A I N   C O N T E N T  -->");
					int EndAt = TripDetails.indexOf("<!-- Trip|Recap|", StartAt);
					tripdata = TripDetails.substring(StartAt, EndAt - 1);
					tripdata = tripdata.replace("'", " ");
					}
				if (actType.equals("BLK")){
				 	int StartAt = TripDetails.indexOf("<TD NOWRAP ALIGN=RIGHT>        BLG");
					int EndAt = TripDetails.indexOf("</TABLE>",StartAt);
					tripdata = TripDetails.substring(StartAt, EndAt - 1);
					
					}
				
				
				 if (tripdata.contains("Trip(s) Not Found"))
				 {  
					 	tripdata = "<HTML>trip info not found </HTML>";
					 		

				 }
			     
				pairing = pairing + "  "+ actType;
				
				  try {
					  String sql = "Insert into trips Values (" + null  + "," + "'" + pairing + "','" + showdate + "','" + showtime + "','" + enddate + "','" +  endtime + "','" + Blok + "','"  + Pay   + "','" + Month + "','" + tripdate +  "','" + tripdata + "')";
			    	 //  db1.execSQL("Insert into trips Values (" + null  + "," + "'" + pairing + "','" + showdate + "','" + showtime + "','" + enddate + "','" +  endtime + "','" + Blok + "','"  + Pay   + "','" + Month + "','" + tripdate +  "','" + tripdata +"')");		  
					  db1.execSQL(sql);
			    	   	Intent i = new Intent();
					    i.setAction("CalMsg");
					    i.putExtra("MSG", "Trip "+ pairing + " added !");
					    ctx.sendBroadcast(i);
			    	   		  
			       				}catch (Exception e){
			       					Log.e("Exception", "IOException", e);
			    	   	  } 
			 	     
			      }
		       }

	        }
	  }
 
 
 public String getTripDetails(String tripnum,String dte, Context ctx){

	 

		// tripnum shoud be like 83Sep12
		
		String data2 = readuserdata(ctx);
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
		    localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
	        HttpEntity resEntity2 = response2.getEntity();
	        tripDetails =  EntityUtils.toString(resEntity2);
	        resEntity2.consumeContent();	
		   
		   }
		   
		   
			      
			      
			      
	  	} catch (IOException e) {
	           Log.e("Exception", "IOException", e);
	     } catch (Exception e) {
	           Log.e("Exception", "General Exception", e);      
		} //end try clause
 	
	  
	  
	  
		return tripDetails;
	  	
	}  
 
 public String getLineDetails(String lineNum,String dte, Context ctx){

	 

		// date shoud be like Sep12
	 String MMMyy = dte.substring(2);
		
		String data2 = readuserdata(ctx);
	  	final String[] logdata2 = data2.split(",");
	  	String base = logdata2[2].trim();
	   	String equip= logdata2[3].trim();
	  	
	  	String tripDetails = "";
	  	
	  	DefaultHttpClient httpClient = AppSettings.getClient();
	  	
	 
	  	try {
		String postString = "https://pilot.fedex.com/vips-bin/vipscgi?webbd?"+ lineNum +"?"+base.trim()+"?"+ equip + "?" + MMMyy + "?";
	    HttpPost httpPost3 = new HttpPost(postString); // gets scheduled month details
	    HttpResponse hResponse1 = httpClient.execute(httpPost3);
	    
	    HttpEntity resEntity = hResponse1.getEntity();
	    tripDetails = EntityUtils.toString(resEntity);
	   resEntity.consumeContent();
	  	
	   
	  
		   
		   
			      
			      
			      
	  	} catch (IOException e) {
	           Log.e("Exception", "IOException", e);
	     } catch (Exception e) {
	           Log.e("Exception", "General Exception", e);      
		} //end try clause
	
	  
	  
	  
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
			
			
			

			
			 DefaultHttpClient httpClient = AppSettings.getClient();
				
				 	
					try {
					    HttpPost httpPost1 = new HttpPost("https://pilot.fedex.com/" + LinkToDigits);
					    HttpResponse response = httpClient.execute(httpPost1);
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
				            
				                  String digit = readBotCode(bm);
				                  sb.append(digit.toString());
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
 
 
 public String calc3032in7(int lookBackDays,int lookBackHours, String srchDate, String srchTime, Context ctx )
 {
	
	

    final DecimalFormat timeFormat = new DecimalFormat("00");


//    String sBlockRemaining = null;

String sOutMinutes,sinMinutes;
int iTotalBlockMin,iTotalOutMin,iTotalInMin, iBlockRemaining;
int iStartSrchHours,iStartSrchMin;
int StartSrchMinutes,  iBlockRemainingMin;
int iOutHours,iOutMin,iPartialMinToUse,iinHours,iinMin,iTotalMinFor24HourPeriod = 0;


String[] sStartSchMinTemp;
sStartSchMinTemp = srchTime.split(":");
iStartSrchHours = Integer.parseInt(sStartSchMinTemp[0]);
iStartSrchMin = Integer.parseInt(sStartSchMinTemp[1]);
StartSrchMinutes = (iStartSrchHours * 60) + iStartSrchMin;

	         
String[] sStartDateTemp;
//String sAdjStartDy;
sStartDateTemp = srchDate.split("-");
int iStartYR = Integer.parseInt(sStartDateTemp[0]);
int iStartMO = Integer.parseInt(sStartDateTemp[1]);
int iStartDay = Integer.parseInt(sStartDateTemp[2]);


SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
GregorianCalendar cal = new GregorianCalendar();
cal.set(GregorianCalendar.MONTH,0);
cal.set(GregorianCalendar.DAY_OF_MONTH,0);
cal.set(GregorianCalendar.YEAR,0);
cal.set(GregorianCalendar.MONTH,iStartMO - 1);
cal.set(GregorianCalendar.DAY_OF_MONTH,iStartDay);
cal.set(GregorianCalendar.YEAR,iStartYR);

String sStartDate = sdf.format(cal.getTime());
String[] sStartDayofLookback = sStartDate.split("-");
int iStartDayofLookback = Integer.parseInt(sStartDayofLookback[2]);
cal.add(GregorianCalendar.DAY_OF_MONTH, - lookBackDays);
String sAdjSrchDate = sdf.format(cal.getTime());
sStartDateTemp = sAdjSrchDate.split("-");
int iAdjStartDay = Integer.parseInt(sStartDateTemp[2]);



UserDataHelper mdbh = new UserDataHelper(ctx);
final SQLiteDatabase db = mdbh.getWritableDatabase(); 

String qry = "Select inn,out,date,blk from fltlog where Date >= '" + sAdjSrchDate + "' and Date <= '" + sStartDate + "'  order by Date" ;
final Cursor cu = db.rawQuery(qry ,null);

if (cu.getCount() > 0) {
cu.moveToFirst();
int blkHours=0,blkMin=0;
iTotalOutMin=0;
iTotalInMin=0;
iPartialMinToUse = 0;
iTotalMinFor24HourPeriod=0;
String sblkHours;
String[] sblkTempHr;
String[] sOutMinutesTemp, sinMinutesTemp,sCurrentDay;
iTotalMinFor24HourPeriod = 0;

for (int i = 1; i <= cu.getCount();i++) {
	 
	 //turn blk time into minutes
	sblkHours=cu.getString(cu.getColumnIndex("blk"));
	sblkTempHr = sblkHours.split(":");
	blkHours = Integer.parseInt(sblkTempHr[0]);
	blkMin = Integer.parseInt(sblkTempHr[1]);
	
	
	String CurrentDate = cu.getString(cu.getColumnIndex("Date"));
	 sCurrentDay = CurrentDate.split("-");
	int iCurDay = Integer.parseInt(sCurrentDay[2]);
	
	//get the in time minutes value
	sinMinutes=cu.getString(cu.getColumnIndex("inn"));
	sinMinutesTemp = sinMinutes.split(":");
	iinHours = Integer.parseInt(sinMinutesTemp[0]);
	iinMin = Integer.parseInt(sinMinutesTemp[1]);
	
	
	
	//get the out time minutes value
	sOutMinutes=cu.getString(cu.getColumnIndex("out"));
	sOutMinutesTemp = sOutMinutes.split(":");
	iOutHours = Integer.parseInt(sOutMinutesTemp[0]);
	iOutMin = Integer.parseInt(sOutMinutesTemp[1]);
	iTotalInMin = 0;
	iTotalOutMin = 0;
	iTotalBlockMin = 0;
	iTotalInMin = (iinHours * 60) + (iinMin);
	iTotalOutMin = (iOutHours * 60) + (iOutMin);
	iTotalBlockMin = (blkHours * 60) + (blkMin);
	
	
	
	// Examine minutes for several criteria//
	// records are sorted in oldest to newest order so need only to examne the first and last records for block time that start before or after the 30 hour windo
/// need to check first record only for starting before or after start time. May not have any records for first day

	
	if (lookBackDays == 7 ) {
		
		
	
	
	
	
	
	if (iAdjStartDay == iCurDay) {  // portion the minutes in the window after the start point 7 days ealier
		
		//This is on the 1st day of the 7 day lookback
		if ((  iTotalOutMin >= StartSrchMinutes) && (iTotalInMin > StartSrchMinutes) ) {
		iTotalMinFor24HourPeriod = iTotalMinFor24HourPeriod + iTotalBlockMin;}
		
		if ((iTotalOutMin < StartSrchMinutes) && (iTotalInMin >= StartSrchMinutes) && (iAdjStartDay == iCurDay)){ 
		iPartialMinToUse = iTotalInMin -  StartSrchMinutes;
		iTotalMinFor24HourPeriod = iTotalMinFor24HourPeriod + iPartialMinToUse;}
	}

	

	    
	    
	if (iCurDay != iAdjStartDay ) {  // date is less then the end date of the search.. Use it all
		
		iTotalMinFor24HourPeriod = iTotalMinFor24HourPeriod + iTotalBlockMin; // use it all
		
		
		
	}
		
	if ( iCurDay == iStartDayofLookback) {  // this occurs on the last day use all or just a portion ?
		
	
		iTotalMinFor24HourPeriod = iTotalMinFor24HourPeriod + iTotalBlockMin; // use it all since the blockin
		// is the start point. We can't go over that !!!!
		
		
	}
	} else {
		
		
		
		//1. this starts and ends on the first day of the lookback
			

		 if ((  iTotalOutMin >= StartSrchMinutes) && (iTotalInMin > StartSrchMinutes) && (iAdjStartDay == iCurDay)) {
				iTotalMinFor24HourPeriod = iTotalMinFor24HourPeriod + iTotalBlockMin;}
			    
			// Case 2 Trip started before the calc point and end after the start point but all before 0Z
				if ((iTotalOutMin < StartSrchMinutes) && (iTotalInMin >= StartSrchMinutes) && (iAdjStartDay == iCurDay)){ 
				iPartialMinToUse = iTotalInMin -  StartSrchMinutes;
	 			iTotalMinFor24HourPeriod = iTotalMinFor24HourPeriod + iPartialMinToUse;}
			
				// Case 3. Trip start on previous day ends on the next day	
				if ((iTotalOutMin >= StartSrchMinutes)  && (iTotalInMin <= StartSrchMinutes) && (iAdjStartDay == iCurDay)){ // just count the block before the blockout 24 earlier
					iTotalMinFor24HourPeriod = iTotalMinFor24HourPeriod + iTotalBlockMin;}
				
				//Case 4. Trip occurs all during the current day of the 24hr lookback	
				if ((iTotalOutMin < StartSrchMinutes)  && (iTotalInMin <= StartSrchMinutes) && (iTotalInMin > iTotalOutMin) && (iAdjStartDay != iCurDay)){
					iTotalMinFor24HourPeriod = iTotalMinFor24HourPeriod + iTotalBlockMin;}
				
				//flt occurs on correct day but before the search period starts		
					
				if ((iTotalOutMin < StartSrchMinutes)  && (iTotalInMin < StartSrchMinutes)  && (iAdjStartDay == iCurDay)){
					iTotalMinFor24HourPeriod = iTotalMinFor24HourPeriod + 0;}
			    // Flt occurs after the end of the look back period 
				
				
			    // Flt occurs after the end of the look back period 
				if ((iTotalOutMin > StartSrchMinutes)  && (iTotalInMin > StartSrchMinutes)  && (iAdjStartDay != iCurDay)){
					iTotalMinFor24HourPeriod = iTotalMinFor24HourPeriod + 0;}
				 // Flt start before start of the blockin est but ends after ( OM<ss IM>ss AD!=CD 
				if ((iTotalOutMin < StartSrchMinutes)  && (iTotalInMin > StartSrchMinutes)  && (iAdjStartDay != iCurDay)){
					iPartialMinToUse =  StartSrchMinutes - iTotalOutMin;
		 			iTotalMinFor24HourPeriod = iTotalMinFor24HourPeriod + iPartialMinToUse;}	
		
		
		
		
		
	}
		cu.moveToNext();// advance the recordset
  // end for loop
}

}

iBlockRemaining = (lookBackHours * 60) - iTotalMinFor24HourPeriod;
int iBlockRemainingHours = (iBlockRemaining/60);
if (iBlockRemainingHours == 0){

iBlockRemainingMin = iBlockRemaining;
}else {
iBlockRemainingMin = (iBlockRemaining - (iBlockRemainingHours * 60));
}
String TimeRem = timeFormat.format(iBlockRemainingHours) + ":" + timeFormat.format(iBlockRemainingMin) ;

	 
	 
	 
	 return TimeRem;
 }

}// end of class
