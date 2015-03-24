package de.unikiel.klik.energychallenge.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import de.unikiel.klik.energychallenge.R;
import de.unikiel.klik.energychallenge.models.RankingItem;


public class RankingAdapter extends ArrayAdapter<RankingItem> {

    public RankingAdapter(Context context) {
        super(context, R.layout.list_ranking);
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
        row = inflater.inflate(R.layout.list_ranking, parent, false);

        TextView positionView = (TextView)row.findViewById(R.id.ranking_position);
        TextView titleView = (TextView)row.findViewById(R.id.ranking_title);
        TextView pointsView = (TextView)row.findViewById(R.id.ranking_points);

        positionView.setText(Integer.toString(getItem(position).getPosition()) + ".");
        titleView.setText(getItem(position).getTitle());
        pointsView.setText(Integer.toString(getItem(position).getPoints()));

        return row;
    }

}