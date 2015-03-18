package de.unikiel.klik.energychallenge.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.unikiel.klik.energychallenge.R;


/* Fragment for the users own profile page */
public class MyProfileFragment extends Fragment {

    public MyProfileFragment() {
    }

    /* Returns an new instance of this fragment */
    public static MyProfileFragment newInstance() {
        return new MyProfileFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Swap in the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_profile, container, false);

        //TODO Receiving the users credentials from the controller
        //TODO Populate the layout with the users credentials

        //TODO Populate the users recent activities
    }
}