package de.unikiel.klik.energychallenge;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/* Fragment for the options menu */
public class OptionsFragment extends Fragment {

    public OptionsFragment() {
    }

    /* Returns an new instance of this fragment */
    public static OptionsFragment newInstance() {
        return new OptionsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Swap in the layout for this fragment
        return inflater.inflate(R.layout.fragment_options, container, false);

        //TODO Alter the layout to the users current settings
    }

    public void logOut() {
        //TODO Logout Action
    }
}