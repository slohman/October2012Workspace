package com.yctc.alpaware;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.AbstractWheelTextAdapter;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;


import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;



public class fltreports2 extends SherlockActivity{

	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fltreports);
	
  	      
  	    final FrameLayout f2 = new FrameLayout(this);
	      final EditText input2 = new EditText(this);
	      
	     
  	      
  	      
	Button r1 = (Button) findViewById(R.id.blkreport);
    r1.setOnClickListener(new View.OnClickListener() {
    	public void onClick(View view) {
    		// dialog to get base search date here
    		
  	     
  	   
  	      
  	      loadspinners1();
  	      
  	   
            
    	}});
    
    Button r2 = (Button) findViewById(R.id.dhdreport);
    r2.setOnClickListener(new View.OnClickListener() {
    	public void onClick(View view) {
    		// dialog to get base search date here
    		
  	      input2.setGravity(Gravity.CENTER);
  	      f2.addView(input2, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
  	      input2.setHint("Enter  Date   YYYY-MM-DD");
  	  
  	           Builder b =  new AlertDialog.Builder(fltreports2.this);
  	           b.setView(f2);
  	           b.setTitle("Date Input");
  	      
  	      
  	     b.setPositiveButton("OK", new DialogInterface.OnClickListener(){
  	    	 	public void onClick(DialogInterface d, int which) {
  	    	 		d.dismiss();
  	    	 		// get date and execute SQL here....
  	    	 		String repDate = input2.getText().toString();
  	    	 		Bundle bun = new Bundle();
  	      			bun.putCharSequence("dID", repDate);
  	    	 		Intent myIntent = new Intent(getBaseContext(), dhdreportlists.class);
  	    	 		myIntent.putExtras(bun);
  	  	 		
  	              startActivityForResult(myIntent, 0);
  	    	 													  }
  	     															}
  	     					);
  	     					
 		b.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
 				public void onClick(DialogInterface d, int which) {
 				d.dismiss();
 																  }
  	
 																		  }
 		                  );
  	 
  	     b.create().show();

	 		
            
    	}});
    
    Button r3 = (Button) findViewById(R.id.eightin24);
    r3.setOnClickListener(new View.OnClickListener() {
    	public void onClick(View view) {
    		
    		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    		
	         GregorianCalendar cal = new GregorianCalendar();
	     
	         String Dte = sdf.format(cal.getTime());
	        
    				Bundle b = new Bundle();
    				b.putString("Dte", Dte);
    				b.putString("IN", "00:00");
    				b.putString("OUT", "00:00");
    				
  	    	 		Intent myIntent = new Intent(getBaseContext(), eightn24a.class);
  	    	 		
  	    	 		myIntent.putExtras(b);
  	  	 		
  	              startActivityForResult(myIntent, 0);
  	 }});
  	     					
    Button r4 = (Button) findViewById(R.id.threeandthree);
    r4.setOnClickListener(new View.OnClickListener() {
    	public void onClick(View view) {
    		
  	    	 		Intent myIntent = new Intent(getBaseContext(), landings.class);
  	    	 	//	myIntent.putExtras(bun);
  	  	 		
  	              startActivityForResult(myIntent, 0);
  	 }});
    Button r5 = (Button) findViewById(R.id.dutylimits);
    r5.setOnClickListener(new View.OnClickListener() {
    	public void onClick(View view) {
    		
  	    	 		Intent myIntent = new Intent(getBaseContext(), dutylimit.class);
  	    	 	//	myIntent.putExtras(bun);
  	  	 		
  	              startActivityForResult(myIntent, 0);
  	 }}); 
    
    Button r6 = (Button) findViewById(R.id.blkreport1);
    r6.setOnClickListener(new View.OnClickListener() {
    	public void onClick(View view) {
    		// dialog to get base search date here
    		
  	     
  	   
  	      
  	      loadspinners();
  	      
  	   
            
    	}});
    	
	}

			public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu){
		    	
		    	
		    	
		    	//	menu.add("Get_Egrid")
		    		
		    		//	.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		                			
		    		MenuInflater inflater=getSupportMenuInflater();
		    		inflater.inflate(R.menu.fltmenu, menu);
		    		return super.onCreateOptionsMenu(menu);
		    		}

		    		public boolean onOptionsItemSelected (com.actionbarsherlock.view.MenuItem item ){
		    			switch (item.getItemId()){
		    			
		    			case R.id.home :
		    				
		    				Intent myIntent1 = new Intent(getBaseContext(), PocketCal20.class);	
		    				startActivityForResult(myIntent1, 0);
		    			return true;	
		    			case R.id.back :
		    				super.onBackPressed();
		    			return true;	
		    		
		    			
			
		    			}
			return false;


		    		}
		    		
		    		public void loadspinners(){
		    			 
		    		 	
		    		   
		    		    final Dialog dialogSearchByDate = new Dialog(this);
		    		    dialogSearchByDate.setContentView(R.layout.getcalendar2_dialog);
		    		    dialogSearchByDate.setTitle("Enter Year, Month & Day, Type");

		    		    
		    		    final ArrayList<String> month = new ArrayList<String>();
		    		    
		    		    month.add("01");
		    		    month.add("02");
		    		    month.add("03");
		    		    month.add("04");
		    		    month.add("05");
		    		    month.add("06");
		    		    month.add("07");
		    		    month.add("08");
		    		    month.add("09");
		    		    month.add("10");
		    		    month.add("11");
		    		    month.add("12");
		    		   
		    		    
		    		    final ArrayList<String> yr = new ArrayList<String>();
		    		    
		    		   yr.add("2012");
		    		    yr.add("2013");
		    		    yr.add("2014");
		    		     yr.add("2015");
		    		     yr.add("2016");
		    		     yr.add("2017");
		    		     yr.add("2018");
		    		     yr.add("2019");
		    		      yr.add("2020");
		    		      yr.add("2021");
		    		      yr.add("2022");
		    		    
		    		      final ArrayList<String> dy = new ArrayList<String>();
		    		      
		    		      dy.add("01");
		    		      dy.add("02");
		    		      dy.add("03");
		    		      dy.add("04");
		    		      dy.add("05");
		    		      dy.add("06");
		    		      dy.add("07");
		    		      dy.add("08");
		    		      dy.add("09");
		    		      dy.add("10");
		    		      dy.add("11");
		    		      dy.add("12");
		    		      dy.add("13");
		    		      dy.add("14");
		    		      dy.add("15");
		    		      dy.add("16");
		    		      dy.add("17");
		    		      dy.add("18");
		    		      dy.add("19");
		    		      dy.add("20");
		    		      dy.add("21");
		    		      dy.add("22");
		    		      dy.add("23");
		    		      dy.add("24");
		    		      dy.add("25");
		    		      dy.add("26");
		    		      dy.add("27");
		    		      dy.add("28");
		    		      dy.add("29");
		    		      dy.add("30");
		    		      dy.add("31");
		    		      
		    		      final ArrayList<String> ty = new ArrayList<String>();
		    		      
		    		      ty.add("767");
		    		      ty.add("757");
		    		      ty.add("777");
		    		      ty.add("A300");
		    		      ty.add("A310");
		    		      ty.add("MD10");
		    		      ty.add("MD11");
		    		      
		    		      
		    		      
		    		    
		    		   
		    		    //get current yr-mo-dy
		    		      
		    		        Calendar c = Calendar.getInstance();  
		    			    SimpleDateFormat yr_date = new SimpleDateFormat("yyyy");
		    			 	String sYear = yr_date.format(c.getTime());
		    			 	
		    			 	SimpleDateFormat month_date = new SimpleDateFormat("mm");
		    			 	String sMonth = month_date.format(c.getTime());
		    			 	
		    			 	SimpleDateFormat day_date = new SimpleDateFormat("dd");
		    			 	String sdy = day_date.format(c.getTime());
		    			 	
		    			 	String sTyp = ty.toString();
		    		    
		    		    
		    		    
		    		    
		    		    final WheelView WVyr = (WheelView) dialogSearchByDate.findViewById(R.id.block_yr);
		    		    int iY = yr.indexOf(sYear);
		    		    WVyr.setViewAdapter(new yrWheelAdapter(this,yr));
		    		    WVyr.setCurrentItem(iY);
		    		    
		    		    final WheelView WVdy = (WheelView) dialogSearchByDate.findViewById(R.id.block_day);
		    		    int iD= dy.indexOf(sdy);
		    		    WVdy.setViewAdapter(new dyWheelAdapter(this,dy));
		    		    WVdy.setCurrentItem(iD); 
		    		    
		    		    final WheelView WVmonth = (WheelView) dialogSearchByDate.findViewById(R.id.block_month);
		    		    int iM = month.indexOf(sMonth);
		    		    WVmonth.setViewAdapter(new monthWheelAdapter(this,month));
		    		    WVmonth.setCurrentItem(iM);
		    		    
		    		    final WheelView WVtype = (WheelView) dialogSearchByDate.findViewById(R.id.block_typ);
		    		    int iT = ty.indexOf(sTyp);
		    		    WVtype.setViewAdapter(new typeWheelAdapter(this,ty));
		    		    WVtype.setCurrentItem(iT);
		    		    
		    		  
		    		    
		    		    Button settings = (Button)dialogSearchByDate.findViewById(R.id.dialogGetBlockHours);
		    			settings.setOnClickListener(new View.OnClickListener() {
		    				@Override
		    				public void onClick(View v) {
		    				
		    					
		    				//save parameters to text fields
		    					
		    				
		    				
		    					
		    				
		    	              
		    	           	  final String s_Month = month.get(WVmonth.getCurrentItem());
		    	           	  final String s_Yr = yr.get(WVyr.getCurrentItem());
		    	           	  final String s_dy = dy.get(WVdy.getCurrentItem());
		    	           	  final String s_typ = ty.get(WVtype.getCurrentItem());
		    	           	  
		    	           	  String srch_from = s_Yr+"-"+s_Month+"-"+s_dy;
		    	           	  
		    	           	
		    	 	 		Bundle bun = new Bundle();
		    	 			bun.putCharSequence("dID", srch_from);
		    	 			bun.putCharSequence("dIDD", s_typ);
		    	 	 		Intent myIntent = new Intent(getBaseContext(), fltreportlists1.class);
		    	 	 		myIntent.putExtras(bun);
		    	  		
		    	         startActivityForResult(myIntent, 0);
		    		 	    
		    		 	    
		    		 	  
		    		 	 
		    		 	   
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
		    		
		    		
		    		public class dyWheelAdapter extends AbstractWheelTextAdapter {
		    		 	
		    		    ArrayList<String> dy;

		    		    //An object of this class must be initialized with an array of Date type
		    		    protected dyWheelAdapter(Context context, ArrayList<String> dy) {
		    		    //Pass the context and the custom layout for the text to the super method
		    		          super(context, R.layout.wheel_item_time);
		    		          this.dy = dy ;
		    		    }

		    		    @Override
		    		 public View getItem(int index, View cachedView, ViewGroup parent) {
		    		          View view = super.getItem(index, cachedView, parent);
		    		          TextView pos = (TextView) view.findViewById(R.id.time_item);

		    		          //Format the date (Name of the day / number of the day)
		    		          
		    		          //Assign the text
		    		          pos.setTextSize(26);
		    		          pos.setText(dy.get(index));
		    		         
		    		          
		    		          return view;
		    		    }
		    		   
		    		    @Override
		    		    public int getItemsCount() {
		    		          return dy.size();
		    		    }

		    		    @Override
		    		    protected CharSequence getItemText(int index) {
		    		          return "";
		    		    }

		    		}
		    		public class monthWheelAdapter extends AbstractWheelTextAdapter {
		    		 	
		    		    ArrayList<String> mnth;

		    		    //An object of this class must be initialized with an array of Date type
		    		    protected monthWheelAdapter(Context context, ArrayList<String> mnth) {
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
		    		
		    		    public class yrWheelAdapter extends AbstractWheelTextAdapter {
		    		     	
		    		        ArrayList<String> yr;

		    		        //An object of this class must be initialized with an array of Date type
		    		        protected yrWheelAdapter(Context context, ArrayList<String> yr) {
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
		    		        
		    		        
		    		        
		    		        public class typeWheelAdapter extends AbstractWheelTextAdapter {
				    		 	
				    		    ArrayList<String> typ;

				    		    //An object of this class must be initialized with an array of Date type
				    		    protected typeWheelAdapter(Context context, ArrayList<String> typ) {
				    		    //Pass the context and the custom layout for the text to the super method
				    		          super(context, R.layout.wheel_item_time);
				    		          this.typ = typ;
				    		    }

				    		    @Override
				    		 public View getItem(int index, View cachedView, ViewGroup parent) {
				    		          View view = super.getItem(index, cachedView, parent);
				    		          TextView pos = (TextView) view.findViewById(R.id.time_item);

				    		          //Format the date (Name of the day / number of the day)
				    		          
				    		          //Assign the text
				    		          pos.setTextSize(26);
				    		          pos.setText(typ.get(index));
				    		         
				    		          
				    		          return view;
				    		    }
				    		   
				    		    @Override
				    		    public int getItemsCount() {
				    		          return typ.size();
				    		    }

				    		    @Override
				    		    protected CharSequence getItemText(int index) {
				    		          return "";
				    		    }
				    		} 
		    		        
		    		        public void loadspinners1(){
				    			 
				    		 	
		 		    		   
				    		    final Dialog dialogSearchByDate = new Dialog(this);
				    		    dialogSearchByDate.setContentView(R.layout.getcalendar1_dialog);
				    		    dialogSearchByDate.setTitle("Enter Year, Month & Day");

				    		    
				    		    final ArrayList<String> month = new ArrayList<String>();
				    		    
				    		    month.add("01");
				    		    month.add("02");
				    		    month.add("03");
				    		    month.add("04");
				    		    month.add("05");
				    		    month.add("06");
				    		    month.add("07");
				    		    month.add("08");
				    		    month.add("09");
				    		    month.add("10");
				    		    month.add("11");
				    		    month.add("12");
				    		   
				    		    
				    		    final ArrayList<String> yr = new ArrayList<String>();
				    		    
				    		   yr.add("2012");
				    		    yr.add("2013");
				    		    yr.add("2014");
				    		     yr.add("2015");
				    		     yr.add("2016");
				    		     yr.add("2017");
				    		     yr.add("2018");
				    		     yr.add("2019");
				    		      yr.add("2020");
				    		      yr.add("2021");
				    		      yr.add("2022");
				    		    
				    		      final ArrayList<String> dy = new ArrayList<String>();
				    		      
				    		      dy.add("01");
				    		      dy.add("02");
				    		      dy.add("03");
				    		      dy.add("04");
				    		      dy.add("05");
				    		      dy.add("06");
				    		      dy.add("07");
				    		      dy.add("08");
				    		      dy.add("09");
				    		      dy.add("10");
				    		      dy.add("11");
				    		      dy.add("12");
				    		      dy.add("13");
				    		      dy.add("14");
				    		      dy.add("15");
				    		      dy.add("16");
				    		      dy.add("17");
				    		      dy.add("18");
				    		      dy.add("19");
				    		      dy.add("20");
				    		      dy.add("21");
				    		      dy.add("22");
				    		      dy.add("23");
				    		      dy.add("24");
				    		      dy.add("25");
				    		      dy.add("26");
				    		      dy.add("27");
				    		      dy.add("28");
				    		      dy.add("29");
				    		      dy.add("30");
				    		      dy.add("31");
				    		      
				    		      
				    		    
				    		   
				    		    //get current yr-mo-dy
				    		      
				    		        Calendar c = Calendar.getInstance();  
				    			    SimpleDateFormat yr_date = new SimpleDateFormat("yyyy");
				    			 	String sYear = yr_date.format(c.getTime());
				    			 	
				    			 	SimpleDateFormat month_date = new SimpleDateFormat("mm");
				    			 	String sMonth = month_date.format(c.getTime());
				    			 	
				    			 	SimpleDateFormat day_date = new SimpleDateFormat("dd");
				    			 	String sdy = day_date.format(c.getTime());
				    		    
				    		    
				    		    
				    		    
				    		    final WheelView WVyr = (WheelView) dialogSearchByDate.findViewById(R.id.block_yr);
				    		    int iY = yr.indexOf(sYear);
				    		    WVyr.setViewAdapter(new yrWheelAdapter(this,yr));
				    		    WVyr.setCurrentItem(iY);
				    		    
				    		    final WheelView WVdy = (WheelView) dialogSearchByDate.findViewById(R.id.block_day);
				    		    int iD= dy.indexOf(sdy);
				    		    WVdy.setViewAdapter(new dyWheelAdapter(this,dy));
				    		    WVdy.setCurrentItem(iD); 
				    		    
				    		    final WheelView WVmonth = (WheelView) dialogSearchByDate.findViewById(R.id.block_month);
				    		    int iM = month.indexOf(sMonth);
				    		    WVmonth.setViewAdapter(new monthWheelAdapter(this,month));
				    		    WVmonth.setCurrentItem(iM);
				    		    
				    		  
				    		    
				    		    Button settings = (Button)dialogSearchByDate.findViewById(R.id.dialogGetBlockHours);
				    			settings.setOnClickListener(new View.OnClickListener() {
				    				@Override
				    				public void onClick(View v) {
				    				
				    					
				    				//save parameters to text fields
				    					
				    				
				    				
				    					
				    				
				    	              
				    	           	  final String s_Month = month.get(WVmonth.getCurrentItem());
				    	           	  final String s_Yr = yr.get(WVyr.getCurrentItem());
				    	           	  final String s_dy = dy.get(WVdy.getCurrentItem());
				    	           	  
				    	           	  String srch_from = s_Yr+"-"+s_Month+"-"+s_dy;
				    	           	  
				    	           	
				    	 	 		Bundle bun = new Bundle();
				    	 			bun.putCharSequence("dID", srch_from);
				    	 			bun.putCharSequence("dIDD", "empty");
				    	 	 		Intent myIntent = new Intent(getBaseContext(), fltreportlists1.class);
				    	 	 		myIntent.putExtras(bun);
				    	  		
				    	         startActivityForResult(myIntent, 0);
				    		 	    
				    		 	    
				    		 	  
				    		 	 
				    		 	   
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
		    		        
		    		        
		    		    }
		    		
		    		    
		    		    



    
	
	        	