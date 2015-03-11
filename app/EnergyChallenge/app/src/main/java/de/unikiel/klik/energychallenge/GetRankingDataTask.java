package de.unikiel.klik.energychallenge;

import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import de.unikiel.klik.energychallenge.utils.ServerRequest;

public class GetRankingDataTask extends GetDataFromServerTask {

    private List<String> rankingTeamData = new ArrayList<>();

    private ArrayAdapter rankingTeamAdapter;

    public GetRankingDataTask(List<String> rankingTeamData, ArrayAdapter rankingTeamAdapter) {
        this.rankingTeamData = rankingTeamData;
        this.rankingTeamAdapter = rankingTeamAdapter;
    }


    @Override
    protected ServerRequest createServerRequest() {
        return new ServerRequest("getRankingTeams");
    }

    @Override
    protected void handleServerResponse(JSONObject response) {

        try {
            JSONArray ranking = response.getJSONArray("Ranking");

            for(int i = 0; i < ranking.length(); i++) {
                rankingTeamData.add(ranking.getJSONObject(i).getString("Name"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        rankingTeamAdapter.notifyDataSetChanged();
    }

}
