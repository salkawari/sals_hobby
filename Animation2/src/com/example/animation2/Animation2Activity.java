package com.example.animation2;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.os.Bundle;

public class Animation2Activity extends Activity {

	// constants..
	private static final String TAG =Animation2Activity.class.getSimpleName();
	private static final boolean IS_DEBUG = true;

	// these are used in the different methods..
	ImageView animImageStickman;
	AnimationDrawable animDrawableStickman;	

	// these are used to work out direction..
	float xStartPos, xEndPos, yStartPos, yEndPos;
	boolean bool_is_up, bool_is_down;

	// this is the lister that will be stuck onto animImageStickman..
	OnClickListener OnClickStickman;

	//useful logging method..
	private void logDebug(String log_message) {
		if (IS_DEBUG) { Log.d(TAG, log_message); } 
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

			//animDrawableStickman = (AnimationDrawable) animImageStickman.getDrawable();

			// we run the animation (using post)..
			runAnimationViaPost();			

			// we set a listener onto this image and override the onclick behaviour..
			animImageStickman.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {	
					if ( bool_is_up ){animImageStickman.setImageResource(R.anim.stickman_pullup);}
					else if (bool_is_down){animImageStickman.setImageResource(R.anim.stickman_pulldown);}
					else {animImageStickman.setImageResource(R.anim.stickman_nopull);}
					
					//animImageStickman.setLayoutParams(params) onLayout 
					
					animDrawableStickman = (AnimationDrawable) animImageStickman.getDrawable();
					Log.w(TAG,"SHORT CLICK! bool_is_up="+bool_is_up+", bool_is_down="+bool_is_down);
					//animDrawableStickman = (AnimationDrawable) animImageStickman.getDrawable();
					runAnimationViaPost();		
				}
			}

					);


			animImageStickman.setOnTouchListener(new OnTouchListener()
			{

				@Override
				public boolean onTouch(View v, MotionEvent event)
				{
					//Log.d(TAG,"START "+ "action id="+event.getAction() +"x="+event.getX()+", y="+event.getY());
					switch (event.getAction())
					{ 
					// this is called when the screen is touched
					case MotionEvent.ACTION_DOWN:		    	  
						xStartPos = event.getX();
						yStartPos = event.getY();
						Log.d(TAG,"START ("+xStartPos+", "+yStartPos+")");
						break;

						//case MotionEvent.ACTION_MOVE:
						//	Log.d(TAG,"ACTION_MOVE "+event.getAction());
						//	break;

						// this is called when the touch ends
					case MotionEvent.ACTION_UP:
						xEndPos = event.getX();
						yEndPos = event.getY();
						Log.d(TAG,"ACTION UP END ("+xEndPos+", "+yEndPos+")");
						Log.d(TAG,"diff in y = "+(yEndPos - yStartPos) );
						if (yStartPos > yEndPos) {bool_is_down = false; bool_is_up = true;}
						else if (yStartPos < yEndPos) {bool_is_down = true; bool_is_up = false;}
						else {bool_is_down = false; bool_is_up = false;}												
						break;

						//      case MotionEvent.ACTION_CANCEL:
						//  	  Log.d(TAG,"ACTION_CANCEL "+event.getAction());
						//  	  break;
					default:		        
						//Log.d(TAG,"other = "+event.getAction());
					}
					//Log.d(TAG,"END "+ "action id="+event.getAction() +"x="+event.getX()+", y="+event.getY());
					return false;
				}
			});



		}catch(Throwable tr) { Log.e(TAG, "Error in onCreate! ",tr);}
	}
	////////////////////////////////////////////
	/*	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		Log.d(TAG,"dispatchTouchEvent action id="+event.getAction());
		return false;
	} */

	// this is an override..	
	/*	@Override
	public boolean onTouchEvent(MotionEvent event)
	{

		//Log.d(TAG,"START "+ "action id="+event.getAction() +"x="+event.getX()+", y="+event.getY());
		switch (event.getAction())
		{ 
		// this is called when the screen is touched
		case MotionEvent.ACTION_DOWN:		    	  
			xStartPos = event.getX();
			yStartPos = event.getY();
			Log.d(TAG,"START ("+xStartPos+", "+yStartPos+")");
			break;

			//  case MotionEvent.ACTION_MOVE:
			// 	  Log.d(TAG,"ACTION_MOVE "+event.getAction());
			//	  break;

			// this is called when the touch ends
		case MotionEvent.ACTION_UP:
			xEndPos = event.getX();
			yEndPos = event.getY();
			Log.d(TAG,"END ("+xEndPos+", "+yEndPos+")");
			//break;

			//      case MotionEvent.ACTION_CANCEL:
			//  	  Log.d(TAG,"ACTION_CANCEL "+event.getAction());
			//  	  break;
			default:		        
			  	  Log.d(TAG,"other = "+event.getAction());
		}
		Log.d(TAG,"END "+ "action id="+event.getAction() +"x="+event.getX()+", y="+event.getY());
		Log.d(TAG,"diff in y = "+(yEndPos - yStartPos) );
		if (yStartPos > yEndPos) {bool_is_down = false; bool_is_up = true;}
		else if (yStartPos < yEndPos) {bool_is_down = true; bool_is_up = false;}
		else {bool_is_down = false; bool_is_up = false;}
		return super.onTouchEvent(event);
	}*/

	////////////////////////////////////////////

	// this method causes the animation to run..
	private void runAnimationViaPost() {
		boolean bool_result = false;
		//		animImageStickman.postInvalidate();
				animDrawableStickman = (AnimationDrawable) animImageStickman.getDrawable();
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
	////////////////////////////////////////////


}




