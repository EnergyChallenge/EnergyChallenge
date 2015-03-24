package de.unikiel.klik.energychallenge.tasks;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.unikiel.klik.energychallenge.R;
import de.unikiel.klik.energychallenge.adapters.SearchResultAdapter;
import de.unikiel.klik.energychallenge.models.SearchResultItem;
import de.unikiel.klik.energychallenge.utils.ServerRequest;

public class PerformSearchTask extends AccessServerTask {

    private SearchResultAdapter searchAdapter;

    private LinearLayout progressIndicator;

    private TextView emptyListText;

    public PerformSearchTask(SearchResultAdapter searchAdapter,
                              LinearLayout progressIndicator,
                              TextView emptyListText) {
        this.searchAdapter = searchAdapter;
        this.progressIndicator = progressIndicator;
        this.emptyListText = emptyListText;
    }

    @Override
    protected ServerRequest createServerRequest() {
        return new ServerRequest("performSearch");
    }

    @Override
    protected void handleServerResponse(JSONObject response) throws JSONException {

        searchAdapter.clear();

        JSONArray searchResult = response.getJSONArray("searchResult");

        for(int i = 0; i < searchResult.length(); i++) {
            int id = searchResult.getJSONObject(i).getInt("id");
            String name = searchResult.getJSONObject(i).getString("name");
            String type = searchResult.getJSONObject(i).getString("type");

            searchAdapter.add(new SearchResultItem(id, name, type));
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
