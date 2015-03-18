package de.unikiel.klik.energychallenge.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.unikiel.klik.energychallenge.R;

public class RankingAdapter extends ArrayAdapter<String> {

    ArrayList<JSONObject> data;

    public RankingAdapter(Context context) {
        super(context, android.R.layout.simple_list_item_1);
        //super(context, R.layout.list_ranking);
        //TODO Temp
        insertDefaultData();
    }


    //TODO TEMP
    private void insertDefaultData() {
        addAll(Arrays.asList(new String[] {"I'm", "Blue"}));
    }

}