package com.fuzzydev.gitfit;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class GFMainListActivity extends FragmentActivity
        implements GFMainListFragment.Callbacks {

    private boolean mTwoPane;
	private ViewPager viewPager;
	private static int NUM_VIEWS = 5;
	private Context context;
	private ViewPageAdapter viewPageAdapter;
	private Handler taskHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_list_activity);
        
        context = this;
        taskHandler = new Handler();  
        viewPageAdapter = new ViewPageAdapter();
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(viewPageAdapter);
        
        if (findViewById(R.id.item_detail_container) != null) {
            mTwoPane = true;
            ((GFMainListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.item_list))
                    .setActivateOnItemClick(true);
        }
       
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(4000);
                        taskHandler.postDelayed(ViewPagerTask, 2000);
                    } catch (Exception e) {
                    	e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    
    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            GFWorkoutListFragment fragment = new GFWorkoutListFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit();

        } else {
            Intent detailIntent = new Intent(this, GFWorkoutListActivity.class);
            startActivity(detailIntent);
        }
    }
    
    private Runnable ViewPagerTask = new Runnable() {
    	int i = 0;
        public void run() {
        	if(i == 4)i=0;
        	viewPager.setCurrentItem(i++,true);
        }
    };

	private class ViewPageAdapter extends PagerAdapter{
	
		@Override
		public int getCount() {
			return NUM_VIEWS;
		}
	
		@Override
		public Object instantiateItem(View collection, int position) {
			GFNotificationView view = new GFNotificationView(context,R.drawable.test,"TEST MESSAGE");
			((ViewPager) collection).addView(view,0);
			
			return view;
		}
	
		@Override
		public void destroyItem(View collection, int position, Object view) {
			((ViewPager) collection).removeView((GFNotificationView) view);
		}
		
		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view==((GFNotificationView)object);
		}
		
		@Override
		public void finishUpdate(View arg0) {}
		
		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {}
	
		@Override
		public Parcelable saveState() {
			return null;
		}
	
		@Override
		public void startUpdate(View arg0) {}
		
	}

}
