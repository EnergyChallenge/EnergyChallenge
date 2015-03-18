package de.unikiel.klik.energychallenge.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.unikiel.klik.energychallenge.R;


/* Fragment for the initial view upon login */
public class MainFragment extends Fragment {

    public MainFragment() {
    }

    /* Returns an new instance of this fragment */
    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Swap in the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
}