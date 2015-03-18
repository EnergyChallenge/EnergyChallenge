package de.unikiel.klik.energychallenge;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.unikiel.klik.energychallenge.utils.NetworkX;


public class JsonTestFragment extends Fragment {

    private List<String> rankingTeamData = new ArrayList<>();

    private ArrayAdapter rankingTeamAdapter;

    private LinearLayout linlaHeaderProgress;

    public JsonTestFragment() {
    }

    /* Returns an new instance of this fragment */
    public static JsonTestFragment newInstance() {
        return new JsonTestFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Context context = getActivity().getApplicationContext();


        //Swap in the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_json_test, container, false);


        //Setting Adapter for List
        ListView list = (ListView) view.findViewById(R.id.ranking_team_list);
        rankingTeamAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, rankingTeamData);
        list.setAdapter(rankingTeamAdapter);

        //Setting up LoadingCircleImage
        linlaHeaderProgress = (LinearLayout) view.findViewById(R.id.linlaHeaderProgress);

        //Fill List with Data
        fillTeamRankingList();

        // Setting Listeners
        Button button = (Button) view.findViewById(R.id.button_refresh);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               fillTeamRankingList();
            }
        });

        return view;
    }

    private void fillTeamRankingList() {

        Context context = getActivity();

        if (NetworkX.isAvailable(context)) {
            new GetRankingDataTask(rankingTeamData,
                                    rankingTeamAdapter,
                                    linlaHeaderProgress).execute();
        } else {
            Toast.makeText(context, "No network connection available.", Toast.LENGTH_SHORT).show();
        }

    }








}