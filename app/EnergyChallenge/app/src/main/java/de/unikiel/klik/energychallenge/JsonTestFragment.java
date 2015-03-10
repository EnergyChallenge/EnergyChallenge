package de.unikiel.klik.energychallenge;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import de.unikiel.klik.energychallenge.utils.DownloadWebpageTask;


public class JsonTestFragment extends Fragment {

    public JsonTestFragment() {
    }

    /* Returns an new instance of this fragment */
    public static JsonTestFragment newInstance() {
        return new JsonTestFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Context context = getActivity().getApplicationContext();

        String stringUrl = "http://headers.jsontest.com/";
        ConnectivityManager connMgr = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            new DownloadWebpageTask().execute(stringUrl);

        } else {

            Toast.makeText(context, "No network connection available.", Toast.LENGTH_SHORT).show();

            Log.v("Output:", "No network connection available.");
        }

        //Swap in the layout for this fragment
        return inflater.inflate(R.layout.fragment_json_test, container, false);

    }
}