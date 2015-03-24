package de.unikiel.klik.energychallenge.fragments;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import de.unikiel.klik.energychallenge.R;
import de.unikiel.klik.energychallenge.adapters.ActivitiesAdapter;
import de.unikiel.klik.energychallenge.dialogs.CompleteActivityDialog;
import de.unikiel.klik.energychallenge.models.ActivitiesItem;
import de.unikiel.klik.energychallenge.tasks.CompleteActivityTask;
import de.unikiel.klik.energychallenge.tasks.GetFavoredActivitiesTask;
import de.unikiel.klik.energychallenge.utils.NetworkX;


/*
 * TODO
 * - Extend Layout with other layout, e.g. for draw a headline
 *   and salutation for the user
 *
 */


public class MainFragment extends ListFragment implements CompleteActivityDialog.DialogListener {

    private ActivitiesAdapter activitiesAdapter;

    private LinearLayout progressIndicator;

    private TextView emptyListText;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MainFragment() {
    }

    /* Returns an new instance of this fragment */
    public static MainFragment newInstance() {
        return new MainFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_extended, container, false);

        progressIndicator = (LinearLayout) view.findViewById(R.id.progress_container_id);
        emptyListText = (TextView) view.findViewById(R.id.empty_id);

        //activitiesAdapter = new ActivitiesAdapter(getActivity()); TODO
        activitiesAdapter = new ActivitiesAdapter(getActivity(), this);

        setListAdapter(activitiesAdapter);

        loadRankingData();

        return view;
    }

    //TODO Rename not Ranking
    private void loadRankingData() {

        Context context = getActivity();

        if (NetworkX.isAvailable(context)) {
            new GetFavoredActivitiesTask(activitiesAdapter, progressIndicator, emptyListText).execute();
        } else {
            emptyListText.setText(R.string.no_network_connection);
            Toast.makeText(context, R.string.no_network_connection, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onDialogPositiveClick(int activityPosition) {

        Context context = getActivity();

        if (NetworkX.isAvailable(context)) {
            ActivitiesItem activity = activitiesAdapter.getItem(activityPosition);
            new CompleteActivityTask(context, activitiesAdapter, activity).execute();
        } else {
            Toast.makeText(context, R.string.no_network_connection, Toast.LENGTH_SHORT).show();
        }

    }

}