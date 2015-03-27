package de.unikiel.klik.energychallenge.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.Contacts;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import de.unikiel.klik.energychallenge.R;
import de.unikiel.klik.energychallenge.adapters.ProposalCommentsAdapter;
import de.unikiel.klik.energychallenge.models.ActivitiesItem;
import de.unikiel.klik.energychallenge.models.Proposal;
import de.unikiel.klik.energychallenge.tasks.CommentProposalTask;
import de.unikiel.klik.energychallenge.tasks.CompleteActivityTask;
import de.unikiel.klik.energychallenge.utils.NetworkX;

public class ProposalActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposal);

        Intent intent = getIntent();
        final Proposal proposal = intent.getParcelableExtra("proposal");


        TextView headlineView = (TextView)findViewById(R.id.proposal_headline);
        RatingBar ratingView = (RatingBar)findViewById(R.id.proposal_rating);
        TextView descriptionView = (TextView)findViewById(R.id.proposal_description);
        ListView commentsListView = (ListView)findViewById(R.id.proposal_comment_list);

        View ownCommentView = getLayoutInflater().inflate(R.layout.footer_list_proposal, null);
        final EditText ownCommentDescView = (EditText) ownCommentView.findViewById(R.id.proposal_own_comment_text);
        final RatingBar ownRatingView = (RatingBar) ownCommentView.findViewById(R.id.proposal_own_rating);
        final Button ownSubmitButton = (Button) ownCommentView.findViewById(R.id.proposal_own_submit);

        ProposalCommentsAdapter proposalCommentsAdapter = new ProposalCommentsAdapter(this);
        if (proposal.getComments() != null) {
            proposalCommentsAdapter.addAll(proposal.getComments());
        }

        headlineView.setText(proposal.getAuthor() + ":");
        ratingView.setRating(proposal.getRating());
        descriptionView.setText(proposal.getDescription());

        ratingView.getProgressDrawable().setTint(Color.parseColor("#B3C833"));
        ownRatingView.getProgressDrawable().setTint(Color.parseColor("#B3C833"));

        commentsListView.addFooterView(ownCommentView);
        commentsListView.setAdapter(proposalCommentsAdapter);

        if (proposal.getOwnComment() != null) {
            ownCommentDescView.setText(proposal.getOwnComment().getText());
            ownRatingView.setRating(proposal.getOwnComment().getRating());
        }

        ownSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String commentText = ownCommentDescView.getText().toString();
                int rating = (int) ownRatingView.getRating();

                if (NetworkX.isAvailable(ProposalActivity.this)) {
                    new CommentProposalTask(ProposalActivity.this, proposal.getId(), commentText, rating).execute();
                } else {
                    Toast.makeText(ProposalActivity.this, R.string.no_network_connection, Toast.LENGTH_SHORT).show();
                }

                finish();
            }
        });

        if (ownRatingView.getRating() == 0) {
            ownSubmitButton.setEnabled(false);
        }

        ownRatingView.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (ownRatingView.getRating() != 0) {
                    ownSubmitButton.setEnabled(true);
                }
            }
        });
    }

}
