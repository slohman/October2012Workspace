package com.yctc.alpaware;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.commonsware.cwac.wakeful.WakefulIntentService;

public class AlarmReceiver extends BroadcastReceiver 
{
    @Override
    public void onReceive(Context context, Intent intent) 
    {
    	
    	String IntTyp = intent.getStringExtra("OP");
    	
    	
    	
    	if (IntTyp.equals("KeepAlive")){
    //	Intent myService = new Intent(context, KeepAliveWakeful.class);
    //    context.startService(myService);
    		
    		WakefulIntentService.sendWakefulWork(context, KeepAliveWakeful.class);
    	}
    	
    	
    	if (IntTyp.equals("OpenTime")){
    	String startDate = 	intent.getStringExtra("STARTDATE");
    	String endDate = 	intent.getStringExtra("ENDDATE");
    	String base = 	intent.getStringExtra("BASE");
    	String equip = 	intent.getStringExtra("EQUIP");
    	String seat = 	intent.getStringExtra("SEAT");
    	String sby = intent.getStringExtra("SBY");
    		
    	Intent myOtService = new Intent(context, OTWakefulSvc.class);	
    	
    	myOtService.putExtra("STARTDATE",startDate);
    	myOtService.putExtra("ENDDATE",endDate);
    	myOtService.putExtra("BASE",base);
    	myOtService.putExtra("EQUIP",equip);
    	myOtService.putExtra("SEAT",seat);
    	myOtService.putExtra("STBY", sby);
    	
    	
    	WakefulIntentService.sendWakefulWork(context, myOtService);
    	
    	}
    }
	
}
