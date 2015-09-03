package com.yctc.alpaware;



import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;




import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.view.View;
import android.widget.Button;

public class filemaintmain extends SherlockActivity{


	 public static final int Main_Menu =0;
	String Msg, response;
	String filename;
	 private ProgressDialog pDialog;
	  public static final int progress_bar_type = 0; 
	  Context ctx = this;
	  
	 
	
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filemaintmain);
        
      

     // instantiate it within the onCreate method
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Downloading file. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setMax(100);
        pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pDialog.setCancelable(true);
     // execute this when the downloader must be fired
   

	
	
        Button installMdb = (Button) findViewById(R.id.installMainDB);
    	installMdb.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        		
        		 Builder b =  new AlertDialog.Builder(filemaintmain.this);
  	             
 	              b.setTitle("Download Main File and Overwrite?");
 	     		
 	      b.setPositiveButton("OK", new DialogInterface.OnClickListener(){
 	     	  	 	public void onClick(DialogInterface d, int which) {
 	     	  	 		d.dismiss();
 	     	  	 	fileDownLoadMain downloadFile = new fileDownLoadMain();
 	     	  	     downloadFile.execute("http://199.68.191.212/");
 	            	
 	             }});
 	       	b.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface d, int which) {
				d.dismiss();
				Intent myIntent = new Intent(getBaseContext(), filemaintmain.class);
		 		startActivityForResult(myIntent, 0);
				 }});

	   b.create().show();
	   
 	            
 	            }});


	            	
	
    	
	Button installUdb = (Button) findViewById(R.id.installuserDB);
	installUdb.setOnClickListener(new View.OnClickListener() {
    	public void onClick(View view) {
    		
    	//	File DataDir = new File(ctx.getDatabasePath("a").toString() );
    		File DataDir = new File(getApplicationInfo().dataDir.toString());
    		
    		
    		 File userfile = new File(DataDir,"/UserData.db");
    		 
    		 if (userfile.exists()){
    			 
    			 Builder b =  new AlertDialog.Builder(filemaintmain.this);
  	             
	              b.setTitle("Download New User Database and Overwrite Current?");
	     		
	      b.setPositiveButton("OK", new DialogInterface.OnClickListener(){
	     	  	 	public void onClick(DialogInterface d, int which) {
	     	  	 		d.dismiss();
	            	//   pd = ProgressDialog.show(filemaintmain.this, "Downloading User Database to Device","please wait", true, false);
	            	 String url = "http://199.68.191.212/";
	            //	 utilities util = new utilities();
	         	//	Context ctx= getApplicationContext();
	            //	 try {
				//		String response = util.saveDB("UserData.db",ctx);
				//		util.showaction(response,ctx);
				//	} catch (IOException e) {}
	            	new fileDownLoadUser().execute(url);
	             }});
	       	b.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface d, int which) {
				d.dismiss();
				Intent myIntent = new Intent(getBaseContext(), filemaintmain.class);
		 		startActivityForResult(myIntent, 0);
				 }});

	   b.create().show();
    		 
    		 
    		 
    		 
    		 
    		 }else{
    		
    		
    	
       	 String url1 = "http://199.68.191.212/";
       	 new fileDownLoadUser().execute(url1);
    		 }
		
       	}});      
	
	Button Savedb = (Button) findViewById(R.id.SaveuserDB);
	Savedb.setOnClickListener(new View.OnClickListener() {
    	public void onClick(View view) {
    		utilities util = new utilities();
    		Context ctx= getApplicationContext();
    		try{
    		String response = util.saveDB("UserData.db",ctx);
    		//String response1 = util.saveDB("PocketCal.db", ctx);
    		
    		util.showaction(response,ctx);
    		//util.showaction(response1,ctx);
    		
    		}catch (Exception e) {}
       	}});      
	Button restdb = (Button) findViewById(R.id.restoreUserDB);
	restdb.setOnClickListener(new View.OnClickListener() {
    	public void onClick(View view) {
    		
    		utilities util = new utilities();
    		Context ctx= getApplicationContext();
    		try{
        		String response = util.restoreDB("UserData.db",ctx);
        		util.showaction(response,ctx);
        		//String response1 = util.restoreDB("PocketCal.db",ctx);
        		//util.showaction(response1,ctx);
        		
        		}catch (Exception e) {}
           	}});    
	
	Button emaildb = (Button) findViewById(R.id.emailUserDB);
	emaildb.setOnClickListener(new View.OnClickListener() {
    	public void onClick(View view) {
    		
        		 emailDB("UserData.db","Email Copy of Pocket Cal User Database");
        		
           	}});  
	
	
	Button loadEG = (Button) findViewById(R.id.loadEGRID);
    loadEG.setOnClickListener(new View.OnClickListener() {
     	public void onClick(View view) {
     		Intent myIntent = new Intent(view.getContext(), loadEGRID.class);
           startActivityForResult(myIntent, 0);
     	}});
    
    Button loaduser = (Button) findViewById(R.id.setUserNamePwd);
    loaduser.setOnClickListener(new View.OnClickListener() {
     	public void onClick(View view) {
     		Intent myIntent = new Intent(view.getContext(), setUserPwd.class);
           startActivityForResult(myIntent, 0);
     	}});
    
    Button chkver = (Button) findViewById(R.id.btnVer);
    chkver.setOnClickListener(new View.OnClickListener() {
     	public void onClick(View view) {
     		Intent myIntent = new Intent(view.getContext(), checkdbver.class);
           startActivityForResult(myIntent, 0);
     	}});
    
    Button btnOCR = (Button) findViewById(R.id.btnocr);
    btnOCR.setOnClickListener(new View.OnClickListener() {
     	public void onClick(View view) {
     		 String url1 = "http://199.68.191.212/";
           	 new fileDownLoadDictionary().execute(url1);
     	}});
  
    Button btnClean = (Button) findViewById(R.id.cleanfiles);
    btnClean.setOnClickListener(new View.OnClickListener() {
     	public void onClick(View view) {
     		 //delete all files in the downloads dir
     		
     		 Builder b =  new AlertDialog.Builder(filemaintmain.this);
	             
             b.setTitle("Delete All files in Pdf Directory?");
    		
     b.setPositiveButton("OK", new DialogInterface.OnClickListener(){
    	  	 	public void onClick(DialogInterface d, int which) {
    	  	 		d.dismiss();
    	  	 		final File destinationDir = new File (Environment.getExternalStorageDirectory(), "PFCFiles");
    	     		for(File file: destinationDir.listFiles()) file.delete();
            }});
      	b.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface d, int which) {
			d.dismiss();
			
			 }});

  b.create().show();
     		
     		
     		
     		
     		
     		
     		
     	}}); 
  

	
}
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu){
		
		
		
		//	menu.add("Get_Egrid")
			
			//	.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
                			
			MenuInflater inflater=getSupportMenuInflater();
			inflater.inflate(R.menu.filemaintmenubar, menu);
			return super.onCreateOptionsMenu(menu);
			}

			public boolean onOptionsItemSelected (com.actionbarsherlock.view.MenuItem item ){
				switch (item.getItemId()){
				
				case R.id.home :
					
					Intent myIntent1 = new Intent(getBaseContext(), PocketCal20.class);	
					startActivityForResult(myIntent1, 0);
				return true;	
					
			
			
		}
				return false;
			}	


			 private  void emailDB(String dbase, String Subj) { 
				 File DataDir = new File(getApplicationInfo().dataDir.toString());
		  	    	String email = (String) "";
		  	    	Intent sendIntent = new Intent(Intent.ACTION_SEND);
		  	    	sendIntent.putExtra(Intent.EXTRA_SUBJECT,Subj);
		  	    	String[] recipients = new String[]{email};
		  	    	sendIntent.putExtra(android.content.Intent.EXTRA_EMAIL, recipients);

		  	    	sendIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse
		  	    		("file://"+ DataDir + dbase));
		  	    	sendIntent.setType("plain/text"); 
		  	    	startActivity(Intent.createChooser(sendIntent, "Send Mail ...."));
		  	    	
					 
		  	    		
		  	    	}
			 
			public class fileDownLoadMain extends AsyncTask<String, Integer, String> {
				 
				  @Override
				  protected String doInBackground(String... aurl) {
					
					  
					   
				
					  final File DataDir = new File(getFilesDir().getParent()+"/databases/");
						   
						    //this is the name of the local file you will create
						File f = new File(DataDir.toString());
					
						if (f.exists()){
						}else{
						f.mkdirs();
						}       
					 
						 int count;
					        try {
					        	 
					            URL url = new URL(aurl[0] + "PCalMain.htm");
					            
					            HttpURLConnection conection = (HttpURLConnection) url.openConnection();
					           
					            conection.setConnectTimeout(30000);
					            conection.setReadTimeout(30000);
					            conection.connect();
					            // getting file length
					            int lenghtOfFile = conection.getContentLength();
					 
					            // input stream to read file - with 8k buffer
					            InputStream input = new BufferedInputStream(url.openStream(), 8192);
					 
					            // Output stream to write file
					             try {
					            OutputStream output = new FileOutputStream(DataDir + "/PocketCal.db");
					             
					             
					 
					            byte data[] = new byte[1024];
					 
					            long total = 0;
					 
					            while ((count = input.read(data)) != -1) {
					                total += count;
					                // publishing the progress....
					                // After this onProgressUpdate will be called
					                int prog = (int) ((total*100)/lenghtOfFile);
					                publishProgress(prog);
					 
					                // writing data to file
					                output.write(data, 0, count);
					            }
					 
					            // flushing output
					            output.flush();
					 
					            // closing streams
					            output.close();
					            input.close();
					        } catch (FileNotFoundException fe){
					        	}
					       
					        } catch (Exception e) {
					        
					        
				  }
					 
					        return null;

				    }
				 
				 
				


				@Override
				  protected void onPostExecute(String result) {
				    // execution of result of Long time consuming operation
				  // copy file to data dir here
					pDialog.dismiss();
				
					
				
				  }
				 
				  @Override
				  protected void onPreExecute() {
				  // Things to be done before execution of long running operation. For example showing ProgessDialog
					  super.onPreExecute();
				        pDialog.show();

				
				  
				  }
				 
				
				  protected void onProgressUpdate(Integer... progress) {
			             
			             pDialog.setProgress((progress[0]));
			        }
				  
				  
				  }
				
			 
			 private class fileDownLoadUser extends AsyncTask<String, Integer, String> {
				 
				  @Override
				  protected String doInBackground(String... aurl) {
					
					  
					   
				
					 
					// File sd = new File(Environment.getExternalStorageDirectory().toString() + "/PocketCalDatabasesUpload/");
				
					  final File DataDir = new File(getFilesDir().getParent()+"/databases/");
						    //this is the file you want to download from the remote server
						   
						    //this is the name of the local file you will create
				
						       
					 
						 int count;
					        try {
					            URL url = new URL(aurl[0] + "UserData.htm");
					            URLConnection conection = url.openConnection();
					            conection.connect();
					            // getting file length
					            int lenghtOfFile = conection.getContentLength();
					 
					            // input stream to read file - with 8k buffer
					            InputStream input = new BufferedInputStream(url.openStream(), 8192);
					 
					            // Output stream to write file
					            OutputStream output = new FileOutputStream(DataDir + "/UserData.db");
					 
					            byte data[] = new byte[1024];
					 
					            long total = 0;
					 
					            while ((count = input.read(data)) != -1) {
					                total += count;
					                // publishing the progress....
					                // After this onProgressUpdate will be called
					                int prog = (int) ((total*100)/lenghtOfFile);
					                publishProgress(prog);
					 
					                // writing data to file
					                output.write(data, 0, count);
					            }
					 
					            // flushing output
					            output.flush();
					 
					            // closing streams
					            output.close();
					            input.close();
					 
					        } catch (Exception e) {
					           
					        }
					 
					        return null;

				    }
				 
				 
				


				@Override
				  protected void onPostExecute(String result) {
				    // execution of result of Long time consuming operation
				  // copy file to data dir here
					pDialog.dismiss();
				
					
				
				  }
				 
				  @Override
				  protected void onPreExecute() {
				  // Things to be done before execution of long running operation. For example showing ProgessDialog
					  super.onPreExecute();
				        pDialog.show();

				
				  
				  }
				 
				
				  protected void onProgressUpdate(Integer... progress) {
			             
			             pDialog.setProgress((progress[0]));
			        }
				  
				  
				  }
				
			 private class fileDownLoadDictionary extends AsyncTask<String, Integer, String> {
				 
				  @Override
				  protected String doInBackground(String... aurl) {
					
					  
					   
				
					 
				 File sd = new File(Environment.getExternalStorageDirectory().toString()+ "/tessdata/tessdata/");
				
						    //this is the file you want to download from the remote server
						   
						    //this is the name of the local file you will create
				 if (sd.canWrite()){
						//do Nothing
					}else{
						sd.mkdirs();
					}
						       
					 
						 int count;
					        try {
					            URL url = new URL(aurl[0] + "engtraineddata.htm");
					            URLConnection conection = url.openConnection();
					            conection.connect();
					            // getting file length
					            int lenghtOfFile = conection.getContentLength();
					 
					            // input stream to read file - with 8k buffer
					            InputStream input = new BufferedInputStream(url.openStream(), 8192);
					 
					            // Output stream to write file
					            OutputStream output = new FileOutputStream(sd + "/eng.traineddata");
					 
					            byte data[] = new byte[1024];
					 
					            long total = 0;
					 
					            while ((count = input.read(data)) != -1) {
					                total += count;
					                // publishing the progress....
					                // After this onProgressUpdate will be called
					                int prog = (int) ((total*100)/lenghtOfFile);
					                publishProgress(prog);
					 
					                // writing data to file
					                output.write(data, 0, count);
					            }
					 
					            // flushing output
					            output.flush();
					 
					            // closing streams
					            output.close();
					            input.close();
					 
					        } catch (Exception e) {
					           
					        }
					 
					        return null;

				    }
				 
				 
				


				@Override
				  protected void onPostExecute(String result) {
				    // execution of result of Long time consuming operation
				  // copy file to data dir here
					pDialog.dismiss();
				
					
				
				  }
				 
				  @Override
				  protected void onPreExecute() {
				  // Things to be done before execution of long running operation. For example showing ProgessDialog
					  super.onPreExecute();
				        pDialog.show();

				
				  
				  }
				 
				
				  protected void onProgressUpdate(Integer... progress) {
			             
			             pDialog.setProgress((progress[0]));
			        }
				  
				  
				  }		

}