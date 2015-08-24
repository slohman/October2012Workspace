package com.yctc.alpaware;




import com.actionbarsherlock.view.MenuInflater;


import android.content.Intent;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;
import android.os.Bundle;

import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class dutylimit extends SherlockActivity{

	
	 int zlax = -8 , zanc = -9;
 	int zhkg = 8 ,  zmem = -6;
 	int zcgn = 2;
 	int ischedDuty, ischedDutymin,ischedDutyMinutes, ifarDuty = 16;
		int ioperationalDuty, ioperationalDutyMinutes;
		int iMaxDutyHoursSched = 0, iMaxDutyMinSched = 0,
		iMaxDutyHoursOp,iMaxDutyMinutesOp, iMaxFarHours,iMaxFarMinutes;
     
     int ihours= 0 , iminutes = 0;
     int itotalminutes;
     
  
	
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dutytime);
        
        final EditText hours = (EditText)findViewById(R.id.dutystartHH);
        final EditText min = (EditText)findViewById(R.id.dutystartMM);
        final RadioButton lax = (RadioButton)findViewById(R.id.lax);
        final RadioButton anc = (RadioButton)findViewById(R.id.anc);
        final RadioButton hkg = (RadioButton)findViewById(R.id.hkg);
        final RadioButton mem = (RadioButton)findViewById(R.id.mem);
        final RadioButton cgn = (RadioButton)findViewById(R.id.cgn);
        final CheckBox dst = (CheckBox)findViewById(R.id.dst);
        
       
        lax.setChecked(true);
        // get the start time Hours and minutes
        
        
         Button calc = (Button) findViewById(R.id.calcduty);
        calc.setOnClickListener(new View.OnClickListener() {
        	
			public void onClick(View view) {   
        		
                       
        
        if (lax.isChecked()) {
        	if (dst.isChecked()){
        		
        		
        		
        		
        		ihours = Integer.parseInt(hours.getText().toString());
        		
        		if (ihours + (zlax+1) < 0){
        			ihours = (ihours + 24) + (zlax+1);
        		}else{
        			ihours = (ihours + (zlax+1));
        		}
        		
        		iminutes = Integer.parseInt(min.getText().toString());
        		itotalminutes = (ihours*60)+ (iminutes);
        		
        	}else {
        		
        		ihours = Integer.parseInt(hours.getText().toString());
        		if (ihours + (zlax) < 0){
        			ihours = (ihours + 24) + (zlax);
        		}else{
        			ihours = (ihours + (zlax));
        		}
        		iminutes = Integer.parseInt(min.getText().toString());
        		itotalminutes = (ihours*60)+ (iminutes);
        	}}
        if (anc.isChecked()) {
        	if (dst.isChecked()){
        		
        		ihours = Integer.parseInt(hours.getText().toString());
        		if (ihours + (zanc+1) < 0){
        			ihours = (ihours + 24) + (zanc+1);
        		}else{
        			ihours = (ihours + (zanc+1));
        		}
        		iminutes = Integer.parseInt(min.getText().toString());
        		itotalminutes = (ihours*60)+ (iminutes);
        	}else {
        		ihours = Integer.parseInt(hours.getText().toString());
        		if (ihours + (zanc) < 0){
        			ihours = (ihours + 24) + (zanc);
        		}else{
        			ihours = (ihours + (zanc));
        		}
        		iminutes = Integer.parseInt(min.getText().toString());
        		itotalminutes = (ihours*60)+ (iminutes);
        	}}
        if (hkg.isChecked()) {
        	
        		ihours = Integer.parseInt(hours.getText().toString());
        		if (ihours + (zhkg) < 0){
        			ihours = (ihours + 24) + (zhkg);
        		}else{
        			ihours = (ihours + (zhkg));
        		}
        		iminutes = Integer.parseInt(min.getText().toString());
        		itotalminutes = (ihours*60)+ (iminutes);
        	}
        
        if (mem.isChecked()) {
        	if (dst.isChecked()){
        		
        		ihours = Integer.parseInt(hours.getText().toString());
        		if (ihours + (zmem+1) < 0){
        			ihours = (ihours + 24) + (zmem+1);
        		}else{
        			ihours = (ihours + (zmem+1));
        		}
        		iminutes = Integer.parseInt(min.getText().toString());
        		itotalminutes = (ihours*60)+ (iminutes);
        	}else {
        		ihours = Integer.parseInt(hours.getText().toString());
        		if (ihours + (zmem) < 0){
        			ihours = (ihours + 24) + (zmem);
        		}else{
        			ihours = (ihours + (zmem));
        		}
        		iminutes = Integer.parseInt(min.getText().toString());
        		itotalminutes = (ihours*60)+ (iminutes);
        	}}
        
        if (cgn.isChecked()) {
        	if (dst.isChecked()){
        		
        		ihours = Integer.parseInt(hours.getText().toString());
        		if (ihours + (zcgn+1) < 0){
        			ihours = (ihours + 24) + (zcgn+1);
        		}else{
        			ihours = (ihours + (zcgn+1));
        		}
        		iminutes = Integer.parseInt(min.getText().toString());
        		itotalminutes = (ihours*60)+ (iminutes);
        	}else {
        		ihours = Integer.parseInt(hours.getText().toString());
        		if (ihours + (zcgn) < 0){
        			ihours = (ihours + 24) + (zcgn);
        		}else{
        			ihours = (ihours + (zcgn));
        		}
        		iminutes = Integer.parseInt(min.getText().toString());
        		itotalminutes = (ihours*60)+ (iminutes);
        	}}
        
     // set a time object
    		Time stime = new Time();
    	    stime.set(0, iminutes, ihours, 1, 1, 1);
    	    Time otime = new Time();
    	    otime.set(0, iminutes, ihours, 1, 1, 1);
    	    Time ftime = new Time();
    	    ftime.set(0, iminutes, ihours, 1, 1, 1);

        
        // Calculate Duty Period to use 
        
        if ((itotalminutes >= 60) && (itotalminutes <= 299 )){  //critical duty period
        
        		CriticalDuty(itotalminutes,ihours,iminutes,stime,otime,ftime);}
        
        	
        if ((itotalminutes >= 300) && (itotalminutes  < 330 )){  //day period with critical to day transition
        		CriticaltoDayDuty(itotalminutes,ihours,iminutes,stime,otime,ftime);}
        	
        if (itotalminutes >= 330 && itotalminutes <= 915){ // normal Day Duty
        	
        		DayDuty(itotalminutes,ihours,iminutes,stime,otime,ftime);}
        
        if (itotalminutes > 915 && itotalminutes < 1005){ // Day to Night trans 1:1
    		
        		DaytoNight(itotalminutes,ihours,iminutes,stime,otime,ftime);}
        
        if (itotalminutes >= 1350 || itotalminutes < 60){ // Day to Night trans 1:1
    		
    		NighttoCriticalDuty(itotalminutes,ihours,iminutes,stime,otime,ftime);    		}	
        
        if (itotalminutes >= 1005 && itotalminutes < 1350){ // Day to Night trans 1:1
    		
    		NightDuty(itotalminutes,ihours,iminutes,stime,otime,ftime);
        }
}});  
        
 // Functions      
        
}
	
	
	public int[] normalize(int hr, int min){
		
		if (min >= 60){
			min = min - 60;
			hr = hr + 1;
		}
		if (hr == 24){ hr = 0;}
		if (hr > 24){
			hr = hr - 24;	}
		
		
		int result[]= {hr,min};
		
		return result;
		
		
	}
	
	public void outputTime(Time t1, Time t2, Time t3){
		
		
		TextView schedlimit = (TextView)findViewById(R.id.schedLimit);
        TextView oplimit = (TextView)findViewById(R.id.opLimit);
        TextView farlimit = (TextView)findViewById(R.id.Farlimit);
        
		schedlimit.setText(t1.format("%H%M"));
		oplimit.setText(t2.format("%H%M"));
		farlimit.setText(t3.format("%H%M"));
		
		
		
	}
	public int[] adjDst(int iMaxDutyHoursSched , int iMaxDutyHoursOp, int iMaxFarHours){
		final RadioButton lax = (RadioButton)findViewById(R.id.lax);
        final RadioButton anc = (RadioButton)findViewById(R.id.anc);
        final RadioButton mem = (RadioButton)findViewById(R.id.mem);
        final RadioButton cgn = (RadioButton)findViewById(R.id.cgn);
        final CheckBox dst = (CheckBox)findViewById(R.id.dst);
		
		if (lax.isChecked()){
			if (dst.isChecked()){
 			iMaxDutyHoursSched = (iMaxDutyHoursSched - (zlax+1));
 			iMaxDutyHoursOp = (iMaxDutyHoursOp - (zlax+1));
 			 iMaxFarHours = (iMaxFarHours  - (zlax+1));
			}else{
				iMaxDutyHoursSched = (iMaxDutyHoursSched - (zlax));
	 			iMaxDutyHoursOp = (iMaxDutyHoursOp - zlax);
	 			 iMaxFarHours = (iMaxFarHours  - zlax);
			}
 		}
 		if (anc.isChecked()){
 			if (dst.isChecked()){
 			iMaxDutyHoursSched = (iMaxDutyHoursSched - (zanc+1));
 			iMaxDutyHoursOp = (iMaxDutyHoursOp - (zanc+1));
 			 iMaxFarHours = (iMaxFarHours  - (zanc+1));
 			}else{
 				iMaxDutyHoursSched = (iMaxDutyHoursSched - (zanc));
 	 			iMaxDutyHoursOp = (iMaxDutyHoursOp - (zanc));
 	 			 iMaxFarHours = (iMaxFarHours  - (zanc));	
 			}
 		}
 		if (mem.isChecked()){
 			if (dst.isChecked()){
 	 			iMaxDutyHoursSched = (iMaxDutyHoursSched - (zmem+1));
 	 			iMaxDutyHoursOp = (iMaxDutyHoursOp - (zmem+1));
 	 			 iMaxFarHours = (iMaxFarHours  - (zmem+1));
 				}else{
 					iMaxDutyHoursSched = (iMaxDutyHoursSched - (zmem));
 		 			iMaxDutyHoursOp = (iMaxDutyHoursOp - zmem);
 		 			 iMaxFarHours = (iMaxFarHours  - zmem);
 				}}
		
 		if (cgn.isChecked()){
 			if (dst.isChecked()){
 			iMaxDutyHoursSched = (iMaxDutyHoursSched + (zcgn+1));
 			iMaxDutyHoursOp = (iMaxDutyHoursOp + (zcgn+1));
 			 iMaxFarHours = (iMaxFarHours  + (zcgn+1));
 		}else{
			iMaxDutyHoursSched = (iMaxDutyHoursSched + (zcgn));
 			iMaxDutyHoursOp = (iMaxDutyHoursOp + (zcgn));
 			 iMaxFarHours = (iMaxFarHours  + (zcgn));	
		}}
 		int[] DSTAdjArray = {iMaxDutyHoursSched,iMaxDutyHoursOp,iMaxFarHours};
		return DSTAdjArray;
		
		
	
	}
