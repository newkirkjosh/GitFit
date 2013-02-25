package com.fuzzydev.gitfit;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.fuzzydev.gitfit.GFLogInFragment.Callbacks;

public class GFLogInActivity extends Activity implements Callbacks{

	Button login;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gflogin_layout);
		FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		    
		GFLogInFragment frag = GFLogInFragment.newInstance(0);
		transaction.replace(R.id.container,frag);
		transaction.addToBackStack(null);
		transaction.commit();			
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	public void onItemSelected(String id) {
		//Callback from GFLoginFragment
		
		if(id.equals("login")){
			startActivity(new Intent(GFLogInActivity.this,GFMainListActivity.class));
		}
		else if(id.equals("register")){
			    FragmentManager manager = getFragmentManager();
			    FragmentTransaction transaction = manager.beginTransaction();
			    
			    GFRegistrationFragment frag = GFRegistrationFragment.newInstance(0);
			    transaction.replace(R.id.container,frag);
			    transaction.addToBackStack(null);
			    transaction.commit();			
		}
	}
}
