package de.unikiel.klik.energychallenge.tasks;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import de.unikiel.klik.energychallenge.adapters.ActivitiesAdapter;
import de.unikiel.klik.energychallenge.utils.ServerRequest;

public class GetFavoredActivitiesTask  extends GetActivitiesTask {

    public GetFavoredActivitiesTask(Context applicationContext,
                                    ActivitiesAdapter activitiesAdapter,
                                    LinearLayout progressIndicator,
                                    TextView emptyListText) {
        super(applicationContext, activitiesAdapter, progressIndicator, emptyListText);
    }

    @Override
    protected ServerRequest createServerRequest() {
        return new ServerRequest("favoredActivities");
    }

}
