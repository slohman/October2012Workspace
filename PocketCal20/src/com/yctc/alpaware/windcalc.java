package com.yctc.alpaware;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;

public class windcalc extends  SherlockActivity {

	

	int rwyHdg;
	int windDir;
	
	int gust;
	
	
	int twC;
	
	
	
	
	
	
	
	
	@Override
	
		
		 public void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
		        setContentView(R.layout.windcalc);
	    
	   
	    
	    
	    Button r1 = (Button) findViewById(R.id.buttonCalc);
	    
	    r1.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View view) {
	    		
	    		
	    		
	     EditText wSpd = (EditText) findViewById(R.id.editwindspeed);
	    
	     EditText rwyH = (EditText) findViewById(R.id.etrwyhdg);
	      
	    String[] windata = wSpd.getText().toString().split("/");
	   
	    
	    		
	 int runwayHeading = Integer.parseInt(rwyH.getText().toString());
	 int windDirection = Integer.parseInt(windata[0].toString());
	int  windSpd = Integer.parseInt(windata[1].toString());
	
	
	
	
	 int gust =  Integer.parseInt(windata[2].toString()) - windSpd;
	 int gustspd = Integer.parseInt(windata[2].toString());
	 
	 
	 
	 
	double windangle = Math.abs(runwayHeading- windDirection);
	double hwC = windSpd * Math.cos(Math.toRadians(windangle)) + .5;
	double xwC;
	
	if (gustspd>windSpd)
	{
		 xwC = gustspd * Math.sin(Math.toRadians(windangle));
	} else {
	
        xwC = windSpd * Math.sin(Math.toRadians(windangle));
	
	}
	
	TextView headwindC = (TextView) findViewById(R.id.tvHWvalue);
	TextView crosswindC = (TextView) findViewById(R.id.tvXW);
	
	int headwind = (int) Math.rint(hwC);
	int crosswind = (int) Math.rint(xwC);
	
	headwindC.setText(Double.toString(headwind));
	crosswindC.setText(Double.toString(crosswind));
	
	
	// min =5 max is +20
	
	int speedadditive = (int) ((headwind * .5) + (gust));
	if (speedadditive >= 20) {
		speedadditive = 20;
	}
	if (speedadditive < 5){
		speedadditive = 5;
	}
	TextView speedA = (TextView) findViewById(R.id.sAdd);
	speedA.setText(String.valueOf(speedadditive));
	
	
	    	}});
	
	
	
}

	
	
}