package de.unikiel.klik.energychallenge;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/* Fragment for the initial view upon login */
public class TeamRankingsTabFragment extends Fragment {

    public TeamRankingsTabFragment(){

    }

    /* Returns an new instance of this fragment */
    public static TeamRankingsTabFragment newInstance() {
        return new TeamRankingsTabFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Swap in the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_team_rankings, container, false);

    }
}