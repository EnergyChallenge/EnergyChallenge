package de.unikiel.klik.energychallenge.tasks;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.unikiel.klik.energychallenge.R;
import de.unikiel.klik.energychallenge.adapters.RankingAdapter;
import de.unikiel.klik.energychallenge.models.RankingItem;
import de.unikiel.klik.energychallenge.utils.ServerRequest;

public class PerformSearchTask extends AccessServerTask {

    private ArrayAdapter searchAdapter; //TODO Change to other adapter

    private LinearLayout progressIndicator;

    private TextView emptyListText;

    public PerformSearchTask(ArrayAdapter searchAdapter,
                              LinearLayout progressIndicator,
                              TextView emptyListText) {
        this.searchAdapter = searchAdapter;
        this.progressIndicator = progressIndicator;
        this.emptyListText = emptyListText;
    }

    @Override
    protected ServerRequest createServerRequest() {
        return new ServerRequest("getUserRanking");
    }

    @Override
    protected void handleServerResponse(JSONObject response) throws JSONException {

        searchAdapter.clear();

        JSONArray ranking = response.getJSONArray("ranking");

        for(int i = 0; i < ranking.length(); i++) {
            int id = ranking.getJSONObject(i).getInt("id");
            String title = ranking.getJSONObject(i).getString("title");

            searchAdapter.add(title);
        }

    }

    @Override
    protected void handleResponseError() {
        emptyListText.setText(R.string.error_in_server_communication);
    }

    @Override
    protected void doBeforeRequest() {
        // Show Loading Indicator
        progressIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    protected void doAfterResponse() {
        // Hide Loading Indicator
        progressIndicator.setVisibility(View.GONE);
    }
}
