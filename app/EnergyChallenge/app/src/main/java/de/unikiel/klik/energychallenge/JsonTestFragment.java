package de.unikiel.klik.energychallenge;


import android.app.ListFragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.unikiel.klik.energychallenge.utils.DownloadWebpageTask;


public class JsonTestFragment extends Fragment {

    private List<String> your_array_list = new ArrayList<String>();
    private ArrayAdapter adapter;

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
        View view = inflater.inflate(R.layout.fragment_json_test, container, false);

        Button button = (Button) view.findViewById(R.id.button_send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonPressed();
            }
        });


        your_array_list.add("foo");
        your_array_list.add("bar");


        ListView list = (ListView) view.findViewById(R.id.list);
        adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, your_array_list);
        list.setAdapter(adapter);


        return view;
    }

    public void buttonPressed() {
        Context context = getActivity().getApplicationContext();
        Toast.makeText(context, "Buttton pressed!.", Toast.LENGTH_SHORT).show();

        your_array_list.add("Moin Moin");
        your_array_list.add("Was geht ab?");
        adapter.notifyDataSetChanged();

    }

    private void getDataFromServer() {
        getDataFromServer("");
    }
    private void getDataFromServer(String text) {

    }

}