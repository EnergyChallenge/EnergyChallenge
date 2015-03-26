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
import de.unikiel.klik.energychallenge.tasks.GetActivitiesTask;
import de.unikiel.klik.energychallenge.utils.NetworkX;

public class ActivitiesFragment extends ListFragment implements CompleteActivityDialog.DialogListener {

    private ActivitiesAdapter activitiesAdapter;

    private LinearLayout progressIndicator;

    private TextView emptyListText;

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
        View view = inflater.inflate(R.layout.fragment_list_extended, container, false);

        progressIndicator = (LinearLayout) view.findViewById(R.id.progress_container_id);
        emptyListText = (TextView) view.findViewById(R.id.empty_id);

        activitiesAdapter = new ActivitiesAdapter(getActivity(), this);

        setListAdapter(activitiesAdapter);

        loadActivityData();

        return view;
    }

    private void loadActivityData() {

        Context context = getActivity();

        if (NetworkX.isAvailable(context)) {
            new GetActivitiesTask(context, activitiesAdapter, progressIndicator, emptyListText).execute();
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
