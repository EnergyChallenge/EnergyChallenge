package de.unikiel.klik.energychallenge;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/* Fragment for viewing team and user rankings */
public class RankingListFragment extends Fragment {

    private FragmentTabHost rankingsTabHost;

    public RankingListFragment() {
    }

    /* Returns an new instance of this fragment */
    public static RankingListFragment newInstance() {
        return new RankingListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

            //Create the tab host
            rankingsTabHost = new FragmentTabHost(getActivity());
            rankingsTabHost.setup(getActivity(), getChildFragmentManager(), R.id.rankings_tabs);

            //Add the tabs
            rankingsTabHost.addTab(rankingsTabHost.newTabSpec("users").setIndicator("Benutzer"),
                                   UserRankingsTabFragment.class, null);
            rankingsTabHost.addTab(rankingsTabHost.newTabSpec("teams").setIndicator("Teams"),
                                   TeamRankingsTabFragment.class, null);

            //TODO Getting the users and teams and their respective scores from the controller
            //TODO Populate the layout with the user and team rankings

            return rankingsTabHost;
    }

    /* Destroy the tab host when it is no longer needed */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        rankingsTabHost = null;
    }

}
