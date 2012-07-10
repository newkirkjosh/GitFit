package com.fuzzydev.gitfit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import com.actionbarsherlock.app.ActionBar.OnNavigationListener;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;

public class GFMainListActivity extends SherlockFragmentActivity implements GFMainListFragment.Callbacks {

    private boolean mTwoPane;
	private ViewPager viewPager;
	private static int NUM_AWESOME_VIEWS = 5;
	private Context cxt;
	private ViewPageAdapter viewPageAdapter;
	private SpinnerAdapter spinAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_list_activity);
        cxt = this;
        
        // This needs to change to a static list gets pulled down
        // so that the item that is currently selected doesn't actually show up on the list
        spinAdapter = new ArrayAdapter<String>(GFMainListActivity.this, 
        		android.R.layout.simple_spinner_dropdown_item,
        		new String[] { "GitFit Workouts", "My Workouts", "Progress", "Git Running" });
        
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setNavigationMode(com.actionbarsherlock.app.ActionBar.NAVIGATION_MODE_LIST);
        getSupportActionBar().setListNavigationCallbacks(spinAdapter, new OnNavigationListener() {
			
			@Override
			public boolean onNavigationItemSelected(int itemPosition, long itemId) {
				// Changing of fragment implementation goes here
				return false;
			}
		});
        
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
    public boolean onPrepareOptionsMenu(Menu menu) {
    	getSupportMenuInflater().inflate(R.menu.abs_items, menu);
    	return super.onPrepareOptionsMenu(menu);
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
			GFNotificationView view = new GFNotificationView(cxt,R.drawable.test,"TEST MESSAGE");
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
