package de.unikiel.klik.energychallenge.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

import de.unikiel.klik.energychallenge.R;


/*The initial login activity which is bypassed if the user is already logged in*/
public class LoginActivity extends Activity {

    EditText usernameEditText;
    EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TODO Check with the controller if the user is already logged in when they open the app
        //TODO If the saved login is correct go straight to the main activity

        //Load the state and login layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Credential inputs
        usernameEditText = (EditText)findViewById(R.id.logInUsername);
        passwordEditText = (EditText)findViewById(R.id.logInPassword);
    }

    public void logIn(View view) {
        //TODO change uses of "username" to "email" across the app to avoid confusion
        //Get credential strings
        final String usernameInput = usernameEditText.getText().toString();
        final String passwordInput = passwordEditText.getText().toString();

        //Remove old session id
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("SESSIONID_COOKIE");
        editor.commit();

        /* Seperate thread to attempt a login with the credentials provided*/
        Thread loginAttempt = new Thread(new Runnable(){
            @Override
            public void run(){
                try {
                    //Create a new http client from the default
                    DefaultHttpClient client = new DefaultHttpClient();

                    //Set the parameters, post them to the server and collect a response
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("email", usernameInput));
                    params.add(new BasicNameValuePair("password", passwordInput));
                    HttpPost post = new HttpPost("http://192.168.0.2:8080/Server/auth/signIn");
                    UrlEncodedFormEntity encodedEntity = new UrlEncodedFormEntity(params, "utf-8");
                    post.setEntity(encodedEntity);
                    HttpResponse response = client.execute(post);
                    HttpEntity responseEntity = response.getEntity();

                    //Check the response if one was given and act accordingly
                    if (responseEntity != null) {
                        //TODO check if the login was successful

                        //Get the cookie store from our http client
                        CookieStore cookieJar = client.getCookieStore();

                        //Get the cookies from the store
                        List<Cookie> cookies = cookieJar.getCookies();

                        //Find the session id cookie
                        for (int i = 0; i < cookies.size(); i++) {
                            Cookie currentCookie = cookies.get(i);
                            if(currentCookie.getName().equals("JSESSIONID")){
                                //If the correct cookie has been found get its session id value
                                String sessionIdCookie = currentCookie.getValue();

                                //Save the cookie to the shared preferences (persistent storage)
                                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("SESSIONID_COOKIE", sessionIdCookie);
                                editor.commit();

                                break;
                            }
                        }

                        //Log.i("RESPONSE", EntityUtils.toString(responseEntity));

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //Run the login thread
        loginAttempt.start();

        //Check if a session id was saved from the login attempt (correct credentials)
        if(preferences.getString("SESSIONID_COOKIE", "Not found") != "Not found"){

            //Login was successful, take the user to the main activity
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        }else{

            //Login was unsuccessful, display a message
            Context context = getApplicationContext();
            CharSequence message = "Oops! There was an issue signing in, please try again shortly";
            Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toast.show();

        }

        //temporary login bypass


        //TODO Login credentials check
    }
}
