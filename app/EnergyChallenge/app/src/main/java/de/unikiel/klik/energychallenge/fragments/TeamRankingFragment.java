package de.unikiel.klik.energychallenge.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import de.unikiel.klik.energychallenge.GetRankingDataTask;
import de.unikiel.klik.energychallenge.R;

import de.unikiel.klik.energychallenge.activities.SearchActivity;
import de.unikiel.klik.energychallenge.adapters.RankingAdapter;
import de.unikiel.klik.energychallenge.fragments.dummy.DummyContent;
import de.unikiel.klik.energychallenge.tasks.GetUserRankingTask;
import de.unikiel.klik.energychallenge.utils.NetworkX;

/**
 * A fragment representing a list of Items.
 * <p/>
 * <p/>
 * Activities containing this fragment MUST implement the {@link }
 * interface.
 */
public class TeamRankingFragment extends ListFragment {

    RankingAdapter rankingAdapter;

    LinearLayout progressIndicator;

    TextView emptyListText;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TeamRankingFragment() {
    }

    /* Returns an new instance of this fragment */
    public static TeamRankingFragment newInstance() {
        return new TeamRankingFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //View view = super.onCreateView(inflater, container, savedInstanceState); //TODO remove this
        View view = inflater.inflate(R.layout.fragment_ranking, container, false);

        progressIndicator = (LinearLayout) view.findViewById(R.id.progress_container_id);
        emptyListText = (TextView) view.findViewById(R.id.empty_id);

        rankingAdapter = new RankingAdapter(getActivity());

        setListAdapter(rankingAdapter);

        loadRankingData();

        return view;
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        //TODO Start ProfilActivity
        Intent searchIntent = new Intent(getActivity(), SearchActivity.class);
        startActivity(searchIntent);

    }

    private void loadRankingData() {

        Context context = getActivity();

        if (NetworkX.isAvailable(context)) {
            //TODO CHnage to TEam
            new GetUserRankingTask(rankingAdapter, progressIndicator, emptyListText).execute();
        } else {
            emptyListText.setText(R.string.no_network_connection);
            Toast.makeText(context, R.string.no_network_connection, Toast.LENGTH_SHORT).show();
        }

    }

}
