package com.fuzzydev.gitfit;

import com.fuzzydev.gitfit.GFMainListFragment.Callbacks;


import com.fuzzydev.gitfit.R;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

public class GFLogInFragment extends Fragment{
	
	
	 public GFLogInFragment() {
	    }

	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);

	    }

	    @Override
	    public View onCreateView(android.view.LayoutInflater inflater, android.view.ViewGroup container, Bundle savedInstanceState) {
		    // Inflate the layout for this fragment
	        return inflater.inflate(R.layout.gflogin_layout, container, false);
	    };

	    @Override
	    public void onAttach(Activity activity) {
	        super.onAttach(activity);

	    }

	    @Override
	    public void onDetach() {
	        super.onDetach();
	    }
}
