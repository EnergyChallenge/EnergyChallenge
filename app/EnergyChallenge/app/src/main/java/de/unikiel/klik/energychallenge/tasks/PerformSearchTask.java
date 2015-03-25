package de.unikiel.klik.energychallenge.tasks;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import de.unikiel.klik.energychallenge.R;
import de.unikiel.klik.energychallenge.adapters.SearchResultAdapter;
import de.unikiel.klik.energychallenge.models.SearchResultItem;
import de.unikiel.klik.energychallenge.utils.ServerRequest;

public class PerformSearchTask extends AccessServerTask {

    private String searchQuery;

    private SearchResultAdapter searchAdapter;

    private LinearLayout progressIndicator;

    private TextView emptyListText;

    public PerformSearchTask(Context applicationContext,
                             String searchQuery,
                             SearchResultAdapter searchAdapter,
                              LinearLayout progressIndicator,
                              TextView emptyListText) {
        super(applicationContext);
        this.searchQuery = searchQuery;
        this.searchAdapter = searchAdapter;
        this.progressIndicator = progressIndicator;
        this.emptyListText = emptyListText;
    }

    @Override
    protected ServerRequest createServerRequest() {
        BasicNameValuePair[] parameters = {new BasicNameValuePair("query", searchQuery)};
        //BasicNameValuePair[] parameters = {new BasicNameValuePair("query","Max")};//TODO
        return new ServerRequest("search", new ArrayList<BasicNameValuePair>(Arrays.asList(parameters)));
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
