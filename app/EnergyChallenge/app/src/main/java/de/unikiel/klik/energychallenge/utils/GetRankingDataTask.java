package de.unikiel.klik.energychallenge.utils;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
