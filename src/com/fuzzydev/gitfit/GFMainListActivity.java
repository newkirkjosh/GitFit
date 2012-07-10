package com.fuzzydev.gitfit;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

public class GFMainListActivity extends FragmentActivity
        implements GFMainListFragment.Callbacks {

    private boolean mTwoPane;
	private ViewPager viewPager;
	private static int NUM_AWESOME_VIEWS = 5;
	private Context cxt;
	private ViewPageAdapter viewPageAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_list_activity);
        cxt = this;
        
        viewPageAdapter = new ViewPageAdapter();
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(viewPageAdapter);

        if (findViewById(R.id.item_detail_container) != null) {
            mTwoPane = true;
            ((GFMainListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.item_list))
                    .setActivateOnItemClick(true);
        }
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

	private class ViewPageAdapter extends PagerAdapter{
	
		
		@Override
		public int getCount() {
			return NUM_AWESOME_VIEWS;
		}
	
		@Override
		public Object instantiateItem(View collection, int position) {
			ImageView img = new ImageView(cxt);
			img.setBackgroundColor(Color.BLACK);
			img.setImageResource(R.drawable.test);
			((ViewPager) collection).addView(img,0);
			
			return img;
		}
	
		@Override
		public void destroyItem(View collection, int position, Object view) {
			((ViewPager) collection).removeView((ImageView) view);
		}
	
		
		
		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view==((ImageView)object);
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
