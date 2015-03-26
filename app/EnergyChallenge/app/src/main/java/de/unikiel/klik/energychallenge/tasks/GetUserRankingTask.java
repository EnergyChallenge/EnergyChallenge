package de.unikiel.klik.energychallenge.tasks;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.unikiel.klik.energychallenge.R;
import de.unikiel.klik.energychallenge.adapters.RankingAdapter;
import de.unikiel.klik.energychallenge.models.RankingItem;
import de.unikiel.klik.energychallenge.utils.ServerRequest;

public class GetUserRankingTask extends GetRankingTask {

    public GetUserRankingTask(Context context, RankingAdapter rankingAdapter,
                              LinearLayout progressIndicator, TextView emptyListText) {
        super(context, rankingAdapter, progressIndicator, emptyListText);
    }

    @Override
    protected String getReceiverOnServer() {
        return "userRanking";
    }

}
