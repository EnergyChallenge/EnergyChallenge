package de.unikiel.klik.energychallenge.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.unikiel.klik.energychallenge.Config;
import de.unikiel.klik.energychallenge.activities.LoginActivity;
import de.unikiel.klik.energychallenge.utils.CurrentUser;
import de.unikiel.klik.energychallenge.utils.IoX;
import de.unikiel.klik.energychallenge.utils.ServerRequest;

public abstract class AccessServerTask extends AsyncTask<String, Void, String> {

    private CurrentUser currentUser;

    private Context context;

    private static final String TAG = "AcessServerTask"; //Tag for Logs

    public AccessServerTask(Context context) {
        this.context = context;
        currentUser = new CurrentUser(context.getApplicationContext());
    }

    protected abstract ServerRequest createServerRequest();

    protected abstract void handleServerResponse(JSONObject response) throws JSONException;

    protected abstract void  handleResponseError();

    protected void doBeforeRequest() {} // Do nothing by default

    protected void doAfterResponse() {} // Do nothing by default

    protected boolean isLoginRequired() {
        return true; //True by default
    }

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

        if (result.equals("Error")) {
            Log.w(TAG, "Could not access Server!");
            handleResponseError();
        }

        try {
            JSONObject response = new JSONObject(result);
            if (isLoginRequired() && !response.getBoolean("authentication")) {
                logout();
            } else {
                handleServerResponse(response.getJSONObject("result"));
            }
        } catch (JSONException e) {
            Log.e(TAG, "Error in JSON");
            e.printStackTrace();
            handleResponseError();
        }

        doAfterResponse();
    }

    private final String performServerRequest(ServerRequest serverRequest) throws IOException {

        String requestUrl = Config.SERVER_REST_PATH + serverRequest.getReceiverOnServer();
        if (serverRequest.isIdSet()) {
            requestUrl += "/" + serverRequest.getId();
        }
        if (Config.AVERT_SERVER_CACHING) {
            requestUrl += "?i=" + Long.toString(System.currentTimeMillis() / 1000);
        }

        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(requestUrl);

        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        if (serverRequest.getParameters() != null) {
            parameters.addAll(serverRequest.getParameters());
        }

        if (isLoginRequired()) {
            parameters.add(new BasicNameValuePair("email", currentUser.getEmail()));
            parameters.add(new BasicNameValuePair("password", currentUser.getPassword()));
        }

        UrlEncodedFormEntity encodedEntity = new UrlEncodedFormEntity(parameters, "utf-8");
        post.setEntity(encodedEntity);
        HttpResponse httpResponse = client.execute(post);
        HttpEntity responseEntity = httpResponse.getEntity();
        return EntityUtils.toString(responseEntity, HTTP.UTF_8);

    }

    private final void logout() {
        currentUser.clear();

        Intent loginIntent = new Intent(context, LoginActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(loginIntent);
    }
}
