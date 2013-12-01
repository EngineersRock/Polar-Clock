package com.clock;

import java.util.Calendar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class ClockView extends View {

	Paint secPaint = new Paint();
	Paint minPaint = new Paint();
	Paint hourPaint = new Paint();
	Paint dayPaint = new Paint();
	Paint monthPaint = new Paint();
	Path path;
	double secEndAngle;
	double minEndAngle;
	double hourEndAngle;
	double dayEndAngle;
	double monthEndAngle;
	int secColor;
	int minColor;
	int hourColor;
	int dayColor;
	int monthColor;
	Calendar c = Calendar.getInstance();
	
	 
	public ClockView(Context context) {
	 super(context);
	 init();
	}
	 
	public ClockView(Context context, AttributeSet attrs) {
	 super(context, attrs);
	 init();
	}
	 
	public ClockView(Context context, AttributeSet attrs, int defStyle) {
	 super(context, attrs, defStyle);
	 init();
	}
	 
	private void init(){
	 
	 
	}
	 
	@Override
	protected void onDraw(Canvas canvas) {
		 // TODO Auto-generated method stub
		 super.onDraw(canvas);
		 // Second circle
	     final RectF secCircle = new RectF();
	     final RectF minCircle = new RectF();
	     final RectF hourCircle = new RectF();
	     final RectF dayCircle = new RectF();
	     final RectF monthCircle = new RectF();
	     // These values determine the location of the circles. Note: These have to be set in here. Why? 
	     // Maybe because it needs to get the size of the current instance???
	     int displayWidth = this.getWidth()/2;
	     int displayHeight = this.getHeight()/2;
	     // These values determine the sizes of the circles
	     int secRadius = 500;
	     int minRadius = 450;
	     int hourRadius = 400;
	     int dayRadius = 350;
	     int monthRadius = 300;
	     float hsv[] = {0, 0, 0};
	     // determines the size and location of the circle. NOTE: The height and width cont the location of the circle and are set such that the
	     // circle is centered on the screen. Change the size of the circle by editing its radius.
	     //builds seconds circle
	     secCircle.set(displayWidth- secRadius, displayHeight - secRadius, displayWidth + secRadius, displayHeight + secRadius);
	     // This creates the color wheel affect by cycling through the hues at max saturation and value.
	     hsv[0] = (float) (c.get(Calendar.SECOND) * 6 + c.get(Calendar.MILLISECOND) * .006);
	     hsv[1] = 1;
	     hsv[2] = 1;
	     secColor = Color.HSVToColor(hsv);
	     secPaint.setColor(secColor);
	     secPaint.setStrokeWidth(30);
	     secPaint.setAntiAlias(true);
	     secPaint.setStrokeCap(Paint.Cap.ROUND);
	     secPaint.setStyle(Paint.Style.STROKE);
	     // Causes the circle to be drawn as time passes. Will finish after 1 minute
	     secEndAngle = c.get(Calendar.SECOND) * 6 + c.get(Calendar.MILLISECOND) * .006;
	     canvas.drawArc(secCircle, -90, (float) secEndAngle, false, secPaint);
		 
	     // builds minute circle
	     minCircle.set(displayWidth- minRadius, displayHeight - minRadius, displayWidth + minRadius, displayHeight + minRadius);
	     hsv[0] = c.get(Calendar.MINUTE) * 6;
	     minColor = Color.HSVToColor(hsv);
	     minPaint.setColor(minColor);
	     minPaint.setStrokeWidth(30);
	     minPaint.setAntiAlias(true);
	     minPaint.setStrokeCap(Paint.Cap.ROUND);
	     minPaint.setStyle(Paint.Style.STROKE);
	     
	     // draws minute circle
	     minEndAngle = c.get(Calendar.MINUTE) * 6;
	     // handles 0 case
	     if (minEndAngle == 0) minEndAngle = 3;
	     canvas.drawArc(minCircle, -90, (float) minEndAngle, false, minPaint);
	     
	     
	     // builds hour circle
	     hourCircle.set(displayWidth- hourRadius, displayHeight - hourRadius, displayWidth + hourRadius, displayHeight + hourRadius);
	     hsv[0] = c.get(Calendar.HOUR) * 30;
	     hourColor = Color.HSVToColor(hsv);
	     hourPaint.setColor(hourColor);
	     hourPaint.setStrokeWidth(30);
	     hourPaint.setAntiAlias(true);
	     hourPaint.setStrokeCap(Paint.Cap.ROUND);
	     hourPaint.setStyle(Paint.Style.STROKE);
	     
	     // draws hour circle
	     hourEndAngle = c.get(Calendar.HOUR) * 30;
	     // handles 0 case. Makes things prettier :)
	     if (hourEndAngle == 0) hourEndAngle = 3;
	     canvas.drawArc(hourCircle, -90, (float) hourEndAngle, false, hourPaint);
	     
	     // builds day circle
	     dayCircle.set(displayWidth- dayRadius, displayHeight - dayRadius, displayWidth + dayRadius, displayHeight + dayRadius);
	     hsv[0] = (c.get(Calendar.DAY_OF_WEEK) - 1) * 360/7;
	     dayColor = Color.HSVToColor(hsv);
	     dayPaint.setColor(dayColor);
	     dayPaint.setStrokeWidth(30);
	     dayPaint.setAntiAlias(true);
	     dayPaint.setStrokeCap(Paint.Cap.ROUND);
	     dayPaint.setStyle(Paint.Style.STROKE);
	     
	     // draws day circle. Makes things prettier :)
	     dayEndAngle = (c.get(Calendar.DAY_OF_WEEK) - 1) * (360 / 7);
	     // handles 0 case
	     if (dayEndAngle == 0) dayEndAngle = 3;
	     canvas.drawArc(dayCircle, -90, (float) dayEndAngle, false, dayPaint);
	     
	     // builds month circle
	     monthCircle.set(displayWidth- monthRadius, displayHeight - monthRadius, displayWidth + monthRadius, displayHeight + monthRadius);
	     hsv[0] = c.get(Calendar.MONTH) * 30;
	     monthColor = Color.HSVToColor(hsv);
	     monthPaint.setColor(monthColor);
	     monthPaint.setStrokeWidth(30);
	     monthPaint.setAntiAlias(true);
	     monthPaint.setStrokeCap(Paint.Cap.ROUND);
	     monthPaint.setStyle(Paint.Style.STROKE);
	     
	     // draws month circle. Makes things prettier :)
	     monthEndAngle = (c.get(Calendar.MONTH) - 1) * 30;
	     // handles 0 case
	     if (monthEndAngle == 0) monthEndAngle = 3;
	     canvas.drawArc(monthCircle, -90, (float) monthEndAngle, false, monthPaint);
	 
	}
	
	
}
