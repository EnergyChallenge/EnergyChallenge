package de.unikiel.klik.energychallenge;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/* Fragment for viewing activities */
public class ActivitiesFragment extends Fragment {

    public ActivitiesFragment() {
    }

    /* Returns an new instance of this fragment */
    public static ActivitiesFragment newInstance() {
        return new ActivitiesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Swap in the layout for this fragment
        return inflater.inflate(R.layout.fragment_activities, container, false);

        //TODO Receiving activities from the controller
        //TODO Populating the layout with the activities
    }
}