public void DayDuty(int totalMinutes, int hours, int minutes,Time stime,Time otime,Time ftime){
	ischedDuty = 13;
	ischedDutyMinutes = 0;
	ioperationalDuty = 14;
	ioperationalDutyMinutes = 30;
	ifarDuty = 16;
	
	iMaxDutyHoursSched = (stime.hour + (13  ));
	iMaxDutyMinSched = (stime.minute);
	iMaxDutyHoursOp = (otime.hour +  ioperationalDuty);
	iMaxDutyMinutesOp = (otime.minute + ioperationalDutyMinutes);
	iMaxFarHours = (ftime.hour + ifarDuty );
	iMaxFarMinutes = (ftime.minute + 0 );
	
	int dstAdj[] = adjDst(iMaxDutyHoursSched,iMaxDutyHoursOp,iMaxFarHours);
	int result[] = normalize(dstAdj[0],iMaxDutyMinSched);
		stime.set(0, result[1], result[0], 1, 1, 1);
		result = normalize(dstAdj[1] ,iMaxDutyMinutesOp);
		otime.set(0, result[1], result[0], 1, 1, 1);
		result =normalize(dstAdj[2] ,iMaxFarMinutes);
		ftime.set(0, result[1], result[0], 1, 1, 1);
		
	    outputTime(stime,otime,ftime);

}
public void CriticalDuty(int totalMinutes, int hours, int minutes, Time stime, Time otime, Time ftime){
	
	
	ischedDuty = 9;
	ioperationalDuty = 10;
	ioperationalDutyMinutes = 30;
	ifarDuty = 16;
	iMaxDutyHoursSched = (stime.hour + ischedDuty );
	iMaxDutyMinSched = (stime.minute + 0);
	iMaxDutyHoursOp = (otime.hour +  ioperationalDuty);
	iMaxDutyMinutesOp = (otime.minute + ioperationalDutyMinutes);
	iMaxFarHours = (ftime.hour + ifarDuty );
	iMaxFarMinutes = (ftime.minute + 0 );
	
	
	
		
		int dstAdj[] = adjDst(iMaxDutyHoursSched,iMaxDutyHoursOp,iMaxFarHours);
		int result[] = normalize(dstAdj[0],iMaxDutyMinSched);
			stime.set(0, result[1], result[0], 1, 1, 1);
			result = normalize(dstAdj[1] ,iMaxDutyMinutesOp);
			otime.set(0, result[1], result[0], 1, 1, 1);
			result =normalize(dstAdj[2] ,iMaxFarMinutes);
			ftime.set(0, result[1], result[0], 1, 1, 1);
		

			outputTime(stime,otime,ftime);

}
public void CriticaltoDayDuty(int totalMinutes, int hours, int minutes, Time stime, Time otime, Time ftime){
		
	ischedDuty = 13;
	ischedDutyMinutes = 0;
	ioperationalDuty = 14;
	ioperationalDutyMinutes = 30;
	ifarDuty = 16;
	iMaxDutyHoursSched = (stime.hour + ischedDuty );
	iMaxDutyMinSched = (stime.minute + 0);
	iMaxDutyHoursOp = (otime.hour +  ioperationalDuty);
	iMaxDutyMinutesOp = (otime.minute + ioperationalDutyMinutes);
	iMaxFarHours = (ftime.hour + ifarDuty );
	iMaxFarMinutes = (ftime.minute + 0 );
	
	
		// transition slope ( 0500 to 0530
		// each min adds 4 minutes to a max of 
		int ihrslopeadj = 0, iminslopeadj=0;
		
		if (iminutes < 15){
			ihrslopeadj = (iminutes * 4);
		}
		
		if ( iminutes >= 15 ){
			ihrslopeadj = (iminutes * 4)/60;
			iminslopeadj =  (iminutes * 4) - 60;
			}
		
		iMaxDutyHoursSched = (stime.hour + (11 + ihrslopeadj  ));
		iMaxDutyMinSched = (stime.minute + iminslopeadj);
		iMaxDutyHoursOp = (otime.hour +  ioperationalDuty);
		iMaxDutyMinutesOp = (otime.minute + ioperationalDutyMinutes);
		iMaxFarHours = (ftime.hour + ifarDuty );
		iMaxFarMinutes = (ftime.minute + 0 );
	


		int dstAdj[] = adjDst(iMaxDutyHoursSched,iMaxDutyHoursOp,iMaxFarHours);
		int result[] = normalize(dstAdj[0],iMaxDutyMinSched);
		stime.set(0, result[1], result[0], 1, 1, 1);
		result = normalize(dstAdj[1] ,iMaxDutyMinutesOp);
		otime.set(0, result[1], result[0], 1, 1, 1);
		result =normalize(dstAdj[2] ,iMaxFarMinutes);
		ftime.set(0, result[1], result[0], 1, 1, 1);
	
		outputTime(stime,otime,ftime);
}
public void DaytoNight(int totalMinutes, int hours, int minutes, Time stime, Time otime, Time ftime){
	
	//1:1 for 90 min
	
	ischedDuty = 13;
	ischedDutyMinutes = 0;
	ioperationalDuty = 14;
	ioperationalDutyMinutes = 30;
	ifarDuty = 16;
	
	int iminslopeadj=0;
	
	iminslopeadj = (totalMinutes - 915); // 915 minutes = 1515 local
	
		ischedDuty = (780 - iminslopeadj)/60;
		ischedDutyMinutes = (780 - iminslopeadj) - (ischedDuty * 60) ;
	
			
	iMaxDutyHoursSched = (stime.hour + (ischedDuty ));
	iMaxDutyMinSched = (stime.minute + ischedDutyMinutes);
	iMaxDutyHoursOp = (otime.hour +  ioperationalDuty);
	iMaxDutyMinutesOp = (otime.minute + ioperationalDutyMinutes);
	iMaxFarHours = (ftime.hour + ifarDuty );
	iMaxFarMinutes = (ftime.minute + 0 );
		
			int dstAdj[] = adjDst(iMaxDutyHoursSched,iMaxDutyHoursOp,iMaxFarHours);
			int result[] = normalize(dstAdj[0],iMaxDutyMinSched);
			stime.set(0, result[1], result[0], 1, 1, 1);
			result = normalize(dstAdj[1] ,iMaxDutyMinutesOp);
			otime.set(0, result[1], result[0], 1, 1, 1);
			result =normalize(dstAdj[2] ,iMaxFarMinutes);
			ftime.set(0, result[1], result[0], 1, 1, 1);
		

			outputTime(stime,otime,ftime);
			
		

}

