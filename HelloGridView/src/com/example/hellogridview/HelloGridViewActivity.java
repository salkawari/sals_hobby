package com.example.hellogridview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;
import android.view.Gravity;
import android.view.View;


public class HelloGridViewActivity extends Activity {
    /** Called when the activity is first created. */
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));
        
        
        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            	//Toast.makeText(HelloGridViewActivity.this, "x " + position, Toast.LENGTH_SHORT).show();
            	Toast toast = Toast.makeText(HelloGridViewActivity.this, "x " + position, Toast.LENGTH_SHORT);

            	toast.setGravity(Gravity.CENTER_VERTICAL, 10, 10);
            	toast.show();
            	
            	//Toast toast = new Toast(HelloGridViewActivity.this);
            	
            	//toast.makeText(HelloGridViewActivity.this, "x " + position, Toast.LENGTH_SHORT);

//            	toast.setDuration(Toast.LENGTH_SHORT);
//            	toast.setView(layout);
//            	toast.makeText(HelloGridViewActivity.this, 'a', toast.LENGTH_SHORT);
//            	toast.makeText(HelloGridViewActivity.this, "text", Toast.LENGTH_SHORT);
//            	toast.makeText(HelloGridViewActivity.this, "y " + position, toast.LENGTH_SHORT).show();
            	

            	
            }
        });
    }
}