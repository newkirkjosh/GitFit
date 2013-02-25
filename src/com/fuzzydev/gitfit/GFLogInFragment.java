package com.fuzzydev.gitfit;

import com.fuzzydev.gitfit.GFMainListFragment.Callbacks;


import com.fuzzydev.gitfit.R;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;

public class GFLogInFragment extends Fragment{
	
	Button login;
	Button register;
	View myView;
    private Callbacks callbacks = loginCallbacks;

	
    public interface Callbacks {

        public void onItemSelected(String id);
    }

    private static Callbacks loginCallbacks = new Callbacks() {
    	
        @Override
        public void onItemSelected(String id) {
        	
        }
    };
    
	public static GFLogInFragment newInstance(int someInt) {
		GFLogInFragment myFragment = new GFLogInFragment();

	    Bundle args = new Bundle();
	    myFragment.setArguments(args);

	    return myFragment;
	}
	
	 public GFLogInFragment() {
	    }

	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	    }
	    @Override
	    public View onCreateView(android.view.LayoutInflater inflater, android.view.ViewGroup container, Bundle savedInstanceState) {
		    // Inflate the layout for this fragment
	    	
	        myView = inflater.inflate(R.layout.gflogin_fragment, container, false);    
	        myView.getBackground().setDither(true); 
	        getActivity().getWindow().setFormat(PixelFormat.RGB_565);
	        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DITHER);
//	        getActivity().getWindow().getDecorView().getBackground().setDither(true);
	        
			login = (Button)myView.findViewById(R.id.login);
			login.setEnabled(true);
			login.setClickable(true);
			login.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					callbacks.onItemSelected("login");			
				}
			});
			
			register = (Button)myView.findViewById(R.id.register);
			register.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					callbacks.onItemSelected("register");
				}
			});
			
			return myView;
	    };

	    @Override
	    public void onAttach(Activity activity) {
	        super.onAttach(activity);
	        if (!(activity instanceof Callbacks)) {
	            throw new IllegalStateException("Activity must implement fragment's callbacks.");
	        }

	        callbacks = (Callbacks) activity;
	    }

	    @Override
	    public void onDetach() {
	        super.onDetach();
	        callbacks = loginCallbacks;
	    }
}
