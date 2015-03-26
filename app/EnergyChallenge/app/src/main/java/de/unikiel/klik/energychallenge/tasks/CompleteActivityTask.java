package de.unikiel.klik.energychallenge.tasks;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import de.unikiel.klik.energychallenge.R;
import de.unikiel.klik.energychallenge.adapters.ActivitiesAdapter;
import de.unikiel.klik.energychallenge.models.ActivitiesItem;
import de.unikiel.klik.energychallenge.utils.ServerRequest;

public class CompleteActivityTask extends AccessServerTask {

    private Context context;

    private ActivitiesAdapter activitiesAdapter;

    private ActivitiesItem activity;

    public CompleteActivityTask(Context context, ActivitiesAdapter activitiesAdapter, ActivitiesItem activity) {
        super(context);
        this.context = context;
        this.activitiesAdapter = activitiesAdapter;
        this.activity = activity;
    }

    @Override
    protected ServerRequest createServerRequest() {
        return new ServerRequest("completeActivity", activity.getId());
    }

    @Override
    protected void handleServerResponse(JSONObject response) throws JSONException {
        if (response.getJSONObject("completeActivity").getBoolean("success")) {
            Toast.makeText(context, R.string.complete_activity_successfull, Toast.LENGTH_SHORT).show();
            activity.setActive(false);
            activitiesAdapter.notifyDataSetChanged();
        } else {
            handleCompletionError();
        }
    }

    @Override
    protected void handleResponseError() {
        handleCompletionError();
    }

    private void handleCompletionError() {
        Toast.makeText(context, R.string.complete_activity_failed, Toast.LENGTH_SHORT).show();
    }

}
