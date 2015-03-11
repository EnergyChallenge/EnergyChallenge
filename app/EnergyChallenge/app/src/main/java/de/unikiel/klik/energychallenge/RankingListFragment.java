package de.unikiel.klik.energychallenge;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;


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

        //Create the tabhost
        TabHost rankingsTabHost;
        rankingsTabHost = (TabHost) container.findViewById(R.id.rankings_tabhost);

        //Set the name and behaviours of the tabs
        TabSpec tabSpec1 = rankingsTabHost.newTabSpec("firstTab");
        tabSpec1.setIndicator("Benutzer");
        tabSpec1.setContent(new Intent(this.getActivity(), UserRankingsTab.class));

        TabSpec tabSpec2 = rankingsTabHost.newTabSpec("secondTab");
        tabSpec2.setIndicator("Teams");
        tabSpec2.setContent(new Intent(this.getActivity(), TeamRankingsTab.class));

        //Add the tabs to the tabhost
        rankingsTabHost.addTab(tabSpec1);
        rankingsTabHost.addTab(tabSpec2);

        //Swap in the layout for this fragment
        return inflater.inflate(R.layout.fragment_ranking_list, container, false);

        //TODO Getting the users and teams and their respective scores from the controller
        //TODO Populate the layout with the user and team rankings
    }
}
