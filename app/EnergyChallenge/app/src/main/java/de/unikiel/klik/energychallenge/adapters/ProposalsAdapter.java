package de.unikiel.klik.energychallenge.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import de.unikiel.klik.energychallenge.R;
import de.unikiel.klik.energychallenge.models.Proposal;


public class ProposalsAdapter extends ArrayAdapter<Proposal> {

    private Context context;

    public ProposalsAdapter(Context context) {
        super(context, R.layout.list_ranking);
        this.context = context;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
        row = inflater.inflate(R.layout.list_proposals, parent, false);

        TextView descriptionView = (TextView)row.findViewById(R.id.proposal_description);
        TextView authorView = (TextView)row.findViewById(R.id.proposal_author);
        RatingBar ratingView = (RatingBar)row.findViewById(R.id.proposal_rating);
        TextView commentsView = (TextView)row.findViewById(R.id.proposal_comments);

        int countComments = getItem(position).getComments().size();
        String commentsText = Integer.toString(countComments) + " ";
        if (countComments == 1) {
            commentsText += context.getString(R.string.comment);
        } else {
            commentsText += context.getString(R.string.comments);
        }

        descriptionView.setText(getItem(position).getDescription());
        authorView.setText(getItem(position).getAuthor() + ":");
        commentsView.setText(commentsText);
        ratingView.setRating(getItem(position).getRating());

        return row;
    }

}