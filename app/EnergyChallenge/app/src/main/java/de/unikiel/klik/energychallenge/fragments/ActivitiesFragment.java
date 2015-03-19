package de.unikiel.klik.energychallenge.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import de.unikiel.klik.energychallenge.R;

import de.unikiel.klik.energychallenge.activities.ProfileActivity;
import de.unikiel.klik.energychallenge.adapters.ActivitiesAdapter;
import de.unikiel.klik.energychallenge.adapters.RankingAdapter;
import de.unikiel.klik.energychallenge.tasks.GetActivitiesTask;
import de.unikiel.klik.energychallenge.tasks.GetUserRankingTask;
import de.unikiel.klik.energychallenge.utils.NetworkX;




/**
 * A fragment representing a list of Items.
 * <p/>
 * <p/>
 * Activities containing this fragment MUST implement the {@link }
 * interface.
 */
public class ActivitiesFragment extends ListFragment {

   ActivitiesAdapter activitiesAdapter;

    LinearLayout progressIndicator;

    TextView emptyListText;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ActivitiesFragment() {
    }

    /* Returns an new instance of this fragment */
    public static ActivitiesFragment newInstance() {
        return new ActivitiesFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //TODO Replace by activit fragment layout or combine list fragments
        View view = inflater.inflate(R.layout.fragment_ranking, container, false);

        progressIndicator = (LinearLayout) view.findViewById(R.id.progress_container_id);
        emptyListText = (TextView) view.findViewById(R.id.empty_id);

        activitiesAdapter = new ActivitiesAdapter(getActivity());

        setListAdapter(activitiesAdapter);

        loadRankingData();

        return view;
    }

    private void loadRankingData() {

        Context context = getActivity();

        if (NetworkX.isAvailable(context)) {
            new GetActivitiesTask(activitiesAdapter, progressIndicator, emptyListText).execute();
        } else {
            emptyListText.setText(R.string.no_network_connection);
            Toast.makeText(context, R.string.no_network_connection, Toast.LENGTH_SHORT).show();
        }

    }

}
