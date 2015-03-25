package de.unikiel.klik.energychallenge.tasks;

import android.content.Context;
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
import de.unikiel.klik.energychallenge.models.Team;
import de.unikiel.klik.energychallenge.models.User;
import de.unikiel.klik.energychallenge.utils.ServerRequest;


/*
 * TODO
 * Refactor later, maybe combine Team/User
 */

public class GetTeamProfileTask extends AccessServerTask {

    private int profileId;

    private ProfileFragment profileFragment;

    private GridLayout profileView;

    private LinearLayout progressIndicator;

    private TextView emptyListText;

    public GetTeamProfileTask(Context applicationContext, int profileId, ProfileFragment profileFragment,
                              GridLayout profileView, LinearLayout progressIndicator,
                              TextView emptyListText) {
        super(applicationContext);
        this.profileId = profileId;
        this.profileFragment = profileFragment;
        this.profileView = profileView;
        this.progressIndicator = progressIndicator;
        this.emptyListText = emptyListText;
    }

    @Override
    protected ServerRequest createServerRequest() {
        return new ServerRequest("teamProfile", profileId);
        //return new ServerRequest("userProfile", 10); //TODO
    }

    @Override
    protected void handleServerResponse(JSONObject response) throws JSONException {

        JSONObject profile = response.getJSONObject("profile");

        String name = profile.getString("name");
        int points = profile.getInt("points");
        int position = profile.getInt("position");
        ArrayList<String> lastActivities = new ArrayList<>();
        ArrayList<String> members = new ArrayList<>();

        JSONArray lastActivitiesInJson = profile.getJSONArray("lastActivities");
        for(int i = 0; i < lastActivitiesInJson.length(); i++) {
            lastActivities.add(lastActivitiesInJson.getString(i));
        }

        JSONArray membersInJson = profile.getJSONArray("members");
        for(int i = 0; i < membersInJson.length(); i++) {
            members.add(membersInJson.getString(i));
        }

        profileFragment.buildProfile(new Team(name, points, position, lastActivities, members));
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
