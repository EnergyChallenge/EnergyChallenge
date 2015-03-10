package de.unikiel.klik.energychallenge;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/* Fragment for viewing and making activity proposals */
public class ProposalsFragment extends Fragment {

    public ProposalsFragment() {
    }

    /* Returns an new instance of this fragment */
    public static ProposalsFragment newInstance() {
        return new ProposalsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Swap in the layout for this fragment
        return inflater.inflate(R.layout.fragment_proposals, container, false);

        //TODO Getting the proposals from the controller
        //TODO Populating the layout with the proposals
    }
}