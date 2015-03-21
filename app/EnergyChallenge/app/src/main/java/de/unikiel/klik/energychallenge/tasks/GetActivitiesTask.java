package de.unikiel.klik.energychallenge.tasks;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.unikiel.klik.energychallenge.R;
import de.unikiel.klik.energychallenge.adapters.ActivitiesAdapter;
import de.unikiel.klik.energychallenge.adapters.RankingAdapter;
import de.unikiel.klik.energychallenge.models.ActivitiesItem;
import de.unikiel.klik.energychallenge.models.RankingItem;
import de.unikiel.klik.energychallenge.utils.ServerRequest;

public class GetActivitiesTask extends AccessServerTask {

    private ActivitiesAdapter activitiesAdapter;

    private LinearLayout progressIndicator;

    private TextView emptyListText;

    public GetActivitiesTask(ActivitiesAdapter activitiesAdapter,
                              LinearLayout progressIndicator,
                              TextView emptyListText) {
        this.activitiesAdapter = activitiesAdapter;
        this.progressIndicator = progressIndicator;
        this.emptyListText = emptyListText;
    }

    @Override
    protected ServerRequest createServerRequest() {
        return new ServerRequest("getActivities"); //TODO Maybe called getAllActivities?
    }

    @Override
    protected void handleServerResponse(JSONObject response) throws JSONException {

        activitiesAdapter.clear();

        JSONArray activities = response.getJSONArray("activities");

        for(int i = 0; i < activities.length(); i++) {
            int id = activities.getJSONObject(i).getInt("id");
            String description = activities.getJSONObject(i).getString("description");
            boolean active = activities.getJSONObject(i).getBoolean("active");

            activitiesAdapter.add(new ActivitiesItem(id, description, active));
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
