package com.yctc.alpaware;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.AbstractWheelTextAdapter;

import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;


import com.actionbarsherlock.app.ActionBar;


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
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore.Files;
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

import com.yctc.alpaware.utilities.LongGetCal;






public class PocketCal20 extends SherlockActivity {
    /** Called when the activity is first created. */
	Context ctx1 = this;
		public String DBpath = "/data/data/com.yctc.alpaware/databases/";
	//public String DBpath = ctx1.getFilesDir().toString();
			
			
	
		 ActionBar actionBar;
		 Context ctx = PocketCal20.this;
		 CookieStore cookieStore = new BasicCookieStore();
		 HttpContext localContext = new BasicHttpContext();
		 HttpPost httpPost = new HttpPost();
		 utilities u = new utilities();
		 DefaultHttpClient httpClient = AppSettings.getClient();
		 Cookie sessionInfo;
		 ProgressDialog pd;
		

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        actionBar = getSupportActionBar();
         
        try {
        	installCBAFiles();
        	copyMiscFiles();
        //	db.close();
        	
		} catch (IOException e) {
		    e.printStackTrace();
		}
        
       
          

}

    public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu){
		MenuInflater inflater=getSupportMenuInflater();
		inflater.inflate(R.menu.mainmenu1, menu);
		
		
		
            			
		
		return true;
		}	
    
    public boolean onOptionsItemSelected (com.actionbarsherlock.view.MenuItem item ){
		
		switch (item.getItemId()){
		
		case R.id.officerslist :
			
			 Intent myIntent = new Intent(ctx, meclist.class);
	        startActivityForResult(myIntent, 0);
		return true;
		
		case R.id.repslist:
			 Intent myIntent1 = new Intent(ctx, repslist.class);
	        startActivityForResult(myIntent1, 0);
		return true;
		
		case R.id.fedexevp :
			 Intent myIntent15 = new Intent(ctx, evp1.class);
	        startActivityForResult(myIntent15, 0);
		return true;
		
		case R.id.mecstaff :
			 Intent myIntent17 = new Intent(ctx, staff.class);
	        startActivityForResult(myIntent17, 0);
		return true;
		
		case R.id.accidentinfo :
			 Intent myIntent18 = new Intent(ctx, accidentinfo.class);
	        startActivityForResult(myIntent18, 0);
		return true;
		case R.id.memcontacts :
			 Intent myIntent19 = new Intent(ctx, contact.class);
	        startActivityForResult(myIntent19, 0);
		return true;
		case R.id.nationalCtc :
			 Intent myIntent24 = new Intent(ctx, nationalctc.class);
	        startActivityForResult(myIntent24, 0);
		return true;
		case R.id.meccommittee :
			 Intent myIntent25 = new Intent(ctx, committee.class);
	        startActivityForResult(myIntent25, 0);
		return true;
		
		//// end of MEC menu options /////
		
		/// start Online menu choices /////
		
		
			
			
					
			
		case R.id.Onlinemnu :
			Intent myIntent21 = new Intent(getBaseContext(),  onlinemenu1.class);
			startActivityForResult(myIntent21, 0);
			return true;

		
			
	
		///////////	
		case R.id.KCMmnu:
			 Intent myIntent2 = new Intent(ctx, KCM.class);
			 startActivityForResult(myIntent2, 0);
		return true;
		
		
		
		///////
		case R.id.ifAlpaContacts:
			 Intent myIntent3 = new Intent(ctx, ifalpa.class);
			 startActivityForResult(myIntent3, 0);
		return true;
		case R.id.usEmbassy:
			 Intent myIntent4 = new Intent(ctx, usembassy.class);
			 startActivityForResult(myIntent4, 0);
		return true;
		case R.id.emerRespData:
			 Intent myIntent5 = new Intent(ctx, ieresponse.class);
			 startActivityForResult(myIntent5, 0);
		return true;
		
		/////////////
		
		case R.id.cbaTOC :
			String s = "toc.pdf";
			ShowFile(s);
			return true;
		case R.id.cbaSection1 :
			String s1 = "Section1.pdf";
			ShowFile(s1);
			return true;
		case R.id.cbaSection2 :
			String s2 = "Section2.pdf";
			ShowFile(s2);
			return true;
		case R.id.cbaSection3 :
			String s3 = "Section3.pdf";
			ShowFile(s3);
			return true;
		case R.id.cbaSection4 :
			String s4 = "Section4.pdf";
			ShowFile(s4);
			return true;
		case R.id.cbaSection5 :
			String s5 = "Section5.pdf";
			ShowFile(s5);
			return true;
			
		case R.id.cbaSection6 :
			String s6 = "Section6.pdf";
			ShowFile(s6);
			return true;
			
		case R.id.cbaSection7 :
			String s7 = "Section7.pdf";
			ShowFile(s7);
			return true;
			
		case R.id.cbaSection8 :
			String s8 = "Section8.pdf";
			ShowFile(s8);
			return true;
			
		case R.id.cbaSection9 :
			String s9 = "Section9.pdf";
			ShowFile(s9);
			return true;
			
		case R.id.cbaSection10 :
			String s10 = "Section10.pdf";
			ShowFile(s10);
			return true;
			
		case R.id.cbaSection11 :
			String s11 = "Section11.pdf";
			ShowFile(s11);
			return true;
			
		case R.id.cbaSection12 :
			String s12 = "Section12.pdf";
			ShowFile(s12);
			return true;
			
		case R.id.cbaSection13 :
			String s13 = "Section13.pdf";
			ShowFile(s13);
			return true;
			
		case R.id.cbaSection14 :
			String s14 = "Section14.pdf";
			ShowFile(s14);
			return true;
			
		case R.id.cbaSection15 :
			String s15 = "Section15.pdf";
			ShowFile(s15);
			return true;
			
		case R.id.cbaSection16 :
			String s16 = "Section16.pdf";
			ShowFile(s16);
			return true;
			
		case R.id.cbaSection17 :
			String s17 = "Section17.pdf";
			ShowFile(s17);
			return true;
			
		case R.id.cbaSection18 :
			String s18 = "Section18.pdf";
			ShowFile(s18);
			return true;
			
		case R.id.cbaSection19 :
			String s19 = "Section19.pdf";
			ShowFile(s19);
			return true;
			
		case R.id.cbaSection20 :
			String s20 = "Section20.pdf";
			ShowFile(s20);
			return true;
			
		case R.id.cbaSection21 :
			String s21 = "Section21.pdf";
			ShowFile(s21);
			return true;
			
		case R.id.cbaSection22 :
			String s22 = "Section22.pdf";
			ShowFile(s22);
			return true;
			
		case R.id.cbaSection23 :
			String s23 = "Section23.pdf";
			ShowFile(s23);
			return true;
			
		case R.id.cbaSection24 :
			String s24 = "Section24.pdf";
			ShowFile(s24);
			return true;
			
		case R.id.cbaSection25 :
			String s25 = "Section25.pdf";
			ShowFile(s25);
			return true;
			
		case R.id.cbaSection26 :
			String s26 = "Section26.pdf";
			ShowFile(s26);
			return true;
			
		case R.id.cbaSection27 :
			String s27 = "Section27.pdf";
			ShowFile(s27);
			return true;
			
		case R.id.cbaSection28 :
			String s28 = "Section28.pdf";
			ShowFile(s28);
			return true;
			
		case R.id.cbaSection29 :
			String s29 = "Section29.pdf";
			ShowFile(s29);
			return true;
			
		case R.id.cbaSection30 :
			String s30 = "Section30.pdf";
			ShowFile(s30);
			return true;
			
		case R.id.cbaSection31 :
			String s31 = "Section31.pdf";
			ShowFile(s31);
			return true;
			
		case R.id.LOAAge65 :
			String loa1 = "LOA1.pdf";
			ShowFile(loa1);
			return true;
			
		case R.id.LOAAdminGrv :
			String loa2 = "LOA2.pdf";
			ShowFile(loa2);
			return true;
			
		case R.id.LOAASAP :
			String loa3 = "LOA3.pdf";
			ShowFile(loa3);
			return true;
			
		case R.id.LOAFDA :
			String loa4 = "LOA4.pdf";
			ShowFile(loa4);
			return true;
			
		case R.id.LOAHumanPerf :
			String loa5 = "LOA5.pdf";
			ShowFile(loa5);
			return true;
		case R.id.LOAIRAQ :
			String loa6 = "LOA6.pdf";
			ShowFile(loa6);
			return true;
		case R.id.LOALetters :
			String loa7 = "LOA7.pdf";
			ShowFile(loa7);
			return true;
		case R.id.LOAFOQA :
			String loa8 = "LOA8.pdf";
			ShowFile(loa8);
			return true;
		///////////////////////////
			
		case R.id.findByAcNum :
			 Intent myIntent60 = new Intent(ctx, acequiplist.class);
			 startActivityForResult(myIntent60, 0);
			return true;	
			
		case R.id.findByFleet:
			 Intent myIntent61 = new Intent(ctx, acequiplisybyfleet.class);
			 startActivityForResult(myIntent61, 0);
			return true;
			
		case R.id.addNew:
			 Intent myIntent62 = new Intent(ctx, acequipnew.class);
			 startActivityForResult(myIntent62, 0);
			return true;
			
	///////////////////////////////////
			
		case R.id.enterFlt:
			 Intent myIntent70 = new Intent(ctx, fltentry.class);
			 startActivityForResult(myIntent70, 0);
		return true;
		
		case R.id.updateFlight:
			 Intent myIntent71 = new Intent(ctx, fltlistout.class);
			 startActivityForResult(myIntent71, 0);
		return true;
		
		case R.id.windComp:
			
			Intent myIntent700 = new Intent(ctx, windcalc.class);
			 startActivityForResult(myIntent700, 0);
			 
			 return true;
			 
		case R.id.gouge:
			String gouge7 = "Card.pdf";
			ShowFile(gouge7);
			return true;
		
		case R.id.rpts:
			
		//	loadspinners();
			
			
			 Intent myIntent72 = new Intent(ctx, fltreports2.class);
			 startActivityForResult(myIntent72, 0);
		
		return true;
		case R.id.fileMaintFlt:
			 Intent myIntent73 = new Intent(ctx, fltlogmaint.class);
			 startActivityForResult(myIntent73, 0);
		return true;
		
	/////////////////////////////////////////////////////
		
		////expenses/////
		
		
		case R.id.enterExp :
			Intent Intent2 = new Intent(getBaseContext(), expentry.class);	
			startActivityForResult(Intent2, 0);
		return true;
		case R.id.listExpenses :
			Intent Intent3 = new Intent(getBaseContext(), explistout.class);	
			startActivityForResult(Intent3, 0);
		return true;
		case R.id.expReports :
			Intent Intent = new Intent(getBaseContext(), expreports.class);
              startActivityForResult(Intent, 0);
        return true;
		case R.id.expMaint :
			Intent Intent4 = new Intent(getBaseContext(), explogmaint.class);
			startActivityForResult(Intent4, 0);
        return true;
        
        /////////////
		
		case R.id.FRQmnu:
			 Intent myIntent8 = new Intent(ctx, freqs.class);
			 startActivityForResult(myIntent8, 0);
		return true;
		case R.id.FDXmnu:
			 String ss1 = "FedEx.pdf";
    			ShowFile(ss1);
		return true;
		case R.id.RHGmnu:
			 Intent myIntent9 = new Intent(ctx, ramphotelmain.class);
			 startActivityForResult(myIntent9, 0);
		return true;
		case R.id.JMPmnu:
			 Intent myIntent10 = new Intent(ctx,jumpseatmain.class);
			 startActivityForResult(myIntent10, 0);
		return true;
		case R.id.PDFmnu:
			 Intent myIntent11 = new Intent(ctx,pdf.class);
			 startActivityForResult(myIntent11, 0);
		return true;
		case R.id.FILmnu:
			 Intent myIntent12 = new Intent(ctx,filemaintmain.class);
			 startActivityForResult(myIntent12, 0);
		return true;
		case R.id.HLPmnu:
			String hlp = "Help.pdf";
        	ShowFile(hlp);
		return true;
		case R.id.ABTmnu:
			 Intent myIntent13 = new Intent(ctx,about.class);
			 startActivityForResult(myIntent13, 0);
		return true;
		case R.id.home :
			Intent i = new Intent();
			startActivity(new Intent(i.ACTION_MAIN).addCategory(i.CATEGORY_HOME));
		
		
		}
		return false;
    }
			
	public void installCBAFiles(){    
		// install files to system if they don't already exist
	File sd = new File(Environment.getExternalStorageDirectory().toString() + "/cba/");
	
	
	//File cbadir = new File(CBApath);
	if (sd.exists() == false) {
		
	sd.mkdirs(); }
	
	
	 for (int i = 1; i <= 31;i++) {
		 String filename = "Section" + i + ".pdf";
	 File cbafile = new File(sd,filename);
	 
	 if (cbafile.exists()){
		 
	 }else{
	
	try {
		InputStream in = this.getResources().getAssets().open(filename);
		OutputStream out = new FileOutputStream(sd + "/" + filename); // Transfer bytes from in to out
	    byte[] buf = new byte[1024]; 
	    int len;
	    while ((len = in.read(buf)) != -1)
		{ out.write(buf, 0, len); 
		}  in.close(); 
	    out.flush();
     	out.close();
     	
     	if (i==31){
     	utilities util = new utilities();
     	Context ctx = getApplicationContext();
     	util.showaction("CBA files Installed to Sd Card",ctx);
     	}
	} catch (IOException e) {
		
		e.printStackTrace();
	utilities util = new utilities();
 	Context ctx = getApplicationContext();
 	
 	util.showaction(" CBA file " + filename + " Install failed",ctx);}
	} }
	 
	 for (int i = 1; i <= 7;i++) {
		 String filename = "LOA" + i + ".pdf";
	 File cbafile = new File(sd,filename);
	 
	 if (cbafile.exists()){
		 
	 }else{
	
	try {
		InputStream in = this.getResources().getAssets().open(filename);
		OutputStream out = new FileOutputStream(sd + "/" + filename); // Transfer bytes from in to out
	    byte[] buf = new byte[1024]; 
	    int len;
	    while ((len = in.read(buf)) != -1)
		{ out.write(buf, 0, len); 
		}  in.close(); 
	    out.flush();
     	out.close();
     	
     	if (i==7){
     	utilities util = new utilities();
     	Context ctx = getApplicationContext();
     	util.showaction("LOA files Installed to Sd Card",ctx);
     	}
	} catch (IOException e) {
		
		e.printStackTrace();
	utilities util = new utilities();
 	Context ctx = getApplicationContext();
 	
 	util.showaction(" LOA Install failed",ctx);}
	} }
	 
	 }
	
	
	 void copyMiscFiles() throws IOException { 
		 File sd = new File(Environment.getExternalStorageDirectory().toString() + "/cba/");
     	
     	if (sd.canWrite() == false) {
     	sd.mkdir(); }
     	
     	
     	
    	
    	
    	 
    	 File cbafile = new File(sd,"/TOC.pdf");
    	 
    	 if (cbafile.exists() == true){
    		 // do nothing
    	 }else{
     	 InputStream in = getResources().getAssets().open("TOC.pdf"); 
      	OutputStream out = new FileOutputStream(sd + "/TOC.pdf"); // Transfer bytes from in to out
      	byte[] buf = new byte[1024]; 
      	int len; 
      	while ((len = in.read(buf)) > 0)
      	{ out.write(buf, 0, len); 
      	}  in.close(); 
	    out.flush();
     	out.close(); 
    	 }     	
     
    	
 	
    	
 	
    	 File cbafile3 = new File(sd,"/cbaIndex.pdf");
    	 
    	 if (cbafile3.exists()){
    		 // do nothing
    	 }else{
     	 InputStream in = getResources().getAssets().open("cbaIndex.pdf"); 
      	OutputStream out = new FileOutputStream(sd + "/cbaIndex.pdf"); // Transfer bytes from in to out
      	byte[] buf = new byte[1024]; 
      	int len; 
      	while ((len = in.read(buf)) > 0)
      	{ out.write(buf, 0, len); 
      	}  in.close(); 
	    out.flush();
     	out.close();
     	
    	 }
     	
     	File pdf1 = new File(sd,"/FedEx.pdf");
   	 
   	 if (pdf1.exists()){
   		 // do nothing
   	 }else{
    	 InputStream in = getResources().getAssets().open("FedEx.pdf"); 
     	OutputStream out = new FileOutputStream(sd + "/FedEx.pdf"); // Transfer bytes from in to out
     	byte[] buf = new byte[1024]; 
     	int len; 
     	while ((len = in.read(buf)) > 0)
     	{ out.write(buf, 0, len); 
     	}  in.close(); 
	    out.flush();
    	out.close();
    	
    	
   	 }
    	File pdf2 = new File(sd,"/FTDTv2.pdf");
      	 
      	 if (pdf2.exists()){
      		 // do nothing
      	 }else{
       	 InputStream in = getResources().getAssets().open("FTDTv2.pdf"); 
        	OutputStream out = new FileOutputStream(sd + "/FTDTv2.pdf"); // Transfer bytes from in to out
        	byte[] buf = new byte[1024]; 
        	int len; 
        	while ((len = in.read(buf)) > 0)
        	{ out.write(buf, 0, len); 
        	}  in.close(); 
   	    out.flush();
       	out.close();
       	
      	}
     	File pdf3 = new File(sd,"/Jumpseat_Guide.pdf");
       	 
       	 if (pdf3.exists()){
       		 // do nothing
       	 }else{
        	 InputStream in = getResources().getAssets().open("Jumpseat_Guide.pdf"); 
         	OutputStream out = new FileOutputStream(sd + "/Jumpseat_Guide.pdf"); // Transfer bytes from in to out
         	byte[] buf = new byte[1024]; 
         	int len; 
         	while ((len = in.read(buf)) > 0)
         	{ out.write(buf, 0, len); 
         	}  in.close(); 
    	    out.flush();
        	out.close();
        	
       	 }
        	File pdf4 = new File(sd,"/Contract_Info.pdf");
          	 
          	 if (pdf4.exists()){
          		 // do nothing
          	 }else{
           	 InputStream in = getResources().getAssets().open("Contract_Info.pdf"); 
            	OutputStream out = new FileOutputStream(sd + "/Contract_Info.pdf"); // Transfer bytes from in to out
            	byte[] buf = new byte[1024]; 
            	int len; 
            	while ((len = in.read(buf)) > 0)
            	{ out.write(buf, 0, len); 
            	}  in.close(); 
       	    out.flush();
           	out.close();
           	
          	}
         	File pdf5 = new File(sd,"/Travel_Info.pdf");
           	 
           	 if (pdf5.exists()){
           		 // do nothing
           	 }else{
            	 InputStream in = getResources().getAssets().open("Travel_Info.pdf"); 
             	OutputStream out = new FileOutputStream(sd + "/Travel_Info.pdf"); // Transfer bytes from in to out
             	byte[] buf = new byte[1024]; 
             	int len; 
             	while ((len = in.read(buf)) > 0)
             	{ out.write(buf, 0, len); 
             	}  in.close(); 
        	    out.flush();
            	out.close();
           	 }
           	File pdf6 = new File(sd,"/Help.pdf");
          	 
          	 if (pdf6.exists()){
          		 // do nothing
          	 }else{
           	 InputStream in = getResources().getAssets().open("Help.pdf"); 
            	OutputStream out = new FileOutputStream(sd + "/Help.pdf"); // Transfer bytes from in to out
            	byte[] buf = new byte[1024]; 
            	int len; 
            	while ((len = in.read(buf)) > 0)
            	{ out.write(buf, 0, len); 
            	}  in.close(); 
       	    out.flush();
           	out.close();
          	 }
          	 
           	File pdf7 = new File(sd,"/Card.pdf");
            if (pdf7.exists()){
         		 // do nothing
         	 }else{
          	 InputStream in = getResources().getAssets().open("Card.pdf"); 
           	OutputStream out = new FileOutputStream(sd + "/Card.pdf"); // Transfer bytes from in to out
           	byte[] buf = new byte[1024]; 
           	int len; 
           	while ((len = in.read(buf)) > 0)
           	{ out.write(buf, 0, len); 
           	}  in.close(); 
      	    out.flush();
          	out.close();
   
        	
       	
       	Context ctx = getApplicationContext();
       	
     	u.showaction("Misc files Installed to Sd Card",ctx);}
     	
    	 }
	
	 void ShowFile(String s) {
	    	
	    	
	    	Intent intent = new Intent();
	    	 intent.setAction(android.content.Intent.ACTION_VIEW);
	    	 File file = new File("mnt/sdcard/cba/" + s);
	    	 intent.setDataAndType(Uri.fromFile(file), "application/pdf");  
	    	 startActivity(intent);
	    }
	 

	
}	 
	


	

