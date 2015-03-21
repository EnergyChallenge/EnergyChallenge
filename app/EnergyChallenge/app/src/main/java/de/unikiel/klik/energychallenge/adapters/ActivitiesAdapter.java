package de.unikiel.klik.energychallenge.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.unikiel.klik.energychallenge.R;
import de.unikiel.klik.energychallenge.dialogs.CompleteActivityDialog;
import de.unikiel.klik.energychallenge.models.ActivitiesItem;
import de.unikiel.klik.energychallenge.models.RankingItem;


public class ActivitiesAdapter extends ArrayAdapter<ActivitiesItem> {

    private Context context;

    public ActivitiesAdapter(Context context) {
        super(context, R.layout.list_ranking);
        this.context = context;
    }


    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;

        LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
        row = inflater.inflate(R.layout.list_activities, parent, false);

        TextView descriptionView = (TextView)row.findViewById(R.id.activities_description);
        Button doButton = (Button)row.findViewById(R.id.activities_do_button);

        descriptionView.setText(getItem(position).getDescription());

        doButton.setEnabled(getItem(position).isActive());

        doButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Activity activity = (Activity) context;

                DialogFragment dialog = new CompleteActivityDialog();
                dialog.show(activity.getFragmentManager(), "CompleteActivityDialog");

                //TODO

                String id = Integer.toString(getItem(position).getId());
                Toast.makeText(context, "You clicked id: " + id, Toast.LENGTH_SHORT).show();
            }
        });

        return row;
    }

}