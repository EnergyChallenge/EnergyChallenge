package de.unikiel.klik.energychallenge.tasks;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.unikiel.klik.energychallenge.R;
import de.unikiel.klik.energychallenge.adapters.ProposalsAdapter;
import de.unikiel.klik.energychallenge.models.Proposal;
import de.unikiel.klik.energychallenge.models.ProposalComment;
import de.unikiel.klik.energychallenge.utils.ServerRequest;

public class GetProposalsTask extends AccessServerTask {

    private ProposalsAdapter proposalsAdapter;

    private LinearLayout progressIndicator;

    private TextView emptyListText;

    public GetProposalsTask(ProposalsAdapter proposalsAdapter,
                             LinearLayout progressIndicator,
                             TextView emptyListText) {
        this.proposalsAdapter = proposalsAdapter;
        this.progressIndicator = progressIndicator;
        this.emptyListText = emptyListText;
    }

    @Override
    protected ServerRequest createServerRequest() {
        return new ServerRequest("proposals");
    }

    @Override
    protected void handleServerResponse(JSONObject response) throws JSONException {

        proposalsAdapter.clear();

        JSONArray proposals = response.getJSONArray("proposals");

        for(int i = 0; i < proposals.length(); i++) {

            int id = proposals.getJSONObject(i).getInt("id");
            String description = proposals.getJSONObject(i).getString("description");
            String author = proposals.getJSONObject(i).getString("author");
            float rating = (float) proposals.getJSONObject(i).getDouble("rating");
            ArrayList<ProposalComment> comments = new ArrayList<>();

            JSONArray commentsInJson = proposals.getJSONObject(i).getJSONArray("comments");
            for(int j = 0; j < commentsInJson.length(); j++) {

                int commentId = commentsInJson.getJSONObject(j).getInt("id");
                String commentAuthor = commentsInJson.getJSONObject(j).getString("author");
                int commentRating = commentsInJson.getJSONObject(j).getInt("rating");
                String commentText = commentsInJson.getJSONObject(j).getString("text");

                comments.add(new ProposalComment(commentId, commentAuthor, commentRating, commentText));
            }

            proposalsAdapter.add(new Proposal(id, description, author, rating, comments));
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
