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

/* TODO
 * Merge GetTeamRanking and GetUserRanking to one and extend it
 */


public class GetTeamRankingTask extends AccessServerTask {

    private RankingAdapter rankingAdapter;

    private LinearLayout progressIndicator;

    private TextView emptyListText;

    public GetTeamRankingTask(Context context,
                              RankingAdapter rankingAdapter,
                              LinearLayout progressIndicator,
                              TextView emptyListText) {
        super(context);
        this.rankingAdapter = rankingAdapter;
        this.progressIndicator = progressIndicator;
        this.emptyListText = emptyListText;
    }

    @Override
    protected ServerRequest createServerRequest() {
        return new ServerRequest("teamRanking");
    }

    @Override
    protected void handleServerResponse(JSONObject response) throws JSONException {

        rankingAdapter.clear();

        JSONArray ranking = response.getJSONArray("ranking");

        for(int i = 0; i < ranking.length(); i++) {
            int position = i + 1;
            int id = ranking.getJSONObject(i).getInt("id");
            String title = ranking.getJSONObject(i).getString("title");
            int points = ranking.getJSONObject(i).getInt("points");

            rankingAdapter.add(new RankingItem(position, id, title, points));
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
