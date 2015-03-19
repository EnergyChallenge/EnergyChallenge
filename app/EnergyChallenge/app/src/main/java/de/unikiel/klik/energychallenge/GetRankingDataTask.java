package de.unikiel.klik.energychallenge;

import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import de.unikiel.klik.energychallenge.utils.ServerRequest;

/**
 *
 * ***************************************************
 *
 *  !!!!!!!!!!!!!!DEPRECATED!!!!!!!!!!!!!!!!!!!!!!!!1
 *
 *
 *
 *          Deprectaed! Do not use!
 *
 *
 * Delete later
 *
 *
 */



public class GetRankingDataTask extends GetDataFromServerTask {

    private List<String> rankingTeamData = new ArrayList<>();

    private ArrayAdapter rankingTeamAdapter;

    private LinearLayout linlaHeaderProgress;

    public GetRankingDataTask(List<String> rankingTeamData, ArrayAdapter rankingTeamAdapter,
                              LinearLayout linlaHeaderProgress) {
        this.rankingTeamData = rankingTeamData;
        this.rankingTeamAdapter = rankingTeamAdapter;
        this.linlaHeaderProgress = linlaHeaderProgress;
    }


    @Override
    protected ServerRequest createServerRequest() {
        return new ServerRequest("getRankingTeams");
    }

    @Override
    protected void handleServerResponse(JSONObject response) {

        rankingTeamData.clear();

        try {
            JSONArray ranking = response.getJSONArray("ranking");

            for(int i = 0; i < ranking.length(); i++) {
                rankingTeamData.add(ranking.getJSONObject(i).getString("name"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        rankingTeamAdapter.notifyDataSetChanged();
    }

    @Override
    protected void doBeforeRequest() {
        Log.v("Task", "Show LoadingIdicator now!"); //TODO Remove
        linlaHeaderProgress.setVisibility(View.VISIBLE);
    }

    @Override
    protected void doAfterResponse() {
        Log.v("Task", "Hide LoadingIdicator now!"); //TODO Remove
        linlaHeaderProgress.setVisibility(View.GONE);
    }

}
