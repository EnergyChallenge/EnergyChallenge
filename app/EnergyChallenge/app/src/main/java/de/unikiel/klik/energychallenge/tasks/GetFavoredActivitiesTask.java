package de.unikiel.klik.energychallenge.tasks;

import android.widget.LinearLayout;
import android.widget.TextView;

import de.unikiel.klik.energychallenge.adapters.ActivitiesAdapter;
import de.unikiel.klik.energychallenge.utils.ServerRequest;

public class GetFavoredActivitiesTask  extends GetActivitiesTask {

    public GetFavoredActivitiesTask(ActivitiesAdapter activitiesAdapter,
                                    LinearLayout progressIndicator,
                                    TextView emptyListText) {
        super(activitiesAdapter, progressIndicator, emptyListText);
    }

    @Override
    protected ServerRequest createServerRequest() {
        return new ServerRequest("getFavoredActivities");
    }

}