public void NighttoCriticalDuty(int totalMinutes, int hours, int minutes, Time stime, Time otime, Time ftime){

	ischedDuty = 11;
	ischedDutyMinutes = 30;
	ioperationalDuty = 13;
	ioperationalDutyMinutes = 0;
	ifarDuty = 16;
	
	
	int iminslopeadj=0;
	
	iminslopeadj = (totalMinutes - 1350); // 1350 minutes = 2230 local
	
		ischedDuty = (690 - iminslopeadj)/60;
		ischedDutyMinutes = (690 - iminslopeadj) - (ischedDuty * 60) ;
		
		iMaxDutyHoursSched = (stime.hour + ischedDuty );
		iMaxDutyMinSched = (stime.minute + ischedDutyMinutes);
		iMaxDutyHoursOp = (otime.hour +  ioperationalDuty);
		iMaxDutyMinutesOp = (otime.minute + ioperationalDutyMinutes);
		iMaxFarHours = (ftime.hour + ifarDuty );
		iMaxFarMinutes = (ftime.minute + 0 );	
	
	int dstAdj[] = adjDst(iMaxDutyHoursSched,iMaxDutyHoursOp,iMaxFarHours);
	int result[] = normalize(dstAdj[0],iMaxDutyMinSched);
	stime.set(0, result[1], result[0], 1, 1, 1);
	result = normalize(dstAdj[1] ,iMaxDutyMinutesOp);
	otime.set(0, result[1], result[0], 1, 1, 1);
	result =normalize(dstAdj[2] ,iMaxFarMinutes);
	ftime.set(0, result[1], result[0], 1, 1, 1);

	outputTime(stime,otime,ftime);

}
public void NightDuty(int totalMinutes, int hours, int minutes, Time stime, Time otime, Time ftime){

	ischedDuty = 11;
	ischedDutyMinutes = 30;
	ioperationalDuty = 13;
	ioperationalDutyMinutes = 0;
	ifarDuty = 16;
	iMaxDutyHoursSched = (stime.hour + ischedDuty );
	iMaxDutyMinSched = (stime.minute + ischedDutyMinutes);
	iMaxDutyHoursOp = (otime.hour +  ioperationalDuty);
	iMaxDutyMinutesOp = (otime.minute + ioperationalDutyMinutes);
	iMaxFarHours = (ftime.hour + ifarDuty );
	iMaxFarMinutes = (ftime.minute + 0 );
	
	int dstAdj[] = adjDst(iMaxDutyHoursSched,iMaxDutyHoursOp,iMaxFarHours);
	int result[] = normalize(dstAdj[0],iMaxDutyMinSched);
	stime.set(0, result[1], result[0], 1, 1, 1);
	result = normalize(dstAdj[1] ,iMaxDutyMinutesOp);
	otime.set(0, result[1], result[0], 1, 1, 1);
	result =normalize(dstAdj[2] ,iMaxFarMinutes);
	ftime.set(0, result[1], result[0], 1, 1, 1);

	outputTime(stime,otime,ftime);

}

public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu){
	
	
	
	//	menu.add("Get_Egrid")
		
		//	.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
            			
		MenuInflater inflater=getSupportMenuInflater();
		inflater.inflate(R.menu.fltreportsmenu, menu);
		return super.onCreateOptionsMenu(menu);
		}

		public boolean onOptionsItemSelected (com.actionbarsherlock.view.MenuItem item ){
			switch (item.getItemId()){
			
			case R.id.home :
				
				Intent myIntent1 = new Intent(getBaseContext(), PocketCal20.class);	
				startActivityForResult(myIntent1, 0);
			return true;	
			case R.id.back :
    			Intent myIntent3 = new Intent(getBaseContext(), fltreports2.class);	
    			startActivityForResult(myIntent3, 0);
			return true;	
		

			}
return false;


		}









}
	
	

	





