package de.unikiel.klik.energychallenge.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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
import java.util.Arrays;
import java.util.List;

import de.unikiel.klik.energychallenge.Config;
import de.unikiel.klik.energychallenge.R;
import de.unikiel.klik.energychallenge.activities.LoginActivity;
import de.unikiel.klik.energychallenge.activities.MainActivity;
import de.unikiel.klik.energychallenge.utils.CurrentUser;
import de.unikiel.klik.energychallenge.utils.ServerRequest;


public class LoginTask  extends AccessServerTask {

    private Context context;

    private String email;

    private String password;

    private ProgressDialog dialog;

    public LoginTask(Context context, String email, String password) {
        super(context);
        this.context = context;
        this.email = email;
        this.password = password;
    }

    protected boolean isLoginRequired() {
        return false; //Because app performs login itself
    }

    @Override
    protected ServerRequest createServerRequest() {
        ArrayList<BasicNameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("email", email));
        parameters.add(new BasicNameValuePair("password", password));
        return new ServerRequest("login", parameters);
    }

    @Override
    protected void handleServerResponse(JSONObject response) {

        try {
            JSONObject user = response.getJSONObject("user");
            performLogin(user.getString("name"), user.getInt("id"));
        } catch (JSONException e) {
            Toast.makeText(context, R.string.invalide_login, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void handleResponseError() {
        Toast.makeText(context, R.string.error_in_server_communication, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void doBeforeRequest() {
        // Show Loading Dialog
        dialog = new ProgressDialog(context);
        dialog.show();
    }

    @Override
    protected void doAfterResponse() {
        // Hide Loading Dialog
        dialog.dismiss();
    }

    private void performLogin(String userName, int userId) {

        CurrentUser currentUser = new CurrentUser(context.getApplicationContext());
        currentUser.setEmail(email);
        currentUser.setPassword(password);
        currentUser.setId(userId);
        currentUser.setName(userName);

        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

}
