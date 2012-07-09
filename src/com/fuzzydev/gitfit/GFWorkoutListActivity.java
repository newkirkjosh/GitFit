package com.fuzzydev.gitfit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;

public class GFWorkoutListActivity extends FragmentActivity 
implements GFWorkoutListFragment.Callbacks{

	private boolean mTwoPane;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gfworkout_list_layout);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            GFWorkoutListFragment fragment = new GFWorkoutListFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpTo(this, new Intent(this, GFMainListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
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
        	Log.v("ID: ",id);
            //Intent detailIntent = new Intent(this, GFWorkoutListActivity.class);
            //startActivity(detailIntent);
        }
    }
}
