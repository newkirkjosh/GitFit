package com.fuzzydev.gitfit;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.app.ActionBar.OnNavigationListener;
import com.fuzzydev.gitfit.cloud.CloudManager;
import com.fuzzydev.gitfit.structures.User;

public class GFMainListActivity extends SherlockFragmentActivity implements GFMainListFragment.Callbacks {

    private boolean mTwoPane;
	private ViewPager viewPager;
	private static int NUM_VIEWS = 5;
	private Context context;
	private ViewPageAdapter viewPageAdapter;
	private SpinnerAdapter spinAdapter;
	private Handler taskHandler;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_list_activity);
        
        taskHandler = new Handler();
        viewPageAdapter = new ViewPageAdapter();
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(viewPageAdapter); 
        context = this;
        
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
        	User user = new User("Dejan");
        	user.setEmail("dejanr23@gmail.com");
        	user.setID("DejanR23");
        	CloudManager.getInstance(this).registerUser(user);
            Intent detailIntent = new Intent(this, GFWorkoutListActivity.class);
            startActivity(detailIntent);
        }
    }
    
    private Runnable ViewPagerTask = new Runnable() {
    	int i = 0;
    	boolean increment = true;
        public void run() {
        	if(i > 3)increment = false;
        	if(increment)
        		viewPager.setCurrentItem(i++,true);
        	else
        		viewPager.setCurrentItem(i--,true);
        	if(i == 0)increment=true;
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
