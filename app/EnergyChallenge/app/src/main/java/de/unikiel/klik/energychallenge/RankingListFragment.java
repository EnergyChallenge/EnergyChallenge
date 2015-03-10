package de.unikiel.klik.energychallenge;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/* Fragment for viewing team and user rankings */
public class RankingListFragment extends Fragment {

    public RankingListFragment() {
    }

    /* Returns an new instance of this fragment */
    public static RankingListFragment newInstance() {
        return new RankingListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Swap in the layout for this fragment
        return inflater.inflate(R.layout.fragment_ranking_list, container, false);

        //TODO Getting the users and teams and their respective scores from the controller
        //TODO Populate the layout with the user and team rankings
    }
}
