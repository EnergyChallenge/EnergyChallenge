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

import de.unikiel.klik.energychallenge.Config;
import de.unikiel.klik.energychallenge.R;
import de.unikiel.klik.energychallenge.tasks.VerificationTask;
import de.unikiel.klik.energychallenge.utils.NetworkX;

/*
 * TODO
 * Use CurrentUser Class
 *
 */



/*The initial login activity which is bypassed if the user is already logged in*/
public class LoginActivity extends Activity {

    //Elements from the layout
    EditText emailEditText;
    EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Check if credentials are saved in shared preferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String persistentEmail = preferences.getString("email", "");
        String persistentPassword = preferences.getString("password", "");
        if(persistentEmail != "" && persistentPassword != ""){

            //Check if the credentials are correct with the server
            if (VerificationTask.checkCredentials(persistentEmail, persistentPassword)) {

                //Credentials valid, go to main activity
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }else{
                //Saved credentials invalid for some reason, remove them from shared preferences
                SharedPreferences.Editor editor = preferences.edit();
                editor.remove("email");
                editor.remove("password");
                editor.commit();
            }
        }

        //Load the state and login layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Credential inputs
        emailEditText = (EditText)findViewById(R.id.logInEmail);
        passwordEditText = (EditText)findViewById(R.id.logInPassword);
    }

    public void logIn(View view) {

        //TODO change uses of "username" to "email" across the app to avoid confusion

        // Change in Config for having Login bypass
        if (!Config.LOGIN_REQUIRED) {

            // Setting up example Settings
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("name", "Sebastian Vettel");
            editor.putString("email", "seabstian.vettel@ferrari.it");
            editor.putString("password", "password");
            editor.commit();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

            return;
        }

        //Only proceed if a network connection is available
        if(!NetworkX.isAvailable(getApplicationContext())) {

            //No network connection, display an error
            Context context = getApplicationContext();
            CharSequence message = "Can't login, your not connected to a network.";
            Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toast.show();

        }else{

            //Remove old saved credentials (if any)
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove("email");
            editor.remove("password");
            editor.commit();

            //Get credential strings
            final String email = emailEditText.getText().toString();
            final String password = passwordEditText.getText().toString();

            //Use the verification task to check the saved credentials
            if (VerificationTask.checkCredentials(email, password)) {

                //Save the correct credentials to the shared preferences (persistent storage)
                editor.putString("email", email);
                editor.putString("password", password);
                editor.commit();

                //Credentials valid, go to main activity
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            } else {

                //Credentials invalid, display an error
                Context context = getApplicationContext();
                CharSequence message = "Invalid username/password combination";
                Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
                toast.show();
            }

        }

    }

}
