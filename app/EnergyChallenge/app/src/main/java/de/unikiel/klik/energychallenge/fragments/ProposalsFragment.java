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
import de.unikiel.klik.energychallenge.activities.ProposalActivity;
import de.unikiel.klik.energychallenge.activities.SearchActivity;
import de.unikiel.klik.energychallenge.adapters.ProposalsAdapter;
import de.unikiel.klik.energychallenge.models.Proposal;
import de.unikiel.klik.energychallenge.tasks.GetProposalsTask;
import de.unikiel.klik.energychallenge.utils.NetworkX;




/**
 * A fragment representing a list of Items.
 */
public class ProposalsFragment extends ListFragment {

    private ProposalsAdapter proposalsAdapter;

    private LinearLayout progressIndicator;

    private TextView emptyListText;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ProposalsFragment() {
    }

    /* Returns an new instance of this fragment */
    public static ProposalsFragment newInstance() {
        return new ProposalsFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_extended, container, false);

        progressIndicator = (LinearLayout) view.findViewById(R.id.progress_container_id);
        emptyListText = (TextView) view.findViewById(R.id.empty_id);

        proposalsAdapter = new ProposalsAdapter(getActivity());

        setListAdapter(proposalsAdapter);

        loadRankingData();

        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent proposalIntent = new Intent(getActivity(), ProposalActivity.class);
        proposalIntent.putExtra("proposal", proposalsAdapter.getItem(position));
        startActivity(proposalIntent);

    }

    private void loadRankingData() {

        Context context = getActivity();

        if (NetworkX.isAvailable(context)) {
            new GetProposalsTask(proposalsAdapter, progressIndicator, emptyListText).execute();
        } else {
            emptyListText.setText(R.string.no_network_connection);
            Toast.makeText(context, R.string.no_network_connection, Toast.LENGTH_SHORT).show();
        }

    }

}
