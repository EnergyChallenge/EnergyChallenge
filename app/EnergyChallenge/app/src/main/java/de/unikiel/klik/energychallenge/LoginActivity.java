package de.unikiel.klik.energychallenge;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;


/*The initial login activity which is bypassed if the user is already logged in*/
public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TODO Check with the controller if the user is already logged in when they open the app
        //TODO If the saved login is correct go straight to the main activity

        //Load the state and login layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void logIn(View view) {
        //temporary login bypass
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        //TODO Login credentials check
    }
}
