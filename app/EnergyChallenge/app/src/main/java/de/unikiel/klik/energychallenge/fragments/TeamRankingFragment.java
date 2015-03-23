package de.unikiel.klik.energychallenge.fragments;

import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import de.unikiel.klik.energychallenge.R;
import de.unikiel.klik.energychallenge.activities.ProfileActivity;
import de.unikiel.klik.energychallenge.adapters.RankingAdapter;
import de.unikiel.klik.energychallenge.tasks.GetTeamRankingTask;
import de.unikiel.klik.energychallenge.utils.NetworkX;

public class TeamRankingFragment extends ListFragment {

    private RankingAdapter rankingAdapter;

    private LinearLayout progressIndicator;

    private TextView emptyListText;

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
        View view = inflater.inflate(R.layout.fragment_list_extended, container, false);

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

        Intent profileIntent = new Intent(getActivity(), ProfileActivity.class);
        profileIntent.putExtra("type", "team");
        profileIntent.putExtra("id", rankingAdapter.getItem(position).getId());
        startActivity(profileIntent);

    }

    private void loadRankingData() {

        Context context = getActivity();

        if (NetworkX.isAvailable(context)) {
            new GetTeamRankingTask(rankingAdapter, progressIndicator, emptyListText).execute();
        } else {
            emptyListText.setText(R.string.no_network_connection);
            Toast.makeText(context, R.string.no_network_connection, Toast.LENGTH_SHORT).show();
        }

    }

}
