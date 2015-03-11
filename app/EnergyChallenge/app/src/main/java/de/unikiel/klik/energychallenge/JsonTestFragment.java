package de.unikiel.klik.energychallenge;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class JsonTestFragment extends Fragment {

    private List<String> rankingTeamData = new ArrayList<>();

    private ArrayAdapter rankingTeamAdapter;

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
        rankingTeamAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, rankingTeamData);
        list.setAdapter(rankingTeamAdapter);

        //Fill List with Data
        fillTeamRankingList();


        return view;
    }

    private void fillTeamRankingList() {

        Context context = getActivity().getApplicationContext();


        ConnectivityManager connMgr = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new GetRankingDataTask(rankingTeamData, rankingTeamAdapter).execute();
        } else {
            Toast.makeText(context, "No network connection available.", Toast.LENGTH_SHORT).show();
        }

    }
}