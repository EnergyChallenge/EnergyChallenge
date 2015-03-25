package de.unikiel.klik.energychallenge.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Contacts;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import de.unikiel.klik.energychallenge.R;
import de.unikiel.klik.energychallenge.adapters.ProposalCommentsAdapter;
import de.unikiel.klik.energychallenge.models.Proposal;

public class ProposalActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposal);

        Intent intent = getIntent();
        Proposal proposal = intent.getParcelableExtra("proposal");


        TextView headlineView = (TextView)findViewById(R.id.proposal_headline);
        RatingBar ratingView = (RatingBar)findViewById(R.id.proposal_rating);
        TextView descriptionView = (TextView)findViewById(R.id.proposal_description);
        ListView commentsListView = (ListView)findViewById(R.id.proposal_comment_list);

        View ownCommentView = getLayoutInflater().inflate(R.layout.footer_list_proposal, null);
        EditText ownCommentDescView = (EditText) ownCommentView.findViewById(R.id.proposal_own_comment_text);
        RatingBar ownRatingView = (RatingBar) ownCommentView.findViewById(R.id.proposal_own_rating);
        Button ownSubmitButton = (Button) ownCommentView.findViewById(R.id.proposal_own_submit);

        ProposalCommentsAdapter proposalCommentsAdapter = new ProposalCommentsAdapter(this);
        proposalCommentsAdapter.addAll(proposal.getComments());

        headlineView.setText(proposal.getAuthor() + ":");
        ratingView.setRating(proposal.getRating());
        descriptionView.setText(proposal.getDescription());

        ownCommentDescView.setText(proposal.getOwnComment().getText());
        ownRatingView.setRating(proposal.getOwnComment().getRating());

        commentsListView.addFooterView(ownCommentView);
        commentsListView.setAdapter(proposalCommentsAdapter);

        // TODO Implement own Comment
        // TODO Get Own Comment data and insert it to fields
        // TODO implement onCLick Event for Button
        // TODO Call Task for submitting comment
        // TODO Then reload Proposal
    }

}
