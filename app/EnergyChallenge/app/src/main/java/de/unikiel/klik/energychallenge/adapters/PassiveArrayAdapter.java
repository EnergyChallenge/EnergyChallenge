package de.unikiel.klik.energychallenge.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

public class PassiveArrayAdapter<T> extends ArrayAdapter<T> {

    public PassiveArrayAdapter(Context context, int resource) {
        super(context, resource);
    }

    public PassiveArrayAdapter(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public PassiveArrayAdapter(Context context, int resource, T[] objects) {
        super(context, resource, objects);
    }

    public PassiveArrayAdapter(Context context, int resource, int textViewResourceId, T[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public PassiveArrayAdapter(Context context, int resource, List<T> objects) {
        super(context, resource, objects);
    }

    public PassiveArrayAdapter(Context context, int resource, int textViewResourceId, List<T> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }
}
