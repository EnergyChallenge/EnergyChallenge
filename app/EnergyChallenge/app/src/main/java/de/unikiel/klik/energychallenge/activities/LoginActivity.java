package de.unikiel.klik.energychallenge.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import de.unikiel.klik.energychallenge.Config;
import de.unikiel.klik.energychallenge.R;
import de.unikiel.klik.energychallenge.tasks.LoginTask;
import de.unikiel.klik.energychallenge.utils.CurrentUser;
import de.unikiel.klik.energychallenge.utils.NetworkX;

/**
 * The initial login activity which is bypassed
 * if the user is already logged in
 * */
public class LoginActivity extends Activity {

    private EditText emailEditText;

    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        CurrentUser currentUser = new CurrentUser(getApplicationContext());

        // A user is sign in so we don't have to ask for credentials again
        if(!currentUser.getEmail().equals("") && !currentUser.getPassword().equals("")) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        //Load the state and login layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Credential inputs
        emailEditText = (EditText)findViewById(R.id.logInEmail);
        passwordEditText = (EditText)findViewById(R.id.logInPassword);
    }

    public void logIn(View view) {

        // Change in Config for having Login bypass
        if (!Config.LOGIN_REQUIRED) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return;
        }

        //Only proceed if a network connection is available
        if(NetworkX.isAvailable(this)) {

            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            new LoginTask(this, email, password).execute();

        } else{
            Toast.makeText(this, R.string.no_network_connection, Toast.LENGTH_SHORT).show();
        }

    }

}
