package de.unikiel.klik.energychallenge.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import de.unikiel.klik.energychallenge.R;
import de.unikiel.klik.energychallenge.models.ProposalComment;


public class ProposalCommentsAdapter extends ArrayAdapter<ProposalComment> {

    private Context context;

    public ProposalCommentsAdapter(Context context) {
        super(context, R.layout.list_ranking);
        this.context = context;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
        row = inflater.inflate(R.layout.list_proposal_comments, parent, false);

        TextView authorView = (TextView)row.findViewById(R.id.proposal_comment_author);
        RatingBar ratingView = (RatingBar)row.findViewById(R.id.proposal_comment_rating);
        TextView textView = (TextView)row.findViewById(R.id.proposal_comment_text);

        ratingView.getProgressDrawable().setTint(Color.parseColor("#B3C833"));

        authorView.setText(getItem(position).getAuthor());
        ratingView.setRating(getItem(position).getRating());
        textView.setText(getItem(position).getText());

        return row;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }
}