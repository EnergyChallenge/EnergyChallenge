package de.unikiel.klik.energychallenge.utils;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import de.unikiel.klik.energychallenge.Config;

// TODO Handle Exceptions

public abstract class GetDataFromServerTask extends AsyncTask<String, Void, String> {

    protected abstract ServerRequest createServerRequest();

    protected abstract void handleServerResponse(JSONObject response);

    @Override
    protected final String doInBackground(String... params) {
        try {
            return performServerRequest(createServerRequest());
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    protected final void onPostExecute(String result) {
        try {
            handleServerResponse(new JSONObject(result));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private final String performServerRequest(ServerRequest serverRequest) throws IOException{
        InputStream inputStream = null;
        int length = 1024;

        try {
            URL url = new URL(Config.SERVER_REST_PATH + serverRequest.getReceiverOnServer() + ".json" /*TODO*/);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            inputStream = conn.getInputStream();

            // Convert the InputStream into a string
            //return IoX.readInputStream(inputStream, length);
             return IoX.readInputStream(inputStream, length);

        // Makes sure that the InputStream is closed after the app is
        // finished using it.
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

}
