package de.unikiel.klik.energychallenge.utils;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetRankingDataTask extends AsyncTask<String, Void, String> {

    private List<String> rankingTeamData = new ArrayList<>();

    private ArrayAdapter rankingTeamAdapter;

    public GetRankingDataTask(List<String> rankingTeamData, ArrayAdapter rankingTeamAdapter) {
        this.rankingTeamData = rankingTeamData;
        this.rankingTeamAdapter = rankingTeamAdapter;
    }


    @Override
    protected String doInBackground(String... params) {

        try {
            return downloadUrl();
        } catch (IOException e) {
            return "Unable to retrieve web page. URL may be invalid.";
        }

    }

    // onPostExecute displays the results of the AsyncTask.
    @Override
    protected void onPostExecute(String result) {
        //Log.v("Result:", result);
        //Do something

        try {
            JSONObject resultInJson = new JSONObject(result);
            JSONArray ranking = resultInJson.getJSONArray("Ranking");

            for(int i = 0; i < ranking.length(); i++) {
                rankingTeamData.add(ranking.getJSONObject(i).getString("Name"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }



        rankingTeamAdapter.notifyDataSetChanged();
        Log.v("Task:", "Läuft");
    }

    private String downloadUrl() throws IOException {
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 500;

        try {
            URL url = new URL("http://soerenhenning.de/getRankingTeams.json");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is, len);

            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }

    }

    // Reads an InputStream and converts it to a String.
    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }

}
