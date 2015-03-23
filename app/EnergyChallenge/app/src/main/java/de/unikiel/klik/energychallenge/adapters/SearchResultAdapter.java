package de.unikiel.klik.energychallenge.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import de.unikiel.klik.energychallenge.models.SearchResultItem;

public class SearchResultAdapter extends ArrayAdapter<SearchResultItem> {

    public SearchResultAdapter(Context context) {
        super(context, android.R.layout.simple_list_item_1);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
        row = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);

        TextView nameView = (TextView)row.findViewById(android.R.id.text1);
        String type = (getItem(position).getType() == "user") ? "Benutzer" : "Team";
        nameView.setText(getItem(position).getName() + " (" + type + ")");

        return row;
    }

}
