package com.fuzzydev.gitfit;

import android.R;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class GFWorkoutListFragment extends ListFragment {

    public static final String ARG_ITEM_ID = "item_id";
    ListView workoutList;
    public GFWorkoutListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(getActivity(),R.layout.simple_list_item_activated_1,R.id.text1,new String [] {"Plyometrics","Cardio","Legs","Arms","Chest","Back","Abs","Shoulder"}));

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);
        
    }

}
