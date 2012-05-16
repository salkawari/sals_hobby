package com.example.animation2;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;
import android.os.Bundle;

public class Animation2Activity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
           final ImageView animImage = (ImageView) 
           findViewById(R.id.stickman);
           final AnimationDrawable animDrawable = 
           (AnimationDrawable) animImage.getDrawable();
           animImage.post(
        	        new Runnable() {
        	          public void run() { 
        	            animDrawable.start();
        	          }
        	        }
        	      );
        	    }
        	  }    
/*           
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}
           
           */