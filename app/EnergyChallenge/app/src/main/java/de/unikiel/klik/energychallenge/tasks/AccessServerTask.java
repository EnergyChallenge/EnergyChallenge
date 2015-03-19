package de.unikiel.klik.energychallenge.tasks;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import de.unikiel.klik.energychallenge.Config;
import de.unikiel.klik.energychallenge.utils.IoX;
import de.unikiel.klik.energychallenge.utils.ServerRequest;


public abstract class AccessServerTask extends AsyncTask<String, Void, String> {

    private static final String TAG = "AcessServerTask"; //Tag for Logs

    protected abstract ServerRequest createServerRequest();

    protected abstract void handleServerResponse(JSONObject response) throws JSONException;

    protected abstract void  handleResponseError();

    protected void doBeforeRequest() {} // Do nothing by default

    protected void doAfterResponse() {} // Do nothing by default

    @Override
    protected final void onPreExecute() {
        doBeforeRequest();
    }

    @Override
    protected final String doInBackground(String... params) {
        try {
            return performServerRequest(createServerRequest());
        } catch (IOException e) {
            e.printStackTrace();
            return "Error";
        }
    }

    @Override
    protected final void onPostExecute(String result) {

        if (result == "Error") {
            Log.w(TAG, "Could not access Server!");
            handleResponseError();
        }

        try {
            handleServerResponse(new JSONObject(result));
        } catch (JSONException e) {
            Log.e(TAG, "Error in JSON");
            e.printStackTrace();
            handleResponseError();
        }

        doAfterResponse();
    }

    private final String performServerRequest(ServerRequest serverRequest) throws IOException{
        InputStream inputStream = null;
        int length = 1024;

        try {
            URL url = new URL(Config.SERVER_REST_PATH
                            + serverRequest.getReceiverOnServer()
                            + Config.SERVER_REST_PATH_END);

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
