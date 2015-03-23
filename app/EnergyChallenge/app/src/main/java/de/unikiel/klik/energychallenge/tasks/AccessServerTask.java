package de.unikiel.klik.energychallenge.tasks;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import de.unikiel.klik.energychallenge.Config;
import de.unikiel.klik.energychallenge.utils.ServerRequest;

/*
 * TODO
 *
 * Rename/-factor doAfterRequest -> handleResponseSuccess
 * Just call it on Success
 *
 */


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

    private final String performServerRequest(ServerRequest serverRequest) throws IOException {

        //TODO Put serverRequest.requestData in http request


        String requestUrl = Config.SERVER_REST_PATH + serverRequest.getReceiverOnServer() + Config.SERVER_REST_PATH_END;

        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(requestUrl);
        HttpResponse httpResponse = client.execute(post);
        HttpEntity responseEntity = httpResponse.getEntity();
        String response = EntityUtils.toString(responseEntity, HTTP.UTF_8);

        return response;

    }
}
