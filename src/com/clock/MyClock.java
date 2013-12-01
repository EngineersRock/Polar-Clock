package com.clock;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;

public class MyClock extends Activity {

	private Timer myTimer;
	boolean looperCalled=false;
	View nextView; 
	TextView centerClock;
	Calendar c = Calendar.getInstance();
	String time;
	/** Called when the activity is first created. */
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
	     super.onCreate(savedInstanceState);
	     setContentView(R.layout.activity_my_clock);
	     //This timer update the UI every second. For some reason this worked but runnables, threads, and handlers didn't. Why???
	     myTimer = new Timer();
	     myTimer.schedule(new TimerTask() {
	         @Override
	         public void run() {
	        	 if(Thread.currentThread().getName().equals(java.util.Timer.class)) {
	    	    	 if(!looperCalled){Looper.prepare(); looperCalled=true;}
	            	 else {}
	    	    	 }
	             TimerMethod();
	         }

	     }, 0, 10);

	 }
	 
	 private void TimerMethod()
	 {
	     //This method is called directly by the timer
	     //and runs in the same thread as the timer.

	     //We call the method that will work with the UI
	     //through the runOnUiThread method.
		 //nextView = findViewById(R.layout.activity_my_clock);
	     this.runOnUiThread(Timer_Tick);
	 }

	 private Runnable Timer_Tick = new Runnable() {
	     public void run() {
	    	 //This method runs in the same thread as the UI.               
	    	 //Do something to the UI thread here
	    	 setContentView(R.layout.activity_my_clock);
	    	 centerClock = (TextView) findViewById(R.id.textView);
	    	 String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

	    	// textView is the TextView view that should display it
	    	centerClock.setText(currentDateTimeString);
	    	
	     }
	 };
	 
	 
	 
	 
	 protected void onPause() {
		  super.onPause();
		  myTimer.cancel();
		}
	 
	 protected void onResume() {
		  super.onResume();
		  //This timer update the UI every second. For some reason this worked but runnables, threads, and handlers didn't. Why???
		  myTimer = new Timer();
		  myTimer.schedule(new TimerTask() {
		         @Override
		         public void run() {
		             TimerMethod();
		         }

		     }, 0, 10);
		}
	 
}

