package de.unikiel.klik.energychallenge.tasks;

import android.content.Context;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.unikiel.klik.energychallenge.R;
import de.unikiel.klik.energychallenge.adapters.ActivitiesAdapter;
import de.unikiel.klik.energychallenge.models.ActivitiesItem;
import de.unikiel.klik.energychallenge.utils.ServerRequest;

public class CommentProposalTask extends AccessServerTask {

    private Context context;

    private int proposalId;

    private String comment;

    private int rating;

    public CommentProposalTask(Context context, int proposalId, String comment, int rating) {
        super(context);
        this.context = context;
        this.proposalId = proposalId;
        this.comment = comment;
        this.rating = rating;
    }

    @Override
    protected ServerRequest createServerRequest() {
        ArrayList<BasicNameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("comment", comment));
        parameters.add(new BasicNameValuePair("rating", Integer.toString(rating)));
        return new ServerRequest("commentProposal", proposalId, parameters);
    }

    @Override
    protected void handleServerResponse(JSONObject response) throws JSONException {
        if (response.getJSONObject("commentProposal").getBoolean("success")) {
            Toast.makeText(context, R.string.comment_proposal_successfull, Toast.LENGTH_SHORT).show();
        } else {
            handleCommentError();
        }
    }

    @Override
    protected void handleResponseError() {
        handleCommentError();
    }

    private void handleCommentError() {
        Toast.makeText(context, R.string.comment_proposal_failed, Toast.LENGTH_SHORT).show();
    }

}
