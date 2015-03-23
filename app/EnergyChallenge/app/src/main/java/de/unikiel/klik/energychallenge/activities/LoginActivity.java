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


/*The initial login activity which is bypassed if the user is already logged in*/
public class LoginActivity extends Activity {

    //Elements from the layout
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

        // Change in Config for having Login bypass
        if (!Config.LOGIN_REQUIRED) {

            // Setting up example Settings
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("name", "seabstian.vettel@ferrari.it");
            editor.putString("email", "Sebastian Vettel");
            editor.commit();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

            return;
        }

        //Only proceed if a network connection is available
        if(!NetworkX.isAvailable(getApplicationContext())) {

            //Credentials invalid, display an error
            Context context = getApplicationContext();
            CharSequence message = "Can't login, your not connected to a network.";
            Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toast.show();

        }else{

            //Get credential strings
            final String usernameInput = usernameEditText.getText().toString();
            final String passwordInput = passwordEditText.getText().toString();

            //Save the credentials to the shared preferences (persistent storage)
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("email", usernameInput);
            editor.putString("password", passwordInput);
            editor.commit();

            //Get the saved email and password from the shared preferences (persistant data)
            final String email = preferences.getString("email", "Not found");
            final String password = preferences.getString("password", "Not found");

            //Use the verification task to check the saved credentials
            if (VerificationTask.checkCredentials(email, password)) {

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
