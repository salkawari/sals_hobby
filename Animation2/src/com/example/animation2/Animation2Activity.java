package com.example.animation2;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.os.Bundle;

public class Animation2Activity extends Activity {

	// constants..
	private static final String TAG =Animation2Activity.class.getSimpleName();
	private static final boolean IS_DEBUG = true;

	// these are used in the different methods..
	ImageView animImageStickman;
	AnimationDrawable animDrawableStickman;	


	// this is the lister that will be stuck onto animImageStickman..
	OnClickListener OnClickStickman;

	//useful logging method..
	private void logDebug(String log_message) {
		if (IS_DEBUG) { Log.d(TAG, log_message); } 
	}

	// this method causes the animation to run..
	private void runAnimationViaPost() {
		boolean bool_result = false;
		//		animImageStickman.postInvalidate();
		//		animDrawableStickman = (AnimationDrawable) animImageStickman.getDrawable();
		bool_result = animImageStickman.post(
				new Runnable() {
					public void run() { 
						try{
							logDebug("starting animation!");
							//animImageStickman.postInvalidate();
							//animDrawableStickman = (AnimationDrawable) animImageStickman.getDrawable();
							//animDrawableStickman.invalidateSelf();
							animImageStickman.setImageDrawable(animDrawableStickman);
							animDrawableStickman.start();
							// this is the reset bit..
							animDrawableStickman.setVisible(true, true);
							
						}catch(Throwable tr) { Log.e(TAG, "Error in runAnimationViaPost! ",tr);}
					}
				}
				);		
		logDebug("bool_result="+bool_result);
	}
	//Called when the activity is first created. 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		try {						
			super.onCreate(savedInstanceState);	
			setContentView(R.layout.main);

			// make an image view for r.id.stickman..			
			animImageStickman = (ImageView) 
					findViewById(R.id.stickman);

			animDrawableStickman = (AnimationDrawable) animImageStickman.getDrawable();

			// we run the animation (using post)..
			runAnimationViaPost();			

			// we set a listener onto this image and override the onclick behaviour..
			animImageStickman.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {	
					animImageStickman.setImageResource(R.anim.stickman2);
					animDrawableStickman = (AnimationDrawable) animImageStickman.getDrawable();
					
					runAnimationViaPost();		
				}
			}

					);

			////////////////////////////////////////////////////////////////////
			// this is the first way of doing it from the animate book..
			//animDrawableStickman = (AnimationDrawable) animImageStickman.getDrawable();
			//animImageStickman.post(
			//	        new Runnable() {
			//	          public void run() { 
			//	        	  logDebug("starting animation!");
			//	        	  animDrawableStickman.start();
			//	          }
			//	        }
			//	      );			
			////////////////////////////////////////////////////////////////////
			////////////////////////////////////////////////////////////////////
			// this is one way of doing it - from andriod developer.com	
			////////////////////////////////////////////////////////////////////
			//import android.view.MotionEvent;
			//import android.view.View;
			//animImageStickman.setBackgroundResource(R.anim.stickman);
			//
			//animDrawableStickman = (AnimationDrawable) animImageStickman.getBackground();
			//
			//// set the listener onto the view..
			//animImageStickman.setOnClickListener( new OnClickListener() {
			//
			//	@Override
			//	public void onClick(View v) { 
			//		logDebug("onClick..!");
			//		animDrawableStickman.start(); }
			//}
			//);
			//////////////////////////////////////////////////////////////////////
		}catch(Throwable tr) { Log.e(TAG, "Error in onCreate! ",tr);}
	}
	//@Override
	//public void onStart() {
	//	try {
	//		logDebug("in onStart");
	//
	//		super.onStart();	
	//		//setContentView(R.layout.main);
	//
	//		// make an image view for r.id.stickman..
	//		animImageStickman = (ImageView) 
	//				findViewById(R.id.stickman);
	//
	//		
	//		runAnimationViaPost();
	//	}catch(Throwable tr) { Log.e(TAG, "Error in onStart! ",tr);}	
	//}
}
//addListenerOnStickman();




