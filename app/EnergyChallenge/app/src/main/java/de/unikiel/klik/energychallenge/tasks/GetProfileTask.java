package de.unikiel.klik.energychallenge.tasks;

import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.unikiel.klik.energychallenge.R;
import de.unikiel.klik.energychallenge.fragments.ProfileFragment;
import de.unikiel.klik.energychallenge.models.User;
import de.unikiel.klik.energychallenge.utils.ServerRequest;


/*
 * TODO
 * Currentlyy just for users
 * Refactor later, maybe combine Team/User
 */

public class GetProfileTask extends AccessServerTask {

    private ProfileFragment profileFragment;

    private GridLayout profileView;

    private LinearLayout progressIndicator;

    private TextView emptyListText;

    public GetProfileTask(ProfileFragment profileFragment,
                          GridLayout profileView, LinearLayout progressIndicator,
                          TextView emptyListText) {
        this.profileFragment = profileFragment;
        this.profileView = profileView;
        this.progressIndicator = progressIndicator;
        this.emptyListText = emptyListText;
    }

    @Override
    protected ServerRequest createServerRequest() {
        return new ServerRequest("getProfile");
    }

    @Override
    protected void handleServerResponse(JSONObject response) throws JSONException {

        JSONObject profile = response.getJSONObject("profile");

        int id = profile.getInt("id");
        String name = profile.getString("name");
        String team = profile.getString("team");
        String institute = profile.getString("institute");
        int points = profile.getInt("points");
        int position = profile.getInt("position");
        ArrayList<String> lastActivities = new ArrayList<>();

        JSONArray lastActivitiesInJson = profile.getJSONArray("lastActivities");
        for(int i = 0; i < lastActivitiesInJson.length(); i++) {
            lastActivities.add(lastActivitiesInJson.getString(i));
        }

        profileFragment.buildProfile(new User(id, name, team, institute, points, position, lastActivities));
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
        profileView.setVisibility(View.VISIBLE);
    }
}
