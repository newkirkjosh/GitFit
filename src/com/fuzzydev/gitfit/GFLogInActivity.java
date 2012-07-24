package com.fuzzydev.gitfit;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
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
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gflogin_layout);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	public void onItemSelected(String id) {
		//Callback from GFLoginFragment
		
		if(id.equalsIgnoreCase("login")){
			startActivity(new Intent(GFLogInActivity.this,GFMainListActivity.class));
		}
		else if(id.equalsIgnoreCase("register")){
			
		}
	}
}
