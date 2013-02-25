package com.fuzzydev.gitfit;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GFRegistrationFragment extends Fragment {

	View myView;
	
	public GFRegistrationFragment(){};
	
	public static GFRegistrationFragment newInstance(int someInt) {
		GFRegistrationFragment myFragment = new GFRegistrationFragment();

	    Bundle args = new Bundle();
	    myFragment.setArguments(args);

	    return myFragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
        myView = inflater.inflate(R.layout.gfregistration_fragment, container, false);

        return myView;
	}

	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}
}
