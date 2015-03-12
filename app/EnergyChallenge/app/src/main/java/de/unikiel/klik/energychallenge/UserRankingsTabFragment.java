package de.unikiel.klik.energychallenge;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/* Fragment for the initial view upon login */
public class UserRankingsTabFragment extends Fragment {

    public UserRankingsTabFragment(){

    }

    /* Returns an new instance of this fragment */
    public static UserRankingsTabFragment newInstance() {
        return new UserRankingsTabFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Swap in the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_user_rankings, container, false);

    }
}